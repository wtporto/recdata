package web.recdata.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import web.recdata.dao.ItemDAO;
import br.edu.ifpb.recdata.entidades.Item;

public class ItemController {

	public ArrayList<Item> listarTodos() throws SQLException {
		return ItemDAO.getInstance().listarTodos();
	}

	public String creat(Item item) {
		ItemDAO.getInstance().creat(item);
		return "Item criado com sucesso";
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
