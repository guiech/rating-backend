package mobile.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mobile.app.model.ProductLikes;

public interface ProductLikesRepository  extends MongoRepository<ProductLikes,String> {}
