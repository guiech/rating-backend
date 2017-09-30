package mobile.app.business.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.util.DBObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import mobile.app.business.ProductBusiness;
import mobile.app.model.Comment;
import mobile.app.model.Product;
import mobile.app.model.User;
import mobile.app.repository.CommentLikesRepository;
import mobile.app.repository.CommentRepository;
import mobile.app.repository.ProductLikesRepository;
import mobile.app.repository.ProductRepository;
import mobile.app.repository.UserRepository;

@Service
@Transactional
public class ProductBusinessImpl implements ProductBusiness {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentLikesRepository commentLikeRepository;
	
	@Autowired
	private ProductLikesRepository productLikeRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public List<?> getProductComments(String productId) {
		return null;
	}

	@Override
	public List<?> getProductRate(String productId) {
		return null;
	}

	@Override
	public List<?> getProductsByName(String Name) {
		return null;
	}

	@Override
	public DBObject getProductDetailsById(String productId) {
		
		DBObject jsonNode =  new BasicDBObject();
		
		jsonNode.put("producto", productRepository.findOne(productId));
		
		jsonNode.put("userLike", "1");
		
		return jsonNode;
	}

	@Override
	public Product saveProduct() {
		return productRepository.save(mockProduct());
	}
	
	@Override
	public Comment saveComment() {
		return mockComment();
	}
	
	private Product mockProduct() {
		Product product = new Product();
		product.setBrand("brand");
		product.setCommentsCount(2);
		product.setCreateAt(new Date());
		product.setCreateBy(getUser());
		product.setDescription("Description");
		DBObject dbObject = new BasicDBObject();
		dbObject.put("users", Arrays.asList("gaiquel@gmail.com"));
		product.setDisLikes(dbObject);
		product.setDislikesCount(1);
		product.setFeatures("Feautures");
		product.setImages(Arrays.asList("~/image1.jpg"));
		dbObject = new BasicDBObject();
		dbObject.put("users", Arrays.asList("gaiquel@gmail.com"));
		product.setLikes(dbObject);
		product.setLikesCount(1);
		product.setName("product name");
		product.setRate(new Float(5.5));
		return product;
	}
	
	private User getUser() {
		List<User> users =  userRepository.findAll();
		return CollectionUtils.isEmpty(users) ? insertUser() : users.get(0);
	}
	
	private Product getProduct() {
		List<Product> product =  productRepository.findAll();
		return CollectionUtils.isEmpty(product) ? mockProduct() : product.get(0);
	}
	
	private User insertUser() {
		User user = new User();
		user.setEmail("santiagomilanese@gmail.com");
		user.setName("santiago");
		user.setPassword("lolalola");
		return userRepository.save(user);
	}
	
	private Comment mockComment() {
		
		Comment comment = new Comment();
		comment.setStars(4);
		comment.setUserId(getUser().getId());
		comment.setProductId(getProduct().getId());
		comment.setLikes(0);
		comment.setDisLikes(0);
		return commentRepository.save(comment);
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}
}
