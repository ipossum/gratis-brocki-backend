package ch.zhaw.gratisbrockibackend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Picture extends BaseEntity {

    private String name;
    private String url;

    @OneToOne
    private Item item;

    @Override
    public String toString(){
        return "Picture{" +
                "name: " + name + '\'' +
                "URL: " + url + '\'' +
                "item: " + item + '\'' +
                '}';
    }
}
