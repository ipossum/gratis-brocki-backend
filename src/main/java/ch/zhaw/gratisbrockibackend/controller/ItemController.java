package ch.zhaw.gratisbrockibackend.controller;

import ch.zhaw.gratisbrockibackend.dto.ItemCreationDto;
import ch.zhaw.gratisbrockibackend.dto.ItemDto;
import ch.zhaw.gratisbrockibackend.repository.ItemRepository;
import ch.zhaw.gratisbrockibackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
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
    public List<ItemDto> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/{id}")
    public ItemDto getItem(@PathVariable("id") Long id) {
        return itemService.getItem(id);
    }

    @PostMapping("/")
    public ResponseEntity<ItemCreationDto> createNewItem(@RequestBody ItemCreationDto itemCreationDto) {
        return itemService.createNewItem(itemCreationDto);
    }
}