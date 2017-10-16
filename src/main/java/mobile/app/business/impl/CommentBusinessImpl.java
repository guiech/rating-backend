package mobile.app.business.impl;

import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import mobile.app.model.Product;
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
	public DBObject getProductComments(String productId, Integer page) {
		DBObject result = new BasicDBObject();
		result.put("success", true);
		PageRequest request = new PageRequest(page == null ? 0 : page.intValue(), pageSize, new Sort(Sort.Direction.DESC, "date"));
		result.put("comments", commentRepository.findByProductIdPageable(productId, request));
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

}
