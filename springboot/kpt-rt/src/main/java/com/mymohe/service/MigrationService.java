package com.mymohe.service;

import com.mymohe.mssql.MssqlStudent;
import com.mymohe.mysql.MysqlStudent;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MigrationService {

    // Inject MSSQL EntityManager
    @PersistenceContext(unitName = "mssql")
    private EntityManager mssqlEntityManager;

    // Inject MySQL EntityManager
    @PersistenceContext(unitName = "mysql")
    private EntityManager mysqlEntityManager;

    /**
     * Migrates data from MSSQL to MySQL.
     *
     * @return A map containing migration summary details.
     */
    @Transactional("mssqlTransactionManager") // Use MSSQL transaction manager
    public Map<String, Object> migrateData() {
        long startTime = System.currentTimeMillis();
        int totalMigrated = 0;

        try {
            // Fetch all records from MSSQL
            List<MssqlStudent> students = mssqlEntityManager.createQuery(
                    "SELECT s FROM MssqlStudent s", MssqlStudent.class).getResultList();

            // Migrate each record to MySQL
            for (MssqlStudent mssqlStudent : students) {
                MysqlStudent mysqlStudent = new MysqlStudent();
                BeanUtils.copyProperties(mssqlStudent, mysqlStudent); // Copy properties

                // Persist the record in MySQL
                mysqlEntityManager.persist(mysqlStudent);
                totalMigrated++;
            }
        } catch (Exception e) {
            throw new RuntimeException("Migration failed: " + e.getMessage(), e);
        }

        long endTime = System.currentTimeMillis();
        double elapsedTime = (endTime - startTime) / 1000.0; // Convert to seconds

        // Prepare migration summary
        Map<String, Object> summary = new HashMap<>();
        summary.put("start_time", LocalDateTime.now().minusSeconds((long) elapsedTime));
        summary.put("end_time", LocalDateTime.now());
        summary.put("elapsed_time", elapsedTime);
        summary.put("total_migrated", totalMigrated);

        return summary;
    }
}