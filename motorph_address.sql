-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: motorph
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` int NOT NULL,
  `street` varchar(255) NOT NULL,
  `barangay` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  `postalcode` char(4) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (12001,'Valero','Bel-Air','Makati','Metro Manila (National Capital Region)','1227'),(12002,'Block 1 Lot 8 and 2, San Antonio De Padua 2','San Antonio De Padua II','DasmariÃ±as','Cavite','4114'),(12003,'Timog Avenue corner Quezon Avenue','South Triangle','Quezon','Metro Manila (National Capital Region)','1100'),(12004,'Solanda','Barangay 657','Manila','Metro Manila','1000'),(12005,'National Highway','Barangay 20','Gingoog','Misamis Oriental','9014'),(12006,'17/85 Stracke Via, Suite 042','Poblacion','Las PiÃ±as','Dinagat Islands','4783'),(12007,'99 Strosin Hills','Poblacion','Bislig','Tawi-Tawi','5340'),(12008,'12A/33 Upton Isle Apt. 420','Tiza','Roxas','Surigao del Norte','1814'),(12009,'90A Dibbert Terrace Apt. 190','San Lorenzo','Tagum','Davao del Norte','6056'),(12010,'T. Morato Avenue corner Scout Rallos','Laging Handa','Quezon','Metro Manila','1103'),(12011,'Shanahan Alley','Santo Tomas','Masbate','Masbate','1572'),(12012,'49 Springs Apt. 266','Poblacion','Taguig','Occidental Mindoro','3200'),(12013,'Sawayn Stream','Ubay','Ubay','Zamboanga del Norte','1208'),(12014,'Kulas Roads','Maragondon','Quirino','Quirino','0962'),(12015,'Lubowitz Meadows','Pililla','Pililla','Zambales','4895'),(12016,'90 O\'Keefe Spur Apt. 379','Catigbian','Catigbian','Sulu','2772'),(12017,'89A Armstrong Trace','Compostela','Compostela','Maguindanao','7874'),(12018,'08 Grant Drive Suite 406','Poblacion','Iloilo','La Union','9186'),(12019,'93A/21 Berge Points','Tapaz','Tapaz','Quezon','2180'),(12020,'65 Murphy Center Suite 094,','Poblacion','Palayan','Quirino','5636'),(12021,'47A/94 Larkin Plaza Apt. 179','Poblacion','Caloocan','Quirino','2751'),(12022,'06A Gulgowski Extensions','Bongabon','Bongabon','Zamboanga del Sur','6085'),(12023,'Padberg Spring','Poblacion','Mabalacat','Lanao del Sur','3959'),(12024,'Ledner Ridges','Poblacion','Kabankalan','Marinduque','8870'),(12025,'Watsica Flats Suite','Poblacion','Malolos','Ifugao','1844'),(12026,'Wilderman Walks','Poblacion','Digos','Davao del Sur','5822'),(12027,'Goyette Valley Suite 219','Poblacion','Tabuk ','Lanao del Sur','3159'),(12028,'Mann Views','Luisiana ','Luisiana ','Dinagat Islands','1263'),(12029,'Stamm Spurs','Bustos','Bustos','Iloilo','4550'),(12030,'Bahringer Oval Suite 145,','Kiamba','Kiamba','Nueva Ecija','7688'),(12031,'Cremin Junction','Surallah ','Surallah ','Cotabato','2809'),(12032,'Hi-way','Yati','Liloan ','Cebu',''),(12033,'','Bulala','Camalaniugan','Bulala, Camalaniugan',''),(12034,'Agapita Building','','Metro Manila','',''),(12035,'88/47 Huel Hills','Puti','Tagaytay','Lanao del Norte','5475');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-09  0:03:40
