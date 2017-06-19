package mobile.app.repository;

import mobile.app.model.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductRepository extends MongoRepository<Product, String> {}
