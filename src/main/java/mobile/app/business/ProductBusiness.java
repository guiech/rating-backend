package mobile.app.business;

import java.util.List;

import mobile.app.model.Product;

public interface ProductBusiness{
	
	//TODO check id we need model comments and rating
	
	public List<?> getProductComments(String productId);
	
	public List<?> getProductRate(String productId);
	
	public List<?> getProductsByName(String Name);
	
	public Product getProductDetailsById(String productId);
	
	public Product saveProduct();

}
