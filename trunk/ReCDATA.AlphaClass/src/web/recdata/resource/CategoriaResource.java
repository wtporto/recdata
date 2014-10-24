package web.recdata.resource;

import java.sql.SQLException;
import java.util.ArrayList;

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
	public ArrayList<Categoria> listarTodos() throws SQLException {
		return new CategoriaController().listarTodos();
	}
}
