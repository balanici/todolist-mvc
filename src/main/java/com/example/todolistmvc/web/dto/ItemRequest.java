package com.example.todolistmvc.web.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ItemRequest {
    private UUID uuid;
    private String itemName;
    private String itemDescription;

    private UUID listUuid;
}
