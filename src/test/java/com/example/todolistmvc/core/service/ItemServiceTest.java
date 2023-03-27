package com.example.todolistmvc.core.service;

import com.example.todolistmvc.core.dto.ItemDto;
import com.example.todolistmvc.core.entity.ItemEntity;
import com.example.todolistmvc.core.repository.ItemEntityRepository;
import com.example.todolistmvc.mapper.ItemMapper;
import com.example.todolistmvc.mapper.ItemMapperImpl;
import com.example.todolistmvc.utils.TestConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static com.example.todolistmvc.utils.TestConstants.ITEM_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        ItemMapperImpl.class
})
class ItemServiceTest {

    @Mock
    private ItemEntityRepository itemEntityRepository;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private ItemService itemService;

    @Test
    void getItems() {
        var itemDto = TestConstants.itemDto();
        var itemEntity = TestConstants.itemEntity();
        var expectedItems = List.of(itemDto);

        when(itemEntityRepository.findAll()).thenReturn(List.of(itemEntity));
        when(itemMapper.itemEntityToDto(itemEntity)).thenReturn(itemDto);

        var actualItems = itemService.getItems();

        assertEquals(expectedItems, actualItems);
    }

    @Test
    void getItemById() {
        var itemDto = TestConstants.itemDto();
        var itemEntity = TestConstants.itemEntity();
        when(itemEntityRepository.findById(ITEM_ID)).thenReturn(Optional.of(itemEntity));
        when(itemMapper.itemEntityToDto(itemEntity)).thenReturn(itemDto);

        var actual = itemService.getItemById(ITEM_ID);

        assertEquals(itemDto, actual);
    }

    @Test
    void createItem() {
        var itemDto = TestConstants.itemDto();
        var itemEntity = TestConstants.itemEntity();
        when(itemEntityRepository.save(any(ItemEntity.class))).thenReturn(itemEntity);
        when(itemMapper.itemDtoToEntity(any(ItemDto.class))).thenReturn(itemEntity);
        when(itemMapper.itemEntityToDto(itemEntity)).thenReturn(itemDto);

        var actual = itemService.createItem(itemDto);

        assertEquals(itemDto, actual);
    }

    @Test
    void updateItemById() {
        var itemDto = TestConstants.itemDto();
        var itemEntity = TestConstants.itemEntity();
        when(itemEntityRepository.findById(ITEM_ID)).thenReturn(Optional.of(itemEntity));
        when(itemEntityRepository.save(any(ItemEntity.class))).thenReturn(itemEntity);
        when(itemMapper.itemEntityToDto(itemEntity)).thenReturn(itemDto);

        var actual = itemService.updateItemById(ITEM_ID, itemDto);

        assertEquals(itemDto, actual);
    }

    @Test
    void deleteItemById() {
        itemService.deleteItemById(ITEM_ID);
        verify(itemEntityRepository).deleteById(ITEM_ID);
    }

}
