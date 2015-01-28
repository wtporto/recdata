package web.recdata.controller;

import java.sql.SQLException;
import java.util.List;

import web.recdata.dao.CategoriaDAO;
import br.edu.ifpb.recdata.entidades.Categoria;

public class CategoriaController {

	public List<Categoria> listarTodos() throws SQLException{
		return CategoriaDAO.getInstance().listarTodos();
	}
}
