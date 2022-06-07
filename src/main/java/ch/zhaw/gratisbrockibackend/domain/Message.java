package ch.zhaw.gratisbrockibackend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
