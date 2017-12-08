package mobile.app.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import mobile.app.model.CommentLikes;
import mobile.app.model.Product;
import mobile.app.model.ProductMin;
import mobile.app.model.User;
import mobile.app.model.UserMin;
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
		List<Integer> likesList = new ArrayList<>(comments.getNumberOfElements());
        // TODO find a better way to know if user is auth or not
        final String userId = !username.equals("anonymousUser") ? userRepository.getByUsername(username).getId() : null;
        comments.forEach(comment -> {
			CommentLikes commentLikes = null;
			if (userId != null) {
				commentLikes = commentLikeRepository.findByUserIdAndCommentId(userId, comment.getId());
			}
			likesList.add(commentLikes != null ? commentLikes.getLikeStatus() : 0);
		});
		DBObject dbComments = new BasicDBObject();
		dbComments.put("content", comments.getContent());
		dbComments.put("likes", likesList);
		dbComments.put("last", comments.isLast());
		result.put("comments", dbComments);

		return result;
	}

	@Override
	public DBObject getProductCommentByUser(String productId, String username) {
		DBObject result = new BasicDBObject();
		final String userId = !username.equals("anonymousUser") ? userRepository.getByUsername(username).getId() : null;
		if(userId != null) {
			Comment comment = commentRepository.findByProductIdAndCreateById(productId, userRepository.getByUsername(username).getId());
			if(comment != null) {
				result.put("comment", comment);
			}
			result.put("exists",  comment != null);
			result.put("success", true);
		} else {
			result.put("success", false);
			result.put("message", "Unauthorized user");
		}
		return result;
	}

	@Override
	public DBObject saveComment(Comment comment, String productId, String username) {
		DBObject result = new BasicDBObject();
		if(comment.getStars() < 1 || comment.getStars() > 5) {
			result.put("success", false);
			result.put("message", "Stars value should be between 1 and 5");
			return result;
		}
		Product product = productRepository.findById(productId);
		if(product != null) {
			User user = userRepository.getByUsername(username);
			Comment existingComment = commentRepository.findByProductIdAndCreateById(productId, user.getId());
			if(comment.getId() != null) {
				if(existingComment != null && comment.getId().equals(existingComment.getId())) {
					if(existingComment.getCreateBy().getId().equals(user.getId())) {
						existingComment.setStars(comment.getStars());
						existingComment.setDate(new Date());
						existingComment.setText(comment.getText());
						commentRepository.save(existingComment);
						product.setRate(commentRepository.getStarsAverageByProductId(productId,this.mongoTemplate));
						productRepository.save(product);
						result.put("success", true);
						result.put("comment", existingComment);
						// TODO: delete likes and dislikes after editing comment???
					} else {
						result.put("success", false);
						result.put("message", "You can not edit messages from other user");
					}
				} else {
					result.put("success", false);
					result.put("message", "Un-existing message");
				}
			} else {
				if(existingComment != null) {
					result.put("success", false);
					result.put("message", "User already commented this product.");
				} else {
					comment.setProduct(ProductMin.parseProduct(product));
					comment.setCreateBy(UserMin.parseUser(user));
					comment.setDate(new Date());
					comment.setLikesCount(0);
					comment.setDislikesCount(0);
					commentRepository.save(comment);
					product.increaseCommentsCount();
					product.setRate(commentRepository.getStarsAverageByProductId(productId,this.mongoTemplate));
					productRepository.save(product);
					result.put("success", true);
					result.put("comment", comment);
				}
			}
		} else {
			result.put("success", false);
			result.put("message", "Product does not exist");
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
					commentLike.setUser(UserMin.parseUser(user));
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
