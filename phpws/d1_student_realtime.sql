-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 22, 2025 at 12:53 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kptrtdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `d1_student_realtime`
--

CREATE TABLE `d1_student_realtime` (
  `matrik_no` varchar(20) NOT NULL,
  `std_name` varchar(255) NOT NULL,
  `ic_no` varchar(20) DEFAULT NULL,
  `passport_no` varchar(20) DEFAULT NULL,
  `OKU_id` int(11) DEFAULT NULL,
  `OKU_no` varchar(20) DEFAULT NULL,
  `income` decimal(10,2) DEFAULT NULL,
  `intake_qualification_id` int(11) DEFAULT NULL,
  `course_code` varchar(20) NOT NULL,
  `status_id` int(11) DEFAULT NULL,
  `semester_no` int(11) DEFAULT NULL,
  `CGP` decimal(5,2) DEFAULT NULL,
  `CGPA` decimal(5,2) DEFAULT NULL,
  `total_credit_hour` int(11) DEFAULT NULL,
  `enter_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `convocation_date` date DEFAULT NULL,
  `end_session_id` int(11) DEFAULT NULL,
  `mode_id` int(11) DEFAULT NULL,
  `sponsor_id` int(11) DEFAULT NULL,
  `campus_code` varchar(20) DEFAULT NULL,
  `graduation_code` varchar(20) DEFAULT NULL,
  `citizen_id` int(11) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `date_create` timestamp NOT NULL DEFAULT current_timestamp(),
  `date_update` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `d1_student_realtime`
--
ALTER TABLE `d1_student_realtime`
  ADD PRIMARY KEY (`matrik_no`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
