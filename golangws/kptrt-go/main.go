package main

import (
    "database/sql"
    "fmt"
    "log"
    "net/http"
    "time"

    "github.com/gin-gonic/gin"
    _ "github.com/denisenkom/go-mssqldb"
    _ "github.com/go-sql-driver/mysql"
    "github.com/jmoiron/sqlx"
)

// Define a struct to represent the rows in the MSSQL table
type StudentRealtime struct {
    MatrikNo             sql.NullString  `db:"matrik_no"`
    StdName              sql.NullString  `db:"std_name"`
    IcNo                 sql.NullString  `db:"ic_no"`
    PassportNo           sql.NullString  `db:"passport_no"`
    OKUId                sql.NullString  `db:"OKU_id"`
    OKUNo                sql.NullString  `db:"OKU_no"`
    Income               sql.NullFloat64 `db:"income"`
    IntakeQualificationId sql.NullString  `db:"intake_qualification_id"`
    CourseCode           sql.NullString  `db:"course_code"`
    StatusId             sql.NullInt64   `db:"status_id"`
    SemesterNo           sql.NullInt64   `db:"semester_no"`
    CGP                  sql.NullFloat64 `db:"CGP"`
    CGPA                 sql.NullFloat64 `db:"CGPA"`
    TotalCreditHour      sql.NullInt64   `db:"total_credit_hour"`
    EnterDate            sql.NullString  `db:"enter_date"`
    EndDate              sql.NullString  `db:"end_date"`
    ConvocationDate      sql.NullString  `db:"convocation_date"`
    EndSessionId         sql.NullInt64   `db:"end_session_id"`
    ModeId               sql.NullInt64   `db:"mode_id"`
    SponsorId            sql.NullInt64   `db:"sponsor_id"`
    CampusCode           sql.NullString  `db:"campus_code"`
    GraduationCode       sql.NullString  `db:"graduation_code"`
    CitizenId            sql.NullInt64   `db:"citizen_id"`
    CountryId            sql.NullString  `db:"country_id"` // Changed from sql.NullInt64 to sql.NullString
    DateCreate           sql.NullString  `db:"date_create"`
    DateUpdate           sql.NullString  `db:"date_update"`
}

// Struct for migration summary
type MigrationSummary struct {
    StartTime      string  `json:"start_time"`
    EndTime        string  `json:"end_time"`
    ElapsedTime    float64 `json:"elapsed_time"`
    TotalMigrated  int     `json:"total_migrated"`
}

// Helper functions to extract values from nullable fields
func getStringOrNull(field sql.NullString) string {
    if field.Valid {
        return field.String
    }
    return ""
}

func getIntOrNull(field sql.NullInt64) int {
    if field.Valid {
        return int(field.Int64)
    }
    return 0
}

func getFloatOrNull(field sql.NullFloat64) float64 {
    if field.Valid {
        return field.Float64
    }
    return 0.0
}

func main() {
    // Initialize Gin
    r := gin.Default()

    // Migration endpoint
    r.GET("/migrate", func(c *gin.Context) {
        // Start the migration process
        startTime := time.Now()

        // Connect to MSSQL
        mssqlConn, err := sqlx.Connect("sqlserver", "server=113.23.136.34;user id=user_viewtestingdb;password=user_viewtestingdb#131024$^~;database=TESTINGDB")
        if err != nil {
            log.Printf("MSSQL Connection failed: %v", err)
            c.JSON(http.StatusInternalServerError, gin.H{"error": fmt.Sprintf("MSSQL Connection failed: %v", err)})
            return
        }
        defer mssqlConn.Close()

        // Connect to MySQL
        mysqlConn, err := sqlx.Connect("mysql", "root:@tcp(localhost:3306)/kptrtdb")
        if err != nil {
            log.Printf("MySQL Connection failed: %v", err)
            c.JSON(http.StatusInternalServerError, gin.H{"error": fmt.Sprintf("MySQL Connection failed: %v", err)})
            return
        }
        defer mysqlConn.Close()

        // Fetch data from MSSQL
        var rows []StudentRealtime
        err = mssqlConn.Select(&rows, "SELECT * FROM [Student].[D1_Student_Realtime]")
        if err != nil {
            log.Printf("Failed to fetch data from MSSQL: %v", err)
            c.JSON(http.StatusInternalServerError, gin.H{"error": fmt.Sprintf("Failed to fetch data from MSSQL: %v", err)})
            return
        }

        log.Printf("Fetched %d rows from MSSQL", len(rows))

        // Prepare insert statement for MySQL
        insertQuery := `
        INSERT INTO D1_Student_Realtime (matrik_no, std_name, ic_no, passport_no, OKU_id, OKU_no, income, intake_qualification_id, course_code, status_id, semester_no, CGP, CGPA, total_credit_hour, enter_date, end_date, convocation_date, end_session_id, mode_id, sponsor_id, campus_code, graduation_code, citizen_id, country_id, date_create, date_update)
        VALUES (:matrik_no, :std_name, :ic_no, :passport_no, :OKU_id, :OKU_no, :income, :intake_qualification_id, :course_code, :status_id, :semester_no, :CGP, :CGPA, :total_credit_hour, :enter_date, :end_date, :convocation_date, :end_session_id, :mode_id, :sponsor_id, :campus_code, :graduation_code, :citizen_id, :country_id, :date_create, :date_update)
        `
        stmt, err := mysqlConn.PrepareNamed(insertQuery)
        if err != nil {
            log.Printf("Failed to prepare MySQL insert statement: %v", err)
            c.JSON(http.StatusInternalServerError, gin.H{"error": fmt.Sprintf("Failed to prepare MySQL insert statement: %v", err)})
            return
        }
        defer stmt.Close()

        totalMigrated := 0
        for _, row := range rows {
            // Convert nullable fields to their respective types
            matrikNo := getStringOrNull(row.MatrikNo)
            stdName := getStringOrNull(row.StdName)
            icNo := getStringOrNull(row.IcNo)
            passportNo := getStringOrNull(row.PassportNo)
            okuId := getStringOrNull(row.OKUId)
            okuNo := getStringOrNull(row.OKUNo)
            income := getFloatOrNull(row.Income)
            intakeQualificationId := getStringOrNull(row.IntakeQualificationId)
            courseCode := getStringOrNull(row.CourseCode)
            statusId := getIntOrNull(row.StatusId)
            semesterNo := getIntOrNull(row.SemesterNo)
            cgp := getFloatOrNull(row.CGP)
            cgpa := getFloatOrNull(row.CGPA)
            totalCreditHour := getIntOrNull(row.TotalCreditHour)
            enterDate := getStringOrNull(row.EnterDate)
            endDate := getStringOrNull(row.EndDate)
            convocationDate := getStringOrNull(row.ConvocationDate)
            endSessionId := getIntOrNull(row.EndSessionId)
            modeId := getIntOrNull(row.ModeId)
            sponsorId := getIntOrNull(row.SponsorId)
            campusCode := getStringOrNull(row.CampusCode)
            graduationCode := getStringOrNull(row.GraduationCode)
            citizenId := getIntOrNull(row.CitizenId)
            countryId := getStringOrNull(row.CountryId) // Updated to handle string values
            dateCreate := getStringOrNull(row.DateCreate)
            dateUpdate := getStringOrNull(row.DateUpdate)

            // Insert into MySQL
            _, err := stmt.Exec(map[string]interface{}{
                "matrik_no":              matrikNo,
                "std_name":               stdName,
                "ic_no":                  icNo,
                "passport_no":            passportNo,
                "OKU_id":                 okuId,
                "OKU_no":                 okuNo,
                "income":                 income,
                "intake_qualification_id": intakeQualificationId,
                "course_code":            courseCode,
                "status_id":              statusId,
                "semester_no":            semesterNo,
                "CGP":                    cgp,
                "CGPA":                   cgpa,
                "total_credit_hour":      totalCreditHour,
                "enter_date":             enterDate,
                "end_date":               endDate,
                "convocation_date":       convocationDate,
                "end_session_id":         endSessionId,
                "mode_id":                modeId,
                "sponsor_id":             sponsorId,
                "campus_code":            campusCode,
                "graduation_code":        graduationCode,
                "citizen_id":             citizenId,
                "country_id":             countryId, // Updated to handle string values
                "date_create":            dateCreate,
                "date_update":            dateUpdate,
            })
            if err != nil {
                log.Printf("Migration failed for row: %+v. Error: %v", row, err)
                c.JSON(http.StatusInternalServerError, gin.H{"error": fmt.Sprintf("Migration failed: %v", err)})
                return
            }
            totalMigrated++
        }

        // End the migration process
        endTime := time.Now()
        elapsedTime := endTime.Sub(startTime).Seconds()

        // Return migration summary
        summary := MigrationSummary{
            StartTime:     startTime.Format("2006-01-02 15:04:05"),
            EndTime:       endTime.Format("2006-01-02 15:04:05"),
            ElapsedTime:   elapsedTime,
            TotalMigrated: totalMigrated,
        }
        c.JSON(http.StatusOK, summary)
    })

    // Serve the HTML template
    r.LoadHTMLGlob("templates/*")

    r.GET("/", func(c *gin.Context) {
        c.HTML(http.StatusOK, "index.html", nil)
    })

    // Start the server
    log.Println("Server is running on http://localhost:8080")
    if err := r.Run(":8080"); err != nil {
        log.Fatalf("Failed to start server: %v", err)
    }
}