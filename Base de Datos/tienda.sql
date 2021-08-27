-- Base de datos: TIENDA
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


-- Estructura de tabla para la tabla RECIBO
CREATE TABLE IF NOT EXISTS `recibo` (
  `num_rec` varchar(8) NOT NULL,
  `cod_cli` varchar(6) NOT NULL,
  `pre_tot` varchar(10) NOT NULL,
  `fecha` varchar(15) NOT NULL,
  PRIMARY KEY (`num_rec`),
  KEY `cod_cli` (`cod_cli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- --------------------------------------------------------


-- Estructura de tabla para la tabla CLIENTE
CREATE TABLE IF NOT EXISTS `cliente` (
  `cod_cli` varchar(6) NOT NULL,
  `nom_cli` varchar(30) NOT NULL,
  `ape_cli` varchar(30) NOT NULL,
  `sexo_cli` varchar(1) NOT NULL,
  `dni_cli` varchar(8) NOT NULL,
  `tel_cli` varchar(9) NOT NULL,
  `ruc_cli` varchar(11) NOT NULL,
  `email_cli` varchar(30) NOT NULL,
  `dir_cli` varchar(30) NOT NULL,
  PRIMARY KEY (`cod_cli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- --------------------------------------------------------

-- Estructura de tabla para la tabla DETALLERECIBO
CREATE TABLE IF NOT EXISTS `detallerecibo` (
  `num_rec` varchar(10) NOT NULL,
  `cod_pro` varchar(6) NOT NULL,
  `des_pro` varchar(30) NOT NULL,
  `cant_pro` varchar(3) NOT NULL,
  `pre_unit` varchar(10) NOT NULL,
  `pre_venta` varchar(10) NOT NULL,
  KEY `num_rec` (`num_rec`),
  KEY `cod_pro` (`cod_pro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- --------------------------------------------------------

-- Estructura de tabla para la tabla DETALLEFACTURA
CREATE TABLE IF NOT EXISTS `detallefactura` (
  `num_fac` varchar(10) NOT NULL,
  `cod_pro` varchar(6) NOT NULL,
  `des_pro` varchar(30) NOT NULL,
  `cant_pro` varchar(3) NOT NULL,
  `pre_unit` varchar(10) NOT NULL,
  `pre_tot` varchar(10) NOT NULL,
  KEY `num_fac` (`num_fac`),
  KEY `cod_pro` (`cod_pro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- --------------------------------------------------------


-- Estructura de tabla para la tabla FACTURA
CREATE TABLE IF NOT EXISTS `factura` (
  `num_fac` varchar(8) NOT NULL,
  `cod_cli` varchar(6) NOT NULL,
  `ruc_cli` varchar(11) NOT NULL,
  `subtotal` varchar(10) NOT NULL,
  `igv` varchar(40) NOT NULL,
  `total` varchar(20) NOT NULL,
  `fec_fac` varchar(20) NOT NULL,
  PRIMARY KEY (`num_fac`),
  KEY `cod_cli` (`cod_cli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- --------------------------------------------------------

-- Estructura de tabla para la tabla PRODUCTO
CREATE TABLE IF NOT EXISTS `producto` (
  `cod_pro` varchar(6) NOT NULL,
  `descripcion` varchar(30) NOT NULL,
  `precio` varchar(10) NOT NULL,
  `Stock` varchar(10) NOT NULL,
  PRIMARY KEY (`cod_pro`,`descripcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Filtros para la tabla RECIBO
ALTER TABLE `recibo`
  ADD CONSTRAINT `recibo_ibfk_1` FOREIGN KEY (`cod_cli`) REFERENCES `cliente` (`cod_cli`) ON DELETE CASCADE ON UPDATE CASCADE;

-- Filtros para la tabla DETALLERECIBO
ALTER TABLE `detallerecibo`
  ADD CONSTRAINT `detallerecibo_ibfk_1` FOREIGN KEY (`num_rec`) REFERENCES `recibo` (`num_rec`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detallerecibo_ibfk_2` FOREIGN KEY (`cod_pro`) REFERENCES `producto` (`cod_pro`) ON DELETE CASCADE ON UPDATE CASCADE;

-- Filtros para la tabla DETALLEFECTURA
ALTER TABLE `detallefactura`
  ADD CONSTRAINT `detallefactura_ibfk_1` FOREIGN KEY (`num_fac`) REFERENCES `factura` (`num_fac`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detallefactura_ibfk_2` FOREIGN KEY (`cod_pro`) REFERENCES `producto` (`cod_pro`) ON DELETE CASCADE ON UPDATE CASCADE;

-- Filtros para la tabla FACTURA
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`cod_cli`) REFERENCES `cliente` (`cod_cli`) ON DELETE CASCADE ON UPDATE CASCADE;
