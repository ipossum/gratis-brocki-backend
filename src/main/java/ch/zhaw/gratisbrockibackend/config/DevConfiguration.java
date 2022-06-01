package ch.zhaw.gratisbrockibackend.config;

import ch.zhaw.gratisbrockibackend.domain.*;
import ch.zhaw.gratisbrockibackend.domain.enums.Category;
import ch.zhaw.gratisbrockibackend.repository.*;
import ch.zhaw.gratisbrockibackend.utils.HasLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile("dev")
public class DevConfiguration implements HasLogger {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;


    public DevConfiguration() {
        getLogger().info("DevConfiguration Class");
    }

    @PostConstruct
    public void test() {
        createUserData();
        createItemData(userRepository.findUserByID(1L));
    }

    private User createUserData() {
        User user = new User();
        user.setEmail("user@gmx.ch");
        user.setUsername("jackass");
        user = userRepository.save(user);
        return user;
    }

    private Item createItemData(User user) {
        Item item = new Item();
        //item.setOwner(user);
        item.setTitle("Meine Steuerrechnung");
        item.setDescription("kaum benutzte und nicht bezahlte Steuerrechnung abzugeben");
        item.setZipCode(8000);
        item.setCategory(Category.ChildrenItemCategory);
        item = itemRepository.save(item);
        //user.addItem(item);   // issues here with FetchType.LAZY in class User
        // -> FetchType.EAGER works, but is not recommended due to potential overhead (use LAZY and JOIN FETCH instead!)
        return item;
    }

}
