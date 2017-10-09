package mobile.app.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mobile.app.business.UserBusiness;
import mobile.app.exceptions.UserAlreadyRegisteredException;
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
		checkDuplicatedUser(user.getEmail(),user.getUsername());
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	private void checkDuplicatedUser(String... userData){
		if(userRepository.getByEmail(userData[0])!=null || userRepository.getByUsername(userData[1])!=null){
			throw new UserAlreadyRegisteredException();
		}
	}
}
