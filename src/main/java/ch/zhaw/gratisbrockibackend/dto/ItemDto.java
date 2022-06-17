package ch.zhaw.gratisbrockibackend.dto;

import ch.zhaw.gratisbrockibackend.domain.enums.Category;
import ch.zhaw.gratisbrockibackend.domain.enums.Condition;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedList;

@Getter
@Setter
public class ItemDto {

    private String title;
    private String description;
    private int zipCode;
    private Category category;
    private Condition condition;
    private String username;
    private HashSet<PictureDto> pictures;
    private LinkedList<MessageDto> messages;

}
