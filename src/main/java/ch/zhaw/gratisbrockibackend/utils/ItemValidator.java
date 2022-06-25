package ch.zhaw.gratisbrockibackend.utils;

import ch.zhaw.gratisbrockibackend.domain.Item;
import ch.zhaw.gratisbrockibackend.domain.enums.Category;
import ch.zhaw.gratisbrockibackend.domain.enums.Condition;
import ch.zhaw.gratisbrockibackend.dto.ItemUpdateDto;
import ch.zhaw.gratisbrockibackend.exceptions.ItemException;
import ch.zhaw.gratisbrockibackend.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Setter
@Getter
@Component
public class ItemValidator { // TODO: add additional, more sophisticated plausibility checks (e.g. DTO correctly formed?)

    ItemRepository itemRepository;

    public void exists (Long id) throws ItemException {
        if (!itemRepository.existsById(id)){
            throw new ItemException("Item not found");
        }
    }

    public void plausibilityCheck (Item item) {

        String title = item.getTitle();
        String description = item.getDescription();
        int zipCode = item.getZipCode();
        Category category = item.getCategory();
        Condition condition = item.getCondition();

        commonPlausibilityCheck(title, description, zipCode, category, condition);
    }

    public void plausibilityCheck (ItemUpdateDto itemUpdateDto) {

        String title = itemUpdateDto.getTitle();
        String description = itemUpdateDto.getDescription();
        int zipCode = itemUpdateDto.getZipCode();
        Category category = itemUpdateDto.getCategory();
        Condition condition = itemUpdateDto.getCondition();

        commonPlausibilityCheck(title, description, zipCode, category, condition);
    }

    private void commonPlausibilityCheck(String title, String description, int zipCode, Category category, Condition condition) throws ItemException {
        if (title == null
                || title.length() < 3) {
            throw new ItemException("Invalid title — make sure minimum length is 3 characters");
        }
        if (description == null
                || description.length() <= 10) {
            throw new ItemException("Invalid description — make sure minimum length is 10 characters");
        }

        if (zipCode < 1000
                || zipCode > 9658) {
            throw new ItemException("Invalid zip code");
        }

        if (category == null) {
            throw new ItemException("Invalid category");
        }

        if (condition == null) {
            throw new ItemException("Invalid condition");
        }
    }
}