package mobile.app.repository.impl;

import mobile.app.config.BaseContext;
import mobile.app.model.Comment;
import mobile.app.repository.CommentRepository;
import mobile.app.repository.common.AverageResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

public abstract class CommentRepositoryImpl extends BaseContext implements CommentRepository {

    @Autowired
    protected MongoTemplate mongoTemplate;


    @Override
    public double getStarsAverageByProductId(String productId) {
        Aggregation agg = newAggregation(
                match(Criteria.where("product.$id").is(new ObjectId(productId))),
                group().avg("stars").as("average")
        );
        AggregationResults<AverageResult> res = mongoTemplate.aggregate(agg, Comment.class, AverageResult.class);
        System.out.println("---->"+res.getUniqueMappedResult().getAverage().doubleValue());
        return res.getUniqueMappedResult().getAverage().doubleValue();
    }
}
