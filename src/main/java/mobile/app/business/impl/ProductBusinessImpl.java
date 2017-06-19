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
public class ProductBusinessImpl implements ProductBusiness{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
	}

	@Override
	public void save(Product entity) {
		productRepository.save(entity);
	}

	@Override
	public void modify(Product entity) {
		productRepository.save(entity);
	}

}
