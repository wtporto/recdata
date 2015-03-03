package web.recdata.controller;

import java.sql.SQLException;
import java.util.List;

import web.recdata.dao.TipoUsuarioDAO;
import br.edu.ifpb.recdata.entidades.TipoUsuario;

public class TipoUsarioController {


	
		public List<TipoUsuario> listarTodos() throws SQLException{
			return TipoUsuarioDAO.getInstance().listarTodos();
		}
	}
