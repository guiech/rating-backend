package mobile.app.controller;

import org.springframework.web.bind.annotation.RequestBody;

import mobile.app.model.User;

public interface UserRestService {

	public User register(@RequestBody User user);

}
