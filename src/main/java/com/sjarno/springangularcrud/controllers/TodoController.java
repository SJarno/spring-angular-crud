package com.sjarno.springangularcrud.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.sjarno.springangularcrud.models.Todo;
import com.sjarno.springangularcrud.models.UserAccount;
import com.sjarno.springangularcrud.repository.TodoRepository;
import com.sjarno.springangularcrud.repository.UserAccountRepository;
import com.sjarno.springangularcrud.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserAccountRepository userAccountRepository;

    @Autowired
    private TodoService todoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public Todo getTodoById(@PathVariable Long id) throws Exception {
        return this.todoService.findTodoWithId(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTodoByid(@PathVariable Long id, @RequestBody Todo todo) {
        try {
            Todo updatedTodo = this.todoService.updateTodo(todo, id);
            return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTodoByid(@PathVariable Long id) {
        try {
            Todo deletedTodo = this.todoService.deleteTodoById(id);
            return new ResponseEntity<Todo>(deletedTodo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
    /* UserAccount userAccount = new UserAccount(username, password, roles); */
    @PostConstruct
    public void init() {
        UserAccount userAccount = new UserAccount(
            "user", 
            passwordEncoder.encode("pass"), 
            new ArrayList<>(Arrays.asList("ROLE_USER")));
        userAccountRepository.deleteAll();
        userAccountRepository.save(userAccount);
        
    }

}
