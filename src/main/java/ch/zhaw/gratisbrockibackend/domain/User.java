package ch.zhaw.gratisbrockibackend.domain;

import ch.zhaw.gratisbrockibackend.domain.enums.Role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@Setter
@Entity
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

	@Column(nullable = false, unique = true)
	private String email;

    @Column
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    private Role role;

	public boolean accountNonExpired;

	public boolean accountNonLocked;

	public boolean credentialsNonExpired;

	public boolean enabled;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Item> items;


    public User() {
        super();
        this.role = Role.USER;
	}

    public void addItem (Item item){
        items.add(item);
    }

}