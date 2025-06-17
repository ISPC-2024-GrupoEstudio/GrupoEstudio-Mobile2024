CREATE DATABASE  IF NOT EXISTS `pet_boutique` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pet_boutique`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: pet_boutique
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `auth_group`
--

DROP TABLE IF EXISTS `auth_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group`
--

LOCK TABLES `auth_group` WRITE;
/*!40000 ALTER TABLE `auth_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_group_permissions`
--

DROP TABLE IF EXISTS `auth_group_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_group_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group_permissions`
--

LOCK TABLES `auth_group_permissions` WRITE;
/*!40000 ALTER TABLE `auth_group_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_permission`
--

DROP TABLE IF EXISTS `auth_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_permission`
--

LOCK TABLES `auth_permission` WRITE;
/*!40000 ALTER TABLE `auth_permission` DISABLE KEYS */;
INSERT INTO `auth_permission` VALUES (1,'Can add log entry',1,'add_logentry'),(2,'Can change log entry',1,'change_logentry'),(3,'Can delete log entry',1,'delete_logentry'),(4,'Can view log entry',1,'view_logentry'),(5,'Can add permission',2,'add_permission'),(6,'Can change permission',2,'change_permission'),(7,'Can delete permission',2,'delete_permission'),(8,'Can view permission',2,'view_permission'),(9,'Can add group',3,'add_group'),(10,'Can change group',3,'change_group'),(11,'Can delete group',3,'delete_group'),(12,'Can view group',3,'view_group'),(13,'Can add user',4,'add_user'),(14,'Can change user',4,'change_user'),(15,'Can delete user',4,'delete_user'),(16,'Can view user',4,'view_user'),(17,'Can add content type',5,'add_contenttype'),(18,'Can change content type',5,'change_contenttype'),(19,'Can delete content type',5,'delete_contenttype'),(20,'Can view content type',5,'view_contenttype'),(21,'Can add session',6,'add_session'),(22,'Can change session',6,'change_session'),(23,'Can delete session',6,'delete_session'),(24,'Can view session',6,'view_session'),(25,'Can add categoriaproductos',7,'add_categoriaproductos'),(26,'Can change categoriaproductos',7,'change_categoriaproductos'),(27,'Can delete categoriaproductos',7,'delete_categoriaproductos'),(28,'Can view categoriaproductos',7,'view_categoriaproductos'),(29,'Can add estadopedido',8,'add_estadopedido'),(30,'Can change estadopedido',8,'change_estadopedido'),(31,'Can delete estadopedido',8,'delete_estadopedido'),(32,'Can view estadopedido',8,'view_estadopedido'),(33,'Can add formadepago',9,'add_formadepago'),(34,'Can change formadepago',9,'change_formadepago'),(35,'Can delete formadepago',9,'delete_formadepago'),(36,'Can view formadepago',9,'view_formadepago'),(37,'Can add pedidos',10,'add_pedidos'),(38,'Can change pedidos',10,'change_pedidos'),(39,'Can delete pedidos',10,'delete_pedidos'),(40,'Can view pedidos',10,'view_pedidos'),(41,'Can add proveedores',11,'add_proveedores'),(42,'Can change proveedores',11,'change_proveedores'),(43,'Can delete proveedores',11,'delete_proveedores'),(44,'Can view proveedores',11,'view_proveedores'),(45,'Can add roles',12,'add_roles'),(46,'Can change roles',12,'change_roles'),(47,'Can delete roles',12,'delete_roles'),(48,'Can view roles',12,'view_roles'),(49,'Can add tipodoc',13,'add_tipodoc'),(50,'Can change tipodoc',13,'change_tipodoc'),(51,'Can delete tipodoc',13,'delete_tipodoc'),(52,'Can view tipodoc',13,'view_tipodoc'),(53,'Can add tipoenvio',14,'add_tipoenvio'),(54,'Can change tipoenvio',14,'change_tipoenvio'),(55,'Can delete tipoenvio',14,'delete_tipoenvio'),(56,'Can view tipoenvio',14,'view_tipoenvio'),(57,'Can add productosxcarrito',15,'add_productosxcarrito'),(58,'Can change productosxcarrito',15,'change_productosxcarrito'),(59,'Can delete productosxcarrito',15,'delete_productosxcarrito'),(60,'Can view productosxcarrito',15,'view_productosxcarrito'),(61,'Can add usuarios',16,'add_usuarios'),(62,'Can change usuarios',16,'change_usuarios'),(63,'Can delete usuarios',16,'delete_usuarios'),(64,'Can view usuarios',16,'view_usuarios'),(65,'Can add productos',17,'add_productos'),(66,'Can change productos',17,'change_productos'),(67,'Can delete productos',17,'delete_productos'),(68,'Can view productos',17,'view_productos'),(69,'Can add productosxpedido',18,'add_productosxpedido'),(70,'Can change productosxpedido',18,'change_productosxpedido'),(71,'Can delete productosxpedido',18,'delete_productosxpedido'),(72,'Can view productosxpedido',18,'view_productosxpedido'),(73,'Can add productosxventa',19,'add_productosxventa'),(74,'Can change productosxventa',19,'change_productosxventa'),(75,'Can delete productosxventa',19,'delete_productosxventa'),(76,'Can view productosxventa',19,'view_productosxventa'),(77,'Can add ventas',20,'add_ventas'),(78,'Can change ventas',20,'change_ventas'),(79,'Can delete ventas',20,'delete_ventas'),(80,'Can view ventas',20,'view_ventas'),(81,'Can add auth group',25,'add_authgroup'),(82,'Can change auth group',25,'change_authgroup'),(83,'Can delete auth group',25,'delete_authgroup'),(84,'Can view auth group',25,'view_authgroup'),(85,'Can add auth group permissions',26,'add_authgrouppermissions'),(86,'Can change auth group permissions',26,'change_authgrouppermissions'),(87,'Can delete auth group permissions',26,'delete_authgrouppermissions'),(88,'Can view auth group permissions',26,'view_authgrouppermissions'),(89,'Can add auth permission',27,'add_authpermission'),(90,'Can change auth permission',27,'change_authpermission'),(91,'Can delete auth permission',27,'delete_authpermission'),(92,'Can view auth permission',27,'view_authpermission'),(93,'Can add auth user',28,'add_authuser'),(94,'Can change auth user',28,'change_authuser'),(95,'Can delete auth user',28,'delete_authuser'),(96,'Can view auth user',28,'view_authuser'),(97,'Can add auth user groups',29,'add_authusergroups'),(98,'Can change auth user groups',29,'change_authusergroups'),(99,'Can delete auth user groups',29,'delete_authusergroups'),(100,'Can view auth user groups',29,'view_authusergroups'),(101,'Can add auth user user permissions',30,'add_authuseruserpermissions'),(102,'Can change auth user user permissions',30,'change_authuseruserpermissions'),(103,'Can delete auth user user permissions',30,'delete_authuseruserpermissions'),(104,'Can view auth user user permissions',30,'view_authuseruserpermissions'),(105,'Can add carrito',31,'add_carrito'),(106,'Can change carrito',31,'change_carrito'),(107,'Can delete carrito',31,'delete_carrito'),(108,'Can view carrito',31,'view_carrito'),(109,'Can add categoria producto',32,'add_categoriaproducto'),(110,'Can change categoria producto',32,'change_categoriaproducto'),(111,'Can delete categoria producto',32,'delete_categoriaproducto'),(112,'Can view categoria producto',32,'view_categoriaproducto'),(113,'Can add django admin log',33,'add_djangoadminlog'),(114,'Can change django admin log',33,'change_djangoadminlog'),(115,'Can delete django admin log',33,'delete_djangoadminlog'),(116,'Can view django admin log',33,'view_djangoadminlog'),(117,'Can add django content type',34,'add_djangocontenttype'),(118,'Can change django content type',34,'change_djangocontenttype'),(119,'Can delete django content type',34,'delete_djangocontenttype'),(120,'Can view django content type',34,'view_djangocontenttype'),(121,'Can add django migrations',35,'add_djangomigrations'),(122,'Can change django migrations',35,'change_djangomigrations'),(123,'Can delete django migrations',35,'delete_djangomigrations'),(124,'Can view django migrations',35,'view_djangomigrations'),(125,'Can add django session',36,'add_djangosession'),(126,'Can change django session',36,'change_djangosession'),(127,'Can delete django session',36,'delete_djangosession'),(128,'Can view django session',36,'view_djangosession'),(129,'Can add pedido',37,'add_pedido'),(130,'Can change pedido',37,'change_pedido'),(131,'Can delete pedido',37,'delete_pedido'),(132,'Can view pedido',37,'view_pedido'),(133,'Can add producto',21,'add_producto'),(134,'Can change producto',21,'change_producto'),(135,'Can delete producto',21,'delete_producto'),(136,'Can view producto',21,'view_producto'),(137,'Can add producto x pedido',38,'add_productoxpedido'),(138,'Can change producto x pedido',38,'change_productoxpedido'),(139,'Can delete producto x pedido',38,'delete_productoxpedido'),(140,'Can view producto x pedido',38,'view_productoxpedido'),(141,'Can add producto x venta',39,'add_productoxventa'),(142,'Can change producto x venta',39,'change_productoxventa'),(143,'Can delete producto x venta',39,'delete_productoxventa'),(144,'Can view producto x venta',39,'view_productoxventa'),(145,'Can add proveedor',40,'add_proveedor'),(146,'Can change proveedor',40,'change_proveedor'),(147,'Can delete proveedor',40,'delete_proveedor'),(148,'Can view proveedor',40,'view_proveedor'),(149,'Can add rol',22,'add_rol'),(150,'Can change rol',22,'change_rol'),(151,'Can delete rol',22,'delete_rol'),(152,'Can view rol',22,'view_rol'),(153,'Can add tipo documento',23,'add_tipodocumento'),(154,'Can change tipo documento',23,'change_tipodocumento'),(155,'Can delete tipo documento',23,'delete_tipodocumento'),(156,'Can view tipo documento',23,'view_tipodocumento'),(157,'Can add usuario',24,'add_usuario'),(158,'Can change usuario',24,'change_usuario'),(159,'Can delete usuario',24,'delete_usuario'),(160,'Can view usuario',24,'view_usuario'),(161,'Can add venta',41,'add_venta'),(162,'Can change venta',41,'change_venta'),(163,'Can delete venta',41,'delete_venta'),(164,'Can view venta',41,'view_venta'),(165,'Can add Cupón',42,'add_cupon'),(166,'Can change Cupón',42,'change_cupon'),(167,'Can delete Cupón',42,'delete_cupon'),(168,'Can view Cupón',42,'view_cupon'),(169,'Can add custom user',43,'add_customuser'),(170,'Can change custom user',43,'change_customuser'),(171,'Can delete custom user',43,'delete_customuser'),(172,'Can view custom user',43,'view_customuser'),(173,'Can add usuario cupon',44,'add_usuariocupon'),(174,'Can change usuario cupon',44,'change_usuariocupon'),(175,'Can delete usuario cupon',44,'delete_usuariocupon'),(176,'Can view usuario cupon',44,'view_usuariocupon'),(177,'Can add cupon usuario',45,'add_cuponusuario'),(178,'Can change cupon usuario',45,'change_cuponusuario'),(179,'Can delete cupon usuario',45,'delete_cuponusuario'),(180,'Can view cupon usuario',45,'view_cuponusuario'),(181,'Can add arrepentimiento',46,'add_arrepentimiento'),(182,'Can change arrepentimiento',46,'change_arrepentimiento'),(183,'Can delete arrepentimiento',46,'delete_arrepentimiento'),(184,'Can view arrepentimiento',46,'view_arrepentimiento');
/*!40000 ALTER TABLE `auth_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  `telefono` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
INSERT INTO `auth_user` VALUES (1,'pbkdf2_sha256$600000$BmaOyS8u0DmYzloEcHhA76$Vu+vEccgqjgD8QDiNcLynXQTEBemOxDoVzl+mtFaB/c=','2024-06-09 23:00:29.254879',1,'admin','','','ea.samsam@gmail.com',1,1,'2024-05-14 00:53:14.233295',NULL),(2,'pbkdf2_sha256$600000$eyB9dYwVYYodufs4QFTzHr$7xXtzA5c65B2YvyN3lhwN9MkHDvIj0MODmqZMqgouNI=','2024-06-04 03:22:12.816197',1,'superusuario','','','superusuario@mail.com',1,1,'2024-06-03 23:44:29.429245',NULL),(3,'pbkdf2_sha256$600000$zrkcbuDDZKTedHPtiuwh4i$4jS3iNmIr1iP3EMaTQG+OgsUTkV6+sXPEMwMuIYrfVI=',NULL,0,'migue','','','',0,1,'2024-06-03 23:46:09.689472',NULL),(4,'pbkdf2_sha256$600000$EeFB2w4aapVeq3H0DgeTQ4$MvV8uoqPEk3z8kwqyA8p66m0vd0//FpzyvwW9RF1jts=',NULL,0,'flor','flor','flor','flor@mail.com',0,1,'2024-06-04 00:32:40.461467',NULL),(5,'pbkdf2_sha256$600000$WG0RmOL1aO9aZEwm1zepcc$CUMwBEPxv2HQ6mdRK9fuE99nZAvoZhsd5g9HIV9lQdg=',NULL,0,'flor1','flor1','flor1','flor1@mail.com',0,1,'2024-06-04 00:36:32.181809',NULL),(6,'pbkdf2_sha256$600000$afbvXs8anBIWFxnzr9OVgs$sVNjP4CKb1zubTQ+mr1g2BzhDPSlaO21SBO7F2F5gEw=','2024-06-04 03:29:53.657443',0,'changuito4','changuito4','changuito4','changuito4@mail.com',0,1,'2024-06-04 01:07:23.802085',NULL),(7,'pbkdf2_sha256$600000$rxWUYDNjJQkxvGKpJHG3gz$MT5rEYfAsXY7TYeXSTd6qhc/zsHNPzlU98pgajhm3gI=','2024-06-14 21:40:44.122462',0,'liomessi','liomessi','liomessi','liomessi@gmail.com',0,1,'2024-06-14 01:11:58.218342',NULL),(8,'pbkdf2_sha256$600000$6Us97FguVPAHeQPFjof8c8$1yVa550cmrfPeNCS+mHr5+khzcd9CWmLsOUERr8gdzM=','2024-06-14 23:51:52.958743',0,'pepesanchez','Pepe','Sanchez','pepesanchez@gmail.com',0,1,'2024-06-14 23:43:52.889086',NULL),(9,'pbkdf2_sha256$600000$Q0eHhSrMYlki2ZAPRztItW$HndAg8P0jsBIk+6rpIvttjjoPhfnDSIVd+m32a607dY=',NULL,0,'florcitaa','Flor','Carrillo','florencianoelcarrillo@gmail.com',0,1,'2024-09-17 22:19:39.840150',NULL),(10,'pbkdf2_sha256$600000$sz7VdkOyFhp2kaZEQ99EOs$EjipUrFwtPgQyySlS4BMq0vHGfyX91dVVKiE87HJ97A=','2024-09-18 00:39:49.213694',0,'Flor007','Flor','C','florencianoelcarrillo@gmail.com',0,1,'2024-09-17 22:20:53.055645',NULL),(11,'pbkdf2_sha256$600000$PH1cY7OVuofDzkS3b7LwHH$h+1tJR7r5Qqj4V0j5YmbOgsLvr9Qac3ulL24ME/Zxkw=',NULL,0,'miledmin','milena','nic','mile@gmail.com',0,1,'2025-05-02 16:06:30.863810',NULL),(12,'pbkdf2_sha256$600000$IGU6hbyOpiSArIY1y7lszw$CogkVdXO1bkiQ3kqCBVu9/DkG9Z8QwEOdTWZWQTiZ9I=','2025-05-02 16:18:22.934337',1,'milenaadmin','','','',1,1,'2025-05-02 16:17:51.730875',NULL),(13,'pbkdf2_sha256$600000$JDFACCmbS4suZWLDvIGF8r$H2EtTPu/EqJFz6wI3Y07IZB/VqAeSExCYod1xoFF09s=',NULL,0,'vale21','Vale','Angeletti','valeangelett11@gmail.com',0,1,'2025-06-16 00:58:21.072153',NULL),(14,'pbkdf2_sha256$600000$4794581fZj4HOOxLLRHPe7$GQAGumjf/VjTj84NH+br3MltGNY5wdHVSFJBF40EM+c=',NULL,0,'userFin','user','final','userfi@gmail.com',0,1,'2025-06-17 12:47:37.388353',NULL);
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_groups`
--

DROP TABLE IF EXISTS `auth_user_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_user_groups` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `group_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_group_id_97559544_fk_auth_group_id` (`group_id`),
  CONSTRAINT `auth_user_groups_group_id_97559544_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `auth_user_groups_user_id_6a12ed8b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_groups`
--

LOCK TABLES `auth_user_groups` WRITE;
/*!40000 ALTER TABLE `auth_user_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_user_permissions`
--

DROP TABLE IF EXISTS `auth_user_user_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_user_user_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_user_permissions`
--

LOCK TABLES `auth_user_user_permissions` WRITE;
/*!40000 ALTER TABLE `auth_user_user_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_user_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrito`
--

DROP TABLE IF EXISTS `carrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrito` (
  `id_carrito` bigint NOT NULL AUTO_INCREMENT,
  `id_producto` int NOT NULL,
  `nombre_usuario` varchar(100) NOT NULL,
  `cantidad` int DEFAULT NULL,
  PRIMARY KEY (`id_carrito`),
  KEY `carrito_producto_FK` (`id_producto`),
  KEY `carrito_usuario_FK` (`nombre_usuario`),
  CONSTRAINT `carrito_producto_FK` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  CONSTRAINT `carrito_usuario_FK` FOREIGN KEY (`nombre_usuario`) REFERENCES `usuario` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrito`
--

LOCK TABLES `carrito` WRITE;
/*!40000 ALTER TABLE `carrito` DISABLE KEYS */;
INSERT INTO `carrito` VALUES (1,18,'admin',1),(2,19,'admin',1),(3,19,'admin',1),(18,19,'pepesanchez',1),(26,17,'miledmin',1),(57,16,'liomessi',1);
/*!40000 ALTER TABLE `carrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria_producto`
--

DROP TABLE IF EXISTS `categoria_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria_producto` (
  `id_categoria_producto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_categoria_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_producto`
--

LOCK TABLES `categoria_producto` WRITE;
/*!40000 ALTER TABLE `categoria_producto` DISABLE KEYS */;
INSERT INTO `categoria_producto` VALUES (1,'Accesorios',NULL),(2,'Cuchas',NULL),(3,'Juguetes',NULL),(4,'Ropa',NULL);
/*!40000 ALTER TABLE `categoria_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_admin_log`
--

DROP TABLE IF EXISTS `django_admin_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_admin_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6_fk_auth_user_id` (`user_id`),
  CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `django_admin_log_user_id_c564eba6_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `django_admin_log_chk_1` CHECK ((`action_flag` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_admin_log`
--

LOCK TABLES `django_admin_log` WRITE;
/*!40000 ALTER TABLE `django_admin_log` DISABLE KEYS */;
INSERT INTO `django_admin_log` VALUES (1,'2024-05-14 01:17:03.365380','1','Tipodoc object (1)',1,'[{\"added\": {}}]',13,1),(2,'2024-06-03 23:46:09.847634','3','migue',1,'[{\"added\": {}}]',4,2),(3,'2024-06-09 23:27:29.119341','admin','admin',1,'[{\"added\": {}}]',24,1),(4,'2025-05-02 16:18:44.778353','1','prueba1 (PORCENTAJE)',1,'[{\"added\": {}}]',42,12),(5,'2025-05-02 16:18:55.279241','2','Ropita Sale -$5000 off (MONTO)',1,'[{\"added\": {}}]',42,12),(6,'2025-05-07 14:40:53.250047','2','Ropita Sale 30% Off (PORCENTAJE)',2,'[{\"changed\": {\"fields\": [\"Nombre\", \"Descripcion\", \"Tipo descuento\", \"Valor descuento\", \"Imagen url\", \"Fecha vencimiento\"]}}]',42,12),(7,'2025-05-07 14:41:39.508799','1','Invierno 50% Off (PORCENTAJE)',2,'[{\"changed\": {\"fields\": [\"Nombre\", \"Descripcion\", \"Valor descuento\", \"Fecha vencimiento\"]}}]',42,12),(8,'2025-05-07 14:43:43.981261','3','Cats Day 20% Off (PORCENTAJE)',1,'[{\"added\": {}}]',42,12),(9,'2025-05-07 14:45:04.464808','4','Cuchas -$5000 Off (MONTO)',1,'[{\"added\": {}}]',42,12),(10,'2025-05-07 14:45:16.963182','4','Cuchas $5000 Off (MONTO)',2,'[{\"changed\": {\"fields\": [\"Nombre\"]}}]',42,12);
/*!40000 ALTER TABLE `django_admin_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_content_type`
--

DROP TABLE IF EXISTS `django_content_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_content_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_content_type`
--

LOCK TABLES `django_content_type` WRITE;
/*!40000 ALTER TABLE `django_content_type` DISABLE KEYS */;
INSERT INTO `django_content_type` VALUES (1,'admin','logentry'),(3,'auth','group'),(2,'auth','permission'),(4,'auth','user'),(5,'contenttypes','contenttype'),(46,'PetBoutiqueApp','arrepentimiento'),(25,'PetBoutiqueApp','authgroup'),(26,'PetBoutiqueApp','authgrouppermissions'),(27,'PetBoutiqueApp','authpermission'),(28,'PetBoutiqueApp','authuser'),(29,'PetBoutiqueApp','authusergroups'),(30,'PetBoutiqueApp','authuseruserpermissions'),(31,'PetBoutiqueApp','carrito'),(32,'PetBoutiqueApp','categoriaproducto'),(7,'PetBoutiqueApp','categoriaproductos'),(42,'PetBoutiqueApp','cupon'),(45,'PetBoutiqueApp','cuponusuario'),(43,'PetBoutiqueApp','customuser'),(33,'PetBoutiqueApp','djangoadminlog'),(34,'PetBoutiqueApp','djangocontenttype'),(35,'PetBoutiqueApp','djangomigrations'),(36,'PetBoutiqueApp','djangosession'),(8,'PetBoutiqueApp','estadopedido'),(9,'PetBoutiqueApp','formadepago'),(37,'PetBoutiqueApp','pedido'),(10,'PetBoutiqueApp','pedidos'),(21,'PetBoutiqueApp','producto'),(17,'PetBoutiqueApp','productos'),(15,'PetBoutiqueApp','productosxcarrito'),(18,'PetBoutiqueApp','productosxpedido'),(19,'PetBoutiqueApp','productosxventa'),(38,'PetBoutiqueApp','productoxpedido'),(39,'PetBoutiqueApp','productoxventa'),(40,'PetBoutiqueApp','proveedor'),(11,'PetBoutiqueApp','proveedores'),(22,'PetBoutiqueApp','rol'),(12,'PetBoutiqueApp','roles'),(13,'PetBoutiqueApp','tipodoc'),(23,'PetBoutiqueApp','tipodocumento'),(14,'PetBoutiqueApp','tipoenvio'),(24,'PetBoutiqueApp','usuario'),(44,'PetBoutiqueApp','usuariocupon'),(16,'PetBoutiqueApp','usuarios'),(41,'PetBoutiqueApp','venta'),(20,'PetBoutiqueApp','ventas'),(6,'sessions','session');
/*!40000 ALTER TABLE `django_content_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_migrations`
--

DROP TABLE IF EXISTS `django_migrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_migrations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_migrations`
--

LOCK TABLES `django_migrations` WRITE;
/*!40000 ALTER TABLE `django_migrations` DISABLE KEYS */;
INSERT INTO `django_migrations` VALUES (1,'contenttypes','0001_initial','2024-05-14 00:21:41.599596'),(2,'auth','0001_initial','2024-05-14 00:21:42.255499'),(3,'admin','0001_initial','2024-05-14 00:21:42.606375'),(4,'admin','0002_logentry_remove_auto_add','2024-05-14 00:21:42.624687'),(5,'admin','0003_logentry_add_action_flag_choices','2024-05-14 00:21:42.640807'),(6,'contenttypes','0002_remove_content_type_name','2024-05-14 00:21:42.780685'),(7,'auth','0002_alter_permission_name_max_length','2024-05-14 00:21:42.857712'),(8,'auth','0003_alter_user_email_max_length','2024-05-14 00:21:42.941679'),(9,'auth','0004_alter_user_username_opts','2024-05-14 00:21:42.951615'),(10,'auth','0005_alter_user_last_login_null','2024-05-14 00:21:43.021774'),(11,'auth','0006_require_contenttypes_0002','2024-05-14 00:21:43.026571'),(12,'auth','0007_alter_validators_add_error_messages','2024-05-14 00:21:43.053583'),(13,'auth','0008_alter_user_username_max_length','2024-05-14 00:21:43.131121'),(14,'auth','0009_alter_user_last_name_max_length','2024-05-14 00:21:43.197869'),(15,'auth','0010_alter_group_name_max_length','2024-05-14 00:21:43.288842'),(16,'auth','0011_update_proxy_permissions','2024-05-14 00:21:43.298815'),(17,'auth','0012_alter_user_first_name_max_length','2024-05-14 00:21:43.371063'),(18,'sessions','0001_initial','2024-05-14 00:21:43.422532'),(19,'PetBoutiqueApp','0001_initial','2024-05-14 01:14:09.363215'),(20,'PetBoutiqueApp','0002_cupon_image_url','2025-05-07 16:32:21.025938');
/*!40000 ALTER TABLE `django_migrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_session`
--

DROP TABLE IF EXISTS `django_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_session`
--

LOCK TABLES `django_session` WRITE;
/*!40000 ALTER TABLE `django_session` DISABLE KEYS */;
INSERT INTO `django_session` VALUES ('010eis9ppjga0wgli93yglkm8dku03h0','.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEJV9:qvc1rrSiXy9QHn8H_VZHlHNqwra1lpnwXjV5uDJRamk','2024-06-18 02:02:15.023347'),('0bqz5kztj7ucqtp3wzjiuxis9tmupdl0','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhL1:PGav0InEK6ysH3KpT8KlS0fP2XhTRpjZfgIYCYU3nRI','2024-10-01 23:10:27.192069'),('0g1n19o3351him3acedaq41n57ktp95v','.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEJqh:gwas0tXmdZIO8QzRAUlc4NAiX3rInTe7N8iFHzis8ys','2024-06-18 02:24:31.469433'),('16994gsl74n3mlsmqadldavorswi4x06','.eJxVjEEOwiAQRe_C2pBCM2Vw6d4zkBkYpGogKe3KeHdt0oVu_3vvv1SgbS1h67KEOamzcur0uzHFh9QdpDvVW9Ox1XWZWe-KPmjX15bkeTncv4NCvXxrb52J4-AMSkIagJHZe2HyI9rsYIoGTTYJSCYRm9BnyNlaFAM8Aqn3B-IXOBw:1sIEUP:GLnpua1DltQivyJRMTdYqT4C9hPOrts9KujPq9LLaRU','2024-06-28 21:29:41.024679'),('2h6pd31kvp79hjjpm06kexvaoux2gtw9','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhqW:volEQqI8yXxF9kZKKutR6X9OyOvM-7f1mht7OJkt5H0','2024-10-01 23:43:00.747506'),('316w5cd7p82xxn85czdihy37vfjm1218','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhnX:Agh1hItFrWSAz_0TnldNQfiPVAZxaA2Uga3Fnd8SyU4','2024-10-01 23:39:55.258475'),('36idtb2wbslrdoxe0n2k487urai0q70o','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhfg:zwBlTWB2gfafei6oij35aOOhuH1C3ElWcwSRJcPUKRc','2024-10-01 23:31:48.598283'),('3bhqxjt8ck3rn8q3uxibhwgy7iprk5df','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqi0U:rcEdHzIA3d3b1ao2JM8rl2tGsoEUH2US1-TzPCpA4QY','2024-10-01 23:53:18.134598'),('3evtz6rwgmhr4wmyyzgwtjjzc9eq1f38','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhgh:-LldKg5XuR322PLZCZ3XCc6H6VoShnIhuZlSMpC1GUg','2024-10-01 23:32:51.894363'),('3gn52kcfjwzshxjbuz1kdb1h85m6s3hw','.eJxVjEEOwiAQRe_C2pBCM2Vw6d4zkBkYpGogKe3KeHdt0oVu_3vvv1SgbS1h67KEOamzcur0uzHFh9QdpDvVW9Ox1XWZWe-KPmjX15bkeTncv4NCvXxrb52J4-AMSkIagJHZe2HyI9rsYIoGTTYJSCYRm9BnyNlaFAM8Aqn3B-IXOBw:1sIDy3:4tek2Ipzr3K2tHmIru6kuN05ScdMFjzz4l2LkOlYVM4','2024-06-28 20:56:15.231700'),('3lq5yslouob8hcw3vabfn8byopjepnzc','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhau:uGiqUAg5_uu-wpYGxyfpRksXtmYxdKZ4tZZDEAxrgp4','2024-10-01 23:26:52.001408'),('3ofrepeph9fct6v0e5kts1puxwxwswcg','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhYr:tHn5IbVJl9Wzck15d_qv5Y4Vik9pMbyifLTAIH8ttiQ','2024-10-01 23:24:45.666241'),('4nh7g25aac7vmhy32gy00f5so17ugdfs','.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEIlB:uSflTvxq3QgmTY3wTkkXEu5FrkXdWvuM8xhXPtHLbms','2024-06-18 01:14:45.104524'),('5754f1v1x4umvyqiyeu7dk8cgbt0i9xf','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqi6J:gPOTwR4oREfkndDOHEd0pn8CzwOEYnXEV2yqkYD5Q18','2024-10-01 23:59:19.743356'),('58dr7omrzk50k2rm86disl16t74h3eep','.eJxVjEEOwiAQRe_C2hCKMIBL9z0DmRlAqoYmpV0Z765NutDtf-_9l4i4rTVuPS9xSuIivDj9boT8yG0H6Y7tNkue27pMJHdFHrTLcU75eT3cv4OKvX5rZ7hkYDQKAitmg2CI0OrBAZhEKTjvIdNARZ8LgCuKg1fKFgKwXov3B_i2N-Y:1sIGaf:Z5XyIWxJK8LWYg3tm6U95HmeZFk1-TqncAhMPUBu37k','2024-06-28 23:44:17.119931'),('698q8n4eszzrm4o9k05b12dgvzohz423','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqiCB:YDjxI1Njh16WtCWEciNZykPFcGo9jlCYiVho54AGHDU','2024-10-02 00:05:23.665924'),('6gvg2dkiabkvgjd26i75kdoeol41lp3e','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqgZF:NXLotHAbSa10G_B0gW6CI-H47OWDn71RPKU5YgpQiOE','2024-10-01 22:21:05.576751'),('6gxbsfsdrt793duwx619gl72h0wkawoa','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhwe:gax80qWJyaTtzPZJyYulxUegqpAnQ5fFcNaJRowh6U0','2024-10-01 23:49:20.595489'),('7qo9v2dp4jcyvezsxn0xrdbjxp2ehdht','.eJxVjEEOwiAQRe_C2pBCM2Vw6d4zkBkYpGogKe3KeHdt0oVu_3vvv1SgbS1h67KEOamzcur0uzHFh9QdpDvVW9Ox1XWZWe-KPmjX15bkeTncv4NCvXxrb52J4-AMSkIagJHZe2HyI9rsYIoGTTYJSCYRm9BnyNlaFAM8Aqn3B-IXOBw:1sIEHt:Gqi2jtHi0fspJL1HGtZkTIwSQZyNrtJcRen6K2oAXBc','2024-06-28 21:16:45.166501'),('7xobm9tgg6l9lngxiqjznztsdmntl65l','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhp9:_XiTgbpaB20HF7EqnTfdKyh56dK3ym7Fn0q5gGgE_Gc','2024-10-01 23:41:35.924641'),('8ku5fz5hrsatyw9g6d112wgydd6u7j3g','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhm6:hM8ySw6Pls5jFCmpkqjcx1gPeKX_yXNHqHsagj1ZdCQ','2024-10-01 23:38:26.928462'),('9nqdl8uq0rnhk4w8thrhqd62wc2yx2r1','.eJxVjEEOwiAQRe_C2hCKMIBL9z0DmRlAqoYmpV0Z765NutDtf-_9l4i4rTVuPS9xSuIivDj9boT8yG0H6Y7tNkue27pMJHdFHrTLcU75eT3cv4OKvX5rZ7hkYDQKAitmg2CI0OrBAZhEKTjvIdNARZ8LgCuKg1fKFgKwXov3B_i2N-Y:1sIGbI:EqiMAt2FGw24zIkYVuoi-Tif3TtYRl5EtzSeYpB2YCw','2024-06-28 23:44:56.038438'),('bh13h5dg56zg3vucybyam4b887a9n839','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqiST:PdUOJidPDMGqpCScLMphViQcZ1-iJa5d_oCR55nd7NY','2024-10-02 00:22:13.127699'),('bxbddoq7iq9cmkp5oylxjcrx78b0kdwq','.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEJFl:0YYvd70nUbbcThj_Lbx22ji9DdJwBIbJg5bbtnNipQ8','2024-06-18 01:46:21.611989'),('d4yz08rgx59wkqeq5k7vgu3b7o3v7gob','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqijV:Mc4PWq7n3bnNRh1VSn3k_QcakQ72jBoQFWLT9f6zn7o','2024-10-02 00:39:49.214931'),('efcgqb5mv3se6jyebmih7fup85o38muk','.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEKkW:TGFl6BEstn6v_JQKmSYhnqs8czWhu4YmLbbFzKfQAKU','2024-06-18 03:22:12.817398'),('f566mxmsy0rb80yuj8wxcc2caqjdlavz','.eJxVjDsOwjAQBe_iGln-4B8lfc5grb1rHEC2FCcV4u4QKQW0b2bei0XY1hq3QUuckV2YZKffLUF-UNsB3qHdOs-9rcuc-K7wgw4-daTn9XD_DiqM-q2NDzIkp61XGYyyxqezU4TFOqERPTiLpgRFXgrIMocihba5CFKSyBj2_gDGwzeB:1sFNJs:4dPBONTWpapfTjDPwff2IMuDGlEUvcIyZ044iaVqODw','2024-06-21 00:19:00.019106'),('ftiq4pn2hcj5hm4jgywoon33ok6erdao','.eJxVjDsOwjAQBe_iGln-4B8lfc5grb1rHEC2FCcV4u4QKQW0b2bei0XY1hq3QUuckV2YZKffLUF-UNsB3qHdOs-9rcuc-K7wgw4-daTn9XD_DiqM-q2NDzIkp61XGYyyxqezU4TFOqERPTiLpgRFXgrIMocihba5CFKSyBj2_gDGwzeB:1sGRWX:sc8J1Oki6F1By_dDF19_Y03Yc-YSHgISRVk_zf_bjfw','2024-06-23 23:00:29.256069'),('fv6w14u3w4gzfi24gackm2yx8b4euqgw','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqi7e:aBncEIhnjveCB5tz3R8IG8zlG-4hGwNocX0xQG1O3OE','2024-10-02 00:00:42.183656'),('giast6t3jg0dnhzrsemcbbrke5s55iie','.eJxVjE0OwiAYBe_C2pCWFgou3XsGwvcnVQNJaVfGu2uTLnT7Zua9VEzbmuPWeIkzqbNy6vS7QcIHlx3QPZVb1VjLusygd0UftOlrJX5eDvfvIKeWv7UPNpBxyKlHz8EQosFEMHU-sFhCARkna8E7BHbCQiLGDAOOfQcyqPcHHyc5mA:1sEKrx:8DSHaEzqI97dWOY8XufItGuhra6BJ-MjZRd57Gqlodo','2024-06-18 03:29:53.658782'),('goeen5pibwxuxwy965ihg6ew6lqt51oy','.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEJ3U:8-_ACTGKWe80BwBgwtjlDSYKj6XK_3sESld8dirTwhk','2024-06-18 01:33:40.718490'),('gpoco1wslmm20tjtik1jb7pjf8qusn81','.eJxVjDsOwjAQBe_iGln-4B8lfc5grb1rHEC2FCcV4u4QKQW0b2bei0XY1hq3QUuckV2YZKffLUF-UNsB3qHdOs-9rcuc-K7wgw4-daTn9XD_DiqM-q2NDzIkp61XGYyyxqezU4TFOqERPTiLpgRFXgrIMocihba5CFKSyBj2_gDGwzeB:1s6ghN:5iN-87VmLnj9rHozF-5Tf_LCA2LJRoYOr_LpI0AHMkU','2024-05-28 01:11:21.309976'),('h3p6ditnsbbh3vo3fzsfmh89edj3nvmh','.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEIiK:jhCVtjqST-fFBz0OEcPgQX6hwdSZ-KJteKgmkvRnWR8','2024-06-18 01:11:48.675244'),('hfagklqjgih5gg5az8dez27gud825fv6','.eJxVjEEOwiAQRe_C2pBCM2Vw6d4zkBkYpGogKe3KeHdt0oVu_3vvv1SgbS1h67KEOamzcur0uzHFh9QdpDvVW9Ox1XWZWe-KPmjX15bkeTncv4NCvXxrb52J4-AMSkIagJHZe2HyI9rsYIoGTTYJSCYRm9BnyNlaFAM8Aqn3B-IXOBw:1sIEf6:-bNYPevugDDUHutMRdOr6CImkykzaUwaUPi-Z86REnw','2024-06-28 21:40:44.123729'),('hgd9tmhskn8095ea01kl6akwlbubky6x','.eJxVjEEOwiAQRe_C2pBCM2Vw6d4zkBkYpGogKe3KeHdt0oVu_3vvv1SgbS1h67KEOamzcur0uzHFh9QdpDvVW9Ox1XWZWe-KPmjX15bkeTncv4NCvXxrb52J4-AMSkIagJHZe2HyI9rsYIoGTTYJSCYRm9BnyNlaFAM8Aqn3B-IXOBw:1sHvU7:bn8zB2doZj9Di62-h6j2BFFG-LunFVhcTegzrf3SjaU','2024-06-28 01:12:07.395278'),('hj15p5a94hmo0nk1frk3qr7wqynw1xme','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhLS:cVYYrDMSgLrOldg2F0gpunL2n9MxEE3_Gf01rEJZUwM','2024-10-01 23:10:54.313977'),('hqtxsf6r0g14441w67e3aqgy9aiv3hkt','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqi1l:M8pJl1F5JLFgh5NZg4zMsRllmi9VWvkMATNk-bymMk4','2024-10-01 23:54:37.149433'),('j0w6f3khtbdhcipdvqmqtjiv59v9v48n','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhlc:bWa23kojcZ9R0GC6OhMZfBgmO6JqZm5rLsPVD6SbbgI','2024-10-01 23:37:56.738735'),('j45d1gsm7r1gpvmthig26ubthfjtm50x','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhlu:Kj3rqacjeTXGXpIDVvADp9W5WAao73ketxgFrp94y2w','2024-10-01 23:38:14.236335'),('jdy2a46006hzqx0tmt9bafipjjc1wz6n','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqi4h:wjCsRdHPguFu0D2FlHGmyOOVhanzy9lX5He41lgzyRY','2024-10-01 23:57:39.632275'),('jhuwkn2oznylug8d92hy85eucqxbx86i','.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEJ8Q:qWp5X7qkP_meDec-S9a0wsP8QyDplXvpiEMjODo-ljQ','2024-06-18 01:38:46.265614'),('jrxpzb31b9fs446auac9il2o2xjmkao3','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhgK:JUUhdEYCd69ffeXpfmKjrGiKNaN7GqOsdHW58W7l91M','2024-10-01 23:32:28.668829'),('jtci2t0n5ivnkpb3lou1tkt7nsd4rulg','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqibO:OesGEjevYCj_pOqAx5nKDepJQ08OfJyqyiPP-Ue6WQI','2024-10-02 00:31:26.080741'),('k06cucah0meay9mzaisr4sqvj4col5be','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqi8X:fT3pG7OyjEeoCGc8YaWVWwwuY8PW1Hqw1pWUaUUxtnk','2024-10-02 00:01:37.965620'),('kneetnsg5q387fnrkos3preex8kvuixf','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqiSy:EAt0imXyNgnUv3SfGb9bu3k5JpknDGbT_dC3dIj-CUg','2024-10-02 00:22:44.627035'),('kr0x5dkpux4v5jvvjg8e846xqcle9bmq','.eJxVjEEOwiAQRe_C2pBCM2Vw6d4zkBkYpGogKe3KeHdt0oVu_3vvv1SgbS1h67KEOamzcur0uzHFh9QdpDvVW9Ox1XWZWe-KPmjX15bkeTncv4NCvXxrb52J4-AMSkIagJHZe2HyI9rsYIoGTTYJSCYRm9BnyNlaFAM8Aqn3B-IXOBw:1sIEGK:7ku1ghgZMo7LkF_V0y3r49Ya4VLXso7UbhFBQJcuU4s','2024-06-28 21:15:08.664552'),('krwdjifz26f7xzn8uctspuqtdiyixrr8','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqi3G:jDPWKI1A460zjffeuAk0Jc1FvDXgjKf-dy3CC4hftds','2024-10-01 23:56:10.344008'),('lhsxk2dzbudum5ztejx58mz7vrl7zvvu','.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEHNt:XB90rHOZBlY2S4s0m29xsIahCPCrpn0AanXt9QzefBo','2024-06-17 23:46:37.848610'),('me0sicgo2ya4if895s61bpryrv8lql2q','e30:1sqhEO:A0bMfXyVyRZ19Wvqnp3phbWvLmBF5zOdn2UiJobuQ20','2024-10-01 23:03:36.905732'),('mg2gq8fh572z7ga1piy8tdadma515kdx','.eJxVjDsOwjAQBe_iGln-4B8lfc5grb1rHEC2FCcV4u4QKQW0b2bei0XY1hq3QUuckV2YZKffLUF-UNsB3qHdOs-9rcuc-K7wgw4-daTn9XD_DiqM-q2NDzIkp61XGYyyxqezU4TFOqERPTiLpgRFXgrIMocihba5CFKSyBj2_gDGwzeB:1sFMyD:QqcaH-yW7c239a8-eGhmaixScfeVgicE5OP3POf79Wg','2024-06-20 23:56:37.918892'),('nomw186ev2m6kf3nr648rjnm9y6o3fyi','.eJxVjEEOwiAQRe_C2hBmoIW6dO8ZyMCAVA0kpV0Z765NutDtf-_9l_C0rcVvPS1-ZnEWgOL0OwaKj1R3wneqtyZjq-syB7kr8qBdXhun5-Vw_w4K9fKtNdjsAGNwqIMehpEiaM2M2SQyMKFCC6OhoKNzbBwoziqawOgmVmTF-wPr1zeL:1uAt5i:XCT9hjDyDVThjJ3uq2pBrBZCiZT-z2rFGGBZza1gZwM','2025-05-16 16:18:22.936967'),('nzu9gly3w7alp86e7tekk1csa6ur8g1s','.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEJTX:UOA4brNSWmG5DIKXV8GEKjG5TNj5BYjsFIhkTisVCBc','2024-06-18 02:00:35.934325'),('ok30qfnt83lw5gyqvkzf97l2uxbyj6kj','.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEKYC:T8KnpFFsDzgc-QkdyNkTbak3ylCGKGHZ_xPOGj1pLbg','2024-06-18 03:09:28.587213'),('pez7bdzpyvnyu5kzcsuswxa5hfhk3jki','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhli:NT_QFbOUH-Uf0sJmMt0CaYfgo7A3nIGsFnzQI3yCv2s','2024-10-01 23:38:02.174216'),('pkbsedcny2gt6ya1jl0gkaw26zp0s015','.eJxVjEEOwiAQRe_C2pBCM2Vw6d4zkBkYpGogKe3KeHdt0oVu_3vvv1SgbS1h67KEOamzcur0uzHFh9QdpDvVW9Ox1XWZWe-KPmjX15bkeTncv4NCvXxrb52J4-AMSkIagJHZe2HyI9rsYIoGTTYJSCYRm9BnyNlaFAM8Aqn3B-IXOBw:1sIEKf:MjEJFhjBkW2LOH6y1B1iJVrYeFpNLtlq40-5QKRYV1w','2024-06-28 21:19:37.318223'),('ppn6jafo82j8thlfxqgl11uyq55r57hh','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqib2:y4JdgUPU-2OKiuS4IK4FABZusQi7YndtdNmeViJT03M','2024-10-02 00:31:04.754377'),('qzkk99tsls9aaqw761827f10x0ya1xar','.eJxVjEEOwiAQRe_C2hCKMIBL9z0DmRlAqoYmpV0Z765NutDtf-_9l4i4rTVuPS9xSuIivDj9boT8yG0H6Y7tNkue27pMJHdFHrTLcU75eT3cv4OKvX5rZ7hkYDQKAitmg2CI0OrBAZhEKTjvIdNARZ8LgCuKg1fKFgKwXov3B_i2N-Y:1sIGcB:W-kzyj6n1ieRr5Qnl5Vci-IM7fA2lsCwmWojhdL5pcQ','2024-06-28 23:45:51.518419'),('r4oi0azvpj3raqlgwq4fl2x315w7tdxh','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhr2:fSIuxzSOL_m-17D6sKMXOwSRSWuyLIgN3fPE1LRz9Xc','2024-10-01 23:43:32.093321'),('s7j3cqaeyfsubq4sg8qvdfdut4yo52pe','.eJxVjEEOwiAQRe_C2pBCM2Vw6d4zkBkYpGogKe3KeHdt0oVu_3vvv1SgbS1h67KEOamzcur0uzHFh9QdpDvVW9Ox1XWZWe-KPmjX15bkeTncv4NCvXxrb52J4-AMSkIagJHZe2HyI9rsYIoGTTYJSCYRm9BnyNlaFAM8Aqn3B-IXOBw:1sIEaU:bGGEXlGxzJhuXCAiimk27v9obXRTf_3PAUxefb6GnCg','2024-06-28 21:35:58.501040'),('smsevmfu93nqu88izcd01oja04wnfven','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhKj:uSXoryYJEZbKzBIUV8xaR6GFTn5CO_B_DLjcRHuiBdE','2024-10-01 23:10:09.274944'),('ssn751tp82jjbqc61l30iu48jfh8j5ru','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqi4N:7xiw7TXRP-APHFn7yxyoeg3qtWBztqPOFgVto-jVKJM','2024-10-01 23:57:19.862600'),('tps00ckh49f7v0xlex69wur06o99vfiv','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqigI:gh0gQrg99mKuw7TykyzF-ru70iMxgf31-tswhN4kMTY','2024-10-02 00:36:30.985990'),('tzns9fgj8qyx5ooodyil3disj3p9rmv4','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhs5:m_ZevNUDAMcXG9tITKOEkEwRk1cOIcVF3HG2rF3OP9w','2024-10-01 23:44:37.484042'),('vk78o1swn11n7eu2o314hgbydnw62mf1','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhUX:VzgdUNeubvlhKLrkvKTAOi7E8wgOlIqAzilD47qHo-8','2024-10-01 23:20:17.464889'),('w2jge9tp0i76a9lusiscxs9kihj4cfwm','.eJxVjDsOwjAQBe_iGln-4B8lfc5grb1rHEC2FCcV4u4QKQW0b2bei0XY1hq3QUuckV2YZKffLUF-UNsB3qHdOs-9rcuc-K7wgw4-daTn9XD_DiqM-q2NDzIkp61XGYyyxqezU4TFOqERPTiLpgRFXgrIMocihba5CFKSyBj2_gDGwzeB:1sGRN2:jxNk2Kkvw_GsH40k4qfD1iVTRV2F3lWp0T3aX7jzf3g','2024-06-23 22:50:40.672530'),('woxw5hylus62njmzusd309zqhjz819jd','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqi7u:S_has8Hhk083-nmh8DHw9QPoxHunrNKDvwf0TDHoCBY','2024-10-02 00:00:58.190104'),('wsa3hu5u1pdg0ndjjx4gahgthxj44lxy','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqiev:tSwTDZyJq0pnAksCvOJCblgkwo1DiQPQR6UpQaE54tE','2024-10-02 00:35:05.819757'),('wupweyndjeh97mwg5vpsvuaewnti3pyb','.eJxVjDsOwjAQBe_iGln-4B8lfc5grb1rHEC2FCcV4u4QKQW0b2bei0XY1hq3QUuckV2YZKffLUF-UNsB3qHdOs-9rcuc-K7wgw4-daTn9XD_DiqM-q2NDzIkp61XGYyyxqezU4TFOqERPTiLpgRFXgrIMocihba5CFKSyBj2_gDGwzeB:1sFN5E:jv5TbWghMPLQj7uSS-vGhCN09gkHVGUPqW_L3KOweYs','2024-06-21 00:03:52.809016'),('wwok35ns5z09h1z1flt55bqzdxlo7fmg','.eJxVjEEOwiAQRe_C2hCKMIBL9z0DmRlAqoYmpV0Z765NutDtf-_9l4i4rTVuPS9xSuIivDj9boT8yG0H6Y7tNkue27pMJHdFHrTLcU75eT3cv4OKvX5rZ7hkYDQKAitmg2CI0OrBAZhEKTjvIdNARZ8LgCuKg1fKFgKwXov3B_i2N-Y:1sIGi0:B2tec_oLbmCNBIZp1Eta-8qkiiRauB4r6eiGY87Z_eg','2024-06-28 23:51:52.960480'),('y35bp3p0spuj8hooewqpi28qteta1oiq','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhlk:qxcHq75GrCp-PvR8YHziTU2O8E7lbz8IGHl8JNS8HQw','2024-10-01 23:38:04.495480'),('y7wyjr6ugp9kvrw4w2fmf3sxwh0yki2v','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqiOD:MxwwNbe5WmEDNu3E26oFww4jtgwVsoOvLXtmS6RVXMA','2024-10-02 00:17:49.976214'),('ypfd2m4s9n2r7luvt67ruq95uwu1ivth','.eJxVjEEOwiAQRe_C2pBCM2Vw6d4zkBkYpGogKe3KeHdt0oVu_3vvv1SgbS1h67KEOamzcur0uzHFh9QdpDvVW9Ox1XWZWe-KPmjX15bkeTncv4NCvXxrb52J4-AMSkIagJHZe2HyI9rsYIoGTTYJSCYRm9BnyNlaFAM8Aqn3B-IXOBw:1sIEFY:gf5RUKE0njUFjP9WQVZ_blOnRpBM6-Br1pr37XfI1J0','2024-06-28 21:14:20.610097'),('ywjdb8ee01xtseq0iclnpca87gca7snd','.eJxVjEEOgjAQRe_StWkoTKfUpXvP0Mx0poIaSCisjHdXEha6_e-9_zKJtnVIW9UljWLOxjXm9Dsy5YdOO5E7TbfZ5nlal5HtrtiDVnudRZ-Xw_07GKgO3zp2GENuwEcNbel9EQDhLmCUgpi1zYEEHEZy2hWm6BWQiIABkXs17w8CqziB:1sqhC6:uPPA2QsCIMXL4lHIsp45fag5gJ8ZzUY-aA09RGe8pbA','2024-10-01 23:01:14.935652'),('yys2dwyupdjob62htzx1dxkdmfqwml9g','.eJxVjEEOwiAQRe_C2hCKMIBL9z0DmRlAqoYmpV0Z765NutDtf-_9l4i4rTVuPS9xSuIivDj9boT8yG0H6Y7tNkue27pMJHdFHrTLcU75eT3cv4OKvX5rZ7hkYDQKAitmg2CI0OrBAZhEKTjvIdNARZ8LgCuKg1fKFgKwXov3B_i2N-Y:1sIGeB:xe3O96Y33V4kF7rTVFm_u4B2oYMlowDPcHPZqCpo7d0','2024-06-28 23:47:55.497798'),('z5mlugvfe7n88gnm0gnwbums8nnsz45j','.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEIzd:aNFjmED8LbS5rjf16IfSM5eKc29jK3wIXJsWOXZONec','2024-06-18 01:29:41.186781'),('z8dw0zx1qacno1bkqnpdwcp43d66gzt4','e30:1sqhEX:CU5MzM8SxWSKNuo9vFY3n8m5bFiiBiUz8YRAGq2PGFw','2024-10-01 23:03:45.200438');
/*!40000 ALTER TABLE `django_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_pedido`
--

DROP TABLE IF EXISTS `estado_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_pedido` (
  `id_estado_pedido` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_estado_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_pedido`
--

LOCK TABLES `estado_pedido` WRITE;
/*!40000 ALTER TABLE `estado_pedido` DISABLE KEYS */;
INSERT INTO `estado_pedido` VALUES (1,'creado'),(2,'finalizado');
/*!40000 ALTER TABLE `estado_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forma_de_pago`
--

DROP TABLE IF EXISTS `forma_de_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forma_de_pago` (
  `id_forma_de_pago` int NOT NULL,
  `desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_forma_de_pago`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forma_de_pago`
--

LOCK TABLES `forma_de_pago` WRITE;
/*!40000 ALTER TABLE `forma_de_pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `forma_de_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `id_pedido` int NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT NULL,
  `id_estado_pedido` int DEFAULT NULL,
  `nombre_usuario` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `id_tipo_de_envio` int DEFAULT NULL,
  `domicilio_envio` varchar(150) DEFAULT NULL,
  `id_forma_de_pago` int DEFAULT NULL,
  `numero_pedido` int NOT NULL,
  `total` decimal(10,2) DEFAULT '0.00',
  `descuento` decimal(10,2) DEFAULT '0.00',
  `codigo_postal` varchar(10) DEFAULT NULL,
  `costo_envio` decimal(10,2) DEFAULT NULL,
  `ciudad_envio` varchar(100) DEFAULT NULL,
  `localidad` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_pedido`),
  UNIQUE KEY `pedido_unique` (`numero_pedido`),
  KEY `estado_idx` (`id_estado_pedido`),
  KEY `usuario_idx` (`nombre_usuario`),
  KEY `fk_pedido_tipoEnvio_idx` (`id_tipo_de_envio`),
  KEY `fk_pedido_formaDePago_idx` (`id_forma_de_pago`),
  CONSTRAINT `fk_pedido_estado` FOREIGN KEY (`id_estado_pedido`) REFERENCES `estado_pedido` (`id_estado_pedido`),
  CONSTRAINT `fk_pedido_formaDePago` FOREIGN KEY (`id_forma_de_pago`) REFERENCES `forma_de_pago` (`id_forma_de_pago`),
  CONSTRAINT `fk_pedido_tipoEnvio` FOREIGN KEY (`id_tipo_de_envio`) REFERENCES `tipo_envio` (`id_tipo_envio`),
  CONSTRAINT `fk_pedido_usuario` FOREIGN KEY (`nombre_usuario`) REFERENCES `usuario` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,'2024-06-14 01:12:38',1,'liomessi',NULL,NULL,NULL,1,0.00,0.00,NULL,NULL,NULL,NULL),(2,'2024-06-14 01:22:07',1,'liomessi',NULL,NULL,NULL,2,0.00,0.00,NULL,NULL,NULL,NULL),(3,'2024-06-14 21:44:55',1,'liomessi',NULL,NULL,NULL,3,0.00,0.00,NULL,NULL,NULL,NULL),(4,'2024-06-14 23:54:12',1,'pepesanchez',NULL,NULL,NULL,4,0.00,0.00,NULL,NULL,NULL,NULL),(5,'2025-05-13 02:59:46',1,'miledmin',NULL,NULL,NULL,5,0.00,0.00,NULL,NULL,NULL,NULL),(6,'2025-05-13 03:27:15',1,'miledmin',NULL,NULL,NULL,6,0.00,0.00,NULL,NULL,NULL,NULL),(7,'2025-05-13 03:43:02',1,'miledmin',NULL,NULL,NULL,7,0.00,0.00,NULL,NULL,NULL,NULL),(8,'2025-06-15 00:53:44',1,'liomessi',NULL,NULL,NULL,8,0.00,0.00,NULL,NULL,NULL,NULL),(9,'2025-06-15 10:39:40',1,'liomessi',NULL,NULL,NULL,9,0.00,0.00,'5158',9525.00,NULL,NULL),(10,'2025-06-15 11:35:56',1,'liomessi',NULL,NULL,NULL,10,0.00,0.00,'5158',8426.00,NULL,NULL),(11,'2025-06-15 22:54:54',1,'liomessi',NULL,NULL,NULL,11,0.00,0.00,'5158',4893.00,NULL,NULL),(12,'2025-06-15 23:18:06',1,'liomessi',NULL,'BUENA VISTA - C. DE FOMENTO - BUENA VISTA - C. DE FOMENTO',NULL,12,11497.00,0.00,'5158',7797.00,NULL,NULL),(13,'2025-06-15 23:59:34',1,'liomessi',1,'Miami 10',NULL,13,30202.00,0.00,'5158',16102.00,'AR-N',NULL),(14,'2025-06-16 00:17:19',1,'liomessi',2,'BARRIALES UP LIBR PUNTO Y COMA - BARRIALES UP LIBR PUNTO Y COMA',NULL,14,16378.00,5000.00,'5158',9878.00,'2',NULL),(15,'2025-06-16 01:01:06',1,'vale21',2,'COSQUIN QUINIELA OJALA - COSQUIN QUINIELA OJALA',NULL,15,19583.00,5000.00,'5158',8083.00,'2',NULL),(16,'2025-06-17 06:12:24',1,'liomessi',2,'CATAMARCA UP ECOLED DISTRIBUC. - CATAMARCA UP ECOLED DISTRIBUC.',NULL,16,88743.00,0.00,'5158',23743.00,'2',NULL),(17,'2025-06-17 06:57:18',1,'liomessi',NULL,'JUJUY C',NULL,17,0.00,0.00,'',0.00,'',NULL),(18,'2025-06-17 07:04:59',1,'liomessi',2,'COLONIA WANDA - COLONIA WANDA',NULL,18,46133.00,0.00,'5158',23133.00,'2',NULL),(19,'2025-06-17 07:23:31',1,'liomessi',2,'CALEUFU - CALEUFU',NULL,19,38363.00,0.00,'5158',15363.00,'La Pampa',NULL),(20,'2025-06-17 09:42:25',1,'liomessi',2,'COSQUIN QUINIELA OJALA - COSQUIN QUINIELA OJALA',NULL,20,64893.00,0.00,'5158',4893.00,'Córdoba',''),(21,'2025-06-17 12:50:51',1,'userFin',1,'Aires 25',NULL,21,36017.00,0.00,'5158',13017.00,'Mendoza','GUAYMALLEN'),(22,'2025-06-17 12:53:36',1,'userFin',2,'COSQUIN QUINIELA OJALA - COSQUIN QUINIELA OJALA',NULL,22,5410.00,3500.00,'5158',5410.00,'Córdoba','COSQUIN');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `petboutiqueapp_cupon`
--

DROP TABLE IF EXISTS `petboutiqueapp_cupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `petboutiqueapp_cupon` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` longtext NOT NULL,
  `tipo_descuento` varchar(20) NOT NULL,
  `valor_descuento` double NOT NULL,
  `imagen_url` varchar(200) DEFAULT NULL,
  `fecha_vencimiento` date NOT NULL,
  `image_url` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `petboutiqueapp_cupon`
--

LOCK TABLES `petboutiqueapp_cupon` WRITE;
/*!40000 ALTER TABLE `petboutiqueapp_cupon` DISABLE KEYS */;
INSERT INTO `petboutiqueapp_cupon` VALUES (1,'Invierno 50% Off','Aprovecha estos descuentos para abrigar a tus mascotas.','PORCENTAJE',50,'https://i.ibb.co/TxNMLrND/cupon-de-descuento.png','2025-07-24',NULL),(2,'Ropita Sale 30% Off','En toda la sección de Ropa, solo por el mes de Mayo.','PORCENTAJE',30,'https://i.ibb.co/Hfqqg9TD/percha.png','2025-05-31',NULL),(3,'Cats Day 20% Off','Febrero es el mes de tu gato, aprovecha y mimalo','PORCENTAJE',20,'https://i.ibb.co/zTytRXsY/gato.png','2025-02-28',NULL),(4,'Cuchas $5000 Off','Hasta el mes de julio, en la sección de cuchas','MONTO',5000,'https://i.ibb.co/Xr9nqdd7/cucha.png','2025-07-31',NULL);
/*!40000 ALTER TABLE `petboutiqueapp_cupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `petboutiqueapp_customuser`
--

DROP TABLE IF EXISTS `petboutiqueapp_customuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `petboutiqueapp_customuser` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(128) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `is_superuser` tinyint(1) DEFAULT '0',
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) DEFAULT NULL,
  `last_name` varchar(150) DEFAULT NULL,
  `is_staff` tinyint(1) DEFAULT '0',
  `is_active` tinyint(1) DEFAULT '1',
  `date_joined` datetime DEFAULT NULL,
  `email` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customuser_unique` (`username`),
  UNIQUE KEY `customuser_unique_1` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `petboutiqueapp_customuser`
--

LOCK TABLES `petboutiqueapp_customuser` WRITE;
/*!40000 ALTER TABLE `petboutiqueapp_customuser` DISABLE KEYS */;
INSERT INTO `petboutiqueapp_customuser` VALUES (3,'pbkdf2_sha256$600000$Tnznn5MhmJMwrazCmT7doY$ySIxbt6hx3Jr/+l4FkJSPXYpIu+GO/H9VJ4Z6enydsY=','2024-06-03 23:34:46',1,'admin','','',1,1,'2024-06-03 23:23:18','admin@admin.com');
/*!40000 ALTER TABLE `petboutiqueapp_customuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `petboutiqueapp_roles`
--

DROP TABLE IF EXISTS `petboutiqueapp_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `petboutiqueapp_roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` longtext NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `fecha_creacion` datetime(6) NOT NULL,
  `fecha_modificacion` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `petboutiqueapp_roles`
--

LOCK TABLES `petboutiqueapp_roles` WRITE;
/*!40000 ALTER TABLE `petboutiqueapp_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `petboutiqueapp_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `petboutiqueapp_roles_permisos`
--

DROP TABLE IF EXISTS `petboutiqueapp_roles_permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `petboutiqueapp_roles_permisos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `roles_id` bigint NOT NULL,
  `authpermission_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PetBoutiqueApp_roles_per_roles_id_authpermission__c85b8cb8_uniq` (`roles_id`,`authpermission_id`),
  CONSTRAINT `PetBoutiqueApp_roles_roles_id_4303ebfc_fk_PetBoutiq` FOREIGN KEY (`roles_id`) REFERENCES `petboutiqueapp_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `petboutiqueapp_roles_permisos`
--

LOCK TABLES `petboutiqueapp_roles_permisos` WRITE;
/*!40000 ALTER TABLE `petboutiqueapp_roles_permisos` DISABLE KEYS */;
/*!40000 ALTER TABLE `petboutiqueapp_roles_permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `petboutiqueapp_usuariocupon`
--

DROP TABLE IF EXISTS `petboutiqueapp_usuariocupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `petboutiqueapp_usuariocupon` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `usado` tinyint(1) NOT NULL,
  `fecha_aplicado` datetime(6) DEFAULT NULL,
  `cupon_id` bigint NOT NULL,
  `usuario_id` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PetBoutiqueApp_usuariocupon_usuario_id_cupon_id_b3e095e2_uniq` (`usuario_id`,`cupon_id`),
  KEY `PetBoutiqueApp_usuar_cupon_id_e189e838_fk_PetBoutiq` (`cupon_id`),
  CONSTRAINT `PetBoutiqueApp_usuar_cupon_id_e189e838_fk_PetBoutiq` FOREIGN KEY (`cupon_id`) REFERENCES `petboutiqueapp_cupon` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `petboutiqueapp_usuariocupon`
--

LOCK TABLES `petboutiqueapp_usuariocupon` WRITE;
/*!40000 ALTER TABLE `petboutiqueapp_usuariocupon` DISABLE KEYS */;
/*!40000 ALTER TABLE `petboutiqueapp_usuariocupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `descripcion` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci,
  `precio` float DEFAULT NULL,
  `stock_actual` int DEFAULT NULL,
  `id_proveedor` int DEFAULT NULL,
  `stock_minimo` int DEFAULT NULL,
  `id_categoria_producto` int DEFAULT NULL,
  `image_url` text,
  `peso` decimal(5,2) DEFAULT '0.50',
  PRIMARY KEY (`id_producto`),
  KEY `proveedor_idx` (`id_proveedor`),
  KEY `fk_productos_categoria_idx` (`id_categoria_producto`),
  CONSTRAINT `fk_productos_categoria` FOREIGN KEY (`id_categoria_producto`) REFERENCES `categoria_producto` (`id_categoria_producto`),
  CONSTRAINT `fk_productos_proveedor` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (2,'Cucha Perro','Cucha para perro madera',45000,13,2,2,2,'https://i.ibb.co/VWr3jPj/Cucha-Exterior.png',3.50),(3,'Buzo Adidog','Buzo algodon con friza elastizado',8000,38,1,8,4,'https://i.ibb.co/QmvL3pK/Buzo-Miami.png',0.30),(6,'Capa ','Para la lluvia con capucha',8000,22,2,2,4,'https://i.ibb.co/hDsc0Hz/Buzo-Puffer.png',0.10),(7,'Huesito','Hueso de fantasía ',1200,19,1,5,3,'https://i.ibb.co/bJn9zFr/Huesos.png',0.25),(8,'Ponchito','Poncho de lana con detalles en morley',15600,9,1,1,2,'https://i.ibb.co/xg9KJD1/Sweter-Gato.png',1.20),(9,'Cucha calabaza','Cucha estilo calabaza para gatos, color naranja, con relleno de guata y una abertura principal.',3500,7,1,1,2,'https://i.ibb.co/5vVSGxk/Calabaza.png',3.00),(10,'Cucha Madera','Cucha de madera para perros ideal para exteriores, con techo corredizo, comedero y bebedero de agua. Detalles en negro en sus bordes',3500,12,1,1,2,'https://i.ibb.co/Yj42b5p/Cucha-Exterior.png',2.80),(11,'Cucha Cat','Cama para gatos con forma de gato, cubierta con peluche e interiór de felpa. Incluye un pompón colgante como juguete.',3500,13,1,1,2,'https://i.ibb.co/TKPQhjq/Forma-Gato.png',3.50),(12,'Cucha Sillon','Cama estilo sillon para perros con relleno de guata, abierto, coor gris con detalles blancos en sus bordes.',3000,12,2,1,2,'https://i.ibb.co/FWr40Sw/Sillon.png',2.60),(13,'Cama colgante','Cama colgante para gatos con cubierta azul oscuro con textura, abertura para su entrada e interior acolchado',3800,15,2,1,2,'https://i.ibb.co/s6TQ6SM/Gato-Colgante.png',4.00),(14,'Cama donna','Cama tipo Donna con relleno de guata y cubierta de felpa para perros en varios colores',4100,18,2,1,2,'https://i.ibb.co/tLkMRj2/Donnas.png',2.70),(15,'Collar para Gato','Collar para gato con broche de seguridad y cascabel en varios colores.',3000,30,1,1,2,'https://i.ibb.co/g3Fgqww/Collar-Gato.png',0.50),(16,'Collar para Gato','Collar para gato con broche de seguridad y cascabel en varios colores.',3000,22,1,1,1,'https://i.ibb.co/g3Fgqww/Collar-Gato.png',0.15),(17,'Bolso transportador','Bolso trasportador para mascotas con interior de felpa, red para una mejor oxigenación y manijas de agarre seguro.',60000,15,1,1,1,'https://i.ibb.co/61VqkHH/Transportador.png',0.12),(18,'Collar para perro','Collares para perros con broche de seguridad y con estampados diversos.',10000,24,1,1,1,'https://i.ibb.co/jvZxXgg/Collar-Perro.png',2.00),(19,'Rascador para gato','Rascador para gato de 4 pisos con bolsa colgante, cucha con salidas, y juguete en su piso superior. Todos los pisos estan cubiertos de felpa con detalles en gris.',100000,9,2,1,1,'https://i.ibb.co/5jYk6K5/Rascador.png',0.20),(20,'Correa extensible','Correa extensible para mascotas con gancho para enganchar en el collar y un largo total de 1 metro con botón regulable.',20000,7,2,1,1,'https://i.ibb.co/Rp00xwC/Correa.png',5.00),(21,'Comedero + Dispenser','Base blanca con comedero transparente en forma de gato y dispensador de agua.',25000,12,2,1,1,'https://i.ibb.co/hMxnRKW/Vertedero.png',0.30),(22,'Caña con pluma','Juguete de tipo caña con una pluma en su extremo para jugar con gatos.',3000,23,2,1,3,'https://i.ibb.co/m987M63/Jueguete-Gato.png',0.60),(23,'Hueso para perro','Huesos de cuero comestible para perros',1500,24,2,1,3,'https://i.ibb.co/mG1rYpS/Huesos.png',1.00),(24,'Peluche Felpa','Peluche de felpa tipo patita para gatos. Varios colores.',2500,23,2,1,3,'https://i.ibb.co/wSTP5j6/Peluche-Gato.png',0.90),(25,'Sogas trenzadas','Sogas trenzadas para jugar con perros. Varios colores.',2500,25,1,1,3,'https://i.ibb.co/CMy9xZ8/Sogas.png',0.70),(26,'Raton de Juguete','Ratón de juguete con control remoto para divertirte con tu gato.',4500,17,1,1,3,'https://i.ibb.co/fYNH3sv/Raton.png',0.25),(27,'Lanzador de pelota','Lanzador automático de pelotas para perros. Incluuye 6 pelotas de regalo.',5500,17,1,1,3,'https://i.ibb.co/QDR8RzX/Arrojador-Automatico.png',0.80),(28,'Chaleco Puffer','Chaleco estilo puffer para perro mediano, color verde militar con interior de corderito.',13000,13,1,1,4,'https://i.ibb.co/sVDqPDc/Buzo-Puffer.png',1.10),(29,'Buzo Orejas','Bolso trasportador para mascotas con interior de felpa, red para una mejor oxigenación y manijas de agarre seguro.',16000,14,1,1,4,'https://i.ibb.co/7RsWs21/Buzo-Orejas-Gato.png',1.40),(30,'Buzo Miami','Buzo para perro mediano. Color verde oscuro con letras \"Miami\" color dorado y detalles en color blanco y rojo. Mangas largas.',17000,12,1,1,4,'https://i.ibb.co/WzWx73x/Buzo-Miami.png',0.35),(31,'Gorro Pingüino','Gorro para gato color celeste en forma de pingüino con detalles en color blanco y amarillo.',8000,17,2,1,4,'https://i.ibb.co/mFRQjc8/Gorro-Pinguino.png',0.15),(32,'Buzo Peluche','Buzo de peluche para perro chico. Varios colores. Con capucha con detalles de orejas. Detalles en blanco. Interior de corderito.',9000,21,2,1,4,'https://i.ibb.co/4SSnNWM/Buzo-Felpa.png',0.90),(33,'Sweater Lana','Sweater tejido de lana para gato. Color celeste con cuello y mangas cortas.',8700,32,2,1,4,'https://i.ibb.co/74cyQ2L/Sweter-Gato.png',0.60);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_x_pedido`
--

DROP TABLE IF EXISTS `producto_x_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto_x_pedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_producto` int DEFAULT NULL,
  `id_pedido` int DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `precio` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `producto_x_pedido_producto_FK` (`id_producto`),
  KEY `producto_x_pedido_pedido_FK` (`id_pedido`),
  CONSTRAINT `producto_x_pedido_pedido_FK` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`),
  CONSTRAINT `producto_x_pedido_producto_FK` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_x_pedido`
--

LOCK TABLES `producto_x_pedido` WRITE;
/*!40000 ALTER TABLE `producto_x_pedido` DISABLE KEYS */;
INSERT INTO `producto_x_pedido` VALUES (1,8,1,1,3500),(2,8,1,1,15600),(3,2,2,1,45000),(4,10,2,1,3500),(5,17,3,1,60000),(6,19,4,1,100000),(7,16,5,1,3000),(8,17,5,1,60000),(9,19,6,1,100000),(10,21,6,1,25000),(11,20,6,1,20000),(12,18,7,1,10000),(13,20,7,1,20000),(14,17,8,1,60000),(15,16,8,1,3000),(16,18,9,1,10000),(17,16,9,1,3000),(18,19,10,1,100000),(19,16,10,1,3000),(20,16,11,1,3000),(21,17,11,1,60000),(22,7,12,1,1200),(23,24,12,1,2500),(24,9,13,1,3500),(25,8,13,1,15600),(26,11,14,1,3500),(27,31,14,1,8000),(28,18,15,1,10000),(29,16,15,1,3000),(30,9,15,1,3500),(31,21,16,1,25000),(32,20,16,2,20000),(33,18,17,1,10000),(34,14,17,2,4100),(35,20,18,1,20000),(36,12,18,1,3000),(37,16,19,1,3000),(38,20,19,1,20000),(39,17,20,1,60000),(40,18,21,2,10000),(41,16,21,1,3000),(42,9,22,1,3500);
/*!40000 ALTER TABLE `producto_x_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_x_venta`
--

DROP TABLE IF EXISTS `producto_x_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto_x_venta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_producto` int DEFAULT NULL,
  `id_venta` int DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `precio_unitario` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `producto_x_venta_venta_FK` (`id_venta`),
  KEY `producto_x_venta_producto_FK` (`id_producto`),
  CONSTRAINT `producto_x_venta_producto_FK` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  CONSTRAINT `producto_x_venta_venta_FK` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_x_venta`
--

LOCK TABLES `producto_x_venta` WRITE;
/*!40000 ALTER TABLE `producto_x_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto_x_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `id_proveedor` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'Sorita','Av. Siempre Viva','3512555896','sorita@gmail.com'),(2,'MamaDog','Salta 55','3514788885','mama.dog@gmail.com');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `nombre_del_rol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'admin'),(2,'user');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_documento`
--

DROP TABLE IF EXISTS `tipo_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_documento` (
  `id_tipo_documento` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_documento`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_documento`
--

LOCK TABLES `tipo_documento` WRITE;
/*!40000 ALTER TABLE `tipo_documento` DISABLE KEYS */;
INSERT INTO `tipo_documento` VALUES (1,'DNI');
/*!40000 ALTER TABLE `tipo_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_envio`
--

DROP TABLE IF EXISTS `tipo_envio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_envio` (
  `id_tipo_envio` int NOT NULL,
  `desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_envio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_envio`
--

LOCK TABLES `tipo_envio` WRITE;
/*!40000 ALTER TABLE `tipo_envio` DISABLE KEYS */;
INSERT INTO `tipo_envio` VALUES (1,'A Domicilio'),(2,'A Sucursal');
/*!40000 ALTER TABLE `tipo_envio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `nombre_usuario` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `telefono` bigint DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `id_tipo_documento` int DEFAULT NULL,
  `numero_documento` int DEFAULT NULL,
  `numero_cliente` int DEFAULT NULL,
  `id_rol` int DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `fotoPerfil` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`nombre_usuario`),
  KEY `rol_idx` (`id_rol`),
  KEY `estado_idx` (`estado`),
  KEY `tipoDoc_idx` (`id_tipo_documento`),
  CONSTRAINT `fk_usuarios_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`),
  CONSTRAINT `fk_usuarios_tipoDoc` FOREIGN KEY (`id_tipo_documento`) REFERENCES `tipo_documento` (`id_tipo_documento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('admin','admin','admin',11111,'admin@admin','admin',1,11111111,1,1,_binary '\0','admin1234',NULL),('changuito','changuito',NULL,NULL,'changuito@mail.com','changuito',1,123456789,NULL,NULL,NULL,'changuito1234$',NULL),('changuito2','changuito2',NULL,NULL,'changuito2@mail.com','changuito2',1,222222,NULL,NULL,NULL,'changuito21234$','https://...'),('changuito3','changuito3',NULL,NULL,'changuito3@mail.com','changuito3',1,3333333,NULL,NULL,NULL,'changuito31234$',NULL),('changuito4','changuito4',NULL,NULL,'changuito4@mail.com','changuito4',1,44444,NULL,NULL,NULL,'changuito41234$',NULL),('Flor007','Flor',NULL,NULL,'florencianoelcarrillo@gmail.com','C',1,34456007,NULL,NULL,NULL,'12345678','https://i.ibb.co/mN1RqnQ/perfilflor.jpg'),('florcita','florcita','casa',303456,'florcita@gmail.com','florcita',1,12345678,1,2,_binary '','florcita',NULL),('florcitaa','Flor',NULL,NULL,'florencianoelcarrillo@gmail.com','Carrillo',1,34456007,NULL,NULL,NULL,'123456',NULL),('liomessi','liomessi',NULL,NULL,'liomessi@gmail.com','liomessi',1,12312312,NULL,NULL,NULL,'liomessi',NULL),('miledmin','milena',NULL,NULL,'mile@gmail.com','nic',1,0,NULL,NULL,NULL,'Mileadmin123!','https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png'),('pepesanchez','Pepe',NULL,NULL,'pepesanchez@gmail.com','Sanchez',1,34456008,NULL,NULL,NULL,'pepesanchez',NULL),('userFin','user usuario','Aires 25',9874502364,'userfi@gmail.com','final',1,15879642,NULL,NULL,NULL,'User.123','https://res.cloudinary.com/dgql9nx7t/image/upload/v1750164691/lt2gqesu4uugaknpcik0.png'),('vale21','Valentina','Bialet Masse 21',3541408654,'valeangelett11@gmail.com','Angeletti',1,65412307,NULL,NULL,NULL,'Valentina.21','https://res.cloudinary.com/dgql9nx7t/image/upload/v1750035534/vt9yrhjeucowzb0hwspa.jpg');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `id_venta` int NOT NULL AUTO_INCREMENT,
  `fecha` varchar(45) DEFAULT NULL,
  `nombre_usuario` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `id_pedido` int DEFAULT NULL,
  `monto` float DEFAULT NULL,
  `numero_factura` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `usuario_idx` (`nombre_usuario`),
  KEY `fk_ventas_nroPedido_idx` (`id_pedido`),
  CONSTRAINT `fk_ventas_usuario` FOREIGN KEY (`nombre_usuario`) REFERENCES `usuario` (`nombre_usuario`),
  CONSTRAINT `venta_pedido_FK` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-17 10:04:15
