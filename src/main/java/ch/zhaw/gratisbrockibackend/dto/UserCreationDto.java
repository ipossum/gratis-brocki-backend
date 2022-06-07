package ch.zhaw.gratisbrockibackend.dto;

import ch.zhaw.gratisbrockibackend.domain.enums.Role;

import java.util.Date;

public class UserCreationDto {

    protected long id;
    protected String createdBy;
    protected Date createdDate;

    private String username;
    private String email;
    private String phoneNumber;
    private Role role;

}
