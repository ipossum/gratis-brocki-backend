package ch.zhaw.gratisbrockibackend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Message extends BaseEntity {
    private String message;

    @ManyToOne
    private Item item;

    @Override
    public String toString(){
        return "Message{" +
                "message: " + message + '\'' +
                '}';
    }
}
