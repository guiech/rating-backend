package mobile.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class UserMin {

    @Id
    public String id;

    public String name;
    public String username;

    public UserMin() {
    }

    public UserMin(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static UserMin parseUser(User user) {
        UserMin userMin = new UserMin(user.getName(), user.getUsername());
        userMin.setId(user.getId());
        return userMin;
    }
}
