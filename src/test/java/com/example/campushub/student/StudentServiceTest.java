package com.example.campushub.student;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentServiceTest {
    @Test
    void findByIdReturnsStudentWhenPresent() {
        StudentRepository repository = mock(StudentRepository.class);
        Student expected = new Student("Test Student", "test@example.com", "IT");
        when(repository.findById(1L)).thenReturn(Optional.of(expected));

        Student actual = new StudentService(repository).findById(1L);

        assertEquals("Test Student", actual.getFullName());
    }

    @Test
    void findByIdThrowsNotFoundWhenMissing() {
        StudentRepository repository = mock(StudentRepository.class);
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> new StudentService(repository).findById(99L));
    }
}
