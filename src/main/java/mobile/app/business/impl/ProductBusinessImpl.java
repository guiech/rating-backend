package mobile.app.business.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import mobile.app.business.ProductBusiness;
import mobile.app.model.Product;

@Service
@Transactional
public class ProductBusinessImpl extends GenericBusiness implements ProductBusiness {

	@Override
	public List<Product> getProductsByName(String name) {
		return productRepository.findByNameRegex(".*"+name+".*");
	}

	@Override
	public DBObject getProductDetailsById(String productId) {
		DBObject jsonNode =  new BasicDBObject();
		jsonNode.put("product", getProduct());
		jsonNode.put("likes",getProductLike());
		return jsonNode;
	}
	
	@Override
	public Product saveProduct(Product product) {
		if (product.getDescription()==null) {
			product =  mockProduct();
		}
		return productRepository.save(product);
	}
	
	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}
}
