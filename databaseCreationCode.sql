SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bd_sweepstake
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bd_sweepstake` DEFAULT CHARACTER SET utf8 ;
USE `bd_sweepstake` ;

-- -----------------------------------------------------
-- Table `bd_sweepstake`.`teams`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sweepstake`.`teams` (
  `idTeam` INT NOT NULL AUTO_INCREMENT,
  `Abv` CHAR(3) NOT NULL,
  `Name` VARCHAR(45) NULL,
  PRIMARY KEY (`idTeam`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `bd_sweepstake`.`stages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sweepstake`.`stages` (
  `idStage` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(25) NOT NULL,
  `numberMatches` INT NOT NULL,
  PRIMARY KEY (`idStage`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `bd_sweepstake`.`sweepstakes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sweepstake`.`sweepstakes` (
  `idSweepstake` INT NOT NULL AUTO_INCREMENT,
  `punterName` VARCHAR(45) NOT NULL,
  `idChampion` INT NOT NULL,
  PRIMARY KEY (`idSweepstake`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `bd_sweepstake`.`scores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sweepstake`.`scores` (
  `idScore` INT NOT NULL AUTO_INCREMENT,
  `idHomeTeam` INT NOT NULL,
  `idAwayTeam` INT NOT NULL,
  `goalsHome`INT NOT NULL,
  `goalsAway` INT NOT NULL,
  `idStage` INT NOT NULL,
  `idSweepstake` INT NOT NULL,
  PRIMARY KEY (`idScore`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- INSERINDO NA TABELA `bd_sweepstake`.`teams`
-- -----------------------------------------------------

INSERT INTO `bd_sweepstake`.`teams` (`Abv`, `Name`)
VALUES  ('NED', 'NETHERLANDS'),
        ('ARG', 'ARGENTINA'),
        ('CRO', 'CROATIA'),
        ('BRA', 'BRAZIL'),
        ('ENG', 'ENGLAND'),
        ('FRA', 'FRANCE'),
        ('MAR', 'MOROCCO'),
        ('POR', 'PORTUGAL');
-- -----------------------------------------------------
-- INSERINDO NA TABELA `bd_sweepstake`.`stages`
-- -----------------------------------------------------

INSERT INTO `bd_sweepstake`.`stages` (`Name`, `numberMatches`)
VALUES  ('Quarter-Final', 4),
        ('Semi-final', 2),
        ('Final', 1);


-- -----------------------------------------------------
-- INSERINDO NA TABELA `bd_sweepstake`.`scores`
-- -----------------------------------------------------
CREATE VIEW greaterIdSweepstake AS SELECT MAX(IdSweepstake) 'maxId' FROM `bd_sweepstake`.`sweepstakes`;

SELECT * FROM greaterIdSweepstake;

-- -----------------------------------------------------
-- VIEW TO SEE THE GREATER ID IN TABLE `bd_sweepstake`.`sweepstakes`
-- -----------------------------------------------------

CREATE VIEW numberOfTeams AS SELECT Count(idTeam) 'numberTeams' FROM `bd_sweepstake`.`teams` ;

SELECT * FROM numberOfTeams;

-- -----------------------------------------------------
-- INSERINDO NA TABELA `bd_sweepstake`.`sweepstakes`
-- -----------------------------------------------------

INSERT INTO `sweepstakes` (`punterName`, `idChampion`)
VALUES ('Mateus', '6');
-- -----------------------------------------------------
-- SELECT TO GET THE TEAMS`bd_sweepstake`.`teams`
-- -----------------------------------------------------

INSERT INTO `scores` (`idScore`, `idHomeTeam`, `idAwayTeam`, `goalsHome`, `goalsAway`,  `idStage`, `idSweepstake`)
VALUES (NULL, ?, ?, ?, ?, ?, ?);

-- -----------------------------------------------------
-- SELECT TO GET THE TEAMS`bd_sweepstake`.`scores`
-- -----------------------------------------------------

SELECT * FROM scores;

-- -----------------------------------------------------
-- VIEW TO SEE THE GREATER ID IN TABLE `bd_sweepstake`.`sweepstakes`
-- -----------------------------------------------------

SELECT * FROM scores;

-- -----------------------------------------------------
-- DATABASE DROPING  `bd_sweepstake`
-- -----------------------------------------------------
DROP DATABASE   `bd_sweepstake`;

-- -----------------------------------------------------
-- SELECT TO GET THE TEAMS`bd_sweepstake`.`sweepstakes`
-- -----------------------------------------------------

SELECT * FROM sweepstakes;


SELECT sweepstakes.idSweepstake 'idBolao', sweepstakes.punterName 'Nome do Jogador', bd_sweepstake.teams.Name 'CAMPEAO'
FROM sweepstakes natural join teams where idChampion = idTeam;


-- -----------------------------------------------------
-- SELECT TO IMPLEMENTS THE METHOD FINDALL()
-- -----------------------------------------------------

SELECT sweepstakes.idSweepstake, sweepstakes.punterName, scores.idStage,teams1.idTeam "idHomeTeam", teams1.Name "nameHome",
       teams1.Abv "abvHome", scores.goalsHome, scores.goalsAway,teams2.Abv "abvAway", teams2.Name "AwayTeam", teams1.idTeam "idAwayTeam"
FROM sweepstakes,scores,teams teams1, teams teams2
WHERE sweepstakes.idSweepstake = scores.idSweepstake AND (
      scores.idHomeTeam = teams1.idTeam AND scores.idAwayTeam = teams2.idTeam);


select * from scores;