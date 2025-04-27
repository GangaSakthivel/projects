package com.example.studentUnivercity.service;

import com.example.studentUnivercity.DTO.CourseRequestDTO;
import com.example.studentUnivercity.DTO.CourseResponseDTO;
import com.example.studentUnivercity.repository.CourseRepository;
import com.example.studentUnivercity.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UniversityRepository universityRepository;

    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO) {

        CourseResponseDTO courseResponseDTO = universityRepository.findById(id);





    }
}
