package com.example.migrationservice.repository;

import com.example.migrationservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface MysqlStudentRepository extends JpaRepository<Student, String> {

    @PersistenceContext(unitName = "mysqlEntityManagerFactory")
    EntityManager getEntityManager();
}
