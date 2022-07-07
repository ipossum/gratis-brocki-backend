package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.domain.Item;
import ch.zhaw.gratisbrockibackend.dto.ItemUpdateDto;
import ch.zhaw.gratisbrockibackend.repository.ItemRepository;
import ch.zhaw.gratisbrockibackend.utils.ItemValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemValidator itemValidator;

    public Page<Item> getItems (Specification<Item> spec, Pageable page){

        return itemRepository.findAll(spec, page);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item createNewItem(Item item) {
        itemValidator.plausibilityCheck(item);
        return itemRepository.save(item);
    }

    public Item getItem(Long id) {
        itemValidator.exists(id);
        return itemRepository.findItemById(id);
    }

    public Item updateItem(Long id, ItemUpdateDto itemUpdateDto) {
        itemValidator.plausibilityCheck(itemUpdateDto);
        Item item = itemRepository.findItemById(id);
        item.setTitle(itemUpdateDto.getTitle());
        item.setDescription(itemUpdateDto.getDescription());
        item.setZipCode(itemUpdateDto.getZipCode());
        item.setCategory(itemUpdateDto.getCategory());
        item.setCondition(itemUpdateDto.getCondition());
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemValidator.exists(id);
        itemRepository.deleteById(id);
    }

}