package ch.zhaw.gratisbrockibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FileDto {

    private String filename;
    private String url;
    private String filetype;
    private long size;

}
