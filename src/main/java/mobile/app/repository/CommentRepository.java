package mobile.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import mobile.app.model.Comment;

@Transactional
public interface CommentRepository extends MongoRepository<Comment, String> {
	 List<Comment> findByProductId(String productId);
}
