package com.sjarno.springangularcrud.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

    private Todo todoWithValuesNotInDB;

    @BeforeEach
    void setUp() {
        this.todoWithValues = new Todo("title", "content");
        this.todoNull = new Todo();
        this.todoWithTitle = new Todo("", "");
        todoWithTitle.setTitle("TitleOnly");
        this.todoWithContent = new Todo("", "");
        todoWithContent.setContent("ContentOnly");
        this.todoWithValuesNotInDB = new Todo("title", "content");
    }


    @Test
    void testCreateTodo() {
        assertEquals(0, this.todoService.getAllTodos().size());
        this.todoService.createTodo(todoWithValues);
        assertEquals(1, this.todoService.getAllTodos().size());
        assertEquals(this.todoWithValues, this.todoService.getAllTodos().get(0));
        assertNotEquals(this.todoNull, this.todoService.getAllTodos().get(0));
    }

    @Test
    void testWrongValuesThrowsErrorWhenCreatingTodo() {
        Exception nullObjectError = assertThrows(NullPointerException.class, () -> {
            todoService.createTodo(null);
        });
        assertEquals("Values cannot be null", nullObjectError.getMessage());
        

        Exception nullValuesError = assertThrows(NullPointerException.class, () -> {
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

    @Test
    void testGetTodoById() throws Exception {
        this.todoService.createTodo(todoWithValues);
        Todo foundTodo = this.todoService.findTodoWithId(1L);
        assertEquals(todoWithValues, foundTodo);
    }
    @Test
    void testGetTodoWithWronIdThrowsError() {
        Exception wrongIdError = assertThrows(Exception.class, () -> {
            this.todoService.findTodoWithId(2L);
        });
        assertEquals("Not found", wrongIdError.getMessage());
        
    }
    @Test
    void testUpdateWithProperValues() throws Exception {
        this.todoService.createTodo(todoWithValues);
        Todo updatedVals = new Todo("TitleUpdated", "ContentUpdated");
        this.todoService.updateTodo(updatedVals, 1L);
        assertEquals(1, this.todoService.getAllTodos().size());
        assertEquals(updatedVals, this.todoService.findTodoWithId(1L));
        assertNotEquals(this.todoWithValues, this.todoService.findTodoWithId(1L));
    }
    @Test
    void testWrongUpdateValuesThrowsError() {
        this.todoService.createTodo(todoWithValues);
        
        Exception nullObjectError = assertThrows(NullPointerException.class, () -> {
            this.todoService.updateTodo(null, 1L);
        });
        assertEquals("Values cannot be null", nullObjectError.getMessage());

        Exception nullValuesError = assertThrows(NullPointerException.class, () -> {
            this.todoService.updateTodo(todoNull, 1L);
        });
        assertEquals("Values cannot be null", nullValuesError.getMessage());

        Exception withoutContentError = assertThrows(IllegalArgumentException.class, () -> {
            this.todoService.updateTodo(todoWithTitle, 1L);
        });
        assertEquals("Content cannot be empty", withoutContentError.getMessage());

        Exception withoutTitleError = assertThrows(IllegalArgumentException.class, () -> {
            this.todoService.updateTodo(todoWithContent, 1L);
        });
        assertEquals("Title cannot be empty", withoutTitleError.getMessage());

        Exception withWrongIdError = assertThrows(Exception.class, () -> {
            this.todoService.updateTodo(todoWithContent, 2L);
        });
        assertEquals("Not found",withWrongIdError.getMessage());

    }
    @Test
    void canDeleteTodoById() throws Exception {
        this.todoService.createTodo(todoWithValues);
        Todo deletedTodo = this.todoService.deleteTodoById(1L);
        assertEquals(0, this.todoService.getAllTodos().size());
        assertEquals(todoWithValues, deletedTodo);

        //
        assertNotEquals(todoWithValuesNotInDB.getId(), deletedTodo.getId());
    }
    @Test
    void deletingWithNonExistingIdThrowsError() {
        this.todoService.createTodo(todoWithValues);
        Exception errorWithWrongId = assertThrows(Exception.class, () -> {
            this.todoService.deleteTodoById(2L);
        });
        assertEquals("Not found", errorWithWrongId.getMessage());

        Exception errorWitNullInput = assertThrows(IllegalArgumentException.class, () -> {
            this.todoService.deleteTodoById(null);
        });
        assertEquals("Id cannot be null", errorWitNullInput.getMessage());
    }
    
}
