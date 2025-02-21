<?php
session_start();

$progress = isset( $_SESSION[ 'progress' ] ) ? $_SESSION[ 'progress' ] : 0;
$elapsed_time = isset( $_SESSION[ 'elapsed_time' ] ) ? $_SESSION[ 'elapsed_time' ] : 0;

echo json_encode( [
    'progress' => $progress,
    'elapsed_time' => $elapsed_time
] );
?>
