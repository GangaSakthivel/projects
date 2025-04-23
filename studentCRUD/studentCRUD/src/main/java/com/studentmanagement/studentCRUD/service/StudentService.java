package com.studentmanagement.studentCRUD.service;

import com.studentmanagement.studentCRUD.exception.StudentSaveException;
import com.studentmanagement.studentCRUD.model.Student;
import com.studentmanagement.studentCRUD.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public void saveInput(Student student) {
       try {
           studentRepository.save(student);
       }catch (Exception e){
           throw new StudentSaveException("Error while saving student" + e.getMessage());
       }
    }
}
