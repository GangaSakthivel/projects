package com.example.securityproject;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
public class StudentController {


    private List<Student> students = new ArrayList<>(List.of(
            new Student(1L, "ganga", 100L),
            new Student(2L,"vino", 95L)

    ));

    @GetMapping("/students")
    public List<Student> getStudent(){
        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;

    }



}
