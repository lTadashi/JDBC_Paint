-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.5.27 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4197
-- Date/time:                    2012-10-02 02:41:55
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for paint
DROP DATABASE IF EXISTS `paint`;
CREATE DATABASE IF NOT EXISTS `paint` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `paint`;


-- Dumping structure for table paint.desenhos
DROP TABLE IF EXISTS `desenhos`;
CREATE TABLE IF NOT EXISTS `desenhos` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nome_desenho` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Data exporting was unselected.


-- Dumping structure for table paint.elipses
DROP TABLE IF EXISTS `elipses`;
CREATE TABLE IF NOT EXISTS `elipses` (
  `id_desenho` int(10) NOT NULL,
  `pos_x` int(10) DEFAULT NULL,
  `pos_y` int(10) DEFAULT NULL,
  `largura` int(10) DEFAULT NULL,
  `altura` int(10) DEFAULT NULL,
  KEY `FK_elipse_id_desenho` (`id_desenho`),
  CONSTRAINT `elipses_ibfk_1` FOREIGN KEY (`id_desenho`) REFERENCES `desenhos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Data exporting was unselected.


-- Dumping structure for table paint.pontos
DROP TABLE IF EXISTS `pontos`;
CREATE TABLE IF NOT EXISTS `pontos` (
  `id_desenho` int(10) NOT NULL,
  `pos_x` int(10) DEFAULT NULL,
  `pos_y` int(10) DEFAULT NULL,
  KEY `FK_ponto_id_desenho` (`id_desenho`),
  CONSTRAINT `pontos_ibfk_1` FOREIGN KEY (`id_desenho`) REFERENCES `desenhos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Data exporting was unselected.


-- Dumping structure for table paint.retangulos
DROP TABLE IF EXISTS `retangulos`;
CREATE TABLE IF NOT EXISTS `retangulos` (
  `id_desenho` int(10) NOT NULL,
  `pos_x` int(10) DEFAULT NULL,
  `pos_y` int(10) DEFAULT NULL,
  `largura` int(10) DEFAULT NULL,
  `altura` int(10) DEFAULT NULL,
  KEY `FK_retangulo_id_desenho` (`id_desenho`),
  CONSTRAINT `FK_retangulo_id_desenho` FOREIGN KEY (`id_desenho`) REFERENCES `desenhos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table paint.retas
DROP TABLE IF EXISTS `retas`;
CREATE TABLE IF NOT EXISTS `retas` (
  `id_desenho` int(10) NOT NULL,
  `pos_x1` int(10) DEFAULT NULL,
  `pos_y1` int(10) DEFAULT NULL,
  `pos_x2` int(10) DEFAULT NULL,
  `pos_y2` int(10) DEFAULT NULL,
  KEY `FK_reta_id_desenho` (`id_desenho`),
  CONSTRAINT `FK_reta_id_desenho` FOREIGN KEY (`id_desenho`) REFERENCES `desenhos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
