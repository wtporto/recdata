SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


CREATE SCHEMA IF NOT EXISTS `recdata` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `recdata` ;

CREATE TABLE IF NOT EXISTS `recdata`.`tb_categoria` (
  `IdCategoria` INT NOT NULL AUTO_INCREMENT,
  `descricao_categoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`IdCategoria`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `recdata`.`tb_item` (
  `idItem` INT NOT NULL AUTO_INCREMENT,
  `tb_categoria_IdCategoria` INT NOT NULL,
  `descricao_item` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`idItem`),
  INDEX `fk_Item_tb_categoria1_idx` (`tb_categoria_IdCategoria` ASC),
  CONSTRAINT `fk_Item_tb_categoria1`
    FOREIGN KEY (`tb_categoria_IdCategoria`)
    REFERENCES `recdata`.`tb_categoria` (`IdCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `recdata`.`tb_usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `login_usuario` VARCHAR(40) NOT NULL,
  `senha_usuario` VARCHAR(23) NOT NULL,
  `nome_usuario` VARCHAR(50) NOT NULL,
  `email_usuario` VARCHAR(45) NOT NULL,
  `telefone_usuario` VARCHAR(10) NOT NULL,
  `cpf_usuario` VARCHAR(11) NOT NULL,
  `endereco_usuario` VARCHAR(70) NULL,
  `data_nasc_usuario` DATE NOT NULL,
  `sexo_usuario` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `recdata`.`tb_monitor` (
  `tb_usuario_idUsuario` INT NOT NULL,
  `serie_monitor` VARCHAR(45) NOT NULL,
  `curso_monitor` VARCHAR(30) NOT NULL,
  `disciplina_monitor` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`tb_usuario_idUsuario`),
  CONSTRAINT `fk_tb_monitor_tb_usuario1`
    FOREIGN KEY (`tb_usuario_idUsuario`)
    REFERENCES `recdata`.`tb_usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `recdata`.`tb_professor` (
  `formacao_professor` VARCHAR(40) NOT NULL,
  `tb_usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`tb_usuario_idUsuario`),
  CONSTRAINT `fk_tb_professor_tb_usuario1`
    FOREIGN KEY (`tb_usuario_idUsuario`)
    REFERENCES `recdata`.`tb_usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `recdata`.`tb_servidor` (
  `privilegio_servidor` TINYINT(1) NOT NULL,
  `tb_usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`tb_usuario_idUsuario`),
  CONSTRAINT `fk_tb_servidor_tb_usuario1`
    FOREIGN KEY (`tb_usuario_idUsuario`)
    REFERENCES `recdata`.`tb_usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `recdata`.`tb_reserva` (
  `idReserva` INT NOT NULL AUTO_INCREMENT,
  `tb_item_idItem` INT NOT NULL,
  `tb_usuario_idUsuario` INT NOT NULL,
  `hora_data_reservado` DATETIME NOT NULL,
  `hora_data_devolucao` DATETIME NOT NULL,
  PRIMARY KEY (`idReserva`),
  INDEX `fk_tb_reserva_tb_item1_idx` (`tb_item_idItem` ASC),
  INDEX `fk_tb_reserva_tb_usuario1_idx` (`tb_usuario_idUsuario` ASC),
  CONSTRAINT `fk_tb_reserva_tb_item1`
    FOREIGN KEY (`tb_item_idItem`)
    REFERENCES `recdata`.`tb_item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_reserva_tb_usuario1`
    FOREIGN KEY (`tb_usuario_idUsuario`)
    REFERENCES `recdata`.`tb_usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
