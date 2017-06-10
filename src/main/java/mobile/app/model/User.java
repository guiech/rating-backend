package mobile.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by guillermoaiquel on 6/10/17.
 */
@Document(collection = "user")
public class User {

    @Id
    public String id;

    public String email;
    public String password;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
