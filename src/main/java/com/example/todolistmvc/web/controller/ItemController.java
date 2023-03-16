package com.example.todolistmvc.web.controller;

import com.example.todolistmvc.core.dto.ItemDto;
import com.example.todolistmvc.core.service.ItemService;
import com.example.todolistmvc.mapper.ItemMapper;
import com.example.todolistmvc.web.dto.ItemRequest;
import com.example.todolistmvc.web.dto.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todo-items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    private final ItemMapper itemMapper;

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getItems() {
        List<ItemResponse> itemResponses = itemService.getItems().stream()
                .map(itemMapper::itemDtoToResponse)
                .toList();

        return ResponseEntity.ok(itemResponses);
    }

    @PostMapping
    public ResponseEntity<ItemResponse> createItem(@RequestBody ItemRequest itemRequest) {
        ItemDto itemDto = itemMapper.itemRequestToDto(itemRequest);
        ItemResponse itemResponse = itemMapper.itemDtoToResponse(itemService.createItem(itemDto));

        return new ResponseEntity<>(itemResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{itemUuid}")
    public ResponseEntity<ItemResponse> getIteByUuid(@PathVariable UUID itemUuid) {
        ItemDto itemDto = itemService.getItemByUuid(itemUuid);
        ItemResponse itemResponse = itemMapper.itemDtoToResponse(itemDto);

        return ResponseEntity.ok(itemResponse);
    }

    @PutMapping("/{itemUuid}")
    public ResponseEntity<ItemResponse> updateItemByUuid(@PathVariable UUID itemUuid,
                                                         @RequestBody ItemRequest itemRequest) {
        ItemDto itemDto = itemMapper.itemRequestToDto(itemRequest);
        ItemResponse itemResponse = itemMapper.itemDtoToResponse(itemService.updateItemByUuid(itemUuid, itemDto));

        return ResponseEntity.ok(itemResponse);
    }

    @DeleteMapping("/{itemUuid}")
    public ResponseEntity<Void> deleteItemByUuid(@PathVariable UUID itemUuid) {

        itemService.deleteItemByUuid(itemUuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
