package mobile.app.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import mobile.app.model.Comment;
import mobile.app.repository.common.AverageResult;

@Transactional
public interface CommentRepository extends MongoRepository<Comment, String> {

    Comment findById(String id);

    @Query("{ product.id : { $eq : ?0}}")
    Page<Comment> findByProductIdPageable(@Param("productId") String productId, Pageable pageable);

    default double getStarsAverageByProductId(String productId, MongoTemplate mongoTemplate) {
        Aggregation agg = newAggregation(
            match(Criteria.where("product.$id").is(new ObjectId(productId))),
            group().avg("stars").as("average")
        );
        AggregationResults<AverageResult> res = mongoTemplate.aggregate(agg, Comment.class, AverageResult.class);
        return res.getUniqueMappedResult().getAverage().doubleValue();
    }
}
