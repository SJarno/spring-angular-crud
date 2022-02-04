package com.sjarno.springangularcrud.controllers;

import java.util.List;

import javax.annotation.PostConstruct;

import com.sjarno.springangularcrud.models.Todo;
import com.sjarno.springangularcrud.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    /* Basically just for quick testing */
    @GetMapping("/greet")
    public String greeting() {
        return "Heippa maailma!";
    }
    @GetMapping("/todos")
    public List<Todo> allTodos() {
        return this.todoRepository.findAll();
    }
    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        return this.todoRepository.findById(id).get();
    }

    @PostConstruct
    private void setUp() {
        this.todoRepository.deleteAll();
        this.todoRepository.save(new Todo("Title", "Content"));
    }
    
}
