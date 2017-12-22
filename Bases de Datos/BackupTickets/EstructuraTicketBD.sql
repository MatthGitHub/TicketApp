-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 31-05-2016 a las 12:20:45
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `ticket`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `area`
--

CREATE TABLE IF NOT EXISTS `area` (
  `id_area` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_area` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `direccion` varchar(30) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `correo` varchar(45) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id_area`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=9183 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `area_sistemas`
--

CREATE TABLE IF NOT EXISTS `area_sistemas` (
  `id_area_sistemas` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_area` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id_area_sistemas`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asunto_principal`
--

CREATE TABLE IF NOT EXISTS `asunto_principal` (
  `id_asuntoP` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id_asuntoP`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=11 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asunto_secundario`
--

CREATE TABLE IF NOT EXISTS `asunto_secundario` (
  `id_asuntoS` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_asuntoS` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `pertenece` int(11) NOT NULL,
  PRIMARY KEY (`id_asuntoS`),
  KEY `pertenece` (`pertenece`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=27 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE IF NOT EXISTS `empleado` (
  `id_empleado` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `apellido` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `documento` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `fk_area` int(11) NOT NULL,
  `legajo` int(11) NOT NULL,
  PRIMARY KEY (`id_empleado`),
  KEY `fk_area` (`fk_area`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=2851 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `encargado_asunto`
--

CREATE TABLE IF NOT EXISTS `encargado_asunto` (
  `usuario` int(11) NOT NULL,
  `asunto` int(11) NOT NULL,
  KEY `usuario` (`usuario`),
  KEY `asunto` (`asunto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados`
--

CREATE TABLE IF NOT EXISTS `estados` (
  `id_estado` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE IF NOT EXISTS `permisos` (
  `id_permiso` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_permiso` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id_permiso`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respuestas`
--

CREATE TABLE IF NOT EXISTS `respuestas` (
  `id_ticket` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `respuesta` varchar(1000) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id_ticket`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticket`
--

CREATE TABLE IF NOT EXISTS `ticket` (
  `id_ticket` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `hora` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fk_area_emisor` int(11) NOT NULL,
  `fk_usuario_emisor` int(11) NOT NULL,
  `fk_area_sistemas` int(11) NOT NULL,
  `asunto` int(11) NOT NULL,
  `observacion` varchar(1500) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `usuario_receptor` int(11) DEFAULT NULL,
  `respuesta` varchar(1500) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `fk_estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_ticket`),
  KEY `fk_area_emisor` (`fk_area_emisor`,`fk_usuario_emisor`,`fk_area_sistemas`,`usuario_receptor`),
  KEY `fk_usuario_emisor` (`fk_usuario_emisor`,`fk_area_sistemas`,`usuario_receptor`),
  KEY `fk_area_sistemas` (`fk_area_sistemas`),
  KEY `usuario_receptor` (`usuario_receptor`),
  KEY `asunto` (`asunto`),
  KEY `fk_estado` (`fk_estado`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=53 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

CREATE TABLE IF NOT EXISTS `tipo_usuario` (
  `id_tipo_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_tipo_usuario` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id_tipo_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `contrasenia` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `fk_empleado` int(11) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `fk_permiso` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `fk_empleado` (`fk_empleado`),
  KEY `fk_permiso` (`fk_permiso`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=7 ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asunto_secundario`
--
ALTER TABLE `asunto_secundario`
  ADD CONSTRAINT `asunto_secundario_ibfk_1` FOREIGN KEY (`pertenece`) REFERENCES `asunto_principal` (`id_asuntoP`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`fk_area`) REFERENCES `area` (`id_area`);

--
-- Filtros para la tabla `encargado_asunto`
--
ALTER TABLE `encargado_asunto`
  ADD CONSTRAINT `encargado_asunto_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `encargado_asunto_ibfk_2` FOREIGN KEY (`asunto`) REFERENCES `asunto_secundario` (`id_asuntoS`);

--
-- Filtros para la tabla `respuestas`
--
ALTER TABLE `respuestas`
  ADD CONSTRAINT `respuestas_ibfk_1` FOREIGN KEY (`id_ticket`) REFERENCES `ticket` (`id_ticket`),
  ADD CONSTRAINT `respuestas_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`fk_area_emisor`) REFERENCES `area` (`id_area`),
  ADD CONSTRAINT `ticket_ibfk_10` FOREIGN KEY (`fk_estado`) REFERENCES `estados` (`id_estado`),
  ADD CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`fk_usuario_emisor`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `ticket_ibfk_4` FOREIGN KEY (`usuario_receptor`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `ticket_ibfk_5` FOREIGN KEY (`fk_area_sistemas`) REFERENCES `area_sistemas` (`id_area_sistemas`),
  ADD CONSTRAINT `ticket_ibfk_8` FOREIGN KEY (`asunto`) REFERENCES `asunto_secundario` (`id_asuntoS`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`fk_empleado`) REFERENCES `empleado` (`id_empleado`),
  ADD CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`fk_permiso`) REFERENCES `permisos` (`id_permiso`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
