package com.example.todolistmvc.web.controller;

import com.example.todolistmvc.core.service.ListService;
import com.example.todolistmvc.mapper.ListMapper;
import com.example.todolistmvc.web.dto.ListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
