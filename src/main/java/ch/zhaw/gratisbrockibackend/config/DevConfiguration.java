package ch.zhaw.gratisbrockibackend.config;

import ch.zhaw.gratisbrockibackend.domain.Item;
import ch.zhaw.gratisbrockibackend.domain.Picture;
import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.domain.enums.Category;
import ch.zhaw.gratisbrockibackend.domain.enums.Condition;
import ch.zhaw.gratisbrockibackend.repository.ItemRepository;
import ch.zhaw.gratisbrockibackend.repository.MessageRepository;
import ch.zhaw.gratisbrockibackend.repository.PictureRepository;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;
import ch.zhaw.gratisbrockibackend.utils.HasLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DevConfiguration() {
        getLogger().info("DevConfiguration Class");
    }

    @PostConstruct
    public void test() {
        createUserData();
        createItemData(userRepository.findUserById(1L), userRepository.findUserById(2L));
        createPictureData(itemRepository.findItemById(1L), itemRepository.findItemById(2L), itemRepository.findItemById(3L), itemRepository.findItemById(4L));
    }

    private void createUserData() {
        User user1 = new User();
        user1.setUsername("Jackass");
        user1.setEmail("user@me.to");
        user1.setPhoneNumber("066 333 55 00");
        user1.setPassword(passwordEncoder.encode("123456"));
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("Hans");
        user2.setEmail("who@me.to");
        user2.setPhoneNumber("033 222 77 99");
        user2.setPassword(passwordEncoder.encode("qwertz"));
        userRepository.save(user2);
    }

    private void createItemData(User owner1, User owner2) {
        Item item1 = new Item();
        item1.setTitle("Meine Steuerrechnung");
        item1.setDescription("kaum benutzte und nicht bezahlte Steuerrechnung abzugeben");
        item1.setZipCode(8000);
        item1.setCategory(Category.CHILDREN);
        item1.setCondition(Condition.USED);
        item1.setOwner(owner1);
        itemRepository.save(item1);

        Item item2 = new Item();
        item2.setTitle("Laptop");
        item2.setDescription("Gebraucht, aber noch super Zustand");
        item2.setZipCode(5001);
        item2.setCategory(Category.HOUSEHOLD);
        item2.setCondition(Condition.USED);
        item2.setOwner(owner1);
        itemRepository.save(item2);

        Item item3 = new Item();
        item3.setTitle("Velo");
        item3.setDescription("Total kaputt");
        item3.setZipCode(8088);
        item3.setCategory(Category.HOUSEHOLD);
        item3.setCondition(Condition.DEFECTIVE);
        item3.setOwner(owner2);
        itemRepository.save(item3);

        Item item4 = new Item();
        item4.setTitle("Rucksack");
        item4.setDescription("Alter Militärrucksack");
        item4.setZipCode(7001);
        item4.setCategory(Category.OTHER);
        item4.setCondition(Condition.USED);
        item4.setOwner(owner2);
        itemRepository.save(item4);
    }

    private void createPictureData(Item item1, Item item2, Item item3, Item item4){

        // item 1
        Picture picture1 = new Picture();
        picture1.setName("Euphonium");
        picture1.setUrl("https://4.imimg.com/data4/AV/OU/MY-1985769/gold-euphonium-500x500.jpg");
        picture1.setItem(item1);
        pictureRepository.save(picture1);

        Picture picture2 = new Picture();
        picture2.setName("Placeholder");
        picture2.setUrl("https://artgalleryofballarat.com.au/wp-content/uploads/2020/06/placeholder-image.png");
        picture2.setItem(item1);
        pictureRepository.save(picture2);

        // item 2
        Picture picture3 = new Picture();
        picture3.setName("Euphonium");
        picture3.setUrl("https://4.imimg.com/data4/AV/OU/MY-1985769/gold-euphonium-500x500.jpg");
        picture3.setItem(item2);
        pictureRepository.save(picture3);

        Picture picture4 = new Picture();
        picture4.setName("Placeholder");
        picture4.setUrl("https://artgalleryofballarat.com.au/wp-content/uploads/2020/06/placeholder-image.png");
        picture4.setItem(item2);
        pictureRepository.save(picture4);

        // item 3
        Picture picture5 = new Picture();
        picture5.setName("Euphonium");
        picture5.setUrl("https://4.imimg.com/data4/AV/OU/MY-1985769/gold-euphonium-500x500.jpg");
        picture5.setItem(item3);
        pictureRepository.save(picture5);

        Picture picture6 = new Picture();
        picture6.setName("Placeholder");
        picture6.setUrl("https://artgalleryofballarat.com.au/wp-content/uploads/2020/06/placeholder-image.png");
        picture6.setItem(item3);
        pictureRepository.save(picture6);

        // item 4
        Picture picture7 = new Picture();
        picture7.setName("Euphonium");
        picture7.setUrl("https://4.imimg.com/data4/AV/OU/MY-1985769/gold-euphonium-500x500.jpg");
        picture7.setItem(item4);
        pictureRepository.save(picture7);

        Picture picture8 = new Picture();
        picture8.setName("Placeholder");
        picture8.setUrl("https://artgalleryofballarat.com.au/wp-content/uploads/2020/06/placeholder-image.png");
        picture8.setItem(item4);
        pictureRepository.save(picture8);
    }
}

