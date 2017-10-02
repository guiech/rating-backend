package mobile.app.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mobile.app.config.BaseContext;
import mobile.app.controller.CommenttRestServ;
import mobile.app.model.Comment;

@RestController
@RequestMapping("/comment")
public class CommentRestServiceImpl extends BaseContext implements CommenttRestServ{

	@Override
	@RequestMapping(value ="/save",method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public Comment saveComment(@RequestBody Comment comment) {
		return commentBusiness.saveComment(comment);
	}

	@Override
	@RequestMapping(value ="/{productId}",method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Comment> getProductComments(@PathVariable String productId) {
		return commentBusiness.getProductComments(productId);
	}
}
