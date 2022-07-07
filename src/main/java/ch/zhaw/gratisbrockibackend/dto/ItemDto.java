package ch.zhaw.gratisbrockibackend.dto;

import ch.zhaw.gratisbrockibackend.domain.enums.Category;
import ch.zhaw.gratisbrockibackend.domain.enums.Condition;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemDto {

    private Long id;
    private String title;
    private String description;
    private int zipCode;
    private Category category;
    private Condition condition;
    private Long userId;
    private List<PictureDto> pictures;

    // Commented out, because it is not yet used
    //private List<MessageDto> messages;

}
