package ch.zhaw.gratisbrockibackend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Lob;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class File extends BaseEntity {

    private String filename;

    private String filetype;

    @Lob // LOB is a datatype for storing objects (in this case BLOB, or binary large object)
    private byte[] data;

}
