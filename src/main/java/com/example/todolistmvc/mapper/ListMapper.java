package com.example.todolistmvc.mapper;

import com.example.todolistmvc.core.dto.ListDto;
import com.example.todolistmvc.web.dto.ListRequest;
import com.example.todolistmvc.web.dto.ListResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ListMapper {

    ListResponse listDtoToResponse(ListDto listDto);

    ListDto listRequestToDto(ListRequest listRequest);

}
