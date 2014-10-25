package web.recdata.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import web.recdata.dao.ItemDAO;
import web.recdata.validacao.Validar;
import br.edu.ifpb.recdata.entidades.Erro;
import br.edu.ifpb.recdata.entidades.Item;

public class ItemController {

	public ArrayList<Item> listarTodos() throws SQLException {
		return ItemDAO.getInstance().listarTodos();
	}

	public Response creat(Item item) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.validarItem();
		if (validacao == Validar.VALIDACAO_OK) {
			
			int idInstituicao = ItemDAO.getInstance().creat(item);
			item.setIdItem(idInstituicao);

			builder.status(Response.Status.CREATED);
			builder.entity(item);
		
		} else {			
			
			Erro erro = new Erro();
			erro.setCodigo(1);
			erro.setMensagem("Solicitação inválida");
			builder.entity(erro);
		}

		return builder.build();
	}

	public ArrayList<Item> readById(Item item) {

		return ItemDAO.getInstance().readById(item.getIdItem());
	}

	public String update(Item item) {
		ItemDAO.getInstance().update(item);
		return "Atualizado com sucesso!";
	}

	public String delete(Item item) {
		ItemDAO.getInstance().delete(item);
		return "Deletado com sucesso!";
	}
}
