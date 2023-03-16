package com.example.todolistmvc.web.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ListRequest {
    private UUID uuid;
    private String listName;
    private String listDescription;
}
