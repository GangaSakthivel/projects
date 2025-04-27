package com.example.studentUnivercity.controller;

import com.example.studentUnivercity.DTO.StudentResponseDTO;
import com.example.studentUnivercity.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents(){
        List<StudentResponseDTO> responseDTOS = studentService.getAllStudents();
        return ResponseEntity.ok(responseDTOS);
    }
}
