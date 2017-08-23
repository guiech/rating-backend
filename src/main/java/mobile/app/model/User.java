package mobile.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user")
public class User {

    @Id
    public String id;

    public String name;
    public String username;
    public String email;
    public String password;
    private List<String> roles;

    public User() {}

    public User(String name, String username, String email, String password, List<String> roles) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public List<Role> getRoles() {
        if(this.roles == null) {
            return new ArrayList<Role>();
        }
        List<Role> roles = new ArrayList<Role>(this.roles.size());
        for(String role : this.roles) {
            roles.add(new Role(role));
        }
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
