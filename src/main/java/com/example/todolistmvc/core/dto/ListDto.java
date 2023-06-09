package com.example.todolistmvc.core.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ListDto {
    private UUID uuid;
    private String listName;
    private String listDescription;
}
