package web.recdata.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import web.recdata.factory.ConnectionFactory;
import web.recdata.model.Entidade;
import web.recdata.model.Monitor;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class MonitorDAO implements GenericDAO {

	// a conexão com o banco de dados
	public Connection connection;

	private UsuarioDAO usuarioDAO;

	public MonitorDAO(ConnectionFactory banco) {
		this.connection = (Connection) banco.getConnection();
		usuarioDAO = new UsuarioDAO(banco);
	}


	public void creat(Entidade entidade) {
		if (entidade instanceof Monitor) {

			Monitor monitor = (Monitor) entidade;

			int chave = usuarioDAO.creat(monitor);

			if (chave != 0) {

				try {

					String sql = "INSERT INTO `monitor` (`UsuarioId_Monitor`,`Serie_Monitor`,`Curso_Monitor`,`Disciplina_Monitor`)"
							+ " VALUES (?, ?, ?, ?)";

					// prepared statement para inserção
					PreparedStatement stmt = (PreparedStatement) connection
							.prepareStatement(sql);

					// seta os valores
					stmt.setInt(1, chave);
					stmt.setString(2, monitor.getSerieMonitor());
					stmt.setString(3, monitor.getCursoMonitor());
					stmt.setString(4, monitor.getDisciplinaMonitor());

					// envia para o Banco e fecha o objeto
					stmt.execute();
					stmt.close();

				} catch (SQLException sqle) {
					throw new RuntimeException(sqle);
				}

			} else
				System.err.println("Não foi possível inserir Monitor!");

		}
	}


	public void readById(Entidade entidade) {
		if (entidade instanceof Monitor) {

			Monitor monitor = (Monitor) entidade;

			try {

				String sql = String
						.format("%s %s",
								"SELECT * FROM `monitor` M  "
										+ "INNER JOIN `usuario` U ON M.`UsuarioId_Monitor` U.`idUsuario` WHERE M. `UsuarioId_Monitor` =",
								monitor.getUsuarioId());

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					monitor.setSerieMonitor(rs.getString("Serie_Monitor"));
					monitor.setCursoMonitor(rs.getString("Curso_Monitor"));
					monitor.setDisciplinaMonitor(rs
							.getString("Disciplina_Monitor"));
				}

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} 
	}


	public void update(Entidade entidade)  {
		if (entidade instanceof Monitor) {

			Monitor monitor = (Monitor) entidade;

			usuarioDAO.update(monitor);

			try {
				/*
				 * Serie_Monitor` VARCHAR(45) NOT NULL, `Curso_Monitor`
				 * VARCHAR(30) NOT NULL, `Disciplina_Monitor
				 */
				String sql = "UPDATE `monitor` SET `Disciplina_Monitor`=?"
						+ " WHERE `UsuarioId_Monitor`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);
				// seta os valores
				stmt.setString(1, monitor.getDisciplinaMonitor());

				// envia para o Banco e fecha o objeto
				stmt.execute();

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		}
	}


	public void delete(Entidade entidade) {
		if (entidade instanceof Monitor) {

			Monitor monitor = (Monitor) entidade;

			try {

				// Deleta uma tupla setando o atributo de identificação com
				// valor representado por ?
				String sql = "DELETE FROM `monitor ` WHERE `UsuarioId_Monitor`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, monitor.getUsuarioId());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

				usuarioDAO.delete(monitor);

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

	@Override
	public void creat(Object entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readById(Object entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List listarTodos(Object entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
