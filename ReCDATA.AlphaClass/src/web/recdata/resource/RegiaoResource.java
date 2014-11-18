package web.recdata.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import web.recdata.controller.RegiaoController;
import br.edu.ifpb.recdata.entidades.Regiao;

@Path("/regiao")
public class RegiaoResource {

	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Regiao> listarTodos() throws SQLException {
		List<Regiao> regioes = new RegiaoController().listarTodos();
		return regioes;
	}
}
