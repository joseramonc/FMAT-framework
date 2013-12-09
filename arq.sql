/*
SQLyog Job Agent Version 10.0 Beta1 Copyright(c) Webyog Inc. All Rights Reserved.


MySQL - 5.6.11 : Database - arq
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`arq` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `arq`;

/*Table structure for table `accion` */

DROP TABLE IF EXISTS `accion`;

CREATE TABLE `accion` (
  `ID` int(6) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `accion` */

insert  into `accion` values (1,'presionaBoton'),(2,'eliminar');

/*Table structure for table `perfil` */

DROP TABLE IF EXISTS `perfil`;

CREATE TABLE `perfil` (
  `ID` int(6) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `perfil` */

insert  into `perfil` values (1,'Estudiante'),(2,'Maestro');

/*Table structure for table `perfilaccion` */

DROP TABLE IF EXISTS `perfilaccion`;

CREATE TABLE `perfilaccion` (
  `IDPerfil` int(6) NOT NULL,
  `IDAccion` int(6) NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`IDPerfil`,`IDAccion`),
  KEY `IDAccion` (`IDAccion`),
  CONSTRAINT `perfilaccion_ibfk_1` FOREIGN KEY (`IDPerfil`) REFERENCES `perfil` (`ID`),
  CONSTRAINT `perfilaccion_ibfk_2` FOREIGN KEY (`IDAccion`) REFERENCES `accion` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `perfilaccion` */

insert  into `perfilaccion` values (1,2,1),(2,1,1),(2,2,0);

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `ID` int(6) NOT NULL AUTO_INCREMENT,
  `Alias` varchar(30) NOT NULL,
  `Contrasenia` varchar(40) NOT NULL,
  `Perfil` int(6) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDPerfil` (`Perfil`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`Perfil`) REFERENCES `perfil` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `usuario` */

insert  into `usuario` values (1,'Yussel','7UEyNsXiW4EmJRkVtDcr+g==',1),(2,'Aldo','1nnSxYn/cYySLGnb7yd9gA==',2),(4,'Pedro','TQ/GtWpewsdYESRFI2+09Q==',1),(5,'Ana','W50uymShkZdf+39yN8Pw9g==',1),(6,'Federico','TQ/GtWpewsdYESRFI2+09Q==',1),(7,'Lucia','TQ/GtWpewsdYESRFI2+09Q==',1),(8,'Marco','pE1CLclEfvJjyaOXBP/Uow==',1);

/*Table structure for table `usuarioaccion` */

DROP TABLE IF EXISTS `usuarioaccion`;

CREATE TABLE `usuarioaccion` (
  `IDUsuario` int(6) NOT NULL,
  `IDAccion` int(6) NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`IDUsuario`,`IDAccion`),
  KEY `IDAccion` (`IDAccion`),
  CONSTRAINT `usuarioaccion_ibfk_1` FOREIGN KEY (`IDUsuario`) REFERENCES `usuario` (`ID`),
  CONSTRAINT `usuarioaccion_ibfk_2` FOREIGN KEY (`IDAccion`) REFERENCES `accion` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `usuarioaccion` */

insert  into `usuarioaccion` values (1,2,1),(2,1,1),(2,2,0),(4,2,1),(5,2,1),(6,2,1),(7,2,1),(8,2,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
