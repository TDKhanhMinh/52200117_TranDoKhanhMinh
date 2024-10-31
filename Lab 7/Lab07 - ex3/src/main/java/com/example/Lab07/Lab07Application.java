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
            studentRepo.save(new Student("Minh", "minh@gmail.com", 20));
            studentRepo.save(new Student("Minh01", "minh01@gmail.com", 21));
            studentRepo.save(new Student("Minh02", "minh02@gmail.com", 22));

            System.out.println("Student in database: ");
            List<Student> students = studentRepo.findAll();
            for (Student student : students) {
                System.out.println(student.toString());
            }

            Student Minh = studentRepo.findById(1).orElseThrow();
            Minh.setEmail("minh004@gmail.com");
            studentRepo.save(Minh);


            System.out.println("Student minh after update");
            List<Student> studentList = studentRepo.findAll();
            for (Student student : studentList) {
                System.out.println(student.toString());
            }


            studentRepo.deleteById(2);
            System.out.println("Student after delete student with id=2");
            List<Student> studentsDelete = studentRepo.findAll();
            for (Student student : studentsDelete) {
                System.out.println(student.toString());
            }

        };
    }
}
