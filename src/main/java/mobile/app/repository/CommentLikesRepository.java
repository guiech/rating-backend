package mobile.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mobile.app.model.CommentLikes;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CommentLikesRepository  extends MongoRepository<CommentLikes,String> {

    CommentLikes findByUserIdAndCommentId(String username, String product);
}