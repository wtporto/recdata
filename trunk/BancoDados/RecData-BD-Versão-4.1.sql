
CREATE SCHEMA IF NOT EXISTS `recdata` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `recdata` ;
--
-- Database: recdata
--
-- --------------------------------------------------------
--
-- Estrutura da tabela tb_regiao
--

CREATE TABLE IF NOT EXISTS tb_regiao (
  cd_regiao int(11) NOT NULL AUTO_INCREMENT,
  nm_regiao varchar(60) NOT NULL,
  dt_registro timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (cd_regiao)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela tb_categoria
--

CREATE TABLE IF NOT EXISTS tb_categoria (
  cd_categoria int(11) NOT NULL AUTO_INCREMENT,
  nm_descricao varchar(45) NOT NULL,
  dt_registro timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (cd_categoria)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------
--
-- Estrutura da tabela tb_item
--

CREATE TABLE IF NOT EXISTS tb_item (
  cd_item int(11) NOT NULL AUTO_INCREMENT,
  cd_categoria int(11) NOT NULL,
  cd_regiao int(11) NOT NULL,
  nm_item varchar(60) NOT NULL, -- Antiga descrição;
  dt_registro timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (cd_item),
  KEY fk_tb_categoria_idx (cd_categoria),
  KEY fk_tb_regiao_idx (cd_regiao)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela tb_reserva
--

CREATE TABLE IF NOT EXISTS tb_reserva (
  cd_reserva int(11) NOT NULL AUTO_INCREMENT,
  cd_item int(11) NOT NULL,
  cd_usuario_reserva int(11) NOT NULL,
  nm_observacao_reserva VARCHAR(255), 
  data_inicio date NOT NULL,
  hora_inicio time NOT NULL,
  data_fim date NOT NULL,
  hora_fim time NOT NULL,
  dt_registro timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (idReserva),
  KEY fk_tb_reserva_tb_item_idx (cd_item),
  KEY fk_tb_reserva_tb_usuario_reserva_idx (cd_usuario_reserva)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela tb_retirada
--

CREATE TABLE IF NOT EXISTS tb_retirada (
  cd_retirada int(11) NOT NULL AUTO_INCREMENT,
  cd_reserva int(11) NOT NULL,
  cd_usuario_liberacao int(11) NOT NULL,
  dt_retirada timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  dt_registro timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (cd_retirada),
  KEY fk_tb_retirada_tb_reserva_idx (cd_reserva),
  KEY fk_tb_retirada_tb_usuario_liberacao_idx (cd_usuario_liberacao)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela tb_devolucao
--

CREATE TABLE IF NOT EXISTS tb_devolucao (
  cd_devolucao int(11) NOT NULL AUTO_INCREMENT,
  cd_reserva int(11) NOT NULL,
  cd_usuario_recebimento int(11) NOT NULL, -- Usuário que recebe o item devolvido.
  cd_usuario_devolucao int(11) NOT NULL, -- Usuário que devolve o item. 
  dt_devolucao timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  dt_registro timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (cd_devolucao),
  KEY fk_tb_devolucao_tb_reserva_idx (cd_reserva),
  KEY fk_tb_devolucao_tb_usuario_recebimento_idx (cd_usuario_recebimento),
  KEY fk_tb_devolucao_tb_usuario_devolucao_idx (cd_usuario_devolucao)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela tb_tipousuario
--

CREATE TABLE IF NOT EXISTS tb_tipousuario (
  cd_tipousuario int(11) NOT NULL AUTO_INCREMENT,
  nm_tipousuario varchar(40) NOT NULL, -- Antigo descrição.
  PRIMARY KEY (cd_tipousuario)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela tb_usuario
--

CREATE TABLE IF NOT EXISTS tb_usuario (
  cd_usuario int(11) NOT NULL AUTO_INCREMENT,
  nm_login varchar(40) NOT NULL,
  nm_senha varchar(23) NOT NULL,
  nm_usuario varchar(50) NOT NULL,
  nm_email varchar(45) NOT NULL,
  nr_telefone varchar(10) NOT NULL,
  nr_cpf varchar(11) NOT NULL,
  nm_endereco varchar(70) DEFAULT NULL,
  dt_nascimento date NOT NULL,
  tp_sexo varchar(1) NOT NULL,
  cd_tipousuario int(11) NOT NULL,
  dt_registro timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (cd_usuario),
  UNIQUE KEY nm_login (nm_login),
  UNIQUE KEY nm_email (nm_email),
  KEY fk_tb_usuario_tb_tipousuario_idx (cd_tipousuario)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;