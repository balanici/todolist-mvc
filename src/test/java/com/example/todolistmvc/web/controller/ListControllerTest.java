package com.example.todolistmvc.web.controller;

import com.example.todolistmvc.core.service.ListService;
import com.example.todolistmvc.mapper.ListMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.example.todolistmvc.utils.TestConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ListController.class)
class ListControllerTest {

    public static final String API_TODO_LISTS_URI = "/api/todo-lists";

    @MockBean
    private ListService listService;

    @MockBean
    private ListMapper listMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getLists() throws Exception {
        var listDto = listDto();
        var listResponse = listResponse();

        when(listMapper.listDtoToResponse(listDto)).thenReturn(listResponse);
        when(listService.getLists())
                .thenReturn(List.of(listDto));

        mockMvc.perform(get(API_TODO_LISTS_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid", Matchers.is(LIST_ID.toString())))
                .andExpect(jsonPath("$[0].listName", Matchers.is(LIST_NAME)))
                .andExpect(jsonPath("$[0].listDescription", Matchers.is(LIST_DESCRIPTION)));
    }

    @Test
    void createList() throws Exception {
        var listRequest = listRequest();
        var jsonListRequest = objectMapper.writeValueAsString(listRequest);
        var listDto = listDto();
        var listResponse = listResponse();

        when(listMapper.listRequestToDto(listRequest)).thenReturn(listDto);
        when(listService.createList(any())).thenReturn(listDto);
        when(listMapper.listDtoToResponse(listDto)).thenReturn(listResponse);

        mockMvc.perform(post(API_TODO_LISTS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonListRequest))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid", Matchers.is(LIST_ID.toString())))
                .andExpect(jsonPath("$.listName", Matchers.is(LIST_NAME)))
                .andExpect(jsonPath("$.listDescription", Matchers.is(LIST_DESCRIPTION)));
    }

    @Test
    void getListByUuid() throws Exception {
        var listDto = listDto();
        var listResponse = listResponse();

        when(listService.getListById(LIST_ID)).thenReturn(listDto);
        when(listMapper.listDtoToResponse(listDto)).thenReturn(listResponse);

        mockMvc.perform(
                        get(API_TODO_LISTS_URI + "/" + LIST_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", Matchers.is(LIST_ID.toString())))
                .andExpect(jsonPath("$.listName", Matchers.is(LIST_NAME)))
                .andExpect(jsonPath("$.listDescription", Matchers.is(LIST_DESCRIPTION)));
    }

    @Test
    void updateListByUuid() throws Exception {
        var listRequest = listRequest();
        var jsonListRequest = objectMapper.writeValueAsString(listRequest);
        var listDto = listDto();
        var listResponse = listResponse();

        when(listMapper.listRequestToDto(any())).thenReturn(listDto);
        when(listService.updateList(LIST_ID, listDto)).thenReturn(listDto);
        when(listMapper.listDtoToResponse(listDto)).thenReturn(listResponse);

        mockMvc.perform(
                        put(API_TODO_LISTS_URI + "/" + LIST_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonListRequest)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", Matchers.is(LIST_ID.toString())))
                .andExpect(jsonPath("$.listName", Matchers.is(LIST_NAME)))
                .andExpect(jsonPath("$.listDescription", Matchers.is(LIST_DESCRIPTION)));

    }

    @Test
    void deleteListByUuid() throws Exception {
        mockMvc.perform(
                        delete(API_TODO_LISTS_URI + "/" + LIST_ID))
                .andExpect(status().isNoContent());
    }
}
