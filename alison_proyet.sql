CREATE DATABASE  IF NOT EXISTS `dbproyecto` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dbproyecto`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: dbproyecto
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `acceso`
--

DROP TABLE IF EXISTS `acceso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acceso` (
  `id_opcion` int NOT NULL,
  `id_rol` int NOT NULL,
  PRIMARY KEY (`id_opcion`,`id_rol`),
  CONSTRAINT `FK4ijjc5lpd7c6ly539scl4r79u` FOREIGN KEY (`id_opcion`) REFERENCES `opcion` (`id_opcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acceso`
--

LOCK TABLES `acceso` WRITE;
/*!40000 ALTER TABLE `acceso` DISABLE KEYS */;
INSERT INTO `acceso` VALUES (1,1),(1,2),(2,3),(3,4),(4,5);
/*!40000 ALTER TABLE `acceso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jefeprestamista`
--

DROP TABLE IF EXISTS `jefeprestamista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jefeprestamista` (
  `id_inversionista_creador` bigint NOT NULL,
  `id_jefe_prestamista` bigint NOT NULL,
  PRIMARY KEY (`id_inversionista_creador`,`id_jefe_prestamista`),
  KEY `FKessb2ulrakf8r2y3hu7x6rnjd` (`id_jefe_prestamista`),
  CONSTRAINT `FKessb2ulrakf8r2y3hu7x6rnjd` FOREIGN KEY (`id_jefe_prestamista`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKmnhi2vf6xj35aqtao7bpmwm36` FOREIGN KEY (`id_inversionista_creador`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jefeprestamista`
--

LOCK TABLES `jefeprestamista` WRITE;
/*!40000 ALTER TABLE `jefeprestamista` DISABLE KEYS */;
INSERT INTO `jefeprestamista` VALUES (4,8),(7,9),(7,36);
/*!40000 ALTER TABLE `jefeprestamista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `montos_prestamos`
--

DROP TABLE IF EXISTS `montos_prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `montos_prestamos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dias` int NOT NULL,
  `monto` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `montos_prestamos`
--

LOCK TABLES `montos_prestamos` WRITE;
/*!40000 ALTER TABLE `montos_prestamos` DISABLE KEYS */;
INSERT INTO `montos_prestamos` VALUES (1,15,154.11),(2,15,205.49),(3,15,308.23),(4,15,410.98),(5,15,513.72),(6,20,155.49),(7,20,207.32),(8,20,310.98),(9,20,414.64),(10,20,518.3),(11,25,156.86),(12,25,209.15),(13,25,313.72),(14,25,418.3),(15,25,522.88),(16,30,157.23),(17,30,210.98),(18,30,316.47),(19,30,421.96),(20,30,527.45),(21,35,159.61),(22,35,212.81),(23,35,319.22),(24,35,425.62),(25,35,532.03);
/*!40000 ALTER TABLE `montos_prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opcion`
--

DROP TABLE IF EXISTS `opcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opcion` (
  `id_opcion` int NOT NULL AUTO_INCREMENT,
  `estado` int NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `ruta` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_opcion`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opcion`
--

LOCK TABLES `opcion` WRITE;
/*!40000 ALTER TABLE `opcion` DISABLE KEYS */;
INSERT INTO `opcion` VALUES (1,1,'Mantenimiento Jefe Prestamista','vistaInversionista'),(2,1,'Mantenimiento Prestamista','vistaJefePrestamista'),(3,1,'Mantenimiento Prestatario','vistaPrestamista'),(4,1,'Solicutudes Prestamos','vistaPrestatario');
/*!40000 ALTER TABLE `opcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permiso`
--

DROP TABLE IF EXISTS `permiso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permiso` (
  `id_rol` bigint NOT NULL,
  `id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id_rol`,`id_usuario`),
  KEY `FK3v1dnvg313ij2p8xjusenvd8j` (`id_usuario`),
  CONSTRAINT `FK2ju5xye24yv09xqlrnlvd550x` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK3v1dnvg313ij2p8xjusenvd8j` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permiso`
--

LOCK TABLES `permiso` WRITE;
/*!40000 ALTER TABLE `permiso` DISABLE KEYS */;
INSERT INTO `permiso` VALUES (1,1),(2,4),(2,7),(3,8),(3,9),(4,33),(4,34),(4,35),(3,36),(5,38),(5,39);
/*!40000 ALTER TABLE `permiso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamista`
--

DROP TABLE IF EXISTS `prestamista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamista` (
  `id_jefe_prestamista_creador` bigint NOT NULL,
  `id_prestamista` bigint NOT NULL,
  PRIMARY KEY (`id_jefe_prestamista_creador`,`id_prestamista`),
  KEY `FKkw3shhq5fd7lbdtaeg1ai4rl1` (`id_prestamista`),
  CONSTRAINT `FK7eh3dvl5sj3i2ua8qlwu7f5rd` FOREIGN KEY (`id_jefe_prestamista_creador`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKkw3shhq5fd7lbdtaeg1ai4rl1` FOREIGN KEY (`id_prestamista`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamista`
--

LOCK TABLES `prestamista` WRITE;
/*!40000 ALTER TABLE `prestamista` DISABLE KEYS */;
INSERT INTO `prestamista` VALUES (8,35);
/*!40000 ALTER TABLE `prestamista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_registro` datetime(6) DEFAULT NULL,
  `monto` double NOT NULL,
  `monto_diario` double NOT NULL,
  `nro_cuotas` int NOT NULL,
  `tipo_cuotas` varchar(255) DEFAULT NULL,
  `id_prestatamista` bigint DEFAULT NULL,
  `id_prestatario` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKibuqjlxmr6hk007vdtn7wt7os` (`id_prestatamista`),
  KEY `FKt92m9hf03dfj5o2eykhotxxq1` (`id_prestatario`),
  CONSTRAINT `FKibuqjlxmr6hk007vdtn7wt7os` FOREIGN KEY (`id_prestatamista`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKt92m9hf03dfj5o2eykhotxxq1` FOREIGN KEY (`id_prestatario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
INSERT INTO `prestamos` VALUES (8,'APROBADO',NULL,'2024-05-22','2024-05-07',NULL,410.98,34.25,15,'DIARIO',35,38),(9,'APROBADO',NULL,'2024-05-28','2024-05-14',NULL,205.49,17.12,15,'DIARIO',35,38);
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestatario`
--

DROP TABLE IF EXISTS `prestatario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestatario` (
  `id_prestamista_creador` bigint NOT NULL,
  `id_prestatario` bigint NOT NULL,
  PRIMARY KEY (`id_prestamista_creador`,`id_prestatario`),
  KEY `FKm1rpa2j4cm8i7r8iyee1v0w2g` (`id_prestatario`),
  CONSTRAINT `FKc8g6vbr7l0qqvls8ogd6ctjpl` FOREIGN KEY (`id_prestamista_creador`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKm1rpa2j4cm8i7r8iyee1v0w2g` FOREIGN KEY (`id_prestatario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestatario`
--

LOCK TABLES `prestatario` WRITE;
/*!40000 ALTER TABLE `prestatario` DISABLE KEYS */;
INSERT INTO `prestatario` VALUES (35,38),(1,39);
/*!40000 ALTER TABLE `prestatario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'admin'),(2,'inversionista'),(3,'jefe prestamista'),(4,'prestamista'),(5,'prestatario');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `rol_id` bigint DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `id_zona` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqf5elo4jcq7qrt83oi0qmenjo` (`rol_id`),
  KEY `FKm08a7xkcijf10r1a27efxcqau` (`id_zona`),
  CONSTRAINT `FKm08a7xkcijf10r1a27efxcqau` FOREIGN KEY (`id_zona`) REFERENCES `zona` (`id`),
  CONSTRAINT `FKqf5elo4jcq7qrt83oi0qmenjo` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'apep apem','adm','adm@gmail.com','nomAdmin','9999','adm',1,'123123',1),(4,'app apm','inve','inv@gmail.com','Inve','2313123','inve',2,'231231',2),(7,'app apm','inva','LIN1@GMAIL.COM','Inva','1231222','inva',2,'87654322',2),(8,'kom','kim','kim@gmail.com','kim','987654321','kim',3,'87654324',2),(9,'kem','kam','kam@gmail.com','kam','987654321','kam',3,'87654329',2),(33,'Sanzhe inostrosa','bam','lucero@gmail.com','Lucero','987654321','bam',4,'87654326',3),(34,'Liom Liem','bim','liam@gmail.com','Liam','9876567','bim',4,'98765432',2),(35,'juanp juanm','juan','juan@gmail.com','juan','987687622','juan',4,'98765456',2),(36,'prueba prueba ','xzx','xaa@gmail.com','XZXprueba','123132312','xzx',3,'43342432',3),(38,'wamp wamm','wam','wam@gmail.com','wam','7534434','wam',5,'87656782',2),(39,'prueba','prueba','prueba@gmail.com','prueba','311123','prueba',5,'76543221',3);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zona`
--

DROP TABLE IF EXISTS `zona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zona` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zona`
--

LOCK TABLES `zona` WRITE;
/*!40000 ALTER TABLE `zona` DISABLE KEYS */;
INSERT INTO `zona` VALUES (1,'Miraflores'),(2,'Surco'),(3,'Centro Lima');
/*!40000 ALTER TABLE `zona` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-08 15:54:59
