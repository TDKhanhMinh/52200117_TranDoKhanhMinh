package com.example.Lab07;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    List<Student> findAllByAgeGreaterThanEqual(int age);

    long countByIeltsScore(double ieltsScore);

    List<Student> findByNameContainingIgnoreCase(String name);

}
