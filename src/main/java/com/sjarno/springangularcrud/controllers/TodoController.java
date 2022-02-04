package com.sjarno.springangularcrud.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TodoController {

    @GetMapping("/greet")
    public String greeting() {
        return "Heippa maailma!";
    }
    
}
