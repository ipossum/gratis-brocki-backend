package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.dto.UserUpdateDto;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;
import ch.zhaw.gratisbrockibackend.utils.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final UserValidator userValidator;

    private final BCryptPasswordEncoder passwordEncoder;

    //private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user) {
        userValidator.plausibilityCheck(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        userValidator.exists(id);
        return userRepository.findUserById(id);
    }

    public User updateUser(Long id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findUserById(id);
        userValidator.plausibilityCheck(userUpdateDto, user);
        user.setUsername(userUpdateDto.getUsername());
        user.setEmail(userUpdateDto.getEmail());
        user.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
        user.setPhoneNumber(userUpdateDto.getPhoneNumber());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userValidator.exists(id);
        userRepository.deleteById(id);
    }

}
