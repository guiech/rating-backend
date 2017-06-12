package mobile.app.repository;

import java.util.List;

import mobile.app.model.User;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by guillermoaiquel on 6/10/17.
 */
public interface UserRepository extends CrudRepository<User, String> {

    @Query("{ email :  { $eq : ?0 }}")
    List<User> findByEmail(@Param("email") String email);

}
