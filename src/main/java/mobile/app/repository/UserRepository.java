package mobile.app.repository;

import java.util.List;

import mobile.app.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ email :  { $eq : ?0 }}")
    List<User> findByEmail(@Param("email") String email);

    @Query("{ email :  { $eq : ?0 }}")
    User getByEmail(@Param("email") String email);

    @Query("{ username : { $eq : ?0}}")
    User getByUsername(@Param("username") String username);

}
