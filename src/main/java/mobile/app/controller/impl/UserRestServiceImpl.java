package mobile.app.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mobile.app.config.BaseContext;
import mobile.app.controller.UserRestService;

@RestController
@RequestMapping("/user")
public class UserRestServiceImpl extends BaseContext implements UserRestService{

}
