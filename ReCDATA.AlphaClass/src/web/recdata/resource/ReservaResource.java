package web.recdata.resource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import web.recdata.controller.ReservaController;
import br.edu.ifpb.recdata.entidades.ReservaItem;

@Path("/reserva")
public class ReservaResource {

	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<ReservaItem> listarTodos() throws SQLException {
		return new ReservaController().listarTodos();
	}

	@POST
	@Path("/consultarReservas")
	@Consumes("application/json")
	@Produces("application/json")
	public List<ReservaItem> consultarReservas(ReservaItem reserva) throws SQLException {
		return new ReservaController().consultarReservas(reserva);
	}
	
	@POST
	@Path("/leitor")
	@Consumes("application/json")
	@Produces("application/json")
	public ArrayList<ReservaItem> readById(ReservaItem reserva) {
		return new ReservaController().readById(reserva);
	}

	@POST
	@Path("/criar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response creat(ReservaItem reserva) throws SQLException {
		return new ReservaController().creat(reserva);
	}

	@POST
	@Path("/atualizar")
	@Consumes("application/json")
	@Produces("text/plan")
	public String update(ReservaItem reserva) {
		return new ReservaController().update(reserva);
	}

	@POST
	@Path("/deletar")
	@Consumes("application/json")
	@Produces("text/plan")
	public String delete(ReservaItem reserva) {
		return new ReservaController().delete(reserva);
	}
}