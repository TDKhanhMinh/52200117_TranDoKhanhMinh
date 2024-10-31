package com.example.Lab07;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPagingRepo extends PagingAndSortingRepository<Student, Integer> {






}
