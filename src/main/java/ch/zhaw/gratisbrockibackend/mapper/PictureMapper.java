package ch.zhaw.gratisbrockibackend.mapper;

import ch.zhaw.gratisbrockibackend.domain.Item;
import ch.zhaw.gratisbrockibackend.domain.Picture;
import ch.zhaw.gratisbrockibackend.dto.ItemCreationDto;
import ch.zhaw.gratisbrockibackend.dto.ItemDto;
import ch.zhaw.gratisbrockibackend.dto.PictureCreationDto;
import ch.zhaw.gratisbrockibackend.dto.PictureDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PictureMapper {

    @Mapping(target = "userId", source = "owner.id")
    ItemDto toItemDto (Item item);

    @Mapping(target = "owner.id", source = "userId")
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "archivedDate", ignore = true)
    Item toItem (ItemCreationDto itemCreationDto);


    @Mapping(target = "itemId", source = "item.id")
    PictureDto toPictureDto(Picture picture);

    @Mapping(target = "item.id", source = "itemId")
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "item", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "archivedDate", ignore = true)
    Picture toPicture (PictureCreationDto pictureCreationDto);

}


