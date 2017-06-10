package mobile.app.repository;

import mobile.app.model.User;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by guillermoaiquel on 6/10/17.
 */
public interface UserRepository extends CrudRepository<User, String> {
}
