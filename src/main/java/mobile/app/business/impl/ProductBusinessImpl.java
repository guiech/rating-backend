package mobile.app.business.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import mobile.app.business.ProductBusiness;
import mobile.app.model.Product;
import mobile.app.repository.ProductRepository;

@Service
@Transactional
public class ProductBusinessImpl implements ProductBusiness {
	
	@Autowired
	private ProductRepository repository;

	@Override
	public List<?> getProductComments(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getProductRate(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getProductsByName(String Name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductDetailsById(String productId) {
		// TODO Auto-generated method stub
		return repository.findOne(productId);
	}

	@Override
	public Product saveProduct() {
		Product product = new Product();
		product.setBrand("brand");
		product.setComments(Arrays.asList("comment 1","comment 2"));
		product.setCommentsCount(2);
		product.setCreateAt(new Date());
		DBObject dbObject = new BasicDBObject();
		dbObject.put("user", "gjaiquel@gmail.com");
		product.setCreateBy(dbObject);
		product.setDescription("Description");
		dbObject = new BasicDBObject();
		dbObject.put("users", Arrays.asList("gaiquel@gmail.com"));
		product.setDisLikes(dbObject);
		product.setDislikessCount(1);
		product.setFeatures("Feautures");
		product.setImages(Arrays.asList("~/image1.jpg"));
		dbObject = new BasicDBObject();
		dbObject.put("users", Arrays.asList("gaiquel@gmail.com"));
		product.setLikes(dbObject);
		product.setLikesCount(1);
		product.setName("product name");
		product.setRate(new Float(5.5));
		return repository.save(product);
	}

}
