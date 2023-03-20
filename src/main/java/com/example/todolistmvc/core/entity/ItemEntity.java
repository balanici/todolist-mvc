package com.example.todolistmvc.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemEntity {

    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "list_entity_uuid",
            foreignKey = @ForeignKey(name = "fk_items__lists_uuid")
    )
    private ListEntity listEntity;

}
