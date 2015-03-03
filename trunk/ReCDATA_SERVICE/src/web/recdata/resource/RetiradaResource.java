package web.recdata.resource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import web.recdata.controller.RetiradaController;
import br.edu.ifpb.recdata.entidades.ReservaItem;
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
	
	/**
	 * 
	 * @param reserva
	 * @return
	 */
	@POST
	@Path("/listar")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Retirada> consultarRetiradas(ReservaItem reserva) {
		
		List<Retirada> retiradas = new ArrayList<Retirada>();
		
		// Consultar retiradas do usuários da lista.
		
		// Retiradas
		return retiradas;		
	}
}
