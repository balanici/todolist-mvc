package com.example.todolistmvc.utils;

import com.example.todolistmvc.core.dto.ItemDto;
import com.example.todolistmvc.core.dto.ListDto;
import com.example.todolistmvc.core.entity.ItemEntity;
import com.example.todolistmvc.core.entity.ListEntity;

import java.util.List;
import java.util.UUID;

public class TestConstants {

    public final static UUID ITEM_ID = UUID.randomUUID();
    public static final String ITEM_NAME = "Item Name";
    public static final String ITEM_DESCRIPTION = "Item Description";


    public final static UUID LIST_ID = UUID.randomUUID();
    public static final String LIST_NAME = "List Name";
    public static final String LIST_DESCRIPTION = "List Description";

    public static ItemDto itemDto() {
        return ItemDto.builder()
                .uuid(ITEM_ID)
                .itemName(ITEM_NAME)
                .itemDescription(ITEM_DESCRIPTION)
                .build();
    }

    public static ItemEntity itemEntity() {
        return new ItemEntity(
                ITEM_ID,
                ITEM_NAME,
                ITEM_DESCRIPTION,
                null);
    }

    public static ListDto listDto() {
        return ListDto.builder()
                .uuid(LIST_ID)
                .listName(LIST_NAME)
                .listDescription(LIST_DESCRIPTION)
                .build();
    }

    public static ListEntity listEntity() {
        return new ListEntity(
                LIST_ID,
                LIST_NAME,
                LIST_DESCRIPTION,
                List.of()
        );
    }
}
