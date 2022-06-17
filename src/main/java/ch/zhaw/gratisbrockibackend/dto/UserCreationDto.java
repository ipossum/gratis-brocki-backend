package ch.zhaw.gratisbrockibackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationDto {

    private String username;
    private String email;
    private String password;
    private String phoneNumber;

}