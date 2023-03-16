package com.example.todolistmvc.core.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemDto {
    private UUID uuid;
    private String itemName;
    private String itemDescription;

    private UUID listUuid;
}
