package ch.zhaw.gratisbrockibackend.dto;

import ch.zhaw.gratisbrockibackend.domain.enums.Category;
import ch.zhaw.gratisbrockibackend.domain.enums.Condition;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ItemDto {

    private String title;
    private String description;
    private int zipCode;
    private Category category;
    private Condition condition;
    private Long userId;
    private Set<PictureDto> pictures;
    private List<MessageDto> messages;

}
