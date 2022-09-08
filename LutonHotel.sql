-- MySQL dump 10.13  Distrib 8.0.30, for Linux (x86_64)
--
-- Host: localhost    Database: LutonHotel
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `booking_date` date NOT NULL,
  `check_in_date` date NOT NULL,
  `check_out_date` date NOT NULL,
  `preferred_room_type` varchar(10) NOT NULL,
  `booking_status` varchar(15) NOT NULL,
  `cust_id` int NOT NULL,
  `staff_id` int DEFAULT NULL,
  `room_no` int DEFAULT NULL,
  PRIMARY KEY (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'2022-08-24','2022-05-23','2022-05-28','Single','cancelled',28,0,0),(2,'2022-08-24','2022-09-09','2022-09-19','Single','cancelled',28,0,0),(3,'2022-08-24','2022-02-22','2022-02-23','Single','cancelled',28,0,101),(4,'2022-08-24','2019-03-08','2019-03-10','Double','cancelled',28,0,0),(5,'2022-08-24','2021-03-09','2021-03-19','Twin','cancelled',28,0,0),(6,'2022-08-24','2022-09-19','2022-09-23','Twin','guaranteed',28,0,302),(7,'2022-08-24','2022-05-06','2022-06-03','Double','cancelled',29,0,0),(8,'2022-08-24','2022-04-05','2022-04-09','Twin','cancelled',29,0,0),(9,'2022-08-24','2022-06-19','2022-07-01','Twin','cancelled',29,0,0),(11,'2022-08-30','2022-02-23','2022-03-05','Double','pending',28,0,0),(12,'2022-08-30','2022-02-25','2022-03-07','Double','pending',28,0,0),(13,'2022-08-30','2022-02-18','2022-02-22','Double','completed',29,0,203),(14,'2022-08-30','2022-03-05','2022-03-09','Double','completed',29,0,204),(15,'2022-08-30','2022-05-07','2022-05-19','Single','guaranteed',29,0,102),(16,'2022-09-04','2022-03-05','2022-03-10','Single','cancelled',28,0,0),(17,'2022-09-04','2022-04-05','2022-04-09','Single','active',34,0,103),(18,'2022-09-04','2022-04-03','2022-05-01','Double','completed',28,0,204),(19,'2022-09-04','2022-06-07','2022-06-05','Double','pending',28,NULL,NULL),(20,'2022-09-04','2022-04-06','2022-04-19','Double','pending',29,0,0),(21,'2022-09-05','2022-04-06','2022-05-04','Double','pending',28,0,0),(22,'2022-09-05','2022-04-09','2022-05-09','Double','cancelled',28,0,0),(23,'2022-09-05','2022-02-05','2022-02-19','Double','pending',28,0,0),(24,'2022-09-05','2022-01-29','2022-02-13','Double','pending',28,0,0),(25,'2022-09-05','2022-03-06','2022-03-09','Double','completed',28,0,203),(26,'2022-09-05','2022-01-30','2022-02-04','Double','pending',28,0,0),(27,'2022-09-05','2022-08-23','2022-08-30','Twin','pending',29,0,0),(28,'2022-09-05','2022-08-05','2022-08-05','Single','completed',28,0,103),(29,'2022-09-05','2022-08-05','2022-08-05','Single','cancelled',18,0,0),(30,'2022-09-05','2022-08-07','2022-08-22','Single','cancelled',18,0,0),(31,'2022-09-05','2022-08-07','2022-08-16','Double','pending',18,NULL,NULL),(32,'2022-09-05','2022-08-01','2022-08-02','Single','pending',18,NULL,NULL);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `cust_id` int NOT NULL AUTO_INCREMENT,
  `cust_full_name` varchar(40) DEFAULT NULL,
  `cust_age` int DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `cust_gender` varchar(10) DEFAULT NULL,
  `contact` varchar(15) DEFAULT NULL,
  `credit_card_no` varchar(20) DEFAULT NULL,
  `organization_name` varchar(75) DEFAULT NULL,
  `website` varchar(75) DEFAULT NULL,
  `account_valid_till` date DEFAULT NULL,
  `next_billing_date` date DEFAULT NULL,
  `discount_percent` float DEFAULT NULL,
  `customer_type` varchar(15) DEFAULT NULL,
  `user_name` varchar(15) NOT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (14,'Doruk Wagle',32,'ktm','male','32','',NULL,NULL,NULL,NULL,NULL,'individual','chd'),(15,NULL,NULL,'lalitpur',NULL,'9892432452345',NULL,'patancollege','pcps.com','2023-08-22','2022-09-21',7,'corporate','patan'),(16,NULL,NULL,'a;dsfjkhl',NULL,'253789042354',NULL,'afjdksl','afdjkl;s','2023-08-22','2022-09-21',10,'corporate','chdasd'),(17,NULL,NULL,'alfdjs',NULL,'897534275534',NULL,'lsfdj','http://www.alsdfk.com','2023-08-22','2022-09-21',10,'corporate','sfdaj'),(18,'Kushal Kafle',19,'ktm','male','9878783457','93857434554334553',NULL,NULL,NULL,NULL,NULL,'individual','chd2'),(19,'ram hari',23,'ktm','female','395873929543','',NULL,NULL,NULL,NULL,NULL,'individual','ram'),(20,'dharan sahar',19,'kktm','male','37894529354','',NULL,NULL,NULL,NULL,NULL,'individual','slkjdfa'),(21,'fdlsk lsd',23,'alfjkds','female','527039842789','',NULL,NULL,NULL,NULL,NULL,'individual','afkshdl'),(22,'sita acharya',24,'bkt','female','98738535987','',NULL,NULL,NULL,NULL,NULL,'individual','sfjkd'),(23,'Radha niraula',23,'palpa','female','89374539897','',NULL,NULL,NULL,NULL,NULL,'individual','radha'),(24,'Radha niraula',23,'palpa','female','89374539897','',NULL,NULL,NULL,NULL,NULL,'individual','radh'),(25,'shyam hari',23,'ktm','male','75893042789345','',NULL,NULL,NULL,NULL,NULL,'individual','shyam'),(26,'khanal vai',34,'ktm','male','9357839875','',NULL,NULL,NULL,NULL,NULL,'individual','khanal'),(27,'Siva Mainali',23,'ktm','male','59783394578','',NULL,NULL,NULL,NULL,NULL,'individual','siva'),(28,'test tester',82,'ktm','female','993587434554','23423487678987',NULL,NULL,NULL,'2022-09-01',NULL,'individual','test'),(29,NULL,NULL,'ktm',NULL,'99834753458',NULL,'testorg','http://test.com','2023-08-22','2022-09-01',7,'corporate','testorg'),(30,'kushal kafle',23,'afhjkds','male','573489257432983','',NULL,NULL,NULL,NULL,NULL,'individual','fadls'),(31,'Buddha Lama',24,'Satungal','male','9818101010','',NULL,NULL,NULL,NULL,NULL,'individual','buddha123'),(32,'sadi acha',32,'fksdhj','female','89347584345','',NULL,NULL,NULL,NULL,NULL,'individual','sadi'),(33,'Santosh Tamang',21,'Nuwakot-13','male','9860000000','',NULL,NULL,NULL,NULL,NULL,'individual','Xic00000'),(34,'kushal kafle',19,'sldfk','male','987924334895','29783498234798345',NULL,NULL,NULL,NULL,NULL,'individual','email');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `invoice_id` int NOT NULL AUTO_INCREMENT,
  `actual_check_in_date` date DEFAULT NULL,
  `actual_check_out_date` date DEFAULT NULL,
  `service_charge` float DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  `discount_amount` float DEFAULT NULL,
  `payment_status` varchar(10) DEFAULT NULL,
  `booking_id` int NOT NULL,
  PRIMARY KEY (`invoice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,'2022-08-30','2022-09-04',200,40200,2814,'paid',11),(2,'2022-08-30','2022-09-05',200,48200,3374,'paid',13),(4,'2022-09-04','2022-09-05',200,7200,0,'paid',18),(5,'2022-09-04','2022-09-04',200,200,0,'paid',12),(6,'2022-09-05',NULL,200,NULL,NULL,'paid',11),(7,'2022-09-05','2022-09-05',200,200,14,'paid',14),(8,'2022-09-05','2022-09-05',200,200,14,'paid',27),(9,'2022-09-06','2022-09-06',200,200,0,'paid',28),(10,'2022-09-08','2022-09-08',200,200,0,'paid',25);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_details`
--

DROP TABLE IF EXISTS `login_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_details` (
  `user_name` varchar(15) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_role` varchar(20) NOT NULL,
  `email_address` varchar(75) NOT NULL,
  PRIMARY KEY (`user_name`),
  UNIQUE KEY `email_address` (`email_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_details`
--

LOCK TABLES `login_details` WRITE;
/*!40000 ALTER TABLE `login_details` DISABLE KEYS */;
INSERT INTO `login_details` VALUES ('afkshdl','asdfkjfh','individual','lsjadfk@ma.co'),('buddha123','buddha123','individual','lama@gmail.com'),('chd','chdasd','individual','chd@gmail.com'),('chd2','chd123','individual','chd2@gmail.com'),('chdasd','as;dfhjl','corporate','pcps1@gmail.com'),('email','email','individual','email@gmail.com'),('fadls','sfkj','individual','ajfldsk@v.com'),('khanal','1234','individual','khanal@gm.co'),('kritika','kritika123','receptionist','kritika@gmail.com'),('patan','patan123','corporate','pcps@gmail.com'),('radh','radha123','individual','radh@gmail.com'),('radha','radha123','individual','radha@gmail.com'),('ram','ssss','individual','ram@gmail.com'),('recept','recept123','receptionist','recept@gmail.com'),('sadi','sadi123','individual','sadi@m.co'),('sfdaj','fjadafds','corporate','ccc@gmail.com'),('sfjkd','sjkdf','individual','sdfj@gmial.com'),('shyam','1234','individual','shyam@gm.com'),('siva','chdff','individual','siva@gmail.com'),('slkjdfa','akfhjds','individual','chdw@gmail.com'),('supriya','supriya123','receptionist','supriya@gmail.com'),('test','test123','individual','test@gmail.com'),('testorg','test123','corporate','testorg@gmail.com'),('Xic00000','Xic00000','individual','st36150@gmail.com');
/*!40000 ALTER TABLE `login_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `room_no` int NOT NULL,
  `room_type` varchar(10) NOT NULL,
  `room_price` float NOT NULL,
  `room_telephone_no` varchar(10) NOT NULL,
  PRIMARY KEY (`room_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (101,'single',6000,'98346574'),(102,'single',6000,'8736423'),(103,'single',7000,'056787823'),(201,'double',8000,'056878790'),(202,'double',8000,'489732234'),(203,'double',8000,'657834345'),(204,'double',7000,'43978342'),(301,'twin',9000,'345345'),(302,'twin',10000,'984573'),(303,'twin',12000,'8374562');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `staff_id` int NOT NULL AUTO_INCREMENT,
  `staff_full_name` varchar(40) NOT NULL,
  `staff_address` varchar(30) NOT NULL,
  `staff_contact` varchar(15) NOT NULL,
  `staff_gender` varchar(10) NOT NULL,
  `staff_type` varchar(20) NOT NULL,
  `user_name` varchar(15) NOT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Kritika Mainali','Kathmandu','9876567687','female','receptionist','kritika'),(2,'Supriya Acharya','Lalitpur','9867665787','female','receptionist','supriya'),(3,'test recept','test address ','98676653556','male','receptionist','recept');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-08 17:15:09
