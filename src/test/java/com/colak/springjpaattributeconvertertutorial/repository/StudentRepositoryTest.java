package com.colak.springjpaattributeconvertertutorial.repository;

import com.colak.springjpaattributeconvertertutorial.jpa.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Order(1)
    void testSave() {
        Student student = new Student(null, "firstName1", "lastName1", Map.of("key1", "value1"));
        Student savedStudent = studentRepository.save(student);
        assertThat(savedStudent.getId()).isNotNull();
    }

    @Test
    @Order(2)
    void testFind() {
        Optional<Student> fetchedStudent = studentRepository.findById(1);
        Student student = fetchedStudent.orElseGet(() -> {
            fail("Optional is empty");
            return null; // Unreachable, but needed for the compiler
        });

        Map<String, String> expectedAttributes = Map.of("key1", "value1");
        assertThat(student.getAttributes()).isEqualTo(expectedAttributes);
    }
}
