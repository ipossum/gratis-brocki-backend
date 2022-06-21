package ch.zhaw.gratisbrockibackend.controller;

import ch.zhaw.gratisbrockibackend.dto.ItemCreationDto;
import ch.zhaw.gratisbrockibackend.dto.ItemDto;
import ch.zhaw.gratisbrockibackend.dto.ItemUpdateDto;
import ch.zhaw.gratisbrockibackend.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/items")
@RestController
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/{id}")
    public ItemDto getItem(@PathVariable("id") Long id) {
        return itemService.getItem(id);
    }

    @PostMapping
    public ResponseEntity<ItemDto> createNewItem(@RequestBody ItemCreationDto itemCreationDto) {
        return itemService.createNewItem(itemCreationDto);
    }
    @PutMapping("/{id}")
    public ItemDto updateItem(@PathVariable("id") Long id, @RequestBody ItemUpdateDto itemUpdateDto) {
        return itemService.updateItem(id, itemUpdateDto);
    }
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
    }
}