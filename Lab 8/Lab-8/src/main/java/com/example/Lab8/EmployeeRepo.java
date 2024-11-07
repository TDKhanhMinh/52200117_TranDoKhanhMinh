package com.example.Lab8;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeRepo extends JpaRepository<Employees, Integer> {
}
