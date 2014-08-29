package web.recdata.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import web.recdata.factory.ConnectionFactory;
import web.recdata.model.Entidade;
import web.recdata.model.Servidor;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ServidorDAO implements GenericDAO {
	// a conexão com o banco de dados
	public Connection connection;

	private UsuarioDAO usuarioDAO;

	public ServidorDAO(ConnectionFactory banco) {
		this.connection = (Connection) banco.getConnection();
		usuarioDAO = new UsuarioDAO(banco);
	}

	@Override
	public void creat(Entidade entidade){
		if (entidade instanceof Servidor) {

			Servidor servidor = (Servidor) entidade;

			int chave = usuarioDAO.creat(servidor);

			if (chave != 0) {

				try {

					String sql = "INSERT INTO `servidor` (`UsuarioId_Servidor`,`Privilegio`)"
							+ " VALUES (?, ?)";

					// prepared statement para inserção
					PreparedStatement stmt = (PreparedStatement) connection
							.prepareStatement(sql);

					// seta os valores
					stmt.setInt(1, chave);
					stmt.setInt(2, servidor.isPrivilegioServidor() ? 0 : 1);

					// envia para o Banco e fecha o objeto
					stmt.execute();
					stmt.close();

				} catch (SQLException sqle) {
					throw new RuntimeException(sqle);
				}

			} else
				System.err.println("Não foi possível inserir Servidor!");

		}
	}

	@Override
	public void readById(Entidade entidade){
		if (entidade instanceof Servidor) {

			Servidor servidor = (Servidor) entidade;

			try {

				String sql = String
						.format("%s %s",
								"SELECT * FROM `servidor` S "
										+ "INNER JOIN `usuario` U ON S.`UsuarioId_Servidor` U.`idUsuario` " +
										   "WHERE P. `UsuarioId_Servidor` =",
										   servidor.getUsuarioId());

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					if (1 == rs.getInt("Privilegio")) {
						servidor.setPrivilegioServidor(true);
					} else
		
					servidor.setPrivilegioServidor(false);
					
				}

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		}
	}

	@Override
	public void update(Entidade entidade) {
		if (entidade instanceof Servidor) {

			Servidor servidor = (Servidor) entidade;

			usuarioDAO.update(servidor);

			try {
				/*
				 * Serie_Monitor` VARCHAR(45) NOT NULL, `Curso_Monitor`
				 * VARCHAR(30) NOT NULL, `Disciplina_Monitor
				 */
				String sql = "UPDATE `servidor` SET `Privilerio`=?"
						+ " WHERE `UsuarioId_Servidor`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);
				// seta os valores
				stmt.setInt(1, servidor.isPrivilegioServidor() ? 0 : 1);

				// envia para o Banco e fecha o objeto
				stmt.execute();

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		}
	}

	@Override
	public void delete(Entidade entidade){
		if (entidade instanceof Servidor) {

			Servidor servidor = (Servidor) entidade;

			try {

				// Deleta uma tupla setando o atributo de identificação com
				// valor representado por ?
				String sql = "DELETE FROM `servidor ` WHERE `UsuarioId_Servidor`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, servidor.getUsuarioId());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

				usuarioDAO.delete(servidor);

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}


	}

}
