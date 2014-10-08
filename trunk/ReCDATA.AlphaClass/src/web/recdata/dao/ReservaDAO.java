package web.recdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
					+ "tb_usuario_idUsuario,hora_data_reservado,"
					+ "hora_data_devolucao) VALUES (?,?,?,?)";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, reserva.getItemIdReserva());
			stmt.setInt(2, reserva.getUsuarioIdReserva());
			stmt.setDate(3, new java.sql.Date(reserva.getHoraDataReserva()
					.getTime()));
			stmt.setDate(4, new java.sql.Date(reserva.getHoraEntrega()
					.getTime()));

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

			String sql = "SELECT * FROM tb_reserva";

			// prepared statement para inser��o
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				reservaAux = new ReservaItem();
				reservaAux.setIdReserva(rs.getInt("idReserva"));
				reservaAux.setItemIdReserva(rs.getInt("tb_item_idItem"));
				reservaAux.setUsuarioIdReserva(rs
						.getInt("tb_usuario_idUsuario"));
				reservaAux
						.setHoraDataReserva(rs.getDate("hora_data_reservado"));
				reservaAux.setHoraEntrega(rs.getDate("hora_data_devolucao"));
				reservas.add(reservaAux);
			}

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return reservas;
	}

}
