package com.example.todolistmvc.core.service;

import com.example.todolistmvc.core.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemService {

    public List<ItemDto> getItems() {

        ItemDto itemDto = getItemDto();

        return List.of(itemDto);
    }

    private ItemDto getItemDto() {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(UUID.randomUUID());
        itemDto.setItemName("Item name");
        itemDto.setItemDescription("Item description");
        return itemDto;
    }

    public ItemDto getItemById(UUID id) {
        ItemDto itemDto = getItemDto();

        return itemDto;
    }

    public ItemDto createItem(ItemDto itemDto) {


        return itemDto;
    }

    public ItemDto updateItem(ItemDto itemDto) {

        return itemDto;
    }

    public void deleteItemById(UUID id) {

        //delete item from DB
    }
}
