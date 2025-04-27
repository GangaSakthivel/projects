package com.example.studentUnivercity.repository;

import com.example.studentUnivercity.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
