package ch.zhaw.gratisbrockibackend.repository;

import ch.zhaw.gratisbrockibackend.domain.User;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

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
    public void saveUserTest() {
        User user = createUser();
        userRepository.save(user);
        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getUserTest() {
        User user = userRepository.findUserById(1L);
        Assertions.assertThat(user.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void updateUserTest() {
        User updatedUser = userRepository.findUserById(1L);
        Assertions.assertThat(updatedUser.getUsername()).isEqualTo("testUser");
        updatedUser.setUsername("updatedUser");
        userRepository.save(updatedUser);
        Assertions.assertThat(updatedUser.getUsername()).isEqualTo("updatedUser");
    }

    @Test
    @Order(4)
    public void deleteUserTest() {
        userRepository.deleteById(1L);
        Assertions.assertThat(userRepository.findUserById(1L)).isNull();
    }
}
