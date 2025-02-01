CREATE DATABASE IF NOT EXISTS `visio_db`;
USE `visio_db`;

DROP TABLE IF EXISTS `tb_violation`;

CREATE TABLE `tb_violation` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `violation_type` VARCHAR(100) DEFAULT NULL,
    `activity_name` VARCHAR(100) DEFAULT NULL,
    `conformance_level` VARCHAR(100) DEFAULT NULL,
    `developer_message` VARCHAR(2000) DEFAULT NULL,
    `device_id` VARCHAR(100) DEFAULT NULL,
    `number_occurrences` INT NOT NULL DEFAULT 0,
    PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=latin1;

SELECT * FROM tb_violation;