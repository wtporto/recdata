package web.recdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import web.recdata.factory.ConnectionFactory;
import web.recdata.model.ReservaItem;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ReservaDAO {

	static ConnectionFactory banco;
	private static ReservaDAO instance;

	public static ReservaDAO getInstance() {
		if (instance == null) {
			banco = new ConnectionFactory();
			instance = new ReservaDAO(banco);
		}
		return instance;
	}

	// a conex�o com o banco de dados
	public Connection connection;

	public ReservaDAO(ConnectionFactory banco) {
		this.connection = (Connection) banco.getConnection();
	}

	public ReservaDAO() {
		this.connection = (Connection) banco.getConnection();
	}

	public void creat(ReservaItem reserva) {

		try {

			String sql = "INSERT INTO tb_reserva (tb_item_idItem,"
					+ "tb_usuario_idUsuario,data_reservado,hora_reservado) "
					+ "VALUES (?,?,?,?)";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, reserva.getItemIdReserva());
			stmt.setInt(2, reserva.getUsuarioIdReserva());
			stmt.setDate(3, new java.sql.Date(reserva.getHoraDataReserva()
					.getTime()));
			stmt.setTime(4, new java.sql.Time(reserva.getHoraDataReserva()
					.getTime()));

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

	}

	public void delete(ReservaItem reserva) {

		try {

			/*
			 * 
			 * DELETE FROM `tb_item` WHERE `idItem`=" + item.getIdItem()
			 */

			String sql = "DELETE FROM tb_reserva WHERE idReserva = ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, reserva.getIdReserva());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	public void update(ReservaItem reserva) {

		try {

			/*
			 * "UPDATE `tb_item` SET `descricao_item`=\"" +
			 * item.getDescricaoItem() + "\" WHERE `idItem`=" +
			 * item.getIdItem();
			 */

			String sql = "UPDATE tb_reserva SET tb_item_idItem = ? WHERE idReserva = ?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, reserva.getItemIdReserva());
			stmt.setInt(2, reserva.getIdReserva());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

	}

	public ArrayList<ReservaItem> readById(ReservaItem reserva) {

		ReservaItem reservaAux = null;
		ArrayList<ReservaItem> reservas = new ArrayList<ReservaItem>();

		try {

			String sql = "SELECT * FROM tb_reserva WHERE tb_usuario_idUsuario = ?";

			// prepared statement para inser��o
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, reserva.getUsuarioIdReserva());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				reservaAux = new ReservaItem();
				reservaAux.setIdReserva(rs.getInt("idReserva"));
				reservaAux.setItemIdReserva(rs.getInt("tb_item_idItem"));
				reservaAux.setUsuarioIdReserva(rs
						.getInt("tb_usuario_idUsuario"));

				long dateHora = rs.getDate("data_reservado").getTime()
						+ rs.getTime("hora_reservado").getTime();

				reservaAux.setHoraDataReserva(new Date(dateHora));

				reservas.add(reservaAux);
			}

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return reservas;
	}

	public ArrayList<ReservaItem> listarTodos() throws SQLException {
		ArrayList<ReservaItem> reservas = new ArrayList<ReservaItem>();
		ReservaItem reservaAux = null;
		String sql = "SELECT * FROM tb_reserva";

		// prepared statement para inser��o
		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			reservaAux = new ReservaItem();
			reservaAux.setIdReserva(rs.getInt("idReserva"));
			reservaAux.setItemIdReserva(rs.getInt("tb_item_idItem"));
			reservaAux.setUsuarioIdReserva(rs.getInt("tb_usuario_idUsuario"));

			long dateHora = rs.getDate("data_reservado").getTime()
					+ rs.getTime("hora_reservado").getTime();

			reservaAux.setHoraDataReserva(new Date(dateHora));
			reservas.add(reservaAux);
		}

		return reservas;
	}

}
