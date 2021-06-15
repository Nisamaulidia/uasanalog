-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 15, 2021 at 03:46 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpus`
--

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `idbuku` int(11) NOT NULL,
  `judul` varchar(100) NOT NULL,
  `penerbit` varchar(100) NOT NULL,
  `tahun` int(4) NOT NULL,
  `status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`idbuku`, `judul`, `penerbit`, `tahun`, `status`) VALUES
(52, 'Kalkulus', 'Gramedia', 2015, 'DIPINJAM'),
(56, 'IPS', 'Gramedia', 2011, 'TERSEDIA'),
(58, 'Analog', 'Erlangga', 2018, 'TERSEDIA');

-- --------------------------------------------------------

--
-- Table structure for table `pinjam`
--

CREATE TABLE `pinjam` (
  `idpinjam` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `nik` varchar(100) NOT NULL,
  `nohp` varchar(20) NOT NULL,
  `idbuku` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pinjam`
--

INSERT INTO `pinjam` (`idpinjam`, `nama`, `nik`, `nohp`, `idbuku`) VALUES
(52, 'nisa', '12345', '654321', 52);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`idbuku`);

--
-- Indexes for table `pinjam`
--
ALTER TABLE `pinjam`
  ADD PRIMARY KEY (`idpinjam`),
  ADD UNIQUE KEY `nik` (`nik`),
  ADD KEY `idbuku` (`idbuku`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pinjam`
--
ALTER TABLE `pinjam`
  ADD CONSTRAINT `pinjam_ibfk_1` FOREIGN KEY (`idbuku`) REFERENCES `buku` (`idbuku`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
