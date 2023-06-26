package com.example.todolistmvc.web.controller;

import com.example.todolistmvc.core.service.ItemService;
import com.example.todolistmvc.mapper.ItemMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.example.todolistmvc.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    public static final String API_TODO_ITEMS_URI = "/api/todo-items";
    public static final String API_PROTECTED_URI = "/api/protected"; //this to be added

    @MockBean
    private ItemService itemService;

    @MockBean
    private ItemMapper itemMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

//    @BeforeEach
//    void setup() {
//        this.mockMvc = MockMvcBuilders.standaloneSetup(new ItemController(itemService, itemMapper)).build();
//    }

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    void getItems() throws Exception {
        var itemDto = itemDto();
        var itemResponse = itemResponse();

        when(itemMapper.itemDtoToResponse(itemDto)).thenReturn(itemResponse);
        when(itemService.getItems())
                .thenReturn(List.of(itemDto));

        mockMvc.perform(get(API_TODO_ITEMS_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid", Matchers.is(ITEM_ID.toString())))
                .andExpect(jsonPath("$[0].itemName", Matchers.is(ITEM_NAME)))
                .andExpect(jsonPath("$[0].itemDescription", Matchers.is(ITEM_DESCRIPTION)));
    }

    @Test
    void createItem() throws Exception {
        var itemRequest = itemRequest();
        var jsonItemRequest = objectMapper.writeValueAsString(itemRequest);
        var itemDto = itemDto();
        var itemResponse = itemResponse();

        when(itemMapper.itemRequestToDto(itemRequest)).thenReturn(itemDto);
        when(itemService.createItem(any())).thenReturn(itemDto);
        when(itemMapper.itemDtoToResponse(itemDto)).thenReturn(itemResponse);

        mockMvc.perform(post(API_TODO_ITEMS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonItemRequest))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid", Matchers.is(ITEM_ID.toString())))
                .andExpect(jsonPath("$.itemName", Matchers.is(ITEM_NAME)))
                .andExpect(jsonPath("$.itemDescription", Matchers.is(ITEM_DESCRIPTION)));
    }

    @Test
    void getItemById() throws Exception {
        var itemDto = itemDto();
        var itemResponse = itemResponse();

        when(itemService.getItemById(ITEM_ID)).thenReturn(itemDto);
        when(itemMapper.itemDtoToResponse(itemDto)).thenReturn(itemResponse);

        mockMvc.perform(
                get(API_TODO_ITEMS_URI + "/" + ITEM_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", Matchers.is(ITEM_ID.toString())))
                .andExpect(jsonPath("$.itemName", Matchers.is(ITEM_NAME)))
                .andExpect(jsonPath("$.itemDescription", Matchers.is(ITEM_DESCRIPTION)));
    }

    @Test
    void updateItemById() throws Exception {
        var itemRequest = itemRequest();
        var jsonItemRequest = objectMapper.writeValueAsString(itemRequest);
        var itemDto = itemDto();
        var itemResponse = itemResponse();

//        when(itemMapper.itemRequestToDto(itemRequest)).thenReturn(itemDto);
        when(itemMapper.itemRequestToDto(any())).thenReturn(itemDto);
        when(itemService.updateItemById(ITEM_ID, itemDto)).thenReturn(itemDto);
//        when(itemService.updateItemById(any(), any())).thenReturn(itemDto);
        when(itemMapper.itemDtoToResponse(itemDto)).thenReturn(itemResponse);

        mockMvc.perform(
                        put(API_TODO_ITEMS_URI + "/" + ITEM_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonItemRequest)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", Matchers.is(ITEM_ID.toString())))
                .andExpect(jsonPath("$.itemName", Matchers.is(ITEM_NAME)))
                .andExpect(jsonPath("$.itemDescription", Matchers.is(ITEM_DESCRIPTION)));
    }

    @Test
    void deleteItemById() throws Exception {
        mockMvc.perform(
                delete(API_TODO_ITEMS_URI + "/" + ITEM_ID))
                .andExpect(status().isNoContent());
    }

    //this is to be added somehow
    @Test
    void testProtectedResource() throws Exception {
        String helloUri = "/hello";

        SecurityContext contextHolder = SecurityContextHolder.createEmptyContext();
        Authentication authentication = new TestingAuthenticationToken("username", "password", "ROLE_USER");
        contextHolder.setAuthentication(authentication);
        SecurityContextHolder.setContext(contextHolder);

        mockMvc.perform(get(API_PROTECTED_URI + helloUri))
            .andExpect(status().isOk());
    }
}
