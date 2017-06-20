package mobile.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import mobile.app.model.User;

@Transactional
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ email :  { $eq : ?0 }}")
    List<User> findByEmail(@Param("email") String email);

    @Query("{ email :  { $eq : ?0 }}")
    User getByEmail(@Param("email") String email);

}
