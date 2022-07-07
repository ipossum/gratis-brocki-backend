package ch.zhaw.gratisbrockibackend.repository;

import ch.zhaw.gratisbrockibackend.domain.Item;
import ch.zhaw.gratisbrockibackend.domain.enums.Category;
import ch.zhaw.gratisbrockibackend.domain.enums.Condition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemRepositoryTests {

    @Autowired
    private ItemRepository itemRepository;

    public Item createItem() {
        Item item = new Item();
        item.setTitle("testItem");
        item.setDescription("description for testing");
        item.setZipCode(9000);
        item.setCategory(Category.SPORT);
        item.setCondition(Condition.USED);
        item.setOwner(null);
        return item;
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveItemTest() {
        Item item = createItem();
        itemRepository.save(item);
        Assertions.assertThat(item.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getItemTest() {
        Item item = itemRepository.findItemById(1L);
        Assertions.assertThat(item.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfItemsTest(){
        List<Item> items = itemRepository.findAll();
        Assertions.assertThat(items.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updateItemTest() {
        Item updatedItem = itemRepository.findItemById(1L);
        Assertions.assertThat(updatedItem.getTitle()).isEqualTo("testItem");
        updatedItem.setTitle("updatedItem");
        itemRepository.save(updatedItem);
        Assertions.assertThat(updatedItem.getTitle()).isEqualTo("updatedItem");
    }

    @Test
    @Order(5)
    public void deleteItemTest() {
        itemRepository.deleteById(1L);
        Assertions.assertThat(itemRepository.findItemById(1L)).isNull();
    }

}
