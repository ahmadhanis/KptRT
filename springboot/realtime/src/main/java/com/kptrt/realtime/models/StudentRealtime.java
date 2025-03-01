package com.kptrt.realtime.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "d1_student_realtime")
public class StudentRealtime {

    @Id
    @Column(name = "matrik_no", nullable = false, length = 20)
    private String matrikNo;

    @Column(name = "std_name", nullable = false, length = 255)
    private String stdName;

    @Column(name = "ic_no", length = 20)
    private String icNo;

    @Column(name = "passport_no", length = 20)
    private String passportNo;

    @Column(name = "OKU_id")
    private Integer okuId;

    @Column(name = "OKU_no", length = 20)
    private String okuNo;

    @Column(name = "income", precision = 10, scale = 2)
    private BigDecimal income;

    @Column(name = "intake_qualification_id")
    private Integer intakeQualificationId;

    @Column(name = "course_code", nullable = false, length = 20)
    private String courseCode;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "semester_no")
    private Integer semesterNo;

    @Column(name = "CGP", precision = 5, scale = 2)
    private BigDecimal cgp;

    @Column(name = "CGPA", precision = 5, scale = 2)
    private BigDecimal cgpa;

    @Column(name = "total_credit_hour")
    private Integer totalCreditHour;

    @Column(name = "enter_date")
    private Date enterDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "convocation_date")
    private Date convocationDate;

    @Column(name = "end_session_id")
    private Integer endSessionId;

    @Column(name = "mode_id")
    private Integer modeId;

    @Column(name = "sponsor_id")
    private Integer sponsorId;

    @Column(name = "campus_code", length = 20)
    private String campusCode;

    @Column(name = "graduation_code", length = 20)
    private String graduationCode;

    @Column(name = "citizen_id")
    private Integer citizenId;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "date_create", nullable = false, updatable = false)
    private Timestamp dateCreate;

    @Column(name = "date_update", nullable = false)
    private Timestamp dateUpdate;

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

    public Integer getOkuId() {
        return okuId;
    }

    public void setOkuId(Integer okuId) {
        this.okuId = okuId;
    }

    public String getOkuNo() {
        return okuNo;
    }

    public void setOkuNo(String okuNo) {
        this.okuNo = okuNo;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
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

    public BigDecimal getCgp() {
        return cgp;
    }

    public void setCgp(BigDecimal cgp) {
        this.cgp = cgp;
    }

    public BigDecimal getCgpa() {
        return cgpa;
    }

    public void setCgpa(BigDecimal cgpa) {
        this.cgpa = cgpa;
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

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}