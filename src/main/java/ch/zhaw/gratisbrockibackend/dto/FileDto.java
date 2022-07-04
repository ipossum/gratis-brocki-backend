package ch.zhaw.gratisbrockibackend.dto;

import ch.zhaw.gratisbrockibackend.domain.BaseEntity;
import ch.zhaw.gratisbrockibackend.domain.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class FileDto {

    private Long id;
    private String filename;
    private String filetype;
    private byte[] data;
    private Long itemId;

}
