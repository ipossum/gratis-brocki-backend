package ch.zhaw.gratisbrockibackend.mapper;

import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.dto.UserCreationDto;
import ch.zhaw.gratisbrockibackend.dto.UserDto;
import ch.zhaw.gratisbrockibackend.dto.UserUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto (User user);
    UserCreationDto toUserCreationDto(User user);
    UserUpdateDto toUserUpdateDto(User user);

    User toUser (UserDto userDto);
    User toUser (UserCreationDto userCreationDto);
    User toUser (UserUpdateDto userUpdateDto);

}


