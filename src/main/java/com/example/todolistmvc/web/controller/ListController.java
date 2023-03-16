package com.example.todolistmvc.web.controller;

import com.example.todolistmvc.core.dto.ListDto;
import com.example.todolistmvc.core.service.ListService;
import com.example.todolistmvc.mapper.ListMapper;
import com.example.todolistmvc.web.dto.ListRequest;
import com.example.todolistmvc.web.dto.ListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todo-lists")
@RequiredArgsConstructor
public class ListController {

    private final ListService listService;

    private final ListMapper listMapper;

    @GetMapping
    public ResponseEntity<List<ListResponse>> getLists() {
        List<ListResponse> listResponses = listService.getLists()
                .stream()
                .map(listMapper::listDtoToResponse)
                .toList();
        return ResponseEntity.ok(listResponses);
    }

    @PostMapping
    public ResponseEntity<ListResponse> createList(@RequestBody ListRequest listRequest) {
        ListDto listDto = listMapper.listRequestToDto(listRequest);
        ListResponse listResponse = listMapper.listDtoToResponse(listService.createList(listDto));
        return new ResponseEntity<>(listResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{listUuid}")
    public ResponseEntity<ListResponse> getListByUuid(@PathVariable UUID listUuid) {
        ListDto listByUuid = listService.getListById(listUuid);

        return ResponseEntity.ok(listMapper.listDtoToResponse(listByUuid));
    }

    @PutMapping("/{listUuid}")
    public ResponseEntity<ListResponse> updateListByUuid(@PathVariable UUID listUuid, @RequestBody ListRequest listRequest) {
        ListDto listDto = listMapper.listRequestToDto(listRequest);
        ListResponse listResponse = listMapper.listDtoToResponse(listService.updateList(listUuid, listDto));
        return new ResponseEntity<>(listResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{listUuid}")
    public ResponseEntity<Void> deleteListByUuid(@PathVariable UUID listUuid) {
        listService.deleteListById(listUuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
