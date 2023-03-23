package com.example.todolistmvc.mapper;

import com.example.todolistmvc.core.dto.ItemDto;
import com.example.todolistmvc.core.entity.ItemEntity;
import com.example.todolistmvc.web.dto.ItemRequest;
import com.example.todolistmvc.web.dto.ItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ItemMapper {

    ItemResponse itemDtoToResponse(ItemDto itemDto);

    ItemDto itemRequestToDto(ItemRequest itemRequest);

    @Mapping(source = "itemName", target = "name")
    @Mapping(source = "itemDescription", target = "description")
//    @Mapping(source = "listUuid", ignore = true)
    ItemEntity itemDtoToEntity(ItemDto itemDto);

    @Mapping(source = "name", target = "itemName")
    @Mapping(source = "description", target = "itemDescription")
    ItemDto itemEntityToDto(ItemEntity itemEntity);
}
