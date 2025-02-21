<?php
// Database connection parameters
$server = '113.23.136.34';
$database = 'TESTINGDB';
$username = 'user_viewtestingdb';
$password = "user_viewtestingdb#131024$^~";

try {
    // Establishing connection using PDO
    $conn = new PDO( "sqlsrv:Server=$server;Database=$database", $username, $password );
    $conn->setAttribute( PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION );

    // Pagination setup
    $recordsPerPage = 10000;
    $page = isset( $_GET[ 'page' ] ) ? ( int )$_GET[ 'page' ] : 1;
    $offset = ( $page - 1 ) * $recordsPerPage;

    // Query with pagination
    $sql = "WITH OrderedStudents AS (
                SELECT *, ROW_NUMBER() OVER (ORDER BY matrik_no) AS RowNum
                FROM [Student].[D1_Student_Realtime]
            )
            SELECT * FROM OrderedStudents WHERE RowNum BETWEEN :start AND :end";

    $stmt = $conn->prepare( $sql );
    $stmt->bindValue( ':start', $offset + 1, PDO::PARAM_INT );
    $stmt->bindValue( ':end', $offset + $recordsPerPage, PDO::PARAM_INT );
    $stmt->execute();

    // Fetch data
    $data = $stmt->fetchAll( PDO::FETCH_ASSOC );

    // Count total records
    $countSql = 'SELECT COUNT(*) AS Total FROM [Student].[D1_Student_Realtime]';
    $countStmt = $conn->query( $countSql );
    $totalRecords = $countStmt->fetch( PDO::FETCH_ASSOC )[ 'Total' ];
    $totalPages = ceil( $totalRecords / $recordsPerPage );

    // Display data
    echo "<table border='1'>";
    echo '<tr><th>Matrik No</th><th>Name</th><th>IC No</th><th>Passport No</th><th>OKU ID</th><th>OKU No</th><th>Income</th><th>Qualification</th><th>Course Code</th><th>Status</th><th>Semester</th><th>CGP</th><th>CGPA</th><th>Total Credit</th><th>Enter Date</th><th>End Date</th><th>Convocation</th><th>End Session</th><th>Mode</th><th>Sponsor</th><th>Campus</th><th>Graduation</th><th>Citizen</th><th>Country</th><th>Date Created</th><th>Date Updated</th></tr>';

    foreach ( $data as $row ) {
        echo '<tr>';
        foreach ( $row as $column ) {
            echo '<td>' . htmlspecialchars( $column, ENT_QUOTES, 'UTF-8' ) . '</td>';
        }
        echo '</tr>';
    }
    echo '</table>';

    // Pagination links
    echo "<div style='margin-top:20px;'>";
    if ( $page > 1 ) {
        echo "<a href='?page=" . ( $page - 1 ) . "'>Previous</a> ";
    }
    if ( $page < $totalPages ) {
        echo "<a href='?page=" . ( $page + 1 ) . "'>Next</a>";
    }
    echo '</div>';

} catch ( PDOException $e ) {
    die( 'Connection failed: ' . $e->getMessage() );
}

// Close connection
$conn = null;
?>
