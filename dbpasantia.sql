-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-02-2020 a las 14:42:37
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbpasantia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `codigo` int(11) NOT NULL,
  `producto` int(11) NOT NULL,
  `usuario` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `total` double NOT NULL,
  `estado` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `compras`
--

INSERT INTO `compras` (`codigo`, `producto`, `usuario`, `cantidad`, `fecha`, `total`, `estado`) VALUES
(3, 2, 12, 5, '2020-02-01', 30, 'a'),
(4, 2, 12, 5, '2020-02-01', 35, 'a'),
(5, 2, 12, 5, '2020-02-01', 5, 'a'),
(6, 2, 12, 5, '2020-02-01', 5, 'a'),
(8, 3, 12, 2, '2020-03-01', 2, 'a'),
(9, 5, 12, 2, '2020-03-01', 2.5, 'a'),
(10, 6, 12, 2, '2020-03-01', 3, 'a'),
(11, 8, 12, 2, '2020-03-01', 1, 'a'),
(13, 2, 1, 15, '2020-05-01', 15, 'i'),
(14, 2, 6, 10, '2020-05-01', 10, 'a'),
(15, 8, 1, 8, '2020-05-01', 4, 'i'),
(28, 6, 1, 5, '2020-04-01', 7.5, 'i'),
(29, 9, 1, 5, '2020-04-01', 5, 'i'),
(30, 9, 1, 5, '2020-04-01', 5, 'i'),
(31, 8, 1, 7, '2020-04-01', 3.5, 'a'),
(32, 8, 1, 10, '2020-04-01', 5, 'a'),
(33, 1, 1, 3, '2020-05-01', 4.5, 'i'),
(34, 7, 1, 25, '2020-05-01', 75, 'a'),
(36, 10, 1, 250, '2020-05-01', 375, 'a'),
(37, 10, 1, 1000, '2020-05-01', 1500, 'i');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `valor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`codigo`, `nombre`, `valor`) VALUES
(1, 'leche semidescremada', 1.5),
(2, 'harina', 1),
(3, 'azucar', 1),
(4, 'te', 0.25),
(5, 'arroz', 1.25),
(6, 'aceite', 1.5),
(7, 'coca cola', 3),
(8, 'agua', 0.5),
(9, 'pan', 1),
(10, 'queso', 1.5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `contrasena` varchar(50) NOT NULL,
  `cedula` varchar(10) NOT NULL,
  `provincia` varchar(50) NOT NULL,
  `ciudad` varchar(50) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `estado` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`codigo`, `nombre`, `usuario`, `contrasena`, `cedula`, `provincia`, `ciudad`, `correo`, `estado`) VALUES
(1, 'alejandro alfonso', 'alejandro', '123456', '0931086219', 'CARCHI', 'guayaquil', 'alejandro.quimisv@ug.edu.ec', 'a'),
(6, 'aaaa', 'a', 'a', 'a', 'pichincha', 'quito', 'a', 'a'),
(12, 'aa', 'aa', 'aa', 'aa', 'Guayas', 'quito', 'aa', 'a'),
(13, 'prueba ', 'prueba', 'prueba', 'prueba', 'guayas', 'guayaquil', 'prueba', 'a');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `producto` (`producto`),
  ADD KEY `usuario` (`usuario`) USING BTREE;

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `compras`
--
ALTER TABLE `compras`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compras`
--
ALTER TABLE `compras`
  ADD CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`codigo`),
  ADD CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`producto`) REFERENCES `producto` (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
