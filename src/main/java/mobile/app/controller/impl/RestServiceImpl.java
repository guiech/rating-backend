package mobile.app.controller.impl;

import java.util.List;

import mobile.app.business.ProductBusiness;
import mobile.app.controller.RestService;
import mobile.app.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestServiceImpl implements RestService {

	@Autowired
	private ProductBusiness productBusiness;

	//TODO Falta capa de negocios
	
	@Override
	@RequestMapping("/get")
	public List<Product> getProducts() {
		return  productBusiness.getAll();
	}

	@Override
	@RequestMapping("/insert")
	public void InsertProduct() {
		productBusiness.save(new Product("heladera","Enfria Bocha"));
	}
}
