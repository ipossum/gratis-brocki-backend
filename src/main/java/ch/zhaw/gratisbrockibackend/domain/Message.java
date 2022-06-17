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

    public Message(Item item, String message) {
        super();
        this.item = item;
        this.message = message;
    }

}
