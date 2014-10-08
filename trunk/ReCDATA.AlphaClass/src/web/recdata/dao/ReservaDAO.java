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

	// a conexï¿½o com o banco de dados
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

	public ArrayList<ReservaItem> readById(ReservaItem reserva) {

		ReservaItem reservaAux = null;
		ArrayList<ReservaItem> reservas = new ArrayList<ReservaItem>();

		try {
			/*
			 * 1 - criar um método no DAO(ReservaDAO) para verificar se o item
			 * tem algo definido em algum momento, hora e dia reservado;
			 * 1.1 - cria código sql para consultar 
			 * 1.2 - testa várias situações com as consultas 
			 * 1.3 - passar isso para a aplicação 1.4 - implementar o serviço
			 */

			String sql = "SELECT * FROM tb_reserva";

			// prepared statement para inserï¿½ï¿½o
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				reservaAux = new ReservaItem();
				reservaAux.setIdReserva(rs.getInt("idReserva"));
				reservaAux.setItemIdReserva(rs.getInt("tb_item_idItem"));
				reservaAux.setUsuarioIdReserva(rs
						.getInt("tb_usuario_idUsuario"));

				long dateHora = rs.getDate("data_reservado").getTime() +
						rs.getTime("hora_reservado").getTime();
				
				reservaAux
						.setHoraDataReserva(new Date(dateHora));
				reservas.add(reservaAux);
			}

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return reservas;
	}

}
