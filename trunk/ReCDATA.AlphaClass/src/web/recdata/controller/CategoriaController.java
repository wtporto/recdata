package web.recdata.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import web.recdata.dao.CategoriaDAO;
import br.edu.ifpb.recdata.entidades.Categoria;

public class CategoriaController {

	public ArrayList<Categoria> listarTodos() throws SQLException{
		return CategoriaDAO.getInstance().listarTodos();
	}
}
