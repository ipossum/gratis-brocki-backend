package ch.zhaw.gratisbrockibackend.mapper;

import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.dto.UserCreationDto;
import ch.zhaw.gratisbrockibackend.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto (User user);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "credentialsNonExpired", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "archivedDate", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    User toUser (UserCreationDto userCreationDto);

}


