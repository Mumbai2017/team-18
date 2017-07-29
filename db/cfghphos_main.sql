-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 29, 2017 at 11:31 PM
-- Server version: 5.6.36-cll-lve
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cfghphos_main`
--

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `t_id` int(11) DEFAULT NULL,
  `msg` text NOT NULL,
  `timepoint` float NOT NULL,
  `timestamp` int(11) DEFAULT NULL,
  `s_type` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `type`, `t_id`, `msg`, `timepoint`, `timestamp`, `s_type`) VALUES
(13, 2, 1631, 'Thanks for reposding', 0, 1501363447, 1),
(12, 1, 4, 'I am thrilled', 300, 1501362446, 1),
(11, 1, 4, 'Thanks for reposding', 400, 1501362349, 1),
(10, 1, 4, 'Great...', 0, 1501361394, 7),
(9, 2, 1631, 'Yoooooo', 96.548, 1501360541, 7),
(14, 2, 1631, 'GOooold', 0, 1501365102, 1),
(15, 2, 1631, 'nicccc', 501.565, 1501365130, 7),
(16, 2, 1631, 'Thanks', 0, 1501365197, 1);

-- --------------------------------------------------------

--
-- Table structure for table `lesson_plan`
--

CREATE TABLE `lesson_plan` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `topic_id` int(11) DEFAULT NULL,
  `description` text,
  `image_url` text,
  `timestamp` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lesson_plan`
--

INSERT INTO `lesson_plan` (`id`, `user_id`, `topic_id`, `description`, `image_url`, `timestamp`, `score`) VALUES
(3, 1, 2, 'Description about mixed ratio', NULL, 1501327968, 3),
(4, 36, 1, 'Description about mixed fractions', NULL, 1501328500, 4);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `id` int(11) NOT NULL,
  `sub_name` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`id`, `sub_name`) VALUES
(1, 'Maths'),
(2, 'Science'),
(3, 'History');

-- --------------------------------------------------------

--
-- Table structure for table `topic`
--

CREATE TABLE `topic` (
  `id` int(11) NOT NULL,
  `topic_name` varchar(100) NOT NULL,
  `unit_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `topic`
--

INSERT INTO `topic` (`id`, `topic_name`, `unit_id`) VALUES
(1, 'Mixed Fractions', 1),
(2, 'Mixed Ratio', 2),
(3, 'Mixed Proportions', 3),
(4, 'Sub Light', 4),
(5, 'Sub Energy', 5),
(6, 'Sub Pressure', 6),
(7, 'Sub American Revolution', 7),
(8, 'Sub French Revolution', 8),
(9, 'Sub British Revolution', 9);

-- --------------------------------------------------------

--
-- Table structure for table `unit`
--

CREATE TABLE `unit` (
  `id` int(11) NOT NULL,
  `unit_name` varchar(100) NOT NULL,
  `sub_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `unit`
--

INSERT INTO `unit` (`id`, `unit_name`, `sub_id`) VALUES
(1, 'Fractions', 1),
(2, 'Ratio', 1),
(3, 'Proportions', 1),
(4, 'Light', 2),
(5, 'Energy', 2),
(6, 'Pressure', 2),
(7, 'American Revolution', 3),
(8, 'French Revolution', 3),
(9, 'British Revolution', 3);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phone_no` int(11) NOT NULL,
  `loc_lat` double NOT NULL,
  `loc_lang` double NOT NULL,
  `type` int(11) NOT NULL DEFAULT '1'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `phone_no`, `loc_lat`, `loc_lang`, `type`) VALUES
(2, 'team1', '81d5f7bf950746f44e2b92f34e57b0a1', 901234, 23, 72, 7),
(1, 'team18', '81d5f7bf950746f44e2b92f34e57b0a1', 901234, 19, 72, 7),
(51, 'xuxiffzi', '538136f948a18f36a59c97fda360cd08', 0, 0, 0, 1),
(50, '', 'd41d8cd98f00b204e9800998ecf8427e', 0, 0, 0, 1),
(49, 'xcvxcg', '6838e8bf0f0c2de56c9c5c33c03ca902', 0, 0, 0, 1),
(48, 'uvuc', '9e3fc1498a8f48bc39bb10d1bb16cd9e', 0, 0, 0, 1),
(47, 'gxhc', 'd5de516f71b4f087a4011e7b9baa990f', 0, 0, 0, 1),
(46, 'bsns', 'fd8bb4e880c8965994c4782bd46f9c3e', 0, 0, 0, 1),
(27, ' bens', 'b21c83f568cb5b79728d24fabf4d47b6', 0, 0, 0, 1),
(26, 'twhekle', '21f2e6e984f67f4528aa523c5d4f75c2', 0, 0, 0, 1),
(23, 'vshdjd', '2636ab3e819ac5fc3d1e7b20711989c1', 0, 18, 72, 1),
(24, 'jgckg', 'acf2390fe605e5befb2ac6604e8f7240', 0, 19, 68, 1),
(25, 'vejejskks', '5d041fe990d078ded3b04dd203235196', 0, 20, 69, 1),
(45, 'bzbz', 'f0a930fbbe9de1196a6f8640099d2759', 0, 0, 0, 1),
(44, 'gxhcgxy', 'd78ad2d4d20345555de373d620efc41f', 0, 0, 0, 1),
(3, 'team', '81d5f7bf950746f44e2b92f34e57b0a1', 901234, 34, 72, 7);

-- --------------------------------------------------------

--
-- Table structure for table `videos`
--

CREATE TABLE `videos` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `youtube_id` varchar(100) NOT NULL,
  `timestamp` int(11) NOT NULL,
  `lp_id` int(11) NOT NULL,
  `score` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `videos`
--

INSERT INTO `videos` (`id`, `user_id`, `youtube_id`, `timestamp`, `lp_id`, `score`) VALUES
(1631, 36, 'RJ-2K58aSkc', 0, 4, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lesson_plan`
--
ALTER TABLE `lesson_plan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `topic`
--
ALTER TABLE `topic`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `unit`
--
ALTER TABLE `unit`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `videos`
--
ALTER TABLE `videos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `lesson_plan`
--
ALTER TABLE `lesson_plan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `topic`
--
ALTER TABLE `topic`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `unit`
--
ALTER TABLE `unit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
--
-- AUTO_INCREMENT for table `videos`
--
ALTER TABLE `videos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1632;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
