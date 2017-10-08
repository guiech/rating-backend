package mobile.app.business.impl;

import java.util.Date;
import java.util.List;

import mobile.app.model.ProductLikes;
import mobile.app.model.User;
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
	public DBObject getProductsByName(String name) {
		Product product = new Product();
		product.setName(name);
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("name", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING, true));
		Example<Product> example = Example.of(product, matcher);
		DBObject jsonNode = new BasicDBObject();
		List<Product> list = productRepository.findAll(example);
		jsonNode.put("count", list.size());
		jsonNode.put("products", list);
		return jsonNode;
//		return productRepository.findByNameRegex(".*"+name+".*");
	}

	@Override
	public DBObject getProductDetailsById(String productId, String username) {
		DBObject jsonNode =  new BasicDBObject();
		jsonNode.put("product", productRepository.findById(productId));
		// TODO find a better way to know if user is auth or not
		ProductLikes productLikes = null;
		if(!username.equals("anonymousUser")) {
			productLikes = productLikeRepository.findByUserIdAndProductId(userRepository.getByUsername(username).getId(), productId);
		}
		jsonNode.put("likes", productLikes != null ? productLikes.getLikeStatus() : 0);
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
	public DBObject getAll() {
		DBObject jsonNode = new BasicDBObject();
		List<Product> products = productRepository.findAll();
		jsonNode.put("count", products.size());
		jsonNode.put("products", products);
		return jsonNode;
	}

	@Override
	public DBObject like(String productId, String username, int status) {
		DBObject result = new BasicDBObject();
		result.put("success", true);
		try {
			User user = userRepository.getByUsername(username);
			Product product = new Product();
			product.setId(productId);

			ProductLikes productLikes = productLikeRepository.findByUserIdAndProductId(user.getId(), productId);
			if (productLikes == null) {
				// Save new like
				if(status != 0) {
					productLikes = new ProductLikes();
					productLikes.setLikeStatus(status);
					productLikes.setCreateAt(new Date());
					productLikes.setProduct(product);
					productLikes.setUser(user);
					productLikeRepository.save(productLikes);
					product = productRepository.findById(productId);
					switch (status) {
						case 1:
							product.increaseLikeCount();
							break;
						case -1:
							product.increaseDislikeCount();
							break;
					}
					productRepository.save(product);
				}
			} else if (productLikes.getLikeStatus() != status) {
				// avoid updating DB if status is the same one
				// change like status
				product = productRepository.findById(productId);
				switch (productLikes.getLikeStatus()) {
					case 1:
						product.decreaseLikeCount();
						break;
					case -1:
						product.decreaseDislikeCount();
						break;
				}
				switch (status) {
					case 1:
						product.increaseLikeCount();
						break;
					case -1:
						product.increaseDislikeCount();
						break;
				}
				productLikes.setLikeStatus(status);
				productLikes.setCreateAt(new Date());
				productLikeRepository.save(productLikes);
				productRepository.save(product);
			}
		} catch (Exception e) {
			result.put("success", false);
			result.put("message", e.getMessage());
		}

		return result;
	}
}
