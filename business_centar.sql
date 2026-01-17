-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 17, 2026 at 01:25 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `business_centar`
--

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `reservation_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `slot_id` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `reservation_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `series_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`reservation_id`, `user_id`, `slot_id`, `status`, `reservation_date`, `series_id`) VALUES
(2, 1, 50, 'ACTIVE', '2026-01-09 17:54:56', NULL),
(3, 1, 4, 'ACTIVE', '2026-01-13 11:09:26', 1),
(4, 1, 20, 'ACTIVE', '2026-01-13 11:09:26', 1),
(5, 1, 62, 'ACTIVE', '2026-01-13 11:09:26', 1),
(7, 2, 17, 'ACTIVE', '2026-01-15 18:40:10', 2),
(8, 2, 63, 'ACTIVE', '2026-01-15 18:40:10', 2),
(9, 2, 64, 'ACTIVE', '2026-01-15 18:40:10', 2),
(10, 2, 65, 'ACTIVE', '2026-01-15 18:40:10', 2),
(11, 2, 66, 'ACTIVE', '2026-01-15 18:40:10', 2),
(12, 2, 67, 'ACTIVE', '2026-01-15 18:40:39', 3),
(13, 2, 3, 'ACTIVE', '2026-01-15 18:40:39', 3),
(14, 2, 19, 'ACTIVE', '2026-01-15 18:40:39', 3),
(15, 2, 68, 'ACTIVE', '2026-01-15 18:40:39', 3),
(16, 2, 69, 'ACTIVE', '2026-01-15 18:40:39', 3),
(17, 2, 70, 'ACTIVE', '2026-01-15 18:40:39', 3),
(18, 2, 71, 'ACTIVE', '2026-01-15 18:40:39', 3),
(19, 2, 72, 'ACTIVE', '2026-01-15 18:40:39', 3),
(20, 2, 73, 'ACTIVE', '2026-01-15 18:40:39', 3),
(21, 2, 74, 'ACTIVE', '2026-01-15 18:40:39', 3),
(22, 2, 75, 'ACTIVE', '2026-01-15 18:40:39', 3),
(23, 2, 76, 'ACTIVE', '2026-01-15 18:40:39', 3),
(24, 2, 77, 'ACTIVE', '2026-01-15 18:40:39', 3),
(25, 2, 78, 'ACTIVE', '2026-01-15 18:40:39', 3),
(26, 2, 79, 'ACTIVE', '2026-01-15 18:40:39', 3),
(27, 2, 80, 'ACTIVE', '2026-01-15 18:40:39', 3),
(28, 2, 81, 'ACTIVE', '2026-01-15 18:40:39', 3),
(29, 2, 82, 'ACTIVE', '2026-01-15 18:40:39', 3),
(30, 2, 83, 'ACTIVE', '2026-01-15 18:40:39', 3),
(31, 2, 84, 'ACTIVE', '2026-01-15 18:40:39', 3),
(32, 2, 85, 'ACTIVE', '2026-01-15 18:40:39', 3),
(33, 2, 86, 'ACTIVE', '2026-01-15 18:40:39', 3),
(34, 2, 87, 'ACTIVE', '2026-01-15 18:40:39', 3),
(35, 2, 88, 'ACTIVE', '2026-01-15 18:40:39', 3),
(36, 2, 89, 'ACTIVE', '2026-01-15 18:40:39', 3),
(37, 2, 90, 'ACTIVE', '2026-01-15 18:40:39', 3),
(38, 2, 91, 'ACTIVE', '2026-01-15 18:40:39', 3),
(39, 2, 92, 'ACTIVE', '2026-01-15 18:40:39', 3),
(40, 2, 93, 'ACTIVE', '2026-01-15 18:40:39', 3),
(41, 2, 94, 'ACTIVE', '2026-01-15 18:40:39', 3),
(42, 2, 95, 'ACTIVE', '2026-01-15 18:40:39', 3),
(43, 2, 96, 'ACTIVE', '2026-01-15 18:40:39', 3),
(44, 2, 97, 'ACTIVE', '2026-01-15 18:40:39', 3),
(45, 2, 98, 'ACTIVE', '2026-01-15 18:40:39', 3),
(46, 2, 99, 'ACTIVE', '2026-01-15 18:40:39', 3);

-- --------------------------------------------------------

--
-- Table structure for table `reservation_series`
--

CREATE TABLE `reservation_series` (
  `series_id` int(11) NOT NULL,
  `type` enum('daily','weekly','monthly','custom') NOT NULL,
  `end_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservation_series`
--

INSERT INTO `reservation_series` (`series_id`, `type`, `end_date`) VALUES
(1, 'daily', '2026-01-10'),
(2, 'weekly', '2026-02-07'),
(3, 'daily', '2026-02-10');

-- --------------------------------------------------------

--
-- Table structure for table `resources`
--

CREATE TABLE `resources` (
  `resource_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `type` varchar(50) NOT NULL,
  `working_hours` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `resources`
--

INSERT INTO `resources` (`resource_id`, `name`, `type`, `working_hours`) VALUES
(1, 'Sala 1', 'Konferencijska sala', '08:00-16:00'),
(2, 'Sala 2', 'Sala za sastanke', '09:00-17:00'),
(3, 'Sala 3', 'Konferencijska sala', '08:30-17:30'),
(4, 'Oprema 1', 'Projektor', '08:00-18:00'),
(5, 'Oprema 2', 'Laptop', '09:00-17:00');

-- --------------------------------------------------------

--
-- Table structure for table `slots`
--

CREATE TABLE `slots` (
  `slot_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `slots`
--

INSERT INTO `slots` (`slot_id`, `resource_id`, `date`, `time`) VALUES
(1, 1, '2026-01-08', '08:00:00'),
(2, 1, '2026-01-08', '09:00:00'),
(3, 1, '2026-01-08', '10:00:00'),
(4, 1, '2026-01-08', '11:00:00'),
(5, 1, '2026-01-08', '12:00:00'),
(6, 1, '2026-01-08', '13:00:00'),
(7, 1, '2026-01-08', '14:00:00'),
(8, 1, '2026-01-08', '15:00:00'),
(9, 1, '2026-08-01', '08:00:00'),
(10, 1, '2026-08-01', '09:00:00'),
(11, 1, '2026-08-01', '10:00:00'),
(12, 1, '2026-08-01', '11:00:00'),
(13, 1, '2026-08-01', '12:00:00'),
(14, 1, '2026-08-01', '13:00:00'),
(15, 1, '2026-08-01', '14:00:00'),
(16, 1, '2026-08-01', '15:00:00'),
(17, 1, '2026-01-09', '08:00:00'),
(18, 1, '2026-01-09', '09:00:00'),
(19, 1, '2026-01-09', '10:00:00'),
(20, 1, '2026-01-09', '11:00:00'),
(21, 1, '2026-01-09', '12:00:00'),
(22, 1, '2026-01-09', '13:00:00'),
(23, 1, '2026-01-09', '14:00:00'),
(24, 1, '2026-01-09', '15:00:00'),
(25, 2, '2026-01-09', '09:00:00'),
(26, 2, '2026-01-09', '10:00:00'),
(27, 2, '2026-01-09', '11:00:00'),
(28, 2, '2026-01-09', '12:00:00'),
(29, 2, '2026-01-09', '13:00:00'),
(30, 2, '2026-01-09', '14:00:00'),
(31, 2, '2026-01-09', '15:00:00'),
(32, 2, '2026-01-09', '16:00:00'),
(33, 3, '2026-01-10', '08:00:00'),
(34, 3, '2026-01-10', '09:00:00'),
(35, 3, '2026-01-10', '10:00:00'),
(36, 3, '2026-01-10', '11:00:00'),
(37, 3, '2026-01-10', '12:00:00'),
(38, 3, '2026-01-10', '13:00:00'),
(39, 3, '2026-01-10', '14:00:00'),
(40, 3, '2026-01-10', '15:00:00'),
(41, 3, '2026-01-10', '16:00:00'),
(42, 4, '2026-01-10', '08:00:00'),
(43, 4, '2026-01-10', '09:00:00'),
(44, 4, '2026-01-10', '10:00:00'),
(45, 4, '2026-01-10', '11:00:00'),
(46, 4, '2026-01-10', '12:00:00'),
(47, 4, '2026-01-10', '13:00:00'),
(48, 4, '2026-01-10', '14:00:00'),
(49, 4, '2026-01-10', '15:00:00'),
(50, 4, '2026-01-10', '16:00:00'),
(51, 4, '2026-01-10', '17:00:00'),
(52, 4, '2026-01-11', '08:00:00'),
(53, 4, '2026-01-11', '09:00:00'),
(54, 4, '2026-01-11', '10:00:00'),
(55, 4, '2026-01-11', '11:00:00'),
(56, 4, '2026-01-11', '12:00:00'),
(57, 4, '2026-01-11', '13:00:00'),
(58, 4, '2026-01-11', '14:00:00'),
(59, 4, '2026-01-11', '15:00:00'),
(60, 4, '2026-01-11', '16:00:00'),
(61, 4, '2026-01-11', '17:00:00'),
(62, 1, '2026-01-10', '11:00:00'),
(63, 1, '2026-01-16', '08:00:00'),
(64, 1, '2026-01-23', '08:00:00'),
(65, 1, '2026-01-30', '08:00:00'),
(66, 1, '2026-02-06', '08:00:00'),
(67, 1, '2026-01-07', '10:00:00'),
(68, 1, '2026-01-10', '10:00:00'),
(69, 1, '2026-01-11', '10:00:00'),
(70, 1, '2026-01-12', '10:00:00'),
(71, 1, '2026-01-13', '10:00:00'),
(72, 1, '2026-01-14', '10:00:00'),
(73, 1, '2026-01-15', '10:00:00'),
(74, 1, '2026-01-16', '10:00:00'),
(75, 1, '2026-01-17', '10:00:00'),
(76, 1, '2026-01-18', '10:00:00'),
(77, 1, '2026-01-19', '10:00:00'),
(78, 1, '2026-01-20', '10:00:00'),
(79, 1, '2026-01-21', '10:00:00'),
(80, 1, '2026-01-22', '10:00:00'),
(81, 1, '2026-01-23', '10:00:00'),
(82, 1, '2026-01-24', '10:00:00'),
(83, 1, '2026-01-25', '10:00:00'),
(84, 1, '2026-01-26', '10:00:00'),
(85, 1, '2026-01-27', '10:00:00'),
(86, 1, '2026-01-28', '10:00:00'),
(87, 1, '2026-01-29', '10:00:00'),
(88, 1, '2026-01-30', '10:00:00'),
(89, 1, '2026-01-31', '10:00:00'),
(90, 1, '2026-02-01', '10:00:00'),
(91, 1, '2026-02-02', '10:00:00'),
(92, 1, '2026-02-03', '10:00:00'),
(93, 1, '2026-02-04', '10:00:00'),
(94, 1, '2026-02-05', '10:00:00'),
(95, 1, '2026-02-06', '10:00:00'),
(96, 1, '2026-02-07', '10:00:00'),
(97, 1, '2026-02-08', '10:00:00'),
(98, 1, '2026-02-09', '10:00:00'),
(99, 1, '2026-02-10', '10:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `full_name`) VALUES
(1, 'testuser', '1234', 'Test User'),
(2, 'jelena', 'jelena123', 'JelenaD'),
(3, 'jelena3', 'jelena123', 'Jelena');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`reservation_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `slot_id` (`slot_id`),
  ADD KEY `fk_res_series` (`series_id`);

--
-- Indexes for table `reservation_series`
--
ALTER TABLE `reservation_series`
  ADD PRIMARY KEY (`series_id`);

--
-- Indexes for table `resources`
--
ALTER TABLE `resources`
  ADD PRIMARY KEY (`resource_id`);

--
-- Indexes for table `slots`
--
ALTER TABLE `slots`
  ADD PRIMARY KEY (`slot_id`),
  ADD KEY `resource_id` (`resource_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `reservation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `reservation_series`
--
ALTER TABLE `reservation_series`
  MODIFY `series_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `resources`
--
ALTER TABLE `resources`
  MODIFY `resource_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `slots`
--
ALTER TABLE `slots`
  MODIFY `slot_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `fk_res_series` FOREIGN KEY (`series_id`) REFERENCES `reservation_series` (`series_id`),
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`slot_id`) REFERENCES `slots` (`slot_id`);

--
-- Constraints for table `slots`
--
ALTER TABLE `slots`
  ADD CONSTRAINT `slots_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `resources` (`resource_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
