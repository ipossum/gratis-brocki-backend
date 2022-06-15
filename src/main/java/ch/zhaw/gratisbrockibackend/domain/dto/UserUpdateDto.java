package ch.zhaw.gratisbrockibackend.domain.dto;

import ch.zhaw.gratisbrockibackend.domain.enums.Role;

import java.util.Date;

public class UserUpdateDto {

    private String lastModifiedBy;
    private Date lastModifiedDate;

    private String username;
    private String email;
    private String phoneNumber;
    private Role role;


}
