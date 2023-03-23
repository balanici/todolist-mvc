package com.example.todolistmvc.core.repository;

import com.example.todolistmvc.core.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemEntityRepository extends JpaRepository<ItemEntity,UUID> {
}
