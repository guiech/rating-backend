package mobile.app.business.impl;

import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import mobile.app.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mobile.app.business.CommentBusiness;
import mobile.app.model.Comment;

@Service
@Transactional
public class CommentBusinessImpl extends GenericBusiness implements CommentBusiness{

	@Override
	public DBObject getProductComments(String productId) {
		DBObject result = new BasicDBObject();
		result.put("success", true);
		result.put("comments", commentRepository.findByProductId(productId));
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
