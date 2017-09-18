package mobile.app.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mobile.app.business.ProductBusiness;
import mobile.app.config.BaseContext;
import mobile.app.controller.UserRestService;

@RestController
@RequestMapping("/product")
public class ProductRestServiceImpl extends BaseContext implements UserRestService{

	@Autowired
	private ProductBusiness productBusiness;
}
