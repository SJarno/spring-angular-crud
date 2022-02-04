package com.sjarno.springangularcrud.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjarno.springangularcrud.models.Todo;
import com.sjarno.springangularcrud.repository.TodoRepository;

import static org.hamcrest.Matchers.*;


import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TodoRepository todoRepository;

    private Todo todoFirst;
    private Todo todoSecond;
    private Todo todoThird;
    private Todo todoWithNullVals;
    private Todo todoWithTitleOnly;
    private Todo todoWithContentOnly;

    private List<Todo> todoList;

    @BeforeEach
    public void setUp() {
        this.todoFirst = new Todo("TitleOne", "ContentOne");
        this.todoSecond = new Todo("TitleTwo", "ContentTwo");
        this.todoThird = new Todo("TitleThree", "ContentThree");
        todoList = new ArrayList<>(Arrays.asList(
            todoFirst, todoSecond, todoThird));
        this.todoRepository.saveAll(todoList);

        this.todoWithNullVals = new Todo();
        this.todoWithTitleOnly = new Todo("TitleOnly", "");
        this.todoWithContentOnly = new Todo("", "ContentOnly");

    }
    @AfterEach
    private void tearDown() {
        this.todoRepository.deleteAll();
    }

    @Test
    void testGreeting() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/greet"))
                .andExpect(status().isOk()).andReturn();
        assertEquals("Heippa maailma!", result.getResponse().getContentAsString());
    }
    /* Initial size: */
    @Test
    void testInitialSize() {
        assertEquals(3, todoRepository.findAll().size());
    }
    @Test
    void testAddingNewTodo() throws Exception {
        
        Todo newTodo = new Todo("newTitle", "newContent");
        MvcResult result = addNewTodo(newTodo)
            .andExpect(status().isOk())
            .andReturn();
        String jsonContent = "{\"id\":4,\"title\":\"newTitle\",\"content\":\"newContent\",\"new\":false}";
        assertEquals(jsonContent, result.getResponse().getContentAsString());
        assertEquals(4, todoRepository.findAll().size());
    }
    /* Test errors here: */
    void addNewTodoWithWrongValues() {

    }
    

    @Test
    void testAllTodos() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/todos"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*].id", 
                containsInAnyOrder(1, 2, 3)))
            .andExpect(jsonPath("$[*].title", 
                containsInAnyOrder("TitleOne", "TitleTwo", "TitleThree")))
            .andExpect(jsonPath("$[*].content", 
                containsInAnyOrder("ContentOne", "ContentTwo", "ContentThree")))
            .andReturn();
        //if need to check whole string:
        //assertEquals("expected", result.getResponse().getContentAsString());
    }
    @Test
    void testGetTodId() throws Exception {
        MvcResult result = getTodoById(1, "TitleOne", "ContentOne")
            .andExpect(status().isOk()).andReturn();
        String content = "{\"id\":1,\"title\":\"TitleOne\",\"content\":\"ContentOne\",\"new\":false}";
        assertEquals(content, result.getResponse().getContentAsString());
    }
    @Test
    void canUpdateTodo() throws Exception{
        Todo updatedValues = new Todo("TitleUpdated", "ContentUpdated");
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.put("/api/update/2")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedValues)))
            .andExpect(status().isOk()).andReturn();
        String jsonContent = "{\"id\":2,\"title\":\"TitleUpdated\",\"content\":\"ContentUpdated\",\"new\":false}";
        assertEquals(jsonContent, result.getResponse().getContentAsString());
        assertEquals(3, this.todoRepository.findAll().size());
    }
    @Test
    void canDeleteTodoById() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/delete/{id}", 2))
            .andExpect(status().isOk()).andReturn();
            String jsonContent = "{\"id\":2,\"title\":\"TitleTwo\",\"content\":\"ContentTwo\",\"new\":false}";
        assertEquals(jsonContent, result.getResponse().getContentAsString());
        assertEquals(2, this.todoRepository.findAll().size());
    }

    private ResultActions addNewTodo(Todo todo) throws Exception {
        return this.mockMvc.perform(post(("/api/add-todo"))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(todo)));
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
