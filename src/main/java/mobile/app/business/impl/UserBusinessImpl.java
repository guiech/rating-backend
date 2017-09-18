package mobile.app.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mobile.app.business.UserBusiness;
import mobile.app.repository.UserRepository;

@Service
@Transactional
public class UserBusinessImpl implements UserBusiness{
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public List<?> getLastSearchedProducts(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSearchedProduct(String userId, String productId) {
		// TODO Auto-generated method stub
	}

	@Override
	public void saveSearchedProduct(String userId, String productId) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
