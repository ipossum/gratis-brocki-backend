package ch.zhaw.gratisbrockibackend.config;

import ch.zhaw.gratisbrockibackend.domain.*;
import ch.zhaw.gratisbrockibackend.domain.enums.Category;
import ch.zhaw.gratisbrockibackend.domain.enums.Condition;
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

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    PictureRepository pictureRepository;

    public DevConfiguration() {
        getLogger().info("DevConfiguration Class");
    }

    @PostConstruct
    public void test() {
        createUserData();
        createItemData(userRepository.findUserById(1L));
        //createMessageData();
        createPictureData(itemRepository.findItemById(1L));
    }

    private void createUserData() {
        User user = new User("jackass", "user@gmx.ch");
        user.setPassword("123456");
        userRepository.save(user);
    }

    private void createItemData(User owner) {
        Item item = new Item("Andy", owner, "Meine Steuerrechnung", "kaum benutzte und nicht bezahlte Steuerrechnung abzugeben", 8000, Category.ChildrenItemCategory, Condition.USED);
        itemRepository.save(item);
        //user.addItem(item);   // issues here with FetchType.LAZY in class User
        // -> FetchType.EAGER works, but is not recommended due to potential overhead (use LAZY and JOIN FETCH instead!)
    }

    /*private void createMessageData(){
        Message message = new Message(item, );
        message.setMessage("Das ist die erste Nachricht in unserem Forum");
        message.setCreatedBy("Alex");
        messageRepository.save(message);
    }*/

    private void createPictureData(Item item){
        Picture picture = new Picture("Ben", item, "Euphonium", "https://4.imimg.com/data4/AV/OU/MY-1985769/gold-euphonium-500x500.jpg");
        pictureRepository.save(picture);
    }
}
