package mobile.app.controller.impl;

import com.mongodb.DBObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mobile.app.config.BaseContext;
import mobile.app.controller.CommentRestService;
import mobile.app.model.Comment;

@RestController
@RequestMapping("/comment")
public class CommentRestServiceImpl extends BaseContext implements CommentRestService {

	@Override
	@RequestMapping(value ="/save/{productId}",method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public DBObject saveComment(@PathVariable String productId, @RequestBody Comment comment) {
		return commentBusiness.saveComment(comment, productId, getAuthInformation().getName());
	}

	@Override
	@RequestMapping(value ="/{productId}",method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public DBObject getProductComments(@PathVariable String productId, @RequestParam(required = false) Integer page) {
		return commentBusiness.getProductComments(productId, page, getAuthInformation().getName());
	}

	@Override
	@RequestMapping(value ="/user/{productId}",method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public DBObject getProductCommentByUser(@PathVariable String productId) {
		return commentBusiness.getProductCommentByUser(productId, getAuthInformation().getName());
	}

	@Override
	@RequestMapping(value ="/like/{commentId}",method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public DBObject like(@PathVariable String commentId) {
		return commentBusiness.like(commentId, getAuthInformation().getName(), 1);
	}

	@Override
	@RequestMapping(value ="/dislike/{commentId}",method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public DBObject unlike(@PathVariable String commentId) {
		return commentBusiness.like(commentId, getAuthInformation().getName(), -1);
	}

	@Override
	@RequestMapping(value ="/unlike/{commentId}",method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public DBObject removeLike(@PathVariable String commentId) {
		return commentBusiness.like(commentId, getAuthInformation().getName(), 0);
	}
}
