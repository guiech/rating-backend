package mobile.app.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import mobile.app.config.BaseContext;
import mobile.app.controller.UserRestService;
import mobile.app.exceptions.UserAlreadyRegisteredException;
import mobile.app.model.User;

@RestController
@RequestMapping("/user")
public class UserRestServiceImpl extends BaseContext implements UserRestService{

	@Override
	@RequestMapping(value ="/register",method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public User register(@RequestBody User user) {
		// TODO Auto-generated method stub
		return userBusiness.register(user);
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserAlreadyRegisteredException.class)
	private DBObject UserAlreadyRegistredExceptionHandler(UserAlreadyRegisteredException e) {
		LOG.error(e.getMessage(),e);
		DBObject dbObject = new BasicDBObject();
		dbObject.put("status", 400);
		return dbObject;
	}
}
