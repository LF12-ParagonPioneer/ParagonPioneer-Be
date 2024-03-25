-- MySQL dump 10.13  Distrib 8.2.0, for macos14.0 (arm64)
--
-- Host: 127.0.0.1    Database: ppAssemblyLineBa
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
-- Table structure for table `admin`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `created_at` datetime(6) NOT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('Admin') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c0r9atamxvbhjjvy5j8da1kam` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `building`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `building` (
  `capacity` int DEFAULT NULL,
  `production_per_minute` float DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `recipe` binary(16) DEFAULT NULL,
  `dtype` varchar(31) NOT NULL,
  `name` varchar(255) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `slug` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dhln7gqbq9qt5lf8a28d0fy99` (`slug`),
  UNIQUE KEY `UK_ntxjs38j4ae4ewq5w8p915ij3` (`recipe`),
  CONSTRAINT `FKoqb8j1mlnygdpoj0gemfh3jls` FOREIGN KEY (`recipe`) REFERENCES `recipe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cost_building_goods`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cost_building_goods` (
  `amount` int DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `building_id` binary(16) DEFAULT NULL,
  `good_id` binary(16) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp6middi6ytdu6swae7vij962a` (`building_id`),
  KEY `FKsbw6dqvuqebpntsjumc7g7mmt` (`good_id`),
  CONSTRAINT `FKp6middi6ytdu6swae7vij962a` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`),
  CONSTRAINT `FKsbw6dqvuqebpntsjumc7g7mmt` FOREIGN KEY (`good_id`) REFERENCES `good` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `good`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `good` (
  `is_map_resource` tinyint(1) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `name` varchar(255) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `slug` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nbh6tb7e9lyj7i7ouk9xqwqdg` (`name`),
  UNIQUE KEY `UK_s6la9h5sjmb40l2ldyrdgrvum` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `population`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `population` (
  `created_at` datetime(6) NOT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m9q869h9yj6e396uevaj0grdp` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `population_requirement`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `population_requirement` (
  `consumption` float DEFAULT NULL,
  `is_basic` bit(1) DEFAULT NULL,
  `produce` float DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `good_id` binary(16) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `population_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbcr71dxrg9y3h2av04oasti89` (`good_id`),
  KEY `FKanqh1no2fb1ouj1cup4sbi6hb` (`population_id`),
  CONSTRAINT `FKanqh1no2fb1ouj1cup4sbi6hb` FOREIGN KEY (`population_id`) REFERENCES `population` (`id`),
  CONSTRAINT `FKbcr71dxrg9y3h2av04oasti89` FOREIGN KEY (`good_id`) REFERENCES `good` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recipe`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `quantity_of_input_1` int DEFAULT NULL,
  `quantity_of_input_10` int DEFAULT NULL,
  `quantity_of_input_2` int DEFAULT NULL,
  `quantity_of_input_3` int DEFAULT NULL,
  `quantity_of_input_4` int DEFAULT NULL,
  `quantity_of_input_5` int DEFAULT NULL,
  `quantity_of_input_6` int DEFAULT NULL,
  `quantity_of_input_7` int DEFAULT NULL,
  `quantity_of_input_8` int DEFAULT NULL,
  `quantity_of_input_9` int DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `input_1` binary(16) DEFAULT NULL,
  `input_10` binary(16) DEFAULT NULL,
  `input_2` binary(16) DEFAULT NULL,
  `input_3` binary(16) DEFAULT NULL,
  `input_4` binary(16) DEFAULT NULL,
  `input_5` binary(16) DEFAULT NULL,
  `input_6` binary(16) DEFAULT NULL,
  `input_7` binary(16) DEFAULT NULL,
  `input_8` binary(16) DEFAULT NULL,
  `input_9` binary(16) DEFAULT NULL,
  `output` binary(16) DEFAULT NULL,
  `production_building` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_f4dq4cgv6n33926akdk7gp20s` (`production_building`),
  KEY `FKk2dnvk7u98noftg1hx6k76i62` (`input_1`),
  KEY `FKoslhn6r9w7ln2o3cs6k3lp7qw` (`input_10`),
  KEY `FKr1w9rj2jdk3te3bdfbk42lc7y` (`input_2`),
  KEY `FKq698ebgk1yrcrttqcuxtyiukc` (`input_3`),
  KEY `FKl9pixcpqdeytt2l6y34xh82ti` (`input_4`),
  KEY `FK1s1ofha5wphx7x3shtuewr8tg` (`input_5`),
  KEY `FK5mwifdj5qx2fsxinpo8wv3ujk` (`input_6`),
  KEY `FKaf9qawkonlo2biqrfmaqkgnac` (`input_7`),
  KEY `FK601j7ligefitu7kjynnjjkful` (`input_8`),
  KEY `FKldrekctvk1mxgc90yef310boa` (`input_9`),
  KEY `FKdr36q569c4aw810t4w7v8rl5v` (`output`),
  CONSTRAINT `FK1s1ofha5wphx7x3shtuewr8tg` FOREIGN KEY (`input_5`) REFERENCES `good` (`id`),
  CONSTRAINT `FK5mwifdj5qx2fsxinpo8wv3ujk` FOREIGN KEY (`input_6`) REFERENCES `good` (`id`),
  CONSTRAINT `FK601j7ligefitu7kjynnjjkful` FOREIGN KEY (`input_8`) REFERENCES `good` (`id`),
  CONSTRAINT `FKaf9qawkonlo2biqrfmaqkgnac` FOREIGN KEY (`input_7`) REFERENCES `good` (`id`),
  CONSTRAINT `FKdr36q569c4aw810t4w7v8rl5v` FOREIGN KEY (`output`) REFERENCES `good` (`id`),
  CONSTRAINT `FKk2dnvk7u98noftg1hx6k76i62` FOREIGN KEY (`input_1`) REFERENCES `good` (`id`),
  CONSTRAINT `FKl9pixcpqdeytt2l6y34xh82ti` FOREIGN KEY (`input_4`) REFERENCES `good` (`id`),
  CONSTRAINT `FKldrekctvk1mxgc90yef310boa` FOREIGN KEY (`input_9`) REFERENCES `good` (`id`),
  CONSTRAINT `FKoslhn6r9w7ln2o3cs6k3lp7qw` FOREIGN KEY (`input_10`) REFERENCES `good` (`id`),
  CONSTRAINT `FKq698ebgk1yrcrttqcuxtyiukc` FOREIGN KEY (`input_3`) REFERENCES `good` (`id`),
  CONSTRAINT `FKr1w9rj2jdk3te3bdfbk42lc7y` FOREIGN KEY (`input_2`) REFERENCES `good` (`id`),
  CONSTRAINT `FKrlc0eox7eht7lcydxamlduwrh` FOREIGN KEY (`production_building`) REFERENCES `building` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `requirement_population_building`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requirement_population_building` (
  `amount` int DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `building_id` binary(16) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `population_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h2xp8yfvhi2ygiaa5a03ksqb2` (`building_id`),
  KEY `FK4jrfuw2qd2g453dbwe827d89i` (`population_id`),
  CONSTRAINT `FK4jrfuw2qd2g453dbwe827d89i` FOREIGN KEY (`population_id`) REFERENCES `population` (`id`),
  CONSTRAINT `FKfax1aoc78u08wa8klwl1v9mfw` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-25 15:57:26
