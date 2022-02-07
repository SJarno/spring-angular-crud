package com.sjarno.springangularcrud.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sjarno.springangularcrud.models.Todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    private Todo todoWithValues;
    private Todo todoNull;
    private Todo todoWithTitle;
    private Todo todoWithContent;

    @BeforeEach
    void setUp() {
        this.todoWithValues = new Todo("title", "content");
        this.todoNull = new Todo();
        this.todoWithTitle = new Todo("", "");
        todoWithTitle.setTitle("TitleOnly");
        this.todoWithContent = new Todo("", "");
        todoWithContent.setContent("ContentOnly");
    }


    @Test
    void testCreateTodo() {
        assertEquals(0, this.todoService.getAllTodos().size());
        this.todoService.createTodo(todoWithValues);
        assertEquals(1, this.todoService.getAllTodos().size());
        assertEquals(this.todoWithValues, this.todoService.getAllTodos().get(0));
    }

    @Test
    void testWrongValuesThrowsError() {
        Exception nullValuesError = assertThrows(IllegalArgumentException.class, () -> {
            todoService.createTodo(todoNull);
        });
        assertEquals("Values cannot be null", nullValuesError.getMessage());

        Exception withoutContentError = assertThrows(IllegalArgumentException.class, () -> {
            todoService.createTodo(todoWithTitle);
        });
        assertEquals("Content cannot be empty", withoutContentError.getMessage());

        Exception withoutTitleError = assertThrows(IllegalArgumentException.class, () -> {
            todoService.createTodo(todoWithContent);
        });
        assertEquals("Title cannot be empty", withoutTitleError.getMessage());
    }
}
