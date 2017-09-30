package mobile.app.controller;

import java.util.List;

import com.mongodb.DBObject;

import mobile.app.model.Comment;
import mobile.app.model.Product;

public interface ProductRestService {
	
	public DBObject getProductById(String productId);
	
	public Product saveProduct();
	
	public Comment saveComment();
	
	public List<Product> getAllProducts();

}
