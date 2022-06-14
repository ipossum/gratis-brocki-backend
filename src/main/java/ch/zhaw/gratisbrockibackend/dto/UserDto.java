package ch.zhaw.gratisbrockibackend.dto;

import ch.zhaw.gratisbrockibackend.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private Role role;

}
