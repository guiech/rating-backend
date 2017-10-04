package mobile.app.business;

import java.util.List;

import com.mongodb.DBObject;

import mobile.app.model.Product;

public interface ProductBusiness{
	
	public DBObject getProductsByName(String name);
	
	public DBObject getProductDetailsById(String productId, String username);
	
	public Product saveProduct(Product product, String username);
	
	public DBObject getAll();

	public DBObject like(String productId, String username, int status);

}
