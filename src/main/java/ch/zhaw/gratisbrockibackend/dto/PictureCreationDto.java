package ch.zhaw.gratisbrockibackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PictureCreationDto {

    private String name;
    private String url;
    private Long itemId;

}
