package mobile.app.business;

import java.util.List;

import mobile.app.model.Product;
import mobile.app.model.User;

public interface UserBusiness {

	public List<Product> getLastSearchedProducts(String userId);
	
	public void deleteSearchedProduct(String userId,String productId);
	
	public void saveSearchedProduct(String userName,String productId, String productName);
	
	public User register(User user);
	
}
