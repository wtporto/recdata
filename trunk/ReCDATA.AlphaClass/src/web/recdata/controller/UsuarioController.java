package web.recdata.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import web.recdata.dao.UsuarioDAO;
import br.edu.ifpb.recdata.entidades.Usuario;

public class UsuarioController {
	public ArrayList<Usuario> listarTodos() throws SQLException {
		return UsuarioDAO.getInstance().listarTodos();
	}

	public ArrayList<Usuario> readById(Usuario user) {
		return UsuarioDAO.getInstance().readById(user);
	}

	public String creat(Usuario user) {
		UsuarioDAO.getInstance().creat(user);
		return "Criado com sucesso";
	}

	public String delete(Usuario user) {
		UsuarioDAO.getInstance().delete(user);
		return "Deletado com sucesso";
	}

	public String update(Usuario user) {
		UsuarioDAO.getInstance().update(user);
		return "Atualizado com sucesso";
	}
	
	public boolean verificaLogin(Usuario user) {
		return UsuarioDAO.getInstance().verificaLogin(user);
	}
}
