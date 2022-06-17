package ch.zhaw.gratisbrockibackend.domain;

import ch.zhaw.gratisbrockibackend.domain.enums.Category;
import ch.zhaw.gratisbrockibackend.domain.enums.Condition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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
    private Set<Picture> pictures;

    @OneToMany (mappedBy = "item")
    private Set<Message> messages;
    // TODO: does a set fit here? does this cause problems when two messages are identical (e.g. "Thanks")?

    public Item () {}

    public Item(String createdBy, User owner, String title, String description, int zipCode, Category category, Condition condition) {
        super(createdBy); // TODO: replace with user id
        this.owner = owner;
        this.title = title;
        this.description = description;
        this.zipCode = zipCode;
        this.category = category;
        this.condition = condition;
        this.pictures = new HashSet<>();
        this.messages = new HashSet<>();
    }

}

