package com.example.UserAuthenticationSpring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class HelloController {

    @GetMapping("/get_user")
    public String helloUser(){
        return "Hello user";
    }
}
