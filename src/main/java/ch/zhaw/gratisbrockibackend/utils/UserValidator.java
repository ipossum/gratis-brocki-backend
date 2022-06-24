package ch.zhaw.gratisbrockibackend.utils;

import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.dto.UserUpdateDto;
import ch.zhaw.gratisbrockibackend.exceptions.UserException;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Setter
@Getter
@Component
public class UserValidator { // TODO: add additional, more sophisticated plausibility checks (e.g. DTO correctly formed?)

    private static final int MIN_LENGTH_PASSWORD = 8;
    private static final int MAX_LENGTH_PASSWORD = 40;

    private final UserRepository userRepository;

    public void exists (Long id) throws UserException {
        if (!userRepository.existsById(id)){
            throw new UserException("User not found");
        }
    }

    public void plausibilityCheck (User user) throws UserException {

        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();

        if (userRepository.existsByEmail(email)){
            throw new UserException("An account with this email address already exists: " + email);
        }
        if (userRepository.existsByUsername(username)) {
            throw new UserException("An account with this username already exists: " + username);
        }
        commonPlausibilityCheck(username, email, password);
    }

    public void plausibilityCheck (UserUpdateDto userUpdateDto, User user) throws UserException {

        String username = userUpdateDto.getUsername();
        String email = userUpdateDto.getEmail();
        String password = userUpdateDto.getPassword();

        if ( !user.getEmail().equals(email)
                && userRepository.existsByEmail(email)) {
            throw new UserException("An account with this email address already exists: " + email);
        }
        if ( !user.getUsername().equals(username)
                && userRepository.existsByUsername(username) ) {
            throw new UserException("An account with this username already exists: " + username);
        }
        commonPlausibilityCheck(username, email, password);
    }

    private void commonPlausibilityCheck(String username, String email, String password) throws UserException {
        if (password == null
                || password.length() < MIN_LENGTH_PASSWORD
                || password.length() > MAX_LENGTH_PASSWORD) {
            throw new UserException("Invalid password - Make sure the size is between 8 and 40 symbols");
        }
        if (username == null
                || username.length() <= 3) {
            throw new UserException("Invalid username");
        }

        if (email == null
                || email.length() <= 6){
            throw new UserException("Invalid email");
        }
    }

}
