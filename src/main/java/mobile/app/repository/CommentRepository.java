package mobile.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import mobile.app.model.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
	 List<Comment> findByProductId(String productId);
}
