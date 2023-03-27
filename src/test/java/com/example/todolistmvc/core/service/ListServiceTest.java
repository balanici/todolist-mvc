package com.example.todolistmvc.core.service;

import com.example.todolistmvc.core.entity.ListEntity;
import com.example.todolistmvc.core.repository.ListEntityRepository;
import com.example.todolistmvc.mapper.ListMapper;
import com.example.todolistmvc.utils.TestConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.todolistmvc.utils.TestConstants.LIST_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListServiceTest {

    @Mock
    private ListEntityRepository listEntityRepository;

    @Mock
    private ListMapper listMapper;

    @InjectMocks
    private ListService listService;

    @Test
    void getLists() {
        var listDto = TestConstants.listDto();
        var listEntity = TestConstants.listEntity();
        when(listEntityRepository.findAll()).thenReturn(List.of(listEntity));
        when(listMapper.listEntityToDto(listEntity)).thenReturn(listDto);

        var actual = listService.getLists();

        assertEquals(List.of(listDto), actual);
    }

    @Test
    void getListById() {
        var listDto = TestConstants.listDto();
        var listEntity = TestConstants.listEntity();
        when(listEntityRepository.findById(LIST_ID)).thenReturn(Optional.of(listEntity));
        when(listMapper.listEntityToDto(listEntity)).thenReturn(listDto);

        var actual = listService.getListById(LIST_ID);

        assertEquals(listDto, actual);
    }

    @Test
    void createList() {
        var listDto = TestConstants.listDto();
        var listEntity = TestConstants.listEntity();
        when(listMapper.listDtoToEntity(listDto)).thenReturn(listEntity);
        when(listEntityRepository.save(any(ListEntity.class))).thenReturn(listEntity);
        when(listMapper.listEntityToDto(listEntity)).thenReturn(listDto);

        var actual = listService.createList(listDto);

        assertEquals(listDto, actual);
    }

    @Test
    void updateList() {
        var listDto = TestConstants.listDto();
        var listEntity = TestConstants.listEntity();
        when(listEntityRepository.findById(LIST_ID)).thenReturn(Optional.of(listEntity));
        when(listEntityRepository.save(any(ListEntity.class))).thenReturn(listEntity);
        when(listMapper.listEntityToDto(listEntity)).thenReturn(listDto);

        var actual = listService.updateList(LIST_ID, listDto);

        assertEquals(listDto, actual);
    }

    @Test
    void deleteListById() {
        listService.deleteListById(LIST_ID);
        verify(listEntityRepository).deleteById(LIST_ID);
    }
}
