package com.example.todolistmvc.core.service;

import com.example.todolistmvc.core.dto.ListDto;
import com.example.todolistmvc.core.entity.ListEntity;
import com.example.todolistmvc.core.repository.ListEntityRepository;
import com.example.todolistmvc.mapper.ListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListService {

    private final ListEntityRepository listEntityRepository;

    private final ListMapper listMapper;


    public List<ListDto> getLists() {
        return listEntityRepository.findAll()
                .stream()
                .map(listMapper::listEntityToDto)
                .toList();
    }

    public ListDto getListById(UUID uuid) {
        return listEntityRepository.findById(uuid)
                .map(listMapper::listEntityToDto)
                .orElseThrow(() -> new RuntimeException("List not found"));
    }

    public ListDto createList(ListDto listDto) {
        ListEntity listEntity = listEntityRepository.save(
                listMapper.listDtoToEntity(listDto)
        );

        return listMapper.listEntityToDto(listEntity);
    }

    public ListDto updateList(UUID uuid, ListDto listDto) {

        ListEntity listEntity = listEntityRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("List not found"));
        listEntity.setName(listDto.getListName());
        listEntity.setDescription(listEntity.getDescription());

        return listMapper.listEntityToDto(listEntityRepository.save(listEntity));
    }

    public void deleteListById(UUID uuid) {
        listEntityRepository.deleteById(uuid);
    }
}
