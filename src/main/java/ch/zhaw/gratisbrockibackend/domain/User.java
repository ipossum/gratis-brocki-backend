package ch.zhaw.gratisbrockibackend.domain;

import ch.zhaw.gratisbrockibackend.domain.enums.Role;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({ "password", "items" }) // quick fix until we set up DTOs
public class User extends BaseEntity{

    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private Role role;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>();

    public void addItem (Item item){
        items.add(item);
    }

    @Override
    public String toString(){
        return "User{" +
                "id: " + id + '\'' +
                "username: " + username + '\'' +
                "email: " + email + '\'' +
                "phone number: " + phoneNumber + '\'' +
                "user role: " + role + '\'' +
                '}';
    }

}

