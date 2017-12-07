package mobile.app.controller;

import java.util.List;

import com.mongodb.DBObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import mobile.app.model.Comment;

public interface CommentRestService {
	
	public DBObject saveComment(@PathVariable String productId, @RequestBody Comment comment);
	
    public DBObject getProductComments(@PathVariable String productId, @PathVariable Integer page);

    public DBObject getProductCommentByUser(@PathVariable String productId);

    public DBObject like(@PathVariable String commentId);

    public DBObject unlike(@PathVariable String commentId);

    public DBObject removeLike(@PathVariable String commentId);

}
