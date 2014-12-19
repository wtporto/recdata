package web.recdata.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.recdata.dao.UsuarioDAO;
import web.recdata.excecao.ReCDataSQLException;
import br.edu.ifpb.recdata.entidades.Usuario;

public class UsuarioController {
	public ArrayList<Usuario> listarTodos() throws SQLException {
		return UsuarioDAO.getInstance().listarTodos();
	}

	public ArrayList<Usuario> readById(Usuario user) {
		return UsuarioDAO.getInstance().readById(user);
	}

	public int creat(Usuario user) throws ReCDataSQLException {		
		return UsuarioDAO.getInstance().creat(user);
	}

	public String delete(Usuario user) {
		UsuarioDAO.getInstance().delete(user);
		return "Deletado com sucesso";
	}

	public String update(Usuario user) {
		UsuarioDAO.getInstance().update(user);
		return "Atualizado com sucesso";
	}
	
	public Usuario verificaLogin(Usuario usuario) {
		return UsuarioDAO.getInstance().verificarLogin(usuario);
	}

	public List<Usuario> consultarUsuariosByNome(Usuario usuario) throws SQLException {
		return UsuarioDAO.getInstance().consultarUsuariosByNome(usuario);
	}
}