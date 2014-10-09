package web.recdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import web.recdata.factory.ConnectionFactory;
import web.recdata.model.DevolucaoItem;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DevolucaoDAO {

	static ConnectionFactory banco;
	private static DevolucaoDAO instance;

	public static DevolucaoDAO getInstance() {
		if (instance == null) {
			banco = new ConnectionFactory();
			instance = new DevolucaoDAO(banco);
		}
		return instance;
	}

	// a conex�o com o banco de dados
	public Connection connection;

	public DevolucaoDAO(ConnectionFactory banco) {
		this.connection = (Connection) banco.getConnection();
	}

	public DevolucaoDAO() {
		this.connection = (Connection) banco.getConnection();
	}

	public void creat(DevolucaoItem devolucao) {

		try {

			String sql = "INSERT INTO tb_devolucao (tb_item_idItem,"
					+ "tb_usuario_idUsuario,data_devolucao,hora_devolucao) "
					+ "VALUES (?,?,?,?)";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, devolucao.getItemIdDevolucao());
			stmt.setInt(2, devolucao.getUsuarioIdDevolucao());
			stmt.setDate(3, new java.sql.Date(devolucao.getHoraDataDevolucao()
					.getTime()));
			stmt.setTime(4, new java.sql.Time(devolucao.getHoraDataDevolucao()
					.getTime()));

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

	}

	public void delete(DevolucaoItem devolucao) {

		try {

			/*
			 * 
			 * DELETE FROM `tb_item` WHERE `idItem`=" + item.getIdItem()
			 */

			String sql = "DELETE FROM tb_devolucao WHERE idDevolucao = ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, devolucao.getIdDevolucao());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	public void update(DevolucaoItem devolucao) {

		try {

			/*
			 * "UPDATE `tb_item` SET `descricao_item`=\"" +
			 * item.getDescricaoItem() + "\" WHERE `idItem`=" +
			 * item.getIdItem();
			 */

			String sql = "UPDATE tb_devolucao SET tb_item_idItem = ? WHERE idDevolucao = ?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, devolucao.getItemIdDevolucao());
			stmt.setInt(2, devolucao.getIdDevolucao());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

	}

	public ArrayList<DevolucaoItem> readById(DevolucaoItem devolucao) {

		DevolucaoItem devolucaoAux = null;
		ArrayList<DevolucaoItem> devolucoes = new ArrayList<DevolucaoItem>();

		try {

			String sql = "SELECT * FROM tb_devolucao WHERE tb_usuario_idUsuario = ?";

			// prepared statement para inser��o
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, devolucao.getUsuarioIdDevolucao());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				devolucaoAux = new DevolucaoItem();
				devolucaoAux.setIdDevolucao(rs.getInt("idDevolucao"));
				devolucaoAux.setItemIdDevolucao(rs.getInt("tb_item_idItem"));
				devolucaoAux.setUsuarioIdDevolucao(rs
						.getInt("tb_usuario_idUsuario"));

				long dateHora = rs.getDate("data_devolucao").getTime()
						+ rs.getTime("hora_devolucao").getTime();

				devolucaoAux.setHoraDataDevolucao(new Date(dateHora));

				devolucoes.add(devolucaoAux);
			}

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return devolucoes;
	}

	public ArrayList<DevolucaoItem> listarTodos() throws SQLException {
		ArrayList<DevolucaoItem> devolucoes = new ArrayList<DevolucaoItem>();
		DevolucaoItem devolucaoAux = null;
		String sql = "SELECT * FROM tb_devolucao";

		// prepared statement para inser��o
		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			devolucaoAux = new DevolucaoItem();
			devolucaoAux.setIdDevolucao(rs.getInt("idDevolucao"));
			devolucaoAux.setItemIdDevolucao(rs.getInt("tb_item_idItem"));
			devolucaoAux.setUsuarioIdDevolucao(rs
					.getInt("tb_usuario_idUsuario"));

			long dateHora = rs.getDate("data_devolucao").getTime()
					+ rs.getTime("hora_devolucao").getTime();

			devolucaoAux.setHoraDataDevolucao(new Date(dateHora));
			devolucoes.add(devolucaoAux);
		}

		return devolucoes;
	}

}
