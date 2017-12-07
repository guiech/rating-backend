package mobile.app.business.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import mobile.app.business.ProductBusiness;
import mobile.app.model.Product;
import mobile.app.model.ProductLikes;
import mobile.app.model.ProductMin;
import mobile.app.model.User;
import mobile.app.model.UserMin;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
	public DBObject getProductByTags(String searchedText, Integer page) {
		DBObject jsonNode = new BasicDBObject();
		jsonNode.put("success", false);

		try {
			String[] tags = searchedText.split(" ");
			Criteria[] tagsCriteria = new Criteria[tags.length];
			for (int i = 0; i < tags.length; i++) {
				tagsCriteria[i] = Criteria.where("tags").regex(tags[i].toLowerCase());
			}
			Criteria criteria = new Criteria();
			criteria.andOperator(tagsCriteria);
			Query query = new Query(criteria);
			query.limit(pageSize);
			query.with(new PageRequest(page == null ? 0 : page.intValue(), pageSize, new Sort(Sort.Direction.DESC, "rate")));
			long start = System.currentTimeMillis();
			List<Product> productList = mongoTemplate.find(query, Product.class);
			System.out.println("getProductByTags: " + (System.currentTimeMillis() - start) + " millis");
			jsonNode.put("products", productList);
			if (productList.size() < pageSize) {
				jsonNode.put("isLast", true);
			} else {
				jsonNode.put("isLast", false);
			}
			jsonNode.put("success", true);
		} catch (Exception e) {
			jsonNode.put("message", e.getMessage());
		}
		return jsonNode;
	}

	@Override
	public Product saveProduct(Product product, String username) {
		product.setCreateBy(UserMin.parseUser(userRepository.getByUsername(username)));
		product.setCreateAt(new Date());
		product.setCommentsCount(0);
		product.setLikesCount(0);
		product.setDislikesCount(0);
		product.setRate(0D);
		product.setTags(Arrays.asList((product.getName() + " " + product.getBrand()).toLowerCase().split(" ")));
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
			Product product = productRepository.findById(productId);
			if(product == null) {
				result.put("success", false);
				result.put("message", "Product does not exist");
				return result;
			}

			ProductLikes productLikes = productLikeRepository.findByUserIdAndProductId(user.getId(), productId);
			if (productLikes == null) {
				// Save new like
				if(status != 0) {
					productLikes = new ProductLikes();
					productLikes.setLikeStatus(status);
					productLikes.setCreateAt(new Date());
					productLikes.setProduct(ProductMin.parseProduct(product));
					productLikes.setUser(UserMin.parseUser(user));
					productLikeRepository.save(productLikes);
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

	@Override
	public DBObject getProductsByNameForResultPage(Integer page, String username, String brand) {
		DBObject result = new BasicDBObject();
		Product product = new Product();
		product.setBrand(brand);
		PageRequest request = new PageRequest(page == null ? 0 : page.intValue(), pageSize, new Sort(Sort.Direction.DESC, "rate"));
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("brand", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING, true));
        Example<Product> example = Example.of(product, matcher);
        Page<Product> products = productRepository.findAll(example, request);
        result.put("products", products);
        result.put("isLast", products.isLast());
        return result;
	}
}
