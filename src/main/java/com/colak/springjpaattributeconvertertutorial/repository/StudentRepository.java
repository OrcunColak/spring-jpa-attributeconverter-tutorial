package com.colak.springjpaattributeconvertertutorial.repository;

import com.colak.springjpaattributeconvertertutorial.jpa.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
