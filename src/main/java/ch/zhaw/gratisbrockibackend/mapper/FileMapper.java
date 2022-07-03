package ch.zhaw.gratisbrockibackend.mapper;

import ch.zhaw.gratisbrockibackend.domain.File;
import ch.zhaw.gratisbrockibackend.domain.Item;
import ch.zhaw.gratisbrockibackend.dto.FileDto;
import ch.zhaw.gratisbrockibackend.dto.ItemCreationDto;
import ch.zhaw.gratisbrockibackend.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FileMapper {

    @Mapping(target = "url", ignore = true)
    @Mapping(target = "size", ignore = true)
    FileDto toFileDto (File file);

}


