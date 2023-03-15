package com.example.todolistmvc.core.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class ItemEntity {
    private UUID id;
    private String itemName;
    private String itemDescription;

    private UUID listId;
}
