<?php
session_start();
header( 'Content-Type: application/json' );

// Database connections using PDO
try {
    $mssql_conn = new PDO( 'sqlsrv:Server=113.23.136.34;Database=TESTINGDB', 'user_viewtestingdb', "user_viewtestingdb#131024$^~" );
    $mssql_conn->setAttribute( PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION );
} catch ( PDOException $e ) {
    echo json_encode( [ 'error' => 'MSSQL Connection failed: ' . $e->getMessage() ] );
    exit();
}

try {
    $mysql_conn = new PDO( 'mysql:host=localhost;dbname=kptrtdb', 'root', '' );
    $mysql_conn->setAttribute( PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION );
} catch ( PDOException $e ) {
    echo json_encode( [ 'error' => 'MySQL Connection failed: ' . $e->getMessage() ] );
    exit();
}

// Start the migration process
$start_time = microtime( true );
$total_migrated = 0;

try {
    $mssql_query = 'SELECT * FROM [Student].[D1_Student_Realtime]';
    $mssql_stmt = $mssql_conn->query( $mssql_query );

    while ( $row = $mssql_stmt->fetch( PDO::FETCH_ASSOC ) ) {
        $insert_query = "INSERT INTO D1_Student_Realtime (matrik_no, std_name, ic_no, passport_no, OKU_id, OKU_no, income, intake_qualification_id, course_code, status_id, semester_no, CGP, CGPA, total_credit_hour, enter_date, end_date, convocation_date, end_session_id, mode_id, sponsor_id, campus_code, graduation_code, citizen_id, country_id, date_create, date_update) 
                        VALUES (:matrik_no, :std_name, :ic_no, :passport_no, :OKU_id, :OKU_no, :income, :intake_qualification_id, :course_code, :status_id, :semester_no, :CGP, :CGPA, :total_credit_hour, :enter_date, :end_date, :convocation_date, :end_session_id, :mode_id, :sponsor_id, :campus_code, :graduation_code, :citizen_id, :country_id, :date_create, :date_update)";

        $stmt = $mysql_conn->prepare( $insert_query );
        $stmt->execute( $row );
        $total_migrated++;
    }
} catch ( PDOException $e ) {
    echo json_encode( [ 'error' => 'Migration failed: ' . $e->getMessage() ] );
    exit();
}

// End the migration process
$end_time = microtime( true );
$elapsed_time = round( $end_time - $start_time, 2 );

// Return migration summary
echo json_encode( [
    'start_time' => date( 'Y-m-d H:i:s', ( int ) $start_time ),
    'end_time' => date( 'Y-m-d H:i:s', ( int ) $end_time ),
    'elapsed_time' => $elapsed_time,
    'total_migrated' => $total_migrated
] );
?>
