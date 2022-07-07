package ch.zhaw.gratisbrockibackend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Picture extends BaseEntity {

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    private Item item;

}
