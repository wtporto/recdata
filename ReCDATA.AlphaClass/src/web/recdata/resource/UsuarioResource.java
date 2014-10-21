package web.recdata.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import web.recdata.controller.UsuarioController;
import web.recdata.model.Usuario;

@Path("/usuario")
public class UsuarioResource {

	@GET
	@Path("/listar")
	@Produces("application/json")
	public ArrayList<Usuario> listarTodos() throws SQLException {
		return new UsuarioController().listarTodos();
	}
	
	@POST
	@Path("/verificar")
	@Consumes("application/json")
	@Produces("application/json")
	public boolean verificaLogin(Usuario user) {
		return new UsuarioController().verificaLogin(user);
	}

	@POST
	@Path("/criar")
	@Consumes("application/json")
	@Produces("text/plan")
	public String creat(Usuario user) {
		return new UsuarioController().creat(user);
	}

	@POST
	@Path("/leitor")
	@Consumes("application/json")
	@Produces("application/json")
	public ArrayList<Usuario> readById(Usuario user) {
		return new UsuarioController().readById(user);
	}

	@DELETE
	@Path("/deletar")
	@Consumes("application/json")
	@Produces("text/plan")
	public String delete(Usuario user) {
		return new UsuarioController().delete(user);
	}

	@PUT
	@Path("/atualizar")
	@Consumes("application/json")
	@Produces("text/plan")
	public String update(Usuario user) {
		return new UsuarioController().update(user);
	}

}
