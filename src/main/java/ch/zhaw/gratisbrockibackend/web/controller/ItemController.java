package ch.zhaw.gratisbrockibackend.web.controller;

import ch.zhaw.gratisbrockibackend.domain.Item;
import ch.zhaw.gratisbrockibackend.domain.dto.ItemDto;
import ch.zhaw.gratisbrockibackend.repository.ItemRepository;
import ch.zhaw.gratisbrockibackend.service.ItemService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/items")
@RestController
public class ItemController {

    private final ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public List<Item> getItems(){
        return itemService.getItems();
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable("id") Long id) {
        return itemService.getItem(id);
    }

    @PostMapping("/")
    public ResponseEntity<ItemDto> create(@RequestBody Item item) {
        if(item.getOwner()==null) {
            return ResponseEntity.badRequest().build();
        }
        itemRepository.save(item);
        ItemDto itemDto = new ItemDto();
        BeanUtils.copyProperties(item, itemDto);
        return ResponseEntity.ok(itemDto);
    }

}