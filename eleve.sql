-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 08, 2021 at 04:55 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestionetudiant`
--

-- --------------------------------------------------------

--
-- Table structure for table `eleve`
--

CREATE TABLE `eleve` (
  `nom` varchar(20) DEFAULT NULL,
  `prenom` varchar(20) DEFAULT NULL,
  `sexe` varchar(6) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `classe` varchar(16) DEFAULT NULL,
  `moyenne` int(2) DEFAULT NULL,
  `ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `eleve`
--

INSERT INTO `eleve` (`nom`, `prenom`, `sexe`, `dob`, `classe`, `moyenne`, `ID`) VALUES
('Laporte', 'Bill', 'Homme', '1999-11-06', 'TS1', 6, 1),
('De Fermat', 'éric', 'Homme', '2005-02-17', '2nd 3', 14, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `eleve`
--
ALTER TABLE `eleve`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `eleve`
--
ALTER TABLE `eleve`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
