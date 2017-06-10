package mobile.app.repository;

import mobile.app.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by guillermoaiquel on 6/10/17.
 */
public interface UserRepository extends MongoRepository<User, String> {
}
