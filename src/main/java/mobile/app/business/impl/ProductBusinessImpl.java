package mobile.app.business.impl;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
		Product product = new Product();
		product.setName(name);
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("name", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING, true));
		Example<Product> example = Example.of(product, matcher);
		return productRepository.findAll(example);
//		return productRepository.findByNameRegex(".*"+name+".*");
	}

	@Override
	public DBObject getProductDetailsById(String productId) {
		DBObject jsonNode =  new BasicDBObject();
		jsonNode.put("product", productRepository.findById(productId));
		jsonNode.put("likes", getProductLike());
		return jsonNode;
	}

	@Override
	public Product saveProduct(Product product, String username) {
		if (product.getDescription()==null) {
			product =  mockProduct();
		}
		product.setCreateBy(userRepository.getByUsername(username));
		product.setCreateAt(new Date());
		product.setCommentsCount(0);
		product.setLikesCount(0);
		product.setDislikesCount(0);
		product.setRate(0f);
		return productRepository.save(product);
	}
	
	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}
}
