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
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_role_id` int NOT NULL,
  `position_id` int NOT NULL,
  `role_name` varchar(15) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  KEY `PositionID_idx` (`position_id`),
  CONSTRAINT `positionID` FOREIGN KEY (`position_id`) REFERENCES `position` (`position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (30001,20001,'Admin'),(30002,20002,'Employee'),(30003,20003,'Employee'),(30004,20004,'Employee'),(30005,20005,'Employee'),(30006,20006,'Employee'),(30007,20007,'Admin'),(30008,20008,'Admin'),(30009,20009,'Admin'),(30010,20010,'Admin'),(30011,20011,'Employee'),(30012,20012,'Employee'),(30013,20013,'Employee'),(30014,20014,'Employee'),(30015,20015,'Employee'),(30016,20016,'Employee'),(30017,20017,'Employee'),(30018,20018,'Admin'),(30019,20019,'Admin'),(30020,20020,'Admin'),(30021,20021,'Employee'),(30022,20022,'Employee'),(30023,20023,'Employee'),(30024,20024,'Employee'),(30025,20025,'Employee'),(30026,20026,'Employee'),(30027,20027,'Employee'),(30028,20028,'Employee'),(30029,20029,'Employee'),(30030,20030,'Employee'),(30031,20031,'Employee'),(30032,20032,'Employee'),(30033,20033,'Employee'),(30034,20034,'Employee'),(30035,20035,'Admin');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
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
