package com.br.universityStudent.repository;

import com.br.universityStudent.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

