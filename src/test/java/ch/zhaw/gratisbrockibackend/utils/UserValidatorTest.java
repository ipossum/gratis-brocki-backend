package ch.zhaw.gratisbrockibackend.utils;

import ch.zhaw.gratisbrockibackend.auth.InvalidPasswordException;
import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.dto.UserCreationDto;
import ch.zhaw.gratisbrockibackend.mapper.UserMapper;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UserValidatorTest {

    private UserRepository userRepository;

    private UserCreationDto userCreationDto;

    private UserMapper userMapper;

    UserValidatorTest(UserRepository userRepository, UserCreationDto userCreationDto, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userCreationDto = userCreationDto;
        this.userMapper = userMapper;
    }

    @BeforeAll
    void setUp() {
        userCreationDto.setUsername("testUser");
        userCreationDto.setEmail("test@test.ch");
        userCreationDto.setPhoneNumber("0795049382");
        userCreationDto.setPassword("qwertz1234");
        User user = userMapper.toUser(userCreationDto);
        userRepository.save(user);

    }

    @Test
    void checkCredentialsUserCreationTest() {
        userCreationDto.setPassword("kurz");
        User user = userMapper.toUser(userCreationDto);
        userRepository.save(user);
        //Assertions.assert;
    }

}