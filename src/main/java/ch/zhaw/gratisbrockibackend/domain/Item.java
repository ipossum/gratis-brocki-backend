package ch.zhaw.gratisbrockibackend.domain;

import ch.zhaw.gratisbrockibackend.domain.enums.Category;
import ch.zhaw.gratisbrockibackend.domain.enums.Condition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.LinkedList;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Item extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int zipCode;

    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Condition condition;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "item")
    private HashSet<Picture> pictures;

    @OneToMany (mappedBy = "item")
    private LinkedList<Message> messages;

}

