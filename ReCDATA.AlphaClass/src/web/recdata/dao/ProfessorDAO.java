package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import principal.Banco;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import entidades.Entidade;
import entidades.Professor;
import excecoes.ClasseInvalidaException;

public class ProfessorDAO implements DAO {

	// a conexão com o banco de dados
	public Connection connection;

	private UsuarioDAO usuarioDAO;

	public ProfessorDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
		usuarioDAO = new UsuarioDAO(banco);
	}

	@Override
	public void creat(Entidade entidade) throws ClasseInvalidaException {

		if (entidade instanceof Professor) {

			Professor professorUsuario = (Professor) entidade;

			int chave = usuarioDAO.creat(professorUsuario);

			if (chave != 0) {

				try {
					/*
					 * `idUsuario_Professor` INT NOT NULL AUTO_INCREMENT,
					 * `Formação_Professor` VARCHAR(40) NOT NULL,
					 */

					String sql = "INSERT INTO `professor` (`idUsuario_Professor`,`Formação_Professor`)"
							+ " VALUES (?, ?";

					// prepared statement para inserção
					PreparedStatement stmt = (PreparedStatement) connection
							.prepareStatement(sql);

					// seta os valores
					stmt.setInt(1, chave);
					stmt.setString(2, professorUsuario.getFormacaoProfessor());

					// envia para o Banco e fecha o objeto
					stmt.execute();
					stmt.close();

				} catch (SQLException sqle) {
					throw new RuntimeException(sqle);
				}

			} else
				System.err.println("Não foi possível inserir Professor!");

		} else {
			throw new ClasseInvalidaException();
		}

	}

	@Override
	public void readById(Entidade entidade) throws ClasseInvalidaException {

		if (entidade instanceof Professor) {

			Professor professor = (Professor) entidade;

			try {

				String sql = String
						.format("%s %s",
								"SELECT * FROM `professor` P  "
										+ "INNER JOIN `usuario` U ON P.`UsuarioId_Professor` U.`idUsuario` " +
										   "WHERE P. `UsuarioId_Professor` =",
										   professor.getUsuarioId());

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					professor.setFormacaoProfessor(rs.getString("Formação_Professor"));
					
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
		if (entidade instanceof Professor) {

			Professor professor = (Professor) entidade;

			usuarioDAO.update(professor);

			try {
				/*
				 * Serie_Monitor` VARCHAR(45) NOT NULL, `Curso_Monitor`
				 * VARCHAR(30) NOT NULL, `Disciplina_Monitor
				 */
				String sql = "UPDATE `Professor` SET `Formação_Professor`=?"
						+ " WHERE `UsuarioId_Professor`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);
				// seta os valores
				stmt.setString(1, professor.getFormacaoProfessor());

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
		if (entidade instanceof Professor) {

			Professor professor = (Professor) entidade;

			try {

				// Deleta uma tupla setando o atributo de identificação com
				// valor representado por ?
				String sql = "DELETE FROM `professor ` WHERE `UsuarioId_Professor`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, professor.getUsuarioId());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

				usuarioDAO.delete(professor);

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
