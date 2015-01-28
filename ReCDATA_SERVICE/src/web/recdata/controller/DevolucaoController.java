package web.recdata.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import web.recdata.dao.DevolucaoDAO;
import br.edu.ifpb.recdata.entidades.DevolucaoItem;

public class DevolucaoController {
	public String creat(DevolucaoItem devolucao) {
		DevolucaoDAO.getInstance().create(devolucao);
		return "Devolução realizada!";
	}

	public String delete(DevolucaoItem devolucao) {
		DevolucaoDAO.getInstance().delete(devolucao);
		return "Devolução deletada!";
	}

	public String update(DevolucaoItem devolucao) {
		DevolucaoDAO.getInstance().update(devolucao);
		return "Devolução atualizada!";
	}

	public ArrayList<DevolucaoItem> readById(DevolucaoItem devolucao) {
		return DevolucaoDAO.getInstance().readById(devolucao);
	}

	public ArrayList<DevolucaoItem> listarTodos() throws SQLException {
		return DevolucaoDAO.getInstance().listarTodos();
	}
}
