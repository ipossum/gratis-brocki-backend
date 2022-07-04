package ch.zhaw.gratisbrockibackend.domain;

import ch.zhaw.gratisbrockibackend.domain.enums.Category;
import ch.zhaw.gratisbrockibackend.domain.enums.Condition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Entity that holds an item of a user
 */

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Item extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 1200)
    private String description;

    @Column(nullable = false, length = 4)
    private int zipCode;

    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Condition condition;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<File> pictures;

    /* Commented out, because it is not yet used
    @OneToMany (mappedBy = "item", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<Message> messages;
    */

}

