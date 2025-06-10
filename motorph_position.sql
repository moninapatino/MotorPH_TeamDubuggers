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
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
  `position_id` int NOT NULL,
  `employee_id` int NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `status` enum('Active','Inactive') NOT NULL DEFAULT 'Active',
  PRIMARY KEY (`position_id`),
  UNIQUE KEY `uniqueUsername` (`username`),
  KEY `PositionEmpID_idx` (`employee_id`),
  CONSTRAINT `PositionEmpID` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (20001,10001,'MGarcia','P@ssword','Active'),(20002,10002,'ALim','P@ssword','Active'),(20003,10003,'BAquino','P@ssword','Active'),(20004,10004,'IReyes','P@ssword','Active'),(20005,10005,'EHernandez','P@ssword','Active'),(20006,10006,'AVillanueva','P@ssword','Active'),(20007,10007,'BSanjose','P@ssword','Active'),(20008,10008,'ARomualdez','P@ssword','Active'),(20009,10009,'RAtienza','P@ssword','Active'),(20010,10010,'RAlvaro','P@ssword','Active'),(20011,10011,'ASalcedo','P@ssword','Active'),(20012,10012,'JLopez','P@ssword','Active'),(20013,10013,'MFrala','P@ssword','Active'),(20014,10014,'LMartinez','P@ssword','Active'),(20015,10015,'FRomualdez','P@ssword','Active'),(20016,10016,'CMata','P@ssword','Active'),(20017,10017,'SDeleon','P@ssword','Active'),(20018,10018,'ASanjose','P@ssword','Active'),(20019,10019,'CRosario','P@ssword','Active'),(20020,10020,'MBautista','P@ssword','Active'),(20021,10021,'DLazaro','P@ssword','Active'),(20022,10022,'KDelossantos','P@ssword','Active'),(20023,10023,'VSantos','P@ssword','Active'),(20024,10024,'TDelrosario','P@ssword','Active'),(20025,10025,'JTolentino','P@ssword','Active'),(20026,10026,'PGutierrez','P@ssword','Active'),(20027,10027,'GManalaysay','P@ssword','Active'),(20028,10028,'LVillegas','P@ssword','Active'),(20029,10029,'CRamos','P@ssword','Active'),(20030,10030,'EMaceda','P@ssword','Active'),(20031,10031,'DAguilar','P@ssword','Active'),(20032,10032,'JCastro','P@ssword','Active'),(20033,10033,'CMartinez','P@ssword','Active'),(20034,10034,'BSantos','P@ssword','Active'),(20035,10035,'Admin','admin','Active');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-09  0:03:41
