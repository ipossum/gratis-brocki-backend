package ch.zhaw.gratisbrockibackend.auth;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginParamsTest {

    LoginParams loginParams = new LoginParams("test@test.ch", "qwertz1234");

    @Test
    void getEmail() {
        assertEquals(loginParams.getEmail(), "test@test.ch");
    }

    @Test
    void getEncodedPassword() {
        System.out.println(loginParams.getEncodedPassword());
        assertNotNull(loginParams.getEncodedPassword());
    }

    @Test
    void minLengthPassword() {
    }

}