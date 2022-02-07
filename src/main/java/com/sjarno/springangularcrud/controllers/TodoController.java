package com.sjarno.springangularcrud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.sjarno.springangularcrud.models.Todo;
import com.sjarno.springangularcrud.repository.TodoRepository;
import com.sjarno.springangularcrud.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;    

    @Autowired
    private TodoService todoService;

    /* Basically just for quick testing */
    @GetMapping("/greet")
    public Map<String, String> greeting() {
        Map<String, String> greet = new HashMap<>();
        greet.put("greet", "Hello, this is a greeting from the server!");
        return greet;
    }

    @PostMapping("/add-todo")
    public ResponseEntity<?> addTodo(@RequestBody Todo todo) {
        try {
            Todo createdTodo = this.todoService.createTodo(todo);
            return new ResponseEntity<Todo>(createdTodo, HttpStatus.CREATED);
        } catch (Exception e) {
           return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        
    }

    @GetMapping("/todos")
    public List<Todo> allTodos() {
        return this.todoService.getAllTodos();
    }
    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        return this.todoRepository.findById(id).get();
    }
    @Transactional
    @PutMapping("/update/{id}")
    public Todo updateTodoByid(@PathVariable Long id, @RequestBody Todo todo) {
        Todo foundTodo = this.todoRepository.findById(id).get();
        foundTodo.setTitle(todo.getTitle());
        foundTodo.setContent(todo.getContent());
        return foundTodo;
    }

    @DeleteMapping("/delete/{id}")
    public Todo deleteTodoByid(@PathVariable Long id) {
        Todo todoTodDelete = this.todoRepository.findById(id).get();
        this.todoRepository.delete(todoTodDelete);

        return todoTodDelete;
    }
    
}
