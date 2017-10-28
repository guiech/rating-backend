package mobile.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class UserPublic {

    @Id
    public String id;

    public String name;
    public String username;

    public UserPublic() {
    }

    public UserPublic(String name, String username) {
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

    public static UserPublic parseUser(User user) {
        UserPublic userPublic = new UserPublic(user.getName(), user.getUsername());
        userPublic.setId(user.getId());
        return userPublic;
    }
}
