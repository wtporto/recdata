SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `recdata` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `recdata` ;

CREATE TABLE IF NOT EXISTS `recdata`.`Item` (
  `idItem` INT NOT NULL AUTO_INCREMENT,
  `statusItem` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idItem`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `recdata`.`Chave` (
  `idItem_Chave` INT NOT NULL AUTO_INCREMENT,
  `Local_Chave` VARCHAR(30) NOT NULL,
  `DescriçãoChave` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`idItem_Chave`),
  CONSTRAINT `fk_Chave_Item`
    FOREIGN KEY (`idItem_Chave`)
    REFERENCES `recdata`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `recdata`.`DataShow` (
  `idItem_Datashow` INT NOT NULL AUTO_INCREMENT,
  `Descrição_Datashow` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idItem_Datashow`),
  CONSTRAINT `fk_DataShow_Item1`
    FOREIGN KEY (`idItem_Datashow`)
    REFERENCES `recdata`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `recdata`.`Caixadesom` (
  `idItem_CaixadeSom` INT NOT NULL AUTO_INCREMENT,
  `Descrição_CaixadeSom` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idItem_CaixadeSom`),
  INDEX `fk_Caixadesom_Item1_idx` (`idItem_CaixadeSom` ASC),
  CONSTRAINT `fk_Caixadesom_Item1`
    FOREIGN KEY (`idItem_CaixadeSom`)
    REFERENCES `recdata`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `recdata`.`Notebook` (
  `idItem_Notebook` INT NOT NULL AUTO_INCREMENT,
  `Descrição_Notebook` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idItem_Notebook`),
  CONSTRAINT `fk_Notebook_Item1`
    FOREIGN KEY (`idItem_Notebook`)
    REFERENCES `recdata`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `recdata`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `Nome_Usuario` VARCHAR(50) NOT NULL,
  `E-mail_Usuario` VARCHAR(45) NOT NULL,
  `Telefone_Usuario` VARCHAR(10) NOT NULL,
  `Idade_Usuario` DATE NOT NULL,
  `Sexo_Usuario` CHAR NOT NULL,
  `Senha_Usuario` VARCHAR(23) NOT NULL,
  `Login_Usuario` VARCHAR(30) NOT NULL,
  `Cpf_Usuario` VARCHAR(11) NOT NULL,
  `Endereco_Usuario` VARCHAR(70) NULL,
  PRIMARY KEY (`idUsuario`, `Idade_Usuario`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `recdata`.`Monitor` (
  `UsuarioId_Monitor` INT NOT NULL AUTO_INCREMENT,
  `Serie_Monitor` VARCHAR(45) NOT NULL,
  `Curso_Monitor` VARCHAR(30) NOT NULL,
  `Disciplina_Monitor` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`UsuarioId_Monitor`),
  INDEX `fk_Monitor_Usuario1_idx` (`UsuarioId_Monitor` ASC),
  CONSTRAINT `fk_Monitor_Usuario1`
    FOREIGN KEY (`UsuarioId_Monitor`)
    REFERENCES `recdata`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `recdata`.`Professor` (
  `idUsuario_Professor` INT NOT NULL AUTO_INCREMENT,
  `Formação_Professor` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`idUsuario_Professor`),
  CONSTRAINT `fk_Professor_Usuario1`
    FOREIGN KEY (`idUsuario_Professor`)
    REFERENCES `recdata`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `recdata`.`Servidor` (
  `UsuarioId_Servidor` INT NOT NULL AUTO_INCREMENT,
  `Privilegio` TINYINT(1) NOT NULL,
  PRIMARY KEY (`UsuarioId_Servidor`),
  CONSTRAINT `fk_Servidor_Usuario1`
    FOREIGN KEY (`UsuarioId_Servidor`)
    REFERENCES `recdata`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `recdata`.`Reserva` (
  `idReserva` INT NOT NULL AUTO_INCREMENT,
  `Item_idItem` INT NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  `HoraData` DATETIME NOT NULL,
  `Devolução` DATETIME NOT NULL,
  INDEX `fk_table1_Usuario1_idx` (`Usuario_idUsuario` ASC),
  PRIMARY KEY (`idReserva`),
  CONSTRAINT `fk_table1_Item1`
    FOREIGN KEY (`Item_idItem`)
    REFERENCES `recdata`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `recdata`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
