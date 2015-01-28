package web.recdata.controller;

import java.sql.SQLException;
import java.util.List;

import web.recdata.dao.RegiaoDAO;
import br.edu.ifpb.recdata.entidades.Regiao;

public class RegiaoController {

	public List<Regiao> listarTodos() throws SQLException{
		return RegiaoDAO.getInstance().listarTodos();
	}
}
