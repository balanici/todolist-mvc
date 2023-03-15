package com.example.todolistmvc.mapper;

import com.example.todolistmvc.core.dto.ListDto;
import com.example.todolistmvc.web.dto.ListResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ListMapper {

    ListResponse listDtoToResponse(ListDto listDto);

}
