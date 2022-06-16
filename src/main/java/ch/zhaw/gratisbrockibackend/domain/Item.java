package ch.zhaw.gratisbrockibackend.domain;

import ch.zhaw.gratisbrockibackend.domain.enums.Category;
import ch.zhaw.gratisbrockibackend.domain.enums.Condition;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Getter
@Setter
@Entity
public class Item extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "zip_code", nullable = false)
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

    public Item() {}

    public Item(String title, String description, int zipCode, Category category, Condition condition) {
        this.title = title;
        this.description = description;
        this.zipCode = zipCode;
        this.category = category;
        this.condition = condition;
        this.pictures = new HashSet<>();
        this.messages = new HashSet<>();
    }

    @Override
    public String toString(){
        return "Item{" +
                "id: " + id + '\'' +
                "title: " + title + '\'' +
                "description: " + description + '\'' +
                "zip code: " + zipCode + '\'' +
                "item category: " + category + '\'' +
                "item condition: " + condition + '\'' +
                "created date: " + createdDate + '\'' +
                "owner: " + owner + '\'' +
                '}';
    }
}

