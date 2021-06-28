-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 28 Jun 2021 pada 14.16
-- Versi server: 10.4.17-MariaDB
-- Versi PHP: 7.4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
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
-- Struktur dari tabel `daftarujian`
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
-- Dumping data untuk tabel `daftarujian`
--

INSERT INTO `daftarujian` (`ID`, `Nama`, `NIM`, `Email`, `waktuUjian`, `Nilai`, `NilaiPerusahaan`, `Laporan`) VALUES
(1, 'Rysa Laksana', '202010370311128', 'rysalaksana@yahoo.co.id', '2 juli 2022', 'Approve', '0001-dikonversi_compressed 2.pdf', 'Rysa Laksana_128_resume PKM 1.pdf');

-- --------------------------------------------------------

--
-- Struktur dari tabel `datamahasiswa`
--

CREATE TABLE `datamahasiswa` (
  `idKel` int(100) NOT NULL,
  `anggotaKel` text NOT NULL,
  `alamatKel` text NOT NULL,
  `waktuAwal` date NOT NULL,
  `waktuAkhir` date NOT NULL,
  `namaFileCV` varchar(100) NOT NULL,
  `namaFilePorto` varchar(100) NOT NULL,
  `Persetujuan` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `datamahasiswa`
--

INSERT INTO `datamahasiswa` (`idKel`, `anggotaKel`, `alamatKel`, `waktuAwal`, `waktuAkhir`, `namaFileCV`, `namaFilePorto`, `Persetujuan`) VALUES
(1, '\naku\nkamu\ndia', 'Malang', '2021-06-03', '2021-06-04', '0001-dikonversi_compressed 2.pdf', '09 November 2020_ILPEM_Dahan CB._Yudisium.pdf', 'Approve'),
(2, '\nbudi\nbeni\nburhan\nbennet', 'teyvat', '2021-06-12', '2021-06-16', 'Rysa Laksana_128_resume PKM 1.pdf', 'PL2-C-Rysa Laksana-202010370311128.pdf', 'Decline'),
(3, 'budi\ndibu\nubid', 'Malang', '2021-06-20', '2021-07-03', 'PL1-C-Rysa Laksana-202010370311128.pdf', 'PL2-C-Rysa Laksana-202010370311128.pdf', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `email`, `type`) VALUES
(5, 'makan', 'minum', 'yaudah@gmail.com', 'Admin'),
(8, 'rysal', '12345', 'klee@yahoo.com', 'Mahasiswa'),
(6, 'pan', 'pan', 'pan@byon.com', 'Mahasiswa'),
(7, 'ir', 'ir', 'ir@byon.com', 'Dosen');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `daftarujian`
--
ALTER TABLE `daftarujian`
  ADD PRIMARY KEY (`ID`);

--
-- Indeks untuk tabel `datamahasiswa`
--
ALTER TABLE `datamahasiswa`
  ADD PRIMARY KEY (`idKel`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `daftarujian`
--
ALTER TABLE `daftarujian`
  MODIFY `ID` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `datamahasiswa`
--
ALTER TABLE `datamahasiswa`
  MODIFY `idKel` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
