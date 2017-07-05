-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-07-2017 a las 11:48:45
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
(9191, 'Departamento de Sueldos', 'Moreno y Moreno', 'sueldosmscb@bariloche.gov.ar'),
(9192, 'Direccion de Tesoreria', 'Mitre 531', 'tesoreriamscb@bariloche.gov.ar'),
(9193, 'Tribunal de faltas II ', 'Terminal Omnibus', 'tribunalfaltas2mscb@bariloche.gov.ar'),
(9194, 'Dpto Terminal de Omnibus', 'Terminal', 'terminalmscb@bariloche.gov.ar');

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
(25, 'Veterinaria', 38, 0),
(28, 'Carpinteria de obra', 9185, 0),
(29, 'Carpinteria de banco', 9185, 0),
(30, 'Pintura', 9185, 0),
(31, 'Herreria', 9185, 0),
(32, 'Plomeria y gas', 9185, 0),
(33, 'Electricidad', 9185, 0),
(34, 'Albañileria', 9185, 0);

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
(2853, 'claudia', 'claudia', '12345987', 38, 988888),
(2854, 'Fernando', 'Arroyo', '22118188', 9185, 686),
(2855, 'Mariano', 'Llanquitur', '16702868', 9185, 20587),
(2856, 'Fabian', 'Munch', '26413059', 9185, 11532),
(2857, 'Osvaldo', 'Prado', '17062344', 9185, 352),
(2859, 'Alejandra', 'Sanders', '25256908', 9185, 11221),
(2860, 'Maria Elisabeth', 'Mella Paredes', '188933818', 9185, 11103),
(2861, 'Mariano', 'Llanquitur', '16702868', 9185, 20587),
(2862, 'Luciano', 'Antonio', 'Paredes', 9185, 20012),
(2863, 'Victor Antonio', 'Pichiñanco', '32213511', 9185, 14111),
(2864, 'Victor Hugo', 'Ruiz', '11131791', 9185, 408),
(2865, 'Mauro Ariel', 'Cardenas Arismendi', '34667555', 9185, 11920),
(2866, 'Fidel Rene', 'Mamani', '13161833', 9185, 790),
(2867, 'Alejo Gabriel', 'Zurano', '33918177', 9185, 20013),
(2868, 'Miguel Angel', 'Vargas', '13989593', 9185, 11381),
(2869, 'Miguel', 'Macaya', '11581226', 9185, 425),
(2870, 'Miguel Angel', 'Inostroza', '13812061', 9185, 11486),
(2871, 'Jorge Alberto', 'Quesada', '16235307', 9185, 1085),
(2872, 'Luis Daniel', 'Catriman', '17451257', 9185, 20575),
(2873, 'Danilo', 'Rosas', '92765874', 9185, 40),
(2874, 'Desiderio', 'Silva', '13123541', 9185, 781),
(2875, 'Hector', 'Sanchez', '26019654', 9185, 514),
(2876, 'Luis Osvaldo', 'Sandoval', '31244422', 9185, 11152),
(2877, 'Daniel Eduardo', 'Comiquil', '30784169', 9185, 11134),
(2878, 'Rodrigo Ezequiel', 'Antimil', '35594598', 9185, 20007),
(2879, 'Ezequiel Eduardo', 'Painefil', '35818060', 9185, 14275),
(2880, 'Luis Alberto', 'Sambueza', '25599796', 9185, 11171),
(2881, 'Jose Basilio', 'Maldonado', '13989273', 9185, 11160),
(2882, 'Leandro', 'Paredes', '32213734', 9185, 12296),
(2883, 'Omar Antonio', 'Huenuman', '20679043', 9185, 21629),
(2884, 'Nelson', 'Gelvez', '31943146', 9185, 735),
(2885, 'Juan Carlos', 'Inostroza', '13812078', 9185, 12539),
(2886, 'Elidoro', 'Muñoz', '16053695', 9185, 11260),
(2887, 'Leonardo Favio', 'Ancavil', '20679497', 9185, 14300),
(2888, 'Luciano', 'Paredes', '35818002', 9185, 20012);

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
(3, 140),
(3, 141),
(3, 142),
(3, 143),
(3, 144),
(3, 145),
(3, 146),
(3, 147),
(3, 148),
(3, 149),
(3, 150),
(3, 151),
(3, 152),
(3, 153),
(3, 154),
(3, 155),
(3, 156),
(3, 157),
(3, 158),
(3, 159),
(3, 160),
(3, 161),
(3, 162),
(3, 163),
(3, 165),
(3, 166),
(3, 167),
(3, 168),
(3, 169),
(3, 170),
(3, 171),
(3, 172),
(3, 173),
(3, 174),
(3, 175),
(3, 176),
(3, 177),
(3, 178),
(3, 179),
(3, 180),
(3, 181),
(3, 182),
(3, 183),
(3, 184),
(3, 185),
(3, 186),
(3, 187),
(3, 188),
(3, 189),
(3, 190),
(3, 191),
(3, 192),
(3, 193),
(3, 194),
(3, 195),
(3, 196),
(3, 197),
(3, 198),
(3, 199),
(3, 200),
(3, 201),
(3, 202),
(3, 203),
(3, 204),
(3, 205),
(3, 206),
(3, 207),
(3, 208),
(3, 209),
(3, 210),
(3, 211),
(3, 212),
(3, 213),
(3, 214),
(3, 215),
(3, 216),
(3, 217),
(3, 218),
(3, 219),
(3, 220),
(3, 221),
(3, 222),
(3, 223),
(3, 224),
(3, 225),
(3, 226),
(3, 227),
(3, 228),
(3, 229),
(3, 230),
(3, 231),
(3, 232),
(3, 233),
(3, 234),
(3, 235),
(3, 236),
(3, 237),
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
(18, 140),
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
(21, 107),
(21, 108),
(21, 109),
(21, 110),
(21, 111),
(21, 113),
(21, 114),
(21, 120),
(21, 121),
(21, 127),
(21, 128),
(21, 132),
(21, 134),
(21, 135),
(21, 136),
(21, 139),
(21, 140),
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
(27, 54),
(27, 102),
(27, 112),
(27, 113),
(27, 114),
(27, 125),
(27, 127),
(27, 129),
(27, 130),
(27, 131),
(27, 139),
(29, 48),
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
(30, 48),
(31, 141),
(31, 142),
(31, 143),
(31, 144),
(31, 145),
(31, 146),
(31, 147),
(31, 148),
(31, 149),
(31, 150),
(31, 151),
(31, 152),
(31, 153),
(31, 154),
(31, 155),
(31, 156),
(31, 157),
(31, 158),
(31, 159),
(31, 160),
(31, 161),
(31, 162),
(31, 163),
(31, 165),
(31, 166),
(31, 167),
(31, 168),
(31, 169),
(31, 170),
(31, 171),
(31, 172),
(31, 173),
(31, 174),
(31, 175),
(31, 176),
(31, 177),
(31, 178),
(31, 179),
(31, 180),
(31, 181),
(31, 182),
(31, 183),
(31, 184),
(31, 185),
(31, 186),
(31, 187),
(31, 188),
(31, 189),
(31, 190),
(31, 191),
(31, 192),
(31, 193),
(31, 194),
(31, 195),
(31, 196),
(31, 197),
(31, 198),
(31, 199),
(31, 200),
(31, 201),
(31, 202),
(31, 203),
(31, 204),
(31, 205),
(31, 206),
(31, 207),
(31, 208),
(31, 209),
(31, 210),
(31, 211),
(31, 212),
(31, 213),
(31, 214),
(31, 215),
(31, 216),
(31, 217),
(31, 218),
(31, 219),
(31, 220),
(31, 221),
(31, 222),
(31, 223),
(31, 224),
(31, 225),
(31, 226),
(31, 227),
(31, 228),
(31, 229),
(31, 230),
(31, 231),
(31, 232),
(31, 233),
(31, 234),
(31, 235),
(31, 236),
(31, 237),
(33, 141),
(33, 142),
(33, 143),
(33, 144),
(33, 145),
(33, 146),
(33, 147),
(33, 148),
(33, 149),
(33, 150),
(33, 151),
(33, 152),
(33, 153),
(33, 154),
(33, 155),
(33, 156),
(33, 157),
(33, 158),
(33, 159),
(33, 160),
(33, 161),
(33, 162),
(33, 163),
(33, 165),
(33, 166),
(33, 167),
(33, 168),
(33, 169),
(33, 170),
(33, 171),
(33, 172),
(33, 173),
(33, 174),
(33, 175),
(33, 176),
(33, 177),
(33, 178),
(33, 179),
(33, 180),
(33, 181),
(33, 182),
(33, 183),
(33, 184),
(33, 185),
(33, 186),
(33, 187),
(33, 188),
(33, 189),
(33, 190),
(33, 191),
(33, 192),
(33, 193),
(33, 194),
(33, 195),
(33, 196),
(33, 197),
(33, 198),
(33, 199),
(33, 200),
(33, 201),
(33, 202),
(33, 203),
(33, 204),
(33, 205),
(33, 206),
(33, 207),
(33, 208),
(33, 209),
(33, 210),
(33, 211),
(33, 212),
(33, 213),
(33, 214),
(33, 215),
(33, 216),
(33, 217),
(33, 218),
(33, 219),
(33, 220),
(33, 221),
(33, 222),
(33, 223),
(33, 224),
(33, 225),
(33, 226),
(33, 227),
(33, 228),
(33, 229),
(33, 230),
(33, 231),
(33, 232),
(33, 233),
(33, 234),
(33, 235),
(33, 236),
(33, 237),
(34, 141),
(34, 142),
(34, 143),
(34, 144),
(34, 145),
(34, 146),
(34, 147),
(34, 148),
(34, 149),
(34, 150),
(34, 151),
(34, 152),
(34, 153),
(34, 154),
(34, 155),
(34, 156),
(34, 157),
(34, 158),
(34, 159),
(34, 160),
(34, 161),
(34, 162),
(34, 163),
(34, 165),
(34, 166),
(34, 167),
(34, 168),
(34, 169),
(34, 170),
(34, 171),
(34, 172),
(34, 173),
(34, 174),
(34, 175),
(34, 176),
(34, 177),
(34, 178),
(34, 179),
(34, 180),
(34, 181),
(34, 182),
(34, 183),
(34, 184),
(34, 185),
(34, 186),
(34, 187),
(34, 188),
(34, 189),
(34, 190),
(34, 191),
(34, 192),
(34, 193),
(34, 194),
(34, 195),
(34, 196),
(34, 197),
(34, 198),
(34, 199),
(34, 200),
(34, 201),
(34, 202),
(34, 203),
(34, 204),
(34, 205),
(34, 206),
(34, 207),
(34, 208),
(34, 209),
(34, 210),
(34, 211),
(34, 212),
(34, 213),
(34, 214),
(34, 215),
(34, 216),
(34, 217),
(34, 218),
(34, 219),
(34, 220),
(34, 221),
(34, 222),
(34, 223),
(34, 224),
(34, 225),
(34, 226),
(34, 227),
(34, 228),
(34, 229),
(34, 230),
(34, 231),
(34, 232),
(34, 233),
(34, 234),
(34, 235),
(34, 236),
(34, 237),
(36, 141),
(36, 142),
(36, 143),
(36, 144),
(36, 145),
(36, 146),
(36, 147),
(36, 148),
(36, 149),
(36, 150),
(36, 151),
(41, 152),
(41, 153),
(41, 154),
(41, 155),
(41, 156),
(41, 157),
(41, 158),
(44, 159),
(44, 160),
(44, 161),
(44, 162),
(44, 163),
(44, 165),
(44, 166),
(49, 167),
(49, 168),
(49, 169),
(49, 170),
(49, 171),
(49, 172),
(49, 173),
(49, 174),
(49, 175),
(49, 176),
(51, 177),
(51, 178),
(51, 179),
(51, 180),
(51, 181),
(51, 182),
(51, 183),
(51, 184),
(51, 185),
(51, 186),
(51, 187),
(51, 188),
(51, 189),
(51, 190),
(51, 191),
(51, 192),
(51, 193),
(51, 194),
(51, 195),
(51, 196),
(51, 197),
(51, 198),
(51, 199),
(51, 200),
(51, 201),
(51, 202),
(51, 203),
(51, 204),
(51, 205),
(51, 206),
(51, 207),
(51, 208),
(51, 209),
(51, 210),
(54, 211),
(54, 212),
(54, 213),
(54, 214),
(54, 215),
(54, 216),
(54, 217),
(54, 218),
(54, 219),
(54, 220),
(54, 221),
(54, 222),
(54, 223),
(54, 224),
(54, 225),
(54, 226),
(58, 227),
(58, 228),
(58, 229),
(58, 230),
(58, 231),
(58, 232),
(58, 233),
(58, 234),
(58, 235),
(58, 236),
(58, 237);

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
(7, 'Eliminado'),
(8, 'Control');

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
(1, 1, '2017-06-30 14:04:53');

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
(633, 326, 21, '2017-05-31', 1, NULL, NULL),
(634, 255, 21, '2017-06-01', 4, NULL, NULL),
(635, 237, 21, '2017-06-01', 5, 'Resolucion por eklein : Ya lo verificó Vanesa de Sueldos, funciona bien ahora.', NULL),
(636, 255, 21, '2017-06-01', 5, 'Resolucion por eklein : Lo probo Vanesa, funciona bien.', NULL),
(637, 301, 21, '2017-06-01', 5, 'Resolucion por eklein : Lo hizo bruno, reinició las claves y cambió los usuarios con ñ por n', NULL),
(638, 321, 18, '2017-06-01', 5, 'Resolucion por avarano : Lo cargo Pgm', NULL),
(639, 327, 18, '2017-06-01', 1, NULL, NULL),
(640, 328, 3, '2017-06-01', 1, NULL, NULL),
(641, 327, 18, '2017-06-02', 5, 'Resolucion por avarano : Las recaudaciones de más en la cuenta “51108 Habilitaciones Comerciales” se debieron a que hubo ítems erróneamente vinculados a esa cuenta. \nEn el mes de abril y ante la consulta de la Cdora. Lilian Baroni, se revisó la vinculación, se corrigieron ítems mal vinculados y se le informó que para corregir esa recaudación en más se debería efectuar el movimiento contable correspondiente.\n \nCon el reporte “Tesor-Recaudación por cuenta contable entre fechas” pueden obtenerse los importes recaudados por ítem entre fechas para determinada cuenta de ingreso. A partir de esa información se pueden determinar los importes a ajustar, que son los que corresponden a los ítems mal vinculados.\n \nCon el reporte “Tesor-Ítems vinculados a una cuenta” pueden verse que ítems están actualmente vinculados a una determinada cuenta de ingreso, para efectuar una revisión de estos datos.', NULL),
(642, 329, 18, '2017-06-02', 1, NULL, NULL),
(643, 329, 18, '2017-06-02', 4, NULL, NULL),
(644, 307, 18, '2017-06-02', 5, 'Resolucion por avarano : si correspondia. se corrigieron los montos porque tiene una eximición. y se anulo la incidencia de la cuota 3 porque lo pago en banco patagonia.\n', NULL),
(645, 318, 18, '2017-06-02', 5, 'Resolucion por avarano : ya lo resolvio claudia.', NULL),
(646, 293, 18, '2017-06-02', 5, 'Resolucion por avarano : Lo resolvieron de Pgm cordoba:\n el problema era que la cuenta de ingreso: “\'51174\', \'PI\', \'TARJETA SUBE\'” estaba usada en abril y no estaba en ninguna ordenanza, yo la agregue en la primera ordenanza q tienen en abril: “\'Nro.:044SH17\' del 2017-04-04”, en el vigente tenia presupuesto 0.00 la cargue igual, el balance esta CERRADO.', NULL),
(647, 287, 18, '2017-06-02', 3, NULL, NULL),
(648, 282, 18, '2017-06-02', 5, 'Resolucion por avarano : Lo termino Matias', NULL),
(649, 277, 18, '2017-06-02', 3, NULL, NULL),
(650, 274, 18, '2017-06-02', 5, 'Resolucion por avarano : desestimado:(', NULL),
(651, 114, 18, '2017-06-02', 3, NULL, NULL),
(652, 112, 18, '2017-06-02', 3, NULL, NULL),
(653, 108, 18, '2017-06-02', 3, NULL, NULL),
(654, 107, 18, '2017-06-02', 3, NULL, NULL),
(655, 106, 18, '2017-06-02', 3, NULL, NULL),
(656, 105, 18, '2017-06-02', 3, NULL, NULL),
(657, 104, 18, '2017-06-02', 3, NULL, NULL),
(658, 329, 18, '2017-06-02', 3, NULL, NULL),
(659, 330, 18, '2017-06-02', 1, NULL, NULL),
(660, 331, 21, '2017-06-02', 1, NULL, NULL),
(661, 331, 21, '2017-06-02', 5, 'Resolucion por eklein : Listo Claudia, te sugiero que revises todos los legajos que tenés con 28 puntos.\n \nSaludos!\n \nEstefanía\n(Sistemas - Interno 140)', NULL),
(662, 317, 18, '2017-06-02', 5, 'Resolucion por avarano : La boleta 9006177858 correspondiente a la cuota 2 del talonario 46526 que venció el 15/5 no se envió al débito ya que el envío se realizó el día 26/4 y la adhesión fue del día 28/4.\n', NULL),
(663, 332, 21, '2017-06-05', 1, NULL, NULL),
(664, 332, 21, '2017-06-05', 5, 'Resolucion por eklein : Ya le agregué la novedad al combo.', NULL),
(665, 333, NULL, '2017-06-05', 1, NULL, NULL),
(666, 334, 18, '2017-06-05', 1, NULL, NULL),
(667, 335, 18, '2017-06-05', 1, NULL, NULL),
(668, 336, 18, '2017-06-05', 1, NULL, NULL),
(669, 337, 18, '2017-06-05', 1, NULL, NULL),
(670, 338, NULL, '2017-06-05', 1, NULL, NULL),
(671, 339, 21, '2017-06-05', 1, NULL, NULL),
(672, 340, 21, '2017-06-05', 1, NULL, NULL),
(673, 340, 21, '2017-06-05', 5, 'Resolucion por eklein : Agregada.-', NULL),
(674, 339, 21, '2017-06-05', 5, 'Resolucion por eklein : Agregada, estaba Batalla de Camacua pero me dijo que esa no era, así que agregué CAMACUA.', NULL),
(675, 324, 21, '2017-06-05', 5, 'Resolucion por eklein : No la actualizaron, en lugar de eso actualizamos el 233 acá para hacer la prueba.', NULL),
(676, 303, 21, '2017-06-05', 5, 'Resolucion por eklein : Listo, hice la parte gráfica, Mati hizo la persistencia', NULL),
(677, 341, 18, '2017-06-06', 1, NULL, NULL),
(678, 341, 18, '2017-06-06', 5, 'Resolucion por avarano : la cuota 12 liquido sin problemas , para la 11 borre categoria zona 0 del 01/01/17\n(repetida por distintos metros frente), liquide y luego volvi a cargar.\n', NULL),
(679, 335, 18, '2017-06-06', 5, 'Resolucion por avarano : se envio archivo complementario.', NULL),
(680, 330, 18, '2017-06-06', 5, 'Resolucion por avarano : actualizado al 31/7', NULL),
(681, 342, NULL, '2017-06-06', 1, NULL, NULL),
(682, 342, 18, '2017-06-06', 3, NULL, NULL),
(683, 343, 18, '2017-06-06', 1, NULL, NULL),
(684, 343, 18, '2017-06-06', 5, 'Resolucion por avarano : modifico importe cuando grabo y no tomaba todo el compromiso.', NULL),
(685, 344, 21, '2017-06-07', 1, NULL, NULL),
(686, 344, 21, '2017-06-07', 5, 'Resolucion por eklein : \n\nEstefanía:\n\nEl control del calendario es a modo de ayuda y opcional, para datos \nanteriores a ese año es más práctico tipearlos directamente.\n\nSaludos.-\n\nIng. Fernando M. Gonzalez\nConsultor Senior\nProgram Consultores S.A.\nJuan del Campillo 779\n(X5000GTO) Cordoba - Argentina\nTel/fax (0351) 4474200 y rotativas\nWeb.Site: www.municipalidad.com', NULL),
(687, 345, 21, '2017-06-08', 1, NULL, NULL),
(688, 345, 21, '2017-06-08', 5, 'Resolucion por eklein : Agrgado a tabla SLD_GLOBAL_SUELDOS como TAFUT123 - TEC-SEGURIDAD E HIGIENE', NULL),
(689, 346, NULL, '2017-06-09', 1, NULL, NULL),
(690, 347, NULL, '2017-06-09', 1, NULL, NULL),
(691, 348, 18, '2017-06-09', 1, NULL, NULL),
(692, 349, 18, '2017-06-09', 1, NULL, NULL),
(693, 350, NULL, '2017-06-09', 1, NULL, NULL),
(694, 351, 27, '2017-06-09', 1, NULL, NULL),
(695, 351, 27, '2017-06-09', 5, NULL, NULL),
(696, 351, 27, '2017-06-09', 2, NULL, NULL),
(697, 351, 27, '2017-06-09', 5, NULL, NULL),
(698, 352, 18, '2017-06-12', 1, NULL, NULL),
(699, 352, 18, '2017-06-12', 5, 'Resolucion por avarano : se mandaron los talonarios de cloacas tipo Omega-ord2016-CM-10, después de que PGm corrigio el proceso.', NULL),
(700, 353, 18, '2017-06-12', 1, NULL, NULL),
(701, 353, 18, '2017-06-12', 5, 'Resolucion por avarano : Di permiso e indique procesa por encargado ya que la cuenta de obras tiene una letra y no deja buscar por cuenta', NULL),
(702, 354, 18, '2017-06-12', 1, NULL, NULL),
(703, 354, 18, '2017-06-12', 5, 'Resolucion por avarano : Se realizo la modificacion', NULL),
(704, 355, NULL, '2017-06-12', 1, NULL, NULL),
(705, 356, 18, '2017-06-12', 1, NULL, NULL),
(706, 355, 18, '2017-06-12', 5, 'Resolucion por avarano : encontraron en el area', NULL),
(707, 357, 27, '2017-06-13', 1, NULL, NULL),
(708, 357, 27, '2017-06-13', 5, NULL, NULL),
(709, 358, NULL, '2017-06-13', 1, NULL, NULL),
(710, 359, NULL, '2017-06-14', 1, NULL, NULL),
(711, 360, 18, '2017-06-14', 1, NULL, NULL),
(712, 361, NULL, '2017-06-14', 1, NULL, NULL),
(713, 359, 18, '2017-06-15', 5, 'Resolucion por avarano : Hablado con Alejandro. Lo hacen asi por indicacion de Pgm porque con cajero banco no tienen el \nticket de comprobante de  pago. Hacienda podria hacer disposición para que en caso de transferencia\nfuera otro el comprobante.', NULL),
(714, 348, 18, '2017-06-15', 5, 'Resolucion por avarano : Mandé excel a Contribuciones.', NULL),
(715, 349, 18, '2017-06-15', 5, 'Resolucion por avarano : Mandé excel a Contribuciones.', NULL),
(716, 114, 18, '2017-06-19', 5, 'Resolucion por avarano : resuelto con actualizacion pgm 14/06', NULL),
(717, 273, 18, '2017-06-19', 5, 'Resolucion por avarano : realizado por Gustavo ', NULL),
(718, 362, 27, '2017-06-19', 1, NULL, NULL),
(719, 362, 27, '2017-06-19', 6, NULL, NULL),
(720, 290, 18, '2017-06-19', 5, 'Resolucion por avarano : realizado', NULL),
(721, 334, 18, '2017-06-19', 5, 'Resolucion por avarano : generadas', NULL),
(722, 342, 18, '2017-06-19', 5, 'Resolucion por avarano : Modificado por pgm', NULL),
(723, 362, 27, '2017-06-19', 5, NULL, NULL),
(724, 363, NULL, '2017-06-22', 1, NULL, NULL),
(725, 363, 31, '2017-06-22', 4, NULL, NULL),
(726, 363, 31, '2017-06-22', 4, NULL, NULL),
(727, 363, 31, '2017-06-22', 3, NULL, NULL),
(728, 363, 31, '2017-06-22', 5, 'Resolucion por farroyo : ', NULL),
(729, 364, NULL, '2017-06-22', 1, NULL, NULL),
(730, 364, 31, '2017-06-22', 2, NULL, NULL),
(731, 364, 31, '2017-06-22', 3, NULL, NULL),
(732, 364, 31, '2017-06-22', 4, NULL, NULL),
(733, 364, 31, '2017-06-22', 3, NULL, NULL),
(734, 364, 31, '2017-06-22', 4, NULL, NULL),
(735, 364, 31, '2017-06-22', 3, NULL, NULL),
(736, 364, 31, '2017-06-22', 4, NULL, NULL),
(737, 364, 31, '2017-06-22', 3, NULL, NULL),
(738, 364, 31, '2017-06-22', 5, 'Resolucion por farroyo : ', NULL),
(739, 365, NULL, '2017-06-22', 1, NULL, NULL),
(740, 366, NULL, '2017-06-22', 1, NULL, NULL),
(741, 367, NULL, '2017-06-22', 1, NULL, NULL),
(742, 368, NULL, '2017-06-22', 1, NULL, NULL),
(743, 369, NULL, '2017-06-22', 1, NULL, NULL),
(744, 370, NULL, '2017-06-22', 1, NULL, NULL),
(745, 371, NULL, '2017-06-22', 1, NULL, NULL),
(746, 372, NULL, '2017-06-22', 1, NULL, NULL),
(747, 373, NULL, '2017-06-22', 1, NULL, NULL),
(748, 374, NULL, '2017-06-22', 1, NULL, NULL),
(749, 358, 31, '2017-06-22', 5, 'Resolucion por farroyo : ', NULL),
(750, 365, 31, '2017-06-22', 5, 'Resolucion por farroyo : ', NULL),
(751, 366, 31, '2017-06-22', 2, NULL, NULL),
(752, 369, 31, '2017-06-23', 2, NULL, NULL),
(753, 370, 31, '2017-06-23', 2, NULL, NULL),
(754, 372, 31, '2017-06-23', 2, NULL, NULL),
(755, 373, 31, '2017-06-23', 2, NULL, NULL),
(756, 367, 31, '2017-06-23', 2, NULL, NULL),
(757, 368, 31, '2017-06-23', 2, NULL, NULL),
(758, 371, 31, '2017-06-23', 2, NULL, NULL),
(759, 374, 31, '2017-06-23', 2, NULL, NULL),
(760, 125, 3, '2017-06-23', 6, NULL, NULL),
(761, 125, 3, '2017-06-23', 5, 'Resolucion por mbenditti : ', NULL),
(762, 240, 3, '2017-06-23', 8, NULL, NULL),
(763, 375, NULL, '2017-06-26', 1, NULL, NULL),
(764, 376, NULL, '2017-06-26', 1, NULL, NULL),
(765, 377, NULL, '2017-06-26', 1, NULL, NULL),
(766, 375, 31, '2017-06-26', 2, NULL, NULL),
(767, 376, 31, '2017-06-26', 2, NULL, NULL),
(768, 377, 31, '2017-06-26', 2, NULL, NULL),
(769, 378, NULL, '2017-06-26', 1, NULL, NULL),
(770, 378, 31, '2017-06-26', 2, NULL, NULL),
(771, 379, NULL, '2017-06-27', 1, NULL, NULL),
(772, 380, NULL, '2017-06-27', 1, NULL, NULL),
(773, 381, NULL, '2017-06-27', 1, NULL, NULL),
(774, 382, NULL, '2017-06-27', 1, NULL, NULL),
(775, 383, NULL, '2017-06-27', 1, NULL, NULL),
(776, 384, NULL, '2017-06-27', 1, NULL, NULL),
(777, 385, NULL, '2017-06-27', 1, NULL, NULL),
(778, 386, NULL, '2017-06-27', 1, NULL, NULL),
(779, 387, NULL, '2017-06-27', 1, NULL, NULL),
(780, 388, NULL, '2017-06-27', 1, NULL, NULL),
(781, 389, NULL, '2017-06-27', 1, NULL, NULL),
(782, 390, NULL, '2017-06-27', 1, NULL, NULL),
(783, 391, NULL, '2017-06-27', 1, NULL, NULL),
(784, 389, 31, '2017-06-27', 2, NULL, NULL),
(785, 388, 31, '2017-06-27', 2, NULL, NULL),
(786, 387, 31, '2017-06-27', 2, NULL, NULL),
(787, 390, 31, '2017-06-27', 2, NULL, NULL),
(788, 391, 31, '2017-06-27', 5, 'Resolucion por farroyo : ', NULL),
(789, 392, NULL, '2017-06-27', 1, NULL, NULL),
(790, 392, 31, '2017-06-27', 2, NULL, NULL),
(791, 386, 31, '2017-06-27', 2, NULL, NULL),
(792, 385, 31, '2017-06-27', 2, NULL, NULL),
(793, 384, 31, '2017-06-27', 2, NULL, NULL),
(794, 383, 31, '2017-06-27', 2, NULL, NULL),
(795, 382, 31, '2017-06-27', 2, NULL, NULL),
(796, 381, 31, '2017-06-27', 2, NULL, NULL),
(797, 380, 31, '2017-06-27', 2, NULL, NULL),
(798, 379, 31, '2017-06-27', 5, 'Resolucion por farroyo : se va a conectar solo electricidad', NULL),
(799, 393, NULL, '2017-06-28', 1, NULL, NULL),
(800, 393, 31, '2017-06-28', 2, NULL, NULL),
(801, 394, NULL, '2017-06-28', 1, NULL, NULL),
(802, 394, 31, '2017-06-28', 2, NULL, NULL),
(803, 395, NULL, '2017-06-28', 1, NULL, NULL),
(804, 395, 31, '2017-06-28', 2, NULL, NULL),
(816, 376, 31, '2017-06-28', 3, NULL, NULL),
(817, 398, NULL, '2017-06-28', 1, NULL, NULL),
(818, 399, NULL, '2017-06-28', 1, NULL, NULL),
(819, 398, 31, '2017-06-28', 2, NULL, NULL),
(820, 399, 31, '2017-06-28', 2, NULL, NULL),
(821, 400, NULL, '2017-06-28', 1, NULL, NULL),
(822, 400, 31, '2017-06-28', 2, NULL, NULL),
(823, 401, NULL, '2017-06-28', 1, NULL, NULL),
(824, 246, 3, '2017-06-30', 5, 'Resolucion por mbenditti : ', NULL),
(825, 402, NULL, '2017-07-03', 1, NULL, NULL),
(826, 403, NULL, '2017-07-03', 1, NULL, NULL),
(827, 404, NULL, '2017-07-03', 1, NULL, NULL),
(828, 405, NULL, '2017-07-03', 1, NULL, NULL),
(829, 406, NULL, '2017-07-03', 1, NULL, NULL),
(830, 407, NULL, '2017-07-03', 1, NULL, NULL),
(831, 405, 31, '2017-07-03', 2, NULL, NULL),
(832, 408, NULL, '2017-07-03', 1, NULL, NULL),
(833, 409, NULL, '2017-07-03', 1, NULL, NULL),
(834, 409, 31, '2017-07-03', 4, NULL, NULL),
(835, 409, 31, '2017-07-03', 4, NULL, NULL),
(836, 410, NULL, '2017-07-03', 1, NULL, NULL),
(837, 411, NULL, '2017-07-03', 1, NULL, NULL),
(838, 412, NULL, '2017-07-03', 1, NULL, NULL),
(839, 413, NULL, '2017-07-03', 1, NULL, NULL),
(840, 414, NULL, '2017-07-03', 1, NULL, NULL),
(841, 415, NULL, '2017-07-03', 1, NULL, NULL),
(842, 415, 31, '2017-07-03', 4, NULL, NULL),
(843, 416, NULL, '2017-07-03', 1, NULL, NULL),
(844, 417, NULL, '2017-07-03', 1, NULL, NULL),
(845, 416, 31, '2017-07-03', 4, NULL, NULL),
(846, 418, NULL, '2017-07-03', 1, NULL, NULL),
(847, 419, NULL, '2017-07-04', 1, NULL, NULL),
(848, 414, 31, '2017-07-04', 2, NULL, NULL),
(849, 414, 31, '2017-07-04', 1, 'Resolucion por farroyo : CORRESPONDE A PLOMERIA', NULL),
(850, 420, NULL, '2017-07-04', 1, NULL, NULL),
(851, 421, NULL, '2017-07-04', 1, NULL, NULL),
(852, 422, NULL, '2017-07-04', 1, NULL, NULL),
(853, 423, NULL, '2017-07-04', 1, NULL, NULL),
(854, 402, 31, '2017-07-04', 4, NULL, NULL),
(855, 424, NULL, '2017-07-04', 1, NULL, NULL),
(856, 425, NULL, '2017-07-04', 1, NULL, NULL),
(857, 366, 51, '2017-07-04', 2, NULL, NULL),
(858, 375, 51, '2017-07-04', 2, NULL, NULL),
(859, 377, 51, '2017-07-04', 2, NULL, NULL),
(860, 378, 51, '2017-07-04', 2, NULL, NULL),
(861, 386, 51, '2017-07-04', 2, NULL, NULL),
(862, 387, 51, '2017-07-04', 2, NULL, NULL),
(863, 389, 51, '2017-07-04', 2, NULL, NULL),
(864, 395, 51, '2017-07-04', 2, NULL, NULL),
(865, 398, 51, '2017-07-04', 2, NULL, NULL),
(866, 399, 51, '2017-07-04', 2, NULL, NULL),
(867, 400, 51, '2017-07-04', 2, NULL, NULL),
(868, 402, 51, '2017-07-04', 2, NULL, NULL),
(869, 406, 51, '2017-07-04', 2, NULL, NULL),
(870, 410, 51, '2017-07-04', 2, NULL, NULL),
(871, 411, 51, '2017-07-04', 2, NULL, NULL),
(872, 416, 51, '2017-07-04', 2, NULL, NULL),
(873, 418, 51, '2017-07-04', 2, NULL, NULL),
(874, 420, 51, '2017-07-04', 2, NULL, NULL),
(875, 421, 51, '2017-07-04', 2, NULL, NULL),
(876, 423, 51, '2017-07-04', 2, NULL, NULL),
(877, 368, 41, '2017-07-04', 2, NULL, NULL),
(878, 380, 41, '2017-07-04', 2, NULL, NULL),
(879, 393, 41, '2017-07-04', 2, NULL, NULL),
(880, 401, 41, '2017-07-04', 2, NULL, NULL),
(881, 407, 41, '2017-07-04', 2, NULL, NULL),
(882, 367, 36, '2017-07-04', 2, NULL, NULL),
(883, 371, 36, '2017-07-04', 2, NULL, NULL),
(884, 374, 36, '2017-07-04', 2, NULL, NULL),
(885, 384, 36, '2017-07-04', 2, NULL, NULL),
(886, 385, 36, '2017-07-04', 2, NULL, NULL),
(887, 412, 36, '2017-07-04', 2, NULL, NULL),
(888, 392, 44, '2017-07-04', 2, NULL, NULL),
(889, 394, 58, '2017-07-04', 2, NULL, NULL),
(890, 404, 58, '2017-07-04', 2, NULL, NULL),
(891, 426, NULL, '2017-07-04', 1, NULL, NULL),
(892, 427, NULL, '2017-07-04', 1, NULL, NULL),
(893, 428, NULL, '2017-07-04', 1, NULL, NULL),
(894, 429, NULL, '2017-07-04', 1, NULL, NULL),
(895, 430, NULL, '2017-07-04', 1, NULL, NULL),
(896, 431, NULL, '2017-07-04', 1, NULL, NULL),
(897, 432, NULL, '2017-07-04', 1, NULL, NULL),
(898, 433, NULL, '2017-07-04', 1, NULL, NULL),
(899, 434, NULL, '2017-07-04', 1, NULL, NULL),
(900, 435, NULL, '2017-07-04', 1, NULL, NULL),
(901, 436, NULL, '2017-07-05', 1, NULL, NULL);

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
(44, 'versiones'),
(45, 'modificarPatrimonio');

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
(4, 8),
(1, 9),
(4, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(3, 13),
(4, 13),
(1, 14),
(1, 15),
(1, 16),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 21),
(1, 22),
(1, 23),
(1, 24),
(1, 25),
(1, 26),
(1, 27),
(4, 27),
(1, 28),
(1, 29),
(1, 30),
(1, 31),
(1, 32),
(1, 33),
(1, 34),
(3, 34),
(4, 34),
(1, 35),
(1, 36),
(1, 37),
(1, 38),
(1, 39),
(1, 40),
(1, 41),
(1, 42),
(1, 43),
(1, 44),
(1, 45),
(4, 45);

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
(139, 'Otros', 23),
(140, 'Modificación de Reportes', 21),
(141, 'Reparacion de cerraduras de puertas', 28),
(142, 'Reparacion de cerraduras de muebles', 28),
(143, 'Reparacion o ejecucion de tabiques de placa de yeso', 28),
(144, 'Reparacion o ejecucion de techos', 28),
(145, 'Reparacion de mobiliario', 28),
(146, 'Reparacion por ingreso de agua en ventanas y/o aire', 28),
(147, 'Reaparacion o colocacion o retiro de piso flotante', 28),
(148, 'Reparacion o colocacion o retiro de alfombras', 28),
(149, 'Reparacion o ejecucion de pisos de madera', 28),
(150, 'Varios', 28),
(151, 'Proyecto especial', 28),
(152, 'Reparacion o ejecucion de estanterias para archivo', 29),
(153, 'Reparacion o ejecucion de repisas para pared', 29),
(154, 'Reparacion o ejecucion de sillas de madera', 29),
(155, 'Reparacion o ejecucion de mesas de madera', 29),
(156, 'Reparacion o ejecucion de aberturas de madera', 29),
(157, 'Varios', 29),
(158, 'Proyecto especial', 29),
(159, 'Pintura interior de edificios', 30),
(160, 'Pintura exterior de edificios', 30),
(161, 'Pintura de elementos de madera', 30),
(162, 'Pintura de cercos y aberturas de metal', 30),
(163, 'Pinturas y terminaciones de yeso o masilla', 30),
(165, 'Varios', 30),
(166, 'Proyecto especial', 30),
(167, 'Reparacion de herramientes de servicios publicos', 31),
(168, 'Reparacion o ejecucion de cercos perimetrales metalicos', 31),
(169, 'Reparacion o ejecucion de puertas o portones metalicos exteriores', 31),
(170, 'Reparacion o ejecucion de mobiliario metalico', 31),
(171, 'Reparacion o ejecucion de rejas', 31),
(172, 'Reparacion o ejecucion de juegos de plazas', 31),
(173, 'Reparacion o ejecucion de aberturas metalicas', 31),
(174, 'Reparacion o ejecucion de estructuras metalicas', 31),
(175, 'Varios', 31),
(176, 'Proyectos especiales', 31),
(177, 'Agua; Sanitarios; Roto', 32),
(178, 'Agua; Sanitarios; Perdida', 32),
(179, 'Agua; Sanitarios; Sin agua', 32),
(180, 'Agua; Sanitarios; Nuevo', 32),
(181, 'Agua; Tanque; Pierde', 32),
(182, 'Agua; Tanque; Rebalsa', 32),
(183, 'Agua; Conexion domiciliaria; Rota por maquina municipal', 32),
(184, 'Agua; Conexion domiciliaria; Nueva', 32),
(185, 'Agua; Conexion domiciliaria; Pierde', 32),
(186, 'Agua; Instalacion; Pierde', 32),
(187, 'Agua; Instalacion; Nueva', 32),
(188, 'Agua; Cocina; Pierde', 32),
(189, 'Agua; Cocina; Sin agua', 32),
(190, 'Agua; Cocina; Nueva', 32),
(191, 'Agua; Termotanque; Pierde agua', 32),
(192, 'Agua; Termotanque; Nuevo', 32),
(193, 'Desagües; Sanitarios; Tapadado', 32),
(194, 'Desagües; Sanitarios; Pierde descarga artefacto lavado de manos', 32),
(195, 'Desagües; Sanitarios; Descarga inodoro', 32),
(196, 'Desagües; Cocina; Tapado', 32),
(197, 'Calefaccion; Calefactores; Encender', 32),
(198, 'Calefaccion; Calefactores; Pierde gas', 32),
(199, 'Calefaccion; Calefactores; Reparar', 32),
(200, 'Calefaccion; Calefactores; Limpieza', 32),
(201, 'Calefaccion; Calefactores; Nuevo', 32),
(202, 'Calefaccion; Caldera; Encender', 32),
(203, 'Calefaccion; Caldera; Nueva', 32),
(204, 'Gas; Instalacion; Nueva', 32),
(205, 'Gas; Instalacion; Olor a gas', 32),
(206, 'Gas; Artefacto; Pierde', 32),
(207, 'Gas; Artefacto; Reparar', 32),
(208, 'Gas; Artefacto; Nuevo', 32),
(209, 'Varios', 32),
(210, 'Proyecto especial', 32),
(211, 'Iluminacion; Llave de luz; Rota', 33),
(212, 'Iluminacion; Cambiar tubo', 33),
(213, 'Iluminacion; Artefacto roto', 33),
(214, 'Iluminacion; Solicitud; Sistema nuevo', 33),
(215, 'Iluminacion; Exterior; Artefacto no funciona', 33),
(216, 'Iluminacion; Exterior; Solicitud sistema nuevo', 33),
(217, 'Tablero electrico; Salta termica', 33),
(218, 'Tablero electrico; Salta disyuntor', 33),
(219, 'Tablero electrico; Solicitud nuevo', 33),
(220, 'Instalacion electrica; Pilar de luz reparar', 33),
(221, 'Instalacion electrica; Solicitud nuevo', 33),
(222, 'Instalacion electrica; Toma corriente; No funciona', 33),
(223, 'Instalacion electrica; Trifasica; Reparacion', 33),
(224, 'Instalacion electrica; Sin energia', 33),
(225, 'Varios', 33),
(226, 'Proyecto especial', 33),
(227, 'Amurar estructura exterior', 34),
(228, 'Reparacion de ceramicos', 34),
(229, 'Colocacion de aberturas', 34),
(230, 'Revoque de mamposteria', 34),
(231, 'Ejecucion o reparacion de cercos perimetrales', 34),
(232, 'Colocacion de pisos ceramicos', 34),
(233, 'Colocacion de ceramicos en pared', 34),
(234, 'Ejecucion o reparacion de tabiques de mamposteria', 34),
(235, 'Ejecucion o reparacion de veredas', 34),
(236, 'Varios', 34),
(237, 'Proyecto especial', 34);

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
  `observacion` varchar(7000) COLLATE utf8_spanish2_ci DEFAULT NULL,
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
(246, '2017-05-09', '2017-06-23 15:46:36', 3, 106, 'Solicita: Lorena Arias\n Area Emisora: secretaria de turismo\n Observacion: Por medio de la presente solicito me habiliten en PGM, poder visualizar el libro de rubro egresos, a fin de poder sacar la información que necesito para realizar rendición de eventos. \n \npongo en copia Marcela Giovannini quien es la Directora de la cual dependo.\n \nAtte. \n \nLorena Arias\nA/c dpto. Administració y eventos\nSecretaria de Turismo y Producción', 'Sin', NULL, NULL),
(247, '2017-05-09', '2017-05-09 17:39:07', 3, 110, 'Solicita: Erica Salvo\n Area Emisora: depto de contribuciones\n Observacion: Buenos días:\nMediante la presente solicito se modifique en el nº de cedulón 0940525517 en descripción subtasa donde dice 21682/2016 192N272020000, debe decir 21682/2017 191F 0006 005.\nAtentamente.\nErica Salvo – Departamento Contribuciones', '', NULL, NULL),
(248, '2017-05-11', '2017-05-11 11:27:33', 27, 48, 'Solicita: Andrea\n Area Emisora: direccion inspeccion general\n Observacion: Andrea solicita modificar cuenta de comercio para que pueda cambiar el tipo de publicidad.', '', NULL, NULL),
(249, '2017-05-11', '2017-05-11 11:29:25', 27, 127, 'Solicita: Andrea\n Area Emisora: direccion inspeccion general\n Observacion: Andrea solicita modificar cuenta de comercio para cambiar el tipo de publicidad', '', NULL, NULL),
(251, '2017-05-11', '2017-05-11 17:33:36', 21, 104, 'Solicita: Dpto de Sueldos - Fabiana Velazquez\n Area Emisora: dpto de sueldos\n Observacion: Por medio de la presente y según lo conversado con el Director del área, Cdor.Cristian PAREDES, solicito habilitar la autorización para acceder a la ventana Resumen del sistema de sueldos\na la Dirección de Recursos Humanos.\nAsí mismo y de ser factible, habilitar igual autorización a los Jefes del Depto de Personal:\n \nCOLIN, Ana Laura Legajo 11195\nDE MARIO, María Magdalena Legajo 11041\nMARTINEZ, Blanca Eva Legajo 170\nHUENCHULLAN, Jorge 947\n \n \nAtte.\n \nFabiana VELAZQUEZ\nA/C Departamento de Sueldos\nMSCB', '', NULL, NULL),
(252, '2017-05-11', '2017-05-11 17:34:22', 21, 132, 'Solicita: Cristian Paredes\n Area Emisora: direccion de contaduria -\n Observacion: Estefania\n \nHabilitale tambien a mgonzalez, Mariana tambien me ayuda con algunos temas de sueldos y utiliza la info para proyectar algunos gastos del presupuesto.\n \ncristian\n', '', NULL, NULL),
(253, '2017-05-11', '2017-05-11 17:38:59', 21, 136, 'Solicita: Estefania Klein\n Area Emisora: direccion de sistemas\n Observacion: Envié mail a Soporte de PGM:\nHola Matías, envío el probema comentado por teléfono:\n\nCuando queremos cortar un recibo de sueldo de una liquidación abierta, en\nel Padrón de Sueldos --> Historial de Cargos, sólo para algunos legajos,\nnos muestra el siguiente error:\nError del servidor\n500 - Error interno del servidor.\nHay un problema con el recurso que busca y no se puede mostrar.\n\nLo estamos haciendo desde baja de Liquidación por el momento como me\nindicaste.', '', NULL, NULL),
(254, '2017-05-11', '2017-05-11 17:38:07', 21, 136, 'Solicita: Estefania Klein\n Area Emisora: direccion de sistemas\n Observacion: Envié mail a Soporte de PGM:\n\n\nHola Matías, de acuerdo a lo conversado telefónicamente, te envío el\nscreenshot del error que hubo mientras se estaba liquidando Planta\nPermanente.\nCuando estaba al 60% mostró lo que adjunto.', '', NULL, '254.jpg'),
(255, '2017-05-12', '2017-06-01 12:01:44', 21, 136, 'Solicita: Vanesa Noves\n Area Emisora: dpto de sueldos\n Observacion: Hola Hernán, ¿cómo estás?, estuve viendo con Vanesa unos legajos con el\ntema de los hijos Mayores a 18 años. Tengo varios casos.\n\nCASO 1: Los legajos para que mires son: 1531 y 8436 (NOV. 1810)\n\néstos tienen hijos que cumplen 18 años en Mayo, y en la liquidación de\nMayo no los está incluyendo, deberían cobrar por hijos con MAYO inclusive.\n\nCASO 2: Legajo 20604 y 287 tiene a la NIETA/NIETO a cargo y no le está\nincluyendo la novedad 1810.\n\nCASO 3: Legajo 300\n\nTiene un NIETO con Discapacidad y no le incluye la novedad 1920.\n\nPodrás revisar la fórmula CantidadHijosEscolaresMenores18() ?\n\nporque antes era Unidad = Empleado.Hijos y se ve que los NIETOS los\ncargaban como hijos.\n\nMuchas gracias.\n\nSaludos!\nEstefanía Klein\n Mensaje de eklein: Estefanía:\n\nEstá modificada la función para contemplar todos los casos. Por favor reliquiden y\nverifiquen nuevamente cada caso.\n\nSaludos.-\nFernando Gonzalez', '', NULL, NULL),
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
(326, '2017-05-31', '2017-05-31 17:40:27', 21, 121, 'Solicita: Gloria Vera\n Area Emisora: Departamento de Sueldos\n Observacion: Hola, cuando corren desde sueldos el proceso de inicio de novedades, llega\nhasta cierto porcentaje, luego aparece el cartel que dice que en proceso\nha finalizado y cuando se le da aceptar, completa la barra de progreso al\n100%.\n\nQuisiera saber si este proceso se termina de ejecutar correctamente aunque\nno llegue la barra al 100%.\n\nAdjunto screenshots.\n\nSaludos\nEstefanía', '', NULL, '326.jpg'),
(327, '2017-06-01', '2017-06-01 12:19:41', 18, 110, 'Solicita: Ruth\n Area Emisora: Tesoreria\n Observacion: Recaudacion en mas partida 51108', '', NULL, NULL),
(328, '2017-06-01', '2017-06-01 16:46:14', 3, 104, 'Solicita: Ruth Saavedra\n Area Emisora: Direccion de Tesoreria\n Observacion: Sr Dtor de Sistemas por intermedio de la pte autorizo clave PGM para Ag. Carlos Llancalahuen , para realizar cierre de planillas, bajar pago y  clave para cajero en linea .-\n\n atte. ruth saavedra Tesorera.-', '', NULL, NULL),
(329, '2017-06-02', '2017-06-02 14:05:41', 18, 110, 'Solicita: Erica\n Area Emisora: Departamento de Contribuciones\n Observacion: La cuenta 10573 de tasa por serv.municipal pagó las cuotas del 2017 por\ncajero "ario" el 30/5/2017 con cheque al día. Cuando van a guardar el\nlibre deuda sale un aviso que dice que "posee pagos con cheques que aun no\nfueron acreditados".\nEsto no es así para esta cuenta. Por donde miramos esto? Y si fuera el\ncaso, donde se registra la acreditación?\n Mensaje de avarano: consultado a Pgm', '', NULL, NULL),
(330, '2017-06-02', '2017-06-02 15:37:34', 18, 110, 'Solicita: Erica\n Area Emisora: Departamento de Contribuciones\n Observacion: Actualizar datos para notificaciones de talonarios', '', NULL, NULL),
(331, '2017-06-02', '2017-06-02 17:47:05', 21, 82, 'Solicita: Claudia Inalaf\n Area Emisora: Departamento de Sueldos\n Observacion: Buen día. Solicito la creación de la categoría: 27 PUNTOS ( Equivalente a $11.613.60)\nY respecto a categoría: 28 puntos, corresponde el valor $12.043.73; el otro poner No Usar.\n \nGracias.\nClaudia', '', NULL, NULL),
(332, '2017-06-05', '2017-06-05 12:08:19', 21, 140, 'Solicita: Fabiana Velazquez\n Area Emisora: Departamento de Sueldos\n Observacion: En el reporte Sueld-Reporte Horizonte con Novedades no aparece la novedad 4230 en el combo.', '', NULL, NULL),
(333, '2017-06-05', '2017-06-05 12:48:58', 18, 109, 'Solicita: Roxana\n Area Emisora: fiscalizacion\n Observacion: padron con cuotas adeudadas judicial', '', NULL, NULL),
(334, '2017-06-05', '2017-06-05 12:49:30', 18, 110, 'Solicita: roxsana\n Area Emisora: fiscalizacion\n Observacion: generacion cuotas\n', '', NULL, NULL),
(335, '2017-06-05', '2017-06-05 12:50:30', 18, 137, 'Solicita: Erica - Martin\n Area Emisora: Departamento de Contribuciones- tesoreria\n Observacion: Mandar debitos altas del 23 al 31 que no se mandaron', '', NULL, NULL),
(336, '2017-06-05', '2017-06-05 14:40:48', 18, 137, 'Solicita: yo\n Observacion: ver porque en csv tiene registros sin el dato de boleta, ver el archivo que genera txt', '', NULL, NULL),
(337, '2017-06-05', '2017-06-05 14:41:22', 18, 137, 'Solicita: yo\n Observacion: Modificar manual pagos electronicos', '', NULL, NULL),
(338, '2017-06-05', '2017-06-05 15:52:13', 18, 137, 'Solicita: lilian\n Observacion: resultado del debito', '', NULL, NULL),
(339, '2017-06-05', '2017-06-05 16:47:17', 21, 107, 'Solicita: Gloria Vera\n Area Emisora: Departamento de Sueldos\n Observacion: BUEN DIA: SOLICITO NOMBRE DE CALLE: CAMACUA, BARRIO LAS VICTORIAS\n \nSALUDOS\n\nGLORIA VERA\nDEPARTAMENTO DE SUELDOS', '', NULL, NULL),
(340, '2017-06-05', '2017-06-05 16:48:00', 21, 80, 'Solicita: Gloria Vera\n Area Emisora: Departamento de Sueldos\n Observacion: ESTEFANIA: NECESITO QUE ME AGREGUES NUEVO ITEM EN ÁREA ADMINISTRATIVA DE HISTORIAL DE CARGOS QUE DIGA: PAS-CONCEJO MUNICIPAL.-\nGRACIAS\nSALUDOS\nGLOARIA VERA\nDEPARTAMENTO DE SUELDOS', '', NULL, NULL),
(341, '2017-06-06', '2017-06-06 12:01:58', 18, 110, 'Solicita: marta\n Area Emisora: fiscalizacion\n Observacion: no liquida cuotas 11  y12 del 2016 , padron 96402.', '', NULL, NULL),
(342, '2017-06-06', '2017-06-06 13:31:32', 18, 110, 'Solicita: roxana\n Area Emisora: fiscalizacion\n Observacion: pasar a situacion normal para prescribir\nPARTIDA 26113 PERIODOS 06/87 A 06/92 Y 02/94 A 06/94\nPARTIDA 26083 PERIODOS 06/87 A 06/92 Y 02/94 A 06/94\nPARTIDA 36985 PERIODOS 01/97 A 01/2003\n', '', NULL, NULL),
(343, '2017-06-06', '2017-06-06 16:00:28', 18, 110, 'Solicita: Julieta\n Area Emisora: Direccion de Contaduria\n Observacion: importe distinto en compromiso 2527 y op 3831', '', NULL, NULL),
(344, '2017-06-07', '2017-06-07 15:52:53', 21, 110, 'Solicita: Claudia Inalaf\n Area Emisora: Departamento de Sueldos\n Observacion: Hola, necesitamos que en los selectores de fecha, de la página de\n"Historial de Cargos", dentro del padrón de sueldos, nos figure desde año\n1989, ya que tienen un caso que necesitan poner la antigüedad desde ese\naño, y sólo muestra desde 1993.\n\nDesde ya muchas gracias.\nEstefanía\n', '', NULL, NULL),
(345, '2017-06-08', '2017-06-08 15:26:22', 21, 79, 'Solicita: Mauricio Caupan\n Area Emisora: Departamento de Sueldos\n Observacion: Estefanía:\n Buenos días, necesitamos que se cree una nueva función en el sistema:\n- Es una función Técnica y el concepto es: Seguridad e Higiene.\nGracias.\nSaludos Mauricio', '', NULL, NULL),
(346, '2017-06-09', '2017-06-09 11:21:01', 18, 110, 'Solicita: Erica\n Area Emisora: Departamento de Contribuciones\n Observacion: No da la opcion de pdf al notificar', '', NULL, NULL),
(347, '2017-06-09', '2017-06-09 11:22:30', 18, 110, 'Solicita: Mariana\n Area Emisora: Direccion de Tesoreria\n Observacion: Hay un problema con las cuotas generadas cómo "Débito incidencia en\nacreditación" correspondientes a los talonarios de Tasa Personal.\nEsta tasa tienen un montón de items distintos, que se imputan a cuentas\ndistintas, pero las mencionadas cuotas tienen un único item. Esto no\npermite cargar las distintas imputaciones correspondientes.', '', NULL, NULL),
(348, '2017-06-09', '2017-06-09 11:23:20', 18, 137, 'Solicita: Lilian\n Area Emisora: Politicas tributarias\n Observacion: Listado de rechazados debitos', '', NULL, NULL),
(349, '2017-06-09', '2017-06-09 11:23:53', 18, 137, 'Solicita: Lilian\n Area Emisora: Politicas tributarias\n Observacion: Debitos talonarios no enviados', '', NULL, NULL),
(350, '2017-06-09', '2017-06-09 11:25:10', 18, 137, 'Solicita: alicia\n Area Emisora: sistemas\n Observacion: Tenemos problemas con el envío de datos de TALONARIOS a entidades\nexternas, ya que no se puede realizar ningún pago.\n\nLINK:\nEn el archivo de exportación de datos no aparece el código de pago link.\n\nAl intentar pagarla , si el talonario es de tasa por servicio municipal\nsólo aparece el importe de la cuota de tasa por servicio municipal.\n\nSi el talonario es de otra tasa u obra, aparece el siguiente mensaje. " Se\nha ingresado un código de pago inválido"\n\nBANELCO:\nEl archivo si tiene el código, pero no está disponible para pagarlo en\nPago Mis cuentas.\n\nSi el talonario es de tasa por servicio municipal sólo aparece el importe\nde la cuota de tasa por servicio municipal .\n\nSi el talonario es de otra tasa u obra, aparece el siguiente mensaje\n"No encontramos la factura indicada. Por favor verificá que: la factura\nesté dentro de los vencimientos a pagar permitidos por la empresa o que no\nhaya sido abonada con anterioridad (Consultá la sección Comprobantes). "', '', NULL, NULL),
(351, '2017-06-09', '2017-06-09 17:30:52', 27, 125, 'Solicita: Julieta\n Area Emisora: Direccion de Contaduria\n Observacion: Se solicito a cordoba,revision de datos sobre un proveedor para la correcta carga de facturas en una orden de pago asociado a éste.', '', NULL, NULL),
(352, '2017-06-12', '2017-06-12 13:58:46', 18, 137, 'Solicita: erica\n Observacion: mandar talonarios que faltan de junio', '', NULL, NULL),
(353, '2017-06-12', '2017-06-12 14:02:28', 18, 132, 'Solicita: nancy\n Area Emisora: legales\n Observacion: No puede ver ficha obras y servicios ni procesar deuda de ficha 5136', '', NULL, NULL),
(354, '2017-06-12', '2017-06-12 14:10:06', 18, 109, 'Solicita: Erica\n Observacion: En reporte talonarios sociales ,filtrar por talonarios hechos hasta 30/09/2010\n', '', NULL, NULL),
(355, '2017-06-12', '2017-06-12 15:45:48', 18, 128, 'Solicita: Erica\n Observacion: padron tasa 30195 cambio de encargado era gonzalez sergio raul y ahora es sergio reynaldo.', '', NULL, NULL),
(356, '2017-06-12', '2017-06-12 15:52:14', 18, 110, 'Solicita: Sergio\n Observacion: preguntar si los pagos son despues del vencimiento o boloetas originales que las genere fisca la impresion\n\n\nTenemos varios contribuyentes que tienen 50 o más cuentas de tasa por serv. a la propiedad y pagan por transferencia. Para recaudarlo, en tesorería imprimen las boletas mediante el procesa deuda. Generan todas las boletas pero al imprimir con la lupa solo hace el pdf para 11 boletas y las demás dan error. Entonces para imprimirlas hay que ir tildando y destildando del listado de boletas de deuda para poder imprimir todo.\nEse es un nro. determinado por el sistema, puede modificarse?', '', NULL, NULL),
(357, '2017-06-13', '2017-06-13 14:05:34', 27, 131, 'Solicita: Raquelina\n Area Emisora: Tribunal de faltas II \n Observacion: Cambiar encabezados de plantillas de documentos para Tribunal de faltas 2', '', NULL, NULL),
(358, '2017-06-13', '2017-06-13 14:33:07', 31, 171, 'Solicita: Magolla\n Area Emisora: Direccion de Sistemas\n Observacion: Prueba mant 1', '', NULL, NULL),
(359, '2017-06-14', '2017-06-14 12:14:32', 18, 110, 'Solicita: sergio\n Area Emisora: Direccion de Tesoreria\n Observacion: Ordenar carga de pagos por transferencias\nestan cargando con cajero comun con fecha del dia y no del pago\nimptresion de boletas no vencidas fisca por titular\n', '', NULL, NULL),
(360, '2017-06-14', '2017-06-14 15:47:50', 18, 110, 'Solicita: Lilian\n Observacion: como se cobra muelles\npedir pgm que se muestre fecha varios hasta', '', NULL, NULL),
(361, '2017-06-14', '2017-06-14 15:50:30', 18, 137, 'Solicita: yo\n Observacion: revisar faltan generar cuotas de debito por incidencias', '', NULL, NULL),
(362, '2017-06-19', '2017-06-19 12:40:31', 27, 54, 'Solicita: Lucas Perez\n Area Emisora: Dpto Terminal de Omnibus\n Observacion: Pedidos de movimientos de conductores:\nSolicitan las entradas y salidas de los siguientes conductores de distintas empresas;\nRuiz Diaz Jeremias 28696692, Robledo Alfredo 23681936 y Arrighi Mario 20312488.', '', NULL, NULL),
(363, '2017-06-22', '2017-06-22 14:31:44', 31, 159, 'Solicita: 1706211601 \n Area Emisora: Delegación Catedral\n Observacion: Empleo solicita adecuar espacio esqui social\n Mensaje de farroyo: 20 litros de pintura exterior blanca\n20 litros pintura inrerior blanca\n2 pinceles Nº 30\n1 rodillo\n\n Mensaje de farroyo: \n$\n3000', '', NULL, NULL),
(364, '2017-06-22', '2017-06-22 17:47:13', 31, 221, 'Solicita: 1706211319\n Area Emisora: Turismo Centro Cívico\n Observacion: solicitan instalación de:\n2 dos protectores para enchufes pàra niños\n2 Tomas de aplicar externos\n1 tira de cable canal\n\n Mensaje de farroyo: 2 ncncambre\n\n Mensaje de farroyo: $3000\n Mensaje de farroyo: 143-DMI-17\nPA2356', '', NULL, NULL),
(365, '2017-06-22', '2017-06-22 18:28:25', 31, 221, 'Solicita: 1706211319\n Area Emisora: Turismo Centro Cívico\n Observacion: Dirección de Mantenimiento\nÁrea de Electricidad\n \nDe acuerdo a lo conversado con Alberto (de la División Electricidad) en su última visita al Departamento de Informes y Atención al Turista, para la mejora de la instalación eléctrica en cocina del Personal de Informes Turísticos, solicitamos nos confirmen la descripción correcta de los materiales necesarios para el arreglo, a fin de solicitar la compra de los materiales a Suministros:\n-(2) dos protectores para enchufes (tapas de protección para niños para tomas de salón)\n-(2) dos tomas de aplicar externos (uno para cocina otro para salón para enchufar luz de emergencia)\n-(1) una tira de cable canal (medidas?)\n \nDesde ya muchas gracias.\nAtentamente.\n\n', '', NULL, NULL),
(366, '2017-06-22', '2017-06-22 18:46:37', 31, 177, 'Solicita: 1706211319\n Area Emisora: Turismo Centro Cívico\n Observacion: Departamento de Mayordomía\nPor la presente informamos que continúa roto el sistema de descarga de agua del depósito de uno de los inodoros en el baño público de mujeres, Centro Cívico. (una varilla del flotante está quebrada). Por lo cual el mismo se encuentra clausurado.\n\nAtentamente.\n \nLic. Verónica Martín a/c\nDepto. de Informes y Atención al Turista\nSecretaría de Turismo y Producción\n', '', NULL, NULL),
(367, '2017-06-22', '2017-06-22 18:49:41', 31, 146, 'Solicita: 1706212319\n Area Emisora: Turismo Centro Cívico\n Observacion: Departamento de Mayordomía\n Informamos que algunas de las ventanas de los baños públicos en centro cívico les falta la chapita que traba la falleba, (dificultando que queden correctamente cerradas), y el pasador de la puerta del baño accesible está roto.\nAtentamente.\n \nLic. Verónica Martín a/c\nDepto. de Informes y Atención al Turista\nSecretaría de Turismo y Producción\n', '', NULL, NULL),
(368, '2017-06-22', '2017-06-22 18:54:29', 31, 157, 'Solicita: 1706221200\n Area Emisora: Terminal de Omnibus\n Observacion: Por la presente se solicita el relevamiento para tomar medidas para la fabricacion de muebles, para guardar los insumos de limpieza en terminal de omnibus sector donde se encuentran los agentes de limpieza y en transito y transporte hablar con flavia para que se lo oriente o llamar a javier celular:154308226. en estos muebles se guardarian lo insumos en general.\n \n \nsin mas me despido atentamente\n Departamento de Mayordomia - division limpeza \n', '', NULL, NULL),
(369, '2017-06-22', '2017-06-22 18:57:14', 31, 221, 'Solicita: 1706211319\n Area Emisora: Turismo Centro Cívico\n Observacion: Dirección de Mantenimiento\nÁrea de Electricidad\n \nDe acuerdo a lo conversado con Alberto (de la División Electricidad) en su última visita al Departamento de Informes y Atención al Turista, para la mejora de la instalación eléctrica en cocina del Personal de Informes Turísticos, solicitamos nos confirmen la descripción correcta de los materiales necesarios para el arreglo, a fin de solicitar la compra de los materiales a Suministros:\n-(2) dos protectores para enchufes (tapas de protección para niños para tomas de salón)\n-(2) dos tomas de aplicar externos (uno para cocina otro para salón para enchufar luz de emergencia)\n-(1) una tira de cable canal (medidas?)\n \nDesde ya muchas gracias.\nAtentamente.\n\n', '', NULL, NULL),
(370, '2017-06-22', '2017-06-22 19:05:54', 31, 222, 'Solicita: 1706221109\n Area Emisora: Concejo Municipal\n Observacion: Buenos días Fernando, por favor necesito arreglar algunas cuestiones de \nelectricidad en la oficina de un concejal, dado que están teniendo \nvarios inconvenientes y se les apaga la computadora continuamente, \nporque se quedan sin luz.\n\n\n\nGracias\n\n-- \nClaudia Haneck\nSecretaria Legislativa\nConcejo Municipal Bariloche\n', '', NULL, NULL),
(371, '2017-06-22', '2017-06-22 19:15:54', 31, 144, 'Solicita: 1706221109\n Area Emisora: Concejo Municipal\n Observacion: Buenos días Fernando, por favor tenemos que reparar con urgencia algunas goteras que \ntiene el pasillo de ingreso a la sala de sesiones.\n\nGracias\n\n-- \nClaudia Haneck\nSecretaria Legislativa\nConcejo Municipal Bariloche\n', '', NULL, NULL),
(372, '2017-06-22', '2017-06-22 19:20:36', 31, 211, 'Solicita: 1706221121\n Area Emisora: CDI Iglesias\n Observacion: A: Dirección de mantenimiento\n \n   Por medio de la presente solicitamos que se pueda acercar a la institución , lo antes posible,  un electricista, ya que la tecla de luz de sala deambuladores esta realizando un corto circuito, dejando sin luz a dos salas.\n \n \nSaluda atte.\nPatricia  Parra.', '', NULL, NULL),
(373, '2017-06-22', '2017-06-22 19:26:21', 31, 225, 'Solicita: 1706211403\n Area Emisora: Terminal de Omnibus\n Observacion: A:Dirección de Mantenimiento\n\nCC:Jefe de Terminal de Ómnibus MSCB\n \nDirección de Mantenimisnto\nPor la presente solicitamos la revisión del puesto de Informes deTerminal de Ómnibus, ubicado en sala de espera sector central, a fin de realizar mejoras y repaciones varias:\n \nElectricidad\nPor favor verificar la instalación eléctrica de dicho puesto (estado y seguridad), tener en cuenta que necesitamos un toma para enchufar pc, celular y otro para eventualmente en invierno conectar un caloventor o pava electrica. \nQuiero comentarles al respecto de los tomas a la vista del público, tenemos el inconveniente que cuando el personal de Informes no está en el puesto, los pasajeros que esperan se meten en el puesto a enchufar sus celulares. \nTener en cuenta además la necesidad de mejora de la iluminación sobre el puesto ya sea agregando o cambiando tubo fluorescente de luz sobre dicho puesto (tubo mediano fino). \nVer además, estado actual de cable telefónico y ficha de conexión a internet en puesto (factibilidad de conexión a internet si hubiera).\n \nDesde ya muchas gracias, aguardo sus comentarios. \nAtentamente.\n  \nLic. Verónica Martin a/c\nDepartamento de Informes y Atención al Turista\nSecretaría de Turismo y Producción\nMunicipalidad de San carlos de Bariloche\ninformesturmscb@bariloche.gov.ar\nTE (0294) 4625508\n \n \n', '', NULL, NULL),
(374, '2017-06-22', '2017-06-22 19:29:30', 31, 145, 'Solicita: 1706211403\n Area Emisora: Terminal de Omnibus\n Observacion: A:Dirección de Mantenimiento\n\nCC:Jefe de Terminal de Ómnibus MSCB\n \nDirección de Mantenimisnto\nPor la presente solicitamos la revisión del puesto de Informes deTerminal de Ómnibus, ubicado en sala de espera sector central, a fin de realizar mejoras y repaciones varias:\n \n\nCarpintería\nAsimismo solicitamos la reparación y mejora del mueble mostrador de dicho puesto, el mismo tiene un estante caído, falta de barniz y pintura blanca en mal estado. Por favor verificar el estado de dicho mueble e informar los materiales necesarios requeridos a fin de gestionar su pronta reparación.\n \n\nDesde ya muchas gracias, aguardo sus comentarios. \nAtentamente.\n  \nLic. Verónica Martin a/c\nDepartamento de Informes y Atención al Turista\nSecretaría de Turismo y Producción\nMunicipalidad de San carlos de Bariloche\ninformesturmscb@bariloche.gov.ar\nTE (0294) 4625508\n \n \n', '', NULL, NULL),
(375, '2017-06-26', '2017-06-26 11:24:58', 31, 197, 'Solicita: Sociales; Ivana\n Area Emisora: sociales', '', NULL, NULL),
(376, '2017-06-26', '2017-06-26 11:26:21', 31, 224, 'Solicita: Tránsito; Archivo PB\n Area Emisora: Tránsito', '', NULL, NULL),
(377, '2017-06-26', '2017-06-26 11:29:14', 31, 202, 'Solicita: CDI Malvinas\n Observacion: Encender caldera', '', NULL, NULL),
(378, '2017-06-26', '2017-06-26 13:50:53', 31, 202, 'Solicita: Moreno y Ruiz Moreno\n Area Emisora: Departamento de Sueldos, Fabiana\n Observacion: Encender Caldera sector Sueldos', '', NULL, NULL),
(379, '2017-06-27', '2017-06-27 12:11:54', 31, 204, 'Solicita: CDI Malvinas-Pomeba\n Area Emisora: Promeba\n Observacion: Me dirijo a esta Dirección por indicación del Ing. Milano, para solicitar la conexión de los servicios de Energía Eléctrica y Gas en el portal (contenedor) ubicado en las intersecciones de las calles Malvina Soledad e Islas del Sur, B° Nahuel Hue, en cercanías del CAAT N°8. \n\n\nEste Portal tiene como destino ser las oficinas del equipo de campo de PROMEBA.\n\n\nLa urgencia del pedido radica en que hasta que no tenga luz y gas no se puede utilizar.\n\n\nAdjunto imágenes de las instalaciones internas.\n\n\nQuedo a la espera de su respuesta.\n\n\n\n\nCordialmente\n', '', NULL, '379.jpg'),
(380, '2017-06-27', '2017-06-27 13:06:06', 31, 157, 'Solicita: Catastro\n Area Emisora: Catastro\n Observacion: Solicitan 12 elevadores de madera para monitores nuevos', '', NULL, NULL),
(381, '2017-06-27', '2017-06-27 13:07:24', 31, 224, 'Solicita: Tránsito\n Area Emisora: Tránsito\n Observacion: Flavia de Transito solcita ver corte de energía en un sector', '', NULL, NULL),
(382, '2017-06-27', '2017-06-27 13:09:05', 31, 222, 'Solicita: Palacio Minicipal\n Area Emisora: Despacho de Gobierno\n Observacion: Zapatilla no funciona en despacho de Gobierno', '', NULL, NULL),
(383, '2017-06-27', '2017-06-27 13:11:35', 31, 212, 'Solicita: Mecánica\n Area Emisora: Macánica\n Observacion: Herreria de Mecánica cambiar farola', '', NULL, NULL),
(384, '2017-06-27', '2017-06-27 13:13:06', 31, 150, 'Solicita: Obras por Contrato\n Area Emisora: Vanesa\n Observacion: Colocar Pizarra en ofician de Obrad por Contrato, ', '', NULL, NULL),
(385, '2017-06-27', '2017-06-27 13:17:45', 31, 141, 'Solicita: Palacio Municipal\n Area Emisora: Despaco de Gobierno\n Observacion: Solicitan poner cerradura de tammbor a un mueble . Fabiana', '', NULL, NULL),
(386, '2017-06-27', '2017-06-27 13:29:47', 31, 189, 'Solicita: Verterdero Municipal\n Area Emisora: Bascula Oficina\n Observacion: Sector Oficina de bascula sin agua', '', NULL, NULL),
(387, '2017-06-27', '2017-06-27 13:53:29', 31, 204, 'Solicita: Cenctro Cultural 270 Viv.\n Area Emisora: Cultura\n Observacion: Solcitan ver para instalar tubos robados del centro los elementos faltantes\n', '', NULL, NULL),
(388, '2017-06-27', '2017-06-27 14:45:13', 31, 214, 'Solicita: Esqui Social\n Area Emisora: Delegación Catedral\n Observacion: Solcitan mejorar iluminación sector de esqui social en cerro caterdral', '', NULL, NULL),
(389, '2017-06-27', '2017-06-27 14:47:29', 31, 177, 'Solicita: esqui Social\n Area Emisora: Delgación Catedral-Sociales\n Observacion: Refugio del programa Esquí Social. Solicitan reparación de cocina y conexión de agua en la bacha. 2944694562', '', NULL, NULL),
(390, '2017-06-27', '2017-06-27 14:48:43', 31, 175, 'Solicita: Esqui Social-Catedral\n Area Emisora: Delegación catedral Sociales\n Observacion: Solictan reparaciones verias. Prado', '', NULL, NULL),
(391, '2017-06-27', '2017-06-27 14:50:00', 31, 141, 'Solicita: Esquí Social-Catedral\n Area Emisora: Delegación Catdral Sociales\n Observacion: Solcitan pintura del sector Esqui´social en el cerro CatedraL\n', '', NULL, NULL),
(392, '2017-06-27', '2017-06-27 15:02:08', 31, 159, 'Solicita: Esqui Social -Catedral\n Area Emisora: Delegación Catedral-Sociales\n Observacion: Solcitan Pintar el sector de esquis social en el cerro catedral', '', NULL, NULL),
(393, '2017-06-28', '2017-06-28 11:22:07', 31, 157, 'Solicita: 1705191406-pudu pudu\n Area Emisora: Pudu Pudu\n Observacion: Norma solcita adeduar un mueble, cortarlo.', '', NULL, NULL),
(394, '2017-06-28', '2017-06-28 11:36:18', 31, 236, 'Solicita: Club de Día 2 de Abril-Nota:507-SDSDyC/DAC\n Area Emisora: Sociales\n Observacion: Se solicita adecuacion de ingreso. 154502239 o 154393857.', '', NULL, NULL),
(395, '2017-06-28', '2017-06-28 11:45:05', 31, 178, 'Solicita: Cintia Tabella Subsecretaria de PLANEAMIENTO\n Area Emisora: Subsecretaria de Planeamiento\n Observacion: Solcitan reparar perdidad de ola mochila del bañito del sector', '', NULL, NULL),
(398, '2017-06-28', '2017-06-28 20:01:47', 31, 188, 'Solicita: TURISMO VILLEGAS 1706221204\n Area Emisora: MAYORDOMIA\n Observacion: SOLICITAN REPARACIÓN DE CUERITO DE CANILLA DE BACHA Y MANIJA DE DESCARGA EN BAÑO DE VARONES', '', NULL, NULL),
(399, '2017-06-28', '2017-06-28 20:05:54', 31, 178, 'Solicita: CAAT 1\n Area Emisora: SOCIALES NOTA: 522-SDSDy DAC-17\n Observacion: Solicita reparación de inodoro', '', NULL, NULL),
(400, '2017-06-28', '2017-06-28 20:11:54', 31, 208, 'Solicita: Centro Comunitario Eva Perón\n Area Emisora: sociales Nota: 523-SDSDyC DAC-17\n Observacion: colocar una cocina. Tel 2944505377. Llamara antes de ir\n', '', NULL, NULL),
(401, '2017-06-28', '2017-06-28 20:18:52', 31, 152, 'Solicita: Centro Comunitario Barrio Unón\n Area Emisora: socuiales. Nota: 537-SDSDyC DAC-17\n Observacion: Solicita construcción de estantes y colocación. Tel 2944639700. Llamar antes de ir', '', NULL, NULL),
(402, '2017-07-03', '2017-07-04 16:33:36', 31, 178, 'Solicita: Marcelo\n Area Emisora: cancejo municipal\n Observacion: Pierde baño Nº 11 primer piso del concejo\n Mensaje de farroyo: Buen día Fernando, te comento estamos con algún problema en uno de los \nbaños del concejo xq se está inundando. Necesitamos si hoy sin falta si \nlo pueden venir a ver.\n\nGracias\n\n-- \nClaudia Haneck\nSecretaria Legislativa\nConcejo Municipal Bariloche\n', '', NULL, NULL),
(403, '2017-07-03', '2017-07-03 12:24:08', 31, 218, 'Solicita: Delegción Lago Moreno-Valle\n Area Emisora: Delegción Lago Moreno\n Observacion: Tiene un corto en el Hipodromo, Salta disjuntor todo el tiempo. Pasr a buscar la llave en la Delegación Lago Moreno', '', NULL, NULL),
(404, '2017-07-03', '2017-07-03 13:16:24', 31, 227, 'Solicita: CDI Iglesias-Rojel\n Area Emisora: CDI Iglesias\n Observacion: Solicitan ejecución de una rampa de acceso por calle Mascardi para mercancias y Amurar portones solictado sobre esa \ncalle y sobre calle Parraviccini.\n', '', NULL, NULL),
(405, '2017-07-03', '2017-07-03 13:17:49', 31, 169, 'Solicita: CDI Iglesias-rojel\n Area Emisora: CDI Iglesias\n Observacion: Solicitan ejecucion de Portones nuevos sobre calle Mascardi y sobre Calle Parraviccini.', '', NULL, NULL),
(406, '2017-07-03', '2017-07-03 13:27:51', 31, 177, 'Solicita: 543-SDSDyC DAC-Alejandra Sandobal\n Area Emisora: Centro Comunitario Barrio Unión\n Observacion: Solicitan reparar los baños del Centro Comunitario Bario Unión- Dependientes del CAAT 9- 2944639700', '', NULL, NULL),
(407, '2017-07-03', '2017-07-03 13:35:41', 31, 152, 'Solicita: 542-SDSDyC DAC-Alejandra Sandobal\n Area Emisora: Centro Cominutari Integral-CIC 34HAS\n Observacion: Solicitan Arreglos de mesas y escritorios en Centro Cominutari Integral-CIC 34HAS dependiente del CAAT 9 -2944639700', '', NULL, NULL),
(408, '2017-07-03', '2017-07-03 14:24:53', 31, 222, 'Solicita: Palacio Municipal- Gabriela\n Area Emisora: Secretaria de Gobierno\n Observacion: Solicitan ver Zapatilla de Gobierno porque no funciona y no pueden usar computadora ni telefono de la Intendencia.', '', NULL, NULL),
(409, '2017-07-03', '2017-07-03 14:45:33', 31, 221, 'Solicita: PROMEBA EN CDI MALVINAS- Milano 1706261651\n Area Emisora: Secretaría der Obras Publicas\n Observacion: Me dirijo a esta Dirección por indicación del Ing. Milano, para solicitar la conexión de los servicios de Energía Eléctrica y Gas en el portal (contenedor) ubicado en las intersecciones de las calles Malvina Soledad e Islas del Sur, B° Nahuel Hue, en cercanías del CAAT N°8. \n\n\nEste Portal tiene como destino ser las oficinas del equipo de campo de PROMEBA.\n\n\nLa urgencia del pedido radica en que hasta que no tenga luz y gas no se puede utilizar.\n\n\nAdjunto imágenes de las instalaciones internas.\n\n\nQuedo a la espera de su respuesta.\n\n\n\n\nCordialmente\n-- \n\nRicardo D. Martín\n(294) 15 425 0797\n Mensaje de farroyo: Alberto: En principio se va a conectar desde un tablero provisorio que colocará la CEB en el Poste donde despues conectaremos al CDI\nosea que debemos tener o pedir un sintenac desde el Poste al Conetiner con capacidad para dos split de 3000W. Supungo\n Mensaje de farroyo: Me dicen que son los que se pusieron en personal cuando no había Gas', '', NULL, '409.jpg'),
(410, '2017-07-03', '2017-07-03 14:54:45', 31, 208, 'Solicita: Eduardo-1706261418\n Area Emisora: Biblioteca Alfonsín\n Observacion: Biblioteca Alfonsín OT:8 Reclamo\nEstimado Fernando Arroyo \nAqui te escribo pidiendo la instalación, funcionamiento del termotanque que instalaron el pasado año habida cuenta que el personal de limpieza no tiene aún suministro de agua caliente en la cocina.\nAgradezco pronta respuesta y ..¡bienvenidos! \nAtentamente, \nEduardo \n \n\n\n', '', NULL, NULL),
(411, '2017-07-03', '2017-07-03 15:11:49', 31, 178, 'Solicita: Turismo Edificio Centro Cívico Lic. Verónica Martín -1706271149\n Area Emisora: Depto. de Informes y Atención al Turista\n Observacion: Departamento de Mayordomía\nPor la presente informamos que el día de ayer por la tarde apareció rota la palanca del sistema de descarga de agua del otro inodoro del baño público de mujeres en c.cívico. Y el flexible del lavatorio de ese mismo baño pierde agua.\nAsimismo continúa roto el sistema de descarga de agua del depósito del inodoros del baño público de mujeres (una varilla del flotante está quebrada). Por lo cual el mismo se encuentra clausurado.\nAtentamente.\n \nLic. Verónica Martín a/c\nDepto. de Informes y Atención al Turista\nSecretaría de Turismo y Producción\nMunicipalidad de San Carlos de Bariloche\ninformesturmscb@bariloche.gov.ar\nTE (0294) 4428675\n', '', NULL, NULL),
(412, '2017-07-03', '2017-07-03 15:20:33', 31, 150, 'Solicita: Nora Redondo-1706281236\n Area Emisora: Delegación Lago Moreno\n Observacion: Buen día: el motivo de este mail es pedirles o bien recordar que falta terminar la división que realizaron en la oficina.\nLo más importante sería el cajón para el cajero con cerradura y el tambor o cerradura para el mueble.\nPor favor. no nos olviden \n \nnora redondo', '', NULL, NULL),
(413, '2017-07-03', '2017-07-03 15:25:41', 31, 221, 'Solicita: MIRIAM-1706282059\n Area Emisora: CDI Malvinas\n Observacion: SOLICITO LA INSTALACIÒN DE UN ENCHUFE  ARRIBA DE LA MESADA EN LA COCINA.SALUDOS CORDIALES MIRIAM', '', NULL, NULL),
(414, '2017-07-03', '2017-07-04 16:00:59', 31, 138, 'Solicita: CAAT Nº 5-1706291357\n Area Emisora: Sociales\n Observacion: Buenas Tardes: Por medio de la presente solicitamos tenga a bien gestionar personal para llevar a cabo mantenimiento en el baño del CAAT Nº 5, de forma urgente,  ya que el inodoro se encuentra inutilizable. Para coordinacion comunicarse con Juanita cel: 2944-605384\n \n', '', NULL, NULL),
(415, '2017-07-03', '2017-07-03 16:24:43', 31, 222, 'Solicita: 545-SDSDyC DAC-17-Alejandra Sandoval\n Area Emisora: Hotelitos 10 de Diciembre-Casa841-Dpto3\n Observacion: Solcitan cambio de enchufe y Presupuesto y colocación de luz de emergencia\n Mensaje de farroyo: nro contacto:2944502239', '', NULL, NULL),
(416, '2017-07-03', '2017-07-03 16:44:20', 31, 187, 'Solicita: 544-SDSDyC DAC-17-Alejandra Sandoval\n Area Emisora: Club de día 2 de Abril\n Observacion: Solicitan colocación de lavarropas: conexión de agua y desagüe te: Contacto 2944502239\n Mensaje de farroyo: COORDINAR VISITA CON ELECTRICOS', '', NULL, NULL),
(417, '2017-07-03', '2017-07-03 16:45:22', 31, 221, 'Solicita: 544-SDSDyC DAC-17-Alejandra Sandoval\n Area Emisora: Club de día 2 de Abril\n Observacion: Solicitan colocación de Lavarropa: Toma corrienten tel- de contacto: 2944502239. COORDINAR VISITA CON LOS PLOMEROS', '', NULL, NULL),
(418, '2017-07-03', '2017-07-03 17:38:33', 31, 178, 'Solicita: Scum-Prado\n Area Emisora: SCUM\n Observacion: Solcitan reparación de depósito de inodoro', '', NULL, NULL),
(419, '2017-07-04', '2017-07-04 12:47:43', 31, 175, 'Solicita: Cristian Ampuero\n Area Emisora: Seguridad e Higiene\n Observacion: Solicita adecuar un tacho 200 L para pruba de matafuegos', '', NULL, NULL),
(420, '2017-07-04', '2017-07-04 16:08:35', 31, 177, 'Solicita: Juanita cel: 2944-605384 \n Area Emisora: CAAT 5\n Observacion: Buenas Tardes: Por medio de la presente solicitamos tenga a bien gestionar personal para llevar a cabo mantenimiento en el baño del CAAT Nº 5, de forma urgente,  ya que el inodoro se encuentra inutilizable. Para coordinacion comunicarse con Juanita cel: 2944-605384\n \n', '', NULL, NULL),
(421, '2017-07-04', '2017-07-04 16:26:19', 31, 207, 'Solicita: Ampuero-1706301909\n Area Emisora: Moreno y Ruiz Moreno\n Observacion: Solicitan revisar cocina, se cerro la llave de paso.', '', NULL, NULL),
(422, '2017-07-04', '2017-07-04 16:30:02', 31, 213, 'Solicita: Lucía 1707031100\n Area Emisora: CDI Araucaria\n Observacion: Hola!\nNecesito realizar los siguientes pedidos de mantenimiento:\n\nCDI Araucaria:\n-Necesitamos arreglar dos luces (hall frio y exterior) y realizar el\nrelevamiento de materiales para cocolor una luz exterior mas\n\nGracias!\nLucía\n', '', NULL, NULL),
(423, '2017-07-04', '2017-07-04 16:31:57', 31, 200, 'Solicita: Lucia 1707031100\n Area Emisora: CDI Arco Iris\n Observacion: Hola!\nNecesito realizar los siguientes pedidos de mantenimiento:\n\nCDI Arco Iris Magico:\n- Les recuerdo de la revisión del calefactor del pasillo que no funciona\n\nGracias!\nLucía\n', '', NULL, NULL),
(424, '2017-07-04', '2017-07-04 16:50:56', 31, 221, 'Solicita: Montes Taller Vial\n Area Emisora: Dinara\n Observacion: Avisa que llego amoladora de banco. Solicitan instalación', '', NULL, NULL),
(425, '2017-07-04', '2017-07-04 17:20:58', 31, 222, 'Solicita: Servicio Técnico 1707040828\n Area Emisora: Delegación Catedral\n Observacion: Hola buen día\n\n                     Por este medio  se solicita la provisión e \ninstalación de una zapatilla eléctrica para la alimentación del reloj en \nla Delegación Catedral .-\n\nAtentamente\n\nServicio Técnico.\n\n', '', NULL, NULL),
(426, '2017-07-04', '2017-07-04 18:53:55', 31, 142, 'Solicita: Maciel\n Area Emisora: Mecánica\n Observacion: Solicita poder poenr cerraduras al cajón de su escritorio', '', NULL, NULL),
(427, '2017-07-04', '2017-07-04 18:59:10', 31, 209, 'Solicita: Daniela Ortíz\n Area Emisora: Recursos Humanos \n Observacion: Solcita reparar radiador que no calienta', '', NULL, NULL),
(428, '2017-07-04', '2017-07-04 19:04:47', 31, 178, 'Solicita: Irene\n Area Emisora: Defensoria del pueblo\n Observacion: Solictan ver perdida de agua en caniila de la oficina de la defensora', '', NULL, NULL),
(429, '2017-07-04', '2017-07-04 19:06:16', 31, 222, 'Solicita: Irene\n Area Emisora: Defensoria del pueblo\n Observacion: Solciotan cambiar foco y ver toma', '', NULL, NULL),
(430, '2017-07-04', '2017-07-04 19:09:37', 31, 193, 'Solicita: Gladis\n Area Emisora: CAAT 5\n Observacion: Solcitan destapar baño', '', NULL, NULL),
(431, '2017-07-04', '2017-07-04 19:10:38', 31, 178, 'Solicita: Falvia\n Area Emisora: Tránsito\n Observacion: Baño de hombres pierde agua', '', NULL, NULL),
(432, '2017-07-04', '2017-07-04 19:12:36', 31, 194, 'Solicita: Pato\n Area Emisora: CDI Pichi Che Ruca\n Observacion: Vacha pierde agua', '', NULL, NULL),
(433, '2017-07-04', '2017-07-04 19:13:59', 31, 155, 'Solicita: Silvia\n Area Emisora: Recursos Humanos\n Observacion: Solicta repara mesa', '', NULL, NULL),
(434, '2017-07-04', '2017-07-04 19:15:07', 31, 155, 'Solicita: silvia Recursos Humanos\n Area Emisora: Moreno y ruiz Moreno\n Observacion: Solcita reparar mesa', '', NULL, NULL),
(435, '2017-07-04', '2017-07-04 19:17:09', 31, 217, 'Solicita: Miguel\n Area Emisora: Gimnasio 1\n Observacion: sin energía pasillos y baños', '', NULL, NULL),
(436, '2017-07-05', '2017-07-05 11:35:30', 31, 186, 'Solicita: Miguel\n Area Emisora: Gimanasio 1\n Observacion: Perdida de agua en la pared ', '', NULL, NULL);

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
(30, 'jcramos', 'a94652aa97c7211ba8954dd15a3cf838', 1685, 1, 1, NULL),
(31, 'farroyo', '9de2dc4967b054f52ff994b4f42f27a6', 2854, 1, 4, NULL),
(33, 'fmunch', '2472ee727ed8de9a818fc657a6895646', 2856, 1, 2, NULL),
(34, 'oprado', '5bbf75fffcd51264fc20bfa75a78ec0f', 2857, 1, 2, NULL),
(36, 'mllanquitur', '85532ed2457219a5887f80d42190a869', 2855, 1, 2, NULL),
(37, 'lparedes', '51bf195bf5654e4909e3166db4604ee4', 2882, 1, 2, NULL),
(38, 'vpichi', 'bc00e0265043d6641b3743dce450c269', 2863, 1, 2, NULL),
(39, 'vruiz', '9a2a3ba925fdace5fda8c833c1f617c8', 2864, 1, 2, NULL),
(40, 'mcardenas', '1a261d825473769c560d9775120b1cd5', 2865, 1, 2, NULL),
(41, 'fmamani', '204c85a079e469684c55cb9b850c461e', 2866, 1, 2, NULL),
(42, 'azurano', '470c03220f6505d437eec941fd4837a7', 2867, 1, 2, NULL),
(43, 'mvargas', 'd49fab26ac2dfc1970ae462229264f35', 2868, 1, 2, NULL),
(44, 'mmacaya', '50045ede5cef2f0934754b05863163ff', 2869, 1, 2, NULL),
(45, 'minostroza', 'f5649659d2b2d03a03bf34de1cf9fdfb', 2870, 1, 2, NULL),
(46, 'jquesada', '623e7503419f51ffddede484d5755b77', 2871, 1, 2, NULL),
(47, 'dcatriman', '6d64e9dc207ad4efb707bf3ca62c8865', 2872, 1, 2, NULL),
(48, 'drosas', '9119489c05aad80a4f68c4b7cdff4fb0', 2873, 1, 2, NULL),
(49, 'dsilva', '5ecc4aa302d527a68a6538d72190310d', 2874, 1, 2, NULL),
(50, 'hsanchez', '0b4a7db11aaab6d963f000655d26bf3f', 2875, 1, 2, NULL),
(51, 'lsandoval', '64d2ae6e32d2de0bb11ec93ce809868b', 2876, 1, 2, NULL),
(52, 'dcomiquil', '9aa3bf37fd4928181a123ca000ff1672', 2877, 1, 2, NULL),
(53, 'rantimil', '7163bdb182889531317c2f7c7a02da66', 2878, 1, 2, NULL),
(54, 'asambueza', '51156a0dd083721be1f7d0910b108051', 2880, 1, 2, NULL),
(55, 'jmaldonado', '7ee17937af0e463c30a6ab124d125c62', 2881, 1, 2, NULL),
(57, 'lucianop', '51bf195bf5654e4909e3166db4604ee4', 2888, 1, 2, NULL),
(58, 'ohuenuman', 'cc15271dedf719bf55e30eaec9479dce', 2883, 1, 2, NULL),
(59, 'ngelvez', '2fa5cadc66447f2d9173a37332d777bf', 2884, 1, 2, NULL),
(60, 'jinostroza', 'f5649659d2b2d03a03bf34de1cf9fdfb', 2885, 1, 2, NULL),
(61, 'emuñoz', 'f330a70e380995187dffe6c6cd22a2db', 2886, 1, 2, NULL),
(62, 'lancavil', 'bc2dee56f4f0bd1d08bd85adc20795f7', 2887, 1, 2, NULL);

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
  MODIFY `id_area` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9195;
--
-- AUTO_INCREMENT de la tabla `area_sistemas`
--
ALTER TABLE `area_sistemas`
  MODIFY `id_area_sistemas` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `asuntos`
--
ALTER TABLE `asuntos`
  MODIFY `id_asuntoP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT de la tabla `base_conocimiento`
--
ALTER TABLE `base_conocimiento`
  MODIFY `id_resolucion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `id_empleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2889;
--
-- AUTO_INCREMENT de la tabla `estados`
--
ALTER TABLE `estados`
  MODIFY `id_estado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `estados_pgm`
--
ALTER TABLE `estados_pgm`
  MODIFY `id_estado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `historial_tickets`
--
ALTER TABLE `historial_tickets`
  MODIFY `id_historial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=902;
--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `id_permiso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;
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
  MODIFY `id_asuntoS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=238;
--
-- AUTO_INCREMENT de la tabla `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id_ticket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=437;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;
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
