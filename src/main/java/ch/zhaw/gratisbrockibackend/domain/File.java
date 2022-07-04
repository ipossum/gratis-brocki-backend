package ch.zhaw.gratisbrockibackend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class File extends BaseEntity {

    @Column(nullable = false, length = 40)
    private String filename;

    @Column(nullable = false)
    private String filetype;

    //@Column(nullable = false)
    @Lob // LOB is a datatype for storing objects (in this case BLOB, or binary large object)
    private byte[] data;

    @ManyToOne
    private Item item;

}
