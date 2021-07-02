-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 01, 2021 at 02:36 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `datalogin`
--

-- --------------------------------------------------------

--
-- Table structure for table `daftarujian`
--

CREATE TABLE `daftarujian` (
  `ID` int(100) NOT NULL,
  `Nama` varchar(100) DEFAULT NULL,
  `NIM` varchar(20) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `waktuUjian` varchar(20) DEFAULT NULL,
  `Nilai` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `NilaiPerusahaan` varchar(100) DEFAULT NULL,
  `Laporan` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `daftarujian`
--

INSERT INTO `daftarujian` (`ID`, `Nama`, `NIM`, `Email`, `waktuUjian`, `Nilai`, `NilaiPerusahaan`, `Laporan`) VALUES
(1, 'Rysa Laksana', '202010370311128', 'rysalaksana@yahoo.co.id', '2 juli 2022', 'Approve', '0001-dikonversi_compressed 2.pdf', 'Rysa Laksana_128_resume PKM 1.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `datamahasiswa`
--

CREATE TABLE `datamahasiswa` (
  `idKel` int(100) NOT NULL,
  `anggotaKel` text NOT NULL,
  `alamatKel` text NOT NULL,
  `waktuAwal` date NOT NULL,
  `waktuAkhir` date NOT NULL,
  `mail` varchar(100) NOT NULL,
  `namaFileCV` varchar(100) NOT NULL,
  `namaFilePorto` varchar(100) NOT NULL,
  `Persetujuan` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `datamahasiswa`
--

INSERT INTO `datamahasiswa` (`idKel`, `anggotaKel`, `alamatKel`, `waktuAwal`, `waktuAkhir`, `mail`, `namaFileCV`, `namaFilePorto`, `Persetujuan`) VALUES
(1, 'Saya\nDia \nKamu\nSiapa\n', 'Di Malang', '2021-07-01', '2021-07-30', 'irfan64bit@gmail.com', 'CV Kelompok.pdf', 'Portofolio Kelompok.pdf', 'Decline'),
(2, 'Angg1\nAngg2\nAngg3\nAngg4\n', 'Where', '2021-07-01', '2021-07-30', 'irfan64bit@webmail.umm.ac.id', 'CV Kelompok.pdf', 'Portofolio Kelompok.pdf', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `type`) VALUES
(5, 'makan', 'minum', 'Admin'),
(8, 'rysal', '12345', 'Mahasiswa'),
(6, 'pan', 'pan', 'Mahasiswa'),
(7, 'ir', 'ir', 'Dosen');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `daftarujian`
--
ALTER TABLE `daftarujian`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `datamahasiswa`
--
ALTER TABLE `datamahasiswa`
  ADD PRIMARY KEY (`idKel`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `daftarujian`
--
ALTER TABLE `daftarujian`
  MODIFY `ID` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `datamahasiswa`
--
ALTER TABLE `datamahasiswa`
  MODIFY `idKel` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
