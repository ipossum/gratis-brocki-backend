/*
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

    // Attribute 'itemId' is directly mapped to the attribute 'id' of 'item' (of type 'Item')
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


*/
