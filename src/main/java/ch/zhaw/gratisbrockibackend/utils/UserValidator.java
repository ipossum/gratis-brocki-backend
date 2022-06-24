package ch.zhaw.gratisbrockibackend.utils;

import ch.zhaw.gratisbrockibackend.auth.InvalidPasswordException;
import ch.zhaw.gratisbrockibackend.auth.UserAlreadyExistsException;
import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.dto.UserCreationDto;
import ch.zhaw.gratisbrockibackend.dto.UserUpdateDto;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Setter
@Getter
@Component
public class UserValidator { // TODO: add additional plausibility checks

    private static final int MIN_LENGTH_PASSWORD = 8;
    private static final int MAX_LENGTH_PASSWORD = 40;
    private final UserRepository userRepository;

    public void checkCredentials (UserCreationDto userCreationDto) throws UserAlreadyExistsException, InvalidPasswordException {
        if (userRepository.existsByEmail(userCreationDto.getEmail())){
            throw new UserAlreadyExistsException("An account with this email address already exists: "
                    + userCreationDto.getEmail());
        }
        if (userRepository.existsByUsername(userCreationDto.getUsername())) {
            throw new UserAlreadyExistsException("An account with this username already exists: "
                    + userCreationDto.getUsername());
        }
        if (userCreationDto.getPassword().length() < MIN_LENGTH_PASSWORD || userCreationDto.getPassword().length() > MAX_LENGTH_PASSWORD) {
            throw new InvalidPasswordException("Chosen password is invalid - Make sure the size is between 8 and 40 symbols");
        }
    }

    public void checkCredentials (UserUpdateDto userUpdateDto, User user) throws UserAlreadyExistsException, InvalidPasswordException {
        if ( !user.getEmail().equals(userUpdateDto.getEmail()) &&
                userRepository.existsByEmail(userUpdateDto.getEmail()) ) {
            throw new UserAlreadyExistsException("An account with this email address already exists: "
                    + userUpdateDto.getEmail());
        }
        if ( !user.getUsername().equals(userUpdateDto.getUsername()) &&
                userRepository.existsByUsername(userUpdateDto.getUsername()) ) {
            throw new UserAlreadyExistsException("An account with this username already exists: "
                    + userUpdateDto.getUsername());
        }
        if (userUpdateDto.getPassword().length() < MIN_LENGTH_PASSWORD || userUpdateDto.getPassword().length() > MAX_LENGTH_PASSWORD) {
            throw new InvalidPasswordException("Chosen password is invalid - Make sure the size is between 8 and 40 symbols");
        }
    }

}
