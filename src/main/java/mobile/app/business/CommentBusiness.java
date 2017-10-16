package mobile.app.business;

import java.util.List;

import com.mongodb.DBObject;
import mobile.app.model.Comment;

public interface CommentBusiness {

	public DBObject getProductComments(String productId, Integer page, String username);
	
	public DBObject saveComment(Comment comment, String productId, String username);

	public DBObject like(String productId, String username, int status);
	
}
