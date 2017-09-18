package mobile.app.business;

import java.util.List;

public interface UserBusiness {

	public List<?> getLastSearchedProducts(String userId) throws Exception;
	
	public void deleteSearchedProduct(String userId,String productId) throws Exception;
	
	public void saveSearchedProduct(String userId,String productId) throws Exception;
	
}
