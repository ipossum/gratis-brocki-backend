package ch.zhaw.gratisbrockibackend.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
public class Message extends BaseEntity {
    private String message;

    @ManyToOne
    private Item item;

    public Message() {}

    public Message(String createdBy, String message, Item item) {
        super(createdBy);
        this.message = message;
        this.item = item;
    }

}
