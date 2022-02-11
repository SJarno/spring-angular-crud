package com.sjarno.springangularcrud.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    
    @GetMapping("/user")
    public Principal login(Principal user) {
        /* System.out.println();
        System.out.println(user);
        System.out.println(); */
        return user;
    }
}
