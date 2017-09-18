package mobile.app.business;

import java.util.List;

import mobile.app.model.Product;

public interface ProductBusiness{
	
	//TODO check id we need model comments and rating
	
	public List<?> getProductComments(String productId) throws Exception;
	
	public List<?> getProductRate(String productId) throws Exception;
	
	public List<?> getProductsByName(String Name) throws Exception;
	
	public Product getProductDetailsById(String productId) throws Exception;

}
