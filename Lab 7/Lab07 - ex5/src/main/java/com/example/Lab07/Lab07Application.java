package com.example.Lab07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Lab07Application {
    @Autowired
    private StudentRepo studentRepo;

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


            System.out.println("Student in database: ");
            List<Student> students = studentRepo.findAll();
            for (Student student : students) {
                System.out.println(student.toString());
            }

            List<Student> studentsByAge = studentRepo.findByMinAge(22);
            System.out.println("Students aged 21 or older:");
            studentsByAge.forEach(System.out::println);

            long countIelts = studentRepo.countByScore(6.5);
            System.out.println("\nNumber of students with an IELTS score of 6.5: " + countIelts);

            List<Student> studentsByName = studentRepo.findByNameContains("02");
            System.out.println("\nStudents with names containing '02':");
            studentsByName.forEach(System.out::println);


        };
    }
}
