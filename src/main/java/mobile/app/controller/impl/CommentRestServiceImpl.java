package mobile.app.controller.impl;

import java.util.List;

import com.mongodb.DBObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public DBObject getProductComments(@PathVariable String productId) {
		return commentBusiness.getProductComments(productId);
	}
}
