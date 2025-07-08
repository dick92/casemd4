package com.example.case4.repository;

import com.example.case4.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFullNameContainingIgnoreCase(String keyword);
}
