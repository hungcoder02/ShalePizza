-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.18-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for eproject2
DROP DATABASE IF EXISTS `eproject2`;
CREATE DATABASE IF NOT EXISTS `eproject2` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `eproject2`;

-- Dumping structure for table eproject2.cart
DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `Quantity` int(11) NOT NULL,
  `FoodID` int(11) NOT NULL,
  `ShoppingID` int(11) NOT NULL,
  PRIMARY KEY (`FoodID`,`ShoppingID`),
  KEY `ShoppingID` (`ShoppingID`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`FoodID`) REFERENCES `food` (`FoodID`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`ShoppingID`) REFERENCES `shopping` (`ShoppingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eproject2.cart: ~17 rows (approximately)
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` (`Quantity`, `FoodID`, `ShoppingID`) VALUES
	(2, 4, 3),
	(2, 6, 1),
	(2, 6, 6),
	(3, 7, 5),
	(1, 10, 2),
	(1, 25, 1),
	(1, 29, 3),
	(3, 29, 4),
	(3, 32, 5),
	(3, 38, 2),
	(2, 40, 3),
	(2, 40, 4),
	(1, 41, 6),
	(1, 42, 1),
	(1, 42, 5),
	(3, 42, 6),
	(1, 45, 3);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

-- Dumping structure for table eproject2.category
DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `CategoryID` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(50) NOT NULL,
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eproject2.category: ~4 rows (approximately)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`CategoryID`, `CategoryName`) VALUES
	(1, 'Pizzas'),
	(2, 'Desert'),
	(3, 'Beverages'),
	(4, 'Bread');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Dumping structure for table eproject2.clerk
DROP TABLE IF EXISTS `clerk`;
CREATE TABLE IF NOT EXISTS `clerk` (
  `MemberID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `FullName` varchar(50) NOT NULL,
  `Phone` varchar(11) NOT NULL,
  `Email` varchar(100) NOT NULL,
  PRIMARY KEY (`MemberID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eproject2.clerk: ~2 rows (approximately)
/*!40000 ALTER TABLE `clerk` DISABLE KEYS */;
INSERT INTO `clerk` (`MemberID`, `Username`, `Password`, `FullName`, `Phone`, `Email`) VALUES
	(1, 'trang', '41bb5e538cae95715414d17d816c61c282f4e0ca', 'Trần Thu Trang', '033297535', 'trantrang@gmail.com'),
	(4, 'tai', 'a9993e364706816aba3e25717850c26c9cd0d89d', 'Nguyễn Ngọc Tài', '0972478143', 'tai@gmail.com');
/*!40000 ALTER TABLE `clerk` ENABLE KEYS */;

-- Dumping structure for table eproject2.food
DROP TABLE IF EXISTS `food`;
CREATE TABLE IF NOT EXISTS `food` (
  `FoodID` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Price` varchar(20) NOT NULL,
  `FoodIMG` varchar(255) NOT NULL,
  PRIMARY KEY (`FoodID`),
  KEY `CategoryID` (`CategoryID`),
  CONSTRAINT `food_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eproject2.food: ~44 rows (approximately)
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` (`FoodID`, `CategoryID`, `Name`, `Price`, `FoodIMG`) VALUES
	(3, 1, 'Cheese Mania', '$13.1', 'Cheese Mania.png'),
	(4, 1, 'Chicken Bacons', '$14.6', 'Cheesy Chicken Bacon.png'),
	(5, 1, 'Haft Haft', '$15.6', 'Haft Haft.png'),
	(6, 1, 'Vaganza', '$20', 'Extravaganza.png'),
	(7, 1, 'Kid Mania', '$10', 'Kid Mania.png'),
	(8, 1, 'Pesto', '$12.6', 'Mussels Pesto Pizza.png'),
	(9, 1, 'Ocian Mania', '$14.7', 'Ocean Mania.png'),
	(10, 1, 'Feast Pizza', '$16.5', 'Pepperoni Feast.png'),
	(11, 1, 'Prime Beef', '$20', 'Prime Beef.png'),
	(12, 1, 'Bigger Form', '$20', 'Pizza Big 4.png'),
	(13, 1, 'Seafood Delight', '$12', 'Seafood Delight.png'),
	(14, 1, 'Teriyaki Chicken', '$10.5', 'Teriyaki Chicken.png'),
	(15, 1, 'Singapore Pizza', '$13', 'Singapore Style Seafood.png'),
	(16, 1, 'Veggie Mania', '$13.5', 'Veggie Mania.png'),
	(17, 1, 'Pizzamin Sea', '$15', 'Pizzamin Sea.png'),
	(18, 1, 'Surf Turf', '$19', 'Surf _ Turf.png'),
	(19, 1, 'Crime City', '$12', 'Pizza Big 4.png'),
	(20, 1, 'Dark Foenix', '$17', 'Mussels Pesto Pizza.png'),
	(21, 3, 'Cola ', '$1', 'Coca.png'),
	(22, 3, 'Ice Cola', '$1.5', 'Coca.png'),
	(23, 3, 'Orange Juice', '$2', 'Nước ép cam.png'),
	(24, 3, 'Mango Juice', '$2', 'Nước ép xoài.png'),
	(25, 3, 'Watermelon Juice', '$3', 'Nước ép dưa hấu.png'),
	(26, 3, 'Vanila Chocolate', '$3.5', 'vani.png'),
	(27, 3, 'Water', '$0', 'Water.png'),
	(28, 3, 'Strawberry Juice', '$4.44', 'straw.png'),
	(29, 3, 'Fruit ', '$5', 'ras.png'),
	(30, 3, 'Pepsi', '$1', 'Pepse.png'),
	(31, 4, 'Pancake', '$10', 'bigstock-pancakes-with-strawberry-and-c-71123920.png'),
	(32, 4, 'Boston Cream Pie', '$11', 'Boston Cream Pie.png'),
	(33, 4, 'Brownies', '$10', 'Brownies.png'),
	(34, 4, 'Carot Cake', '$9.8', 'Carrot Cake.png'),
	(35, 4, 'Cheese Cake', '$12', 'Cheesecake.png'),
	(36, 4, 'Chocolate Cookie', '$10', 'Chocolate Chip Cookies.png'),
	(37, 4, 'Cream Pie ', '$12', 'Pecan Pie.png'),
	(38, 4, 'Crepee', '$8', 'Crepe_.png'),
	(39, 4, 'Tapioca', '$10', 'Tapioca.png'),
	(40, 4, 'Tiramisu', '$10.9', 'Tiramisu.png'),
	(41, 2, 'Banana Froster', '$3', 'Bananas Foster.png'),
	(42, 2, 'Ice Cream', '$2', 'Ice Cream.png'),
	(43, 2, 'Maracon', '$1', 'Macaron.png'),
	(44, 2, 'Mochi Cream', '$1', 'Mochi.png'),
	(45, 2, 'Chocolate Cream', '$2', 'Whoopie Pie.png'),
	(46, 3, 'abc', '123', 'kkk.png');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;

-- Dumping structure for table eproject2.manager
DROP TABLE IF EXISTS `manager`;
CREATE TABLE IF NOT EXISTS `manager` (
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `FullName` varchar(50) NOT NULL,
  `Phone` varchar(11) NOT NULL,
  `Email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eproject2.manager: ~2 rows (approximately)
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` (`Username`, `Password`, `FullName`, `Phone`, `Email`) VALUES
	('trang', 'trantrang.', 'Trần Thu Trang', '0332975355', 'trantrang@gmail.com'),
	('hung', 'hungcao', 'Trần Thu Trang', '0332975355', 'trantrang@gmail.com');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;

-- Dumping structure for table eproject2.shopping
DROP TABLE IF EXISTS `shopping`;
CREATE TABLE IF NOT EXISTS `shopping` (
  `ShoppingID` int(11) NOT NULL AUTO_INCREMENT,
  `Username_Clerk` int(11) NOT NULL,
  `Created` datetime NOT NULL DEFAULT curtime(),
  PRIMARY KEY (`ShoppingID`),
  KEY `Username_Clerk` (`Username_Clerk`),
  CONSTRAINT `shopping_ibfk_1` FOREIGN KEY (`Username_Clerk`) REFERENCES `clerk` (`MemberID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table eproject2.shopping: ~6 rows (approximately)
/*!40000 ALTER TABLE `shopping` DISABLE KEYS */;
INSERT INTO `shopping` (`ShoppingID`, `Username_Clerk`, `Created`) VALUES
	(1, 1, '2021-09-10 15:55:39'),
	(2, 1, '2021-09-10 15:56:31'),
	(3, 1, '2021-09-10 15:57:33'),
	(4, 1, '2021-09-10 16:18:40'),
	(5, 1, '2021-09-10 16:43:39'),
	(6, 1, '2021-09-10 16:50:22');
/*!40000 ALTER TABLE `shopping` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
