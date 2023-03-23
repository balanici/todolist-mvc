package com.example.todolistmvc.mapper;

import com.example.todolistmvc.core.dto.ListDto;
import com.example.todolistmvc.core.entity.ListEntity;
import com.example.todolistmvc.web.dto.ListRequest;
import com.example.todolistmvc.web.dto.ListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ListMapper {

    ListResponse listDtoToResponse(ListDto listDto);

    ListDto listRequestToDto(ListRequest listRequest);

    //Todo: Find how to use inverse mapper
    @Mapping(source = "name", target = "listName")
    @Mapping(source = "description", target = "listDescription")
    ListDto listEntityToDto(ListEntity listEntity);

    @Mapping(source = "listName", target = "name")
    @Mapping(source = "listDescription", target = "description")
    ListEntity listDtoToEntity(ListDto listDto);

}
