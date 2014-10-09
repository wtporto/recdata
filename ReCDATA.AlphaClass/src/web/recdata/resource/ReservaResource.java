package web.recdata.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import web.recdata.controller.ReservaController;
import web.recdata.model.ReservaItem;

@Path("/reserva")
public class ReservaResource {

	@GET
	@Path("/listar")
	@Produces("application/json")
	public ArrayList<ReservaItem> listarTodos() throws SQLException {
		return new ReservaController().listarTodos();
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
	@Produces("text/plan")
	public String creat(ReservaItem reserva) {
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