/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - bioskop_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bioskop_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `bioskop_db`;

/*Table structure for table `kasir` */

DROP TABLE IF EXISTS `kasir`;

CREATE TABLE `kasir` (
  `idKasir` bigint(20) NOT NULL AUTO_INCREMENT,
  `korisnickoIme` varchar(50) NOT NULL,
  `sifra` varchar(50) NOT NULL,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  PRIMARY KEY (`idKasir`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `kasir` */

insert  into `kasir`(`idKasir`,`korisnickoIme`,`sifra`,`ime`,`prezime`) values 
(1,'admin','admin123','Djordje','Popovic');

/*Table structure for table `musterija` */

DROP TABLE IF EXISTS `musterija`;

CREATE TABLE `musterija` (
  `idMusterija` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `imePrezime` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `kontaktTelefon` varchar(255) DEFAULT NULL,
  `idStarosnoDoba` bigint(20) NOT NULL,
  PRIMARY KEY (`idMusterija`),
  KEY `idStarosnoDoba` (`idStarosnoDoba`),
  CONSTRAINT `musterija_ibfk_1` FOREIGN KEY (`idStarosnoDoba`) REFERENCES `starosnodoba` (`idStarosnoDoba`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `musterija` */

insert  into `musterija`(`idMusterija`,`ime`,`prezime`,`imePrezime`,`email`,`kontaktTelefon`,`idStarosnoDoba`) values 
(1,'Lazar','Popovic','Lazar Popovic','popovic.lazar2006@gmail.com','0677131170',2),
(3,'Lazar','Cadjenovic','Lazar Cadjenovic','cadjo1@gmail.com','0677025330',2),
(4,'Matija','Cubelic','Matija Cubelic','matija@gmail.com','0677035550',2),
(5,'Natasa','Popovic','Natasa Popovic','natasapopovic77@gmail.com','0666615435',3),
(6,'Bojan','Popovic','Bojan Popovic','popovicbojansd1@gmail.com','0631092588',3);

/*Table structure for table `projekcija` */

DROP TABLE IF EXISTS `projekcija`;

CREATE TABLE `projekcija` (
  `idProjekcija` bigint(20) NOT NULL AUTO_INCREMENT,
  `nazivFilma` varchar(100) NOT NULL,
  `cena` decimal(10,2) NOT NULL,
  `zanr` varchar(50) NOT NULL,
  `tipProjekcije` varchar(50) NOT NULL,
  `trajanje` bigint(20) NOT NULL,
  `datumVreme` datetime NOT NULL,
  PRIMARY KEY (`idProjekcija`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `projekcija` */

insert  into `projekcija`(`idProjekcija`,`nazivFilma`,`cena`,`zanr`,`tipProjekcije`,`trajanje`,`datumVreme`) values 
(1,'Obsession',350.00,'Horor','2D',112,'2026-06-20 20:00:00'),
(2,'Backrooms',350.00,'Horor','2D',100,'2026-06-28 21:00:00'),
(3,'Supergirl',350.00,'Superherojski','2D',130,'2026-06-28 19:00:00'),
(4,'The drama',400.00,'Drama','2D',100,'2026-06-30 20:00:00'),
(5,'The Odyssey',600.00,'Istorijski','IMAX',150,'2026-07-01 19:00:00');

/*Table structure for table `racun` */

DROP TABLE IF EXISTS `racun`;

CREATE TABLE `racun` (
  `idRacun` bigint(20) NOT NULL AUTO_INCREMENT,
  `datumVreme` datetime NOT NULL,
  `storniran` tinyint(4) DEFAULT 0,
  `ukupnaCena` decimal(10,2) NOT NULL,
  `idMusterija` bigint(20) NOT NULL,
  `idKasir` bigint(20) NOT NULL,
  PRIMARY KEY (`idRacun`),
  KEY `idMusterija` (`idMusterija`),
  KEY `idKasir` (`idKasir`),
  CONSTRAINT `racun_ibfk_1` FOREIGN KEY (`idMusterija`) REFERENCES `musterija` (`idMusterija`),
  CONSTRAINT `racun_ibfk_2` FOREIGN KEY (`idKasir`) REFERENCES `kasir` (`idKasir`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `racun` */

insert  into `racun`(`idRacun`,`datumVreme`,`storniran`,`ukupnaCena`,`idMusterija`,`idKasir`) values 
(1,'2026-06-25 22:39:34',0,1400.00,1,1),
(2,'2026-06-18 19:01:45',1,2450.00,1,1),
(3,'2026-06-25 22:54:34',0,350.00,1,1),
(4,'2026-06-25 23:10:17',0,1900.00,3,1),
(5,'2026-06-25 23:01:16',0,1200.00,4,1),
(6,'2026-06-25 23:01:26',0,350.00,4,1),
(7,'2026-06-25 23:01:36',0,350.00,1,1),
(8,'2026-06-25 23:01:44',0,600.00,1,1),
(9,'2026-06-25 23:02:03',0,800.00,3,1);

/*Table structure for table `starosnodoba` */

DROP TABLE IF EXISTS `starosnodoba`;

CREATE TABLE `starosnodoba` (
  `idStarosnoDoba` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  `godinaOd` bigint(20) NOT NULL,
  `godinaDo` bigint(20) NOT NULL,
  PRIMARY KEY (`idStarosnoDoba`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `starosnodoba` */

insert  into `starosnodoba`(`idStarosnoDoba`,`naziv`,`godinaOd`,`godinaDo`) values 
(1,'Deca',0,10),
(2,'Omladina',11,25),
(3,'Odrasli',26,64),
(4,'Penzioneri',65,120);

/*Table structure for table `stavkaracuna` */

DROP TABLE IF EXISTS `stavkaracuna`;

CREATE TABLE `stavkaracuna` (
  `idRacun` bigint(20) NOT NULL,
  `rb` bigint(20) NOT NULL,
  `kolicina` decimal(10,2) NOT NULL,
  `iznos` decimal(10,2) NOT NULL,
  `idProjekcija` bigint(20) NOT NULL,
  PRIMARY KEY (`idRacun`,`rb`),
  KEY `idProjekcija` (`idProjekcija`),
  CONSTRAINT `stavkaracuna_ibfk_1` FOREIGN KEY (`idProjekcija`) REFERENCES `projekcija` (`idProjekcija`),
  CONSTRAINT `stavkaracuna_ibfk_2` FOREIGN KEY (`idRacun`) REFERENCES `racun` (`idRacun`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavkaracuna` */

insert  into `stavkaracuna`(`idRacun`,`rb`,`kolicina`,`iznos`,`idProjekcija`) values 
(1,1,4.00,1400.00,1),
(2,1,7.00,2450.00,1),
(3,1,1.00,350.00,1),
(4,1,2.00,1200.00,5),
(4,2,2.00,700.00,2),
(5,1,2.00,1200.00,5),
(6,1,1.00,350.00,3),
(7,1,1.00,350.00,2),
(8,1,1.00,600.00,5),
(9,1,2.00,800.00,4);

/*Table structure for table `zadruga` */

DROP TABLE IF EXISTS `zadruga`;

CREATE TABLE `zadruga` (
  `idZadruga` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  `PIB` varchar(255) NOT NULL,
  `kontaktTelefon` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`idZadruga`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `zadruga` */

/*Table structure for table `zaposlenje` */

DROP TABLE IF EXISTS `zaposlenje`;

CREATE TABLE `zaposlenje` (
  `idZaposlenje` bigint(20) NOT NULL AUTO_INCREMENT,
  `idKasir` bigint(20) NOT NULL,
  `idZadruga` bigint(20) NOT NULL,
  `brojUgovora` varchar(255) NOT NULL,
  `datumSaradnje` date DEFAULT NULL,
  PRIMARY KEY (`idZaposlenje`),
  KEY `idKasir` (`idKasir`),
  KEY `idZadruga` (`idZadruga`),
  CONSTRAINT `zaposlenje_ibfk_1` FOREIGN KEY (`idKasir`) REFERENCES `kasir` (`idKasir`),
  CONSTRAINT `zaposlenje_ibfk_2` FOREIGN KEY (`idZadruga`) REFERENCES `zadruga` (`idZadruga`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `zaposlenje` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
