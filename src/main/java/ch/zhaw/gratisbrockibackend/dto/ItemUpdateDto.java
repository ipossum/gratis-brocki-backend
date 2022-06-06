package ch.zhaw.gratisbrockibackend.dto;


import ch.zhaw.gratisbrockibackend.domain.Picture;
import ch.zhaw.gratisbrockibackend.domain.enums.Condition;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ItemUpdateDto {

    private Date lastModifiedDate;
    private String lastModifiedBy;
    private String title;
    private String description;
    private int zipCode;
    private Condition condition;
    private Picture picture;

}
