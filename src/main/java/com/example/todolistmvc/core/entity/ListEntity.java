package com.example.todolistmvc.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "lists")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "listEntity")
    private List<ItemEntity> itemEntities = new ArrayList<>();
}
