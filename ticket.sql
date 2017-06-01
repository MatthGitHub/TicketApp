-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-06-2017 a las 11:48:35
-- Versión del servidor: 5.7.11
-- Versión de PHP: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ticket`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `areas`
--

CREATE TABLE `areas` (
  `id_area` int(11) NOT NULL,
  `nombre_area` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `direccion` varchar(30) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `correo` varchar(45) COLLATE utf8_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `areas`
--

INSERT INTO `areas` (`id_area`, `nombre_area`, `direccion`, `correo`) VALUES
(38, 'Direccion de Sistemas', 'Mitre 531', 'secsistemas@bariloche.gov.ar'),
(9185, 'Direccion de Mantenimiento e Infraestructura', 'Rivadavia 520', 'dirmantenimientomscb@bariloche.gov.ar'),
(9186, 'Direccion de Contaduria', 'Mitre 531', 'contaduriacontablemscb@bariloche.gov.ar'),
(9187, 'Departamento de Contribuciones', 'Mitre 531', 'contribucionesmscb@bariloche.gov.ar'),
(9188, 'Secretaria de Hacienda', 'Mitre 531', 'haciendamscb@bariloche.gov.ar'),
(9189, 'Secretaria de Gobierno', 'Centro civico', 'secgobiernomscb@bariloche.gov.ar'),
(9190, 'Politicas tributarias', 'Mitre 531', 'politicastributarias1mscb@bariloche.gov.ar'),
(9191, 'Departamento de Sueldos', 'Moreno y Moreno', 'sueldosmscb@bariloche.gov.ar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `area_sistemas`
--

CREATE TABLE `area_sistemas` (
  `id_area_sistemas` int(11) NOT NULL,
  `nombre_area` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asuntos`
--

CREATE TABLE `asuntos` (
  `id_asuntoP` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `fk_area` int(11) NOT NULL,
  `visible` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `asuntos`
--

INSERT INTO `asuntos` (`id_asuntoP`, `nombre`, `fk_area`, `visible`) VALUES
(11, 'PGM', 38, 0),
(12, 'Sueldos', 38, 0),
(13, 'WebDoc', 38, 0),
(14, 'Terminal', 38, 0),
(15, 'Cajero', 38, 0),
(16, 'Correo', 38, 0),
(17, 'Servicio tecnico', 38, 0),
(19, 'Tickets', 38, 0),
(20, 'Informe de pedidos', 38, 0),
(21, 'PGM nuevo', 38, 0),
(22, 'Servidores', 38, 0),
(23, 'Fichadas', 38, 0),
(24, 'Pedidos a Córdoba', 38, 0),
(25, 'Veterinaria', 38, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `base_conocimiento`
--

CREATE TABLE `base_conocimiento` (
  `id_resolucion` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `resolucion` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `fk_ticket` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `id_empleado` int(11) NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `apellido` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `documento` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `fk_area` int(11) NOT NULL,
  `legajo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`id_empleado`, `nombre`, `apellido`, `documento`, `fk_area`, `legajo`) VALUES
(1, 'matias', 'benditti', '35593648', 38, 999998),
(113, 'alicia estela', 'varano', '17142252', 38, 174),
(146, 'gustavo alberto', 'dinardo', '17605283', 38, 232),
(199, 'hector gabriel', 'acuña', '16392455', 38, 331),
(395, 'gladys mabel', 'ibañez', '14759902', 38, 606),
(983, 'maria beatriz', 'marin', '23831042', 38, 20389),
(1177, 'eduardo', 'tomatis', '25553289', 38, 11263),
(1637, 'enzo david', 'aguilera kaiser', '28217146', 38, 20515),
(1685, 'juan carlos', 'ramos', '29428784', 38, 11327),
(2023, 'gustavo ruben', 'castro lamaison', '26645915', 38, 12275),
(2555, 'raul daniel', 'alvarez covarrubias', '31943461', 38, 13792),
(2851, 'estefania', 'klein', '32768591', 38, 999997),
(2852, 'bruno', 'ovando', '38091463', 38, 999999),
(2853, 'claudia', 'claudia', '12345987', 38, 988888);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `encargado_servicios`
--

CREATE TABLE `encargado_servicios` (
  `usuario` int(11) NOT NULL,
  `asunto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `encargado_servicios`
--

INSERT INTO `encargado_servicios` (`usuario`, `asunto`) VALUES
(3, 27),
(3, 28),
(3, 29),
(3, 30),
(3, 45),
(3, 46),
(3, 47),
(3, 48),
(3, 49),
(3, 50),
(3, 51),
(3, 52),
(3, 53),
(3, 54),
(3, 55),
(3, 56),
(3, 57),
(3, 58),
(3, 59),
(3, 60),
(3, 62),
(3, 66),
(3, 67),
(3, 68),
(3, 69),
(3, 70),
(3, 71),
(3, 74),
(3, 76),
(3, 77),
(3, 78),
(3, 79),
(3, 80),
(3, 81),
(3, 82),
(3, 83),
(3, 84),
(3, 85),
(3, 86),
(3, 87),
(3, 88),
(3, 89),
(3, 90),
(3, 91),
(3, 92),
(3, 93),
(3, 94),
(3, 95),
(3, 96),
(3, 97),
(3, 98),
(3, 99),
(3, 100),
(3, 101),
(3, 102),
(3, 103),
(3, 104),
(3, 105),
(3, 106),
(3, 107),
(3, 108),
(3, 109),
(3, 110),
(3, 111),
(3, 112),
(3, 113),
(3, 114),
(3, 115),
(3, 116),
(3, 117),
(3, 118),
(3, 119),
(3, 120),
(3, 121),
(3, 122),
(3, 123),
(3, 124),
(3, 125),
(3, 126),
(3, 127),
(3, 128),
(3, 129),
(3, 130),
(3, 131),
(3, 132),
(3, 134),
(3, 135),
(3, 138),
(3, 139),
(15, 27),
(15, 28),
(15, 29),
(15, 48),
(15, 51),
(15, 102),
(15, 130),
(16, 59),
(16, 60),
(16, 62),
(16, 90),
(16, 91),
(16, 92),
(16, 93),
(16, 94),
(16, 95),
(16, 96),
(17, 29),
(17, 30),
(17, 49),
(17, 50),
(17, 51),
(17, 66),
(17, 67),
(17, 68),
(17, 69),
(17, 70),
(17, 71),
(17, 74),
(17, 76),
(17, 77),
(17, 78),
(17, 79),
(17, 80),
(17, 81),
(17, 82),
(17, 83),
(17, 84),
(17, 85),
(17, 86),
(17, 87),
(17, 88),
(17, 102),
(17, 130),
(17, 134),
(17, 135),
(18, 27),
(18, 48),
(18, 102),
(18, 104),
(18, 109),
(18, 110),
(18, 111),
(18, 115),
(18, 116),
(18, 117),
(18, 118),
(18, 119),
(18, 120),
(18, 121),
(18, 122),
(18, 123),
(18, 124),
(18, 125),
(18, 126),
(18, 127),
(18, 128),
(18, 131),
(18, 132),
(18, 137),
(19, 59),
(19, 62),
(19, 90),
(19, 91),
(19, 92),
(19, 93),
(19, 94),
(19, 95),
(19, 96),
(21, 29),
(21, 48),
(21, 49),
(21, 50),
(21, 66),
(21, 67),
(21, 68),
(21, 69),
(21, 70),
(21, 71),
(21, 74),
(21, 76),
(21, 77),
(21, 78),
(21, 79),
(21, 80),
(21, 81),
(21, 82),
(21, 83),
(21, 84),
(21, 85),
(21, 86),
(21, 87),
(21, 88),
(21, 104),
(21, 106),
(21, 109),
(21, 110),
(21, 111),
(21, 113),
(21, 114),
(21, 120),
(21, 121),
(21, 127),
(21, 132),
(21, 134),
(21, 135),
(21, 136),
(21, 139),
(23, 97),
(23, 98),
(23, 99),
(23, 100),
(23, 101),
(24, 27),
(24, 28),
(24, 29),
(24, 30),
(24, 45),
(24, 46),
(24, 47),
(24, 48),
(24, 51),
(24, 52),
(24, 53),
(24, 54),
(24, 55),
(24, 56),
(24, 57),
(24, 58),
(24, 59),
(24, 60),
(24, 62),
(24, 89),
(24, 90),
(24, 91),
(24, 92),
(24, 93),
(24, 94),
(24, 95),
(24, 96),
(24, 97),
(24, 98),
(24, 99),
(24, 100),
(24, 101),
(24, 102),
(24, 103),
(25, 62),
(25, 90),
(25, 91),
(25, 92),
(25, 93),
(25, 94),
(25, 95),
(25, 96),
(27, 102),
(27, 112),
(27, 113),
(27, 114),
(27, 127),
(27, 129),
(27, 130),
(27, 131),
(27, 139),
(29, 59),
(29, 60),
(29, 96),
(29, 102),
(29, 104),
(29, 105),
(29, 106),
(29, 107),
(29, 108),
(29, 109),
(29, 110),
(30, 29),
(30, 30),
(30, 47),
(30, 48);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados`
--

CREATE TABLE `estados` (
  `id_estado` int(11) NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `estados`
--

INSERT INTO `estados` (`id_estado`, `nombre`) VALUES
(1, 'Enviado'),
(2, 'Recibido'),
(3, 'En espera'),
(4, 'Respondido'),
(5, 'Resuelto'),
(6, 'Trabajando'),
(7, 'Eliminado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados_pgm`
--

CREATE TABLE `estados_pgm` (
  `id_estado` int(11) NOT NULL,
  `estado` varchar(15) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `estados_pgm`
--

INSERT INTO `estados_pgm` (`id_estado`, `estado`) VALUES
(1, 'Excelente'),
(2, 'Bien'),
(3, 'Lento'),
(4, 'Bloqueado'),
(5, 'Reiniciando');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_actual_pgm`
--

CREATE TABLE `estado_actual_pgm` (
  `id` int(11) NOT NULL,
  `fk_estado_pgm` int(11) NOT NULL,
  `fecha` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `estado_actual_pgm`
--

INSERT INTO `estado_actual_pgm` (`id`, `fk_estado_pgm`, `fecha`) VALUES
(1, 1, '2016-11-18 14:01:04');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_tickets`
--

CREATE TABLE `historial_tickets` (
  `id_historial` int(11) NOT NULL,
  `fk_ticket` int(11) NOT NULL,
  `fk_usuario` int(11) DEFAULT NULL COMMENT AS `Usuario actual`,
  `fecha` date NOT NULL,
  `fk_estado` int(11) DEFAULT NULL,
  `resolucion` varchar(3000) DEFAULT NULL COMMENT AS `Mensajes,respuestas y como se resolvio`,
  `fk_razon` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `historial_tickets`
--

INSERT INTO `historial_tickets` (`id_historial`, `fk_ticket`, `fk_usuario`, `fecha`, `fk_estado`, `resolucion`, `fk_razon`) VALUES
(28, 16, 24, '2016-11-16', 1, NULL, NULL),
(29, 17, 24, '2016-11-16', 1, NULL, NULL),
(32, 20, 24, '2016-11-16', 1, NULL, NULL),
(33, 21, 24, '2016-11-16', 1, NULL, NULL),
(34, 22, 24, '2016-11-16', 1, NULL, NULL),
(35, 16, 24, '2016-11-16', 5, 'RESUELTO', NULL),
(36, 23, 24, '2016-11-16', 1, NULL, NULL),
(37, 24, 24, '2016-11-16', 1, NULL, NULL),
(38, 25, 24, '2016-11-16', 1, NULL, NULL),
(39, 26, 24, '2016-11-16', 1, NULL, NULL),
(40, 27, 24, '2016-11-16', 1, NULL, NULL),
(41, 28, 24, '2016-11-16', 1, NULL, NULL),
(42, 29, 24, '2016-11-16', 1, NULL, NULL),
(43, 30, 24, '2016-11-16', 1, NULL, NULL),
(44, 31, 24, '2016-11-16', 1, NULL, NULL),
(45, 32, 24, '2016-11-16', 1, NULL, NULL),
(46, 33, 24, '2016-11-16', 1, NULL, NULL),
(47, 34, 24, '2016-11-16', 1, NULL, NULL),
(48, 35, 24, '2016-11-16', 1, NULL, NULL),
(49, 36, 24, '2016-11-16', 1, NULL, NULL),
(50, 37, 24, '2016-11-16', 1, NULL, NULL),
(51, 38, 24, '2016-11-16', 1, NULL, NULL),
(52, 34, 24, '2016-11-16', 5, NULL, NULL),
(53, 39, 24, '2016-11-16', 1, NULL, NULL),
(54, 31, 24, '2016-11-16', 5, 'atendido por castro solucionado', NULL),
(55, 20, 24, '2016-11-16', 5, NULL, NULL),
(56, 40, 24, '2016-11-16', 1, NULL, NULL),
(57, 40, 24, '2016-11-16', 5, NULL, NULL),
(58, 17, 24, '2016-11-16', 5, 'Tenia acceso directo del cajero viejo', NULL),
(60, 22, 24, '2016-11-16', 5, 'Fue a tesoreria\n', NULL),
(61, 24, 24, '2016-11-16', 5, 'Cambio la clave y se olvido.', NULL),
(62, 41, 24, '2016-11-18', 1, NULL, NULL),
(63, 39, 24, '2016-11-18', 5, 'resuelto', NULL),
(64, 42, 24, '2016-11-18', 1, NULL, NULL),
(65, 25, 24, '2016-11-18', 5, 'resuelto', NULL),
(66, 26, 24, '2016-11-18', 5, 'resuelto', NULL),
(67, 42, 24, '2016-11-18', 5, 'resuelto', NULL),
(68, 41, 24, '2016-11-18', 5, 'resuelto', NULL),
(69, 43, 24, '2016-11-18', 1, NULL, NULL),
(70, 38, 24, '2016-11-18', 5, 'resuelto', NULL),
(71, 43, 24, '2016-11-18', 5, 'resuelto', NULL),
(72, 44, 24, '2016-11-18', 1, NULL, NULL),
(73, 37, 24, '2016-11-18', 5, NULL, NULL),
(79, 39, 24, '2016-11-29', 1, NULL, NULL),
(80, 41, 24, '2016-11-29', 1, NULL, NULL),
(82, 39, 24, '2016-11-29', 5, 'resuelto', NULL),
(83, 27, 24, '2016-11-29', 5, 'Problema del servidor cuando se desconecta remotamente alguien bloquea las conexiones.', NULL),
(84, 33, 24, '2016-11-29', 5, 'Contraseña reinicializada.', NULL),
(85, 44, 24, '2016-11-29', 5, 'Permisos otorgados\n', NULL),
(118, 23, 3, '2017-02-15', 2, NULL, NULL),
(119, 41, 3, '2017-02-15', 2, NULL, NULL),
(120, 23, 3, '2017-02-15', 5, 'null - - - Resolucion: se hizo', NULL),
(121, 41, 3, '2017-02-15', 5, 'Resolucion: se hizo', NULL),
(147, 76, 30, '2017-04-18', 1, NULL, NULL),
(149, 76, 27, '2017-04-18', 2, NULL, NULL),
(150, 28, 15, '2017-04-19', 2, NULL, NULL),
(151, 29, 15, '2017-04-19', 2, NULL, NULL),
(152, 30, 15, '2017-04-19', 2, NULL, NULL),
(153, 35, 15, '2017-04-19', 2, NULL, NULL),
(154, 36, 15, '2017-04-19', 2, NULL, NULL),
(161, 76, 3, '2017-04-19', 7, NULL, NULL),
(172, 77, 3, '2017-04-19', 1, NULL, NULL),
(173, 77, 3, '2017-04-19', 2, NULL, NULL),
(174, 77, 3, '2017-04-19', 5, 'Resolucion: Se agregaron los accesos a consulta generarl de fichadas', NULL),
(175, 78, 3, '2017-04-19', 1, NULL, NULL),
(176, 78, 3, '2017-04-19', 2, NULL, NULL),
(177, 78, 3, '2017-04-19', 5, 'Resolucion: Se agregaron permisos solicitados\n', NULL),
(178, 79, 3, '2017-04-19', 1, NULL, NULL),
(179, 79, 3, '2017-04-19', 2, NULL, NULL),
(180, 80, 3, '2017-04-19', 1, NULL, NULL),
(181, 81, 3, '2017-04-19', 1, NULL, NULL),
(182, 80, 3, '2017-04-19', 2, NULL, NULL),
(183, 81, 3, '2017-04-19', 2, NULL, NULL),
(184, 82, 3, '2017-04-19', 1, NULL, NULL),
(185, 82, 3, '2017-04-19', 2, NULL, NULL),
(186, 80, 3, '2017-04-19', 5, 'Resolucion: ', NULL),
(187, 81, 3, '2017-04-19', 5, 'Resolucion: ', NULL),
(188, 82, 3, '2017-04-19', 5, 'Resolucion: Se crea usuario en PGM nuevo con permisos de Mercado Comunitario S1', NULL),
(189, 83, 3, '2017-04-19', 1, NULL, NULL),
(190, 83, 3, '2017-04-19', 2, NULL, NULL),
(195, 79, 18, '2017-04-20', 2, NULL, NULL),
(202, 79, 18, '2017-04-20', 4, 'nullMensaje de avarano: hola', NULL),
(203, 79, 3, '2017-04-20', 4, 'Mensaje de mbenditti: Hola', NULL),
(213, 90, 18, '2017-04-21', 1, NULL, NULL),
(217, 94, 18, '2017-04-21', 1, NULL, NULL),
(218, 94, 18, '2017-04-21', 4, ' Mensaje de avarano: Se modifico estado a Adeudado.\nPosteriormente se verifico que la anulacion correspondia a una dj rectificativa posterior.\n\nSe creo reporte para consultar boletas adeudadas/prescriptas.', NULL),
(219, 94, 18, '2017-04-21', 5, ' Mensaje de avarano: Se modifico estado a Adeudado.\nPosteriormente se verifico que la anulacion correspondia a una dj rectificativa posterior.\n\nSe creo reporte para consultar boletas adeudadas/prescriptas.\n - - - - - Resolucion por avarano : Se modifico estado a Adeudado.\nPosteriormente se verifico que la anulacion correspondia a una dj rectificativa posterior.\n\nSe creo reporte para consultar boletas adeudadas/prescriptas.', NULL),
(223, 90, 18, '2017-04-21', 7, NULL, NULL),
(225, 95, 18, '2017-04-21', 1, NULL, NULL),
(226, 95, 18, '2017-04-21', 5, 'Resolucion por avarano : Verificamos el caso y el item estaba transferido exactamente igual a los datos existentes en la base de datos original de la transferencia. Posiblemente esa boleta haya sido modificada posterior a la transferencia. Verificamos además todos los talonarios y no existen cuotas de los mismos sin ítems.\n\n', NULL),
(230, 98, 18, '2017-04-21', 1, NULL, NULL),
(231, 98, 18, '2017-04-21', 3, NULL, NULL),
(234, 98, 18, '2017-04-21', 4, ' Mensaje de avarano: Respondieron que no encontraron nada mal.\n\nLos procesas eran 8000958357 y 8031989232. Hector encontro que se ambos tienen igual id_proceso\nl', NULL),
(241, 104, 18, '2017-04-21', 1, NULL, NULL),
(242, 105, 18, '2017-04-21', 1, NULL, NULL),
(244, 106, 18, '2017-04-21', 1, NULL, NULL),
(245, 107, 18, '2017-04-21', 1, NULL, NULL),
(246, 108, 18, '2017-04-21', 1, NULL, NULL),
(247, 109, 18, '2017-04-21', 1, NULL, NULL),
(248, 109, 18, '2017-04-21', 3, NULL, NULL),
(249, 109, 18, '2017-04-21', 4, NULL, NULL),
(250, 110, 18, '2017-04-21', 1, NULL, NULL),
(251, 111, NULL, '2017-04-21', 1, NULL, NULL),
(252, 111, 18, '2017-04-21', 4, NULL, NULL),
(253, 112, NULL, '2017-04-21', 1, NULL, NULL),
(254, 79, 18, '2017-04-21', 4, NULL, NULL),
(255, 79, 18, '2017-04-21', 5, 'Resolucion por avarano : ', NULL),
(256, 113, 18, '2017-04-21', 1, NULL, NULL),
(257, 113, 18, '2017-04-21', 4, NULL, NULL),
(258, 113, 18, '2017-04-21', 5, 'Resolucion por avarano : lo anulo ccartes.', NULL),
(259, 114, 18, '2017-04-21', 1, NULL, NULL),
(260, 83, 3, '2017-04-21', 5, 'Resolucion por mbenditti : Se envio excel con los datos solicitados', NULL),
(261, 115, NULL, '2017-04-21', 1, NULL, NULL),
(262, 116, NULL, '2017-04-21', 1, NULL, NULL),
(263, 117, 3, '2017-04-21', 1, NULL, NULL),
(264, 118, NULL, '2017-04-24', 1, NULL, NULL),
(265, 119, 3, '2017-04-24', 1, NULL, NULL),
(266, 120, NULL, '2017-04-24', 1, NULL, NULL),
(267, 121, NULL, '2017-04-24', 1, NULL, NULL),
(268, 122, 21, '2017-04-24', 1, NULL, NULL),
(269, 122, 21, '2017-04-24', 5, 'Resolucion por eklein : 3 minutos', NULL),
(270, 119, 3, '2017-04-24', 5, 'Resolucion por mbenditti : Porque tenia un plan de pago anulado y 3 cuotas pagas, se generaron las notas de credito\n', NULL),
(271, 120, 3, '2017-04-24', 5, 'Resolucion por mbenditti : Resetio clave bruno', NULL),
(272, 117, 3, '2017-04-24', 5, 'Resolucion por mbenditti : (Y)', NULL),
(273, 123, NULL, '2017-04-24', 1, NULL, NULL),
(274, 116, 27, '2017-04-24', 5, 'Resolucion por bovando : servidor listo para sitio comunicar, tiempo: 2 hs', NULL),
(275, 115, 27, '2017-04-24', 5, 'Resolucion por bovando : ', NULL),
(276, 124, NULL, '2017-04-24', 1, NULL, NULL),
(277, 125, NULL, '2017-04-24', 1, NULL, NULL),
(278, 123, 3, '2017-04-25', 5, 'Resolucion por mbenditti : Se les dio permiso para el fichas de proveedores en listados especiales.', NULL),
(279, 118, 18, '2017-04-25', 4, NULL, NULL),
(282, 128, NULL, '2017-04-26', 1, NULL, NULL),
(283, 128, 3, '2017-04-26', 5, 'Resolucion por mbenditti : Se creo usuario en el sistema de fichadas', NULL),
(284, 129, NULL, '2017-04-26', 1, NULL, NULL),
(285, 130, NULL, '2017-04-26', 1, NULL, NULL),
(303, 133, 18, '2017-04-28', 1, NULL, NULL),
(304, 134, 18, '2017-04-28', 1, NULL, NULL),
(305, 134, 18, '2017-04-28', 5, 'Resolucion por avarano : se modifico el estado de las declaraciones-.', NULL),
(306, 118, 18, '2017-04-28', 5, 'Resolucion por avarano : se informo del listado\n', NULL),
(307, 130, 18, '2017-04-28', 5, 'Resolucion por avarano : ya lo hizo mati', NULL),
(308, 130, 18, '2017-04-28', 1, 'Resolucion por avarano : ya lo hizo mati', NULL),
(309, 135, 18, '2017-04-28', 1, NULL, NULL),
(310, 135, 18, '2017-04-28', 5, 'Resolucion por avarano : Pgm modifico estado cuotas talonarios a adeudadas y se caducaron los talonarios.', NULL),
(311, 130, 3, '2017-04-28', 5, 'Resolucion por avarano : ya lo hizo mati\nResolucion por mbenditti : Si lo hice yo', NULL),
(312, 136, NULL, '2017-05-02', 1, NULL, NULL),
(313, 136, 27, '2017-05-02', 5, 'Resolucion por bovando : Claves solicitadas blanqueadas.', NULL),
(321, 133, 18, '2017-05-02', 4, NULL, NULL),
(323, 133, 18, '2017-05-02', 5, 'Resolucion por avarano : En estas 3 cuentas  no coinciden el tipo de obra con el tipo de plan.\nNo se cual corresponde y habria que revisar la fecha de vencimiento de la deuda generada.\n \n904002531                                         \n904002618                                         \n904002713\n', NULL),
(387, 208, 27, '2017-05-04', 1, NULL, NULL),
(388, 208, 27, '2017-05-04', 5, 'Resolucion por bovando : Se modico el documento de plantilla Nota Retencion Licencia con lo solicitado.', NULL),
(389, 209, NULL, '2017-05-04', 1, NULL, NULL),
(390, 209, 27, '2017-05-04', 5, 'Resolucion por bovando : Se creo usuario semarin para ver sus fichadas', NULL),
(407, 226, NULL, '2017-05-04', 1, NULL, NULL),
(408, 226, 18, '2017-05-05', 7, NULL, NULL),
(409, 227, 18, '2017-05-05', 1, NULL, NULL),
(410, 228, 18, '2017-05-05', 1, NULL, NULL),
(411, 229, 18, '2017-05-05', 1, NULL, NULL),
(412, 229, 18, '2017-05-05', 5, 'Resolucion por avarano : 20161121|13:36:03|roceballo boleta 0933602166\n\n20161122|10:00:18 AM|MAMONTES el recibo digital\n\nSec.Hacienda solicito anulacion boleta adeudada', NULL),
(413, 230, 18, '2017-05-05', 1, NULL, NULL),
(414, 231, 18, '2017-05-05', 1, NULL, NULL),
(415, 232, 18, '2017-05-05', 1, NULL, NULL),
(416, 230, 18, '2017-05-05', 5, 'Resolucion por avarano : La generación de la boleta por baja comercial se hace por el administrador de deuda, tipo de período :\n \nCOMERCIO E INDUSTRIA   |  SS - SUBTASAS GENERALES   |  S3 - BAJA DE HABILITACION COMERCIAL \n \nY buscas el item “Certif.Baja Comercial”\n', NULL),
(417, 227, 18, '2017-05-05', 5, 'Resolucion por avarano : Las fechas de las cuotas generadas no se pueden modificar.\nYa anulé la boleta que vencia en mayo y liquidé la que corresponde( vencimiento 17/4)\n', NULL),
(418, 233, 18, '2017-05-05', 1, NULL, NULL),
(419, 234, 21, '2017-05-05', 1, NULL, NULL),
(420, 234, 21, '2017-05-05', 5, 'Resolucion por eklein : Utilicé el grupo SECHACIENDA-S2, tiene los mismos permisos que S1, pero además pueden ver el resumen del padrón de sueldos.\nLos siguientes usuarios:\nAMARQUEZ\nJFRANCISCO\nKLOPEZ', NULL),
(421, 235, 21, '2017-05-05', 1, NULL, NULL),
(422, 236, 21, '2017-05-05', 1, NULL, NULL),
(423, 237, 17, '2017-05-05', 1, NULL, NULL),
(424, 238, NULL, '2017-05-05', 1, NULL, NULL),
(425, 238, 3, '2017-05-05', 5, 'Resolucion por mbenditti : Se creo nuevo usuario smtestore en PGM', NULL),
(426, 239, NULL, '2017-05-05', 1, NULL, NULL),
(427, 239, 21, '2017-05-05', 2, NULL, NULL),
(428, 239, 21, '2017-05-05', 5, 'Resolucion por eklein : Los puse en SECHACIENDA-S2.', NULL),
(429, 240, 27, '2017-05-08', 1, NULL, NULL),
(430, 121, 18, '2017-05-08', 5, 'Resolucion por avarano : Hice  Reporte: POLTRI-Notas crédito-débito\n\n', NULL),
(431, 233, 18, '2017-05-08', 5, 'Resolucion por avarano : Talo-Talonarios Sociales con el que podes sacar un padrón de talonarios que adeudan cuotas y que se hicieron en concepto de \n\nAyudas sociales a Personas                        \nEquipamiento Comunitario-PilaresSociales          \nEquipamiento Familiar                             \nFortalecimiento Comunidad Caat 7                  \nFo.SE.Mu.                                         \nFondo Rotatorio Economia Social     \nMejoramiento Habitacional Caat 10                 \nMejoramiento Habitacional                         \nMicrocreditos Programa Agricultura Familiar       \nRecupero de materiales                            \nREDES   \nDesarrollo Social                                 \nTraslado Habitacional\n\notro listado : Nombre Reporte: Talo-Estado cuotas sociales generadas\n\n', NULL),
(432, 228, 18, '2017-05-08', 5, 'Resolucion por avarano :  Reporte: Talo-Talonarios por tipo', NULL),
(433, 241, NULL, '2017-05-08', 1, NULL, NULL),
(434, 232, 18, '2017-05-08', 7, NULL, NULL),
(435, 242, NULL, '2017-05-08', 1, NULL, NULL),
(436, 231, 21, '2017-05-08', 2, NULL, NULL),
(437, 237, 21, '2017-05-08', 3, NULL, NULL),
(438, 236, 21, '2017-05-08', 3, NULL, NULL),
(439, 235, 21, '2017-05-08', 3, NULL, NULL),
(440, 231, 21, '2017-05-08', 3, NULL, NULL),
(441, 21, 3, '2017-05-08', 5, 'Resolucion por mbenditti : ', NULL),
(442, 28, 3, '2017-05-08', 5, 'Resolucion por mbenditti : ', NULL),
(443, 29, 3, '2017-05-08', 5, 'Resolucion por mbenditti : ', NULL),
(444, 30, 3, '2017-05-08', 5, 'Resolucion por mbenditti : ', NULL),
(445, 32, 3, '2017-05-08', 5, 'Resolucion por mbenditti : ', NULL),
(446, 35, 3, '2017-05-08', 5, 'Resolucion por mbenditti : ', NULL),
(447, 36, 3, '2017-05-08', 5, 'Resolucion por mbenditti : ', NULL),
(448, 243, 21, '2017-05-08', 1, NULL, NULL),
(449, 243, 21, '2017-05-08', 3, NULL, NULL),
(450, 241, 3, '2017-05-08', 5, 'Resolucion por mbenditti : Era por los permisos de devengar multiples periodos/cuotas.\nSe arregla para la proxima actualizacion de PGM.', NULL),
(451, 244, NULL, '2017-05-09', 1, NULL, NULL),
(452, 245, NULL, '2017-05-09', 1, NULL, NULL),
(453, 246, NULL, '2017-05-09', 1, NULL, NULL),
(454, 247, NULL, '2017-05-09', 1, NULL, NULL),
(455, 247, 3, '2017-05-09', 5, 'Resolucion por mbenditti : Lo miodificaron desde Córdoba.', NULL),
(456, 245, 3, '2017-05-10', 4, NULL, NULL),
(457, 248, NULL, '2017-05-11', 1, NULL, NULL),
(458, 248, 27, '2017-05-11', 7, NULL, NULL),
(459, 249, NULL, '2017-05-11', 1, NULL, NULL),
(460, 249, 27, '2017-05-11', 5, 'Resolucion por bovando : Se solicito a PGM que cambien por la base de datos, una cuenta de comercio que tenia comillas en el texto describiendo el tipo de publicidad.\nPGM realizo la modificacion y ya se puede modificar el tipo de publicidad y demas.', NULL),
(461, 245, 3, '2017-05-11', 5, 'Resolucion por mbenditti : Ya poseia el permiso\n', NULL),
(462, 244, 3, '2017-05-11', 5, 'Resolucion por mbenditti : Se creo un usuario para los sistemas PGM, PGM nuevo, WebDoc y Fichadas.', NULL),
(464, 243, 21, '2017-05-11', 5, 'Resolucion por eklein : Cristian envió la partida a la cuál se imputan los pasantes.', NULL),
(465, 251, 21, '2017-05-11', 1, NULL, NULL),
(466, 252, 21, '2017-05-11', 1, NULL, NULL),
(467, 252, 21, '2017-05-11', 5, 'Resolucion por eklein : La puse en el mismo grupo que Cristian Paredes', NULL),
(468, 251, 21, '2017-05-11', 5, 'Resolucion por eklein : Hice los de Personal, estamos a la espera del listado de Usuarios de RRHH.', NULL),
(469, 253, 21, '2017-05-11', 1, NULL, NULL),
(470, 254, 21, '2017-05-11', 1, NULL, NULL),
(471, 255, 21, '2017-05-12', 1, NULL, NULL),
(472, 256, 21, '2017-05-15', 1, NULL, NULL),
(473, 257, NULL, '2017-05-15', 1, NULL, NULL),
(474, 257, 3, '2017-05-15', 5, 'Resolucion por mbenditti : Se agregó el permiso solicitado.', NULL),
(475, 258, NULL, '2017-05-16', 1, NULL, NULL),
(476, 254, 21, '2017-05-16', 3, NULL, NULL),
(477, 255, 21, '2017-05-16', 3, NULL, NULL),
(478, 259, 21, '2017-05-16', 1, NULL, NULL),
(479, 259, 21, '2017-05-16', 5, 'Resolucion por eklein : Ya le di el alta en PGM Nuevo en SUELDOS-S1 y le dejé los permisos solicitados en el viejo.', NULL),
(480, 260, 21, '2017-05-16', 1, NULL, NULL),
(481, 261, 21, '2017-05-16', 1, NULL, NULL),
(482, 260, 21, '2017-05-16', 5, 'Resolucion por eklein : ', NULL),
(483, 261, 21, '2017-05-16', 5, 'Resolucion por eklein : ', NULL),
(484, 258, 18, '2017-05-16', 5, 'Resolucion por avarano : Se modificó la fecha según lo solicitado.', NULL),
(485, 231, 21, '2017-05-16', 5, 'Resolucion por eklein : Matías hizo una página web para importar el reporte generado con el PGM Nuevo en .csv y levantarlo\ndede ahí y así poder generar los recibos con el sistemas de sueldos web.', NULL),
(486, 262, 18, '2017-05-16', 1, NULL, NULL),
(487, 262, 18, '2017-05-17', 5, 'Resolucion por avarano : Se realizaron las modificaciones', NULL),
(488, 263, 21, '2017-05-17', 1, NULL, NULL),
(489, 263, 21, '2017-05-17', 5, 'Resolucion por eklein : Les di los permisos en RRHH-S1 en el reporte indicado.', NULL),
(490, 253, 21, '2017-05-17', 5, 'Resolucion por eklein : Lo arreglaron desde PGM. Lo probó Claudia de Sueldos. Ya funciona.', NULL),
(491, 256, 21, '2017-05-17', 3, NULL, NULL),
(492, 264, 21, '2017-05-17', 1, NULL, NULL),
(493, 264, 21, '2017-05-17', 5, 'Resolucion por eklein : Hola Fabiana, ya hice el cambio y creé la Novedad 4230 APORTE SEG.VIDA RETROACTIVO. Deberán poner el valor a deducir en VALOR_1 de la Novedad individual.\n \nRecuerden que la falta de vinculación les va a aparecer a fin de mes, pidan la partida a Cristian para ésta novedad.\n \nSaludos!\n \nEstefanía\n', NULL),
(494, 265, NULL, '2017-05-17', 1, NULL, NULL),
(495, 266, 18, '2017-05-17', 1, NULL, NULL),
(496, 267, 18, '2017-05-17', 1, NULL, NULL),
(497, 265, 18, '2017-05-17', 5, 'Resolucion por avarano : Se dio permiso cambio de año a grupo tesoreria-s3', NULL),
(498, 266, 18, '2017-05-17', 5, 'Resolucion por avarano : La afectación está desafectada, por eso no puede utilizarse.', NULL),
(499, 267, 18, '2017-05-17', 5, 'Resolucion por avarano : En el sistema nuevo no existe tipo de recibo ingreso por retenciones, asi que todos los ingresos son por recaudacion de caja.\nNo se puede imprimir ese tipo de recibo.\nLo ven del libro banco.', NULL),
(500, 235, 21, '2017-05-17', 5, 'Resolucion por eklein : Se agregó el que chipeó y se modifico el término Propietarios en el Menu y el Manu principal.', NULL),
(501, 268, 18, '2017-05-17', 1, NULL, NULL),
(502, 269, 18, '2017-05-18', 1, NULL, NULL),
(503, 256, 21, '2017-05-18', 5, 'Resolucion por eklein : Hola Estefania, ya se resolvieron los problemas en  el reporte para que \nlo prueben nuevamente.\n\nSaludos\n\n    Ing. Dehaine Alejandro\n      Consultor Senior\n    PROGRAM CONSULTORES S.A.\n         Juan del Campillo 779\n(X5000GTO) - Córdoba - Argentina\nTel/fax (0351) 4474200 y rotativas\nWeb-Site:www.municipalidad.com\n\n\nYa lo probamos, funciona. Sacaron los SUBTOTALES para que sume bien.', NULL),
(504, 270, 18, '2017-05-18', 1, NULL, NULL),
(505, 271, 18, '2017-05-18', 1, NULL, NULL),
(506, 272, 18, '2017-05-18', 1, NULL, NULL),
(507, 273, NULL, '2017-05-18', 1, NULL, NULL),
(508, 274, 3, '2017-05-18', 1, NULL, NULL),
(509, 275, 3, '2017-05-18', 1, NULL, NULL),
(510, 276, 18, '2017-05-18', 1, NULL, NULL),
(511, 275, 3, '2017-05-18', 5, 'Resolucion por mbenditti : Se asigno el numero de legajo como clave.', NULL),
(512, 277, 18, '2017-05-18', 1, NULL, NULL),
(513, 271, 18, '2017-05-18', 5, 'Resolucion por avarano : En el campo subarea habia quedado grabado un punto a continuacion del codigo. Se corrigio.', NULL),
(514, 273, 18, '2017-05-18', 4, NULL, NULL),
(515, 278, 21, '2017-05-19', 1, NULL, NULL),
(516, 278, 21, '2017-05-19', 5, 'Resolucion por eklein : Ubicada en grupo SECGOBIERNO-S2, mismos permisos que SECGOBIERNO-S3 pero además con padrón de sueldos.', NULL),
(517, 272, 18, '2017-05-19', 5, 'Resolucion por avarano : No se estaban mandando todos los talonarios, solo los activos.\nEl activo determina si se pueden o no hacer NUEVOS talonarios de ese tipo. ', NULL),
(518, 269, 18, '2017-05-19', 5, 'Resolucion por avarano : Modificado por Córdoba', NULL),
(519, 279, 18, '2017-05-19', 1, NULL, NULL),
(520, 277, 18, '2017-05-19', 3, NULL, NULL),
(521, 277, 18, '2017-05-19', 4, NULL, NULL),
(522, 277, 18, '2017-05-19', 3, NULL, NULL),
(523, 273, 18, '2017-05-19', 3, NULL, NULL),
(524, 280, NULL, '2017-05-19', 1, NULL, NULL),
(525, 280, 18, '2017-05-19', 4, NULL, NULL),
(526, 280, 18, '2017-05-19', 3, NULL, NULL),
(527, 281, 18, '2017-05-19', 1, NULL, NULL),
(528, 282, 18, '2017-05-19', 1, NULL, NULL),
(529, 125, 25, '2017-05-19', 2, NULL, NULL),
(530, 125, 25, '2017-05-19', 1, 'Resolucion por dalvarez : no ', NULL),
(531, 283, NULL, '2017-05-19', 1, NULL, NULL),
(532, 284, 18, '2017-05-19', 1, NULL, NULL),
(539, 287, 18, '2017-05-19', 1, NULL, NULL),
(555, 288, 18, '2017-05-23', 1, NULL, NULL),
(556, 289, NULL, '2017-05-23', 1, NULL, NULL),
(557, 290, 18, '2017-05-23', 1, NULL, NULL),
(558, 291, 18, '2017-05-23', 1, NULL, NULL),
(559, 292, NULL, '2017-05-24', 1, NULL, NULL),
(560, 293, NULL, '2017-05-24', 1, NULL, NULL),
(561, 294, 24, '2017-05-24', 1, NULL, NULL),
(562, 294, 3, '2017-05-24', 1, 'Resolucion por mbenditti : Falta poner mezcla', NULL),
(563, 294, 3, '2017-05-24', 4, 'Resolucion por mbenditti : Falta poner mezcla', NULL),
(564, 295, NULL, '2017-05-24', 1, NULL, NULL),
(565, 296, 21, '2017-05-24', 1, NULL, NULL),
(566, 297, 21, '2017-05-24', 1, NULL, NULL),
(567, 296, 21, '2017-05-24', 5, 'Resolucion por eklein : Se le dió el permiso ABST_APRPA', NULL),
(568, 297, 21, '2017-05-24', 5, 'Resolucion por eklein : Ya tenían los permisos, sólo se dió de alta el usuario SRANQUEHUE que no tenía, en CULTURA-S3.', NULL),
(569, 288, 18, '2017-05-26', 5, 'Resolucion por avarano : enviado', NULL),
(570, 281, 18, '2017-05-26', 5, 'Resolucion por avarano : realizado reporte Nombre Reporte: Contr-Padron estado deuda por tipo obra', NULL),
(571, 298, 21, '2017-05-26', 1, NULL, NULL),
(572, 298, 21, '2017-05-26', 5, 'Resolucion por eklein : Hola Vanesa, el problema era porque quedó la novedad con el cálculo viejo con zona de surogancia, ahora quedó SAC - ZONA como debe ser.\n\nSaludos\n\nEstefanía\n(Sistemas - Interno 140)\n\nAdemás cuando de PGM hicieron el cambio en la novedad 10300 para que no se incluya la zona en la subrogancia,\nse olvidaron de sacar AZONA = MONTO + MONTO_ZO_SUB , que sumaba la zona de la subrogancia a la zona fria (que ahora ya la incluye)', NULL),
(573, 299, 18, '2017-05-26', 1, NULL, NULL),
(574, 299, 18, '2017-05-26', 5, 'Resolucion por avarano : nc automatica para registrar pago anual , con importe distinto.', NULL),
(575, 300, 21, '2017-05-26', 1, NULL, NULL),
(576, 300, 21, '2017-05-26', 5, 'Resolucion por eklein : Había quedado mal el cálculo porque se tenía en cuenta la zona de la subrogancia, como ya no está incluida en la misma, cambia \nel cálculo. Ver cambio en novedades.', NULL),
(577, 301, 27, '2017-05-26', 1, NULL, NULL),
(578, 302, 21, '2017-05-26', 1, NULL, NULL),
(579, 302, 21, '2017-05-26', 5, 'Resolucion por eklein : Se le asignaron los permisos al legajo 375 y se la cambió a perfil Jefe.', NULL),
(580, 303, 21, '2017-05-26', 1, NULL, NULL),
(581, 237, 21, '2017-05-26', 4, NULL, NULL),
(582, 304, 18, '2017-05-26', 1, NULL, NULL),
(583, 305, 18, '2017-05-26', 1, NULL, NULL),
(584, 306, 18, '2017-05-26', 1, NULL, NULL),
(585, 307, 18, '2017-05-26', 1, NULL, NULL),
(586, 308, 18, '2017-05-26', 1, NULL, NULL),
(587, 308, 18, '2017-05-26', 5, 'Resolucion por avarano : 15939\n1 0935456406 cobrado\n2 0935456414 cobrado\n3 0935456422 R08 orden no pagar\n\n15928\n1 0935456286 cobrado\n2 0935456294 R10 falta de fondos\n3 0935456309 R10 falta de fondos\n\nFalta generar deuda a los rechazados del vencimiento tasa 10/04. Mande el archivo a cordoba para que lo carguen\n', NULL),
(588, 306, 18, '2017-05-26', 5, 'Resolucion por avarano : Estas notas de credito no estaba activo. Active y di permisos. Martes manda mail.', NULL),
(589, 307, 18, '2017-05-26', 4, NULL, NULL),
(590, 307, 18, '2017-05-26', 3, NULL, NULL),
(591, 307, 18, '2017-05-26', 4, NULL, NULL),
(592, 309, 18, '2017-05-29', 1, NULL, NULL),
(593, 310, NULL, '2017-05-29', 1, NULL, NULL),
(594, 309, 18, '2017-05-29', 4, NULL, NULL),
(595, 309, 18, '2017-05-29', 5, 'Resolucion por avarano : Pgm generó los débitos correspondientes a la cuota 3.\nNo se habían mandado las boletas mal acreditadas.', NULL),
(596, 246, 3, '2017-05-29', 3, NULL, NULL),
(597, 311, 21, '2017-05-29', 1, NULL, NULL),
(598, 311, 21, '2017-05-29', 5, 'Resolucion por eklein : Listo!\n \nUsuarios\n \nBWINKELMAN clave winkelman\n \nMEGGERS clave eggers\n \nLuego la cambian de General--> Seguridad --> Cambio de contraseña\n \nSaludos!\n \nEstefanía\n(Sistemas - Interno 140)', NULL),
(599, 312, 3, '2017-05-29', 1, NULL, NULL),
(600, 313, 3, '2017-05-29', 1, NULL, NULL),
(601, 314, 3, '2017-05-29', 1, NULL, NULL),
(602, 310, 18, '2017-05-29', 4, NULL, NULL),
(603, 310, 18, '2017-05-30', 5, 'Resolucion por avarano : Pgm lo incorporó.', NULL),
(604, 291, 18, '2017-05-30', 5, 'Resolucion por avarano : se anularon en legales\n', NULL),
(605, 280, 18, '2017-05-30', 5, 'Resolucion por avarano : modifico pgm', NULL),
(606, 284, 18, '2017-05-30', 5, 'Resolucion por avarano : no hay ningun inconveniente', NULL),
(607, 277, 18, '2017-05-30', 4, NULL, NULL),
(608, 98, 18, '2017-05-30', 4, ' Mensaje de avarano: Respondieron que no encontraron nada mal.\n\nLos procesas eran 8000958357 y 8031989232. Hector encontro que se ambos tienen igual id_proceso\nl', NULL),
(609, 98, 18, '2017-05-30', 5, ' Mensaje de avarano: Respondieron que no encontraron nada mal.\n\nLos procesas eran 8000958357 y 8031989232. Hector encontro que se ambos tienen igual id_proceso\nl\nResolucion por avarano : quizas traiga problemas.', NULL),
(610, 315, 18, '2017-05-30', 1, NULL, NULL),
(611, 315, 18, '2017-05-30', 5, 'Resolucion por avarano : di de alta tipo plan, periodo e item. vincule y envie manual por mail', NULL),
(612, 316, 18, '2017-05-30', 1, NULL, NULL),
(613, 316, 18, '2017-05-30', 5, 'Resolucion por avarano : di de alta la persona, no tenian permiso', NULL),
(614, 317, 18, '2017-05-30', 1, NULL, NULL),
(615, 111, 18, '2017-05-30', 5, 'Resolucion por avarano : no lo hicieron', NULL),
(616, 109, 18, '2017-05-30', 5, 'Resolucion por avarano : no volvio a suceder, aparentemente', NULL),
(617, 110, 18, '2017-05-30', 5, 'Resolucion por avarano : Fernando dijo  que lo solucionarion. no volvio a pasar.', NULL),
(618, 318, 18, '2017-05-30', 1, NULL, NULL),
(619, 318, 18, '2017-05-30', 4, NULL, NULL),
(620, 319, 21, '2017-05-31', 1, NULL, NULL),
(621, 319, 21, '2017-05-31', 5, 'Resolucion por eklein : La Novedad 1240 se encuentra cargada tanto en Remunerativo (Ya no se usa) como en Subsidio.\nEl problema es que está 2 veces con el mismo código de Novedad, por lo que en el momento\nde la migración, ésto debe haber causado la no vinculación del Subsidio.\nCristian ya cargó las cuentas correspondientes.', NULL),
(622, 320, 21, '2017-05-31', 1, NULL, NULL),
(623, 320, 21, '2017-05-31', 4, NULL, NULL),
(624, 321, 18, '2017-05-31', 1, NULL, NULL),
(625, 321, 18, '2017-05-31', 3, NULL, NULL),
(626, 321, 18, '2017-05-31', 4, NULL, NULL),
(627, 322, 21, '2017-05-31', 1, NULL, NULL),
(628, 322, 21, '2017-05-31', 5, 'Resolucion por eklein : Ya cambiaron el reporte.', NULL),
(629, 323, 21, '2017-05-31', 1, NULL, NULL),
(630, 324, 21, '2017-05-31', 1, NULL, NULL),
(631, 325, 21, '2017-05-31', 1, NULL, NULL),
(632, 325, 21, '2017-05-31', 5, 'Resolucion por eklein : Estefanía:\n\nLo solicitado no es viable ya que cada empleado tiene asignada su área\nadministrativa específica y la vinculación de las novedades se debe realizar para\ncada área administrativa en caso de corresponder partidas discriminadas o para todas\nlas áreas cuando todo se imputa a una sola partida. No existe discriminación por\ntipos de empleado en la vinculación de novedades.\n\nSaludos.-\n\n\nIng. Fernando M. Gonzalez\nConsultor Senior\nProgram Consultores S.A.\nJuan del Campillo 779\n(X5000GTO) Cordoba - Argentina\nTel/fax (0351) 4474200 y rotativas\nWeb.Site: www.municipalidad.com', NULL),
(633, 326, 21, '2017-05-31', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `id_permiso` int(11) NOT NULL,
  `nombre_permiso` varchar(30) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`id_permiso`, `nombre_permiso`) VALUES
(4, 'misTickets'),
(5, 'nuevoTicket'),
(6, 'baseConocimiento'),
(7, 'MenuAdministracion'),
(8, 'administrarTicketV'),
(9, 'verHistorialTicketB'),
(10, 'eliminarTicketB'),
(11, 'cambiarEstadoB'),
(12, 'MenuEstadoPGM'),
(13, 'MenuConfiguracion'),
(14, 'areasV'),
(15, 'eliminarAreaB'),
(16, 'modificarAreaB'),
(17, 'nuevAreaB'),
(18, 'asuntosV'),
(19, 'eliminarAsuntoB'),
(20, 'nuevoAsuntoB'),
(21, 'serviciosV'),
(22, 'eliminarServiciosB'),
(23, 'nuevoServicioB'),
(24, 'RazonesTransferenciaV'),
(25, 'eliminarRazonB'),
(26, 'nuevaRazonB'),
(27, 'usuariosV'),
(28, 'eliminarUsuarioB'),
(29, 'dehsabilitarUsuarioB'),
(30, 'habilitarUsuarioB'),
(31, 'resetearClaveB'),
(32, 'nuevoUsuarioB'),
(33, 'cambiarRolUsuarioB'),
(34, 'AsignarAsuntosV'),
(35, 'RolesV'),
(36, 'eliminarRolB'),
(37, 'nuevoRolB'),
(38, 'agregarPermisoB'),
(39, 'quitarPermisoB'),
(40, 'EmpleadosV'),
(41, 'eliminarEmpleadoB'),
(42, 'nuevoEmpleadoB'),
(43, 'modificarEmpeladoB'),
(44, 'versiones');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `razones_transferencias`
--

CREATE TABLE `razones_transferencias` (
  `id_razon` int(11) NOT NULL,
  `nombre_razon` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `razones_transferencias`
--

INSERT INTO `razones_transferencias` (`id_razon`, `nombre_razon`) VALUES
(1, 'Asunto erroneo'),
(2, 'Ya realice mi parte');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respuestas`
--

CREATE TABLE `respuestas` (
  `id_ticket` int(11) NOT NULL,
  `respuesta` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id_rol` int(11) NOT NULL,
  `nombre_rol` varchar(30) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id_rol`, `nombre_rol`) VALUES
(1, 'administrador'),
(2, 'user'),
(3, 'sistemas'),
(4, 'jefe de area');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles_permisos`
--

CREATE TABLE `roles_permisos` (
  `fk_rol` int(11) NOT NULL,
  `fk_permiso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `roles_permisos`
--

INSERT INTO `roles_permisos` (`fk_rol`, `fk_permiso`) VALUES
(1, 4),
(2, 4),
(3, 4),
(4, 4),
(1, 5),
(2, 5),
(3, 5),
(4, 5),
(1, 6),
(2, 6),
(3, 6),
(4, 6),
(1, 7),
(3, 7),
(4, 7),
(1, 8),
(1, 9),
(4, 9),
(1, 10),
(1, 11),
(4, 11),
(1, 12),
(3, 12),
(4, 12),
(1, 13),
(3, 13),
(4, 13),
(1, 14),
(4, 14),
(1, 15),
(1, 16),
(1, 17),
(4, 17),
(1, 18),
(4, 18),
(1, 19),
(1, 20),
(4, 20),
(1, 21),
(4, 21),
(1, 22),
(1, 23),
(4, 23),
(1, 24),
(4, 24),
(1, 25),
(1, 26),
(4, 26),
(1, 27),
(3, 27),
(1, 28),
(1, 29),
(3, 29),
(1, 30),
(3, 30),
(1, 31),
(3, 31),
(1, 32),
(3, 32),
(1, 33),
(1, 34),
(4, 34),
(1, 35),
(1, 36),
(1, 37),
(1, 38),
(1, 39),
(1, 40),
(4, 40),
(1, 41),
(1, 42),
(4, 42),
(1, 43),
(4, 43),
(1, 44);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

CREATE TABLE `servicios` (
  `id_asuntoS` int(11) NOT NULL,
  `nombre_asuntoS` varchar(70) COLLATE utf8_spanish2_ci NOT NULL,
  `pertenece` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `servicios`
--

INSERT INTO `servicios` (`id_asuntoS`, `nombre_asuntoS`, `pertenece`) VALUES
(27, 'Alta usuario (Legajo y Documento)', 11),
(28, 'Baja usuario (Legajo y Documento)', 11),
(29, 'Modificar permisos (Legajo y Documento)', 11),
(30, 'Pedido auditoria', 11),
(45, 'Estadisticas', 11),
(46, 'Unificacion de personas', 11),
(47, 'No funciona el crystal', 11),
(48, 'Otros problemas', 11),
(49, 'Diferencias en sueldos', 12),
(50, 'Actualizaciones de sueldos', 12),
(51, 'Alta usuario (Legajo y Documento)', 13),
(52, 'Baja usuario (Legajo y Documento)', 13),
(53, 'Configuracion de usuario (Permisos e instancias)', 13),
(54, 'Pedido de estadisticas (Arribos y salidas)', 14),
(55, 'Error de vinculacion (Aclarar cedulon/es)', 15),
(56, 'No se puede cobrar cedulon (Error de vinculo)', 15),
(57, 'Alta usuario cajero', 15),
(58, 'Baja usuario cajero', 15),
(59, 'Pedido de nuevo correo electronico', 16),
(60, 'Baja de correo electronico', 16),
(62, 'Problemas con el turnero', 17),
(66, 'Generacion de informes de sueldos', 12),
(67, 'Programacion de liquidacion', 12),
(68, 'Alta novedades', 12),
(69, 'Vinculacion de novedades', 12),
(70, 'Modificacion de novedades', 12),
(71, 'Baja novedades', 12),
(74, 'Actualizacion acumuladores de ganancia', 12),
(76, 'Elaboracion de informes de ganancias', 12),
(77, 'Generacion de recibos de manos a la obra', 12),
(78, 'Alta y modificacion de un cargo', 12),
(79, 'Alta y modificacion de funcion', 12),
(80, 'Alta y modificacion de area administrativa', 12),
(81, 'Alta y modificacion de organigrama', 12),
(82, 'Alta y modificacion de categoria', 12),
(83, 'Alta y modificacion de tipo de empleado', 12),
(84, 'Alta y modificacion de ubicacion de empelado', 12),
(85, 'Modificacion de sueldo basico en las categorias', 12),
(86, 'Modificacion de sueldo basico en las subrrogancias', 12),
(87, 'Actualizacion de salario minimo vital y movil', 12),
(88, 'Actualizacion de limites para asignaciones familiares', 12),
(89, 'Agregar nuevo servicio (Cual,por y para que)', 19),
(90, 'Mal funcionamiento del CPU', 17),
(91, 'Problemas con el mouse o teclado', 17),
(92, 'Mal funcionamiento de la impresora', 17),
(93, 'No funciona internet', 17),
(94, 'No funcionan los mail', 17),
(95, 'Cambio-Movimiento de impresoras', 17),
(96, 'No puedo enviar correos', 16),
(97, 'Saber si esta entregado', 20),
(98, 'Saber en que secretaria se encuentra', 20),
(99, 'Como realizar un pedido de abastecimiento', 20),
(100, 'Da error al cargar el pedido', 20),
(101, 'A que cuenta lo tengo que imputar', 20),
(102, 'No funciona', 21),
(103, 'No puede ingresar', 15),
(104, 'Alta usuario(Legajo y Documento)', 21),
(105, 'Baja usuario(Legajo y Documento)', 21),
(106, 'Modificar usuario(Legajo y Documento)', 21),
(107, 'Alta de calles', 21),
(108, 'Alta de barrios', 21),
(109, 'Creacion de reportes', 21),
(110, 'Otro problemas', 21),
(111, 'Error de migracion', 21),
(112, 'Instalacion servidor web', 22),
(113, 'Nuevo usuario', 23),
(114, 'Permisos para consulta general', 23),
(115, 'talonarios', 24),
(116, 'tsm', 24),
(117, 'tish', 24),
(118, 'pagos electronicos', 24),
(119, 'obras contribuciones', 24),
(120, 'listados', 24),
(121, 'sueldos', 24),
(122, 'compras', 24),
(123, 'obras privadas', 24),
(124, 'trib.faltas', 24),
(125, 'contaduria', 24),
(126, 'tesoreria', 24),
(127, 'otros', 24),
(128, 'Auditoria', 21),
(129, 'Agregar Entidad de cobro de multas', 13),
(130, 'Resetear Clave', 13),
(131, 'Cambiar documento de plantillas', 13),
(132, 'Modificación Permisos', 21),
(134, 'Modificación Aplicación', 25),
(135, 'Modificación BDD', 25),
(136, 'Errores de PGM', 12),
(137, 'Pagos electronicos', 21),
(138, 'Modificacion', 19),
(139, 'Otros', 23);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tickets`
--

CREATE TABLE `tickets` (
  `id_ticket` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creador` int(11) NOT NULL,
  `servicio` int(11) NOT NULL,
  `observacion` varchar(3000) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `patrimonio` varchar(15) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `tiempoResolucion` datetime DEFAULT NULL,
  `adjunto` varchar(100) COLLATE utf8_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `tickets`
--

INSERT INTO `tickets` (`id_ticket`, `fecha`, `hora`, `creador`, `servicio`, `observacion`, `patrimonio`, `tiempoResolucion`, `adjunto`) VALUES
(16, '2016-11-16', '2016-11-16 15:12:29', 24, 54, '16/11/2016 terminal de omnubus o puede ingresar al cajero daniel solucionado', NULL, NULL, NULL),
(17, '2016-11-16', '2016-11-16 18:11:05', 24, 54, 'diaz jorge no puede ingresar a cajero', NULL, NULL, NULL),
(20, '2016-11-16', '2016-11-16 17:34:55', 24, 103, 'USUARIO JORGE DIAZ', NULL, NULL, NULL),
(21, '2016-11-16', '2016-11-16 15:39:38', 24, 103, 'RAQUELINA TRIBUBAL DE FALTAS 2', NULL, NULL, NULL),
(22, '2016-11-16', '2016-11-16 18:12:04', 24, 55, 'TRANSITO PLAYON 16/11/16', NULL, NULL, NULL),
(23, '2016-11-16', '2016-11-18 16:10:57', 24, 102, 'LEGALES SANDRA NO PUEDE IMPRIMIR DEUDA', NULL, NULL, NULL),
(24, '2016-11-16', '2016-11-16 18:13:00', 24, 102, 'ana sangoy problemas con contraseña', NULL, NULL, NULL),
(25, '2016-11-16', '2016-11-18 12:20:29', 24, 27, 'norma trib.faltas 2 problema con archivar textos', NULL, NULL, NULL),
(26, '2016-11-16', '2016-11-18 12:21:46', 24, 102, 'maria de politicas tributarias usuario caducado 14/10/16', NULL, NULL, NULL),
(27, '2016-11-16', '2016-11-29 15:05:19', 24, 102, 'hacienda error de servidor 500', NULL, NULL, NULL),
(28, '2016-11-16', '2016-11-16 16:00:00', 24, 48, 'bety de catastro dice que no fue informada como se va implementar el nuevo PGM', NULL, NULL, NULL),
(29, '2016-11-16', '2016-11-16 16:02:30', 24, 48, 'marta de fiszcalicacion no puede cargar la calle al nuevo proseso', NULL, NULL, NULL),
(30, '2016-11-16', '2016-11-16 16:07:31', 24, 48, 'susana  urrutia de mesa entradas de morerno y moreno dice que se mudan otra vez solicita tecnico para configurar impresoras\n', NULL, NULL, NULL),
(31, '2016-11-16', '2016-11-16 17:32:29', 24, 48, 'transito y tte. tiene problemas con tiket de taxis y remises', NULL, NULL, NULL),
(32, '2016-11-16', '2016-11-16 16:14:31', 24, 103, 'chofa mesa entrada civico', NULL, NULL, NULL),
(33, '2016-11-16', '2016-11-29 15:06:11', 24, 102, 'graciela de cultura no puede ingresar con contraseña', NULL, NULL, NULL),
(34, '2016-11-16', '2016-11-16 16:47:04', 24, 48, 'lago moreno nora no puede hacer recibo de libre deudas', NULL, NULL, NULL),
(35, '2016-11-16', '2016-11-16 16:24:01', 24, 48, 'idalinda de transito y tte. pregunta sobre montos de la tarifaria ', NULL, NULL, NULL),
(36, '2016-11-16', '2016-11-16 16:28:01', 24, 48, 'delegacion moreno beatriz el cajero no reconoce libre de dudas', NULL, NULL, NULL),
(37, '2016-11-16', '2016-11-18 12:38:31', 24, 48, 'susana davies transito problemas con modificacion personal', NULL, NULL, NULL),
(38, '2016-11-16', '2016-11-18 12:26:05', 24, 48, 'playon dudas con recibo digital', NULL, NULL, NULL),
(39, '2016-11-16', '2016-11-29 14:56:12', 24, 107, 'erika conribuciones pide cargar la calle GUTERO EN PGM N UEVO', NULL, NULL, NULL),
(40, '2016-11-16', '2016-11-16 17:44:42', 24, 48, 'mabel de inspeccion gral pregunta si hoy a la tarde pueden trabajar en webdoc la repuesta es si\n', NULL, NULL, NULL),
(41, '2016-11-18', '2016-11-29 14:55:49', 24, 107, 'Erika cargar la calle Guttero', NULL, NULL, NULL),
(42, '2016-11-18', '2016-11-18 12:22:31', 24, 51, 'Mabel de Inspeccion pregunta y a la tarde pueden trabajar con Webdoc', NULL, NULL, NULL),
(43, '2016-11-18', '2016-11-18 12:27:13', 24, 56, 'delegacion otto gabriela no puede imprimir cedulon', NULL, NULL, NULL),
(44, '2016-11-18', '2016-11-29 15:06:55', 24, 102, 'gaby delegacion otto menu de fito/impresora/cambio de domicilio/cierre temporario/', NULL, NULL, NULL),
(76, '2017-04-18', '2017-04-18 14:38:03', 30, 112, 'Solicita: Juan ramos\n Area Emisora: direccion de sistemas\n Observacion: Crear servidor web para la instalacion del comunicar luego de estar instalado se encarga Castro.', '', NULL, NULL),
(77, '2017-04-19', '2017-04-19 14:30:02', 3, 114, 'Solicita: Susana Urrutia\n Area Emisora: secretaria de turismo\n Observacion: Buen dia:\n\nSolicito se agregue al Permiso PGM, consulta general de fichada, a los agentes que a continuaciòn se detallan, que forman parte del personal de la Direccòn de Trabajo:\n\n- Gabriela Anteanao - Legajo 12912\n\n- Gabriela Torres - Legajo 14836\n\n- Gonzalo Jaquis - Legajo 14833\n\n\nUsuarios: rabarzua - surrutia\n\n\nMuchas gracias.\n\n\nSusana', '', NULL, NULL),
(78, '2017-04-19', '2017-04-19 14:39:43', 3, 114, 'Solicita: Lucas Perez\n Area Emisora: terminal omnibus\n Observacion: buenos días les solicito por este medio tengan a bien poder agregar dentro de mi permiso para ver fichadas a la agente Contreras María Alicia LEG N º 11330  y DÍAZ JORGE EDELIO LEG N º 1181  ya q no puedo visualizar sus ingresos y Salidas\n\ndesde ya muchas gracias los saludo\nATENTAMENTE\n \nLUCAS PEREZ', '', NULL, NULL),
(79, '2017-04-19', '2017-04-21 15:09:49', 3, 110, 'Solicita: Politicas tributarias\n Area Emisora: secretaria de hacienda\n Observacion: Hola ali me podrías decir por favor quien genero estas notas de débito, gracias!!!!!!!!!!!!!! María.\n \n0939313870 \n0939313846 \n0940292465 \n Mensaje de avarano: Las notas de débito las hizo Martín de Tesoreria. Las 1eras dos el 10/01/17 y la 3era el 13/2.\nY si mirás en la ficha de cada factura en la descripción te dice la causa.\nTodas las notas de débito/crédito que hacen referencia a diferencia de importes , por lo general, se hacen en Tesorería , por los pagos electrónicos.\n', '', NULL, NULL),
(80, '2017-04-19', '2017-04-19 14:43:57', 3, 104, 'Solicita: Mercado Comunitario\n Area Emisora: secretaria de hacienda\n Observacion: Buen día,\nsolicitamos tengan a bien dar permisos para entrar al PGM nuevo al usuario\nlanton leg.12350 para poder ver deudas, ordenes de pago, tasa personal y\ncon el PGM viejo poder hacer pedidos de abastecimiento, proyectos de\nresolución y ver resoluciones.\nGracias!\n\n', '', NULL, NULL),
(81, '2017-04-19', '2017-04-19 14:44:30', 3, 27, 'Solicita: Mercado Comunitario\n Area Emisora: secretaria de hacienda\n Observacion: Buen día,\nsolicitamos tengan a bien dar permisos para entrar al PGM nuevo al usuario\nlanton leg.12350 para poder ver deudas, ordenes de pago, tasa personal y\ncon el PGM viejo poder hacer pedidos de abastecimiento, proyectos de\nresolución y ver resoluciones.\nGracias!\n\n', '', NULL, NULL),
(82, '2017-04-19', '2017-04-19 14:46:03', 3, 106, 'Solicita: Ivana Calo\n Area Emisora: secretaria\n Observacion: Hola buen día solicito poder terner acceso al PGM nuevo mi nombre es Ivana Caló Legajo 11713\nactualmente tengo acceso a aprobacion de pedidos de abastecimiento de la Secretaría de Desarrollo Social Cultural y Deportivo que incluye todos los pedids generados en Sociales, Cultura y Deportes\naceesoa  padrón de personas, acceso a realizacion de proyectos de todos los tipos, pase por sistema de todos los proyectos generados en sociales, deportes y cultura. realizacion de pedidos de abastecimiento. y solicito acceso para poder ver las ordenes de pago.\n \natte Ivana. ', '', NULL, NULL),
(83, '2017-04-19', '2017-04-19 16:36:24', 3, 54, 'Solicita: Lucas Perez\n Area Emisora: terminal omnibus\n Observacion: por otra parte vuelvo a solicitarles  enviarme en carácter de URGENTE los movimientos de micros en terminal desde enero de 2016 a la fecha para elevar informe a la SUBSECRETARIA DE TTO. Y TTE. \ndesde ya muchas gracias los saludo\nATENTAMENTE\n \nLUCAS PEREZ', '', NULL, NULL),
(90, '2017-04-21', '2017-04-21 12:11:37', 18, 110, 'Solicita: yo\n Area Emisora: dpto. tributario\n Observacion: esta es la observacion', '', NULL, NULL),
(94, '2017-04-21', '2017-04-21 12:26:54', 18, 110, 'Solicita: Cecilia Cartes\n Area Emisora: dpto. tributario\n Observacion: Solicita modificar estado boleta 0940481666 de Anulado a Adeudado ya que se debe abonar. \nTambien quiere saber quien lo anulo.', '', NULL, NULL),
(95, '2017-04-21', '2017-04-21 12:45:11', 18, 115, 'Solicita: Alicia\n Area Emisora: direccion de sistemas\n Observacion: El talonario 38604 es al que no se le había migrado el item "Serv.Urbano y\nVialidad Rural"  en su cuota nro 4 por $ 709.08 .\nPara solucionarlo en el momento se agregó el item por el "administrador de\ndeuda".\n\nPara estar seguros de que no existan otras diferencias como\nesta,necesitamos que realicen un control de la migración.', '', NULL, NULL),
(98, '2017-04-21', '2017-05-30 16:32:08', 18, 127, 'Solicita: alicia\n Area Emisora: direccion de sistemas\n Observacion:  al realizar un procesa deuda a un bien de tasa por serv.a la\npropiedad , apareció relacionado otro procesa deuda que no tiene nada que\nver, inclusive esta pagado hace años.\n\nAdjunto captura de pantalla de lo sucedido.\n\nSi bien esto no ocasionó mayores problemas, ya que se cobró lo que\ncorrespondía, será conveniente ver que es lo que pasó.\n Mensaje de avarano: Pgm dijo que no sabe que paso , pero Hector descubrio que tiene que ver con los id_process.\nCoincidio id_proces nuevo con alguno ya existente.', '', NULL, NULL),
(104, '2017-04-21', '2017-04-21 13:31:13', 18, 115, 'Solicita: Erica\n Area Emisora: depto de contribuciones\n Observacion: desde el área de contribuciones solicitan el siguiente\nrequerimiento sobre las notificaciones por planes de pago.\nActualmente la notificación tiene los datos del titular del bien y no del\nencargado del plan de pago. Es necesario que la notificacion este a nombre\nde quien sea firmante del plan de pago. Este requerimiento obedece a la\nnecesidad de notificar correctamente a quien tramito el plan de pago.\n', '', NULL, NULL),
(105, '2017-04-21', '2017-04-21 13:34:49', 18, 127, 'Solicita: Lilian Baroni\n Area Emisora: direccion de sistemas\n Observacion: Tenemos reiteradas solicitudes de que se pueda volver a estado "Adeudado" \ncuotas que estén en cualquier estado excepto "Cobrado" y "Financiado".', '', NULL, NULL),
(106, '2017-04-21', '2017-04-21 13:43:34', 18, 127, 'Solicita: alicia\n Area Emisora: direccion de sistemas\n Observacion: En el padrón de residuos urbanos, que se liquidan las cuotas considerando\nlos adicionales facturados, aunque la cuota esté pagada, el adicional se\npuede pasar a adicional pendiente y después borrarlo.\nDe esta forma, se pierde el dato de los m3 recolectados.\nSerá posible que, cuando la boleta relacionada al adicional esté pagada,\nel sistema no deja pasar el adicional facturado a pendiente?', '', NULL, NULL),
(107, '2017-04-21', '2017-04-21 13:48:10', 18, 115, 'Solicita: alicia\n Area Emisora: direccion de sistemas\n Observacion: Al hacer un tipo de talonario con tasa de interés 0, y financiar una deuda\nde 31800$ dan 12 cuotas de 2649.99 en lugar de 2650.\nHaciendo lo mismo con el usuario prueba da bien.\nHay alguna configuración que tenga que cambiar?', '', NULL, NULL),
(108, '2017-04-21', '2017-04-21 13:52:26', 18, 120, 'Solicita: Erica\n Area Emisora: depto de contribuciones\n Observacion: Necesitamos que la ayuda de "Listado de tipos de conexiones por obra"\ndisponible para la realización de reportes muestre el concepto y no solo\nel tipo de conexión, para poder identificar la obra buscada.\nSe utiliza esta ayuda en el reporte "Contr-Listado datos por Obra".', '', NULL, NULL),
(109, '2017-04-21', '2017-04-21 13:57:46', 18, 127, 'Solicita: alicia\n Area Emisora: direccion de sistemas\n Observacion: Una consulta sobre la unificación de personas. En el sistema anterior\ncuando se unificaba, la persona original se borraba de la tabla de\npersonas. Ahora no es asi?\nPorque encontramos unos 450 que suponiamos se debían haber borrado, pero\nsiguen estando.\nAdjunto reporte con los registros encontrados, que saque con el siguiente\nselect:\n\nselect a.tipo_documento,a.documento,\nb.tipo_documento_original,b.documento_original,\nb.tipo_documento_nuevo,b.documento_nuevo,estado,fecha_proceso\nfrom personas a join cambio_clave b on a.tipo_documento =\nb.tipo_documento_original and a.documento = b.documento_original\nwhere  b.documento_original <> b.documento_nuevo\nand estado <> \'P\'\n\n Mensaje de avarano: Ejecutamos el proceso de unificacion a las 15:00 hs, verifique varios \ncasos de las personas cargadas para unificar y no se registraron \nproblemas. No obstante, sigan ejecutando el proceso fuera del horario de \natención al público y sin otro proceso masivo ejecutándose en paralelo, \ntal como me informaron que lo vienen haciendo.\n\n\n', '', NULL, NULL),
(110, '2017-04-21', '2017-04-21 14:00:41', 18, 126, 'Solicita: alicia\n Area Emisora: direccion de tesoreria\n Observacion: En el día de ayer el cajero "garanda" recaudo en una operación una boleta\nde talonario y una cuota de convenio de pago y no se imprimió el duplicado\ndel ticket correspondiente  al convenio. Tampoco salió cuando se hizo la\nreimpresión.\nFaltará alguna configuración?', '', NULL, NULL),
(111, '2017-04-21', '2017-04-21 14:03:51', 18, 115, 'Solicita: alicia\n Area Emisora: depto de contribuciones\n Observacion: Se puede hacer una carátula de talonario, distinta de la estandar, que no\ndetalle los períodos incluído en el plan  y tampoco muestre los importes\noriginal, total financiado anticipo y descuento?\nEsto es porque para cumplir (o casi) con una forma de financiación\nestablecida por resolucion, para obra de cloaca , se hicieron condiciones\nparticulares de financiacion , que incluyen descuentos en el monto a\nfinanciar. De esta manera se llega a montos  similares a los que pagarían\ncon las 12 primeras cuotas sin interés y luego 12, 24 o mas cuotas.(ver\ncorreo del 15/2 asunto nueva obra donde se adjunta resolución).\n Mensaje de avarano: dice Fernando que no se puede, pero hay que resolverlo.', '', NULL, NULL),
(112, '2017-04-21', '2017-04-21 14:37:30', 18, 127, 'Solicita: Lilian\n Area Emisora: hacienda\n Observacion: Se puede hacer un seguimiento de las notificaciones enviadas por el\n"Listado/Notificación de deuda"? Es para saber quienes pagaron la boleta\nde deuda generada en la notificación.', '', NULL, NULL),
(113, '2017-04-21', '2017-04-21 16:15:18', 18, 128, 'Solicita: Martin Velazquez\n Area Emisora: direccion de tesoreria\n Observacion: Verificar si corresponde nota debito 0940390001 de padron TSM 22945 , con item Seg.e Higiene.\n Mensaje de avarano: Se hizo por error, correspondia al padron de comercio 22945 del Banco Patagonia.\nEl usuario que lo hizo fue lbaroni, el 15/03/17.\n', '', NULL, NULL),
(114, '2017-04-21', '2017-04-21 17:32:22', 18, 117, 'Solicita: todos\n Area Emisora: sistemasEn el padrón de comercio, cuando se busca una cuenta hay que completar con los ceros. Podrá ser que se autocompleten , como en el padrón de tasa por servicios municipales?\n Observacion: En el padrón de comercio, cuando se busca una cuenta hay que completar con los ceros.\nPodrá ser que se autocompleten , como en el padrón de tasa por servicios municipales?', '', NULL, NULL),
(115, '2017-04-21', '2017-04-21 17:46:45', 27, 129, 'Solicita: Raquelina\n Area Emisora: tribunal faltas 2\n Observacion: Agregar comisaria 42 como entidad de cobro de multas en el webdoc.', '', NULL, NULL),
(116, '2017-04-21', '2017-04-21 17:50:13', 27, 112, 'Solicita: Juan Carlos\n Area Emisora: direccion de sistemas\n Observacion: Configurar maquina virtual con servidor web para el sistema de COMUNICAR', '', NULL, NULL),
(117, '2017-04-21', '2017-04-21 17:55:29', 21, 89, 'Solicita: Estefania Klein\n Area Emisora: direccion de sistemas\n Observacion: Mostrar ultima interfaz de ticketneitor', '', NULL, NULL),
(118, '2017-04-24', '2017-04-25 14:47:29', 18, 109, 'Solicita: Lilian- Marta\n Area Emisora: Politicas tributarias\n Observacion: Listado por obra cloaca san francisco contado y por plan , cantidad ,pagado ,consolidado.\ncuenta,nomenclatura,talonario,deuda original,cuotas cobradas, cuotas adeudadas.\n Mensaje de avarano: Genere listado en pgm  Contr-Padron Financiacion Cloacas San Francisco. ', '', NULL, NULL),
(119, '2017-04-24', '2017-04-24 12:56:09', 3, 110, 'Solicita: Marta Montenegro\n Area Emisora: dpto. fiscalizacion\n Observacion: La cuenta de tasa inmueble 19542 tiene una nota de credito por año. ¿Porque?', '', NULL, NULL),
(120, '2017-04-24', '2017-04-24 13:28:57', 21, 130, 'Solicita: JCURIQUEO\n Area Emisora: direccion obras particulares\n Observacion: Pidieron que le reseteemos la clave porque ya hace varios dias que le falla.', '', NULL, NULL),
(121, '2017-04-24', '2017-04-24 13:32:16', 18, 109, 'Solicita: Alejandra Galvan\n Area Emisora: Politicas Tributarias\n Observacion: Notas de credito/debito entre fechas con padron,descripcion y monto', '', NULL, NULL),
(122, '2017-04-24', '2017-04-24 13:57:13', 21, 48, 'Solicita: Viviana Soto\n Area Emisora: mesa entrada corralon\n Observacion: Le desapareció el permiso para dar de alta personas a MESACORRALON-S1', '', NULL, NULL),
(123, '2017-04-24', '2017-04-24 15:05:30', 3, 106, 'Solicita: Marcos Pavonº\n Area Emisora: deportes\n Observacion: Buen dia Matias, por favor necesito que puedas habilitar en el pegm para ver el registro de proveedores no solo la op. Buscar los pagos por proveedor para ver los pendientes y lo pagado. Hoy solo puedo visualizar por op.\n \nTe pido también que habilites de igual forma a los usuarios: Soledadr, fperez, cgonzalez, jcorrillo\n \nMuchas gracias.\n \n \nMarcos Pavon \n', '', NULL, NULL),
(124, '2017-04-24', '2017-04-24 17:47:18', 29, 48, 'Solicita: Seguimiento cuenta\n Area Emisora: Tesoreria\n Observacion: Se necesita que el listado de Seguimiento de Cuenta, tenga los importes individuales de cada dia, ademas de los acumulados.', '', NULL, NULL),
(125, '2017-04-24', '2017-05-05 14:36:00', 3, 92, 'Solicita: Daniela\n Area Emisora: recursos humanos\n Observacion: Por medio del presente, solicito servicio técnico en el área de Medicina Laboral para verificar conexión de impresora en el área administrativa (HP1212), e impresora HP Deskjet F380 (oficina del Dr.) por fallas en el funcionamiento.\n \nMuchas gracias!!!!\n \nDaniela', '', NULL, NULL),
(128, '2017-04-26', '2017-04-26 13:05:49', 3, 113, 'Solicita: Lorenzo Oscar Zapata\n Observacion: Buena. \n\nas tardes me comunico a fin de solicitar usuario para poder ingresar a mis horarios de fichada de manera digital.\n\n\n\n\nSin mas espero su respuesta\n\nSaludos.\n\n\n\n\nZapata Lorenzo Oscar\n\nDNI 30479647\n\nlegajo 508\n', '', NULL, NULL),
(129, '2017-04-26', '2017-04-26 13:45:39', 15, 118, 'Solicita: Listado Debitos nos realizados\n Area Emisora: depto de contribuciones\n Observacion: Envie un email a soporte@municipalidad.com	:\n\nBuenas tardes,\n\nQueria consultarles como podemos ver el resultado de los debitos directos rechazados, y la razon informada por el banco.\n\nMuchas gracias,\n\nEnzo A. Kayser\nSistemas MSCB \n', '', NULL, NULL),
(130, '2017-04-26', '2017-04-26 17:13:29', 3, 110, 'Solicita: Servicios Publicos\n Area Emisora: servicios p\n Observacion: DE: DIRECCIÒN DE SERVICIOS PÙBLICOS\n  A: DIRECCIÒN DE SISTEMAS\n \n \n    Por la presente me dirijo a Ud, solicitando se ANULE el tema en Recibo Digital, Cementerio-Derecho de Exhumaciòn.\n    Cambiar  valor  en Recibo Digital al tema, Cementerio Derecho de Inhumaciòn e Ingreso- Corresponde el valor a este derecho, segun Ordenanza Nº2810-CM-16  $ 4090,00.\nEn Administraciòn de Deuda agregar Derecho de Introducciòn y separar Derecho de Testimonio e Inhumaciòn.\n \n \n \n    Atentamente\n \n \n \nServicios Pùblicos', '', NULL, NULL),
(133, '2017-04-28', '2017-05-02 12:50:30', 18, 110, 'Solicita: Andrea\n Area Emisora: depto de contribuciones\n Observacion: revisar cuotas generadas en obras de cloacas, que se correspondan con tipo plan (ejec y no ejec) , items separados y no solo uno\n\n Mensaje de avarano: En estas 3 cuentas  no coinciden el tipo de obra con el tipo de plan.\nNo se cual corresponde y habria que revisar la fecha de vencimiento de la deuda generada.\n \n904002531                                         \n904002618                                         \n904002713\n', '', NULL, NULL),
(134, '2017-04-28', '2017-04-28 15:41:23', 18, 110, 'Solicita: Marcela\n Area Emisora: dpto. tributario\n Observacion: Volver a estado emitidas declaraciones juradas de la 5/2016 a 12/2016 de la cuenta 007049, ', '', NULL, NULL),
(135, '2017-04-28', '2017-04-28 15:57:46', 18, 115, 'Solicita: Marta\n Area Emisora: depto de contribuciones\n Observacion: Se caducaron los talonarios 46668/9 pero el sistema solo anulo las cuotas de talonario y no paso los talonarios a anulados y las cuotas originales a adeudada\n', '', NULL, NULL),
(136, '2017-05-02', '2017-05-02 11:17:33', 27, 127, 'Solicita: Cecilia \n Area Emisora: tributario\n Observacion: Blanqueo de cuentas: 027695 \n022399 \n018252', '', NULL, NULL),
(208, '2017-05-04', '2017-05-04 12:25:00', 27, 131, 'Solicita: Raquelina\n Area Emisora: tribunal faltas 2\n Observacion: Solicita la modificacion de un documento de plantilla en Webdoc', '', NULL, NULL),
(209, '2017-05-04', '2017-05-04 16:47:03', 27, 113, 'Solicita: Susana Elizabeth Marin\n Area Emisora: sociales\n Observacion: Solicita usuario para ver sus fichadas.', '', NULL, NULL),
(226, '2017-05-04', '2017-05-04 17:53:28', 18, 27, '', '', NULL, '226.doc'),
(227, '2017-05-05', '2017-05-05 12:51:51', 18, 110, 'Solicita: Andrea\n Area Emisora: depto de contribuciones\n Observacion: Modificar fecha vencimiento cuota al 17/04/17  cuenta 9040002713 ejecutada', '', NULL, NULL),
(228, '2017-05-05', '2017-05-05 12:52:42', 18, 109, 'Solicita: Erica\n Area Emisora: depto de contribuciones\n Observacion: padron talonarios por obra ( las margaritas)', '', NULL, NULL),
(229, '2017-05-05', '2017-05-05 13:29:55', 18, 128, 'Solicita: ruth\n Area Emisora: direccion de tesoreria\n Observacion: Boleta 0933602166  de Lallana Ernesto transito $7800. Aparentemente se cobró con recibo digital 00000418 ticket T000962565', '', NULL, NULL),
(230, '2017-05-05', '2017-05-05 13:40:06', 18, 110, 'Solicita: Monica\n Area Emisora: deleg. cerro catedral\n Observacion: Necesita recibo digital para baja habilitacion comercial', '', NULL, NULL),
(231, '2017-05-05', '2017-05-05 14:37:01', 18, 109, 'Solicita: alicia\n Area Emisora: direccion de sistemas\n Observacion: recibos manos a la obra', '', NULL, NULL),
(232, '2017-05-05', '2017-05-05 15:01:04', 18, 102, 'Solicita: karina\n Area Emisora: mesa de entradas \n Observacion: Cuando generamos una boleta tipo Tasa personal por el administrador de\ndeuda, después de Aceptar, no aparece el link "Ver Boleta" , que es el que\npermite el acceso rápido a la boleta para imprimirlo, ni el nro. de boleta\nen la parte superior de la página. Nos dicen que antes salía.\n', '', NULL, NULL),
(233, '2017-05-05', '2017-05-05 16:18:50', 18, 109, 'Solicita: Erica\n Area Emisora: depto de contribuciones\n Observacion: listado talonarios por tipo de talonario', '', NULL, NULL),
(234, '2017-05-05', '2017-05-05 17:18:18', 21, 132, 'Solicita: Ariel Gomis / Fabiana Velazquez\n Area Emisora: dpto de sueldos\n Observacion: Por medio de la presente y a pedido de la Secretaría de Hacienda se autoriza a la Dirección de Sistemas a dar los permisos necesarios para que dicha Secretaría pueda visualizar la pantalla Resumen.\n \nAtte.\n \nFabiana VELAZQUEZ\nA/C Departamento de Sueldos\nMSCB', '', NULL, NULL),
(235, '2017-05-05', '2017-05-05 17:30:15', 21, 134, 'Solicita: Juan Carlos Ramos\n Area Emisora: direccion de sistemas\n Observacion: En "Modificar Animal" agregar quién realizó el chipeado: \'Chip aplicado por:\'\nCambiar "Propietarios" por "Personas"', '', NULL, NULL),
(236, '2017-05-05', '2017-05-05 17:30:50', 21, 135, 'Solicita: Juan Carlos Ramos\n Area Emisora: direccion de sistemas\n Observacion: Limpiar la base y poner datos correctos para mostrar a veterinaria', '', NULL, NULL),
(237, '2017-05-05', '2017-05-26 16:27:54', 21, 134, 'Solicita: Estefanía Klein\n Area Emisora: direccion de sistemas\n Observacion: Agregar en Cargar Animal y en Modificar Animal, para subir la foto del mismo.\n Mensaje de eklein: Además, debe poder subirse más de una foto, el útil para los equinos.', '', NULL, NULL),
(238, '2017-05-05', '2017-05-05 17:48:53', 3, 27, 'Solicita: Fabiana Gonzalez\n Observacion: A quien corresponda:\nPor este medio solicto alta en PGM para la agente que se detalla a continuación.\nConfección proyectos de resolución.\n \nStella Maris Testore\nDNI 32030611\nLEGAJO 14382\n \nAtte.-\n \nFabiana González\nA/C Depto. Administrativo\nJefatura de Gabinete', '', NULL, NULL),
(239, '2017-05-05', '2017-05-05 17:54:20', 3, 106, 'Solicita: Juan Francisco\n Area Emisora: secretaria de hacienda\n Observacion: Con el fin de verificar las solicitudes de adelantos de Sueldos, que se gestionan desde esta Secretaría, solicitamos habilite la opción para poder visualizar el padrón de sueldos, a los usuarios AGOMIS, DBREIDE, KLOPEZ, AMARQUEZ, y JFRANCISCO.\n \n \nSaludos\n \nJuan Francisco.-\n \nRespuesta por mbenditti: Dale tefi hacelo', '', NULL, NULL),
(240, '2017-05-08', '2017-05-08 11:40:39', 27, 112, 'Solicita: Juan \n Area Emisora: direccion de sistemas\n Observacion: Crear y preparar servidor de desarrollo para el sitio Comunicar', '', NULL, NULL),
(241, '2017-05-08', '2017-05-08 14:42:13', 18, 127, 'Solicita: Karina\n Area Emisora: mesa de entradas n\n Observacion: Cuando generamos una boleta tipo Tasa personal por el administrador de\ndeuda, después de Aceptar, no aparece el link "Ver Boleta" , que es el que\npermite el acceso rápido a la boleta para imprimirlo, ni el nro. de boleta\nen la parte superior de la página. Nos dicen que antes salía.', '', NULL, NULL),
(242, '2017-05-08', '2017-05-08 16:10:45', 18, 110, 'Solicita: Marta\n Area Emisora: depto de contribuciones\n Observacion: En la notificacion de deuda para escribania incluir otras deudas ademas de tasa. Podria ser seleccionando por nomenclatura como en procesa\nVer si no hay algo ya en el sistema nuevo.', '', NULL, NULL),
(243, '2017-05-08', '2017-05-08 17:38:50', 21, 69, 'Solicita: Gloria\n Area Emisora: dpto de sueldos\n Observacion: Se necesitan las partidas presupuestarias para las novedades de pasantes listadas.', '', NULL, '243.pdf'),
(244, '2017-05-09', '2017-05-09 17:33:30', 3, 27, 'Solicita: Lucas Perez\n Area Emisora: terminal omnibus\n Observacion: Sres.\nDireccion de Sistemas\nS                /              D\n \n \nA quien corresponda: \n \n                                 Solicitamos nos remitan USUARIO Y PASSWORD, para ejecutar en el sistema PGM nuevo. Para el Agente Sergio Ortiz de Elguea (Leg 1129). Jefe de la Division Administrativa (OMIDUC), con las siguientes atribuciones:\n \n \nProyecto de Resolucion. \nPedidos de Abstecimiento. \nFichadas de Personal. \nPadron de Comercios. \nweb doc: habilitaciones. \nHomologaciones.\n                              Sin otro particular, saludamos a Uds. atentamente.\n \n                                                                                                                                                     Guillermo W. Perea\n                                                                                                                                                       Director OMIDUC', '', NULL, NULL),
(245, '2017-05-09', '2017-05-10 11:07:22', 3, 29, 'Solicita: Lucas Perez\n Area Emisora: terminal omnibus\n Observacion: buenos días, me dirijo a ustedes por este medio a fin de solicitarles tengan a bien poder agregar a mi pgm permisos para ver proyectos de resoluciones sin mas los saludo\n \nATENTAMENTE \nLUCAS PEREZ \n Mensaje de mbenditti: buen dia mi usuario es lperez', '', NULL, NULL),
(246, '2017-05-09', '2017-05-09 17:37:44', 3, 106, 'Solicita: Lorena Arias\n Area Emisora: secretaria de turismo\n Observacion: Por medio de la presente solicito me habiliten en PGM, poder visualizar el libro de rubro egresos, a fin de poder sacar la información que necesito para realizar rendición de eventos. \n \npongo en copia Marcela Giovannini quien es la Directora de la cual dependo.\n \nAtte. \n \nLorena Arias\nA/c dpto. Administració y eventos\nSecretaria de Turismo y Producción', '', NULL, NULL),
(247, '2017-05-09', '2017-05-09 17:39:07', 3, 110, 'Solicita: Erica Salvo\n Area Emisora: depto de contribuciones\n Observacion: Buenos días:\nMediante la presente solicito se modifique en el nº de cedulón 0940525517 en descripción subtasa donde dice 21682/2016 192N272020000, debe decir 21682/2017 191F 0006 005.\nAtentamente.\nErica Salvo – Departamento Contribuciones', '', NULL, NULL),
(248, '2017-05-11', '2017-05-11 11:27:33', 27, 48, 'Solicita: Andrea\n Area Emisora: direccion inspeccion general\n Observacion: Andrea solicita modificar cuenta de comercio para que pueda cambiar el tipo de publicidad.', '', NULL, NULL),
(249, '2017-05-11', '2017-05-11 11:29:25', 27, 127, 'Solicita: Andrea\n Area Emisora: direccion inspeccion general\n Observacion: Andrea solicita modificar cuenta de comercio para cambiar el tipo de publicidad', '', NULL, NULL),
(251, '2017-05-11', '2017-05-11 17:33:36', 21, 104, 'Solicita: Dpto de Sueldos - Fabiana Velazquez\n Area Emisora: dpto de sueldos\n Observacion: Por medio de la presente y según lo conversado con el Director del área, Cdor.Cristian PAREDES, solicito habilitar la autorización para acceder a la ventana Resumen del sistema de sueldos\na la Dirección de Recursos Humanos.\nAsí mismo y de ser factible, habilitar igual autorización a los Jefes del Depto de Personal:\n \nCOLIN, Ana Laura Legajo 11195\nDE MARIO, María Magdalena Legajo 11041\nMARTINEZ, Blanca Eva Legajo 170\nHUENCHULLAN, Jorge 947\n \n \nAtte.\n \nFabiana VELAZQUEZ\nA/C Departamento de Sueldos\nMSCB', '', NULL, NULL),
(252, '2017-05-11', '2017-05-11 17:34:22', 21, 132, 'Solicita: Cristian Paredes\n Area Emisora: direccion de contaduria -\n Observacion: Estefania\n \nHabilitale tambien a mgonzalez, Mariana tambien me ayuda con algunos temas de sueldos y utiliza la info para proyectar algunos gastos del presupuesto.\n \ncristian\n', '', NULL, NULL),
(253, '2017-05-11', '2017-05-11 17:38:59', 21, 136, 'Solicita: Estefania Klein\n Area Emisora: direccion de sistemas\n Observacion: Envié mail a Soporte de PGM:\nHola Matías, envío el probema comentado por teléfono:\n\nCuando queremos cortar un recibo de sueldo de una liquidación abierta, en\nel Padrón de Sueldos --> Historial de Cargos, sólo para algunos legajos,\nnos muestra el siguiente error:\nError del servidor\n500 - Error interno del servidor.\nHay un problema con el recurso que busca y no se puede mostrar.\n\nLo estamos haciendo desde baja de Liquidación por el momento como me\nindicaste.', '', NULL, NULL),
(254, '2017-05-11', '2017-05-11 17:38:07', 21, 136, 'Solicita: Estefania Klein\n Area Emisora: direccion de sistemas\n Observacion: Envié mail a Soporte de PGM:\n\n\nHola Matías, de acuerdo a lo conversado telefónicamente, te envío el\nscreenshot del error que hubo mientras se estaba liquidando Planta\nPermanente.\nCuando estaba al 60% mostró lo que adjunto.', '', NULL, '254.jpg'),
(255, '2017-05-12', '2017-05-12 15:27:49', 21, 136, 'Solicita: Vanesa Noves\n Area Emisora: dpto de sueldos\n Observacion: Hola Hernán, ¿cómo estás?, estuve viendo con Vanesa unos legajos con el\ntema de los hijos Mayores a 18 años. Tengo varios casos.\n\nCASO 1: Los legajos para que mires son: 1531 y 8436 (NOV. 1810)\n\néstos tienen hijos que cumplen 18 años en Mayo, y en la liquidación de\nMayo no los está incluyendo, deberían cobrar por hijos con MAYO inclusive.\n\nCASO 2: Legajo 20604 y 287 tiene a la NIETA/NIETO a cargo y no le está\nincluyendo la novedad 1810.\n\nCASO 3: Legajo 300\n\nTiene un NIETO con Discapacidad y no le incluye la novedad 1920.\n\nPodrás revisar la fórmula CantidadHijosEscolaresMenores18() ?\n\nporque antes era Unidad = Empleado.Hijos y se ve que los NIETOS los\ncargaban como hijos.\n\nMuchas gracias.\n\nSaludos!\nEstefanía Klein', '', NULL, NULL),
(256, '2017-05-15', '2017-05-15 13:59:27', 21, 110, 'Solicita: Vanesa Noves\n Area Emisora: dpto de sueldos\n Observacion: Hola, ejecutaron desde sueldos el reporte "Sueld-Resumen Liquidacion por\nLugar de Cobro" con los parámetros:\n\n2017\n05\nPeriodo Mensual\nAPI\n\nY el "Total Gral" suma TODOS los NETO, no sólo los "TOTAL" como debería,\nlo corrí con otros parámetros y hace lo mismo, por ejemplo:\n\n2017\n04\nPeriodo Mensual\nPlanta, Contratados, politicos\n\nNo sé si ésto sucederá con todos los reportes. Necesitamos que la suma sea\nla correcta ya que los resportes se envían a otras áreas.\n\nSaludos\nEstefanía', '', NULL, NULL),
(257, '2017-05-15', '2017-05-15 17:10:05', 3, 27, 'Solicita: Clara M. Perez\n Area Emisora: direccion obras particulares\n Observacion: Por la presente solicito permiso en PGM para el Item de Listado de Recibos Digitales para la máquina opart-11.\n(Clara)\n \nAtentamente.\n \nClara María Pérez\nCoord.Administrativa\nObras Particulares.\n \nSan Carlos de Bariloche, 12 de Mayo de 2017.\n ', '', NULL, NULL),
(258, '2017-05-16', '2017-05-16 12:12:13', 3, 110, 'Solicita: Erica Salvo\n Area Emisora: direccion de sistemas\n Observacion: Buenos días:\nDe acuerdo al proyecto de Resolución Nº 3348-2017 solicito se modifique la fecha de vencimiento de la obra cloacas San Francisco obra no ejecutada que venció el 15/05/17 a la fecha 17/07/17.\nSaludos, Erica', '', NULL, NULL),
(259, '2017-05-16', '2017-05-16 12:25:05', 21, 104, 'Solicita: Fabiana Velazquez\n Area Emisora: dpto de sueldos\n Observacion: Por medio de la presente y conforme la Resolución 2484-I-2017, solicito dar de alta clave PGM a la agente LINARES, Elizabeth Legajo Nº 21492 para realizar identicas funciones que la agente \nNICOLE, Laura Legajo Nº 11478.-\n \nAtte.\n \nFabiana\n\nHola Fabiana, ya le di los mismos permisos que ustedes en el PGM Nuevo, ¿en el viejo necesita algo?.\nSaludos!\n\nSi para ver Resoluciones y Proyectos.\nGracias,\nAh! otra cosa, además podrías darle de baja los permisos que trae de otra area?\nGracias Estefanía.\n \nFabi', '', NULL, NULL),
(260, '2017-05-16', '2017-05-16 12:27:42', 21, 104, 'Solicita: Daniela Vega\n Area Emisora: dpto de sueldos\n Observacion: Por medio del presente, solicito acceso al Padrón de Sueldos según fuera acordado con la jefa de Departamento Sueldos, en una primera etapa,  según el siguiente detalle:\n \n- Mariela Muñoz, legajo 12699 \n- Daniela Vega, legajo 1042\n- Dalia Hernandez, legjo 616\n- Daniela Ortiz, legajo 1125\n \nCabe aclarar que los accesos serán solicitados una vez que se determine la vinculación directa con la tarea, dado el carácter restrictivo de la información que allí se aloja.\n \nMuchas gracias!\n \nAtte.\n \nDaniela Vega \nA/C DIRECCION DE RECURSOS HUMANOS\nint. 430-432\n', '', NULL, NULL),
(261, '2017-05-16', '2017-05-16 12:28:35', 21, 132, 'Solicita: Lilian Baroni\n Area Emisora: secretaria de hacienda\n Observacion: Hola Claudia, \n\n\n\n\n\nMe podes dar el acceso al padron de sueldos.\n\n\n\n\nmuchas gracias ! \n\n\n\n\n\nSaludos, Lilian. ', '', NULL, NULL),
(262, '2017-05-16', '2017-05-16 15:37:12', 18, 109, 'Solicita: erica\n Area Emisora: depto de contribuciones\n Observacion: talo-talonarios sociales sacar tipo microcreditos\ntalo-estado cuotas... solo las generadas', '', NULL, NULL),
(263, '2017-05-17', '2017-05-17 12:05:50', 21, 132, 'Solicita: Fabiana Velazquez\n Area Emisora: dpto de sueldos\n Observacion: Estefi, buen día, necesito le habilites a RRHH el siguiente listado:\n \nTipo de Reporte: 02ES - Listados específicos\nNombre del Reporte: Listado vencimiento contratos\n \nPara los usuarios VEGA, MUÑOZ, ORTIZ, HERNANDEZ.\n \nMuchas gracias!\n \nFabi', '', NULL, NULL),
(264, '2017-05-17', '2017-05-17 15:15:11', 21, 68, 'Solicita: Fabiana Velazquez\n Area Emisora: dpto de sueldos\n Observacion: Estefanía:\nTe paso los datos para el cambio de importe del seguro de vida.\n \nAporte Patronal COD:4060 APORTE SEGURO VIDA HORIZONTE.\nPara mayo/2017 el valor debe ser $ 9,09 por agente.-\n \nHabría que crear un código que se llame APORTE SEG.VIDA RETROACTIVO para cargarle $ 4,52 por agente.-\n \nGracias!\n \nFabi\n \n \n \n \nFabiana VELAZQUEZ\nA/C Departamento de Sueldos\nMSCB', '', NULL, NULL),
(265, '2017-05-17', '2017-05-17 15:31:56', 18, 132, 'Solicita: gabriela baez\n Area Emisora: direccion de tesoreria\n Observacion: Permiso de cambio de año para ella y Pabla Vargas.', '', NULL, NULL),
(266, '2017-05-17', '2017-05-17 15:33:33', 18, 110, 'Solicita: Mariela\n Area Emisora: direccion de contaduria -\n Observacion: No se puede tomar afectación 98 del 25/11/16 al hacer compromiso a proveedor Pichaud', '', NULL, NULL),
(267, '2017-05-17', '2017-05-17 15:35:22', 18, 110, 'Solicita: Inés\n Area Emisora: direccion de contaduria -\n Observacion: No puede imprimir recibo de ingreso por retención fondo reparo . Recibos 74 y 85 del 2014. Sale en Libro banco caja 83, 20 al 22 de enero 2014\n', '', NULL, NULL),
(268, '2017-05-17', '2017-05-17 17:18:54', 18, 109, 'Solicita: Cristian Paredes\n Area Emisora: direccion de contaduria -\n Observacion: listados de deuda no documentada y documentada al 31122016.\nseparar generado antes del 01012016.\nespecificar saldo de origen,cobranza,saldo a cobrar.\nPentaho?', '', NULL, NULL),
(269, '2017-05-18', '2017-05-18 16:00:35', 18, 110, 'Solicita: erica\n Area Emisora: depto de contribuciones\n Observacion: Modificacion de situacion judicial a normal de la boleta 0037016796 de la cuenta 064581D2003 \ny la boleta 0037087022 de la cuenta 064577D2003 para poder prescribir.\n \n', '', NULL, NULL),
(270, '2017-05-18', '2017-05-18 16:04:33', 18, 109, 'Solicita: erica\n Area Emisora: depto de contribuciones\n Observacion: no funciona salida excel reporte contri-listado datos por obra.', '', NULL, NULL),
(271, '2017-05-18', '2017-05-18 16:06:26', 18, 48, 'Solicita: Matias\n Area Emisora: division patrimoniales\n Observacion: Da error al grabar bien 50954', '', NULL, NULL),
(272, '2017-05-18', '2017-05-18 16:07:52', 18, 110, 'Solicita: M.j.lopez\n Area Emisora: delegacion cerro otto\n Observacion: No se realizaron debitos automaticos de talonario de partida 32264. ', '', NULL, NULL),
(273, '2017-05-18', '2017-05-18 17:52:07', 18, 131, 'Solicita: Raquelina\n Area Emisora: tribunal faltas 2\n Observacion: En plantilla sentencia alcoholemia modificar donde dice res 2714 debe decir 2810.\nAgregar a todas las plantillas el mail de trib.faltas. Pedi que lo envien por mail .\n Mensaje de avarano: Modifique plantilla alcoholemia', '', NULL, NULL),
(274, '2017-05-18', '2017-05-18 16:20:52', 18, 138, 'Solicita: Alicia\n Area Emisora: direccion de sistemas\n Observacion: sería lindo que las listas estuvieran ordenadas alfabeticamente...:)\nGracias! (por si lo decidis hacer)', '', NULL, NULL),
(275, '2017-05-18', '2017-05-18 16:35:37', 18, 139, 'Solicita: Erica\n Area Emisora: depto de contribuciones\n Observacion: no puede ingresar con nro de legajo', '', NULL, NULL),
(276, '2017-05-18', '2017-05-18 16:53:53', 18, 110, 'Solicita: Marcela\n Area Emisora: depto de contribuciones\n Observacion: El expediente de faltas 133859H2016  asignado a HERNANDEZ MARTIN GINES (DNI 24986393) ( con plan de pago) \ncorresponde en realidad a HERNANDEZ MORENO GINES (PAS 6412098) .\nVerificar en webdoc y faltas y modificar.', '', NULL, NULL),
(277, '2017-05-18', '2017-05-30 16:27:16', 18, 110, 'Solicita: erica\n Area Emisora: depto de contribuciones\n Observacion: En certificado de legales de obra de cloaca no sale Reglamentacion juridica aplicable.\nEjemplos :cuenta cloaca 904002314 , cuenta trib.faltas (si sale) 124863G2015\n Mensaje de avarano: solicitado a Pgm cordoba\n Mensaje de avarano: lo estan solucionando', '', NULL, NULL),
(278, '2017-05-19', '2017-05-19 11:52:17', 21, 132, 'Solicita: Cristian Paredes (Chamatropulos)\n Area Emisora: direccion de contaduria -\n Observacion: Por medio de la presente solicito habilite acceso para consulta al padron de sueldos a la agente fgonzalez\n \nSaludos\n \nCristian\n----- Original Message ----- \nFrom: Fabiana Gonzalez \nTo: Direccion de Contaduria Gral. MSCB \nSent: Thursday, May 18, 2017 2:27 PM\n\n\nA: DIRECCIÓN DE CONTADURÍA GENERAL\nSR. CRISTIAN PAREDES\n \nDE: JEFATURA DE GABINETE\n \nPor este medio solicito tenga a bien arbitrar los medios necesarios a fin de autorizar a la Agente González Fabiana - Legajo Nº 20506,  para poder acceder a la visión del padrón de sueldos.\nDicha solicitud se basa en que en diversas oportunidades es necesario ingresar al sistema por cuestiones operativas, como por ejemplo horas extras si fueran autorizadas o no para el correspondiente control inmediato, diferencia en haberes por distintos reclamos de los agentes municipales, etc.\nSaluda atentamente.\n \nPablo L. Chamatrópulos\nJefe de Gabinete\nMunicipalidad de San Carlos de Bariloche       ', '', NULL, NULL),
(279, '2017-05-19', '2017-05-19 12:36:12', 18, 110, 'Solicita: alicia\n Area Emisora: direccion de sistemas\n Observacion: Agregar en notificacion de deuda el telefono y mail del area. Confirmar con Lilian.', '', NULL, NULL),
(280, '2017-05-19', '2017-05-19 14:42:49', 18, 110, 'Solicita: Roxana\n Area Emisora: dpto. fiscalizacion\n Observacion: Necesitamos que se libere el estado de los periodos 06/87  a 06/92 – 02/94 a 06/94 de la partida 26084,\nse encuentra en estado adeudado judicial, necesitamos su pase a rentas.\n\n Mensaje de avarano: pedido a pgm', '', NULL, NULL),
(281, '2017-05-19', '2017-05-19 15:51:22', 18, 109, 'Solicita: lilian\n Area Emisora: Politicas Tributarias\n Observacion: Reporte las margaritas adeudado cobrado financiado', '', NULL, NULL),
(282, '2017-05-19', '2017-05-19 15:53:55', 18, 109, 'Solicita: roxana\n Area Emisora: dpto. fiscalizacion\n Observacion: padron por titular/encargdo con deuda por nro_de documentos acturalizado al 31/5 separado capital interes total', '', NULL, NULL),
(283, '2017-05-19', '2017-05-19 17:16:34', 3, 93, 'Solicita: Erica Salvo\n Area Emisora: depto de contribuciones\n Observacion: Buenas tardes:\nMediante la presente solicito la instalación de internet en la máquina del usuario JSOTO.\nAtentamente.\nErica Salvo', '', NULL, NULL),
(284, '2017-05-19', '2017-05-19 17:20:21', 18, 110, 'Solicita: monica\n Area Emisora: deleg. cerro catedral\n Observacion: anulo boleta de declaracion jurada 1/17 y no puede presentar rectificativa. cuenta 27739.', '', NULL, NULL),
(287, '2017-05-19', '2017-05-19 17:54:05', 18, 110, 'Solicita: Marcel\n Area Emisora: direccion de contaduria -\n Observacion: no pudieron rectificar dos veces en el dia, informamos a pgm', '', NULL, NULL),
(288, '2017-05-23', '2017-05-23 17:30:25', 18, 48, 'Solicita: Gabriela\n Area Emisora: recursos humanos\n Observacion: tipos de resoluciones realizadas por año', '', NULL, NULL),
(289, '2017-05-23', '2017-05-23 17:33:26', 18, 137, 'Solicita: Mariana\n Area Emisora: direccion de tesoreria\n Observacion: mail de contribuyente por que  no aparecen boletas en pago mis cuentas. Padrones 58132,58545,58204,58156.', '', NULL, NULL),
(290, '2017-05-23', '2017-05-23 17:34:49', 18, 109, 'Solicita: erica\n Area Emisora: depto de contribuciones\n Observacion: ver diferencias en reportes talonarios sociales y otro de talonario', '', NULL, NULL),
(291, '2017-05-23', '2017-05-23 17:37:59', 18, 128, 'Solicita: erica\n Area Emisora: depto de contribuciones\n Observacion: quien anulo talonarios 39851-39865. no correspondia\nRespuesta por avarano: se anularon en legales', '', NULL, NULL),
(292, '2017-05-24', '2017-05-24 11:59:09', 3, 93, 'Solicita: No aclara\n Area Emisora: secretaria de hacienda\n Observacion: \nBuen día! solicitamos por favor el arreglo de una de las pc de la oficina por problemas de conectividad , ya que la misma desde el día de ayer comenzo con problemas y no funciona nada vinculado a la red (sin PGM, internet, mail).\nDesde ya esperando su pronta respuesta, saluda atte,\nSecretaría de Hacienda\n', '', NULL, NULL),
(293, '2017-05-24', '2017-05-24 12:37:19', 3, 110, 'Solicita: Alejandro Reyna\n Area Emisora: direccion de tesoreria\n Observacion: Alicia, \nNo encuentro la diferencia que le aparece a Cristian, me podras ayudar?\n \nGracias\n \nAlejandro\n ', '', NULL, '293.odt'),
(294, '2017-05-24', '2017-05-24 15:35:23', 3, 58, 'Solicita: Genusso\n Area Emisora: secretaria de hacienda\n Observacion: Prueba3 Mant\n Mensaje de mbenditti: LAlalalaa', '', NULL, '294.txt'),
(295, '2017-05-24', '2017-05-24 17:01:10', 25, 91, '', '', NULL, '295.txt'),
(296, '2017-05-24', '2017-05-24 17:05:32', 21, 29, 'Solicita: Paola Cid\n Area Emisora: direccion de sistemas\n Observacion: Por medio de la presente se solicita autorizar a la agente municipal Villalobo Mariana Legajo Nº 12305, el permiso en su clave PGM, para aprobar Pedidos de Abastecimientos generados desde las áreas dependientes de ésta Subsecretaria.\nDesde ya muchas gracias, Paola Cid Desarrollo Local', '', NULL, NULL),
(297, '2017-05-24', '2017-05-24 17:08:12', 21, 132, 'Solicita: Ana Geron\n Area Emisora: cultura\n Observacion: DIRECCION DE SISTEMAS:\n\nPor medio de la presente y con motivo de no poder acceder a las tareas a realiza mediante el PGM solicitamos, a la brevedad posible,  acceso a: \nemitir recibo digital\nreporte de deudas\ncarga de personas (artesanos, artistas entre otros)\nodenes de pago de resoluciones\n \nLas agentes municipales asignados son:\nGraciela Chodilef.........leg. 384\nMarisa Ranquehue......leg. 11577\nAlicia Fernandez..........leg. 266\n \nAtentamente\n \nAna Geron\nSubsecretaria de Cultura \n', '', NULL, NULL),
(298, '2017-05-26', '2017-05-26 13:13:05', 21, 70, 'Solicita: Vanesa Noves SUELDOS\n Area Emisora: Direccion de Sistemas\n Observacion: Buen Dia Estefania: te envio nombre y legajo del agente que el sistema no  le liquidó prenatal en Abril, ni en Mayo:\nAVILES, JOSE FARIS Legajo Nº14113\n \nMuchas Gracias\n \nVANESA LORENA NOVES\nDEPARTAMENTO DE SUELDOS', '', NULL, NULL),
(299, '2017-05-26', '2017-05-26 13:46:54', 18, 128, 'Solicita: maria\n Area Emisora: politicas tributarias\n Observacion: quien hizo la boleta nº 6002190466.\n', '', NULL, NULL),
(300, '2017-05-26', '2017-05-26 14:05:45', 21, 70, 'Solicita: Mauricio Caupan\n Area Emisora: Direccion de Sistemas\n Observacion: Estefanía: te envío mail solicitando la revisión de las siguientes novedades del sistema, ya que la fórmula presenta un error en el cálculo:\n-1765: Adic. Responsabilidad Jerárquica (Dirección)\n-1766: Adic. Responsabilidad Jerárquica (Departamento)\n-1767: Adic. Responsabilidad Jerárquica (División)\n-1768: Adic. Responsabilidad Jerárquica (Sección)\n \nAtte. Mauricio\n ', '', NULL, NULL),
(301, '2017-05-26', '2017-05-26 15:40:47', 21, 139, 'Solicita: Guarderia Iglesias\n Area Emisora: Direccion de Sistemas\n Observacion: Por la presente, se informa que el personal que a continuación se detalla no ha podido ingresar con el número de usuario por ustedes informado:\n \nMaría del Carmen Quiñehual \nRoque Castaño \nSilvana Tellez\nAtte.\nSandra Blasquiz', '', NULL, NULL),
(302, '2017-05-26', '2017-05-26 15:41:54', 21, 114, 'Solicita: Ana Sangoi\n Area Emisora: Direccion de Sistemas\n Observacion: DE: TRIBUNAL DE FALTAS Nº1\nA: DIRECCION DE SISTEMAS\n \nPOR MEDIO DE LA PRESENTE SOLICITO SE ME HABILITE EN EL SISTEMA PGM LA VERIFICACION DEL FICHADO DE LOS LEGAJOS DEL PERSONAL DE ESTA DIRECCION, Y QUE SE DETALLAN A CONTINUACION: Nº460, 664, 468, 14618, 14619, 467, 972, 864, 163, 11382, 21595, 11589, 8569, 562, A FIN DE REALIZAR EL CONTROL CORRESPONDIENTE.\n \nATTE.\n \nANA L. SANGOI\nusuario (asangoi)\nSECRETARIA\n', '', NULL, NULL),
(303, '2017-05-26', '2017-05-26 16:29:35', 21, 134, 'Solicita: Veterinaria\n Area Emisora: Direccion de Sistemas\n Observacion: Agregar Tamaño para equinos (alzada) con medida.\nVacunas de Felinos (triple y leucemia)\nVacunas de Equinos (anemia infecciosa, inlfuenza equina, adenitis equina,encefalomielitis.\nAgregar para Equinos, si tienen libreta sanitaria.', '', NULL, NULL),
(304, '2017-05-26', '2017-05-26 16:51:10', 18, 137, 'Solicita: yo\n Area Emisora: Direccion de Sistemas\n Observacion: REVISAR ARCHIVOS DE RESULTADO DE DEBITO  Y DEUDA GENERADA. \nHACER LISTADO.', '', NULL, NULL),
(305, '2017-05-26', '2017-05-26 16:55:06', 18, 137, 'Solicita: erica\n Area Emisora: Departamento de Contribuciones\n Observacion: LIstado de talonarios que no se enviaron a débito(por seleccionar solo activos al enviar)', '', NULL, NULL),
(306, '2017-05-26', '2017-05-26 17:14:30', 18, 132, 'Solicita: lili toro\n Area Emisora: habilitaciones\n Observacion: permiso para hacer notas de credito de habilitacion comercial a una cuenta comercio', '', NULL, NULL),
(307, '2017-05-26', '2017-05-26 17:58:08', 18, 137, 'Solicita: maria\n Area Emisora: politicas tributarias\n Observacion: no corresponde deuda por debit.incid.en acred a la partida 53049\n Mensaje de avarano: VER EXCEL PADRON 53049\n\n Mensaje de avarano: BUSCAR POR PROCESA\n', '', NULL, NULL),
(308, '2017-05-26', '2017-05-26 17:17:16', 18, 137, 'Solicita: Lilian\n Area Emisora: politicas tributarias\n Observacion: padrones 15938 15939 verificar que debitos se hicieron y cuales fueron rechazados.', '', NULL, NULL),
(309, '2017-05-29', '2017-05-29 12:57:25', 18, 137, 'Solicita: berenice\n Area Emisora: politicas tributarias\n Observacion: Alicia,\nNecesito que la cuota 03 de la cta. Nº 15939 (0935456422) que tiene orden de no pagar me aparezca en el PGM como adeudada.\nY lo mismo para la cuota 02 y 03 de  cta. Nº 15938 (0935456309) que no se debitaron por falta de fondos, necesito que aparezcan en PGM como adeudadas.\nTengo que computar unas notas de crédito que genere y ya corregí de acuerdo a lo que me informaste. \nGracias\n\n Mensaje de avarano: Las cuotas cobradas no se pueden volver a adeudadas, en ningún caso.\nPor eso se cargan las cuotas DÉBITO INCIDENCIA EN ACREDITACIÓN ,    para generar nuevamente la deuda, en estos casos en que el sistema las dió, erroneamente,  por cobradas.\n\nPara utilizar las notas de crédito tenés que seleccionar esas cuotas.\n\nEn el caso de estos padrones, Pgm Córdoba tiene que generar todavía los DÉBITOS INCIDENCIA EN ACREDITACIÓN  correspondientes a la cuota 3/2017, para volver a generar la deuda.\nDurante la mañana de hoy te confirmo cuando lo harán.\n', '', NULL, NULL),
(310, '2017-05-29', '2017-05-29 17:08:40', 18, 111, 'Solicita: nancy\n Area Emisora: ejecuciones fiscales\n Observacion: No está la ficha 49259 del bien 25029\n Mensaje de avarano: pedido a Pgm', '', NULL, NULL);
INSERT INTO `tickets` (`id_ticket`, `fecha`, `hora`, `creador`, `servicio`, `observacion`, `patrimonio`, `tiempoResolucion`, `adjunto`) VALUES
(311, '2017-05-29', '2017-05-29 15:20:41', 21, 104, 'Solicita: Cristian Paredes\n Area Emisora: Direccion de Contaduria\n Observacion: Atte.\n \nEn el dia de la fecha comenzaron su pasantia dos personas de la UNRN por lo que solicito usuario y clave de PGM para ellas dos:\n \n- Brenda Winkelman\n- Marina Eggers\n \nCon los permisos iguales al usuario malcacer\n \nSaludos\n \nCr. Paredes Cristian\nDireccion de Contaduria', '', NULL, NULL),
(312, '2017-05-29', '2017-05-29 15:27:22', 3, 29, 'Solicita: Yo\n Area Emisora: Politicas tributarias\n Observacion: Probando', '', NULL, NULL),
(313, '2017-05-29', '2017-05-29 18:37:46', 3, 54, 'Probando sin adjunto - Numero de interno: 145', NULL, NULL, NULL),
(314, '2017-05-29', '2017-05-29 18:38:17', 3, 113, 'probando con adjunto - Numero de interno: 145', NULL, NULL, NULL),
(315, '2017-05-30', '2017-05-30 16:57:46', 18, 110, 'Solicita: betty\n Area Emisora: catastro\n Observacion: solicitan poder generar tasa personal por derechos de mensura.', '', NULL, NULL),
(316, '2017-05-30', '2017-05-30 17:00:11', 18, 132, 'Solicita: gladys quezada\n Area Emisora: turismo\n Observacion: no pueden dar de alta personas', '', NULL, NULL),
(317, '2017-05-30', '2017-05-30 17:05:13', 18, 137, 'Solicita: erica\n Area Emisora: Departamento de Contribuciones\n Observacion: se mando al debito la boleta 9006177858?', '', NULL, NULL),
(318, '2017-05-30', '2017-05-30 17:20:28', 18, 109, 'Solicita: Lilian\n Area Emisora: Politicas tributarias\n Observacion: reporte de tish y tsm catedral facturado y cobrado\n\n Mensaje de avarano: ver reporte Tribu-Total facturado por comercio por Ingreso x r', '', NULL, NULL),
(319, '2017-05-31', '2017-05-31 11:32:31', 21, 69, 'Solicita: Fabiana Velazquez\n Area Emisora: Direccion de Sistemas\n Observacion: Te adjunto los legajos, son solo los pintados.\nGracias!\nFabi\n \n Fabiana VELAZQUEZ\nA/C Departamento de Sueldos\nMSCB\n\nNovedad 1240', '', NULL, NULL),
(320, '2017-05-31', '2017-05-31 11:35:13', 21, 110, 'Solicita: Estefania Klein\n Area Emisora: Direccion de Sistemas\n Observacion: Hola, necesitamos por favor si nos pueden modificar el reporte que se saca\ndesde:\n\nSueldos y Jornales --> Orden de Pago --> Vinculación de Sueldos con\nContabilidad el reporte: "ERRORES VINCULACION DE SUELDOS CON CONTABILIDAD"\n\nQue saque el reporte del estilo:\n\nArea Administrativa / Codigo de la novedad numérico / Concepto Novedad\n\nY que no lo duplique por cada empleado.\n\nDe ésta forma facilitamos el trabajo del Dpto. de Sueldos cuando llega la\nhora del cierre.\n\nDesde ya muchas gracias.\n\nEstefanía\n Mensaje de eklein: Estefanía:\n\nEs correcta la observación. Se elaboró la solicitud de requerimiento respectiva para\nsu desarrollo. Informaremos al respecto cuando tengamos novedades de actualización.\n\nSaludos.-\n\nIng. Fernando M. Gonzalez\nConsultor Senior\nProgram Consultores S.A.\nJuan del Campillo 779\n(X5000GTO) Cordoba - Argentina\nTel/fax (0351) 4474200 y rotativas\nWeb.Site: www.municipalidad.com', '', NULL, NULL),
(321, '2017-05-31', '2017-05-31 11:39:21', 18, 110, 'Solicita: erica\n Area Emisora: Departamento de Contribuciones\n Observacion: Hay que cargar un certificado viejo de la cuenta  902037498 de obras y servicios boleta nº 0030749021. El nro. de certificado es 7779,por $ 3368.20 fecha 31/03/1992.\n\n Mensaje de avarano: se pidio a pgm', '', NULL, NULL),
(322, '2017-05-31', '2017-05-31 11:51:24', 21, 66, 'Solicita: Gloria Vera\n Area Emisora: Departamento de Sueldos\n Observacion: El Reporte Sueld-Listado de Aportes incluye API (1301-1306) y no debería.', '', NULL, NULL),
(323, '2017-05-31', '2017-05-31 17:24:45', 21, 121, 'Solicita: Fabiana Velazquez\n Area Emisora: Departamento de Sueldos\n Observacion: Hola Alejandro, te cuento lo que pasó:\n\nLe pregunté a Gloria de Sueldos, ayer ella corrió el proceso de\n"Vinculación de Sueldos con Contabilidad" y le dió los siguientes montos:\n\nPlanta: 54904478.17\nContratados: 11632688.99\nPoliticos: 4275703.96\nPasantes: 70892.10\n------------------------\nTotal 70883763.22 (coincide con la suma de Aportes en Totales por concepto)\n (ADJUNTO ARCHIVOS)\n\nEl tema es que hace media hora yo lo corrí nuevamente para ver si me daba\ndiferencias y si el problema estaba en la vinculación, pero me dieron los\nmismos montos, conclusión el problema no estaba en la vinculación a menos\nque no funcionara bien el proceso.\n\nEl reporte Sueld-Listado de Aportes, tengo entendido que es un reporte\nnuevo que pidió Contaduría a PGM para poder discriminar de la IMPUTACION\ndel NETO, los aportes.\n\nEstuvimos corriendo este reporte toda la mañana y fue cambiando los\nvalores, dando distinto de ayer, el tema es que ni en sueldos ni nosotros\nestuvimos cambiando cosas.\nLo único que hicimos fue correr el proceso de vinculacion nuevamente, y\nluego dió bien ese reporte, pero si hubiese sido eso, me tendrían que\nhaber dado distintos los valores de Totales Contabilidad Presupuesto.\n\nNos gustaría tener un informe de qué sucedió realmente, ya que desde aquí\nno logramos detectar cuál fue el problema.\n\nDesde ya muchas gracias y quedamos a la espera.\n\nSaludos\nEstefanía\n\n> Hola Alejandro, te envío el listado de aportes que sacaron ayer que\n> coincidían los montos, si comparás con los montos de Planta y Contratados,\n> hay diferencias con el reporte que saca ahora.\n>\n> Según los de sueldos no han tocado nada.\n>\n> Ayer se hizo una vinculación de una novedad nueva - Aportes patronales -\n> Novedad 4230, creo que el tema está ahí.\n>\n> Hoy necesitan enviar los informes a contaduría para hacer las órdenes de\n> pago al banco.\n>\n> Quedo a la espera.\n> Muchas gracias!\n> Estefanía\n>\n>\n', '', NULL, '323.pdf'),
(324, '2017-05-31', '2017-05-31 17:32:22', 21, 127, 'Solicita: Estefania Klein\n Area Emisora: Direccion de Sistemas\n Observacion: Hola, necesitamos si nos pueden actualizar la base del usuario "prueba"\ncon el backup del día 30 de Mayo de 2017.\n\nDesde ya muchas gracias.\n\nSistemas\n', '', NULL, NULL),
(325, '2017-05-31', '2017-05-31 17:33:55', 21, 121, 'Solicita: Estefania Klein\n Area Emisora: Direccion de Sistemas\n Observacion: El 30/05/2017 a las 10:32, Consultas Municipalidad Bariloche escribió:\n> Hola Fernando/Hernán:\n> Estamos vinculando una novedad y necesitaríamos si nos pueden hacer\n> opciones en Area Administrativa para el próximo mes.\n> Por ejemplo, hay novedades que se imputan para todos los Contratados a una\n> sola cuenta, o para todos los Políticos/Planta, no se discriminan por área\n> administrativa.\n>\n> Estaría bueno tener opciones por tipo de empleado por ejemplo:\n>\n> Todas Contratados\n> Todas Politicos\n> Todas Planta Permanente\n> Todas API\n> Todas Pasantes\n>\n> Y que "Todas" NO incluya las que dicen INACTIVA.\n>\n>\n> Muchas gracias.\n> Saludos\n> Estefanía\n>\n> Sistemas', '', NULL, NULL),
(326, '2017-05-31', '2017-05-31 17:40:27', 21, 121, 'Solicita: Gloria Vera\n Area Emisora: Departamento de Sueldos\n Observacion: Hola, cuando corren desde sueldos el proceso de inicio de novedades, llega\nhasta cierto porcentaje, luego aparece el cartel que dice que en proceso\nha finalizado y cuando se le da aceptar, completa la barra de progreso al\n100%.\n\nQuisiera saber si este proceso se termina de ejecutar correctamente aunque\nno llegue la barra al 100%.\n\nAdjunto screenshots.\n\nSaludos\nEstefanía', '', NULL, '326.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `nombre_usuario` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `contrasenia` varchar(36) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `fk_empleado` int(11) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `fk_rol` int(11) NOT NULL,
  `id_extreme` varchar(180) COLLATE utf8_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nombre_usuario`, `contrasenia`, `fk_empleado`, `activo`, `fk_rol`, `id_extreme`) VALUES
(3, 'mbenditti', '090c36e3bb39377468363197afb3e91b', 1, 1, 1, NULL),
(15, 'ekayser', '6b5b0dd03c9c85725032ce5f3a0918ae', 1637, 1, 2, NULL),
(16, 'gdinardo', '4c96f8324e3ba54a99e78249b95daa30', 146, 1, 2, NULL),
(17, 'gcastro', '4c96f8324e3ba54a99e78249b95daa30', 2023, 1, 3, NULL),
(18, 'avarano', 'e94ef563867e9c9df3fcc999bdb045f5', 113, 1, 1, NULL),
(19, 'etomatis', '6d6354ece40846bf7fca65dfabd5d9d4', 1177, 1, 2, NULL),
(21, 'eklein', '57610aba222b92a23e65f51a88492579', 2851, 1, 1, NULL),
(22, 'hacuña', '3ab9071536d62f29aa8b3fd39141f6ad', 199, 1, 1, NULL),
(23, 'bmarin', 'e4f7614a887a8cc07a2eea93a1e31122', 983, 1, 2, NULL),
(24, 'gibanez', '05fe03b494c0f1a7d6cb49f0bf3fd70d', 395, 1, 2, NULL),
(25, 'dalvarez', 'aa47f8215c6f30a0dcdb2a36a9f4168e', 2555, 1, 2, NULL),
(27, 'bovando', 'e3928a3bc4be46516aa33a79bbdfdb08', 2852, 1, 1, NULL),
(28, 'administrador', '6f8725703f6dcc6bf2a329d2fe77fb39', 1, 1, 1, NULL),
(29, 'clorusso', '2b9ff3efc4a999ecfacd18c4bbc57a2e', 2853, 1, 1, NULL),
(30, 'jcramos', 'a94652aa97c7211ba8954dd15a3cf838', 1685, 1, 1, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `areas`
--
ALTER TABLE `areas`
  ADD PRIMARY KEY (`id_area`);

--
-- Indices de la tabla `area_sistemas`
--
ALTER TABLE `area_sistemas`
  ADD PRIMARY KEY (`id_area_sistemas`);

--
-- Indices de la tabla `asuntos`
--
ALTER TABLE `asuntos`
  ADD PRIMARY KEY (`id_asuntoP`),
  ADD KEY `fk_area` (`fk_area`);

--
-- Indices de la tabla `base_conocimiento`
--
ALTER TABLE `base_conocimiento`
  ADD PRIMARY KEY (`id_resolucion`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`id_empleado`),
  ADD KEY `fk_area` (`fk_area`);

--
-- Indices de la tabla `encargado_servicios`
--
ALTER TABLE `encargado_servicios`
  ADD PRIMARY KEY (`usuario`,`asunto`),
  ADD KEY `usuario` (`usuario`),
  ADD KEY `asunto` (`asunto`);

--
-- Indices de la tabla `estados`
--
ALTER TABLE `estados`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `estados_pgm`
--
ALTER TABLE `estados_pgm`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `estado_actual_pgm`
--
ALTER TABLE `estado_actual_pgm`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_estado_pgm` (`fk_estado_pgm`);

--
-- Indices de la tabla `historial_tickets`
--
ALTER TABLE `historial_tickets`
  ADD PRIMARY KEY (`id_historial`),
  ADD KEY `fk_ticket` (`fk_ticket`,`fk_usuario`),
  ADD KEY `fk_usuario_emisor` (`fk_usuario`),
  ADD KEY `fecha` (`fecha`),
  ADD KEY `fk_estado` (`fk_estado`),
  ADD KEY `fk_razon` (`fk_razon`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`id_permiso`);

--
-- Indices de la tabla `razones_transferencias`
--
ALTER TABLE `razones_transferencias`
  ADD PRIMARY KEY (`id_razon`);

--
-- Indices de la tabla `respuestas`
--
ALTER TABLE `respuestas`
  ADD PRIMARY KEY (`id_ticket`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `roles_permisos`
--
ALTER TABLE `roles_permisos`
  ADD PRIMARY KEY (`fk_rol`,`fk_permiso`) USING BTREE,
  ADD KEY `fk_permiso` (`fk_permiso`);

--
-- Indices de la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD PRIMARY KEY (`id_asuntoS`),
  ADD KEY `pertenece` (`pertenece`);

--
-- Indices de la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id_ticket`),
  ADD KEY `fk_usuario_emisor` (`creador`),
  ADD KEY `asunto` (`servicio`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `fk_empleado` (`fk_empleado`),
  ADD KEY `fk_permiso` (`fk_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `areas`
--
ALTER TABLE `areas`
  MODIFY `id_area` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9192;
--
-- AUTO_INCREMENT de la tabla `area_sistemas`
--
ALTER TABLE `area_sistemas`
  MODIFY `id_area_sistemas` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `asuntos`
--
ALTER TABLE `asuntos`
  MODIFY `id_asuntoP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT de la tabla `base_conocimiento`
--
ALTER TABLE `base_conocimiento`
  MODIFY `id_resolucion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `id_empleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2854;
--
-- AUTO_INCREMENT de la tabla `estados`
--
ALTER TABLE `estados`
  MODIFY `id_estado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `estados_pgm`
--
ALTER TABLE `estados_pgm`
  MODIFY `id_estado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `historial_tickets`
--
ALTER TABLE `historial_tickets`
  MODIFY `id_historial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=634;
--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `id_permiso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;
--
-- AUTO_INCREMENT de la tabla `razones_transferencias`
--
ALTER TABLE `razones_transferencias`
  MODIFY `id_razon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `servicios`
--
ALTER TABLE `servicios`
  MODIFY `id_asuntoS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=140;
--
-- AUTO_INCREMENT de la tabla `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id_ticket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=327;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asuntos`
--
ALTER TABLE `asuntos`
  ADD CONSTRAINT `asuntos_ibfk_1` FOREIGN KEY (`fk_area`) REFERENCES `areas` (`id_area`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD CONSTRAINT `empleados_ibfk_1` FOREIGN KEY (`fk_area`) REFERENCES `areas` (`id_area`);

--
-- Filtros para la tabla `encargado_servicios`
--
ALTER TABLE `encargado_servicios`
  ADD CONSTRAINT `encargado_servicios_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `encargado_servicios_ibfk_2` FOREIGN KEY (`asunto`) REFERENCES `servicios` (`id_asuntoS`);

--
-- Filtros para la tabla `estado_actual_pgm`
--
ALTER TABLE `estado_actual_pgm`
  ADD CONSTRAINT `estado_actual_pgm_ibfk_1` FOREIGN KEY (`fk_estado_pgm`) REFERENCES `estados_pgm` (`id_estado`);

--
-- Filtros para la tabla `historial_tickets`
--
ALTER TABLE `historial_tickets`
  ADD CONSTRAINT `historial_tickets_ibfk_1` FOREIGN KEY (`fk_ticket`) REFERENCES `tickets` (`id_ticket`),
  ADD CONSTRAINT `historial_tickets_ibfk_2` FOREIGN KEY (`fk_usuario`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `historial_tickets_ibfk_4` FOREIGN KEY (`fk_estado`) REFERENCES `estados` (`id_estado`),
  ADD CONSTRAINT `historial_tickets_ibfk_5` FOREIGN KEY (`fk_razon`) REFERENCES `razones_transferencias` (`id_razon`);

--
-- Filtros para la tabla `roles_permisos`
--
ALTER TABLE `roles_permisos`
  ADD CONSTRAINT `roles_permisos_ibfk_1` FOREIGN KEY (`fk_rol`) REFERENCES `roles` (`id_rol`),
  ADD CONSTRAINT `roles_permisos_ibfk_2` FOREIGN KEY (`fk_permiso`) REFERENCES `permisos` (`id_permiso`);

--
-- Filtros para la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD CONSTRAINT `servicios_ibfk_1` FOREIGN KEY (`pertenece`) REFERENCES `asuntos` (`id_asuntoP`);

--
-- Filtros para la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`creador`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `tickets_ibfk_8` FOREIGN KEY (`servicio`) REFERENCES `servicios` (`id_asuntoS`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`fk_empleado`) REFERENCES `empleados` (`id_empleado`),
  ADD CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`fk_rol`) REFERENCES `roles` (`id_rol`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
