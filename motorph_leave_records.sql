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
-- Table structure for table `leave_records`
--

DROP TABLE IF EXISTS `leave_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leave_records` (
  `leave_id` int NOT NULL,
  `employee_id` int NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `leave_type` enum('Sick Leave','Vacation Leave','Maternity Leave','Paternity Leave','Emergency Leave') NOT NULL,
  `status` enum('Pending','Approved','Declined','Cancelled') NOT NULL DEFAULT 'Pending',
  PRIMARY KEY (`leave_id`),
  KEY `LeaveEmpID_idx` (`employee_id`),
  CONSTRAINT `LeaveEmpID` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `chkValidDates` CHECK ((`start_date` <= `end_date`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_records`
--

LOCK TABLES `leave_records` WRITE;
/*!40000 ALTER TABLE `leave_records` DISABLE KEYS */;
INSERT INTO `leave_records` VALUES (50001,10002,'2024-01-11','2024-01-12','Sick Leave','Approved'),(50003,10008,'2024-01-29','2024-01-31','Emergency Leave','Approved'),(50004,10019,'2024-01-10','2024-02-12','Emergency Leave','Approved'),(50005,10028,'2024-01-17','2024-02-19','Vacation Leave','Approved'),(50006,10030,'2024-01-12','2024-01-15','Sick Leave','Approved'),(50008,10015,'2024-01-11','2024-01-12','Sick Leave','Approved'),(50010,10011,'2024-01-29','2024-01-31','Emergency Leave','Approved'),(50011,10029,'2024-01-10','2024-02-12','Emergency Leave','Approved'),(50012,10025,'2024-01-17','2024-02-19','Vacation Leave','Approved'),(50013,10032,'2024-01-12','2024-01-15','Sick Leave','Approved'),(50015,10005,'2024-01-11','2024-01-12','Sick Leave','Approved'),(50017,10012,'2024-01-29','2024-01-31','Emergency Leave','Approved'),(50018,10018,'2024-01-10','2024-02-12','Emergency Leave','Approved'),(50019,10027,'2024-01-17','2024-02-19','Vacation Leave','Approved'),(50020,10002,'2024-01-12','2024-01-15','Sick Leave','Approved'),(50022,10008,'2024-01-11','2024-01-12','Sick Leave','Approved'),(50024,10028,'2024-01-29','2024-01-31','Emergency Leave','Approved'),(50025,10030,'2024-01-10','2024-02-12','Emergency Leave','Approved'),(50026,10031,'2024-01-17','2024-02-19','Vacation Leave','Approved'),(50027,10015,'2024-01-12','2024-01-15','Sick Leave','Approved'),(50029,10011,'2024-01-11','2024-01-12','Sick Leave','Approved'),(50031,10025,'2024-01-29','2024-01-31','Emergency Leave','Approved'),(50032,10032,'2024-01-10','2024-02-12','Emergency Leave','Approved'),(50033,10033,'2024-01-17','2024-02-19','Vacation Leave','Approved'),(50034,10005,'2024-01-12','2024-01-15','Sick Leave','Approved'),(50036,10012,'2024-01-11','2024-01-12','Sick Leave','Approved'),(50038,10027,'2024-01-29','2024-01-31','Emergency Leave','Approved'),(50039,10016,'2024-01-10','2024-02-12','Emergency Leave','Approved'),(50040,10006,'2024-01-17','2024-02-19','Vacation Leave','Approved'),(50041,10001,'2024-01-12','2024-01-15','Sick Leave','Approved');
/*!40000 ALTER TABLE `leave_records` ENABLE KEYS */;
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
