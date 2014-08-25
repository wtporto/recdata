package web.recdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import web.recdata.factory.ConnectionFactory;
import web.recdata.model.Chave;
import web.recdata.model.Entidade;
import web.recdata.model.Item;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ChaveDAO implements DAO {

	// a conexão com o banco de dados
	public Connection connection;

	private ItemDAO itemDAO;

	public ChaveDAO(ConnectionFactory banco) {
		this.connection = (Connection) banco.getConnection();
		itemDAO = new ItemDAO(banco);
	}
	
	public ArrayList<Entidade> listarTodos() throws SQLException{
		ArrayList<Entidade> itens = new ArrayList<Entidade>();
		// e passado por parrametro um objeto generico para facilitar a nossa vida ;)
		String sql = String
		.format("%s",
				"SELECT idItem, statusItem, Local_Chave,DescriçãoChave FROM `item` I,`chave` C " +
				"WHERE I.idItem =C.idItem_Chave");
		
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);
	
		while(rs.next()){
			Item item = new Item();
			item.setIdItem(rs.getInt("idItem"));
			item.setStatusItem(rs.getBoolean("statusItem"));
			Chave chave = new Chave();
			chave.setLocalChave("Local_Chave");
			chave.setDescricaoChave("DescriçãoChave");
			itens.add(item);
			itens.add(chave);//add o item chave
		}
		
		return itens;
	}
	// olhe pessoaDAO que é a superclasse e discenteDAO que é a subclasse

	@Override
	public void creat(Entidade entidade)  {

		if (entidade instanceof Chave) {

			Chave chaveItem = (Chave) entidade;

			int chave = itemDAO.creat(chaveItem);

			if (chave != 0) {

				try {

					String sql = "INSERT INTO `chave` (`idItem_Chave`,`Local_Chave`,`DescriçãoChave`)"
							+ " VALUES (?, ?, ?)";

					// prepared statement para inserção
					PreparedStatement stmt = (PreparedStatement) connection
							.prepareStatement(sql);

					// seta os valores
					stmt.setInt(1, chave);
					stmt.setString(2, chaveItem.getLocalChave());
					stmt.setString(3, chaveItem.getDescricaoChave());

					// envia para o Banco e fecha o objeto
					stmt.execute();
					stmt.close();

				} catch (SQLException sqle) {
					throw new RuntimeException(sqle);
				}

			} else
				System.err.println("Não foi possível inserir Chave!");

		}
	}

	@Override
	public void readById(Entidade entidade) {

		if (entidade instanceof Chave) {

			Chave chave = (Chave) entidade;

			try {
				/*
				 * ` `idItem_Chave` INT NOT NULL AUTO_INCREMENT,
				 * `Local_Chave`VARCHAR(30) NOT NULL, `DescriçãoChave`
				 * VARCHAR(40) NOT NULL,
				 */
				String sql = "SELECT * FROM `chave` C "
										+ "INNER JOIN `item` I ON C.`idItem_Chave` = I.`idItem` "
										+ " WHERE C.`idItem_Chave` =" +	chave.getIdItem();

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					chave.setStatusItem(rs.getInt("statusItem") == 1 ? true: false  );
					chave.setLocalChave(rs.getString("Local_Chave"));
					chave.setDescricaoChave(rs.getString("DescriçãoChave"));
				}

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		}
	}

	@Override
	public void update(Entidade entidade) {
		if (entidade instanceof Chave) {

			Chave chave = (Chave) entidade;

			itemDAO.update(chave);

			try {

				String sql = "UPDATE `chave` SET `DescriçãoChave`=?"
						+ " WHERE `idItem_Chave`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);
				
				// seta os valores
				stmt.setString(1, chave.getDescricaoChave());

				// envia para o Banco e fecha o objeto
				stmt.execute();

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		}
	}

	@Override
	public void delete(Entidade entidade)  {
		if (entidade instanceof Chave) {

			Chave chave = (Chave) entidade;

			try {

				// Deleta uma tupla setando o atributo de identificação com
				// valor representado por ?
				String sql = "DELETE FROM `chave` WHERE `idItem_Chave`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, chave.getIdItem());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

				itemDAO.delete(chave);

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

}
