package ch.zhaw.gratisbrockibackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemListDto {

    private String title;
    private int zipCode;
    private PictureDto picture;

}
