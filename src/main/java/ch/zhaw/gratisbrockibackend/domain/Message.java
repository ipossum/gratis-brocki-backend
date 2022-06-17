package ch.zhaw.gratisbrockibackend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Message extends BaseEntity {
    private String message;

    @ManyToOne
    private Item item;

    public Message(String createdBy, String message, Item item) {
        super(createdBy);
        this.message = message;
        this.item = item;
    }

}
