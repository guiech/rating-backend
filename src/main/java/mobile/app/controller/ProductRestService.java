package mobile.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mongodb.DBObject;

import mobile.app.model.Product;

public interface ProductRestService {
	
	public DBObject getProductById(String productId);

	public DBObject like(@PathVariable String productId);

	public DBObject unlike(@PathVariable String productId);

	public DBObject removeLike(@PathVariable String productId);

	public Product saveProduct(@RequestBody Product product);
	
	public DBObject getAllProducts();
	
	public DBObject getProductsByName(@PathVariable String name);
	

}
