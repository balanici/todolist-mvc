package com.example.todolistmvc.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class ListResponse {
    private UUID uuid;
    private String listName;
    private String listDescription;
}
