package ch.zhaw.gratisbrockibackend.domain;

import ch.zhaw.gratisbrockibackend.domain.enums.Role;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "User")
public class User extends BaseEntity{

    private String username;
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private Role role;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>();

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

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return passwordHash;
    }
    public void setPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Set<Item> getItems() {
        return items;
    }
    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public void addItem (Item item){
        items.add(item);
    }

    @Override
    public String toString(){
        return "User{" +
                "username: " + username + '\'' +
                "email: " + email + '\'' +
                "phone number: " + phoneNumber + '\'' +
                "user role: " + role + '\'' +
                '}';
    }

}

