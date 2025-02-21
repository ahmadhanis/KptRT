const express = require("express");
const mysql = require("mysql2/promise");
const sql = require("mssql");
const cors = require("cors");
const path = require("path");

const app = express();
const PORT = 3000;

app.use(cors()); // Allow frontend requests
app.use(express.json());

// Serve the HTML file when accessing "/"
app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, "index.html"));
});

// MSSQL Connection Config
const mssqlConfig = {
    user: "user_viewtestingdb",
    password: "user_viewtestingdb#131024$^~",
    server: "113.23.136.34",
    database: "TESTINGDB",
    options: {
        encrypt: false, // Disable encryption if not needed
        trustServerCertificate: true
    }
};

// MySQL Connection Config
const mysqlConfig = {
    host: "localhost",
    user: "root",
    password: "",
    database: "kptrtdb"
};

// Migration Route
app.get("/migrate", async (req, res) => {
    let startTime = Date.now();
    let totalMigrated = 0;

    try {
        // Connect to MSSQL
        console.log("Connecting to MSSQL...");
        await sql.connect(mssqlConfig);
        const mssqlResult = await sql.query("SELECT * FROM [Student].[D1_Student_Realtime]");
        const records = mssqlResult.recordset;

        // Connect to MySQL
        console.log("Connecting to MySQL...");
        const mysqlConn = await mysql.createConnection(mysqlConfig);

        // Insert into MySQL
        console.log(`Migrating ${records.length} records...`);
        for (const row of records) {
            const query = `INSERT INTO D1_Student_Realtime (
                matrik_no, std_name, ic_no, passport_no, OKU_id, OKU_no, income, intake_qualification_id, course_code, 
                status_id, semester_no, CGP, CGPA, total_credit_hour, enter_date, end_date, convocation_date, 
                end_session_id, mode_id, sponsor_id, campus_code, graduation_code, citizen_id, country_id, date_create, date_update
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`;

            await mysqlConn.execute(query, [
                row.matrik_no, row.std_name, row.ic_no, row.passport_no, row.OKU_id, row.OKU_no, row.income,
                row.intake_qualification_id, row.course_code, row.status_id, row.semester_no, row.CGP, row.CGPA,
                row.total_credit_hour, row.enter_date, row.end_date, row.convocation_date, row.end_session_id,
                row.mode_id, row.sponsor_id, row.campus_code, row.graduation_code, row.citizen_id, row.country_id,
                row.date_create, row.date_update
            ]);

            totalMigrated++;
        }

        let endTime = Date.now();
        let elapsedTime = ((endTime - startTime) / 1000).toFixed(2);

        console.log(`Migration completed! Time taken: ${elapsedTime} seconds`);

        res.json({
            start_time: new Date(startTime).toLocaleString(),
            end_time: new Date(endTime).toLocaleString(),
            elapsed_time: elapsedTime,
            total_migrated: totalMigrated
        });

    } catch (error) {
        console.error("Migration failed:", error);
        res.status(500).json({ error: error.message });
    } finally {
        sql.close();
    }
});

// Start Server
app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});
