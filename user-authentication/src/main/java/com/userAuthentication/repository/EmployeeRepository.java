package com.userAuthentication.repository;

import com.userAuthentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<User, Long> {
}
