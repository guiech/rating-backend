package mobile.app.business;

import java.util.List;

import com.mongodb.DBObject;

import mobile.app.model.Product;

public interface ProductBusiness{
	
	public List<Product> getProductsByName(String name);
	
	public DBObject getProductDetailsById(String productId);
	
	public Product saveProduct(Product product, String username);
	
	public List<Product> getAll();

}
