-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 07, 2018 at 12:10 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tsf`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `ADMIN_ID` varchar(15) NOT NULL,
  `ADMIN_NAME` varchar(15) NOT NULL,
  `ADMIN_PHN` varchar(12) NOT NULL,
  `ADMIN_EMAIL` varchar(20) NOT NULL,
  `ADMIN_PASS` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`ADMIN_ID`, `ADMIN_NAME`, `ADMIN_PHN`, `ADMIN_EMAIL`, `ADMIN_PASS`) VALUES
('Admin', 'AIUB', '0095463', 'AIUB@gmail.com', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `consulting`
--

CREATE TABLE `consulting` (
  `ID` int(3) NOT NULL,
  `DAY` varchar(10) NOT NULL,
  `SAMI_ID` varchar(2) NOT NULL,
  `TEC_ID` varchar(10) NOT NULL,
  `CONS_START` decimal(5,2) NOT NULL,
  `CONS_END` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `consulting`
--

INSERT INTO `consulting` (`ID`, `DAY`, `SAMI_ID`, `TEC_ID`, `CONS_START`, `CONS_END`) VALUES
(1, 'Sunday', '2', '100001', '10.00', '12.30'),
(2, 'Thursday', '2', '100001', '10.00', '12.00'),
(3, 'Sunday', '1', '100001', '15.00', '18.00'),
(4, 'Sunday', '2', '100001', '14.00', '15.00'),
(5, 'Tuesday', '2', '100001', '15.00', '15.30'),
(6, 'Sunday', '2', '100001', '9.00', '10.00'),
(7, 'Tuesday', '2', '100001', '16.30', '18.00'),
(8, 'Thursday', '2', '100001', '12.00', '16.00'),
(9, 'Sunday', '2', '100001', '15.30', '16.30'),
(10, 'Monday', '1', '100001', '10.30', '11.30'),
(11, 'Sunday', '1', '100001', '8.00', '8.00');

-- --------------------------------------------------------

--
-- Table structure for table `cor_by_sami`
--

CREATE TABLE `cor_by_sami` (
  `ID` int(5) NOT NULL,
  `SAMI_ID` int(3) NOT NULL,
  `SEC_ID` int(3) NOT NULL,
  `TEC_ID` varchar(10) NOT NULL,
  `DAY1` varchar(10) NOT NULL,
  `DAY2` varchar(10) NOT NULL,
  `START_AT` decimal(5,2) DEFAULT NULL,
  `END_TIME` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cor_by_sami`
--

INSERT INTO `cor_by_sami` (`ID`, `SAMI_ID`, `SEC_ID`, `TEC_ID`, `DAY1`, `DAY2`, `START_AT`, `END_TIME`) VALUES
(7, 1, 5, '100003', 'Sunday', 'Tuesday', '8.00', '9.50'),
(8, 2, 1, '100002', 'Sunday', 'Monday', '8.00', '10.00'),
(11, 2, 7, '100003', 'Sunday', 'Tuesday', '8.00', '9.30'),
(12, 1, 7, '100005', 'Sunday', 'Tuesday', '20.30', '22.00'),
(13, 2, 5, '100005', 'Sunday', 'Tuesday', '8.31', '10.00'),
(14, 1, 1, '100001', 'Sunday', 'Tuesday', '10.00', '12.00'),
(16, 2, 4, '100001', 'Monday', 'Wenesday', '8.00', '10.00'),
(18, 2, 11, '100002', 'Sunday', 'Tuesday', '8.00', '10.00'),
(19, 2, 8, '100003', 'Sunday', 'Tuesday', '8.00', '9.30'),
(20, 2, 9, '100003', 'Sunday', 'Tuesday', '11.30', '13.00'),
(23, 2, 2, '100004', 'Sunday', 'Tuesday', '8.00', '10.00'),
(24, 2, 10, '100001', 'Monday', 'Wednesday', '10.00', '12.00'),
(25, 2, 12, '100001', 'Monday', 'Tuesday', '9.30', '11.30');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `ID` int(5) NOT NULL,
  `C_NAME` varchar(20) NOT NULL,
  `DEP_NAME` varchar(35) NOT NULL,
  `COR_TYPE` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`ID`, `C_NAME`, `DEP_NAME`, `COR_TYPE`) VALUES
(1, 'Java', 'Science &Information Tecnology', 'CS_Theory'),
(2, 'Java (LAB)', 'Science &Information Tecnology', 'Lab'),
(4, 'Intregration Calcula', 'Engineering', 'Theory'),
(5, 'Database', 'Science &Information Tecnology', 'CS_Theory'),
(6, 'Accounting', 'Business Administrat', 'Theory'),
(7, 'English', 'Art & Social Science', 'Theory');

-- --------------------------------------------------------

--
-- Table structure for table `course_type`
--

CREATE TABLE `course_type` (
  `COR_TYPE_ID` int(11) NOT NULL,
  `COR_TYPE` varchar(10) NOT NULL,
  `COR_DURATION` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course_type`
--

INSERT INTO `course_type` (`COR_TYPE_ID`, `COR_TYPE`, `COR_DURATION`) VALUES
(1, 'Theory', '1.30'),
(2, 'Lab', '3'),
(3, 'CS_Theory', '2');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `ID` int(2) NOT NULL,
  `DEP_NAME` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`ID`, `DEP_NAME`) VALUES
(1, 'Science &Information Tecnology'),
(2, 'Business Administrator'),
(3, 'Engineering'),
(4, 'Art & Social Science');

-- --------------------------------------------------------

--
-- Table structure for table `samester`
--

CREATE TABLE `samester` (
  `SAMI_ID` int(11) NOT NULL,
  `SAMI_NAME` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `samester`
--

INSERT INTO `samester` (`SAMI_ID`, `SAMI_NAME`) VALUES
(1, 'Fall 2016-17'),
(2, 'Spring 2016-17');

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE `section` (
  `ID` int(11) NOT NULL,
  `COR_NAME` varchar(25) NOT NULL,
  `SEC_NAME` varchar(5) NOT NULL,
  `ROOM_NO` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `section`
--

INSERT INTO `section` (`ID`, `COR_NAME`, `SEC_NAME`, `ROOM_NO`) VALUES
(1, 'Java', 'A', '423'),
(2, 'Java', 'B', '422'),
(3, 'Java (LAB)', 'A', 'CL 9'),
(5, 'Intregration Calculas', 'B', '7043'),
(6, 'Java (LAB)', 'F', '35'),
(7, 'Intregration Calculas', 'A', '754'),
(8, 'Intregration Calculas', 'C', '742'),
(9, 'Intregration Calculas', 'D', '432'),
(12, 'Java', 'G', '423'),
(13, 'Java', 'W', '456');

-- --------------------------------------------------------

--
-- Table structure for table `status_type`
--

CREATE TABLE `status_type` (
  `ID` int(1) NOT NULL,
  `S_VALUE` varchar(1) NOT NULL,
  `STATUS` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status_type`
--

INSERT INTO `status_type` (`ID`, `S_VALUE`, `STATUS`) VALUES
(1, '0', 'Available'),
(2, '1', 'Not Available'),
(3, '2', 'Busy'),
(4, '3', 'Meeting'),
(5, '4', 'Break');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `ID` int(5) NOT NULL,
  `S_NAME` varchar(20) DEFAULT NULL,
  `S_DEP` varchar(35) DEFAULT NULL,
  `S_PASS` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`ID`, `S_NAME`, `S_DEP`, `S_PASS`) VALUES
(1, 'Fahad', 'Science &Information Tecnology', '12345'),
(5, 'dadd', 'Science &Information Tecnology', '12345'),
(6, 'Nisa', 'Science &Information Tecnology', '12345'),
(7, 'Karim', 'Art & Social Science', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `ID` varchar(10) NOT NULL,
  `TEC_NAME` varchar(50) NOT NULL,
  `TEC_NUM` varchar(50) NOT NULL,
  `TEC_EMAIL` varchar(50) NOT NULL,
  `TEC_DEP` varchar(50) NOT NULL,
  `TEC_POSI` varchar(50) NOT NULL,
  `ROOM_NO` varchar(5) NOT NULL,
  `TEC_PASS` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`ID`, `TEC_NAME`, `TEC_NUM`, `TEC_EMAIL`, `TEC_DEP`, `TEC_POSI`, `ROOM_NO`, `TEC_PASS`) VALUES
('100001', 'Estesham Chowdhury', '18332923', 'Estesham@aiub.com', 'Science &Information Tecnology', 'Assistant', '401', '12345'),
('100002', 'Jahid Hasan', '01982736453', 'Jahid@aiub.edu', 'Science &Information Tecnology', 'Assistant', '421', '12345'),
('100003', 'Aysha siddiqie', '019283735', 'Aysha@aiub.edu', 'Engineering', 'Senior', '667', '12345'),
('100004', 'Jarin', '01823645463', 'jarin@aiub.com', 'Science &Information Tecnology', 'Assistant', '', '12345'),
('100005', 'Jarin', '01928364553', 'jarin@gmail.com', 'Engineering', 'assistant', '', '12345'),
('100006', 'Hafiz', '0192837645', 'hafi@aiub.com', 'Business Administrator', 'Assistant', '724', '12345'),
('100007', 'Bijan', '0199836217', 'Bijan@aiub.edu', 'Art & Social Science', 'Assistant', '648', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `tec_notice`
--

CREATE TABLE `tec_notice` (
  `ID` int(3) NOT NULL,
  `TEC_ID` varchar(10) NOT NULL,
  `DATE` varchar(20) NOT NULL,
  `TIME` varchar(10) NOT NULL,
  `NOTICE` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tec_notice`
--

INSERT INTO `tec_notice` (`ID`, `TEC_ID`, `DATE`, `TIME`, `NOTICE`) VALUES
(2, '100005', '2017-04-28', '', 'ID card Dao'),
(5, '100001', '2017-05-01', '12:40 PM', 'project submission start at 11 am'),
(6, '100001', '2017-05-03', '10:25 AM', 'project submission start at 11 am'),
(7, '100001', '2017-05-03', '14:12 PM', 'project submission start at 11 am'),
(10, '100001', '2017-05-03', '20:54 PM', 'bbbb'),
(11, '100001', '2017-05-05', '22:58 PM', 'grade uploded'),
(12, '100001', '2017-05-09', '10:20 AM', 'grade  hhhh'),
(13, '100001', '2017-05-09', '10:20 AM', 'grade  hhhh'),
(17, '100001', '2017-07-05', '21:07 PM', 'grade up'),
(18, '100001', '2017-07-05', '21:07 PM', 'grade up'),
(19, '100001', '2017-07-27', '10:19 AM', 'grade upjhaad'),
(20, '100001', '2017-08-02', '16:18 PM', 'ghvgvgbghgg'),
(21, '100001', '2017-08-25', '15:49 PM', '2652565126556'),
(22, '100001', '2017-11-19', '20:03 PM', 'kasjbhdjkasbhd'),
(23, '100001', '2017-11-19', '20:03 PM', 'kasjbhdjkasbhd'),
(24, '100001', '2017-11-19', '22:10 PM', 'Quiz Tomorrow'),
(25, '100001', '2017-12-13', '12:59 PM', 'Quiz Tomorrow'),
(26, '100001', '2018-02-12', '22:25 PM', ' dbgsahdgkas'),
(27, '100001', '2018-02-12', '22:25 PM', ' dbgsahdgkas'),
(28, '100001', '2018-04-20', '19:00 PM', ' kjhfbvksdhngbvkjdsfs');

-- --------------------------------------------------------

--
-- Table structure for table `tec_status`
--

CREATE TABLE `tec_status` (
  `ID` int(3) NOT NULL,
  `TEC_ID` varchar(10) NOT NULL,
  `DATE` varchar(10) NOT NULL,
  `STATUS` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tec_status`
--

INSERT INTO `tec_status` (`ID`, `TEC_ID`, `DATE`, `STATUS`) VALUES
(2, '100001', '2018-04-20', '2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`ADMIN_ID`);

--
-- Indexes for table `consulting`
--
ALTER TABLE `consulting`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `cor_by_sami`
--
ALTER TABLE `cor_by_sami`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `C_NAME` (`C_NAME`);

--
-- Indexes for table `course_type`
--
ALTER TABLE `course_type`
  ADD PRIMARY KEY (`COR_TYPE_ID`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `samester`
--
ALTER TABLE `samester`
  ADD PRIMARY KEY (`SAMI_ID`),
  ADD UNIQUE KEY `SAMI_NAME` (`SAMI_NAME`);

--
-- Indexes for table `section`
--
ALTER TABLE `section`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `status_type`
--
ALTER TABLE `status_type`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tec_notice`
--
ALTER TABLE `tec_notice`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tec_status`
--
ALTER TABLE `tec_status`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consulting`
--
ALTER TABLE `consulting`
  MODIFY `ID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `cor_by_sami`
--
ALTER TABLE `cor_by_sami`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `course_type`
--
ALTER TABLE `course_type`
  MODIFY `COR_TYPE_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `ID` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `samester`
--
ALTER TABLE `samester`
  MODIFY `SAMI_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `section`
--
ALTER TABLE `section`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `status_type`
--
ALTER TABLE `status_type`
  MODIFY `ID` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `tec_notice`
--
ALTER TABLE `tec_notice`
  MODIFY `ID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `tec_status`
--
ALTER TABLE `tec_status`
  MODIFY `ID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
