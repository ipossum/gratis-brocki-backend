package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.dto.UserCreationDto;
import ch.zhaw.gratisbrockibackend.dto.UserDto;
import ch.zhaw.gratisbrockibackend.mapper.UserMapper;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Resource
    private UserRepository userRepository;

    @Resource
    UserCreationDto userCreationDto;

    private UserMapper userMapper;

    UserServiceTest(UserRepository userRepository, UserCreationDto userCreationDto, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userCreationDto = userCreationDto;
        this.userMapper = userMapper;
    }

    /*@BeforeAll
    void setUp() {
        userCreationDto.setUsername("testUser");
        userCreationDto.setEmail("test@test.ch");
        userCreationDto.setPhoneNumber("0795049382");
        userCreationDto.setPassword("qwertz1234");
    }*/

    @Test
    void registerNewUser() {
        userCreationDto.setUsername("testUser");
        userCreationDto.setEmail("test@test.ch");
        userCreationDto.setPhoneNumber("0795049382");
        userCreationDto.setPassword("qwertz1234");
        User user = userMapper.toUser(userCreationDto);
        userRepository.save(user);
        assertNotNull(userRepository.findUserById(1L));
    }

    @Test
    void getUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}