-- -----------------------------------------------------
-- Table `offadas`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offadas`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offadas`.`login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offadas`.`login` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `logged_in` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_login_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_login_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `offadas`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offadas`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offadas`.`status` (
  `code` CHAR(4) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offadas`.`offerset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offadas`.`offerset` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL DEFAULT '名称未設定',
  `status_code` CHAR(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_offerset_user1_idx` (`user_id` ASC),
  INDEX `fk_offerset_status1_idx` (`status_code` ASC),
  CONSTRAINT `fk_offerset_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `offadas`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_offerset_status1`
    FOREIGN KEY (`status_code`)
    REFERENCES `offadas`.`status` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offadas`.`offer`
-- ----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offadas`.`offer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `offerset_id` INT NOT NULL,
  `target_class` VARCHAR(45) NOT NULL,
  `content_class` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_offer_offerset1_idx` (`offerset_id` ASC),
  CONSTRAINT `fk_offer_offerset1`
    FOREIGN KEY (`offerset_id`)
    REFERENCES `offadas`.`offerset` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offadas`.`offer_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offadas`.`offer_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `offerset_id` INT NOT NULL,
  `offer_id` INT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;
