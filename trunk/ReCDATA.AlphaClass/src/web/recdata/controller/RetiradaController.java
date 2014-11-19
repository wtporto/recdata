package web.recdata.controller;

import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import web.recdata.dao.RetiradaDAO;
import web.recdata.validacao.Validar;
import br.edu.ifpb.recdata.entidades.Erro;
import br.edu.ifpb.recdata.entidades.Retirada;

public class RetiradaController {

	public Response creat(Retirada retirada) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.validarRetirada();
		if (validacao == Validar.VALIDACAO_OK) {
			
			int idRetirada = RetiradaDAO.getInstance().create(retirada);
			retirada.setId(idRetirada);

			builder.status(Response.Status.CREATED);
			builder.entity(retirada);
		
		} else {			
			
			Erro erro = new Erro();
			erro.setCodigo(1);
			erro.setMensagem("Solicitação inválida");
			builder.entity(erro);
		}

		return builder.build();
	}
}
