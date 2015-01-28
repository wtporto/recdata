package web.recdata.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import web.recdata.controller.CategoriaController;
import br.edu.ifpb.recdata.entidades.Categoria;

@Path("/categoria")
public class CategoriaResource {

	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Categoria> listarTodos() throws SQLException {
		List<Categoria> categorias = new CategoriaController().listarTodos();
		return categorias;
	}
}
