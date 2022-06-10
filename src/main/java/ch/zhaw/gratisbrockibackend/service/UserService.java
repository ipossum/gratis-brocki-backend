package ch.zhaw.gratisbrockibackend.service;


import ch.zhaw.gratisbrockibackend.auth.UserAlreadyExistException;
import ch.zhaw.gratisbrockibackend.domain.Role;
import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.repository.RoleRepository;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public User registerNewUserAccount(final User userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        }
        final User user = new User(userDto.getFullName(), userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByRole("ROLE_USER"))));
        return userRepository.save(user);
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }

}
