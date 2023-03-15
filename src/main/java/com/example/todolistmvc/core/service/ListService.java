package com.example.todolistmvc.core.service;

import com.example.todolistmvc.core.dto.ListDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ListService {



    public List<ListDto> getLists() {
        ListDto listDto = ListDto.builder()
                .id(UUID.randomUUID())
                .listName("List name")
                .listDescription("List description")
                .build();

        return List.of(listDto);
    }

    public ListDto getListById(UUID id) {
        ListDto listDto = ListDto.builder()
                .id(UUID.randomUUID())
                .listName("List name")
                .listDescription("List description")
                .build();
        return listDto;
    }

    public ListDto createList(ListDto listDto) {


        return listDto;
    }

    public ListDto updateList(ListDto listDto) {

        return listDto;
    }

    public void deleteListById(UUID id) {

        //delete list from DB
    }
}
