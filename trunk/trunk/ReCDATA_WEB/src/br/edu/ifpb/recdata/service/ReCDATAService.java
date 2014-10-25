package br.edu.ifpb.recdata.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.edu.ifpb.recdata.entidades.Categoria;
import br.edu.ifpb.recdata.entidades.Item;

/**
 * Definition: Contains the services interfaces of QManager.
 * 
 * @author Rhavy Maia Guedes
 * 
 */
public interface ReCDATAService {

	/**
	 * Recuperar todos os itens cadastrados.
	 * 
	 * @param negotiation
	 * @return
	 */
	@GET
	@Path("/categoria/listar")
	@Produces("application/json")
	public List<Categoria> listarCategorias();
	
	@POST
	@Path("/item/criar")
	@Consumes("application/json")
	@Produces("text/plain")
	public String cadastrarItem(Item item);
}