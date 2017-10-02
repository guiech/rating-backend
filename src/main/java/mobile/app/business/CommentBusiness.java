package mobile.app.business;

import java.util.List;

import mobile.app.model.Comment;

public interface CommentBusiness {

	public List<Comment> getProductComments(String productId);
	
	public Comment saveComment(Comment comment);
	
}
