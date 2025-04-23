package com.studentmanagement.studentCRUD.controller;


import com.studentmanagement.studentCRUD.exception.StudentNotFoundException;
import com.studentmanagement.studentCRUD.model.Student;
import com.studentmanagement.studentCRUD.repository.StudentRepository;
import com.studentmanagement.studentCRUD.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        return students;

    }

    @PostMapping("/input")
    public ResponseEntity<String> createStudents(@RequestBody Student student){
        studentService.saveInput(student);
        return ResponseEntity.ok("Details saved successfully.");

    }

    @GetMapping("/{id}")
    public Student getAllStudentById(@PathVariable Long id){
        Student student = studentRepository.findById(id).get();
        return student;

    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException("Student not found with the " + id));

        existingStudent.setName(student.getName());
        existingStudent.setPercentage(student.getPercentage());
        existingStudent.setBranch(student.getBranch());

        studentRepository.save(existingStudent);

        return ResponseEntity.ok(existingStudent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()){
            throw new StudentNotFoundException("Student with the " + id + " does not exists.");
        }

        studentRepository.deleteById(id);
        return ResponseEntity.ok("Student with the id " + id + " was deleted successfully");
    }

    //why not ResponseEntity<Student>
    // you are sending a simple message (a String) in the response body, not the actual Student entity.


}
