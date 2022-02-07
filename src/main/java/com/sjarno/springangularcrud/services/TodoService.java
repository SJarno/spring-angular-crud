package com.sjarno.springangularcrud.services;

import java.util.List;
import java.util.Optional;

import com.sjarno.springangularcrud.models.Todo;
import com.sjarno.springangularcrud.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodo(Todo todo) {
        if (validateTodo(todo));
        return this.todoRepository.save(todo);
    }
    private boolean validateTodo(Todo todo) {
        if (todo == null || todo.getTitle() == null || todo.getContent() == null ) {
            throw new NullPointerException("Values cannot be null");
        }
        if (todo.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (todo.getContent().isBlank()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
        return true;
    }

    public List<Todo> getAllTodos() {
        return this.todoRepository.findAll();
    }
    private Todo findTodoWithId(Long id) throws Exception {
        Optional<Todo> found = this.todoRepository.findById(id);
        if (found.isPresent()) {
            return found.get();
        }
        throw new Exception("Not found");
    }
    
}
