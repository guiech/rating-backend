package mobile.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import mobile.app.model.Comment;

public interface CommenttRestServ {
	
	public Comment saveComment(@RequestBody Comment comment);
	
    public List<Comment> getProductComments(@PathVariable String productId);

}
