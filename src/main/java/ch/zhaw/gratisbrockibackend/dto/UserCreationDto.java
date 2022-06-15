package ch.zhaw.gratisbrockibackend.dto;

import ch.zhaw.gratisbrockibackend.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserCreationDto {

    protected Long id;
    protected String createdBy;
    protected Date createdDate;

    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private Role role;

}
