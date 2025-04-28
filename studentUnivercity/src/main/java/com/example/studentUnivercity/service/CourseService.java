package com.example.studentUnivercity.service;

import com.example.studentUnivercity.DTO.CourseRequestDTO;
import com.example.studentUnivercity.DTO.CourseResponseDTO;
import com.example.studentUnivercity.model.Course;
import com.example.studentUnivercity.model.University;
import com.example.studentUnivercity.repository.CourseRepository;
import com.example.studentUnivercity.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UniversityRepository universityRepository;

    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO) {

        University university = universityRepository.findById(courseRequestDTO.getUniversityId())
                .orElseThrow(() -> new RuntimeException("University not found with the ID: " + courseRequestDTO.getUniversityId()));

        //request to entity conversion
        Course course = new Course();

        course.setUniversity(university);
        course.setName(courseRequestDTO.getName());
        course.setDescription(courseRequestDTO.getDescription());
        course.setCredits(courseRequestDTO.getCredits());
        course.setSemester(courseRequestDTO.getSemester());
        course.setStartDate(courseRequestDTO.getStartDate());
        course.setEndDate(courseRequestDTO.getEndDate());
        course.setDuration(courseRequestDTO.getDuration());
        course.setLevel(courseRequestDTO.getLevel());

        //course entity to response

        CourseResponseDTO responseDTO = new CourseResponseDTO();
        responseDTO.setName(course.getName());
        responseDTO.setDescription(course.getDescription());
        responseDTO.setCredits(course.getCredits());
        responseDTO.setSemester(course.getSemester());
        responseDTO.setStartDate(course.getStartDate());
        responseDTO.setEndDate(course.getEndDate());
        responseDTO.setDuration(course.getDuration());
        responseDTO.setLevel(course.getLevel());

        //settting up the fields that we want in response
        responseDTO.setUniversityName(course.getUniversity().getUniversityName());
        responseDTO.setUniversityWebsite(course.getUniversity().getWebsite());

        return responseDTO;

    }

//    public CourseResponseDTO getAllCourses() {
//
//    }
}
