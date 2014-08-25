package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import principal.Banco;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import entidades.Entidade;
import entidades.Notebook;
import excecoes.ClasseInvalidaException;

public class NotebookDAO implements DAO {

	// a conexão com o banco de dados
	public Connection connection;

	private ItemDAO itemDAO;

	public NotebookDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
		itemDAO = new ItemDAO(banco);
	}

	@Override
	public void creat(Entidade entidade) throws ClasseInvalidaException {
		if (entidade instanceof Notebook) {

			Notebook notebook = (Notebook) entidade;

			int chave = itemDAO.creat(notebook);

			if (chave != 0) {

				try {

					String sql = "INSERT INTO `notebook` (`idItem_Notebook`,`Descrição_Notebook`)"
							+ " VALUES (?, ?)";

					// prepared statement para inserção
					PreparedStatement stmt = (PreparedStatement) connection
							.prepareStatement(sql);

					// seta os valores
					stmt.setInt(1, chave);
					stmt.setString(2, notebook.getDescricaoNotebook());

					// envia para o Banco e fecha o objeto
					stmt.execute();
					stmt.close();

				} catch (SQLException sqle) {
					throw new RuntimeException(sqle);
				}

			} else
				System.err.println("Não foi possível inserir Notebook!");

		} else {
			throw new ClasseInvalidaException();
		}


		
	}

	@Override
	public void readById(Entidade entidade) throws ClasseInvalidaException {
		if (entidade instanceof Notebook) {

			Notebook notebook = (Notebook) entidade;

			try {
				/*
				 * idItem_Datashow` INT NOT NULL AUTO_INCREMENT,
				 * `Descrição_Datashow` VARCHAR(50) NOT NULL,
				 */
				String sql = String
				.format("%s %s",
						"SELECT * FROM `notebook` N "
								+ "INNER JOIN `item` I ON N.`idItem_Notebook` = I.`idItem` "
								+ " WHERE N.`idItem_Notebook` =",
								notebook.getIdItem());

		// prepared statement para inserção
		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			if (1 == rs.getInt("statusItem")) {
				notebook.setStatusItem(true);
			} else
				notebook.setStatusItem(false);
			notebook.setDescricaoNotebook(rs.getString("Descrição_Notebook"));
		}


			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} else {
			throw new ClasseInvalidaException();
		}
		

		
	}

	@Override
	public void update(Entidade entidade) throws ClasseInvalidaException {
		if (entidade instanceof Notebook) {

			Notebook notebook = (Notebook) entidade;

			itemDAO.update(notebook);

			try {

				String sql = "UPDATE `notebook` SET `Descrição_Notebook`=?"
						+ " WHERE `idItem_Notebook`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);
				// seta os valores
				stmt.setString(1, notebook.getDescricaoNotebook());

				// envia para o Banco e fecha o objeto
				stmt.execute();

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} else {
			throw new ClasseInvalidaException();
		}
		
		
	}

	@Override
	public void delete(Entidade entidade) throws ClasseInvalidaException {
		if (entidade instanceof Notebook) {

			Notebook notebook = (Notebook) entidade;

			try {

				// Deleta uma tupla setando o atributo de identificação com
				// valor representado por ?
				String sql = "DELETE FROM `notebook` WHERE `idItem_Notebook`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, notebook.getIdItem());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

				itemDAO.delete(notebook);

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
	}

}
