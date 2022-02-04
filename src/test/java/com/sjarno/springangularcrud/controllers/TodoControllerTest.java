package com.sjarno.springangularcrud.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sjarno.springangularcrud.repository.TodoRepository;

import static org.hamcrest.Matchers.*;


import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void testGreeting() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/greet"))
                .andExpect(status().isOk()).andReturn();
        assertEquals("Heippa maailma!", result.getResponse().getContentAsString());
    }
    /* Initial size: */
    @Test
    void testInitialSize() {
        assertEquals(1, todoRepository.findAll().size());
    }

    @Test
    void testAllTodos() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/todos"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*].id", containsInAnyOrder(1)))
            .andExpect(jsonPath("$[*].title", containsInAnyOrder("Title")))
            .andExpect(jsonPath("$[*].content", containsInAnyOrder("Content")))
            .andReturn();
        //if need to check whole string:
        //assertEquals("expected", result.getResponse().getContentAsString());
    }
    @Test
    void testGetTodId() throws Exception {
        MvcResult result = getTodoById(1, "Title", "Content")
            .andExpect(status().isOk()).andReturn();
        String content = "{\"id\":1,\"title\":\"Title\",\"content\":\"Content\",\"new\":false}";
        assertEquals(content, result.getResponse().getContentAsString());
    }
    private ResultActions getTodoById(int id, String title, String content) throws Exception{
        return this.mockMvc.perform(get("/api/todos/"+id)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id", is(id)))
            .andExpect(jsonPath("$.title", is(title)))
            .andExpect(jsonPath("$.content", is(content)));
            //.andExpect(status());
            
            
    }

    
}
