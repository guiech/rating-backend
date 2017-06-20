package mobile.app.business.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mobile.app.business.UserBusiness;
import mobile.app.model.User;

@Service
@Transactional
public class UserBusinessImpl implements UserBusiness{

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
	}

	@Override
	public void save(User entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void modify(User entity) {
		// TODO Auto-generated method stub
	}

}
