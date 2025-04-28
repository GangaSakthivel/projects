package com.example.studentUnivercity.controller;


import com.example.studentUnivercity.DTO.CourseRequestDTO;
import com.example.studentUnivercity.DTO.CourseResponseDTO;
import com.example.studentUnivercity.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/input")
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO courseRequestDTO){
        CourseResponseDTO createResponse = courseService.createCourse(courseRequestDTO);
        return ResponseEntity.ok(createResponse);
    }

//    @GetMapping
//    public ResponseEntity<List<CourseResponseDTO>> getAllCourses(){
//        CourseResponseDTO responseDTO = courseService.getAllCourses();
//        //return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//
//    }
}
