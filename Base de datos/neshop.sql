-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-01-2020 a las 21:21:42
-- Versión del servidor: 10.1.36-MariaDB
-- Versión de PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `neshop`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `card`
--

CREATE TABLE `card` (
  `card_number` bigint(20) NOT NULL,
  `balance` double NOT NULL,
  `cvc` tinyint(4) NOT NULL,
  `titular` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `card`
--

INSERT INTO `card` (`card_number`, `balance`, `cvc`, `titular`) VALUES
(0, 999999999, 100, 'Oscar Isaac Ruiz Rodríguez'),
(589, 15000, 125, 'asd asd'),
(857, 15000, 102, 'Rafael Leon Estrada'),
(4444, 654684, 58, 'José');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--

CREATE TABLE `category` (
  `id_category` int(11) NOT NULL,
  `name_category` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `category` (`id_category`, `name_category`) VALUES
(0, 'Consolas'),
(1, 'Juegos'),
(2, 'Accesorios'),
(3, 'Juguetes');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

CREATE TABLE `product` (
  `Id_product` bigint(20) NOT NULL,
  `name_product` varchar(50) NOT NULL,
  `stock` int(11) NOT NULL,
  `category` int(11) NOT NULL,
  `price` double NOT NULL,
  `description` text NOT NULL,
  `image` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `purchase`
--

CREATE TABLE `purchase` (
  `id_purchase` bigint(20) NOT NULL,
  `date` datetime NOT NULL,
  `quantity` int(11) NOT NULL,
  `total` double NOT NULL,
  `subtotal` double NOT NULL,
  `user` varchar(30) NOT NULL,
  `product` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `sex` char(1) NOT NULL,
  `phone` varchar(12) NOT NULL,
  `born` date NOT NULL,
  `user_type` char(1) NOT NULL,
  `credit_card` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`username`, `password`, `name`, `lastname`, `sex`, `phone`, `born`, `user_type`, `credit_card`) VALUES
('asd', 'asd', 'asd', 'asd', 'M', '589658745213', '2020-01-22', 'U', 589),
('Sassy', 'asd', 'Oscar Isaac', 'Ruiz Rodríguez', 'M', '3336587554', '2000-02-26', 'A', 0),
('SkillerHero', 'SkillerHero', 'Rafael', 'Leon Estrada', 'M', '589623587415', '2020-01-30', 'U', 857);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`card_number`);

--
-- Indices de la tabla `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id_category`);

--
-- Indices de la tabla `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`Id_product`),
  ADD KEY `category` (`category`);

--
-- Indices de la tabla `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`id_purchase`),
  ADD KEY `user` (`user`,`product`),
  ADD KEY `product` (`product`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`),
  ADD KEY `credit_card` (`credit_card`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `purchase`
--
ALTER TABLE `purchase`
  MODIFY `id_purchase` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category`) REFERENCES `category` (`id_category`);

--
-- Filtros para la tabla `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `purchase_ibfk_2` FOREIGN KEY (`product`) REFERENCES `product` (`Id_product`);

--
-- Filtros para la tabla `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`credit_card`) REFERENCES `card` (`card_number`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
