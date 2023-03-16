package com.example.todolistmvc.mapper;

import com.example.todolistmvc.core.dto.ItemDto;
import com.example.todolistmvc.web.dto.ItemRequest;
import com.example.todolistmvc.web.dto.ItemResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ItemMapper {

    ItemResponse itemDtoToResponse(ItemDto itemDto);

    ItemDto itemRequestToDto(ItemRequest itemRequest);
}
