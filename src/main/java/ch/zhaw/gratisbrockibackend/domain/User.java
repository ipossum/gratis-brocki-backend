package ch.zhaw.gratisbrockibackend.domain;

import ch.zhaw.gratisbrockibackend.domain.enums.Role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Entity
@Table(name = "user")
// @JsonIgnoreProperties({ "password", "items" }) // quick fix until we set up DTOs
public class User extends BaseEntity{

    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Transient
    @JsonProperty
    private String confirmedPassword;

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

