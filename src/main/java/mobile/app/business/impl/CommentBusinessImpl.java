package mobile.app.business.impl;

import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;

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
	public List<Comment> getProductComments(String productId) {
		return commentRepository.findByProductId(productId);
	}

	@Override
	public DBObject saveComment(Comment comment, String productId, String username) {
		DBObject result = new BasicDBObject();
		Product product = productRepository.findById(productId);
		if(product != null) {
			product.increaseCommentsCount();
			comment.setProduct(product);
			comment.setCreateBy(userRepository.getByUsername(username));
			comment.setDate(new Date());
			comment.setLikesCount(0);
			comment.setDislikesCount(0);
			commentRepository.save(comment);
			// TODO improve this shit, should get rate avg with mongo query
			OptionalDouble average = commentRepository.findByProductId(productId).stream().mapToDouble(Comment::getStars).average();
			product.setRate(average.getAsDouble());
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
