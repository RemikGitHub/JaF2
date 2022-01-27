-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema database
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `database` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `database` ;

-- -----------------------------------------------------
-- Table `database`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT '0',
  `registration_date_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 44
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `database`.`posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database`.`posts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(80) NOT NULL,
  `content` LONGTEXT NOT NULL,
  `post_category` ENUM('FRONTEND', 'BACKEND', 'MOBILE') NOT NULL,
  `published_date_time` DATETIME NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`, `users_id`),
  INDEX `fk_posts_users_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_posts_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `database`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `database`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` MEDIUMTEXT NOT NULL,
  `write_date_time` DATETIME NOT NULL,
  `users_id` INT NOT NULL,
  `posts_id` INT NOT NULL,
  `posts_users_id` INT NOT NULL,
  PRIMARY KEY (`id`, `users_id`, `posts_id`, `posts_users_id`),
  INDEX `fk_comments_users1_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_comments_posts1_idx` (`posts_id` ASC, `posts_users_id` ASC) VISIBLE,
  CONSTRAINT `fk_comments_posts1`
    FOREIGN KEY (`posts_id` , `posts_users_id`)
    REFERENCES `database`.`posts` (`id` , `users_id`),
  CONSTRAINT `fk_comments_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `database`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `database`.`tokens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database`.`tokens` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `token` VARCHAR(45) NOT NULL,
  `created_date` DATETIME NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`, `users_id`),
  INDEX `fk_tokens_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_tokens_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `database`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `database`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database`.`user_role` (
  `role_name` VARCHAR(20) NOT NULL DEFAULT 'USER',
  `username` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`role_name`, `username`),
  INDEX `fk_user_username_idx` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_user_username`
    FOREIGN KEY (`username`)
    REFERENCES `database`.`users` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
