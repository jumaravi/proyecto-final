-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-05-2023 a las 08:54:02
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `palmeritas`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizar_cama` (IN `num_ingreso` INT, IN `num_habitacion` INT(5), IN `nueva_cama` VARCHAR(1))  BEGIN
    UPDATE ingreso
    SET cama = nueva_cama
    WHERE codigo_i = num_ingreso AND numeroHabitacion = num_habitacion;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `agregar_Medico` (IN `codigo_m_entrada` INT UNSIGNED, IN `nombre_entrada` VARCHAR(100), IN `apellidos_entrada` VARCHAR(200), IN `telefono_entrada` INT(10) UNSIGNED, IN `especialidad_entrada` VARCHAR(100))  BEGIN
    INSERT INTO medico (codigo_m, nombre, apellidos, telefono, especialidad)
    VALUES (codigo_m_entrada, nombre_entrada, apellidos_entrada, telefono_entrada, especialidad_entrada);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `agregar_paciente` (IN `codigo_p_entrada` INT UNSIGNED, IN `nombre_entrada` VARCHAR(100), IN `apellidos_entrada` VARCHAR(200), IN `direccion_entrada` VARCHAR(255), IN `poblacion_entrada` VARCHAR(255), IN `provincia_entrada` VARCHAR(255), IN `codigoPostal_entrada` INT(10) UNSIGNED, IN `telefono_entrada` INT(10) UNSIGNED, IN `fechaNacimiento_entrada` DATE)  BEGIN
    INSERT INTO paciente (codigo_p, nombre, apellidos, direccion, poblacion, provincia, codigoPostal, telefono, fechaNacimiento)
    VALUES (codigo_p_entrada, nombre_entrada, apellidos_entrada, direccion_entrada, poblacion_entrada, provincia_entrada, codigoPostal_entrada, telefono_entrada, fechaNacimiento_entrada);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ingresos_medico_atendido_por_anio` (IN `codigo_m_entrada` INT)  BEGIN
DECLARE nombre_medico VARCHAR(100);
DECLARE anio INT;
DECLARE num_ingresos INT;

SELECT nombre INTO nombre_medico 
FROM medico 
WHERE codigo_m = codigo_m_entrada;

SELECT YEAR(fechaIngreso), COUNT(*) INTO anio, num_ingresos
FROM ingreso
WHERE codigo_m = codigo_m_entrada
GROUP BY YEAR(fechaIngreso);

SELECT nombre_medico, anio, num_ingresos;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `listar_ingresos_por_paciente` (IN `codigo` INT)  BEGIN
    SELECT p.codigo_p, p.nombre, p.apellidos, i.fechaIngreso 
    FROM paciente p 
    INNER JOIN ingreso i ON p.codigo_p = i.codigo_p 
    WHERE p.codigo_p = codigo;
END$$

--
-- Funciones
--
CREATE DEFINER=`root`@`localhost` FUNCTION `ingresos_por_paciente` (`codigo` INT) RETURNS VARCHAR(255) CHARSET utf8mb4 BEGIN
DECLARE num_ingresos INT;
DECLARE nombre_apellidos VARCHAR(300);

SELECT CONCAT(paciente.nombre, ' ', paciente.apellidos), COUNT(*)
INTO nombre_apellidos, num_ingresos
FROM ingreso
INNER JOIN paciente ON ingreso.codigo_p = paciente.codigo_p
WHERE paciente.codigo_p = codigo;

RETURN CONCAT('El paciente ', nombre_apellidos, ' ha tenido ', num_ingresos, ' ingresos.');
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `num_ingresos_por_medico` (`codigo_m_entrada` INT) RETURNS INT(11) BEGIN
DECLARE num_ingresos INT;
SELECT COUNT(*) INTO num_ingresos 
FROM ingreso 
WHERE codigo_m = codigo_m_entrada;
RETURN num_ingresos;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingreso`
--

CREATE TABLE `ingreso` (
  `codigo_i` int(10) UNSIGNED NOT NULL,
  `numeroHabitacion` int(5) UNSIGNED NOT NULL,
  `cama` varchar(1) NOT NULL,
  `fechaIngreso` date NOT NULL,
  `codigo_p` int(10) UNSIGNED NOT NULL,
  `codigo_m` int(10) UNSIGNED NOT NULL
) ;

--
-- Volcado de datos para la tabla `ingreso`
--

INSERT INTO `ingreso` (`codigo_i`, `numeroHabitacion`, `cama`, `fechaIngreso`, `codigo_p`, `codigo_m`) VALUES
(1, 1, 'A', '2022-12-12', 1, 1),
(2, 11, 'C', '2022-02-15', 20, 9),
(3, 8, 'B', '2022-03-10', 33, 11),
(31, 22, 'C', '2022-01-01', 19, 22),
(32, 6, 'A', '2022-01-30', 16, 19),
(33, 9, 'B', '2022-04-08', 3, 2),
(34, 16, 'A', '2022-02-07', 28, 10),
(35, 21, 'C', '2022-04-20', 10, 14),
(36, 20, 'C', '2022-01-14', 36, 12),
(37, 14, 'C', '2022-02-01', 17, 5),
(38, 13, 'B', '2022-03-15', 18, 22),
(39, 18, 'A', '2022-02-10', 40, 18),
(40, 2, 'C', '2022-03-22', 27, 17),
(41, 13, 'B', '2022-02-26', 30, 5),
(42, 7, 'A', '2022-04-09', 39, 2),
(43, 23, 'B', '2022-04-05', 4, 14),
(44, 7, 'B', '2022-03-06', 23, 13),
(45, 19, 'C', '2022-04-19', 32, 4),
(46, 10, 'B', '2022-03-16', 35, 3),
(47, 18, 'C', '2022-01-25', 7, 22),
(48, 12, 'A', '2022-04-12', 9, 14),
(49, 24, 'A', '2022-01-08', 26, 2),
(51, 21, 'A', '2022-02-18', 34, 15),
(52, 5, 'B', '2022-04-10', 31, 22),
(53, 16, 'B', '2022-02-26', 11, 21),
(54, 1, 'C', '2022-04-21', 13, 10),
(55, 8, 'C', '2022-01-12', 5, 13),
(56, 25, 'A', '2022-02-05', 6, 18),
(57, 4, 'C', '2022-03-13', 14, 3),
(58, 15, 'B', '2022-03-02', 1, 21),
(59, 6, 'C', '2022-02-23', 29, 8),
(60, 3, 'A', '2023-04-15', 15, 6),
(61, 10, 'B', '2023-04-16', 8, 19),
(62, 4, 'C', '2023-04-17', 29, 1),
(63, 14, 'B', '2023-04-17', 12, 20),
(64, 8, 'A', '2023-04-18', 37, 14),
(65, 11, 'B', '2023-04-18', 24, 22),
(66, 2, 'C', '2023-04-19', 18, 4),
(67, 6, 'A', '2023-04-19', 39, 3),
(68, 15, 'B', '2023-04-19', 16, 2),
(69, 1, 'C', '2023-04-20', 9, 9),
(70, 13, 'A', '2023-04-20', 21, 18),
(71, 5, 'B', '2023-04-21', 28, 16),
(72, 12, 'C', '2023-04-21', 25, 15),
(73, 9, 'A', '2023-04-22', 4, 13),
(74, 16, 'B', '2023-04-22', 36, 5),
(75, 7, 'C', '2023-04-23', 32, 10),
(76, 11, 'A', '2023-04-23', 2, 20),
(77, 14, 'B', '2023-04-23', 35, 12),
(78, 3, 'C', '2023-04-24', 7, 1),
(79, 15, 'A', '2023-04-24', 33, 11),
(80, 8, 'B', '2023-04-25', 30, 17),
(81, 12, 'C', '2023-04-25', 14, 8),
(82, 2, 'A', '2023-04-26', 6, 4),
(83, 16, 'B', '2023-04-26', 13, 21),
(84, 5, 'C', '2023-04-27', 26, 16),
(85, 110, 'B', '2022-01-20', 21, 1),
(86, 206, 'C', '2022-04-02', 16, 13),
(87, 117, 'A', '2021-10-01', 32, 6),
(88, 118, 'B', '2022-03-12', 27, 10),
(89, 203, 'B', '2022-01-23', 3, 5),
(90, 112, 'B', '2022-04-09', 29, 9),
(91, 113, 'C', '2021-11-17', 4, 4),
(92, 116, 'B', '2022-02-14', 8, 15),
(93, 202, 'C', '2022-02-20', 7, 2),
(94, 204, 'A', '2021-10-16', 23, 17),
(95, 207, 'C', '2022-03-27', 31, 11),
(96, 205, 'B', '2022-05-08', 25, 16),
(97, 119, 'A', '2022-04-25', 22, 20),
(98, 207, 'A', '2021-11-07', 6, 9),
(99, 105, 'C', '2022-03-01', 34, 3),
(100, 202, 'A', '2021-10-10', 2, 22),
(101, 103, 'A', '2022-03-05', 33, 10),
(102, 113, 'B', '2022-02-17', 14, 16),
(103, 201, 'C', '2022-01-05', 38, 13),
(104, 112, 'C', '2022-05-01', 23, 19),
(105, 117, 'C', '2022-01-09', 27, 15),
(106, 111, 'A', '2022-02-10', 21, 1),
(107, 104, 'B', '2022-04-19', 36, 19),
(108, 119, 'B', '2021-11-25', 10, 11),
(109, 105, 'A', '2022-01-26', 37, 14),
(110, 201, 'A', '2022-05-07', 1, 6),
(111, 106, 'C', '2022-03-19', 28, 2),
(112, 112, 'A', '2022-02-01', 13, 18),
(113, 203, 'B', '2022-01-10', 17, 5),
(114, 120, 'A', '2022-02-18', 16, 6),
(115, 105, 'B', '2022-04-23', 40, 3),
(116, 111, 'B', '2022-05-01', 12, 5),
(117, 201, 'B', '2021-10-23', 11, 18),
(119, 204, 'B', '2022-04-27', 9, 4),
(120, 116, 'C', '2022-01-24', 35, 12),
(121, 67, 'A', '2002-10-12', 40, 20),
(122, 50, 'C', '2004-11-20', 30, 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medico`
--

CREATE TABLE `medico` (
  `codigo_m` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(200) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `especialidad` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `medico`
--

INSERT INTO `medico` (`codigo_m`, `nombre`, `apellidos`, `telefono`, `especialidad`) VALUES
(1, 'dadasd', 'dadsad', 'dadssada', 'dadsa'),
(2, 'María', 'Fernández Rodríguez', '645678901', 'Pediatría'),
(3, 'José', 'Martínez Sánchez', '634567890', 'Traumatología'),
(4, 'Ana', 'García Ruiz', '612345678', 'Dermatología'),
(5, 'Luis', 'Pérez López', '612345679', 'Oftalmología'),
(6, 'Isabel', 'González García', '645678902', 'Ginecología'),
(8, 'Sara', 'Gómez Rodríguez', '612345679', 'Oncología'),
(9, 'Javier', 'Fernández Sánchez', '912345679', 'Neurología'),
(10, 'Carmen', 'Martínez Ruiz', '634567892', 'Pediatría'),
(11, 'David', 'García Sánchez', '645678903', 'Dermatología'),
(12, 'Rocío', 'Pérez González', '634567893', 'Oftalmología'),
(13, 'Pablo', 'González Ruiz', '645678904', 'Ginecología'),
(14, 'Marta', 'Sánchez López', '612345680', 'Cardiología'),
(15, 'Sergio', 'Gómez Pérez', '634567894', 'Traumatología'),
(16, 'Elena', 'Fernández Rodríguez', '912345680', 'Oncología'),
(17, 'Juan', 'Pérez García', '634567895', 'Neurología'),
(18, 'Lucía', 'García Ruiz', '645678905', 'Dermatología'),
(19, 'Jorge', 'Martínez Sánchez', '634567896', 'Oftalmología'),
(20, 'Alicia', 'Pérez López', '645678906', 'Pediatría'),
(21, 'Hugo', 'González García', '912345681', 'Cardiología'),
(22, 'Carla', 'Sánchez Pérez', '634567897', 'Traumatología'),
(23, 'Juan', 'Montoya Romero', '654343288', 'Cardiologia'),
(24, 'Juan', 'Montoya Romero', '654343288', 'Cardiologia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `codigo_p` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(200) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `poblacion` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `codigoPostal` varchar(10) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `fechaNacimiento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`codigo_p`, `nombre`, `apellidos`, `direccion`, `poblacion`, `provincia`, `codigoPostal`, `telefono`, `fechaNacimiento`) VALUES
(1, 'DDDD', 'DDD', 'dasdads', 'dasdsada', 'dasdasdsa', '28001', '111111111', '2022-11-11'),
(2, 'Ana', 'García Ruiz', 'Calle San Antonio, 24', 'Sevilla', 'Sevilla', '41001', '645678901', '1990-02-20'),
(3, 'Luis', 'Martínez Sánchez', 'Avenida de la Constitución, 8', 'Granada', 'Granada', '18001', '634567890', '1978-11-10'),
(4, 'María', 'Fernández Gómez', 'Plaza Mayor, 1', 'Toledo', 'Toledo', '45001', '612345678', '1995-08-15'),
(5, 'José', 'Rodríguez García', 'Calle Mayor, 3', 'Valencia', 'Valencia', '46001', '612345679', '1970-07-25'),
(6, 'Sofía', 'López Hernández', 'Calle Alcalá, 45', 'Madrid', 'Madrid', '28001', '634567891', '1983-04-12'),
(7, 'Miguel', 'González Sánchez', 'Avenida de la Libertad, 15', 'Barcelona', 'Barcelona', '8001', '645678902', '1988-09-05'),
(8, 'Lucía', 'Romero García', 'Calle Mayor, 20', 'Bilbao', 'Vizcaya', '48001', '612345680', '1992-06-03'),
(9, 'Javier', 'Sanz Gutiérrez', 'Plaza España, 1', 'Madrid', 'Madrid', '28001', '612345681', '1980-03-08'),
(10, 'Nuria', 'García Moreno', 'Calle Real, 10', 'Sevilla', 'Sevilla', '41001', '634567892', '1998-01-30'),
(11, 'Fernando', 'López Ortiz', 'Avenida de la Constitución, 50', 'Granada', 'Granada', '18001', '645678903', '1982-12-20'),
(12, 'Carmen', 'Torres García', 'Calle San Antonio, 8', 'Toledo', 'Toledo', '45001', '612345682', '1975-10-18'),
(13, 'Manuel', 'García Pérez', 'Plaza Mayor, 5', 'Valencia', 'Valencia', '46001', '612345683', '1989-07-05'),
(14, 'Cristina', 'Hernández Romero', 'Calle Mayor, 30', 'Madrid', 'Madrid', '28001', '634567893', '1994-04-22'),
(15, 'Maria', 'Hernández López', 'Calle Mayor 34', 'Madrid', 'Madrid', '28001', '654321785', '1982-03-15'),
(16, 'Sergio', 'Sánchez Ramírez', 'Calle Goya 23', 'Valencia', 'Valencia', '46001', '632145898', '1990-12-18'),
(17, 'Elena', 'Martínez García', 'Calle Alcalá 56', 'Madrid', 'Madrid', '28009', '698547215', '1974-05-22'),
(18, 'Hugo', 'Muñoz López', 'Calle Marqués de Viana 18', 'Barcelona', 'Barcelona', '8001', '654123789', '1988-10-31'),
(19, 'Alicia', 'González Sánchez', 'Calle Mayor 67', 'Sevilla', 'Sevilla', '41001', '687123456', '1975-06-23'),
(20, 'Adrián', 'Gómez García', 'Calle Gran Vía 89', 'Madrid', 'Madrid', '28013', '632598741', '1991-09-17'),
(21, 'Olivia', 'Fernández López', 'Calle Princesa 15', 'Madrid', 'Madrid', '28008', '654789123', '1985-02-20'),
(22, 'David', 'López Pérez', 'Calle San Bernardo 23', 'Madrid', 'Madrid', '28004', '698547258', '1978-09-12'),
(23, 'Lucía', 'Rodríguez González', 'Calle Alfonso XII 8', 'Barcelona', 'Barcelona', '8001', '632598749', '1992-07-05'),
(24, 'Mario', 'García Gutiérrez', 'Calle Alcalá 43', 'Madrid', 'Madrid', '28006', '654789129', '1980-01-28'),
(25, 'Raquel', 'Martínez García', 'Calle Mayor 56', 'Sevilla', 'Sevilla', '41001', '698547219', '1977-08-03'),
(26, 'Diego', 'Santos Gómez', 'Calle Mayor 12', 'Valencia', 'Valencia', '46001', '687123459', '1993-04-14'),
(27, 'Alba', 'López Martínez', 'Calle Gran Vía 34', 'Barcelona', 'Barcelona', '8001', '654321789', '1986-11-27'),
(28, 'Sara', 'García López', 'Calle Goya 67', 'Madrid', 'Madrid', '28001', '632145897', '1994-08-10'),
(29, 'Sofía', 'Hernández García', 'Calle Mayor 4', 'Madrid', 'Madrid', '28001', '678123456', '1998-05-17'),
(30, 'Marta', 'Vidal López', 'Calle Marqués de Urquijo 12', 'Madrid', 'Madrid', '28003', '645987321', '1978-09-02'),
(31, 'Hugo', 'Romero Ortiz', 'Avenida del Parque 25', 'Valencia', 'Valencia', '46001', '601234467', '1965-02-20'),
(32, 'Miguel', 'González García', 'Calle de la Paz 12', 'Córdoba', 'Córdoba', '14001', '622345678', '1985-07-12'),
(33, 'María', 'López Rodríguez', 'Calle Mayor 45', 'Valladolid', 'Valladolid', '47001', '601234567', '2003-04-30'),
(34, 'Lucía', 'García Fernández', 'Avenida de la Libertad 78', 'Zaragoza', 'Zaragoza', '50001', '612645678', '1980-11-11'),
(35, 'Andrés', 'Sánchez Moreno', 'Calle Mayor 12', 'Málaga', 'Málaga', '29001', '678123459', '1975-06-22'),
(36, 'Natalia', 'Torres Ramos', 'Calle La Palma 8', 'Sevilla', 'Sevilla', '41001', '678901234', '1995-01-15'),
(37, 'Roberto', 'Martínez Ruiz', 'Calle Alcalá 60', 'Madrid', 'Madrid', '28001', '623456789', '1973-08-03'),
(38, 'Juan', 'Hernández López', 'Calle Mayor 6', 'Barcelona', 'Barcelona', '8001', '645678001', '1990-02-27'),
(39, 'Manuel', 'Jiménez García', 'Calle Gran Vía 15', 'Madrid', 'Madrid', '28013', '629345678', '1969-12-31'),
(40, 'María', 'Rodríguez Pérez', 'Calle San Bernardo 20', 'Madrid', 'Madrid', '28015', '601134567', '1997-03-10'),
(41, 'José', 'Laguillo Verne', 'Calle María 29', 'Dos Hermanas', 'Sevilla', '40560', '644305070', '2002-10-21');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_ingresos`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_ingresos` (
`nombre_paciente` varchar(100)
,`apellidos_paciente` varchar(200)
,`nombre_medico` varchar(100)
,`apellidos_medico` varchar(200)
,`fechaIngreso` date
);

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_ingresos`
--
DROP TABLE IF EXISTS `vista_ingresos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_ingresos`  AS SELECT `paciente`.`nombre` AS `nombre_paciente`, `paciente`.`apellidos` AS `apellidos_paciente`, `medico`.`nombre` AS `nombre_medico`, `medico`.`apellidos` AS `apellidos_medico`, `ingreso`.`fechaIngreso` AS `fechaIngreso` FROM ((`ingreso` join `paciente` on(`ingreso`.`codigo_p` = `paciente`.`codigo_p`)) join `medico` on(`ingreso`.`codigo_m` = `medico`.`codigo_m`)) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ingreso`
--
ALTER TABLE `ingreso`
  ADD PRIMARY KEY (`codigo_i`),
  ADD UNIQUE KEY `codigo_i` (`codigo_i`),
  ADD KEY `codigo_p` (`codigo_p`),
  ADD KEY `codigo_m` (`codigo_m`);

--
-- Indices de la tabla `medico`
--
ALTER TABLE `medico`
  ADD PRIMARY KEY (`codigo_m`),
  ADD UNIQUE KEY `codigo_m` (`codigo_m`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`codigo_p`),
  ADD UNIQUE KEY `codigo_p` (`codigo_p`),
  ADD UNIQUE KEY `telefono` (`telefono`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ingreso`
--
ALTER TABLE `ingreso`
  MODIFY `codigo_i` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ingreso`
--
ALTER TABLE `ingreso`
  ADD CONSTRAINT `ingreso_ibfk_1` FOREIGN KEY (`codigo_p`) REFERENCES `paciente` (`codigo_p`),
  ADD CONSTRAINT `ingreso_ibfk_2` FOREIGN KEY (`codigo_m`) REFERENCES `medico` (`codigo_m`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
