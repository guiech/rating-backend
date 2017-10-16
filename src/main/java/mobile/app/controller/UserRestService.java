package mobile.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mongodb.DBObject;

import mobile.app.model.Product;
import mobile.app.model.User;

public interface UserRestService {

	public User register(@RequestBody User user);

	public List<DBObject> getLastSearchedProducts();
	
	public void deleteSearchedProduct(@PathVariable String productId);
	
	public void saveSearchedProduct(@RequestBody Product product);

	public DBObject userData();
	
}
