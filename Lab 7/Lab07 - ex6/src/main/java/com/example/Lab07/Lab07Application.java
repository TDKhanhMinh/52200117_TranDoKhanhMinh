package com.example.Lab07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class Lab07Application {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StudentPagingRepo studentPagingRepo;

    public static void main(String[] args) {
        SpringApplication.run(Lab07Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
//            studentRepo.save(new Student("Minh", "minh@gmail.com", 20, 7.5));
//            studentRepo.save(new Student("Minh01", "minh01@gmail.com", 21, 5.5));
//            studentRepo.save(new Student("Minh02", "minh02@gmail.com", 22, 6.5));
//
//            studentRepo.save(new Student("Minh03", "minh@gmail.com", 20, 7.5));
//            studentRepo.save(new Student("Minh04", "minh01@gmail.com", 21, 5.5));
//            studentRepo.save(new Student("Minh05", "minh02@gmail.com", 22, 6.5));




            Sort sort = Sort.by("age").descending().and(Sort.by("ieltsScore").ascending());
            Iterable<Student> sortedStudents = studentPagingRepo.findAll(sort);
            System.out.println("Students sorted by age (desc) and IELTS score (asc):");
            sortedStudents.forEach(System.out::println);

            Pageable pageable = PageRequest.of(1, 3, Sort.by("age").descending());
            List<Student> pagedStudents = studentPagingRepo.findAll(pageable).getContent();
            System.out.println("\nStudents in positions 4-5-6:");
            pagedStudents.forEach(System.out::println);

        };
    }
}
