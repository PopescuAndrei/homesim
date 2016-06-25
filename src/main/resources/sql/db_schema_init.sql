CREATE SCHEMA IF NOT EXISTS `homesim` DEFAULT CHARACTER SET latin1 ;
USE `homesim` ;

-- -----------------------------------------------------
-- Table `homesim`.`agents`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `homesim`.`agents` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `AGENT_NAME` VARCHAR(50) NOT NULL,
  `AVATAR_IMG` VARCHAR(255) NOT NULL,
  `LOC_X` INT(11) NULL DEFAULT NULL,
  `LOC_Y` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `homesim`.`needs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `homesim`.`needs` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NEED_NAME` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `homesim`.`agent_needs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `homesim`.`agent_needs` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `AGENT_ID` INT(11) NOT NULL,
  `NEED_ID` INT(11) NOT NULL,
  `DECAY_RATE` DOUBLE NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `AGENT_ID` (`AGENT_ID` ASC),
  INDEX `NEED_ID` (`NEED_ID` ASC),
  CONSTRAINT `agent_needs_ibfk_1`
    FOREIGN KEY (`AGENT_ID`)
    REFERENCES `homesim`.`agents` (`ID`),
  CONSTRAINT `agent_needs_ibfk_2`
    FOREIGN KEY (`NEED_ID`)
    REFERENCES `homesim`.`needs` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `homesim`.`houses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `homesim`.`houses` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `PATH` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `homesim`.`nodes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `homesim`.`nodes` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `POSX` INT(11) NOT NULL,
  `POSY` INT(11) NOT NULL,
  `HOUSE_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `NODES` (`HOUSE_ID` ASC),
  CONSTRAINT `NODES`
    FOREIGN KEY (`HOUSE_ID`)
    REFERENCES `homesim`.`houses` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 88
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `homesim`.`appliances`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `homesim`.`appliances` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `POSES` VARCHAR(255) NULL DEFAULT NULL,
  `TYPE` VARCHAR(255) NULL DEFAULT NULL,
  `NODE_ID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_NODE_ID` (`NODE_ID` ASC),
  CONSTRAINT `FK_APP_NODE`
    FOREIGN KEY (`NODE_ID`)
    REFERENCES `homesim`.`nodes` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 36
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `homesim`.`edges`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `homesim`.`edges` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `A_ID` INT(11) NULL DEFAULT NULL,
  `B_ID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_A_ID` (`A_ID` ASC),
  INDEX `FK_B_ID` (`B_ID` ASC),
  CONSTRAINT `FK_EDGE_A_NODE`
    FOREIGN KEY (`A_ID`)
    REFERENCES `homesim`.`nodes` (`ID`),
  CONSTRAINT `FK_EDGE_B_NODE`
    FOREIGN KEY (`B_ID`)
    REFERENCES `homesim`.`nodes` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 149
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `homesim`.`scenarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `homesim`.`scenarios` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(20) NOT NULL,
  `ACTIVITIES_FILE` VARCHAR(255) NOT NULL,
  `HOUSE_ID` INT(11) NOT NULL,
  `SENSOR_FILE` VARCHAR(255) NOT NULL,
  `STARTING_POINT` INT(11) NOT NULL,
  `SIMS_PER_SEC` INT(11) NULL DEFAULT NULL,
  `WALKING_SPEED` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_SC_HS` (`HOUSE_ID` ASC),
  CONSTRAINT `FK_SC_HS`
    FOREIGN KEY (`HOUSE_ID`)
    REFERENCES `homesim`.`houses` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `homesim`.`scenarios_agents`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `homesim`.`scenarios_agents` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `AGENT_ID` INT(11) NOT NULL,
  `SCENARIO_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `SCENARIO_ID` (`SCENARIO_ID` ASC),
  INDEX `AGENT_ID` (`AGENT_ID` ASC),
  CONSTRAINT `scenarios_agents_ibfk_1`
    FOREIGN KEY (`SCENARIO_ID`)
    REFERENCES `homesim`.`scenarios` (`ID`),
  CONSTRAINT `scenarios_agents_ibfk_2`
    FOREIGN KEY (`AGENT_ID`)
    REFERENCES `homesim`.`agents` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
