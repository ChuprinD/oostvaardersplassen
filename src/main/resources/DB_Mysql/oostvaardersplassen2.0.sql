-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: oostvaardersplassen
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animal` (
  `Year` int NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Amount` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal`
--

LOCK TABLES `animal` WRITE;
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` VALUES (1990,'Heck cattle',30),(1991,'Heck cattle',30),(1992,'Heck cattle',35),(1993,'Heck cattle',35),(1994,'Heck cattle',30),(1995,'Heck cattle',60),(1996,'Heck cattle',80),(1997,'Heck cattle',115),(1998,'Heck cattle',135),(1999,'Heck cattle',170),(2000,'Heck cattle',195),(2001,'Heck cattle',240),(2002,'Heck cattle',280),(2003,'Heck cattle',340),(2004,'Heck cattle',390),(2005,'Heck cattle',450),(2006,'Heck cattle',500),(2007,'Heck cattle',510),(2008,'Heck cattle',580),(2009,'Heck cattle',580),(2010,'Heck cattle',640),(2011,'Heck cattle',589),(2012,'Heck cattle',680),(2013,'Heck cattle',550),(2014,'Heck cattle',480),(2015,'Heck cattle',450),(2016,'Heck cattle',450),(2017,'Heck cattle',400),(2018,'Heck cattle',320),(2019,'Heck cattle',350),(2020,'Heck cattle',310),(2021,'Heck cattle',200),(2022,'Heck cattle',250),(1991,'Konik horses',20),(1992,'Konik horses',20),(1993,'Konik horses',20),(1994,'Konik horses',30),(1995,'Konik horses',35),(1996,'Konik horses',45),(1997,'Konik horses',55),(1998,'Konik horses',70),(1999,'Konik horses',85),(2000,'Konik horses',120),(2001,'Konik horses',160),(2002,'Konik horses',200),(2003,'Konik horses',240),(2004,'Konik horses',280),(2005,'Konik horses',330),(2006,'Konik horses',390),(2007,'Konik horses',450),(2008,'Konik horses',520),(2009,'Konik horses',610),(2010,'Konik horses',700),(2011,'Konik horses',800),(2012,'Konik horses',900),(2013,'Konik horses',950),(2014,'Konik horses',1000),(2015,'Konik horses',1100),(2016,'Konik horses',1150),(2017,'Konik horses',1150),(2018,'Konik horses',1050),(2019,'Konik horses',1150),(2020,'Konik horses',1150),(2021,'Konik horses',990),(2022,'Konik horses',1250),(1999,'Red deer',45),(2000,'Red deer',70),(2001,'Red deer',100),(2002,'Red deer',140),(2003,'Red deer',180),(2004,'Red deer',240),(2005,'Red deer',300),(2006,'Red deer',380),(2007,'Red deer',460),(2008,'Red deer',590),(2009,'Red deer',770),(2010,'Red deer',1000),(2011,'Red deer',1150),(2012,'Red deer',1400),(2013,'Red deer',1700),(2014,'Red deer',2000),(2015,'Red deer',2200),(2016,'Red deer',2700),(2017,'Red deer',3000),(2018,'Red deer',3050),(2019,'Red deer',3250),(2020,'Red deer',3200),(2021,'Red deer',2500),(2022,'Red deer',3200);
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animal_birth`
--

DROP TABLE IF EXISTS `animal_birth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animal_birth` (
  `Year` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Amount` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal_birth`
--

LOCK TABLES `animal_birth` WRITE;
/*!40000 ALTER TABLE `animal_birth` DISABLE KEYS */;
INSERT INTO `animal_birth` VALUES (1990,'Heck cattle',0),(1990,'Konik horses',0),(1990,'Red deer',0),(1991,'Heck cattle',0),(1991,'Konik horses',0),(1991,'Red deer',0),(1992,'Heck cattle',10),(1992,'Konik horses',0),(1992,'Red deer',0),(1993,'Heck cattle',15),(1993,'Konik horses',5),(1993,'Red deer',0),(1994,'Heck cattle',5),(1994,'Konik horses',5),(1994,'Red deer',0),(1995,'Heck cattle',5),(1995,'Konik horses',0),(1995,'Red deer',0),(1996,'Heck cattle',10),(1996,'Konik horses',0),(1996,'Red deer',0),(1997,'Heck cattle',10),(1997,'Konik horses',0),(1997,'Red deer',0),(1998,'Heck cattle',10),(1998,'Konik horses',5),(1998,'Red deer',0),(1999,'Heck cattle',15),(1999,'Konik horses',10),(1999,'Red deer',5),(2000,'Heck cattle',15),(2000,'Konik horses',5),(2000,'Red deer',5),(2001,'Heck cattle',30),(2001,'Konik horses',15),(2001,'Red deer',0),(2002,'Heck cattle',10),(2002,'Konik horses',0),(2002,'Red deer',5),(2003,'Heck cattle',25),(2003,'Konik horses',15),(2003,'Red deer',5),(2004,'Heck cattle',45),(2004,'Konik horses',10),(2004,'Red deer',10),(2005,'Heck cattle',45),(2005,'Konik horses',15),(2005,'Red deer',0),(2006,'Heck cattle',95),(2006,'Konik horses',20),(2006,'Red deer',15),(2007,'Heck cattle',45),(2007,'Konik horses',10),(2007,'Red deer',15),(2008,'Heck cattle',115),(2008,'Konik horses',30),(2008,'Red deer',25),(2009,'Heck cattle',65),(2009,'Konik horses',45),(2009,'Red deer',25),(2010,'Heck cattle',165),(2010,'Konik horses',80),(2010,'Red deer',135),(2011,'Heck cattle',45),(2011,'Konik horses',110),(2011,'Red deer',125),(2012,'Heck cattle',270),(2012,'Konik horses',150),(2012,'Red deer',500),(2013,'Heck cattle',240),(2013,'Konik horses',330),(2013,'Red deer',500),(2014,'Heck cattle',150),(2014,'Konik horses',444),(2014,'Red deer',550),(2015,'Heck cattle',80),(2015,'Konik horses',300),(2015,'Red deer',580),(2016,'Heck cattle',220),(2016,'Konik horses',500),(2016,'Red deer',950),(2017,'Heck cattle',230),(2017,'Konik horses',530),(2017,'Red deer',1050),(2018,'Heck cattle',100),(2018,'Konik horses',275),(2018,'Red deer',1200),(2019,'Heck cattle',200),(2019,'Konik horses',600),(2019,'Red deer',1850),(2020,'Heck cattle',230),(2020,'Konik horses',530),(2020,'Red deer',2100),(2021,'Heck cattle',0),(2021,'Konik horses',15),(2021,'Red deer',200),(2022,'Heck cattle',80),(2022,'Konik horses',380),(2022,'Red deer',970);
/*!40000 ALTER TABLE `animal_birth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animal_mortality`
--

DROP TABLE IF EXISTS `animal_mortality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animal_mortality` (
  `Year` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Amount` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal_mortality`
--

LOCK TABLES `animal_mortality` WRITE;
/*!40000 ALTER TABLE `animal_mortality` DISABLE KEYS */;
INSERT INTO `animal_mortality` VALUES (1990,'Heck cattle',0),(1990,'Konik horses',0),(1990,'Red deer',0),(1991,'Heck cattle',0),(1991,'Konik horses',0),(1991,'Red deer',0),(1992,'Heck cattle',10),(1992,'Konik horses',0),(1992,'Red deer',0),(1993,'Heck cattle',15),(1993,'Konik horses',5),(1993,'Red deer',0),(1994,'Heck cattle',5),(1994,'Konik horses',5),(1994,'Red deer',0),(1995,'Heck cattle',5),(1995,'Konik horses',0),(1995,'Red deer',0),(1996,'Heck cattle',10),(1996,'Konik horses',0),(1996,'Red deer',0),(1997,'Heck cattle',10),(1997,'Konik horses',0),(1997,'Red deer',0),(1998,'Heck cattle',10),(1998,'Konik horses',5),(1998,'Red deer',0),(1999,'Heck cattle',15),(1999,'Konik horses',10),(1999,'Red deer',5),(2000,'Heck cattle',15),(2000,'Konik horses',5),(2000,'Red deer',5),(2001,'Heck cattle',30),(2001,'Konik horses',15),(2001,'Red deer',0),(2002,'Heck cattle',10),(2002,'Konik horses',0),(2002,'Red deer',5),(2003,'Heck cattle',25),(2003,'Konik horses',15),(2003,'Red deer',5),(2004,'Heck cattle',45),(2004,'Konik horses',10),(2004,'Red deer',10),(2005,'Heck cattle',45),(2005,'Konik horses',15),(2005,'Red deer',0),(2006,'Heck cattle',95),(2006,'Konik horses',20),(2006,'Red deer',15),(2007,'Heck cattle',45),(2007,'Konik horses',10),(2007,'Red deer',15),(2008,'Heck cattle',115),(2008,'Konik horses',30),(2008,'Red deer',25),(2009,'Heck cattle',65),(2009,'Konik horses',45),(2009,'Red deer',25),(2010,'Heck cattle',165),(2010,'Konik horses',80),(2010,'Red deer',135),(2011,'Heck cattle',45),(2011,'Konik horses',110),(2011,'Red deer',125),(2012,'Heck cattle',270),(2012,'Konik horses',150),(2012,'Red deer',500),(2013,'Heck cattle',240),(2013,'Konik horses',330),(2013,'Red deer',500),(2014,'Heck cattle',150),(2014,'Konik horses',444),(2014,'Red deer',550),(2015,'Heck cattle',80),(2015,'Konik horses',300),(2015,'Red deer',580),(2016,'Heck cattle',220),(2016,'Konik horses',500),(2016,'Red deer',950),(2017,'Heck cattle',230),(2017,'Konik horses',530),(2017,'Red deer',1050),(2018,'Heck cattle',100),(2018,'Konik horses',275),(2018,'Red deer',1200),(2019,'Heck cattle',200),(2019,'Konik horses',600),(2019,'Red deer',1850),(2020,'Heck cattle',230),(2020,'Konik horses',530),(2020,'Red deer',2100),(2021,'Heck cattle',0),(2021,'Konik horses',15),(2021,'Red deer',200),(2022,'Heck cattle',80),(2022,'Konik horses',380),(2022,'Red deer',970);
/*!40000 ALTER TABLE `animal_mortality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `designation`
--

DROP TABLE IF EXISTS `designation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `designation` (
  `code` int NOT NULL AUTO_INCREMENT,
  `title` char(35) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `designation`
--

LOCK TABLES `designation` WRITE;
/*!40000 ALTER TABLE `designation` DISABLE KEYS */;
/*!40000 ALTER TABLE `designation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed`
--

DROP TABLE IF EXISTS `feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feed` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(150) NOT NULL,
  `Type` varchar(150) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed`
--

LOCK TABLES `feed` WRITE;
/*!40000 ALTER TABLE `feed` DISABLE KEYS */;
/*!40000 ALTER TABLE `feed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grass`
--

DROP TABLE IF EXISTS `grass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grass` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Year` int DEFAULT NULL,
  `Height_CM` float DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grass`
--

LOCK TABLES `grass` WRITE;
/*!40000 ALTER TABLE `grass` DISABLE KEYS */;
INSERT INTO `grass` VALUES (1,1998,28.79,'Grass'),(2,1999,27.07,'Grass'),(3,2000,27.28,'Grass'),(4,2001,26.38,'Grass'),(5,2002,25.67,'Grass'),(6,2003,25.47,'Grass'),(7,2004,22.16,'Grass'),(8,2005,23.45,'Grass'),(9,2006,23.98,'Grass'),(10,2007,22.75,'Grass'),(11,2008,22.93,'Grass'),(12,2009,21.92,'Grass'),(13,2010,22.67,'Grass'),(14,2011,18.14,'Grass'),(15,2012,18.32,'Grass'),(16,2013,19.14,'Grass'),(17,2014,19.77,'Grass'),(18,2015,19.45,'Grass'),(19,2016,18.14,'Grass'),(20,2017,18.05,'Grass'),(21,2018,16.93,'Grass'),(22,2019,17.84,'Grass'),(23,2020,16.49,'Grass'),(24,2021,15.25,'Grass'),(25,2022,15.01,'Grass');
/*!40000 ALTER TABLE `grass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habitat`
--

DROP TABLE IF EXISTS `habitat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habitat` (
  `Pen_Number` int unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(150) NOT NULL,
  `Location` varchar(150) NOT NULL,
  `Capacity` varchar(150) NOT NULL,
  `Descr` varchar(150) NOT NULL,
  PRIMARY KEY (`Pen_Number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitat`
--

LOCK TABLES `habitat` WRITE;
/*!40000 ALTER TABLE `habitat` DISABLE KEYS */;
/*!40000 ALTER TABLE `habitat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `species`
--

DROP TABLE IF EXISTS `species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `species` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(150) NOT NULL,
  `Type` varchar(150) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `species`
--

LOCK TABLES `species` WRITE;
/*!40000 ALTER TABLE `species` DISABLE KEYS */;
/*!40000 ALTER TABLE `species` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weather`
--

DROP TABLE IF EXISTS `weather`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weather` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(150) NOT NULL,
  `Tempreture` int unsigned NOT NULL,
  `Affect` varchar(150) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weather`
--

LOCK TABLES `weather` WRITE;
/*!40000 ALTER TABLE `weather` DISABLE KEYS */;
/*!40000 ALTER TABLE `weather` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-09 19:16:02
