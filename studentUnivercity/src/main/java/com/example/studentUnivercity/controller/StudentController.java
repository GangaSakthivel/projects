package com.example.studentUnivercity.controller;

import com.example.studentUnivercity.DTO.StudentDetailsDTO;
import com.example.studentUnivercity.DTO.StudentRequestDTO;
import com.example.studentUnivercity.DTO.StudentResponseDTO;
import com.example.studentUnivercity.model.Student;
import com.example.studentUnivercity.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/input")
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO requestDTO){
        StudentResponseDTO responseDTO = studentService.createStudent(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequestDTO requestDTO){
        StudentResponseDTO responseDTO = studentService.updateStudent(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StudentResponseDTO> deleteStudent(@PathVariable Long id){
        StudentResponseDTO responseDTO = studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/{name}")
    public ResponseEntity<StudentDetailsDTO> getStudentDetails(@PathVariable Long id, @PathVariable String name){
        StudentDetailsDTO detailsDTO = studentService.getStudentDetails(id, name);
        return ResponseEntity.ok(detailsDTO);

    }




}
