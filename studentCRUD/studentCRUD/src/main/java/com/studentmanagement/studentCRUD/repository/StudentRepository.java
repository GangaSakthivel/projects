package com.studentmanagement.studentCRUD.repository;

import com.studentmanagement.studentCRUD.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
