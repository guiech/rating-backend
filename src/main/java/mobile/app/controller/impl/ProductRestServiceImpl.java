package mobile.app.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mobile.app.business.ProductBusiness;
import mobile.app.config.BaseContext;
import mobile.app.controller.ProductRestService;
import mobile.app.model.Product;

@RestController
@RequestMapping("/product")
public class ProductRestServiceImpl extends BaseContext implements ProductRestService{

	@Autowired
	private ProductBusiness productBusiness;

	@Override
	@RequestMapping(value ="/{productId}",method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Product getProductById(@PathVariable String productId) {
		// TODO Auto-generated method stub
		return productBusiness.getProductDetailsById(productId);
	}

	@Override
	@RequestMapping(value ="/save",method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Product saveProduct() {
		return productBusiness.saveProduct();		
	}
}
