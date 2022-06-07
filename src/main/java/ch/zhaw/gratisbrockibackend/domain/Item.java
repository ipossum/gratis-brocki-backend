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

    private String title;
    private String description;
    private int zipCode;
    private Category category;
    private Condition condition;
    //private LinkedList<Message> messages;

    @ManyToOne
    private User owner;

    @OneToOne(mappedBy = "item", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    Picture picture;

    @OneToMany (mappedBy = "message")
    private Set<Message> messages = new HashSet<>();

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

