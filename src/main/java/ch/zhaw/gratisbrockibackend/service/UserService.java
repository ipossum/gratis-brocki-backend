package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.auth.InvalidPasswordException;
import ch.zhaw.gratisbrockibackend.auth.UserAlreadyExistsException;
import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.dto.UserCreationDto;
import ch.zhaw.gratisbrockibackend.dto.UserDto;
import ch.zhaw.gratisbrockibackend.dto.UserUpdateDto;
import ch.zhaw.gratisbrockibackend.mapper.UserMapper;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;
import ch.zhaw.gratisbrockibackend.utils.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@AllArgsConstructor
@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final UserValidator userValidator;

    public User registerNewUser(final UserCreationDto userCreationDto) throws HttpClientErrorException, UserAlreadyExistsException, InvalidPasswordException {
        try {
            userValidator.checkCredentials(userCreationDto);
            User user = userMapper.toUser(userCreationDto);
            user.setPassword(passwordEncoder.encode(userCreationDto.getPassword()));
            userRepository.save(user);
            return user;
            //return userMapper.toUserDto(user);
        } catch (HttpClientErrorException.BadRequest | UserAlreadyExistsException | InvalidPasswordException e) {
            return null;
        }
    }

    public UserDto getUser(Long id) {
        try {
            return userMapper.toUserDto(userRepository.findUserById(id));
        } catch (HttpClientErrorException.BadRequest e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        try {
            User user = userRepository.findUserById(id);
            userValidator.checkCredentials(userUpdateDto, user);
            user.setUsername(userUpdateDto.getUsername());
            user.setEmail(userUpdateDto.getEmail());
            user.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
            user.setPhoneNumber(userUpdateDto.getPhoneNumber());
            userRepository.save(user);
            return userMapper.toUserDto(user);
        } catch (HttpClientErrorException.BadRequest | UserAlreadyExistsException | InvalidPasswordException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (HttpClientErrorException.BadRequest e) {
            e.printStackTrace();
        }
    }
}
