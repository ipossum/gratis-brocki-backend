package ch.zhaw.gratisbrockibackend.domain;

import ch.zhaw.gratisbrockibackend.domain.enums.Role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

	@Column(nullable = false, unique = true)
	private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Transient
    @JsonProperty
    private String confirmedPassword;

    private Role role;

	public boolean accountNonExpired;

	public boolean accountNonLocked;

	public boolean credentialsNonExpired;

	public boolean enabled;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Item> items;

	public User() {}

	public User(String username, String email) {
		this.username = username;
		this.email = email;
        this.role = Role.USER;
		this.enabled = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.accountNonExpired = true;
        this.items = new HashSet<>();
	}

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