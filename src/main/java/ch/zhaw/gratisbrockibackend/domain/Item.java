package ch.zhaw.gratisbrockibackend.domain;

import ch.zhaw.gratisbrockibackend.domain.enums.Category;
import ch.zhaw.gratisbrockibackend.domain.enums.Condition;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;

@Getter
@Setter
@Entity
@Table(name = "item")
public class Item extends BaseEntity {

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private int zipCode;

    @Column(nullable = false)
    private Category category;

    private Condition condition;
    //private LinkedList<Message> messages;

    @ManyToOne
    private User owner;

    @OneToOne(mappedBy = "item", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    Picture picture;

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

