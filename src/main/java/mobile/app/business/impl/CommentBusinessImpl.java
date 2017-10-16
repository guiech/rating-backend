package mobile.app.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import mobile.app.model.CommentLikes;
import mobile.app.model.Product;
import mobile.app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mobile.app.business.CommentBusiness;
import mobile.app.model.Comment;

@Service
@Transactional
public class CommentBusinessImpl extends GenericBusiness implements CommentBusiness{

	public static final int pageSize = 4;

	@Override
	public DBObject getProductComments(String productId, Integer page, String username) {
		DBObject result = new BasicDBObject();
		result.put("success", true);
		PageRequest request = new PageRequest(page == null ? 0 : page.intValue(), pageSize, new Sort(Sort.Direction.DESC, "date"));

		Page<Comment> comments = commentRepository.findByProductIdPageable(productId, request);
		List<DBObject> commentList = new ArrayList<>(comments.getNumberOfElements());
		comments.forEach(comment -> {
			DBObject dbComment = new BasicDBObject();
			dbComment.put("id", comment.getId());
			dbComment.put("date", comment.getText());
			dbComment.put("stars", comment.getStars());
			dbComment.put("likesCount", comment.getLikesCount());
			dbComment.put("dislikesCount", comment.getDislikesCount());
			dbComment.put("date", comment.getDate());
			DBObject dbUser = new BasicDBObject();
			dbUser.put("name", comment.getCreateBy().getName());
			dbUser.put("username", comment.getCreateBy().getUsername());
			dbComment.put("createBy", dbUser);
			// TODO find a better way to know if user is auth or not
			CommentLikes commentLikes = null;
			if (!username.equals("anonymousUser")) {
				commentLikes = commentLikeRepository.findByUserIdAndCommentId(comment.getCreateBy().getId(), comment.getId());
			}
			dbComment.put("userLike", commentLikes != null ? commentLikes.getLikeStatus() : 0);
			commentList.add(dbComment);
		});



		DBObject dbComments = new BasicDBObject();
		dbComments.put("content", commentList);
		dbComments.put("last", comments.isLast());
		result.put("comments", dbComments);


		return result;
	}

	@Override
	public DBObject saveComment(Comment comment, String productId, String username) {
		DBObject result = new BasicDBObject();
		Product product = productRepository.findById(productId);
		if(product != null) {
			comment.setProduct(product);
			comment.setCreateBy(userRepository.getByUsername(username));
			comment.setDate(new Date());
			comment.setLikesCount(0);
			comment.setDislikesCount(0);
			commentRepository.save(comment);
			product.increaseCommentsCount();
			product.setRate(commentRepository.getStarsAverageByProductId(productId,this.mongoTemplate));
			productRepository.save(product);
			result.put("success", true);
			result.put("comment", comment);
		} else {
			result.put("success", false);
			result.put("error", "Product does not exist");
		}
		return result;
	}

	@Override
	public DBObject like(String commentId, String username, int status) {
		DBObject result = new BasicDBObject();
		result.put("success", true);
		try {
			User user = userRepository.getByUsername(username);
			Comment comment = commentRepository.findById(commentId);
			if(comment == null) {
				result.put("success", false);
				result.put("message", "Comment does not exist");
				return result;
			}

			CommentLikes commentLike = commentLikeRepository.findByUserIdAndCommentId(user.getId(), commentId);
			if (commentLike == null) {
				// Save new like
				if(status != 0) {
					commentLike = new CommentLikes();
					commentLike.setLikeStatus(status);
					commentLike.setCreateAt(new Date());
					commentLike.setComment(comment);
					commentLike.setUser(user);
					commentLikeRepository.save(commentLike);
					switch (status) {
						case 1:
							comment.increaseLikeCount();
							break;
						case -1:
							comment.increaseDislikeCount();
							break;
					}
					commentRepository.save(comment);
				}
			} else if (commentLike.getLikeStatus() != status) {
				// avoid updating DB if status is the same one
				// change like status
				switch (commentLike.getLikeStatus()) {
					case 1:
						comment.decreaseLikeCount();
						break;
					case -1:
						comment.decreaseDislikeCount();
						break;
				}
				switch (status) {
					case 1:
						comment.increaseLikeCount();
						break;
					case -1:
						comment.increaseDislikeCount();
						break;
				}
				commentLike.setLikeStatus(status);
				commentLike.setCreateAt(new Date());
				commentLikeRepository.save(commentLike);
				commentRepository.save(comment);
			}
		} catch (Exception e) {
			result.put("success", false);
			result.put("message", e.getMessage());
		}

		return result;
	}

}
