-- MySQL Workbench Forward Engineering


SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Schema test_cglib_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test_cglib_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `test_cglib_db`;

-- -----------------------------------------------------
-- Table `test_cglib_db`.`node`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_cglib_db`.`node`
(
  `number`               INT         NOT NULL AUTO_INCREMENT,
  `node_name`          VARCHAR(30) NOT NULL,
  `transaction_passed` BOOLEAN     NOT NULL,

  PRIMARY KEY (number)

)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_unicode_ci;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `test_cglib_db`.`node`
-- -----------------------------------------------------
START TRANSACTION;
USE `test_cglib_db`;
INSERT INTO `test_cglib_db`.`node` (number, `node_name`, `transaction_passed`)
VALUES (1, 'first', true);
INSERT INTO `test_cglib_db`.`node` (number, `node_name`, `transaction_passed`)
VALUES (3, 'lucky', true);
INSERT INTO `test_cglib_db`.`node` (number, `node_name`, `transaction_passed`)
VALUES (5, 'always with problems', false);
INSERT INTO `test_cglib_db`.`node` (number, `node_name`, `transaction_passed`)
VALUES (8, 'experimental', false);
INSERT INTO `test_cglib_db`.`node` (number, `node_name`, `transaction_passed`)
VALUES (10, 'last', false);

COMMIT;

