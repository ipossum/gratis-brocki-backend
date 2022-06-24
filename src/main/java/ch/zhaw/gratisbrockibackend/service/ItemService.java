package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.domain.Item;
import ch.zhaw.gratisbrockibackend.dto.ItemUpdateDto;
import ch.zhaw.gratisbrockibackend.mapper.ItemMapper;
import ch.zhaw.gratisbrockibackend.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@AllArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    public Page<Item> getItems (Specification<Item> spec, Pageable page){
        // TODO: add some validation (e.g. make sure not all items are returned, but only the first page as default!)
        return itemRepository.findAll(spec, page);
    }

    public Item getItem(Long id) {
        return itemRepository.findItemById(id);
    }

    public Item createNewItem(Item item) {
        try {
            // TODO: add some validation logic with itemValidator
            return itemRepository.save(item);
        } catch (HttpClientErrorException.BadRequest e) {
            e.printStackTrace();
            return null;
        }
    }

    public Item updateItem(Long id, ItemUpdateDto itemUpdateDto) {
        Item item = itemRepository.findItemById(id);
        // TODO: add some validation logic with itemValidator
        item.setTitle(itemUpdateDto.getTitle());
        item.setDescription(itemUpdateDto.getDescription());
        item.setZipCode(itemUpdateDto.getZipCode());
        item.setCategory(itemUpdateDto.getCategory());
        item.setCondition(itemUpdateDto.getCondition());
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        try {
            itemRepository.deleteById(id);
        } catch (HttpClientErrorException.BadRequest e) {
            e.printStackTrace();
        }
    }

}