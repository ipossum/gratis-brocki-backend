package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.auth.UserAlreadyExistsException;
import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.dto.UserCreationDto;
import ch.zhaw.gratisbrockibackend.dto.UserDto;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto registerNewUser(final UserCreationDto userCreationDto) throws UserAlreadyExistsException {
        if (emailExists(userCreationDto.getEmail())) {
            throw new UserAlreadyExistsException("An account with this email address already exists: " + userCreationDto.getEmail());
        }
        final User user = new User(userCreationDto.getUsername(), userCreationDto.getEmail());
        user.setPassword(passwordEncoder.encode(userCreationDto.getPassword()));
        return convertEntityToDto(userRepository.save(user));
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
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
            if(validCredentials(user)) {
                user.setEmail(userDto.getEmail());
                user.setPhoneNumber(userDto.getPhoneNumber());
                userRepository.save(user);
            }
        } catch (HttpClientErrorException.BadRequest | UserAlreadyExistsException e) {
            e.printStackTrace();
            //return null;
        }
        //return convertEntityToDto(userRepository.findUserByID(id));
    }

    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (HttpClientErrorException.BadRequest e) {
            e.printStackTrace();
        }
    }

    private UserDto convertEntityToDto(User user) { // TODO: replace with mapper
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public boolean validCredentials(User user) throws UserAlreadyExistsException {
        if (userRepository.findUserByEmail(user.getEmail()) != null){
            throw new UserAlreadyExistsException("An account with this email address already exists: " + user.getEmail());
        }
        if (!user.getPassword().equals(user.getConfirmedPassword())) {
            throw new IllegalStateException("password doesn't match");
        }
        return true;
    }

    private boolean emailExists(final String email) {
        return userRepository.findUserByEmail(email) != null;
    }

}
