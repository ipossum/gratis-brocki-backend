package ch.zhaw.gratisbrockibackend.web.controller;

import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.domain.dto.UserDto;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;

import ch.zhaw.gratisbrockibackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RequestMapping("api/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    //@Autowired
    UserRepository userRepository;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }

    @GetMapping({"/{id}"})
    public UserDto getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

//    @PostMapping("/")
//    public void addNewUser(@RequestBody User user){
//        userRepository.save(user);
//    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        userService.updateUser(id, userDto);
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}