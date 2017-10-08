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
			// TODO improve this shit, should get rate avg with mongo query
			float total = comment.getStars();
			for(Comment c : commentRepository.findByProductId(productId)) {
				total += c.getStars();
			}
			product.setRate(total / (float)product.getCommentsCount());
			productRepository.save(product);
			commentRepository.save(comment);
			result.put("success", true);
			result.put("comment", comment);
		} else {
			result.put("success", false);
			result.put("error", "Product does not exist");
		}
		return result;
	}
	
}
