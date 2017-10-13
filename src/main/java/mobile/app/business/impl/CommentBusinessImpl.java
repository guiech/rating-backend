package mobile.app.business.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import mobile.app.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
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

			// TODO move to DAO
			Aggregation agg = newAggregation(
					match(Criteria.where("product.$id").is(new ObjectId(productId))),
					group().avg("stars").as("promedio")
			);
			AggregationResults<Average> res = mongoTemplate.aggregate(agg, Comment.class, Average.class);
			product.setRate(res.getUniqueMappedResult().getPromedio());

			productRepository.save(product);
			result.put("success", true);
			result.put("comment", comment);
		} else {
			result.put("success", false);
			result.put("error", "Product does not exist");
		}
		return result;
	}

	private class Average {
		public String id;
		public Double promedio;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Double getPromedio() {
			return promedio;
		}

		public void setPromedio(Double promedio) {
			this.promedio = promedio;
		}
	}
	
}
