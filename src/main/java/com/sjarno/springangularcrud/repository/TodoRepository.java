package com.sjarno.springangularcrud.repository;

import com.sjarno.springangularcrud.models.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>{
    
}
