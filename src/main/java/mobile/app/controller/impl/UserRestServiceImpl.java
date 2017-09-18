package mobile.app.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mobile.app.config.BaseContext;
import mobile.app.controller.UserRestService;
import mobile.app.services.UserService;

@RestController
@RequestMapping("/user")
public class UserRestServiceImpl extends BaseContext implements UserRestService{

	@Autowired
	private UserService userService;

	//TODO Falta capa de negocios
	
}
