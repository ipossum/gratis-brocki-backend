package ch.zhaw.gratisbrockibackend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Picture extends BaseEntity {

    private String name;
    private String url;

    @ManyToOne
    private Item item;

}
