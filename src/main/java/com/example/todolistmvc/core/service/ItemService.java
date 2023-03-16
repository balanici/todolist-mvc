package com.example.todolistmvc.core.service;

import com.example.todolistmvc.core.dto.ItemDto;
import lombok.AllArgsConstructor;
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
        itemDto.setUuid(UUID.randomUUID());
        itemDto.setItemName("Item name");
        itemDto.setItemDescription("Item description");
        return itemDto;
    }

    public ItemDto getItemByUuid(UUID uuid) {
        ItemDto itemDto = getItemDto();

        return itemDto;
    }

    public ItemDto createItem(ItemDto itemDto) {


        return itemDto;
    }

    public ItemDto updateItemByUuid(UUID uuid, ItemDto itemDto) {

        return itemDto;
    }

    public void deleteItemByUuid(UUID uuid) {

        //delete item from DB
    }
}
