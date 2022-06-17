package ch.zhaw.gratisbrockibackend.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Picture extends BaseEntity {

    private String name;
    private String url;

    @ManyToOne
    private Item item;

    public Picture() {}

    public Picture(String createdBy, Item item, String name, String url) {
        super(createdBy);
        this.item = item;
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString(){
        return "Picture{" +
                "name: " + name + '\'' +
                "URL: " + url + '\'' +
                '}';
    }
}
