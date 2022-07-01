package ch.zhaw.gratisbrockibackend.mapper;

import ch.zhaw.gratisbrockibackend.domain.Item;
import ch.zhaw.gratisbrockibackend.dto.ItemCreationDto;
import ch.zhaw.gratisbrockibackend.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "userId", source = "owner.id")
    ItemDto toItemDto (Item item);

    @Mapping(target = "owner.id", source = "userId")
    @Mapping(target = "owner", ignore = true)
    //@Mapping(target = "messages", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "archivedDate", ignore = true)
    Item toItem (ItemCreationDto itemCreationDto);

}


