-- Host: 127.0.0.1    Database: ngpit
-- ------------------------------------------------------
-- Server version	8.0.31
-- ------------------------------------------------------

--
-- Database Name:
-- 
DROP DATABASE ngpit;
CREATE DATABASE IF NOT EXISTS ngpit;
USE ngpit;

--
-- Table structure for table `login_details`
--
DROP TABLE IF EXISTS `login_details`;
CREATE TABLE `login_details` (
    `id` INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `email` VARCHAR(50) UNIQUE  DEFAULT NULL,
    `password` VARCHAR(50)  DEFAULT NULL,
    `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `modified` DATETIME NULL DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `login_details`
--
INSERT INTO `login_details` (email, password, created) VALUES 
	('himanshu@gmail.com','himanshu123','2024-03-12 12:00:00');


--
-- Table structure for table `faculty`
--
DROP TABLE IF EXISTS `faculty`;
CREATE TABLE `faculty` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` Varchar(500) DEFAULT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(1000) DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=latin1;


--
-- Table structure for table `gallery`
--
DROP TABLE IF EXISTS `gallery`;
CREATE TABLE `gallery` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` VARCHAR(500) NOT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



--
-- Table structure for table `courses`
--
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) DEFAULT NULL,
  `decsription` VARCHAR(1000) DEFAULT NULL,
  `image` VARCHAR(500) NOT NULL,
  `faculty_id` INT DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL,
  FOREIGN KEY (`faculty_id`) REFERENCES `faculty`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `user`
--
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `login_id` INT NOT NULL,
  `first_name` VARCHAR(255) DEFAULT NULL,
  `last_name` VARCHAR(255) DEFAULT NULL,
  `image` VARCHAR(500) DEFAULT NULL,
  `phone_no` VARCHAR(15) DEFAULT NULL,
  `gender` VARCHAR(15) DEFAULT NULL,
  `age` INT DEFAULT NULL,
  `current_address_1` VARCHAR(1000) DEFAULT NULL,
  `current_address_2` VARCHAR(1000) DEFAULT NULL,
  `current_city` VARCHAR(255) DEFAULT NULL,
  `current_state` VARCHAR(255) DEFAULT NULL,
  `current_pincode` VARCHAR(15) DEFAULT NULL,
  `current_country` VARCHAR(255) DEFAULT NULL,
  `permanent_address_1` VARCHAR(1000) DEFAULT NULL,
  `permanent_address_2` VARCHAR(1000) DEFAULT NULL,
  `permanent_city` VARCHAR(255) DEFAULT NULL,
  `permanent_state` VARCHAR(255) DEFAULT NULL,
  `permanent_pincode` VARCHAR(15) DEFAULT NULL,
  `permanent_country` VARCHAR(255) DEFAULT NULL,
  `last_education` VARCHAR(15) DEFAULT NULL,
  `grades` VARCHAR(15) DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` DATETIME NULL DEFAULT NULL,
  FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



--
-- Table structure for table `check_in`
--
DROP TABLE IF EXISTS `check_in`;
CREATE TABLE `check_in` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login_id` INT NOT NULL,
  `project_name` VARCHAR(255) DEFAULT NULL,
  `task_description` VARCHAR(1000) DEFAULT NULL,
  `total_task` INT DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (`login_id`) REFERENCES `login_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `check_out`
--
DROP TABLE IF EXISTS `check_out`;
CREATE TABLE `check_out` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login_id` INT DEFAULT NULL,
  `project_name` VARCHAR(255) DEFAULT NULL,
  `todays_task` VARCHAR(1000) DEFAULT NULL,
  `working_hours_report` VARCHAR(1000) DEFAULT NULL,
  `total_hours` INT DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `account_deletion_msg``
--
DROP TABLE IF EXISTS `account_deletion_msg`;
CREATE TABLE `account_deletion_msg` (
  `id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `login_id` INT NOT NULL,
  `message` VARCHAR(255) DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
  FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `my_courses``
--
DROP TABLE IF EXISTS `my_courses`;
CREATE TABLE `my_courses` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `login_id` INT DEFAULT NULL,
  `course_id` INT DEFAULT NULL,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (`login_id`) REFERENCES `login_details`(`id`),
  FOREIGN KEY (`course_id`) REFERENCES `courses`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;








