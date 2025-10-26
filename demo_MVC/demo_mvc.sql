-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3306
-- Thời gian đã tạo: Th10 26, 2025 lúc 12:19 PM
-- Phiên bản máy phục vụ: 5.7.31
-- Phiên bản PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `demo_mvc`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `language`
--

DROP TABLE IF EXISTS `language`;
CREATE TABLE IF NOT EXISTS `language` (
  `language_id` char(2) COLLATE utf8mb4_unicode_ci NOT NULL,
  `language_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`language_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `language`
--

INSERT INTO `language` (`language_id`, `language_name`) VALUES
('EN', 'English'),
('VI', 'Tiếng Việt'),
('JA', 'Japanese');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `price` decimal(38,2) NOT NULL,
  `weight` decimal(38,2) NOT NULL,
  `product_category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FKb74tk5jq8jwurq34dks9agydq` (`product_category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`product_id`, `price`, `weight`, `product_category_id`) VALUES
(1, '1500.00', '1.25', 1),
(2, '25.00', '0.50', 2),
(3, '50.00', '0.00', 3),
(4, '2500.00', '5.00', 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_category`
--

DROP TABLE IF EXISTS `product_category`;
CREATE TABLE IF NOT EXISTS `product_category` (
  `product_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `can_be_shipped` bit(1) NOT NULL,
  PRIMARY KEY (`product_category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product_category`
--

INSERT INTO `product_category` (`product_category_id`, `can_be_shipped`) VALUES
(1, b'1'),
(2, b'1'),
(3, b'0'),
(4, b'1'),
(5, b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_category_translation`
--

DROP TABLE IF EXISTS `product_category_translation`;
CREATE TABLE IF NOT EXISTS `product_category_translation` (
  `product_category_id` int(11) NOT NULL,
  `language_id` char(2) COLLATE utf8mb4_unicode_ci NOT NULL,
  `category_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`product_category_id`,`language_id`),
  KEY `language_id` (`language_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product_category_translation`
--

INSERT INTO `product_category_translation` (`product_category_id`, `language_id`, `category_name`) VALUES
(1, 'EN', 'Electronics'),
(1, 'VI', 'Điện tử'),
(2, 'EN', 'Clothing'),
(2, 'VI', 'Quần áo'),
(3, 'EN', 'Digital Services'),
(3, 'VI', 'Dịch vụ số'),
(4, 'EN', 'Vehicle'),
(4, 'VI', 'XE'),
(1, 'JA', '電子機器'),
(2, 'JA', '衣類'),
(3, 'JA', 'デジタルサービス'),
(4, 'JA', '自動車');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_translation`
--

DROP TABLE IF EXISTS `product_translation`;
CREATE TABLE IF NOT EXISTS `product_translation` (
  `product_id` int(11) NOT NULL,
  `language_id` char(2) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product_description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`product_id`,`language_id`),
  KEY `language_id` (`language_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product_translation`
--

INSERT INTO `product_translation` (`product_id`, `language_id`, `product_name`, `product_description`) VALUES
(1, 'EN', 'Laptop', 'High-performance laptop for professionals'),
(1, 'VI', 'Máy tính xách tay', 'Laptop hiệu năng cao cho dân văn phòng'),
(2, 'EN', 'T-shirt', 'Cotton t-shirt with logo'),
(2, 'VI', 'Áo thun', 'Áo thun cotton in logo thương hiệu'),
(3, 'EN', 'Online Course', 'Learn coding skills anytime'),
(3, 'VI', 'Khóa học trực tuyến', 'Học lập trình mọi lúc mọi nơi'),
(4, 'EN', 'CONTAINER', 'CONTAINER'),
(4, 'VI', 'XE TẢI', 'XE TẢI'),
(1, 'JA', 'ノートパソコン', '軽量で高性能なノートPC'),
(2, 'JA', 'Tシャツ', 'カジュアルで快適なTシャツ'),
(3, 'JA', 'オンラインコース', '自宅で学べるオンライン学習コース'),
(4, 'JA', 'トラック', '大型で強力な貨物トラック');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
