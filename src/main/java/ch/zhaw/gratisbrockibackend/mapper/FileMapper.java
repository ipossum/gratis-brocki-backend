package ch.zhaw.gratisbrockibackend.mapper;

import ch.zhaw.gratisbrockibackend.domain.File;
import ch.zhaw.gratisbrockibackend.dto.FileDto;
import ch.zhaw.gratisbrockibackend.dto.FileResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FileMapper {

    @Mapping(target = "url", ignore = true)
    @Mapping(target = "size", ignore = true)
    FileResponseDto toFileResponseDto (File file);

    @Mapping(target = "itemId", source = "item.id")
    FileDto toFileDto (File file);

}


