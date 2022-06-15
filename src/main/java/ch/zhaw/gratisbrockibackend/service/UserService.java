package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.domain.dto.UserDto;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
        //return userRepository.findAll();
    }

    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public UserDto getUser(Long id) {
        try {
            return convertEntityToDto(userRepository.findUserByID(id));
        } catch (HttpClientErrorException.BadRequest e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateUser(Long id, UserDto userDto) {
        User user;
        try {
            user = userRepository.findUserByID(id);
            if(checkCredentials(user)) {
                user.setEmail(userDto.getEmail());
                user.setPhoneNumber(userDto.getPhoneNumber());
                userRepository.save(user);
            }
        } catch (HttpClientErrorException.BadRequest e) {
            e.printStackTrace();
            //return null;
        }
        //return convertEntityToDto(userRepository.findUserByID(id));
    }

    public ResponseEntity<UserDto> createUser(User user) {
        if (checkCredentials(user)) {
                userRepository.save(user);
                UserDto userDto = new UserDto();
                BeanUtils.copyProperties(user, userDto);
                return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.badRequest().build();
    }

    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (HttpClientErrorException.BadRequest e) {
            e.printStackTrace();
        }
    }

    public boolean checkCredentials(User user){
        boolean validCredentials = false;
        //Optional<User> userOptional= userRepository.findUserByEmail(user.getEmail());
        if (user.getEmail().equals(userRepository.findUserByEmail(user.getEmail()))){
            throw new IllegalStateException("email already taken");
        }
        else if (!user.getPassword().equals(user.getConfirmedPassword())) {
            throw new IllegalStateException("password doesn't match");
        }
        else {
            validCredentials = true;
        }
        return validCredentials;
    }

}