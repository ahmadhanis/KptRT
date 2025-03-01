package com.kptrt.realtime.services;

import com.kptrt.realtime.models.StudentRealtime;
import com.kptrt.realtime.repositories.StudentRealtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataMigrationService {

    private static final Logger logger = LoggerFactory.getLogger(DataMigrationService.class);

    @Autowired
    private JdbcTemplate mssqlJdbcTemplate; // For MSSQL Connection

    @Autowired
    private StudentRealtimeRepository studentRealtimeRepository;

    @Transactional
    public String migrateData() {
        long startTime = System.currentTimeMillis();
        int totalMigrated = 0;

        try {
            logger.info("Starting data migration...");

            // Fetch data from MSSQL
            String sql = "SELECT * FROM Student.D1_Student_Realtime";
            List<StudentRealtime> studentList = new ArrayList<>();

            mssqlJdbcTemplate.query(sql, rs -> {
                StudentRealtime student = mapResultSetToStudent(rs);
                studentList.add(student);
                System.out.println(student.getMatrikNo());
            });

            logger.info("Fetched {} records from MSSQL", studentList.size());

            if (!studentList.isEmpty()) {
                // Save all records into MySQL
                studentRealtimeRepository.saveAll(studentList);
                for (StudentRealtime student : studentList) {
                    studentRealtimeRepository.save(student);
                }
                totalMigrated = studentList.size();
                logger.info("Successfully migrated {} records to MySQL", totalMigrated);
            } else {
                logger.warn("No records found for migration.");
            }

            long endTime = System.currentTimeMillis();
            double elapsedTime = (endTime - startTime) / 1000.0;

            return "Migration completed. Total records migrated: " + totalMigrated +
                    ". Time taken: " + elapsedTime + " seconds.";

        } catch (Exception e) {
            logger.error("Migration failed: {}", e.getMessage(), e);
            return "Migration failed: " + e.getMessage();
        }
    }

    private StudentRealtime mapResultSetToStudent(ResultSet rs) throws SQLException {
        StudentRealtime student = new StudentRealtime();

        student.setMatrikNo(rs.getString("matrik_no"));
        student.setStdName(rs.getString("std_name"));
        student.setIcNo(rs.getString("ic_no"));
        student.setPassportNo(rs.getString("passport_no"));
        student.setOkuId(rs.getInt("OKU_id"));
        student.setOkuNo(rs.getString("OKU_no"));
        student.setIncome(rs.getBigDecimal("income"));
        student.setIntakeQualificationId(rs.getInt("intake_qualification_id"));
        student.setCourseCode(rs.getString("course_code"));
        student.setStatusId(rs.getInt("status_id"));
        student.setSemesterNo(rs.getInt("semester_no"));
        student.setCgp(rs.getBigDecimal("CGP"));
        student.setCgpa(rs.getBigDecimal("CGPA"));
        student.setTotalCreditHour(rs.getInt("total_credit_hour"));
        student.setEnterDate(rs.getDate("enter_date"));
        student.setEndDate(rs.getDate("end_date"));
        student.setConvocationDate(rs.getDate("convocation_date"));
        student.setEndSessionId(rs.getInt("end_session_id"));
        student.setModeId(rs.getInt("mode_id"));
        student.setSponsorId(rs.getInt("sponsor_id"));
        student.setCampusCode(rs.getString("campus_code"));
        student.setGraduationCode(rs.getString("graduation_code"));
        student.setCitizenId(rs.getInt("citizen_id"));
        student.setCountryId(rs.getInt("country_id"));
        student.setDateCreate(rs.getTimestamp("date_create"));
        student.setDateUpdate(rs.getTimestamp("date_update"));

        return student;
    }
}
