package mobile.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mobile.app.model.CommentLikes;

public interface CommentLikesRepository  extends MongoRepository<CommentLikes,String> {}