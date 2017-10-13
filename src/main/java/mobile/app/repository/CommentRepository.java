package mobile.app.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import mobile.app.model.Comment;
import mobile.app.repository.common.AverageResult;

@Transactional
public interface CommentRepository extends MongoRepository<Comment, String> {
	List<Comment> findByProductId(String productId);

	public default double getStarsAverageByProductId(String productId, MongoTemplate mongoTemplate) {
        Aggregation agg = newAggregation(
                match(Criteria.where("product.$id").is(new ObjectId(productId))),
                group().avg("stars").as("average")
        );
        AggregationResults<AverageResult> res = mongoTemplate.aggregate(agg, Comment.class, AverageResult.class);
        System.out.println("---->"+res.getUniqueMappedResult().getAverage().doubleValue());
        return res.getUniqueMappedResult().getAverage().doubleValue();
    }
}
