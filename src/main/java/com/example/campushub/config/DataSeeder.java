package com.example.campushub.config;

import com.example.campushub.student.Student;
import com.example.campushub.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seedStudents(StudentRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Student("Nguyen Minh Anh", "minhanh@example.com", "Information Technology"));
                repository.save(new Student("Tran Quoc Bao", "quocbao@example.com", "Software Engineering"));
                repository.save(new Student("Le Thu Ha", "thuha@example.com", "Computer Science"));
            }
        };
    }
}
