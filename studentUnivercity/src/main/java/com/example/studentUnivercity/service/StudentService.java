package com.example.studentUnivercity.service;

import com.example.studentUnivercity.DTO.StudentResponseDTO;
import com.example.studentUnivercity.DTO.UniversityResponseDTO;
import com.example.studentUnivercity.model.Student;
import com.example.studentUnivercity.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        List<StudentResponseDTO> responseDTOS = students.stream()
                .map(student -> new StudentResponseDTO(
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getPhoneNumber(),
                        student.getEnrollmentDate(),
                        student.getGender()
                ))
                .collect(Collectors.toList());

        return responseDTOS;

    }
}
