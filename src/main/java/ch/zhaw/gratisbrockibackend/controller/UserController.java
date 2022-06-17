package ch.zhaw.gratisbrockibackend.controller;

import ch.zhaw.gratisbrockibackend.auth.UserAlreadyExistsException;
import ch.zhaw.gratisbrockibackend.dto.UserCreationDto;
import ch.zhaw.gratisbrockibackend.dto.UserDto;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;
import ch.zhaw.gratisbrockibackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    //@Autowired
    //UserRepository userRepository;

    @GetMapping("/")
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }

    @GetMapping({"/{id}"})
    public UserDto getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/{id}")
    public UserDto registerNewUser(@RequestBody UserCreationDto userCreationDto) throws UserAlreadyExistsException {
        return userService.registerNewUser(userCreationDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}