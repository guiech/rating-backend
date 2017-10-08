package mobile.app.business;

import java.util.List;

import com.mongodb.DBObject;
import mobile.app.model.Comment;

public interface CommentBusiness {

	public List<Comment> getProductComments(String productId);
	
	public DBObject saveComment(Comment comment, String productId, String username);
	
}
