package web.recdata.resource;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import web.recdata.controller.RetiradaController;
import br.edu.ifpb.recdata.entidades.Retirada;

@Path("/retirada")
public class RetiradaResource {

	@POST
	@Path("/criar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response creat(Retirada retirada) throws SQLException {
		Response response = new RetiradaController().creat(retirada);
		return response;
	}
}
