-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 20, 2026 at 07:31 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET
time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `juegos_de_mesa`
--

-- --------------------------------------------------------

--
-- Table structure for table `asociacion`
--

CREATE TABLE `asociacion`
(
    `id`             int(11) NOT NULL,
    `cif`            text NOT NULL,
    `nombre`         text NOT NULL,
    `razon_social`   text NOT NULL,
    `direccion`      text NOT NULL,
    `fecha_apertura` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `asociacion`
--

INSERT INTO `asociacion` (`id`, `cif`, `nombre`, `razon_social`, `direccion`, `fecha_apertura`)
VALUES (1, 'CIF', 'Juegos De Mesa', 'Razón Social', 'Dirección', '2026-10-10');

-- --------------------------------------------------------

--
-- Table structure for table `juegos`
--

CREATE TABLE `juegos`
(
    `id`             int(11) NOT NULL,
    `nombre`         text NOT NULL,
    `autores`        text NOT NULL,
    `min_jugadores`  int(11) NOT NULL,
    `max_jugadores`  int(11) NOT NULL,
    `estimacion_minutos` double NOT NULL,
    `tematica`       text NOT NULL,
    `ubicacion`      text NOT NULL,
    `estado`         text NOT NULL,
    `propietaria_id` int(11) DEFAULT NULL,
    `fecha_alta`     date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `juegos`
--

INSERT INTO `juegos` (`id`, `nombre`, `autores`, `min_jugadores`, `max_jugadores`, `estimacion_minutos`, `tematica`,
                      `ubicacion`, `estado`, `propietaria_id`, `fecha_alta`)
VALUES (1, 'juego1', 'autor1', 1, 3, 10, 'tematica1', 'ubicacion1', 'DISPONIBLE', 1, '2026-01-01'),
       (2, 'juego2', 'autor2 y autor3', 1, 5, 15, 'tematica2', 'ubicacio2', 'DISPONIBLE', 1, '2026-02-02');

-- --------------------------------------------------------

--
-- Table structure for table `partidas`
--

CREATE TABLE `partidas`
(
    `id`              int(11) NOT NULL,
    `juego_id`        int(11) NOT NULL,
    `fecha`           date NOT NULL,
    `hora`            time NOT NULL,
    `plazas_deseadas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `partidas`
--

INSERT INTO `partidas` (`id`, `juego_id`, `fecha`, `hora`, `plazas_deseadas`)
VALUES (1, 1, '2026-01-01', '12:00:00', 3),
       (2, 2, '2026-02-02', '14:00:00', 5);

-- --------------------------------------------------------

--
-- Table structure for table `partidas_jugadoras`
--

CREATE TABLE `partidas_jugadoras`
(
    `id`              int(11) NOT NULL,
    `partida_id`      int(11) NOT NULL,
    `jugadora_id`     int(11) DEFAULT NULL,
    `nombre_invitada` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `partidas_jugadoras`
--

INSERT INTO `partidas_jugadoras` (`id`, `partida_id`, `jugadora_id`, `nombre_invitada`)
VALUES (1, 1, NULL, 'invitada1'),
       (2, 1, 1, NULL),
       (3, 2, NULL, 'invitada2'),
       (4, 2, 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `socias`
--

CREATE TABLE `socias`
(
    `id`        int(11) NOT NULL,
    `dni`       text NOT NULL,
    `nombre`    text NOT NULL,
    `apellidos` text NOT NULL,
    `fecha_nac` date NOT NULL,
    `telefono`  text NOT NULL,
    `email`     text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `socias`
--

INSERT INTO `socias` (`id`, `dni`, `nombre`, `apellidos`, `fecha_nac`, `telefono`, `email`)
VALUES (1, 'dni1', 'nombre1', 'apellidos1', '2001-01-01', '111111111', 'email1'),
       (2, 'dni2', 'nombre2', 'apellidos2', '2002-02-02', '222222222', 'email2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `asociacion`
--
ALTER TABLE `asociacion`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `juegos`
--
ALTER TABLE `juegos`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_juego_propiet` (`propietaria_id`);

--
-- Indexes for table `partidas`
--
ALTER TABLE `partidas`
    ADD PRIMARY KEY (`id`),
  ADD KEY `ck_par_jueg_id` (`juego_id`);

--
-- Indexes for table `partidas_jugadoras`
--
ALTER TABLE `partidas_jugadoras`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_parjug_juegid` (`partida_id`),
  ADD KEY `fk_parjug_jugaid` (`jugadora_id`);

--
-- Indexes for table `socias`
--
ALTER TABLE `socias`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `asociacion`
--
ALTER TABLE `asociacion`
    MODIFY `id` int (11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `juegos`
--
ALTER TABLE `juegos`
    MODIFY `id` int (11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `partidas`
--
ALTER TABLE `partidas`
    MODIFY `id` int (11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `partidas_jugadoras`
--
ALTER TABLE `partidas_jugadoras`
    MODIFY `id` int (11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `socias`
--
ALTER TABLE `socias`
    MODIFY `id` int (11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `juegos`
--
ALTER TABLE `juegos`
    ADD CONSTRAINT `fk_juego_propiet` FOREIGN KEY (`propietaria_id`) REFERENCES `socias` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `partidas`
--
ALTER TABLE `partidas`
    ADD CONSTRAINT `ck_par_jueg_id` FOREIGN KEY (`juego_id`) REFERENCES `juegos` (`id`);

--
-- Constraints for table `partidas_jugadoras`
--
ALTER TABLE `partidas_jugadoras`
    ADD CONSTRAINT `fk_parjug_par_id` FOREIGN KEY (`partida_id`) REFERENCES `partidas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_parjug_soc_id` FOREIGN KEY (`jugadora_id`) REFERENCES `socias` (`id`) ON
DELETE
SET NULL ON
UPDATE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
