package com.example.migrationservice.repository;

import com.example.migrationservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MssqlStudentRepository extends JpaRepository<Student, String> {
}
