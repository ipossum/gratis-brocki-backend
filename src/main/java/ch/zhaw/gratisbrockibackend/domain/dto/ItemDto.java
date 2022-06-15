package ch.zhaw.gratisbrockibackend.domain.dto;

import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.domain.enums.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

    private String title;
    private String description;
    private int zipCode;
    private Category category;
    private User owner;

}
