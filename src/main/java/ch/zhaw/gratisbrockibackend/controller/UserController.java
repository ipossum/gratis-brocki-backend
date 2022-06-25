package ch.zhaw.gratisbrockibackend.controller;

import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.dto.UserCreationDto;
import ch.zhaw.gratisbrockibackend.dto.UserDto;
import ch.zhaw.gratisbrockibackend.dto.UserUpdateDto;
import ch.zhaw.gratisbrockibackend.mapper.UserMapper;
import ch.zhaw.gratisbrockibackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("api/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping
    public UserDto registerNewUser(@RequestBody UserCreationDto userCreationDto) {
        User user = userMapper.toUser(userCreationDto);
        return userMapper.toUserDto(userService.registerNewUser(user));
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return userMapper.toUserDto(userService.getUser(id));
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateDto userUpdateDto) {
        return userMapper.toUserDto(userService.updateUser(id, userUpdateDto));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}