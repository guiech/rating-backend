package mobile.app.business;

import java.util.List;

import com.mongodb.DBObject;

import mobile.app.model.User;

public interface UserBusiness {

	public List<DBObject> getLastSearchedProducts(String userName);
	
	public void deleteSearchedProduct(String userName,String productId);
	
	public void saveSearchedProduct(String userName,String productId, String productName);
	
	public User register(User user);

	public DBObject getUserData(String username);
	
}
