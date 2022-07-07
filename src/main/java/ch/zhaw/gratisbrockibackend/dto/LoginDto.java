package ch.zhaw.gratisbrockibackend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * View Model object for storing a user's credentials.
 */
@Getter
@Setter
public class LoginDto {

    @NotNull
    @Size(min = 1, max = 50)
    private String email;

    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    // prettier-ignore
    @Override
    public String toString() {
        return "LoginVM{" +
                "username='" + email + '\'' +
                '}';
    }
}
