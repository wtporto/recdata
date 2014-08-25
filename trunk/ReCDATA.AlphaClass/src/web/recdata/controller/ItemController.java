package web.recdata.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import web.recdata.dao.ItemDAO;
import web.recdata.model.Item;

public class ItemController {
	
	public ArrayList<Item> listarTodos() throws SQLException{
		return ItemDAO.getInstance().listarTodos();
	}
	
	public int creat(Item item){
		return ItemDAO.getInstance().creat(item);
	}
	
	public Item readById(Item item){
		
		return ItemDAO.getInstance().readById(item);
	}
	
	public void update(Item item){
		ItemDAO.getInstance().update(item);
	}
	
	public void delete(Item item){
		ItemDAO.getInstance().delete(item);
	}
}
