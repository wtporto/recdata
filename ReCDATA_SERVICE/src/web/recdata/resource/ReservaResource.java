package web.recdata.resource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import web.recdata.controller.ReservaController;
import br.edu.ifpb.recdata.entidades.Item;
import br.edu.ifpb.recdata.entidades.ReservaItem;
import br.edu.ifpb.recdata.entidades.Usuario;

@Path("/reserva")
public class ReservaResource {

	@GET
	@Path("/entidade")
	@Produces("application/json")
	public Response entidade() throws SQLException {
		ResponseBuilder builder = Response.status(Response.Status.OK);
		builder.expires(new Date());
		
		Date data = new Date();
		ReservaItem reserva = new ReservaItem();
		reserva.setDataRegistro(data);
		reserva.setHoraDataFim(data);
		reserva.setHoraDataInicio(data);
		reserva.setId(1);
		reserva.setObservacao("Observação da reserva");
		
		Item item = new Item();
		item.setId(2);
		item.setDescricao("Descrição do item");
		
		reserva.setItem(item);
		
		Usuario usuario = new Usuario();
		usuario.setId(3);
		usuario.setNome("Wemerson");
		
		reserva.setUsuarioAtendente(usuario);
		reserva.setUsuarioReserva(usuario);
		
		builder.entity(reserva);
		
		return builder.build();
	}
	
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
     @Path("/cadastrar")
     @Consumes("application/json")
     @Produces("application/json")
	public Response creat(ReservaItem reserva) {
		
		Response response = new ReservaController().creat(reserva);
		
		return response;
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