package ch.zhaw.gratisbrockibackend;


import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.service.UserService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTests {


    /**
     * Funktioniert nicht weil UserService kein Interface ist.
     */

    private UserService userService;

    public User createUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setEmail("test@test.ch");
        user.setPhoneNumber("0796666666");
        user.setPassword("qwertz@1234");
        return user;
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void registerNewUserTest() {
        User user = createUser();
        userService.registerNewUser(user);
        Assertions.assertNotNull(userService.getUser(1L));
    }

}
