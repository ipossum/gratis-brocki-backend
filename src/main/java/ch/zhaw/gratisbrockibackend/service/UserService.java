package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.auth.UserAlreadyExistsException;
import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.domain.dto.UserDto;
import ch.zhaw.gratisbrockibackend.domain.dto.UserCreationDto;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(final UserCreationDto userCreationDto) throws UserAlreadyExistsException {
        if (emailExists(userCreationDto.getEmail())) {
            throw new UserAlreadyExistsException("An account with this email address already exists: " + userCreationDto.getEmail());
        }
        final User user = new User(userCreationDto.getUsername(), userCreationDto.getEmail());
        user.setPassword(passwordEncoder.encode(userCreationDto.getPassword()));
        return userRepository.save(user);
    }

    private boolean emailExists(final String email) {
        return userRepository.findUserByEmail(email) != null;
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
            return convertEntityToDto(userRepository.findUserById(id));
        } catch (HttpClientErrorException.BadRequest e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateUser(Long id, UserDto userDto) {
        User user;
        try {
            user = userRepository.findUserById(id);
            if (validCredentials(user)) {
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
        if (validCredentials(user)) {
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

    public boolean validCredentials(User user) {
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new IllegalStateException("email already taken");
            return false;
        }
        if (!user.getPassword().equals(user.getConfirmedPassword())) {
            throw new IllegalStateException("password doesn't match");
            return false;
        }
        return true;
    }
}