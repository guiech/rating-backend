package mobile.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import mobile.app.model.Product;

@Transactional
public interface ProductRepository extends MongoRepository<Product, String> {
	
	List<Product> findByNameRegex(String name);

	Product findById(String id);
}
