package mobile.app.business;

import java.util.List;

import com.mongodb.DBObject;

import mobile.app.model.Comment;
import mobile.app.model.Product;

public interface ProductBusiness{
	
	public List<?> getProductComments(String productId);
	
	public List<?> getProductRate(String productId);
	
	public List<?> getProductsByName(String Name);
	
	public DBObject getProductDetailsById(String productId);
	
	public Product saveProduct();

	public Comment saveComment();
	
	public List<Product> getAll();

}
