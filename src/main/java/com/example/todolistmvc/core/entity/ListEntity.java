package com.example.todolistmvc.core.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class ListEntity {
    private UUID id;
    private String listName;
    private String listDescription;
}
