package com.example.studentUnivercity.service;

import com.example.studentUnivercity.DTO.CourseRequestDTO;
import com.example.studentUnivercity.DTO.CourseResponseDTO;
import com.example.studentUnivercity.exceptions.ResourceNotFoundException;
import com.example.studentUnivercity.model.Course;
import com.example.studentUnivercity.model.University;
import com.example.studentUnivercity.repository.CourseRepository;
import com.example.studentUnivercity.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UniversityRepository universityRepository;

    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO) {

        // Fetch university using university_id from request DTO
        University university = universityRepository.findById(courseRequestDTO.getUniversity_id())
                .orElseThrow(() -> new RuntimeException("University not found with the ID: " + courseRequestDTO.getUniversity_id()));

        // Convert request DTO to Course entity
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

        // Save the course entity to generate ID
        Course savedCourse = courseRepository.save(course);

        // Convert saved entity to response DTO
        CourseResponseDTO responseDTO = new CourseResponseDTO();
        responseDTO.setId(savedCourse.getId());
        responseDTO.setName(savedCourse.getName());
        responseDTO.setDescription(savedCourse.getDescription());
        responseDTO.setCredits(savedCourse.getCredits());
        responseDTO.setSemester(savedCourse.getSemester());
        responseDTO.setStartDate(savedCourse.getStartDate());
        responseDTO.setEndDate(savedCourse.getEndDate());
        responseDTO.setDuration(savedCourse.getDuration());
        responseDTO.setLevel(savedCourse.getLevel());
        responseDTO.setUniversityName(savedCourse.getUniversity().getUniversityName());
        responseDTO.setUniversityWebsite(savedCourse.getUniversity().getWebsite());

        return responseDTO;
    }


    public List<CourseResponseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();

        List<CourseResponseDTO> responseDTOs = new ArrayList<>();

        for (Course course : courses) {
            CourseResponseDTO responseDTO = new CourseResponseDTO();
            responseDTO.setId(course.getId());
            responseDTO.setId(course.getUniversity().getId());
            responseDTO.setName(course.getName());
            responseDTO.setDescription(course.getDescription());
            responseDTO.setCredits(course.getCredits());
            responseDTO.setSemester(course.getSemester());
            responseDTO.setStartDate(course.getStartDate());
            responseDTO.setEndDate(course.getEndDate());
            responseDTO.setDuration(course.getDuration());
            responseDTO.setLevel(course.getLevel());

            responseDTO.setUniversityName(course.getUniversity().getUniversityName());
            responseDTO.setUniversityWebsite(course.getUniversity().getWebsite());

            responseDTOs.add(responseDTO);
        }

        return responseDTOs; // Return the list of CourseResponseDTOs
    }


    public CourseResponseDTO getCourseById(Long id) {
        // Fetch the course by ID from the repository
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + id));  // Throw exception if course not found

        // Manually map the Course entity to CourseResponseDTO
        CourseResponseDTO responseDTO = new CourseResponseDTO();
        responseDTO.setId(course.getUniversity().getId());
        responseDTO.setName(course.getName());
        responseDTO.setDescription(course.getDescription());
        responseDTO.setCredits(course.getCredits());
        responseDTO.setSemester(course.getSemester());
        responseDTO.setStartDate(course.getStartDate());
        responseDTO.setEndDate(course.getEndDate());
        responseDTO.setDuration(course.getDuration());
        responseDTO.setLevel(course.getLevel());
        responseDTO.setUniversityName(course.getUniversity().getUniversityName());
        responseDTO.setUniversityWebsite(course.getUniversity().getWebsite());

        return responseDTO;
    }


    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO) {

        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + id));

        University university = universityRepository.findById(courseRequestDTO.getUniversity_id())
                .orElseThrow(() -> new ResourceNotFoundException("University not found with ID: " + courseRequestDTO.getUniversity_id()));

        // Update course fields
        existingCourse.setName(courseRequestDTO.getName());
        existingCourse.setDescription(courseRequestDTO.getDescription());
        existingCourse.setCredits(courseRequestDTO.getCredits());
        existingCourse.setSemester(courseRequestDTO.getSemester());
        existingCourse.setStartDate(courseRequestDTO.getStartDate());
        existingCourse.setEndDate(courseRequestDTO.getEndDate());
        existingCourse.setDuration(courseRequestDTO.getDuration());
        existingCourse.setLevel(courseRequestDTO.getLevel());
        existingCourse.setUniversity(university);

        // Save updated course
        Course updatedCourse = courseRepository.save(existingCourse);

        // Map updated course to response DTO
        CourseResponseDTO responseDTO = new CourseResponseDTO();
        responseDTO.setId(updatedCourse.getUniversity().getId());
        responseDTO.setName(updatedCourse.getName());
        responseDTO.setDescription(updatedCourse.getDescription());
        responseDTO.setCredits(updatedCourse.getCredits());
        responseDTO.setSemester(updatedCourse.getSemester());
        responseDTO.setStartDate(updatedCourse.getStartDate());
        responseDTO.setEndDate(updatedCourse.getEndDate());
        responseDTO.setDuration(updatedCourse.getDuration());
        responseDTO.setLevel(updatedCourse.getLevel());
        responseDTO.setUniversityName(updatedCourse.getUniversity().getUniversityName());
        responseDTO.setUniversityWebsite(updatedCourse.getUniversity().getWebsite());

        return responseDTO;
    }


    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + id));

        courseRepository.delete(course);
    }

}
