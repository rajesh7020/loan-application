-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: loan-app
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `applied_loan`
--

DROP TABLE IF EXISTS `applied_loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applied_loan` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` bit(1) DEFAULT NULL,
  `is_approved` bit(1) DEFAULT NULL,
  `loan_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdmfqionlyc3dm2a7esnda2f9t` (`loan_id`),
  KEY `FKgkcbla7vnpebhonm7r0vlebp3` (`user_id`),
  CONSTRAINT `FKdmfqionlyc3dm2a7esnda2f9t` FOREIGN KEY (`loan_id`) REFERENCES `loan` (`id`),
  CONSTRAINT `FKgkcbla7vnpebhonm7r0vlebp3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applied_loan`
--

LOCK TABLES `applied_loan` WRITE;
/*!40000 ALTER TABLE `applied_loan` DISABLE KEYS */;
INSERT INTO `applied_loan` VALUES (1,_binary '',_binary '',1,2),(2,_binary '',_binary '\0',2,2),(3,_binary '',_binary '\0',3,2),(4,_binary '',_binary '\0',4,2);
/*!40000 ALTER TABLE `applied_loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loan` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` bit(1) DEFAULT NULL,
  `loan_amount` double DEFAULT NULL,
  `loan_name` varchar(255) DEFAULT NULL,
  `loan_term` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan`
--

LOCK TABLES `loan` WRITE;
/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
INSERT INTO `loan` VALUES (1,_binary '',2500.75,'Personal Loan','Monthly'),(2,_binary '',255500.75,'Car Loan','10 Years'),(3,_binary '',55500.75,'Bike Loan','5 Years'),(4,_binary '',55500.75,'Home Loan','5 Years'),(5,_binary '',55500.75,'Assest Loan','5 Years'),(6,_binary '',958500.75,'Business Loan','5 Years'),(7,_binary '',958500.75,'Animal Loan','5 Years'),(8,_binary '',8500.75,'Cloth Loan','1 Years'),(9,_binary '',56500.75,'Computer Loan','1 Years');
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` bit(1) DEFAULT NULL,
  `age` double DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,28,'brainerhub@gmail.com','$2a$10$90JU2.v7uB9brAXxqJGj0..UnplqmLCe9xhgqDf2jYw8SveNC5dzO',1),(2,_binary '',25,'rajesh@gmail.com','$2a$10$BmWv5hTrb56Lhf13mefmeujcSipiW2gDDPo6GD6hYcihzkeEzyKTS',2),(3,_binary '',27,'rajesh.bhushan@gmail.com','$2a$10$U.jQkH2Sfcsh1SRslkLr6uKtRXroevr218gjSI54A6SGeG8B1XGOS',2),(4,_binary '',29,'9537127214@gmail.com','$2a$10$kA8UPPRt2P3iTOWGrOckqu6nRMFfWWpGKcHxhkW/DYIqgg94R.0y2',2),(5,_binary '',29,'test@gmail.com','$2a$10$W.CpWjtfGJ49x3anXgjGCuXunLOqF3EEXKf3cA/pK3MWuSG2zmJ8y',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-06  1:04:32
