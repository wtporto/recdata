package web.recdata.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import web.recdata.controller.TipoUsarioController;
import br.edu.ifpb.recdata.entidades.TipoUsuario;

@Path("/tipousuario")
public class TipoUsuarioResource {

	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<TipoUsuario> listarTodos() throws SQLException {
		List<TipoUsuario> tiposUsuarios = new TipoUsarioController().listarTodos();
		return tiposUsuarios;
	}
}
