package com.example.todolistmvc.core.repository;

import com.example.todolistmvc.core.entity.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ListEntityRepository extends JpaRepository<ListEntity, UUID> {
}
