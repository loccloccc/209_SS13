package com.example.campushub.student;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    public Student create(Student student) {
        if (repository.existsByEmail(student.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        return repository.save(student);
    }

    public Student update(Long id, Student input) {
        Student existing = findById(id);
        if (!existing.getEmail().equals(input.getEmail()) && repository.existsByEmail(input.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        existing.setFullName(input.getFullName());
        existing.setEmail(input.getEmail());
        existing.setMajor(input.getMajor());
        return repository.save(existing);
    }

    public void delete(Long id) {
        Student existing = findById(id);
        repository.delete(existing);
    }
}
