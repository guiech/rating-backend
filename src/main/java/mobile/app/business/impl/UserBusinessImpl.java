package mobile.app.business.impl;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import mobile.app.business.UserBusiness;
import mobile.app.exceptions.UserAlreadyRegisteredException;
import mobile.app.model.User;

@Service
@Transactional
public class UserBusinessImpl extends GenericBusiness implements UserBusiness {

	@Override
	public List<DBObject> getLastSearchedProducts(String username) {
		return userRepository.getByUsername(username).getSearchedProducts();
	}

	@Override
	public void deleteSearchedProduct(String username, String productId) {
		User user = userRepository.getByUsername(username);
		List<DBObject> products = user.getSearchedProducts();
        Predicate<DBObject> productPredicate = p-> p.get("productId").equals(productId);
		products.removeIf(productPredicate);
		userRepository.save(user);
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

	@Override
	public DBObject getUserData(String username) {
		User user = userRepository.getByUsername(username);
		DBObject json = new BasicDBObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("username", user.getUsername());
		json.put("email", user.getEmail());
		json.put("roles", user.getRoles());
		return json;
	}

	private void checkDuplicatedUser(String... userData) {
		if (userRepository.getByEmail(userData[0]) != null || userRepository.getByUsername(userData[1]) != null) {
			throw new UserAlreadyRegisteredException();
		}
	}
}
