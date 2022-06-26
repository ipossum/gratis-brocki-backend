package ch.zhaw.gratisbrockibackend.domain;

import ch.zhaw.gratisbrockibackend.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@ToString
@Getter
@Setter
@Entity
@Table(name="users")
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

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Item> items;


    public User() {
        super();
        this.role = Role.USER;
	}
}