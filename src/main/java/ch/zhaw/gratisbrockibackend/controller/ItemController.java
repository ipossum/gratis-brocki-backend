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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("api/v1/items")
@RestController
public class ItemController {

    private final ItemService itemService;

    private final ItemMapper itemMapper;

    @PostMapping
    public ItemDto createNewItem(@RequestBody ItemCreationDto itemCreationDto) {
        Item item = itemMapper.toItem(itemCreationDto);
        return itemMapper.toItemDto(itemService.createNewItem(item));
    }

    @GetMapping
    public Page<ItemDto> getItems(@Filter Specification<Item> spec, Pageable page) {
        return itemService.getItems(spec, page)
                .map(itemMapper::toItemDto);
    }

    @GetMapping("/all")
    public List<ItemDto> getAllItems(@RequestParam(required = false) String search) {
        return itemService.getAllItems()
                .stream()
                .map(itemMapper::toItemDto)
                .toList();

    @GetMapping("/{id}")
    public ItemDto getItem(@PathVariable("id") Long id) {
        return itemMapper.toItemDto(itemService.getItem(id));
    }

    @PutMapping("/{id}")
    public ItemDto updateItem(@PathVariable("id") Long id, @RequestBody ItemUpdateDto itemUpdateDto) {
        return itemMapper.toItemDto(itemService.updateItem(id, itemUpdateDto));
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
    }
}