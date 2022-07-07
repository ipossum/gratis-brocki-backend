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
    PictureRepository pictureRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public DevConfiguration() {
        getLogger().info("DevConfiguration Class");
    }

    @PostConstruct
    public void test() {
        createUserData();
        createItemData(userRepository.findUserById(1L), userRepository.findUserById(2L), userRepository.findUserById(3L), userRepository.findUserById(4L));
        createPictureData(itemRepository.findItemById(1L), itemRepository.findItemById(2L), itemRepository.findItemById(3L), itemRepository.findItemById(4L), itemRepository.findItemById(5L), itemRepository.findItemById(6L), itemRepository.findItemById(7L), itemRepository.findItemById(8L), itemRepository.findItemById(9L), itemRepository.findItemById(10L));
    }

    private void createUserData() {
        User user1 = new User();
        user1.setUsername("Jackass");
        user1.setEmail("user@tmail.ch");
        user1.setPhoneNumber("0779483928");
        user1.setPassword(passwordEncoder.encode("123456"));
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("Hans");
        user2.setEmail("who@tmail.ch");
        user2.setPhoneNumber("0784950294");
        user2.setPassword(passwordEncoder.encode("qwertz@6789"));
        userRepository.save(user2);

        User user3 = new User();
        user3.setUsername("Claudia");
        user3.setEmail("claudia@tmail.ch");
        user3.setPhoneNumber("0794568329");
        user3.setPassword(passwordEncoder.encode("zuiop#4567"));
        userRepository.save(user3);

        User user4 = new User();
        user4.setUsername("Pia");
        user4.setEmail("pia.eugster@mymail.ch");
        user4.setPhoneNumber("0769407382");
        user4.setPassword(passwordEncoder.encode("asdfg@4567"));
        userRepository.save(user4);

    }

    private void createItemData(User owner1, User owner2, User owner3, User owner4) {
        Item item1 = new Item();
        item1.setTitle("Meine Steuerrechnung");
        item1.setDescription("kaum benutzte und nicht bezahlte Steuerrechnung abzugeben");
        item1.setZipCode(8000);
        item1.setCategory(Category.OTHER);
        item1.setCondition(Condition.USED);
        item1.setOwner(owner1);
        itemRepository.save(item1);

        Item item2 = new Item();
        item2.setTitle("Notebook");
        item2.setDescription("Ende der 1980er-Jahre führte Toshiba die Bezeichnung Notebook ein, um besonders kompakte und leichte (wie ein Notizbuch) Geräte besser vermarkten zu können");
        item2.setZipCode(5001);
        item2.setCategory(Category.HOUSEHOLD);
        item2.setCondition(Condition.USED);
        item2.setOwner(owner1);
        itemRepository.save(item2);

        Item item3 = new Item();
        item3.setTitle("Velo");
        item3.setDescription("Ein Fahrrad, kurz Rad, in der Schweiz Velo, ist ein mindestens zweirädriges, für gewöhnlich einspuriges Landfahrzeug, das ausschließlich durch die Muskelkraft auf ihm befindlicher Personen durch das Treten von Pedalen oder Handkurbeln angetrieben wird.");
        item3.setZipCode(8088);
        item3.setCategory(Category.VEHICLE);
        item3.setCondition(Condition.DEFECTIVE);
        item3.setOwner(owner2);
        itemRepository.save(item3);

        Item item4 = new Item();
        item4.setTitle("Rucksack");
        item4.setDescription("Der Rucksack ist ein Behälter aus Stoff, flexiblem Kunststoff (Nylon oder PVC) oder Leder, der an Gurten auf dem Rücken getragen wird und dem Transport von Gegenständen dient");
        item4.setZipCode(7001);
        item4.setCategory(Category.CLOTHING);
        item4.setCondition(Condition.USED);
        item4.setOwner(owner2);
        itemRepository.save(item4);

        Item item5 = new Item();
        item5.setTitle("Smartphone");
        item5.setDescription("Smartphone mit vorinstallierter SpyderApp");
        item5.setZipCode(9014);
        item5.setCategory(Category.HOUSEHOLD);
        item5.setCondition(Condition.DEFECTIVE);
        item5.setOwner(owner3);
        itemRepository.save(item5);

        Item item6 = new Item();
        item6.setTitle("Kinderwagen");
        item6.setDescription("Der Kinderwagen ist ein Transportmittel, mit dem Säuglinge und Kleinkinder im Liegen oder im Sitzen befördert werden.");
        item6.setZipCode(9014);
        item6.setCategory(Category.CHILDREN);
        item6.setCondition(Condition.NEW);
        item6.setOwner(owner3);
        itemRepository.save(item6);

        Item item7 = new Item();
        item7.setTitle("Geburtstagskuchen");
        item7.setDescription("Halb aufgegessener Kuchen, ist aber noch frisch (bis jetzt)");
        item7.setZipCode(6591);
        item7.setCategory(Category.OTHER);
        item7.setCondition(Condition.USED);
        item7.setOwner(owner4);
        itemRepository.save(item7);

        Item item8 = new Item();
        item8.setTitle("Schubkarre");
        item8.setDescription("Eine Schubkarre (in der Schweiz auch Karrette oder Garette, in Österreich und Bayern auch Scheibtruhe bzw. Scheibtruche, in oberdeutschen Dialekten „Radbere“ und „Radwer“, in Franken „Rowern/Robbern“, in Thüringen „Radeberle“ und in niederdeutschen Dialekten „Schiebkarre“) ist ein Hilfsmittel zum Transport von Schüttgütern und anderen Lasten durch eine Person.");
        item8.setZipCode(6591);
        item8.setCategory(Category.GARDEN);
        item8.setCondition(Condition.USED);
        item8.setOwner(owner4);
        itemRepository.save(item8);

        Item item9 = new Item();
        item9.setTitle("Skateboard");
        item9.setDescription("Ein Skateboard, gelegentlich verdeutscht auch Rollbrett genannt, ist ein Brett (Deck) mit zwei Achsen (Trucks) und vier Rollen (Wheels), auf welchem man sich stehend durch Abstoßen vom Untergrund mit einem Bein (Pushen) oder mittels einer besonderen Technik des abwechselnden Drucks beider Füße zu den Außenseiten des Skateboards (diagonal bis quer zur Fahrtrichtung) durch geschickte Gewichtsverlagerung (Pumpen) fortbewegen kann.");
        item9.setZipCode(6591);
        item9.setCategory(Category.SPORT);
        item9.setCondition(Condition.USED);
        item9.setOwner(owner4);
        itemRepository.save(item9);

        Item item10 = new Item();
        item10.setTitle("BMW M3");
        item10.setDescription("extrem lautes M3, perfekt um vor ZHAW laut machen");
        item10.setZipCode(9014);
        item10.setCategory(Category.VEHICLE);
        item10.setCondition(Condition.USED);
        item10.setOwner(owner3);
        itemRepository.save(item10);
    }


    private void createPictureData(Item item1, Item item2, Item item3, Item item4, Item item5, Item item6, Item item7, Item item8, Item item9, Item item10){

        // item 1
        Picture picture1 = new Picture();
        picture1.setName("Steuerrechnung");
        picture1.setUrl("https://www.stadt-zuerich.ch/content/fd/de/index/steuern/natuerliche-personen/steuern-bezahlen/qr-steuerrechnung/_jcr_content/mainparsys/graphic/image.1752.jpg/1633779829102.jpg");
        picture1.setItem(item1);
        pictureRepository.save(picture1);

        // item 2
        Picture picture2 = new Picture();
        picture2.setName("Notebook");
        picture2.setUrl("https://bilder.pcwelt.de/4307875_620x310_r.webp");
        picture2.setItem(item2);
        pictureRepository.save(picture2);

        // item 3
        Picture picture3 = new Picture();
        picture3.setName("Velo");
        picture3.setUrl("https://frontend.syunify.de/syimagesrv/show?imgroot=GPRAG&ColorSku=110491&imgSize=620&qlty=3&RecRef=60f6c0bc977f460a39b184c8");
        picture3.setItem(item3);
        pictureRepository.save(picture3);

        // item 4
        Picture picture4 = new Picture();
        picture4.setName("Rucksack");
        picture4.setUrl("https://cdn.shopify.com/s/files/1/0250/8090/products/Rolltop_Rucksack-Backpacks-13160-01_Black_930x1395_crop_center.jpg?v=1652789731");
        picture4.setItem(item4);
        pictureRepository.save(picture4);

        // item 5
        Picture picture5 = new Picture();
        picture5.setName("Smartphone");
        picture5.setUrl("https://www.inside-digital.de/img/smartphone-kaputt-1200x900.jpg");
        picture5.setItem(item5);
        pictureRepository.save(picture5);

        // item 6
        Picture picture6 = new Picture();
        picture6.setName("Kinderwagen");
        picture6.setUrl("https://baby-lucien.de/media/catalog/product/cache/fd44668e021234ab2030210c7e541d99/s/t/stylo_class_sp261.jpg");
        picture6.setItem(item6);
        pictureRepository.save(picture6);

        // item 7
        Picture picture7 = new Picture();
        picture7.setName("Geburtstagskuchen");
        picture7.setUrl("https://media-cdn.tripadvisor.com/media/photo-s/13/90/db/da/nusstorte-nach-halfte.jpg");
        picture7.setItem(item7);
        pictureRepository.save(picture7);

        // item 8
        Picture picture8 = new Picture();
        picture8.setName("Schubkarre");
        picture8.setUrl("https://www.spielwarenzauber.ch/78146-large_default/schubkarre-metall.jpg");
        picture8.setItem(item8);
        pictureRepository.save(picture8);

        // item 9
        Picture picture9 = new Picture();
        picture9.setName("Skateboard");
        picture9.setUrl("https://cdn.skatedeluxe.com/thumb/jCYj7mCYE5BNFY9lQ-SCRuLyMQ4=/fit-in/600x700/filters:fill(white):brightness(-4)/product/155392-0-RIPNDIP-LordNermal85.jpg");
        picture9.setItem(item9);
        pictureRepository.save(picture9);

        // item 10
        Picture picture10 = new Picture();
        picture10.setName("BMW M3");
        picture10.setUrl("https://www.tuningblog.eu/wp-content/uploads/2021/03/BMW-M3-Touring-G81-Competition-Widebody-Tuning-1.jpg");
        picture10.setItem(item10);
        pictureRepository.save(picture10);

    }
}

