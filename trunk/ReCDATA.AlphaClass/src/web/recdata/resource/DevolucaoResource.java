package web.recdata.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import web.recdata.controller.DevolucaoController;
import br.edu.ifpb.recdata.entidades.DevolucaoItem;

@Path("/devolucao")
public class DevolucaoResource {
	@POST
	@Path("/criar")
	@Consumes("application/json")
	@Produces("text/plan")
	public String creat(DevolucaoItem devolucao) {
		return new DevolucaoController().creat(devolucao);
	}

	@POST
	@Path("/deletar")
	@Consumes("application/json")
	@Produces("text/plan")
	public String delete(DevolucaoItem devolucao) {
		return new DevolucaoController().delete(devolucao);
	}

	@POST
	@Path("/atualizar")
	@Consumes("application/json")
	@Produces("text/plan")
	public String update(DevolucaoItem devolucao) {
		return new DevolucaoController().update(devolucao);
	}

	@POST
	@Path("/leitor")
	@Consumes("application/json")
	@Produces("application/json")
	public ArrayList<DevolucaoItem> readById(DevolucaoItem devolucao) {
		return new DevolucaoController().readById(devolucao);
	}

	@GET
	@Path("/listar")
	@Consumes("application/json")
	@Produces("application/json")
	public ArrayList<DevolucaoItem> listarTodos() throws SQLException {
		return new DevolucaoController().listarTodos();
	}
}
