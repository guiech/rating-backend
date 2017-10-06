package mobile.app.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mobile.app.business.UserBusiness;
import mobile.app.exceptions.UserAlreadyRegistredException;
import mobile.app.model.Product;
import mobile.app.model.User;

@Service
@Transactional
public class UserBusinessImpl extends GenericBusiness implements UserBusiness {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
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

	@Override
	public User register(User user) {
		checkDuplicatedUser(user.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	private void checkDuplicatedUser(String username){
		if(userRepository.getByUsername(username)!=null){
			throw new UserAlreadyRegistredException();
		}
	}
}
