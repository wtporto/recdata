package web.recdata.resource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import web.recdata.controller.UsuarioController;
import web.recdata.exececao.ReCDataSQLException;
import web.recdata.validacao.Validar;
import br.edu.ifpb.recdata.entidades.Erro;
import br.edu.ifpb.recdata.entidades.Usuario;

@Path("/usuario")
public class UsuarioResource {

	@GET
	@Path("/listar")
	@Produces("application/json")
	public ArrayList<Usuario> listarTodos() throws SQLException {
		return new UsuarioController().listarTodos();
	}
	
	@POST
	@Path("/consultarUsuarios")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Usuario> consultarUsuarios(Usuario usuario) throws SQLException {
		return new UsuarioController().consultarUsuariosByNome(usuario);
	}

	@POST
	@Path("/verificar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response verificaLogin(Usuario usuario) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.validarLogin();
		if (validacao == Validar.VALIDACAO_OK) {

			UsuarioController controller = new UsuarioController();
			Usuario usuarioConsulta = controller.verificaLogin(usuario);

			if (usuarioConsulta != null) {
				builder.status(Response.Status.ACCEPTED);
				builder.entity(usuarioConsulta);
			} else {
				Erro erro = new Erro();
				erro.setCodigo(1);
				erro.setMensagem("Usuário não autorizado");
				builder.status(Response.Status.FORBIDDEN).entity(erro);
			}
		}

		return builder.build();
	}

	@POST
	@Path("/cadastrar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response creat(Usuario usuario) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.validarUsuario();
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				UsuarioController controller = new UsuarioController();
				int idUsuario = controller.creat(usuario);
				usuario.setId(idUsuario);

				builder.status(Response.Status.CREATED);
				builder.entity(usuario);

			} catch (ReCDataSQLException qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		}

		return builder.build();
	}

	@POST
	@Path("/leitor")
	@Consumes("application/json")
	@Produces("application/json")
	public ArrayList<Usuario> readById(Usuario user) {
		return new UsuarioController().readById(user);
	}

	@DELETE
	@Path("/deletar")
	@Consumes("application/json")
	@Produces("text/plan")
	public String delete(Usuario user) {
		return new UsuarioController().delete(user);
	}

	@PUT
	@Path("/atualizar")
	@Consumes("application/json")
	@Produces("text/plan")
	public String update(Usuario user) {
		return new UsuarioController().update(user);
	}

}
