package mobile.app.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mobile.app.business.ProductBusiness;
import mobile.app.model.Product;
import mobile.app.repository.ProductRepository;

@Service
@Transactional
public class ProductBusinessImpl implements ProductBusiness {
	
	@Autowired
	private ProductRepository repository;

	@Override
	public List<?> getProductComments(String productId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getProductRate(String productId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getProductsByName(String Name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductDetailsById(String productId) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}
}
