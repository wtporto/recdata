package web.recdata.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import web.recdata.dao.ReservaDAO;
import web.recdata.validacao.Validar;
import br.edu.ifpb.recdata.entidades.Erro;
import br.edu.ifpb.recdata.entidades.ReservaItem;

public class ReservaController {
	public ArrayList<ReservaItem> listarTodos() throws SQLException {
		return ReservaDAO.getInstance().listarTodos();
	}

	public ArrayList<ReservaItem> readById(ReservaItem reserva) {
		return ReservaDAO.getInstance().readById(reserva);
	}

	public Response creat(ReservaItem reserva) {
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.validarItem();
		if (validacao == Validar.VALIDACAO_OK) {
			
			int idReserva = ReservaDAO.getInstance().creat(reserva);
			reserva.setId(idReserva);

			builder.status(Response.Status.CREATED);
			builder.entity(reserva);
		
		} else {			
			
			Erro erro = new Erro();
			erro.setCodigo(5);
			erro.setMensagem("Não foi possível criar a reserva");
			
			builder.status(Response.Status.NOT_ACCEPTABLE);
			builder.entity(erro);
		}

		return builder.build();
	}

	public String update(ReservaItem reserva) {
		ReservaDAO.getInstance().update(reserva);
		return "Reserva atualizada!";
	}

	public String delete(ReservaItem reserva) {
		ReservaDAO.getInstance().delete(reserva);
		return "Reserva deletada!";
	}
}
