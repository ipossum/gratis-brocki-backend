package ch.zhaw.gratisbrockibackend.controller;

import ch.zhaw.gratisbrockibackend.domain.Item;
import ch.zhaw.gratisbrockibackend.dto.ItemCreationDto;
import ch.zhaw.gratisbrockibackend.dto.ItemDto;
import ch.zhaw.gratisbrockibackend.dto.ItemUpdateDto;
import ch.zhaw.gratisbrockibackend.mapper.ItemMapper;
import ch.zhaw.gratisbrockibackend.service.ItemService;
import com.turkraft.springfilter.boot.Filter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("api/v1/items")
@RestController
public class ItemController {

    private final ItemService itemService;

    private final ItemMapper itemMapper;

    @GetMapping
    public Page<ItemDto> getItems(@Filter Specification<Item> spec, Pageable page) {
        return itemService.getItems(spec, page)
                .map(itemMapper::toItemDto);
    }

    @GetMapping("/{id}")
    public ItemDto getItem(@PathVariable("id") Long id) {
        ItemDto itemDto = itemMapper.toItemDto(itemService.getItem(id));
        return itemDto;
    }

    @PostMapping
    public ResponseEntity<ItemDto> createNewItem(@RequestBody ItemCreationDto itemCreationDto) {
        Item item = itemMapper.toItem(itemCreationDto);
        ItemDto itemDto = itemMapper.toItemDto(itemService.createNewItem(item));
        return ResponseEntity.ok(itemDto);
    }

    @PutMapping("/{id}")
    public ItemDto updateItem(@PathVariable("id") Long id, @RequestBody ItemUpdateDto itemUpdateDto) {
        ItemDto itemDto = itemMapper.toItemDto(itemService.updateItem(id, itemUpdateDto));
        return itemDto;
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
    }
}