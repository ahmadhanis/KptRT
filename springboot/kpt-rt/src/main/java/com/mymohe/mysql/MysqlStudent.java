package com.mymohe.mysql;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "D1_Student_Realtime", schema = "Student")
public class MysqlStudent {

    @Id
    @Column(name = "matrik_no")
    private String matrikNo;

    @Column(name = "std_name")
    private String stdName;

    @Column(name = "ic_no")
    private String icNo;

    @Column(name = "passport_no")
    private String passportNo;

    @Column(name = "OKU_id")
    private String okuId;

    @Column(name = "OKU_no")
    private String okuNo;

    @Column(name = "income")
    private Double income;

    @Column(name = "intake_qualification_id")
    private Integer intakeQualificationId;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "semester_no")
    private Integer semesterNo;

    @Column(name = "CGP")
    private Double cgp;

    @Column(name = "CGPA")
    private Double cgpa;

    @Column(name = "total_credit_hour")
    private Integer totalCreditHour;

    @Column(name = "enter_date")
    private LocalDateTime enterDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "convocation_date")
    private LocalDateTime convocationDate;

    @Column(name = "end_session_id")
    private Integer endSessionId;

    @Column(name = "mode_id")
    private Integer modeId;

    @Column(name = "sponsor_id")
    private Integer sponsorId;

    @Column(name = "campus_code")
    private String campusCode;

    @Column(name = "graduation_code")
    private String graduationCode;

    @Column(name = "citizen_id")
    private Integer citizenId;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    // Getters and Setters
    public String getMatrikNo() {
        return matrikNo;
    }

    public void setMatrikNo(String matrikNo) {
        this.matrikNo = matrikNo;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getIcNo() {
        return icNo;
    }

    public void setIcNo(String icNo) {
        this.icNo = icNo;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getOkuId() {
        return okuId;
    }

    public void setOkuId(String okuId) {
        this.okuId = okuId;
    }

    public String getOkuNo() {
        return okuNo;
    }

    public void setOkuNo(String okuNo) {
        this.okuNo = okuNo;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Integer getIntakeQualificationId() {
        return intakeQualificationId;
    }

    public void setIntakeQualificationId(Integer intakeQualificationId) {
        this.intakeQualificationId = intakeQualificationId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(Integer semesterNo) {
        this.semesterNo = semesterNo;
    }

    public Double getCgp() {
        return cgp;
    }

    public void setCgp(Double cgp) {
        this.cgp = cgp;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public Integer getTotalCreditHour() {
        return totalCreditHour;
    }

    public void setTotalCreditHour(Integer totalCreditHour) {
        this.totalCreditHour = totalCreditHour;
    }

    public LocalDateTime getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(LocalDateTime enterDate) {
        this.enterDate = enterDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getConvocationDate() {
        return convocationDate;
    }

    public void setConvocationDate(LocalDateTime convocationDate) {
        this.convocationDate = convocationDate;
    }

    public Integer getEndSessionId() {
        return endSessionId;
    }

    public void setEndSessionId(Integer endSessionId) {
        this.endSessionId = endSessionId;
    }

    public Integer getModeId() {
        return modeId;
    }

    public void setModeId(Integer modeId) {
        this.modeId = modeId;
    }

    public Integer getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Integer sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getCampusCode() {
        return campusCode;
    }

    public void setCampusCode(String campusCode) {
        this.campusCode = campusCode;
    }

    public String getGraduationCode() {
        return graduationCode;
    }

    public void setGraduationCode(String graduationCode) {
        this.graduationCode = graduationCode;
    }

    public Integer getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Integer citizenId) {
        this.citizenId = citizenId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}