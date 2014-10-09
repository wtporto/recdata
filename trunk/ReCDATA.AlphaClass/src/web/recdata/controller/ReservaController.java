package web.recdata.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import web.recdata.dao.ReservaDAO;
import web.recdata.model.ReservaItem;

public class ReservaController {
	public ArrayList<ReservaItem> listarTodos() throws SQLException {
		return ReservaDAO.getInstance().listarTodos();
	}

	public ArrayList<ReservaItem> readById(ReservaItem reserva) {
		return ReservaDAO.getInstance().readById(reserva);
	}

	public String creat(ReservaItem reserva) {
		ReservaDAO.getInstance().creat(reserva);
		return "Item reservado!";
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
