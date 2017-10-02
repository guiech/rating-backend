package mobile.app.business;

import java.util.List;

import mobile.app.model.Product;

public interface UserBusiness {

	public List<Product> getLastSearchedProducts(String userId);
	
	public void deleteSearchedProduct(String userId,String productId);
	
	public void saveSearchedProduct(String userId,String productId);
	
}
