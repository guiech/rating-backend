package mobile.app.controller;

import mobile.app.model.Product;

public interface ProductRestService {
	
	public Product getProductById(String productId);
	
	public Product saveProduct();

}
