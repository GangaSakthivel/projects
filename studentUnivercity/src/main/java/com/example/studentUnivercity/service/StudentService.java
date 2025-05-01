package com.example.studentUnivercity.service;

import com.example.studentUnivercity.DTO.StudentRequestDTO;
import com.example.studentUnivercity.DTO.StudentResponseDTO;
import com.example.studentUnivercity.exceptions.ResourceNotFoundException;
import com.example.studentUnivercity.model.Student;
import com.example.studentUnivercity.model.University;
import com.example.studentUnivercity.repository.StudentRepository;
import com.example.studentUnivercity.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private UniversityRepository universityRepository;

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
                        student.getGender(),
                        student.getId()
                ))
                .collect(Collectors.toList());

        return responseDTOS;

    }

    public StudentResponseDTO createStudent(StudentRequestDTO requestDTO) {

        Student student = new Student();

        student.setFirstName(requestDTO.getFirstName());
        student.setLastName(requestDTO.getLastName());
        student.setEmail(requestDTO.getEmail());
        student.setPhoneNumber(requestDTO.getPhoneNumber());
        student.setEnrollmentDate(requestDTO.getEnrollmentDate());
        student.setGender(requestDTO.getGender());

        University university = universityRepository.findById(requestDTO.getUniversity_id())
                .orElseThrow(() -> new ResourceNotFoundException("University not found with id: " + requestDTO.getUniversity_id()));
        student.setUniversity(university);

        Student savedStudent = studentRepository.save(student);


        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setId(savedStudent.getId());
        responseDTO.setFirstName(savedStudent.getFirstName());
        responseDTO.setLastName(savedStudent.getLastName());
        responseDTO.setEmail(savedStudent.getEmail());
        responseDTO.setPhoneNumber(savedStudent.getPhoneNumber());
        responseDTO.setEnrollmentDate(savedStudent.getEnrollmentDate());
        responseDTO.setGender(savedStudent.getGender());
        responseDTO.setUniversity_id(university.getId());

        return responseDTO;

    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO requestDTO) {
        Student student = new Student();

        student.setFirstName(requestDTO.getFirstName());
        student.setLastName(requestDTO.getLastName());
        student.setEmail(requestDTO.getEmail());
        student.setPhoneNumber(requestDTO.getPhoneNumber());
        student.setEnrollmentDate(requestDTO.getEnrollmentDate());
        student.setGender(requestDTO.getGender());

        University university = universityRepository.findById(requestDTO.getUniversity_id())
                .orElseThrow(() -> new ResourceNotFoundException("University not found with id: " + requestDTO.getUniversity_id()));
        student.setUniversity(university);

        Student savedStudent = studentRepository.save(student);


        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setId(savedStudent.getId());
        responseDTO.setFirstName(savedStudent.getFirstName());
        responseDTO.setLastName(savedStudent.getLastName());
        responseDTO.setEmail(savedStudent.getEmail());
        responseDTO.setPhoneNumber(savedStudent.getPhoneNumber());
        responseDTO.setEnrollmentDate(savedStudent.getEnrollmentDate());
        responseDTO.setGender(savedStudent.getGender());
        responseDTO.setUniversity_id(university.getId());

        return responseDTO;


    }

    public StudentResponseDTO deleteStudent(Long id) {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " not found"));

            studentRepository.deleteById(id);

            StudentResponseDTO responseDTO = new StudentResponseDTO();
            responseDTO.setId(student.getId());
            responseDTO.setFirstName(student.getFirstName());
            responseDTO.setLastName(student.getLastName());
            responseDTO.setEmail(student.getEmail());
            responseDTO.setPhoneNumber(student.getPhoneNumber());
            responseDTO.setGender(student.getGender());
            responseDTO.setUniversity_id(student.getUniversity().getId());

            return responseDTO;
        }

}

