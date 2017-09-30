package mobile.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mobile.app.model.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {}
