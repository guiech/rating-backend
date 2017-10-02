package mobile.app.business.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mobile.app.business.UserBusiness;
import mobile.app.model.Product;

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
	public void saveSearchedProduct(String userId, String productId) {
		// TODO Auto-generated method stub
	}

//	public void save(User user) {
//		user.setPassword(getPasswordEncoder().encode(user.getPassword()));
//		userRepository.save(user);
//	}
}
