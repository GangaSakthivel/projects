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

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        List<CourseResponseDTO> responseDTOs = courseService.getAllCourses();
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        CourseResponseDTO responseDTO = courseService.getCourseById(id);  // Call service to get the course by ID
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);  // Return the response DTO with HTTP status OK
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDTO courseRequestDTO) {
        CourseResponseDTO updatedCourse = courseService.updateCourse(id, courseRequestDTO);  // Call service to update course
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);  // Return the updated course with HTTP status OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);  // Call service to delete the course by ID
        return new ResponseEntity<>("Course with ID " + id + " deleted successfully", HttpStatus.OK);  // Return confirmation message
    }



}
