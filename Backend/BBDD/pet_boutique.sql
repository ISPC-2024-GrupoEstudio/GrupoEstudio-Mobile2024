-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 16-09-2024 a las 00:59:45
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pet_boutique`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auth_group`
--

CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auth_group_permissions`
--

CREATE TABLE `auth_group_permissions` (
  `id` bigint(20) NOT NULL,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auth_permission`
--

CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `auth_permission`
--

INSERT INTO `auth_permission` (`id`, `name`, `content_type_id`, `codename`) VALUES
(1, 'Can add log entry', 1, 'add_logentry'),
(2, 'Can change log entry', 1, 'change_logentry'),
(3, 'Can delete log entry', 1, 'delete_logentry'),
(4, 'Can view log entry', 1, 'view_logentry'),
(5, 'Can add permission', 2, 'add_permission'),
(6, 'Can change permission', 2, 'change_permission'),
(7, 'Can delete permission', 2, 'delete_permission'),
(8, 'Can view permission', 2, 'view_permission'),
(9, 'Can add group', 3, 'add_group'),
(10, 'Can change group', 3, 'change_group'),
(11, 'Can delete group', 3, 'delete_group'),
(12, 'Can view group', 3, 'view_group'),
(13, 'Can add user', 4, 'add_user'),
(14, 'Can change user', 4, 'change_user'),
(15, 'Can delete user', 4, 'delete_user'),
(16, 'Can view user', 4, 'view_user'),
(17, 'Can add content type', 5, 'add_contenttype'),
(18, 'Can change content type', 5, 'change_contenttype'),
(19, 'Can delete content type', 5, 'delete_contenttype'),
(20, 'Can view content type', 5, 'view_contenttype'),
(21, 'Can add session', 6, 'add_session'),
(22, 'Can change session', 6, 'change_session'),
(23, 'Can delete session', 6, 'delete_session'),
(24, 'Can view session', 6, 'view_session'),
(25, 'Can add categoriaproductos', 7, 'add_categoriaproductos'),
(26, 'Can change categoriaproductos', 7, 'change_categoriaproductos'),
(27, 'Can delete categoriaproductos', 7, 'delete_categoriaproductos'),
(28, 'Can view categoriaproductos', 7, 'view_categoriaproductos'),
(29, 'Can add estadopedido', 8, 'add_estadopedido'),
(30, 'Can change estadopedido', 8, 'change_estadopedido'),
(31, 'Can delete estadopedido', 8, 'delete_estadopedido'),
(32, 'Can view estadopedido', 8, 'view_estadopedido'),
(33, 'Can add formadepago', 9, 'add_formadepago'),
(34, 'Can change formadepago', 9, 'change_formadepago'),
(35, 'Can delete formadepago', 9, 'delete_formadepago'),
(36, 'Can view formadepago', 9, 'view_formadepago'),
(37, 'Can add pedidos', 10, 'add_pedidos'),
(38, 'Can change pedidos', 10, 'change_pedidos'),
(39, 'Can delete pedidos', 10, 'delete_pedidos'),
(40, 'Can view pedidos', 10, 'view_pedidos'),
(41, 'Can add proveedores', 11, 'add_proveedores'),
(42, 'Can change proveedores', 11, 'change_proveedores'),
(43, 'Can delete proveedores', 11, 'delete_proveedores'),
(44, 'Can view proveedores', 11, 'view_proveedores'),
(45, 'Can add roles', 12, 'add_roles'),
(46, 'Can change roles', 12, 'change_roles'),
(47, 'Can delete roles', 12, 'delete_roles'),
(48, 'Can view roles', 12, 'view_roles'),
(49, 'Can add tipodoc', 13, 'add_tipodoc'),
(50, 'Can change tipodoc', 13, 'change_tipodoc'),
(51, 'Can delete tipodoc', 13, 'delete_tipodoc'),
(52, 'Can view tipodoc', 13, 'view_tipodoc'),
(53, 'Can add tipoenvio', 14, 'add_tipoenvio'),
(54, 'Can change tipoenvio', 14, 'change_tipoenvio'),
(55, 'Can delete tipoenvio', 14, 'delete_tipoenvio'),
(56, 'Can view tipoenvio', 14, 'view_tipoenvio'),
(57, 'Can add productosxcarrito', 15, 'add_productosxcarrito'),
(58, 'Can change productosxcarrito', 15, 'change_productosxcarrito'),
(59, 'Can delete productosxcarrito', 15, 'delete_productosxcarrito'),
(60, 'Can view productosxcarrito', 15, 'view_productosxcarrito'),
(61, 'Can add usuarios', 16, 'add_usuarios'),
(62, 'Can change usuarios', 16, 'change_usuarios'),
(63, 'Can delete usuarios', 16, 'delete_usuarios'),
(64, 'Can view usuarios', 16, 'view_usuarios'),
(65, 'Can add productos', 17, 'add_productos'),
(66, 'Can change productos', 17, 'change_productos'),
(67, 'Can delete productos', 17, 'delete_productos'),
(68, 'Can view productos', 17, 'view_productos'),
(69, 'Can add productosxpedido', 18, 'add_productosxpedido'),
(70, 'Can change productosxpedido', 18, 'change_productosxpedido'),
(71, 'Can delete productosxpedido', 18, 'delete_productosxpedido'),
(72, 'Can view productosxpedido', 18, 'view_productosxpedido'),
(73, 'Can add productosxventa', 19, 'add_productosxventa'),
(74, 'Can change productosxventa', 19, 'change_productosxventa'),
(75, 'Can delete productosxventa', 19, 'delete_productosxventa'),
(76, 'Can view productosxventa', 19, 'view_productosxventa'),
(77, 'Can add ventas', 20, 'add_ventas'),
(78, 'Can change ventas', 20, 'change_ventas'),
(79, 'Can delete ventas', 20, 'delete_ventas'),
(80, 'Can view ventas', 20, 'view_ventas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auth_user`
--

CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL,
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
  `telefono` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `auth_user`
--

INSERT INTO `auth_user` (`id`, `password`, `last_login`, `is_superuser`, `username`, `first_name`, `last_name`, `email`, `is_staff`, `is_active`, `date_joined`, `telefono`) VALUES
(1, 'pbkdf2_sha256$600000$BmaOyS8u0DmYzloEcHhA76$Vu+vEccgqjgD8QDiNcLynXQTEBemOxDoVzl+mtFaB/c=', '2024-06-09 23:00:29.254879', 1, 'admin', '', '', 'ea.samsam@gmail.com', 1, 1, '2024-05-14 00:53:14.233295', NULL),
(2, 'pbkdf2_sha256$600000$eyB9dYwVYYodufs4QFTzHr$7xXtzA5c65B2YvyN3lhwN9MkHDvIj0MODmqZMqgouNI=', '2024-06-04 03:22:12.816197', 1, 'superusuario', '', '', 'superusuario@mail.com', 1, 1, '2024-06-03 23:44:29.429245', NULL),
(3, 'pbkdf2_sha256$600000$zrkcbuDDZKTedHPtiuwh4i$4jS3iNmIr1iP3EMaTQG+OgsUTkV6+sXPEMwMuIYrfVI=', NULL, 0, 'migue', '', '', '', 0, 1, '2024-06-03 23:46:09.689472', NULL),
(4, 'pbkdf2_sha256$600000$EeFB2w4aapVeq3H0DgeTQ4$MvV8uoqPEk3z8kwqyA8p66m0vd0//FpzyvwW9RF1jts=', NULL, 0, 'flor', 'flor', 'flor', 'flor@mail.com', 0, 1, '2024-06-04 00:32:40.461467', NULL),
(5, 'pbkdf2_sha256$600000$WG0RmOL1aO9aZEwm1zepcc$CUMwBEPxv2HQ6mdRK9fuE99nZAvoZhsd5g9HIV9lQdg=', NULL, 0, 'flor1', 'flor1', 'flor1', 'flor1@mail.com', 0, 1, '2024-06-04 00:36:32.181809', NULL),
(6, 'pbkdf2_sha256$600000$afbvXs8anBIWFxnzr9OVgs$sVNjP4CKb1zubTQ+mr1g2BzhDPSlaO21SBO7F2F5gEw=', '2024-06-04 03:29:53.657443', 0, 'changuito4', 'changuito4', 'changuito4', 'changuito4@mail.com', 0, 1, '2024-06-04 01:07:23.802085', NULL),
(7, 'pbkdf2_sha256$600000$rxWUYDNjJQkxvGKpJHG3gz$MT5rEYfAsXY7TYeXSTd6qhc/zsHNPzlU98pgajhm3gI=', '2024-06-14 01:12:07.394014', 0, 'liomessi', 'liomessi', 'liomessi', 'liomessi@gmail.com', 0, 1, '2024-06-14 01:11:58.218342', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auth_user_groups`
--

CREATE TABLE `auth_user_groups` (
  `id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auth_user_user_permissions`
--

CREATE TABLE `auth_user_user_permissions` (
  `id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `id_carrito` bigint(20) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `nombre_usuario` varchar(100) NOT NULL,
  `cantidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `carrito`
--

INSERT INTO `carrito` (`id_carrito`, `id_producto`, `nombre_usuario`, `cantidad`) VALUES
(1, 18, 'admin', 1),
(2, 19, 'admin', 1),
(3, 19, 'admin', 1),
(6, 2, 'liomessi', 1),
(7, 10, 'liomessi', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_producto`
--

CREATE TABLE `categoria_producto` (
  `id_categoria_producto` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `categoria_producto`
--

INSERT INTO `categoria_producto` (`id_categoria_producto`, `nombre`, `descripcion`) VALUES
(1, 'Accesorios', NULL),
(2, 'Cuchas', NULL),
(3, 'Juguetes', NULL),
(4, 'Ropa', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `django_admin_log`
--

CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext DEFAULT NULL,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) UNSIGNED NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ;

--
-- Volcado de datos para la tabla `django_admin_log`
--

INSERT INTO `django_admin_log` (`id`, `action_time`, `object_id`, `object_repr`, `action_flag`, `change_message`, `content_type_id`, `user_id`) VALUES
(1, '2024-05-14 01:17:03.365380', '1', 'Tipodoc object (1)', 1, '[{\"added\": {}}]', 13, 1),
(2, '2024-06-03 23:46:09.847634', '3', 'migue', 1, '[{\"added\": {}}]', 4, 2),
(3, '2024-06-09 23:27:29.119341', 'admin', 'admin', 1, '[{\"added\": {}}]', 24, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `django_content_type`
--

CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `django_content_type`
--

INSERT INTO `django_content_type` (`id`, `app_label`, `model`) VALUES
(1, 'admin', 'logentry'),
(3, 'auth', 'group'),
(2, 'auth', 'permission'),
(4, 'auth', 'user'),
(5, 'contenttypes', 'contenttype'),
(7, 'PetBoutiqueApp', 'categoriaproductos'),
(8, 'PetBoutiqueApp', 'estadopedido'),
(9, 'PetBoutiqueApp', 'formadepago'),
(10, 'PetBoutiqueApp', 'pedidos'),
(21, 'PetBoutiqueApp', 'producto'),
(17, 'PetBoutiqueApp', 'productos'),
(15, 'PetBoutiqueApp', 'productosxcarrito'),
(18, 'PetBoutiqueApp', 'productosxpedido'),
(19, 'PetBoutiqueApp', 'productosxventa'),
(11, 'PetBoutiqueApp', 'proveedores'),
(22, 'PetBoutiqueApp', 'rol'),
(12, 'PetBoutiqueApp', 'roles'),
(13, 'PetBoutiqueApp', 'tipodoc'),
(23, 'PetBoutiqueApp', 'tipodocumento'),
(14, 'PetBoutiqueApp', 'tipoenvio'),
(24, 'PetBoutiqueApp', 'usuario'),
(16, 'PetBoutiqueApp', 'usuarios'),
(20, 'PetBoutiqueApp', 'ventas'),
(6, 'sessions', 'session');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `django_migrations`
--

CREATE TABLE `django_migrations` (
  `id` bigint(20) NOT NULL,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `django_migrations`
--

INSERT INTO `django_migrations` (`id`, `app`, `name`, `applied`) VALUES
(1, 'contenttypes', '0001_initial', '2024-05-14 00:21:41.599596'),
(2, 'auth', '0001_initial', '2024-05-14 00:21:42.255499'),
(3, 'admin', '0001_initial', '2024-05-14 00:21:42.606375'),
(4, 'admin', '0002_logentry_remove_auto_add', '2024-05-14 00:21:42.624687'),
(5, 'admin', '0003_logentry_add_action_flag_choices', '2024-05-14 00:21:42.640807'),
(6, 'contenttypes', '0002_remove_content_type_name', '2024-05-14 00:21:42.780685'),
(7, 'auth', '0002_alter_permission_name_max_length', '2024-05-14 00:21:42.857712'),
(8, 'auth', '0003_alter_user_email_max_length', '2024-05-14 00:21:42.941679'),
(9, 'auth', '0004_alter_user_username_opts', '2024-05-14 00:21:42.951615'),
(10, 'auth', '0005_alter_user_last_login_null', '2024-05-14 00:21:43.021774'),
(11, 'auth', '0006_require_contenttypes_0002', '2024-05-14 00:21:43.026571'),
(12, 'auth', '0007_alter_validators_add_error_messages', '2024-05-14 00:21:43.053583'),
(13, 'auth', '0008_alter_user_username_max_length', '2024-05-14 00:21:43.131121'),
(14, 'auth', '0009_alter_user_last_name_max_length', '2024-05-14 00:21:43.197869'),
(15, 'auth', '0010_alter_group_name_max_length', '2024-05-14 00:21:43.288842'),
(16, 'auth', '0011_update_proxy_permissions', '2024-05-14 00:21:43.298815'),
(17, 'auth', '0012_alter_user_first_name_max_length', '2024-05-14 00:21:43.371063'),
(18, 'sessions', '0001_initial', '2024-05-14 00:21:43.422532'),
(19, 'PetBoutiqueApp', '0001_initial', '2024-05-14 01:14:09.363215');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `django_session`
--

CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `django_session`
--

INSERT INTO `django_session` (`session_key`, `session_data`, `expire_date`) VALUES
('010eis9ppjga0wgli93yglkm8dku03h0', '.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEJV9:qvc1rrSiXy9QHn8H_VZHlHNqwra1lpnwXjV5uDJRamk', '2024-06-18 02:02:15.023347'),
('0g1n19o3351him3acedaq41n57ktp95v', '.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEJqh:gwas0tXmdZIO8QzRAUlc4NAiX3rInTe7N8iFHzis8ys', '2024-06-18 02:24:31.469433'),
('4nh7g25aac7vmhy32gy00f5so17ugdfs', '.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEIlB:uSflTvxq3QgmTY3wTkkXEu5FrkXdWvuM8xhXPtHLbms', '2024-06-18 01:14:45.104524'),
('bxbddoq7iq9cmkp5oylxjcrx78b0kdwq', '.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEJFl:0YYvd70nUbbcThj_Lbx22ji9DdJwBIbJg5bbtnNipQ8', '2024-06-18 01:46:21.611989'),
('efcgqb5mv3se6jyebmih7fup85o38muk', '.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEKkW:TGFl6BEstn6v_JQKmSYhnqs8czWhu4YmLbbFzKfQAKU', '2024-06-18 03:22:12.817398'),
('f566mxmsy0rb80yuj8wxcc2caqjdlavz', '.eJxVjDsOwjAQBe_iGln-4B8lfc5grb1rHEC2FCcV4u4QKQW0b2bei0XY1hq3QUuckV2YZKffLUF-UNsB3qHdOs-9rcuc-K7wgw4-daTn9XD_DiqM-q2NDzIkp61XGYyyxqezU4TFOqERPTiLpgRFXgrIMocihba5CFKSyBj2_gDGwzeB:1sFNJs:4dPBONTWpapfTjDPwff2IMuDGlEUvcIyZ044iaVqODw', '2024-06-21 00:19:00.019106'),
('ftiq4pn2hcj5hm4jgywoon33ok6erdao', '.eJxVjDsOwjAQBe_iGln-4B8lfc5grb1rHEC2FCcV4u4QKQW0b2bei0XY1hq3QUuckV2YZKffLUF-UNsB3qHdOs-9rcuc-K7wgw4-daTn9XD_DiqM-q2NDzIkp61XGYyyxqezU4TFOqERPTiLpgRFXgrIMocihba5CFKSyBj2_gDGwzeB:1sGRWX:sc8J1Oki6F1By_dDF19_Y03Yc-YSHgISRVk_zf_bjfw', '2024-06-23 23:00:29.256069'),
('giast6t3jg0dnhzrsemcbbrke5s55iie', '.eJxVjE0OwiAYBe_C2pCWFgou3XsGwvcnVQNJaVfGu2uTLnT7Zua9VEzbmuPWeIkzqbNy6vS7QcIHlx3QPZVb1VjLusygd0UftOlrJX5eDvfvIKeWv7UPNpBxyKlHz8EQosFEMHU-sFhCARkna8E7BHbCQiLGDAOOfQcyqPcHHyc5mA:1sEKrx:8DSHaEzqI97dWOY8XufItGuhra6BJ-MjZRd57Gqlodo', '2024-06-18 03:29:53.658782'),
('goeen5pibwxuxwy965ihg6ew6lqt51oy', '.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEJ3U:8-_ACTGKWe80BwBgwtjlDSYKj6XK_3sESld8dirTwhk', '2024-06-18 01:33:40.718490'),
('gpoco1wslmm20tjtik1jb7pjf8qusn81', '.eJxVjDsOwjAQBe_iGln-4B8lfc5grb1rHEC2FCcV4u4QKQW0b2bei0XY1hq3QUuckV2YZKffLUF-UNsB3qHdOs-9rcuc-K7wgw4-daTn9XD_DiqM-q2NDzIkp61XGYyyxqezU4TFOqERPTiLpgRFXgrIMocihba5CFKSyBj2_gDGwzeB:1s6ghN:5iN-87VmLnj9rHozF-5Tf_LCA2LJRoYOr_LpI0AHMkU', '2024-05-28 01:11:21.309976'),
('h3p6ditnsbbh3vo3fzsfmh89edj3nvmh', '.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEIiK:jhCVtjqST-fFBz0OEcPgQX6hwdSZ-KJteKgmkvRnWR8', '2024-06-18 01:11:48.675244'),
('hgd9tmhskn8095ea01kl6akwlbubky6x', '.eJxVjEEOwiAQRe_C2pBCM2Vw6d4zkBkYpGogKe3KeHdt0oVu_3vvv1SgbS1h67KEOamzcur0uzHFh9QdpDvVW9Ox1XWZWe-KPmjX15bkeTncv4NCvXxrb52J4-AMSkIagJHZe2HyI9rsYIoGTTYJSCYRm9BnyNlaFAM8Aqn3B-IXOBw:1sHvU7:bn8zB2doZj9Di62-h6j2BFFG-LunFVhcTegzrf3SjaU', '2024-06-28 01:12:07.395278'),
('jhuwkn2oznylug8d92hy85eucqxbx86i', '.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEJ8Q:qWp5X7qkP_meDec-S9a0wsP8QyDplXvpiEMjODo-ljQ', '2024-06-18 01:38:46.265614'),
('lhsxk2dzbudum5ztejx58mz7vrl7zvvu', '.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEHNt:XB90rHOZBlY2S4s0m29xsIahCPCrpn0AanXt9QzefBo', '2024-06-17 23:46:37.848610'),
('mg2gq8fh572z7ga1piy8tdadma515kdx', '.eJxVjDsOwjAQBe_iGln-4B8lfc5grb1rHEC2FCcV4u4QKQW0b2bei0XY1hq3QUuckV2YZKffLUF-UNsB3qHdOs-9rcuc-K7wgw4-daTn9XD_DiqM-q2NDzIkp61XGYyyxqezU4TFOqERPTiLpgRFXgrIMocihba5CFKSyBj2_gDGwzeB:1sFMyD:QqcaH-yW7c239a8-eGhmaixScfeVgicE5OP3POf79Wg', '2024-06-20 23:56:37.918892'),
('nzu9gly3w7alp86e7tekk1csa6ur8g1s', '.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEJTX:UOA4brNSWmG5DIKXV8GEKjG5TNj5BYjsFIhkTisVCBc', '2024-06-18 02:00:35.934325'),
('ok30qfnt83lw5gyqvkzf97l2uxbyj6kj', '.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEKYC:T8KnpFFsDzgc-QkdyNkTbak3ylCGKGHZ_xPOGj1pLbg', '2024-06-18 03:09:28.587213'),
('w2jge9tp0i76a9lusiscxs9kihj4cfwm', '.eJxVjDsOwjAQBe_iGln-4B8lfc5grb1rHEC2FCcV4u4QKQW0b2bei0XY1hq3QUuckV2YZKffLUF-UNsB3qHdOs-9rcuc-K7wgw4-daTn9XD_DiqM-q2NDzIkp61XGYyyxqezU4TFOqERPTiLpgRFXgrIMocihba5CFKSyBj2_gDGwzeB:1sGRN2:jxNk2Kkvw_GsH40k4qfD1iVTRV2F3lWp0T3aX7jzf3g', '2024-06-23 22:50:40.672530'),
('wupweyndjeh97mwg5vpsvuaewnti3pyb', '.eJxVjDsOwjAQBe_iGln-4B8lfc5grb1rHEC2FCcV4u4QKQW0b2bei0XY1hq3QUuckV2YZKffLUF-UNsB3qHdOs-9rcuc-K7wgw4-daTn9XD_DiqM-q2NDzIkp61XGYyyxqezU4TFOqERPTiLpgRFXgrIMocihba5CFKSyBj2_gDGwzeB:1sFN5E:jv5TbWghMPLQj7uSS-vGhCN09gkHVGUPqW_L3KOweYs', '2024-06-21 00:03:52.809016'),
('z5mlugvfe7n88gnm0gnwbums8nnsz45j', '.eJxVjMsOwiAQRf-FtSEd3rh07zcQBhipGkhKuzL-uzbpQrf3nHNfLMRtrWEbZQlzZmcm2Ol3w5gepe0g32O7dZ56W5cZ-a7wgw5-7bk8L4f7d1DjqN9aCuXAZQSnAKzMwieJNEmFxmpvDAkPSUePGomokNQ2FQVRWaA0FcHeH8ICN6c:1sEIzd:aNFjmED8LbS5rjf16IfSM5eKc29jK3wIXJsWOXZONec', '2024-06-18 01:29:41.186781');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_pedido`
--

CREATE TABLE `estado_pedido` (
  `id_estado_pedido` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `estado_pedido`
--

INSERT INTO `estado_pedido` (`id_estado_pedido`, `nombre`) VALUES
(1, 'creado'),
(2, 'finalizado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `forma_de_pago`
--

CREATE TABLE `forma_de_pago` (
  `id_forma_de_pago` int(11) NOT NULL,
  `desc` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `id_estado_pedido` int(11) DEFAULT NULL,
  `nombre_usuario` varchar(12) DEFAULT NULL,
  `id_tipo_de_envio` int(11) DEFAULT NULL,
  `domicilio_envio` varchar(50) DEFAULT NULL,
  `id_forma_de_pago` int(11) DEFAULT NULL,
  `numero_pedido` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id_pedido`, `fecha`, `id_estado_pedido`, `nombre_usuario`, `id_tipo_de_envio`, `domicilio_envio`, `id_forma_de_pago`, `numero_pedido`) VALUES
(1, '2024-06-14 01:12:38', 1, 'liomessi', NULL, NULL, NULL, 1),
(2, '2024-06-14 01:22:07', 1, 'liomessi', NULL, NULL, NULL, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `petboutiqueapp_customuser`
--

CREATE TABLE `petboutiqueapp_customuser` (
  `id` bigint(20) NOT NULL,
  `password` varchar(128) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `is_superuser` tinyint(1) DEFAULT 0,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) DEFAULT NULL,
  `last_name` varchar(150) DEFAULT NULL,
  `is_staff` tinyint(1) DEFAULT 0,
  `is_active` tinyint(1) DEFAULT 1,
  `date_joined` datetime DEFAULT NULL,
  `email` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `petboutiqueapp_customuser`
--

INSERT INTO `petboutiqueapp_customuser` (`id`, `password`, `last_login`, `is_superuser`, `username`, `first_name`, `last_name`, `is_staff`, `is_active`, `date_joined`, `email`) VALUES
(3, 'pbkdf2_sha256$600000$Tnznn5MhmJMwrazCmT7doY$ySIxbt6hx3Jr/+l4FkJSPXYpIu+GO/H9VJ4Z6enydsY=', '2024-06-03 23:34:46', 1, 'admin', '', '', 1, 1, '2024-06-03 23:23:18', 'admin@admin.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `petboutiqueapp_roles`
--

CREATE TABLE `petboutiqueapp_roles` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` longtext NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `fecha_creacion` datetime(6) NOT NULL,
  `fecha_modificacion` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `petboutiqueapp_roles_permisos`
--

CREATE TABLE `petboutiqueapp_roles_permisos` (
  `id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  `authpermission_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `stock_actual` int(11) DEFAULT NULL,
  `id_proveedor` int(11) DEFAULT NULL,
  `stock_minimo` int(11) DEFAULT NULL,
  `id_categoria_producto` int(11) DEFAULT NULL,
  `image_url` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre`, `descripcion`, `precio`, `stock_actual`, `id_proveedor`, `stock_minimo`, `id_categoria_producto`, `image_url`) VALUES
(1, 'Modificado', 'Collar entretejido con term reforzadas', 5000, 20, 1, 5, 1, 'https://i.ibb.co/WcVM7Nj/Collar-Gato.png'),
(2, 'Cucha Perro', 'Cucha para perro madera', 45000, 13, 2, 2, 2, 'https://i.ibb.co/VWr3jPj/Cucha-Exterior.png'),
(3, 'Buzo Adidog', 'Buzo algodon con friza elastizado', 8000, 38, 1, 8, 4, 'https://i.ibb.co/QmvL3pK/Buzo-Miami.png'),
(6, 'Capa ', 'Para la lluvia con capucha', 8000, 22, 2, 2, 4, 'https://i.ibb.co/hDsc0Hz/Buzo-Puffer.png'),
(7, 'Huesito', 'Hueso de fantasía ', 1200, 20, 1, 5, 3, 'https://i.ibb.co/bJn9zFr/Huesos.png'),
(8, 'Ponchito', 'Poncho de lana con detalles en morley', 15600, 10, 1, 1, 2, 'https://i.ibb.co/xg9KJD1/Sweter-Gato.png'),
(9, 'Cucha calabaza', 'Cucha estilo calabaza para gatos, color naranja, con relleno de guata y una abertura principal.', 3500, 10, 1, 1, 2, 'https://i.ibb.co/5vVSGxk/Calabaza.png'),
(10, 'Cucha Madera', 'Cucha de madera para perros ideal para exteriores, con techo corredizo, comedero y bebedero de agua. Detalles en negro en sus bordes', 3500, 12, 1, 1, 2, 'https://i.ibb.co/Yj42b5p/Cucha-Exterior.png'),
(11, 'Cucha Cat', 'Cama para gatos con forma de gato, cubierta con peluche e interiór de felpa. Incluye un pompón colgante como juguete.', 3500, 14, 1, 1, 2, 'https://i.ibb.co/TKPQhjq/Forma-Gato.png'),
(12, 'Cucha Sillon', 'Cama estilo sillon para perros con relleno de guata, abierto, coor gris con detalles blancos en sus bordes.', 3000, 13, 2, 1, 2, 'https://i.ibb.co/FWr40Sw/Sillon.png'),
(13, 'Cama colgante', 'Cama colgante para gatos con cubierta azul oscuro con textura, abertura para su entrada e interior acolchado', 3800, 15, 2, 1, 2, 'https://i.ibb.co/s6TQ6SM/Gato-Colgante.png'),
(14, 'Cama donna', 'Cama tipo Donna con relleno de guata y cubierta de felpa para perros en varios colores', 4100, 20, 2, 1, 2, 'https://i.ibb.co/tLkMRj2/Donnas.png'),
(15, 'Collar para Gato', 'Collar para gato con broche de seguridad y cascabel en varios colores.', 3000, 30, 1, 1, 2, 'https://i.ibb.co/g3Fgqww/Collar-Gato.png'),
(16, 'Collar para Gato', 'Collar para gato con broche de seguridad y cascabel en varios colores.', 3000, 30, 1, 1, 1, 'https://i.ibb.co/g3Fgqww/Collar-Gato.png'),
(17, 'Bolso transportador', 'Bolso trasportador para mascotas con interior de felpa, red para una mejor oxigenación y manijas de agarre seguro.', 60000, 20, 1, 1, 1, 'https://i.ibb.co/61VqkHH/Transportador.png'),
(18, 'Collar para perro', 'Collares para perros con broche de seguridad y con estampados diversos.', 10000, 30, 1, 1, 1, 'https://i.ibb.co/jvZxXgg/Collar-Perro.png'),
(19, 'Rascador para gato', 'Rascador para gato de 4 pisos con bolsa colgante, cucha con salidas, y juguete en su piso superior. Todos los pisos estan cubiertos de felpa con detalles en gris.', 100000, 12, 2, 1, 1, 'https://i.ibb.co/5jYk6K5/Rascador.png'),
(20, 'Correa extensible', 'Correa extensible para mascotas con gancho para enganchar en el collar y un largo total de 1 metro con botón regulable.', 20000, 13, 2, 1, 1, 'https://i.ibb.co/Rp00xwC/Correa.png'),
(21, 'Comedero + Dispenser', 'Base blanca con comedero transparente en forma de gato y dispensador de agua.', 25000, 14, 2, 1, 1, 'https://i.ibb.co/hMxnRKW/Vertedero.png'),
(22, 'Caña con pluma', 'Juguete de tipo caña con una pluma en su extremo para jugar con gatos.', 3000, 23, 2, 1, 3, 'https://i.ibb.co/m987M63/Jueguete-Gato.png'),
(23, 'Hueso para perro', 'Huesos de cuero comestible para perros', 1500, 24, 2, 1, 3, 'https://i.ibb.co/mG1rYpS/Huesos.png'),
(24, 'Peluche Felpa', 'Peluche de felpa tipo patita para gatos. Varios colores.', 2500, 24, 2, 1, 3, 'https://i.ibb.co/wSTP5j6/Peluche-Gato.png'),
(25, 'Sogas trenzadas', 'Sogas trenzadas para jugar con perros. Varios colores.', 2500, 25, 1, 1, 3, 'https://i.ibb.co/CMy9xZ8/Sogas.png'),
(26, 'Raton de Juguete', 'Ratón de juguete con control remoto para divertirte con tu gato.', 4500, 17, 1, 1, 3, 'https://i.ibb.co/fYNH3sv/Raton.png'),
(27, 'Lanzador de pelota', 'Lanzador automático de pelotas para perros. Incluuye 6 pelotas de regalo.', 5500, 17, 1, 1, 3, 'https://i.ibb.co/QDR8RzX/Arrojador-Automatico.png'),
(28, 'Chaleco Puffer', 'Chaleco estilo puffer para perro mediano, color verde militar con interior de corderito.', 13000, 13, 1, 1, 4, 'https://i.ibb.co/sVDqPDc/Buzo-Puffer.png'),
(29, 'Buzo Orejas', 'Bolso trasportador para mascotas con interior de felpa, red para una mejor oxigenación y manijas de agarre seguro.', 16000, 14, 1, 1, 4, 'https://i.ibb.co/7RsWs21/Buzo-Orejas-Gato.png'),
(30, 'Buzo Miami', 'Buzo para perro mediano. Color verde oscuro con letras \"Miami\" color dorado y detalles en color blanco y rojo. Mangas largas.', 17000, 12, 1, 1, 4, 'https://i.ibb.co/WzWx73x/Buzo-Miami.png'),
(31, 'Gorro Pingüino', 'Gorro para gato color celeste en forma de pingüino con detalles en color blanco y amarillo.', 8000, 18, 2, 1, 4, 'https://i.ibb.co/mFRQjc8/Gorro-Pinguino.png'),
(32, 'Buzo Peluche', 'Buzo de peluche para perro chico. Varios colores. Con capucha con detalles de orejas. Detalles en blanco. Interior de corderito.', 9000, 21, 2, 1, 4, 'https://i.ibb.co/4SSnNWM/Buzo-Felpa.png'),
(33, 'Sweater Lana', 'Sweater tejido de lana para gato. Color celeste con cuello y mangas cortas.', 8700, 32, 2, 1, 4, 'https://i.ibb.co/74cyQ2L/Sweter-Gato.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto_x_pedido`
--

CREATE TABLE `producto_x_pedido` (
  `id` bigint(20) NOT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_pedido` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto_x_pedido`
--

INSERT INTO `producto_x_pedido` (`id`, `id_producto`, `id_pedido`, `cantidad`, `precio`) VALUES
(1, 8, 1, 1, 3500),
(2, 8, 1, 1, 15600),
(3, 2, 2, 1, 45000),
(4, 10, 2, 1, 3500);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto_x_venta`
--

CREATE TABLE `producto_x_venta` (
  `id` bigint(20) NOT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_venta` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id_proveedor` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id_proveedor`, `nombre`, `direccion`, `telefono`, `mail`) VALUES
(1, 'Sorita', 'Av. Siempre Viva', '3512555896', 'sorita@gmail.com'),
(2, 'MamaDog', 'Salta 55', '3514788885', 'mama.dog@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL,
  `nombre_del_rol` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_rol`, `nombre_del_rol`) VALUES
(1, 'admin'),
(2, 'user');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_documento`
--

CREATE TABLE `tipo_documento` (
  `id_tipo_documento` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `tipo_documento`
--

INSERT INTO `tipo_documento` (`id_tipo_documento`, `nombre`) VALUES
(1, 'DNI');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_envio`
--

CREATE TABLE `tipo_envio` (
  `id_tipo_envio` int(11) NOT NULL,
  `desc` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `nombre_usuario` varchar(12) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `id_tipo_documento` int(11) DEFAULT NULL,
  `numero_documento` int(11) DEFAULT NULL,
  `numero_cliente` int(11) DEFAULT NULL,
  `id_rol` int(11) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `perfil_imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`nombre_usuario`, `nombre`, `direccion`, `telefono`, `email`, `apellido`, `id_tipo_documento`, `numero_documento`, `numero_cliente`, `id_rol`, `estado`, `password`, `perfil_imagen`) VALUES
('admin', 'admin', 'admin', 11111, 'admin@admin', 'admin', 1, 11111111, 1, 1, b'0', 'admin1234', NULL),
('changuito', 'changuito', NULL, NULL, 'changuito@mail.com', 'changuito', 1, 123456789, NULL, NULL, NULL, 'changuito1234$', NULL),
('changuito2', 'changuito2', NULL, NULL, 'changuito2@mail.com', 'changuito2', 1, 222222, NULL, NULL, NULL, 'changuito21234$', NULL),
('changuito3', 'changuito3', NULL, NULL, 'changuito3@mail.com', 'changuito3', 1, 3333333, NULL, NULL, NULL, 'changuito31234$', NULL),
('changuito4', 'changuito4', NULL, NULL, 'changuito4@mail.com', 'changuito4', 1, 44444, NULL, NULL, NULL, 'changuito41234$', NULL),
('florcita', 'florcita', 'casa', 303456, 'florcita@gmail.com', 'florcita', 1, 12345678, 1, 2, b'1', 'florcita', NULL),
('liomessi', 'liomessi', NULL, NULL, 'liomessi@gmail.com', 'liomessi', 1, 12312312, NULL, NULL, NULL, 'liomessi', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id_venta` int(11) NOT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `nombre_usuario` varchar(12) DEFAULT NULL,
  `id_pedido` int(11) DEFAULT NULL,
  `monto` float DEFAULT NULL,
  `numero_factura` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `auth_group`
--
ALTER TABLE `auth_group`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indices de la tabla `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  ADD KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`);

--
-- Indices de la tabla `auth_permission`
--
ALTER TABLE `auth_permission`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`);

--
-- Indices de la tabla `auth_user`
--
ALTER TABLE `auth_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indices de la tabla `auth_user_groups`
--
ALTER TABLE `auth_user_groups`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  ADD KEY `auth_user_groups_group_id_97559544_fk_auth_group_id` (`group_id`);

--
-- Indices de la tabla `auth_user_user_permissions`
--
ALTER TABLE `auth_user_user_permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  ADD KEY `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` (`permission_id`);

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`id_carrito`),
  ADD KEY `carrito_producto_FK` (`id_producto`),
  ADD KEY `carrito_usuario_FK` (`nombre_usuario`);

--
-- Indices de la tabla `categoria_producto`
--
ALTER TABLE `categoria_producto`
  ADD PRIMARY KEY (`id_categoria_producto`);

--
-- Indices de la tabla `django_admin_log`
--
ALTER TABLE `django_admin_log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  ADD KEY `django_admin_log_user_id_c564eba6_fk_auth_user_id` (`user_id`);

--
-- Indices de la tabla `django_content_type`
--
ALTER TABLE `django_content_type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`);

--
-- Indices de la tabla `django_migrations`
--
ALTER TABLE `django_migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `django_session`
--
ALTER TABLE `django_session`
  ADD PRIMARY KEY (`session_key`),
  ADD KEY `django_session_expire_date_a5c62663` (`expire_date`);

--
-- Indices de la tabla `estado_pedido`
--
ALTER TABLE `estado_pedido`
  ADD PRIMARY KEY (`id_estado_pedido`);

--
-- Indices de la tabla `forma_de_pago`
--
ALTER TABLE `forma_de_pago`
  ADD PRIMARY KEY (`id_forma_de_pago`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD UNIQUE KEY `pedido_unique` (`numero_pedido`),
  ADD KEY `estado_idx` (`id_estado_pedido`),
  ADD KEY `usuario_idx` (`nombre_usuario`),
  ADD KEY `fk_pedido_tipoEnvio_idx` (`id_tipo_de_envio`),
  ADD KEY `fk_pedido_formaDePago_idx` (`id_forma_de_pago`);

--
-- Indices de la tabla `petboutiqueapp_customuser`
--
ALTER TABLE `petboutiqueapp_customuser`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `customuser_unique` (`username`),
  ADD UNIQUE KEY `customuser_unique_1` (`email`);

--
-- Indices de la tabla `petboutiqueapp_roles`
--
ALTER TABLE `petboutiqueapp_roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `petboutiqueapp_roles_permisos`
--
ALTER TABLE `petboutiqueapp_roles_permisos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `PetBoutiqueApp_roles_per_roles_id_authpermission__c85b8cb8_uniq` (`roles_id`,`authpermission_id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `proveedor_idx` (`id_proveedor`),
  ADD KEY `fk_productos_categoria_idx` (`id_categoria_producto`);

--
-- Indices de la tabla `producto_x_pedido`
--
ALTER TABLE `producto_x_pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `producto_x_pedido_producto_FK` (`id_producto`),
  ADD KEY `producto_x_pedido_pedido_FK` (`id_pedido`);

--
-- Indices de la tabla `producto_x_venta`
--
ALTER TABLE `producto_x_venta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `producto_x_venta_venta_FK` (`id_venta`),
  ADD KEY `producto_x_venta_producto_FK` (`id_producto`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id_proveedor`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `tipo_documento`
--
ALTER TABLE `tipo_documento`
  ADD PRIMARY KEY (`id_tipo_documento`);

--
-- Indices de la tabla `tipo_envio`
--
ALTER TABLE `tipo_envio`
  ADD PRIMARY KEY (`id_tipo_envio`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`nombre_usuario`),
  ADD KEY `rol_idx` (`id_rol`),
  ADD KEY `estado_idx` (`estado`),
  ADD KEY `tipoDoc_idx` (`id_tipo_documento`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `usuario_idx` (`nombre_usuario`),
  ADD KEY `fk_ventas_nroPedido_idx` (`id_pedido`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `auth_group`
--
ALTER TABLE `auth_group`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `auth_permission`
--
ALTER TABLE `auth_permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT de la tabla `auth_user`
--
ALTER TABLE `auth_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `auth_user_groups`
--
ALTER TABLE `auth_user_groups`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `auth_user_user_permissions`
--
ALTER TABLE `auth_user_user_permissions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `id_carrito` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `categoria_producto`
--
ALTER TABLE `categoria_producto`
  MODIFY `id_categoria_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `django_admin_log`
--
ALTER TABLE `django_admin_log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `django_content_type`
--
ALTER TABLE `django_content_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `django_migrations`
--
ALTER TABLE `django_migrations`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `petboutiqueapp_customuser`
--
ALTER TABLE `petboutiqueapp_customuser`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `petboutiqueapp_roles`
--
ALTER TABLE `petboutiqueapp_roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `petboutiqueapp_roles_permisos`
--
ALTER TABLE `petboutiqueapp_roles_permisos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de la tabla `producto_x_pedido`
--
ALTER TABLE `producto_x_pedido`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `producto_x_venta`
--
ALTER TABLE `producto_x_venta`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id_proveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipo_documento`
--
ALTER TABLE `tipo_documento`
  MODIFY `id_tipo_documento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  ADD CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  ADD CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`);

--
-- Filtros para la tabla `auth_permission`
--
ALTER TABLE `auth_permission`
  ADD CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`);

--
-- Filtros para la tabla `auth_user_groups`
--
ALTER TABLE `auth_user_groups`
  ADD CONSTRAINT `auth_user_groups_group_id_97559544_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  ADD CONSTRAINT `auth_user_groups_user_id_6a12ed8b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`);

--
-- Filtros para la tabla `auth_user_user_permissions`
--
ALTER TABLE `auth_user_user_permissions`
  ADD CONSTRAINT `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  ADD CONSTRAINT `auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`);

--
-- Filtros para la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD CONSTRAINT `carrito_producto_FK` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  ADD CONSTRAINT `carrito_usuario_FK` FOREIGN KEY (`nombre_usuario`) REFERENCES `usuario` (`nombre_usuario`);

--
-- Filtros para la tabla `django_admin_log`
--
ALTER TABLE `django_admin_log`
  ADD CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  ADD CONSTRAINT `django_admin_log_user_id_c564eba6_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `fk_pedido_estado` FOREIGN KEY (`id_estado_pedido`) REFERENCES `estado_pedido` (`id_estado_pedido`),
  ADD CONSTRAINT `fk_pedido_formaDePago` FOREIGN KEY (`id_forma_de_pago`) REFERENCES `forma_de_pago` (`id_forma_de_pago`),
  ADD CONSTRAINT `fk_pedido_tipoEnvio` FOREIGN KEY (`id_tipo_de_envio`) REFERENCES `tipo_envio` (`id_tipo_envio`),
  ADD CONSTRAINT `fk_pedido_usuario` FOREIGN KEY (`nombre_usuario`) REFERENCES `usuario` (`nombre_usuario`);

--
-- Filtros para la tabla `petboutiqueapp_roles_permisos`
--
ALTER TABLE `petboutiqueapp_roles_permisos`
  ADD CONSTRAINT `PetBoutiqueApp_roles_roles_id_4303ebfc_fk_PetBoutiq` FOREIGN KEY (`roles_id`) REFERENCES `petboutiqueapp_roles` (`id`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `fk_productos_categoria` FOREIGN KEY (`id_categoria_producto`) REFERENCES `categoria_producto` (`id_categoria_producto`),
  ADD CONSTRAINT `fk_productos_proveedor` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`);

--
-- Filtros para la tabla `producto_x_pedido`
--
ALTER TABLE `producto_x_pedido`
  ADD CONSTRAINT `producto_x_pedido_pedido_FK` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`),
  ADD CONSTRAINT `producto_x_pedido_producto_FK` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`);

--
-- Filtros para la tabla `producto_x_venta`
--
ALTER TABLE `producto_x_venta`
  ADD CONSTRAINT `producto_x_venta_producto_FK` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  ADD CONSTRAINT `producto_x_venta_venta_FK` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuarios_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`),
  ADD CONSTRAINT `fk_usuarios_tipoDoc` FOREIGN KEY (`id_tipo_documento`) REFERENCES `tipo_documento` (`id_tipo_documento`);

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `fk_ventas_usuario` FOREIGN KEY (`nombre_usuario`) REFERENCES `usuario` (`nombre_usuario`),
  ADD CONSTRAINT `venta_pedido_FK` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
