package web.recdata.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import web.recdata.controller.ItemController;
import web.recdata.model.Item;

@Path("/item")
public class ItemResource {
	
	//CRIA��O A MAIS
	@GET
	@Path("/listar")
	@Produces("application/json")
	public ArrayList<Item> listarTodos() throws SQLException{
		return new ItemController().listarTodos();
	}
	
	public ItemResource(){
		//auto create
	}
	
	//OKAY
	@POST
	@Path("/criar")
	@Consumes("text/xml")
	@Produces("text/plain")
	public String creat(Item item){
		new ItemController().creat(item);
		return "Criado com sucesso: " ;
	}
	  
	//NADA A VER APRIMORAR
	@POST
	@Path("/leitor")
	@Consumes("text/xml")
	@Produces("application/json")
	public Item readById(Item item){
		System.out.println("EXECUTED");
		return new ItemController().readById(item);
	}
	
	//MODIFICAR OS DAO (ESSE JA ESTA ATUALIZADO)
	@Path("/atualizar")
	@PUT
	@Consumes("text/xml")
	@Produces("text/plain")
	public String update(Item item){
		new ItemController().update(item);
		return "Atualiza��o concluida com sucesso!";
	}
	
	//FUNCIONANDO
	@Path("/deletar")
	@DELETE
	@Consumes("text/xml")
	@Produces("text/plain")
	public String delete(Item item){
		new ItemController().delete(item);
		return "Deletado com sucesso!";
	}
}
