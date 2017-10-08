package mobile.app.business.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import mobile.app.model.Comment;
import mobile.app.model.Product;
import mobile.app.model.ProductLikes;
import mobile.app.model.User;
import mobile.app.repository.CommentLikesRepository;
import mobile.app.repository.CommentRepository;
import mobile.app.repository.ProductLikesRepository;
import mobile.app.repository.ProductRepository;
import mobile.app.repository.UserRepository;

public class GenericBusiness {
	
	@Autowired
	protected ProductRepository productRepository;
	
	@Autowired
	protected UserRepository userRepository;
	
	@Autowired
	protected CommentLikesRepository commentLikeRepository;
	
	@Autowired
	protected ProductLikesRepository productLikeRepository;
	
	@Autowired
	protected CommentRepository commentRepository;
	
	
	//TODO Try to DRY the code.
	protected User getUser() {
		List<User> users =  userRepository.findAll();
		return CollectionUtils.isEmpty(users) ? mockUser() : users.get(0);
	}
	
	protected Product getProduct() {
		List<Product> product =  productRepository.findAll();
		return CollectionUtils.isEmpty(product) ? mockProduct() : product.get(0);
	}
	
	protected ProductLikes getProductLike() {
		List<ProductLikes> productLikes =  productLikeRepository.findAll();
		return CollectionUtils.isEmpty(productLikes) ? mockProductLike() : productLikes.get(0);
	}

	protected Comment mockComment() {
		Comment comment = new Comment();
		comment.setStars(4);
		comment.setCreateBy(getUser());
		comment.setProduct(getProduct());
		comment.setLikesCount(0);
		comment.setDislikesCount(0);
		return commentRepository.save(comment);
	}
	
	protected Product mockProduct() {
		Product product = new Product();
		product.setBrand("brand");
		product.setCommentsCount(2);
		product.setCreateAt(new Date());
		product.setCreateBy(getUser());
		product.setDescription("Description");
		DBObject dbObject = new BasicDBObject();
		dbObject.put("users", Arrays.asList("gaiquel@gmail.com"));
		product.setDislikesCount(1);
		product.setFeatures("Features");
		product.setImages(Arrays.asList("~/image1.jpg"));
		dbObject = new BasicDBObject();
		dbObject.put("users", Arrays.asList("gaiquel@gmail.com"));
		product.setLikesCount(1);
		product.setName("product name");
		product.setRate(new Float(5.5));
		return productRepository.save(product);
	}
	
	protected ProductLikes mockProductLike() {
		ProductLikes likes = new ProductLikes();
		likes.setCreateAt(new Date());
		likes.setUser(getUser());
		likes.setProduct(getProduct());
		likes.setLikeStatus(1);
		return productLikeRepository.save(likes);
	}
	
	protected User mockUser() {
		User user = new User();
		user.setEmail("santiagomilanese@gmail.com");
		user.setName("santiago");
		user.setPassword("lolalola");
		return userRepository.save(user);
	}

}
