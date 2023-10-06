CREATE DATABASE `bank-project`;
USE `bank-project`;

CREATE TABLE `users`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `enabled` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `authorities`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `authority` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `customer`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(45) NOT NULL,
    `pwd` VARCHAR(200) NOT NULL,
    `role` VARCHAR(45) NOT NULL ,
    PRIMARY KEY (`id`)
);

INSERT IGNORE INTO `users` VALUES (NULL, "ngochung1809","Ngochung1809!",1);
INSERT IGNORE INTO `authorities` VALUES (NULL, "ngochung1809","WRITE");
INSERT IGNORE INTO `customer` VALUES (NULL,"tongochung1809@gmail.com","Ngochung1809!","ADMIN");