-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2023 at 07:38 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tour_booking_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `carttb`
--

CREATE TABLE `carttb` (
  `cartid` int(20) NOT NULL,
  `tourid` int(20) NOT NULL,
  `username` varchar(100) NOT NULL,
  `payment_status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `complainttb`
--

CREATE TABLE `complainttb` (
  `complaintid` int(20) NOT NULL,
  `username` varchar(100) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `message` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `complainttb`
--

INSERT INTO `complainttb` (`complaintid`, `username`, `subject`, `message`) VALUES
(43, 'yug_1112', 'About managers', 'Sometimes managers are not behaving good');

-- --------------------------------------------------------

--
-- Table structure for table `feedbacktb`
--

CREATE TABLE `feedbacktb` (
  `feedbackid` int(20) NOT NULL,
  `username` varchar(100) NOT NULL,
  `rating` varchar(10) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `message` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `feedbacktb`
--

INSERT INTO `feedbacktb` (`feedbackid`, `username`, `rating`, `subject`, `message`) VALUES
(34, 'yug_1112', 'four', 'Something', 'Something abou the service'),
(35, 'yug_1112', 'three', 'adsdsd', 'dsf');

-- --------------------------------------------------------

--
-- Table structure for table `gallerytb`
--

CREATE TABLE `gallerytb` (
  `galleryid` int(20) NOT NULL,
  `tour_name` varchar(30) NOT NULL,
  `place_name` varchar(30) NOT NULL,
  `image_url` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `gallerytb`
--

INSERT INTO `gallerytb` (`galleryid`, `tour_name`, `place_name`, `image_url`) VALUES
(1, 'Kashmir', 'Gulmarg', '/kashmir/gulmarg.jpg'),
(2, 'Kashmir', 'Chasmesahi', '/kashmir/chasmesahi.jpg'),
(3, 'Kashmir', 'Pahalham', '/kashmir/pahalgam.jpg'),
(4, 'Kashmir', 'Sonamarg', '/kashmir/sonamarg.jpg'),
(5, 'Kashmir', 'Srinagar', '/kashmir/srinagar.jpg'),
(6, 'Kashmir', 'Tulip Garden', '/kashmir/tulipgarden.jpg'),
(7, 'Kashmir', 'Zero Point', '/kashmir/zeropoint.jpg'),
(8, 'Himachal', 'Hidimba Temple', '/hp/hidimba.jpg'),
(9, 'Himachal', 'Khajjiar', '/hp/khajjiar.jpg'),
(10, 'Himachal', 'Rohtang', '/hp/rohtang.jpg'),
(11, 'Himachal', 'Shimla Church', '/hp/shimlachurch.jpg'),
(12, 'Himachal', 'Dharamshala Stadium', '/hp/stadium.jpg'),
(13, 'Himachal', 'Hanuman Temple', '/hp/temple.jpg'),
(14, 'Goa', 'Aguada Jail', '/goa/aguadajail.jpg'),
(15, 'Goa', 'Anjuna Beach', '/goa/beach.jpg'),
(16, 'Goa', 'Goa Church', '/goa/church.jpg'),
(17, 'Goa', 'Bagha Beach', '/goa/goabeach.jpg'),
(18, 'Goa', 'Jacks Resort', '/goa/resort.jpg'),
(19, 'Kerala', 'Houseboat', '/kerala/houseboat.jpeg'),
(20, 'Kerala', 'Kanyakumari', '/kerala/kanyakumari.jpg'),
(21, 'Kerala', 'Munnar', '/kerala/munnar.jpg'),
(22, 'Kerala', 'Rameshvaram Temple', '/kerala/rameshvaram.jpg'),
(23, 'Kerala', 'Marmala Waterfall', '/kerala/waterfall.jpg'),
(24, 'Kerala', 'Wayanad', '/kerala/wayanad.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(63);

-- --------------------------------------------------------

--
-- Table structure for table `historytb`
--

CREATE TABLE `historytb` (
  `historyid` int(20) NOT NULL,
  `tourid` int(20) NOT NULL,
  `username` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `historytb`
--

INSERT INTO `historytb` (`historyid`, `tourid`, `username`) VALUES
(55, 40, 'yug_1112');

-- --------------------------------------------------------

--
-- Table structure for table `persontb`
--

CREATE TABLE `persontb` (
  `personid` int(20) NOT NULL,
  `tourid` int(20) NOT NULL,
  `username` varchar(100) NOT NULL,
  `fname` varchar(30) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `email` varchar(40) NOT NULL,
  `phoneno` varchar(15) NOT NULL,
  `dob` date NOT NULL,
  `gender` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `persontb`
--

INSERT INTO `persontb` (`personid`, `tourid`, `username`, `fname`, `lname`, `email`, `phoneno`, `dob`, `gender`) VALUES
(38, 37, 'yug_1112', 'Kushal', 'Majiwala', 'kushal@gmail.com', '9107876567', '2023-06-09', 'male'),
(39, 37, 'yug_1112', 'Yug', 'Majiwala', 'yug@gmail.com', '878987656', '2023-06-22', 'male'),
(41, 40, 'yug_1112', 'Kushal', 'Majiwala', 'kushal@gmail.com', '9879767867', '2023-06-24', 'male'),
(42, 40, 'yug_1112', 'Meghna', 'Majiwala', 'meghna@gmail.com', '8787678987', '2023-06-24', 'female'),
(48, 47, 'yug_1112', 'Meghna', 'Majiwala', 'meghna@gmail.com', '9878987689', '2023-06-24', 'female');

-- --------------------------------------------------------

--
-- Table structure for table `projectgroups`
--

CREATE TABLE `projectgroups` (
  `groupname` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `projectgroups`
--

INSERT INTO `projectgroups` (`groupname`, `username`) VALUES
('admin', 'kushal_1212'),
('admin', 'samarth_dastanwala'),
('user', 'meghna_majiwala'),
('user', 'raj_patel'),
('user', 'yash_patel'),
('user', 'yug_1112');

-- --------------------------------------------------------

--
-- Table structure for table `tourtb`
--

CREATE TABLE `tourtb` (
  `tourid` int(20) NOT NULL,
  `tourmasterid` int(20) NOT NULL,
  `username` varchar(100) NOT NULL,
  `payment_method` varchar(10) NOT NULL,
  `payment_status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tourtb`
--

INSERT INTO `tourtb` (`tourid`, `tourmasterid`, `username`, `payment_method`, `payment_status`) VALUES
(37, 4, 'yug_1112', 'cash', 'remaining'),
(40, 2, 'yug_1112', 'online', 'done'),
(47, 5, 'yug_1112', 'online', 'remaining');

-- --------------------------------------------------------

--
-- Table structure for table `tour_mastertb`
--

CREATE TABLE `tour_mastertb` (
  `tourmasterid` int(20) NOT NULL,
  `tour_title` varchar(30) NOT NULL,
  `tour_pic` varchar(100) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `journey_begin_time` time NOT NULL,
  `per_person_price` int(30) NOT NULL,
  `pickup_address` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tour_mastertb`
--

INSERT INTO `tour_mastertb` (`tourmasterid`, `tour_title`, `tour_pic`, `start_date`, `end_date`, `journey_begin_time`, `per_person_price`, `pickup_address`) VALUES
(2, 'Goa', '/goa/goabeach.jpg', '2023-03-08', '2023-03-15', '20:15:34', 15000, 'Citylight, Surat'),
(4, 'Himachal', '/hp/khajjiar.jpg', '2023-03-05', '2023-03-14', '22:30:34', 16000, 'Adajan, Surat'),
(5, 'Kashmir', '/kashmir/sonamarg.jpg', '2023-04-12', '2023-04-24', '23:00:34', 20000, 'Adajan, Surat');

-- --------------------------------------------------------

--
-- Table structure for table `tour_placetb`
--

CREATE TABLE `tour_placetb` (
  `tourplaceid` int(20) NOT NULL,
  `tourmasterid` int(20) NOT NULL,
  `place_name` varchar(40) NOT NULL,
  `place_city` varchar(50) NOT NULL,
  `place_state` varchar(50) NOT NULL,
  `place_description` varchar(200) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tour_placetb`
--

INSERT INTO `tour_placetb` (`tourplaceid`, `tourmasterid`, `place_name`, `place_city`, `place_state`, `place_description`, `start_date`, `end_date`) VALUES
(8, 2, 'cruise', 'Panji', 'Goa', 'This is the largest cruise for all the tourists which everyone can enjoy.', '2023-04-03', '2023-04-10'),
(9, 2, 'Central Jail', 'Panji', 'Goa', 'This is the main and central jail of the entire state of goa.', '2023-04-03', '2023-04-10'),
(10, 2, 'Goa Church', 'Panji', 'Goa', 'This is the central and the largest church of the state of goa', '2023-05-11', '2023-05-12'),
(11, 2, 'Bagha Beach', 'Panji', 'Goa', 'This beach is the best and most visited beach of goa.', '2023-05-13', '2023-05-15'),
(12, 2, 'Jacks Resort', 'Panji', 'Goa', 'This is the largest and most visited Resort in goa. All the charges of the resort is included in the package', '2023-05-16', '2023-05-18'),
(13, 4, 'Hidimba Temple', 'Manali', 'Himachal', 'This temple is placed in Manali and lot of people visit this temple every year', '2023-05-02', '2023-05-02'),
(14, 4, 'Shimla Church', 'Shimla', 'Himachal', 'This is the central church of the Shimla city. Lot of tourists visit this church every year', '2023-05-03', '2023-05-04'),
(15, 4, 'Rohtang', 'Manali', 'Himachal', 'We can visit this place to enjoy snow and snow fall is also expected here', '2023-05-06', '2023-05-07'),
(16, 4, 'Khajjiar', 'Dalhousie', 'Himachal', 'This is the place where so many movies have been shooted', '2023-05-09', '2023-05-12'),
(17, 4, 'Dharamshala Stadium', 'Dharamshala', 'Himachal', 'This is the cricket stadium which is at the highest peak of the country.', '2023-05-11', '2023-05-13'),
(18, 5, 'Gulmarg', 'gulmarg', 'Kashmir', 'This is the place where we can enjoy the gondola rope way.', '2023-05-16', '2023-05-17'),
(19, 5, 'Sonamarg', 'Sonamarg', 'Kashmir', 'This is the place where we are expecting to enjoy snow fall.', '2023-05-18', '2023-05-19'),
(20, 5, 'Dal Lake', 'Srinagar', 'Kashmir', 'This is the largest lake of Kashmir and we can enjoy living in the boat house.', '2023-05-19', '2023-05-21'),
(21, 5, 'Zero Point', 'Zojjila', 'Kashmir', 'This is the place which is over 5000 meter in height. We can enjoy snow in this place', '2023-05-20', '2023-05-22'),
(22, 5, 'Tulip Garden', 'Srinagar', 'Kashmir', 'This is the largest flower garden in Kashmir.', '2023-05-23', '2023-05-25'),
(56, 4, 'Kullu', 'Kullu', 'Himachal', 'People can enjoy river rafting in this place', '2023-06-22', '2023-06-23'),
(57, 5, 'Chandavadi', 'Pahalgam', 'Kashmir', 'This place is the starting point of the amarnath yatra', '2023-06-15', '2023-06-17'),
(58, 5, 'Betab Valley', 'Pahalgam', 'Kashmir', 'This is the most famous Valley where Movies shooting take place.', '2023-06-14', '2023-06-15'),
(59, 5, 'Vaishnodevi Temple', 'Katra', 'Kashmir', 'This is one of the most famous and visited temples in india.', '2023-06-23', '2023-06-24'),
(60, 5, 'Jammu Sight scene', 'Jammu', 'Kashmir', 'This is the place where people can enjoy in many gardens', '2023-06-15', '2023-06-16'),
(61, 4, 'New Kufri', 'Shimla', 'Himachal', 'This is the place where people can enjoy rides in the theme park.', '2023-06-23', '2023-06-24'),
(62, 5, 'Shankaracharya temple', 'Srinagar', 'Kashmir', 'This is one of the most visited temple in srinagar located on the mountain.', '2023-06-15', '2023-06-16');

-- --------------------------------------------------------

--
-- Table structure for table `usertb`
--

CREATE TABLE `usertb` (
  `username` varchar(100) NOT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usertb`
--

INSERT INTO `usertb` (`username`, `fname`, `lname`, `email`, `password`) VALUES
('kushal_1212', 'Kushal', 'Majiwala', 'kushalmajiwala1212@gmail.com', 'PBKDF2WithHmacSHA256:2048:gpxGEXMlFM5U3rsXeMZVZfmoY2bNnt0NHDBXui3Racw=:EfEGetybB5l7Zq2Z5GGhl9WvfjXMwMlY2H83+0kAaFU='),
('meghna_majiwala', 'Meghna', 'Majiwala', 'meghna@gmail.com', 'PBKDF2WithHmacSHA256:2048:Is93ZrQzSN/54JLXA6PWrMSs3zGt7NH+3CM/NDg/qxs=:vzmMlcbLTmE7kSSK4x4ZLjH7OIhrfigLhphiDRSjbpc='),
('raj_patel', 'Raj', 'Patel', 'raj@gmail.com', 'PBKDF2WithHmacSHA256:2048:p0jyIb7FwQzNrD6QwYCvKHQk04qz3il79cWsmSWhAKY=:XR/Ce31rsLSGJaKDJTBvEhtCc569Ge8ee4NorwMsg3A='),
('samarth_dastanwala', 'Samarth', 'Dastanwala', 'samarth@gmail.com', 'PBKDF2WithHmacSHA256:2048:QNe5zERKouLreRKo1INQZRMX9PipqsXJ7JI+PF3QkHs=:glPHXkRbfenK0O4atr2V4bta+ntn8bbbFkVHFbdUM2U='),
('yash_patel', 'Yash', 'Patel', 'yash@gmail.com', 'PBKDF2WithHmacSHA256:2048:L0aYBmoBP0+jaJiLriV/AgHdzqmorr8eYsIM+TE8v58=:38nocXsCfCkoX4MpDi6HkxLoYvYYCnksM2h1/hproj0='),
('yug_1112', 'Yug', 'Majiwala', 'yug@gmail.com', 'PBKDF2WithHmacSHA256:2048:ooo4KE/mt9LNBotaH55+2fBTMQ/G+YjNER4x5DKdE/E=:pTPXbQMAR/QE4bIEgCpSlTfwlwzmTUeOwok8WM3+H58=');

-- --------------------------------------------------------

--
-- Table structure for table `vehicletb`
--

CREATE TABLE `vehicletb` (
  `vehicleid` int(20) NOT NULL,
  `tourmasterid` int(20) NOT NULL,
  `vehicle_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `vehicletb`
--

INSERT INTO `vehicletb` (`vehicleid`, `tourmasterid`, `vehicle_name`) VALUES
(10, 2, 'bus'),
(11, 2, 'train');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carttb`
--
ALTER TABLE `carttb`
  ADD PRIMARY KEY (`cartid`),
  ADD KEY `tourid` (`tourid`),
  ADD KEY `carttb_ibfk_2` (`username`);

--
-- Indexes for table `complainttb`
--
ALTER TABLE `complainttb`
  ADD PRIMARY KEY (`complaintid`),
  ADD KEY `complainttb_ibfk_1` (`username`);

--
-- Indexes for table `feedbacktb`
--
ALTER TABLE `feedbacktb`
  ADD PRIMARY KEY (`feedbackid`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `gallerytb`
--
ALTER TABLE `gallerytb`
  ADD PRIMARY KEY (`galleryid`);

--
-- Indexes for table `historytb`
--
ALTER TABLE `historytb`
  ADD PRIMARY KEY (`historyid`),
  ADD KEY `tourid` (`tourid`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `persontb`
--
ALTER TABLE `persontb`
  ADD PRIMARY KEY (`personid`),
  ADD KEY `tourid` (`tourid`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `projectgroups`
--
ALTER TABLE `projectgroups`
  ADD PRIMARY KEY (`groupname`,`username`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `tourtb`
--
ALTER TABLE `tourtb`
  ADD PRIMARY KEY (`tourid`),
  ADD KEY `tourmasterid` (`tourmasterid`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `tour_mastertb`
--
ALTER TABLE `tour_mastertb`
  ADD PRIMARY KEY (`tourmasterid`);

--
-- Indexes for table `tour_placetb`
--
ALTER TABLE `tour_placetb`
  ADD PRIMARY KEY (`tourplaceid`),
  ADD KEY `tourmasterid` (`tourmasterid`);

--
-- Indexes for table `usertb`
--
ALTER TABLE `usertb`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `vehicletb`
--
ALTER TABLE `vehicletb`
  ADD PRIMARY KEY (`vehicleid`),
  ADD KEY `tourmasterid` (`tourmasterid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `carttb`
--
ALTER TABLE `carttb`
  MODIFY `cartid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `complainttb`
--
ALTER TABLE `complainttb`
  MODIFY `complaintid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `feedbacktb`
--
ALTER TABLE `feedbacktb`
  MODIFY `feedbackid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `gallerytb`
--
ALTER TABLE `gallerytb`
  MODIFY `galleryid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `historytb`
--
ALTER TABLE `historytb`
  MODIFY `historyid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `persontb`
--
ALTER TABLE `persontb`
  MODIFY `personid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `tourtb`
--
ALTER TABLE `tourtb`
  MODIFY `tourid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `tour_mastertb`
--
ALTER TABLE `tour_mastertb`
  MODIFY `tourmasterid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tour_placetb`
--
ALTER TABLE `tour_placetb`
  MODIFY `tourplaceid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT for table `vehicletb`
--
ALTER TABLE `vehicletb`
  MODIFY `vehicleid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `carttb`
--
ALTER TABLE `carttb`
  ADD CONSTRAINT `carttb_ibfk_1` FOREIGN KEY (`tourid`) REFERENCES `tourtb` (`tourid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `carttb_ibfk_2` FOREIGN KEY (`username`) REFERENCES `usertb` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `complainttb`
--
ALTER TABLE `complainttb`
  ADD CONSTRAINT `complainttb_ibfk_1` FOREIGN KEY (`username`) REFERENCES `usertb` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `feedbacktb`
--
ALTER TABLE `feedbacktb`
  ADD CONSTRAINT `feedbacktb_ibfk_1` FOREIGN KEY (`username`) REFERENCES `usertb` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `historytb`
--
ALTER TABLE `historytb`
  ADD CONSTRAINT `historytb_ibfk_1` FOREIGN KEY (`tourid`) REFERENCES `tourtb` (`tourid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `historytb_ibfk_2` FOREIGN KEY (`username`) REFERENCES `usertb` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `persontb`
--
ALTER TABLE `persontb`
  ADD CONSTRAINT `persontb_ibfk_1` FOREIGN KEY (`tourid`) REFERENCES `tourtb` (`tourid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `persontb_ibfk_2` FOREIGN KEY (`username`) REFERENCES `usertb` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `projectgroups`
--
ALTER TABLE `projectgroups`
  ADD CONSTRAINT `projectgroups_ibfk_1` FOREIGN KEY (`username`) REFERENCES `usertb` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tourtb`
--
ALTER TABLE `tourtb`
  ADD CONSTRAINT `tourtb_ibfk_1` FOREIGN KEY (`tourmasterid`) REFERENCES `tour_mastertb` (`tourmasterid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tourtb_ibfk_2` FOREIGN KEY (`username`) REFERENCES `usertb` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tour_placetb`
--
ALTER TABLE `tour_placetb`
  ADD CONSTRAINT `tour_placetb_ibfk_1` FOREIGN KEY (`tourmasterid`) REFERENCES `tour_mastertb` (`tourmasterid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vehicletb`
--
ALTER TABLE `vehicletb`
  ADD CONSTRAINT `vehicletb_ibfk_1` FOREIGN KEY (`tourmasterid`) REFERENCES `tour_mastertb` (`tourmasterid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
