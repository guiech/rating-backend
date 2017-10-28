package mobile.app.business;

import com.mongodb.DBObject;

import mobile.app.model.Product;

public interface ProductBusiness{
	
	public DBObject getProductsByName(String name);
	
	public DBObject getProductsByNameForResultPage(Integer page, String username, String brand);
	
	public DBObject getProductDetailsById(String productId, String username);

	public DBObject getProductByTags(String searchedText, Integer page);
	
	public Product saveProduct(Product product, String username);
	
	public DBObject getAll();

	public DBObject like(String productId, String username, int status);

}
