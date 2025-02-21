package com.example.migrationservice.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "D1_Student_Realtime")
public class Student {

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
    private String intakeQualificationId;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "status_id")
    private String statusId;

    @Column(name = "semester_no")
    private Integer semesterNo;

    @Column(name = "CGP")
    private Double CGP;

    @Column(name = "CGPA")
    private Double CGPA;

    @Column(name = "total_credit_hour")
    private Integer totalCreditHour;

    @Column(name = "enter_date")
    @Temporal(TemporalType.DATE)
    private Date enterDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "convocation_date")
    @Temporal(TemporalType.DATE)
    private Date convocationDate;

    @Column(name = "end_session_id")
    private String endSessionId;

    @Column(name = "mode_id")
    private String modeId;

    @Column(name = "sponsor_id")
    private String sponsorId;

    @Column(name = "campus_code")
    private String campusCode;

    @Column(name = "graduation_code")
    private String graduationCode;

    @Column(name = "citizen_id")
    private String citizenId;

    @Column(name = "country_id")
    private String countryId;

    @Column(name = "date_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @Column(name = "date_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate;

    // Getters and setters
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

    public String getIntakeQualificationId() {
        return intakeQualificationId;
    }

    public void setIntakeQualificationId(String intakeQualificationId) {
        this.intakeQualificationId = intakeQualificationId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public Integer getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(Integer semesterNo) {
        this.semesterNo = semesterNo;
    }

    public Double getCGP() {
        return CGP;
    }

    public void setCGP(Double CGP) {
        this.CGP = CGP;
    }

    public Double getCGPA() {
        return CGPA;
    }

    public void setCGPA(Double CGPA) {
        this.CGPA = CGPA;
    }

    public Integer getTotalCreditHour() {
        return totalCreditHour;
    }

    public void setTotalCreditHour(Integer totalCreditHour) {
        this.totalCreditHour = totalCreditHour;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getConvocationDate() {
        return convocationDate;
    }

    public void setConvocationDate(Date convocationDate) {
        this.convocationDate = convocationDate;
    }

    public String getEndSessionId() {
        return endSessionId;
    }

    public void setEndSessionId(String endSessionId) {
        this.endSessionId = endSessionId;
    }

    public String getModeId() {
        return modeId;
    }

    public void setModeId(String modeId) {
        this.modeId = modeId;
    }

    public String getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }
}
