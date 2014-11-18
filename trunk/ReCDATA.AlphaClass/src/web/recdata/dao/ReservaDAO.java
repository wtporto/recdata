package web.recdata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import web.recdata.factory.DBPool;
import web.recdata.util.BancoUtil;
import web.recdata.util.StringUtil;
import br.edu.ifpb.recdata.entidades.Item;
import br.edu.ifpb.recdata.entidades.ReservaItem;
import br.edu.ifpb.recdata.entidades.Usuario;

public class ReservaDAO {

	static DBPool banco;
	private static ReservaDAO instance;

	public static ReservaDAO getInstance() {		
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new ReservaDAO(banco);
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;

	public ReservaDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	public ReservaDAO() {
		this.connection = (Connection) banco.getConn();
	}

	public int creat(ReservaItem reserva) {

		int idReserva = BancoUtil.IDVAZIO;
		try {
			
			String sql = "INSERT INTO tb_reserva (cd_item,"
					+ " cd_usuario_reserva, data_inicio, hora_inicio,"
					+ " data_fim, hora_fim %s) "
					+ "VALUES ("
					+ " " + reserva.getItem().getId() + ","
					+ " " + reserva.getUsuario().getId() + ","
					+ " '" + new java.sql.Date(reserva.getHoraDataInicio()
							.getTime()) + "',"
					+ " '" + new java.sql.Time(reserva.getHoraDataInicio()
							.getTime()) + "',"
					+ " '" + new java.sql.Date(reserva.getHoraDataFim()
							.getTime()) + "',"
					+ " '" + new java.sql.Time(reserva.getHoraDataFim()
							.getTime()) + "'"
					+ " %s )";
			
			String observacao = reserva.getObservacao();
			
			if (!StringUtil.ehVazio(observacao)) {
				sql = String.format(sql, ", nm_observacao", 
						", '" + reserva.getObservacao() + "'");
			} else {
				sql = String.format(sql, BancoUtil.PALAVRA_VAZIA, 
						BancoUtil.PALAVRA_VAZIA);
			}

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idReserva = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
		
		return idReserva;

	}

	public void delete(ReservaItem reserva) {

		try {

			String sql = "DELETE FROM tb_reserva WHERE idReserva = ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, reserva.getId());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	public void update(ReservaItem reserva) {

		try {

			String sql = "UPDATE tb_reserva SET tb_item_idItem = ? WHERE idReserva = ?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, reserva.getItem().getId());
			stmt.setInt(2, reserva.getId());

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

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, reserva.getUsuario().getId());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				reservaAux = new ReservaItem();
				reservaAux.setId(rs.getInt("cd_reserva"));

				Item item = new Item();
				item.setId(rs.getInt("cd_item"));
				reservaAux.setItem(item);

				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("cd_usuario"));
				reservaAux.setUsuario(usuario);

				long dateHoraInicio = rs.getDate("data_inicio").getTime()
						+ rs.getTime("hora_inicio").getTime();

				reservaAux.setHoraDataInicio(new Date(dateHoraInicio));

				long dateHoraFim = rs.getDate("data_fim").getTime()
						+ rs.getTime("hora_fim").getTime();

				reservaAux.setHoraDataFim(new Date(dateHoraFim));

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
		String sql = "SELECT R.cd_reserva, R.cd_item, "
					+ "R.cd_usuario_reserva, R.nm_observacao_reserva, "
					+ "R.data_inicio, R.hora_inicio, "
					+ "R.data_fim,hora_fim "
					+ "FROM tb_reserva as R";

		// prepared statement para inseeção
		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			reservaAux = new ReservaItem();
			reservaAux.setId(rs.getInt("R.cd_reserva"));

			Item item = new Item();
			item.setId(rs.getInt("R.cd_item"));
			reservaAux.setItem(item);

			Usuario usuario = new Usuario();
			usuario.setId(rs.getInt("R.cd_usuario_reserva"));
			reservaAux.setUsuario(usuario);

			long dateHoraInicio = rs.getDate("R.data_inicio").getTime()
					+ rs.getTime("R.hora_inicio").getTime();

			reservaAux.setHoraDataInicio(new Date(dateHoraInicio));

			long dateHoraFim = rs.getDate("R.data_fim").getTime()
					+ rs.getTime("R.hora_fim").getTime();

			reservaAux.setHoraDataFim(new Date(dateHoraFim));

			reservas.add(reservaAux);
		}

		return reservas;
	}
}
