package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.auth.UserAlreadyExistsException;
import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.dto.UserCreationDto;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findUserById(id);
    }

    private boolean emailExists(final String email) {
        return userRepository.findUserByEmail(email) != null;
    }

}
