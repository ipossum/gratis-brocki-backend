package ch.zhaw.gratisbrockibackend.utils;

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

    private final UserRepository userRepository;

    public void checkCredentials (UserCreationDto userCreationDto) throws UserAlreadyExistsException {
        if (userRepository.existsByEmail(userCreationDto.getEmail())){
            throw new UserAlreadyExistsException("An account with this email address already exists: "
                    + userCreationDto.getEmail());
        }
        if (userRepository.existsByUsername(userCreationDto.getUsername())) {
            throw new UserAlreadyExistsException("An account with this username already exists: "
                    + userCreationDto.getUsername());
        }

    }

    public void checkCredentials (UserUpdateDto userUpdateDto, User user) throws UserAlreadyExistsException {
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
    }

}
