package com.example.migrationservice.service;

import com.example.migrationservice.model.Student;
import com.example.migrationservice.repository.MssqlStudentRepository;
import com.example.migrationservice.repository.MysqlStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MigrationService {

    @Autowired
    private MssqlStudentRepository mssqlRepo;

    @Autowired
    private MysqlStudentRepository mysqlRepo;

    public String migrateData() {
        long startTime = System.currentTimeMillis();

        List<Student> students = mssqlRepo.findAll();
        mysqlRepo.saveAll(students);

        long endTime = System.currentTimeMillis();
        long elapsedTime = (endTime - startTime) / 1000; // Convert ms to seconds

        return "Migration completed! Time taken: " + elapsedTime + " seconds. Total Records: " + students.size();
    }
}
