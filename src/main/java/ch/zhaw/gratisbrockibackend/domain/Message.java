package ch.zhaw.gratisbrockibackend.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Entity that is planned to hold messages sent between users
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Message extends BaseEntity {

    private String message;

    @ManyToOne
    private Item item;

}
