package ch.zhaw.gratisbrockibackend.utils;

import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.dto.UserUpdateDto;
import ch.zhaw.gratisbrockibackend.exceptions.UserException;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
        String phoneNumber = user.getPhoneNumber();

        if (userRepository.existsByEmail(email)){
            throw new UserException("An account with this email address already exists: " + email);
        }
        if (userRepository.existsByUsername(username)) {
            throw new UserException("An account with this username already exists: " + username);
        }
        commonPlausibilityCheck(username, email, password, phoneNumber);
    }

    public void plausibilityCheck (UserUpdateDto userUpdateDto, User user) throws UserException {

        String username = userUpdateDto.getUsername();
        String email = userUpdateDto.getEmail();
        String password = userUpdateDto.getPassword();
        String phoneNumber = user.getPhoneNumber();

        if ( !user.getEmail().equals(email)
                && userRepository.existsByEmail(email)) {
            throw new UserException("An account with this email address already exists: " + email);
        }
        if ( !user.getUsername().equals(username)
                && userRepository.existsByUsername(username) ) {
            throw new UserException("An account with this username already exists: " + username);
        }
        commonPlausibilityCheck(username, email, password, phoneNumber);
    }

    private void commonPlausibilityCheck(String username, String email, String password, String phoneNumber) throws UserException {
        if (password == null
                || password.length() < MIN_LENGTH_PASSWORD
                || password.length() > MAX_LENGTH_PASSWORD) {
            throw new UserException("Invalid password - Make sure the size is between 8 and 40 symbols");
        }
        if (!passwordValidation(password)){
            throw new UserException("Invalid password - Password must contain letters, numbers and special characters");
        }
        if (username == null
                || username.length() <= 3 || username.length() > 254) {
            throw new UserException("Invalid username");
        }
        if (!(phoneNumber.startsWith("076") ||
                phoneNumber.startsWith("077") ||
                phoneNumber.startsWith("078") ||
                phoneNumber.startsWith("079"))) {
            throw new UserException("Invalid phone-number");
        }
        if (email == null
                || email.length() <= 6){
            throw new UserException("Invalid email");
        }
        if (!emailValidation(email)) {
            throw new UserException("Invalid email - your mail address has the wrong format");
        }
        if (email.length() > 254) {
            throw new UserException("Invalid email - your email is too long");
        }
    }

    public static boolean passwordValidation(String password)
    {
        Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

        Matcher hasLetter = letter.matcher(password);
        Matcher hasDigit = digit.matcher(password);
        Matcher hasSpecial = special.matcher(password);

        return hasLetter.find() && hasDigit.find() && hasSpecial.find();
    }

    private static boolean emailValidation(String email) {
        return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                .matcher(email)
                .matches();
    }

}
