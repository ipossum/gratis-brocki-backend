package ch.zhaw.gratisbrockibackend.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Username already exists")
public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException(){
        super();
    }

    public UserAlreadyExistsException(String message){
        super(message);
    }
}
