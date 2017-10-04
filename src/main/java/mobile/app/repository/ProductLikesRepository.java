package mobile.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import mobile.app.model.ProductLikes;

@Transactional
public interface ProductLikesRepository  extends MongoRepository<ProductLikes,String> {

    ProductLikes findByUserIdAndProductId(String username, String product);
}
