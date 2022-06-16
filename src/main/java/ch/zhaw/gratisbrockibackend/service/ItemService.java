package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.domain.Item;
import ch.zhaw.gratisbrockibackend.dto.ItemCreationDto;
import ch.zhaw.gratisbrockibackend.dto.ItemDto;
import ch.zhaw.gratisbrockibackend.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDto> getItems() {
        return itemRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public ItemDto getItem(Long id) {
        return convertEntityToDto(itemRepository.getById(id));
    }

    public ResponseEntity<ItemCreationDto> createNewItem(ItemCreationDto itemCreationDto) {

        if (itemCreationDto.getOwner() == null) {
            return ResponseEntity.badRequest().build();
        }
        Item item = new Item();
        item.setTitle(itemCreationDto.getTitle()); // TODO: introduce mapper (e.g. MapStruct) to handle this conversion
        item.setDescription(itemCreationDto.getDescription());
        itemRepository.save(item);
        return ResponseEntity.ok(itemCreationDto);
    }

    private ItemDto convertEntityToDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setTitle(item.getTitle());
        itemDto.setDescription(item.getDescription());
        return itemDto;
    }

}