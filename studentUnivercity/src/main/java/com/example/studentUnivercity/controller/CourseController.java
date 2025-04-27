package com.example.studentUnivercity.controller;


import com.example.studentUnivercity.DTO.CourseRequestDTO;
import com.example.studentUnivercity.DTO.CourseResponseDTO;
import com.example.studentUnivercity.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO courseRequestDTO){
        CourseResponseDTO createResponse = courseService.createCourse(courseRequestDTO);
        return ResponseEntity.ok(createResponse);
    }
}
