package mobile.app.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import mobile.app.business.UserBusiness;
import mobile.app.exceptions.UserAlreadyRegisteredException;
import mobile.app.model.Product;
import mobile.app.model.User;

@Service
@Transactional
public class UserBusinessImpl extends GenericBusiness implements UserBusiness {

	@Override
	public List<Product> getLastSearchedProducts(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSearchedProduct(String userId, String productId) {
		// TODO Auto-generated method stub
	}

	@Override
	public void saveSearchedProduct(String username, String productId, String productName) {
		DBObject searchedProduct = new BasicDBObject();
		searchedProduct.put("productId", productId);
		searchedProduct.put("productName", productName);
		User user = userRepository.getByUsername(username);
		List<DBObject> products = user.getSearchedProducts();
		if (!products.contains(searchedProduct)) {
			products.add(searchedProduct);
			user.setSearchedProducts(products);
			userRepository.save(user);
		}
	}

	@Override
	public User register(User user) {
		checkDuplicatedUser(user.getEmail(), user.getUsername());
		return userRepository.save(user);
	}

	private void checkDuplicatedUser(String... userData) {
		if (userRepository.getByEmail(userData[0]) != null || userRepository.getByUsername(userData[1]) != null) {
			throw new UserAlreadyRegisteredException();
		}
	}
}
