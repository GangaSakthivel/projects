package com.example.securityproject;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {

    private Long id;
    private String name;
    private Long marks;
}
