package mobile.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import mobile.app.model.Product;
import mobile.app.model.User;

public interface UserRestService {

	public User register(@RequestBody User user);

	public List<Product> getLastSearchedProducts();
	
	public void deleteSearchedProduct(String userName,String productId);
	
	public void saveSearchedProduct(@RequestBody Product product);
	
}
