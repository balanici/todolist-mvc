package com.example.todolistmvc.core.service;

import com.example.todolistmvc.core.dto.ItemDto;
import com.example.todolistmvc.core.entity.ItemEntity;
import com.example.todolistmvc.core.repository.ItemEntityRepository;
import com.example.todolistmvc.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemEntityRepository itemEntityRepository;

    private final ItemMapper itemMapper;

    public List<ItemDto> getItems() {
        return itemEntityRepository.findAll()
                .stream()
                .map(itemMapper::itemEntityToDto)
                .toList();
    }

    public ItemDto getItemById(UUID uuid) {
        return itemMapper.itemEntityToDto(
                itemEntityRepository.findById(uuid)
                        .orElseThrow(() -> new RuntimeException("Item not found"))
        );
    }

    public ItemDto createItem(ItemDto itemDto) {
        ItemEntity itemEntity = itemEntityRepository.save(
                itemMapper.itemDtoToEntity(itemDto)
        );

        return itemMapper.itemEntityToDto(itemEntity);
    }

    public ItemDto updateItemById(UUID uuid, ItemDto itemDto) {

        ItemEntity itemEntity = itemEntityRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        itemEntity.setName(itemDto.getItemName());
        itemEntity.setDescription(itemDto.getItemDescription());
        itemEntityRepository.save(itemEntity);

        return itemMapper.itemEntityToDto(itemEntity);
    }

    public void deleteItemById(UUID uuid) {
        itemEntityRepository.deleteById(uuid);
    }
}
