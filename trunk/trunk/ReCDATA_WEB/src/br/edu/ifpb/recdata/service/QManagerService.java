package br.edu.ifpb.recdata.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.edu.ifpb.recdata.entidades.Item;

/**
 * Definition: Contains the services interfaces of QManager.
 * 
 * @author Rhavy Maia Guedes
 * 
 */
public interface QManagerService {

	/**
	 * 
	 * @param negotiation
	 * @return
	 */
	@GET
	@Path("/item/listar")
	@Produces("application/json")
	public ArrayList<Item> listarTodos();

}