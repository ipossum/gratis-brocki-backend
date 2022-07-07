package ch.zhaw.gratisbrockibackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDto {

    private Long id;
    private String filename;
    private String filetype;
    private byte[] data;
    private Long itemId;

}
