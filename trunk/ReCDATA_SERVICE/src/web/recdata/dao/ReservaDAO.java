package web.recdata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import web.recdata.factory.DBPool;
import web.recdata.util.BancoUtil;
import web.recdata.util.StringUtil;
import br.edu.ifpb.recdata.entidades.Categoria;
import br.edu.ifpb.recdata.entidades.Item;
import br.edu.ifpb.recdata.entidades.Regiao;
import br.edu.ifpb.recdata.entidades.ReservaItem;
import br.edu.ifpb.recdata.entidades.Usuario;

public class ReservaDAO {

	private static DBPool banco;
	
	private static ReservaDAO instance;
	
	// Conexão com o banco de dados
	public Connection connection;

	public static ReservaDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new ReservaDAO(banco);
		return instance;
	}

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
					+ " cd_usuario_reserva,"
					+ " data_inicio,"
					+ " hora_inicio,"
					+ " data_fim,"
					+ " hora_fim"
					+ " %s)"
					+ " VALUES ("+ reserva.getItem().getId() 
					+ "," + reserva.getUsuarioReserva().getId() 
					+ ",'" + new java.sql.Date(reserva.getHoraDataInicio().getTime()) + "'"
					+ ",'" + new java.sql.Time(reserva.getHoraDataInicio().getTime()) + "'"
					+ ",'" + new java.sql.Date(reserva.getHoraDataFim().getTime()) + "'"
					+ ",'" + new java.sql.Time(reserva.getHoraDataFim().getTime()) + "'"
					+ " %s )";
			
			// Adicionar uma observação na reserva.
			String observacao = reserva.getObservacao();
			
			if (!StringUtil.ehVazio(observacao)) {
				sql = String.format(sql, ", nm_observacao", 
						", '" + reserva.getObservacao() + "'");
			} else {
				sql = String.format(sql, BancoUtil.STRING_VAZIA, 
						BancoUtil.STRING_VAZIA);
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

			String sql = "DELETE FROM tb_reserva"
					+ " WHERE idReserva = ?";

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

			String sql = "UPDATE tb_reserva"
					+ " SET tb_item_idItem = ?"
					+ " WHERE idReserva = ?";

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
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<ReservaItem> listarReservasUsuarioById(Usuario usuario) {

		ArrayList<ReservaItem> reservas = new ArrayList<ReservaItem>();

		try {

			String sql = "SELECT R.cd_reserva,"
					+ " R.cd_item,"
					+ " I.nm_item, "
					+ " R.cd_usuario_reserva,"
					+ " R.nm_observacao, "
					+ " R.data_inicio,"
					+ " R.hora_inicio, "
					+ " R.data_fim,"
					+ " R.hora_fim"
					+ " FROM  tb_reserva as R  tb_item as I"
					+ " WHERE R.cd_usuario_reserva= ?"
					+ " AND R.cd_item = I.cd_item";					

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, usuario.getId());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				ReservaItem reserva = new ReservaItem();
				reserva.setId(rs.getInt("cd_reserva"));

				Item item = new Item();
				item.setId(rs.getInt("cd_item"));
				item.setDescricao(rs.getString("nm_item"));
				reserva.setItem(item);

				usuario = new Usuario();
				usuario.setId(rs.getInt("cd_usuario_reserva"));
				reserva.setUsuarioReserva(usuario);

				long dateHoraInicio = rs.getDate("data_inicio").getTime()
						+ rs.getTime("hora_inicio").getTime();

				reserva.setHoraDataInicio(new Date(dateHoraInicio));

				long dateHoraFim = rs.getDate("data_fim").getTime()
						+ rs.getTime("hora_fim").getTime();

				reserva.setHoraDataFim(new Date(dateHoraFim));

				reservas.add(reserva);
			}

		} catch (SQLException sqle) {
			
			throw new RuntimeException(sqle);
		}

		return reservas;
	}

	public List<ReservaItem> listarTodos() throws SQLException {
		
		List<ReservaItem> reservas = new ArrayList<ReservaItem>();
		
		ReservaItem reservaAux = null;
		String sql = "SELECT R.cd_reserva," 
				+ " R.cd_item, "
				+ " R.cd_usuario_reserva,"
				+ " R.nm_observacao, "
				+ " R.data_inicio,"
				+ " R.hora_inicio, " 
				+ " R.data_fim,"
				+ " R.hora_fim "
				+ " FROM tb_reserva as R";

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
			reservaAux.setUsuarioReserva(usuario);

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
	
	public List<ReservaItem> consultarReservas(ReservaItem reserva) 
			throws SQLException {
		
		List<ReservaItem> reservas = new ArrayList<ReservaItem>();		
		
		String juncaoItem = BancoUtil.STRING_VAZIA;
		String idCategoria = BancoUtil.STRING_VAZIA;
		String idRegiao = BancoUtil.STRING_VAZIA;
		String descricao = BancoUtil.STRING_VAZIA;
		
		Item item = reserva.getItem();
		
		if (item!=null) {			
			// Categoria
			juncaoItem = "R.cd_item = I.cd_item";
			
			Categoria categoria = item.getCategoria();
			if (categoria!=null && categoria.getId()!=BancoUtil.IDVAZIO) {
				idCategoria = "AND I.cd_categoria = " + categoria.getId();
			}
			// Região
			Regiao regiao = item.getRegiao();
			if (regiao!=null && regiao.getId()!=BancoUtil.IDVAZIO) {
				idRegiao = "AND I.cd_regiao = " + regiao.getId();
			}
			// Descrição
			if (!StringUtil.ehVazio(item.getDescricao())) {
				descricao = "AND I.nm_item LIKE '" + item.getDescricao() + "%'";
			}
		}		
		
		String dataReserva = BancoUtil.STRING_VAZIA;
		
		String sql = String.format("%s %s %s %s %s %s", 
				"SELECT R.cd_reserva,"
				+ " R.cd_item,"
				+ " I.nm_item,"
				+ " R.cd_usuario_reserva,"
				+ " R.nm_observacao,"
				+ " R.data_inicio,"
				+ " R.hora_inicio,"
				+ " R.data_fim,"
				+ " R.hora_fim "
				+ " FROM tb_reserva as R,"
				+ " tb_item as I"
				+ " WHERE ",
				juncaoItem,
				idCategoria,
				idRegiao,
				descricao,
				dataReserva);

		// prepared statement para consulta
		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			ReservaItem reservaConsulta = new ReservaItem();
			reservaConsulta.setId(rs.getInt("R.cd_reserva"));

			Item itemConsulta = new Item();
			itemConsulta.setId(rs.getInt("R.cd_item"));
			itemConsulta.setDescricao(rs.getString("I.nm_item"));
			reservaConsulta.setItem(itemConsulta);

			Usuario usuario = new Usuario();
			usuario.setId(rs.getInt("R.cd_usuario_reserva"));
			reservaConsulta.setUsuarioReserva(usuario);

			long dateHoraInicio = rs.getDate("R.data_inicio").getTime()
					+ rs.getTime("R.hora_inicio").getTime();

			reservaConsulta.setHoraDataInicio(new Date(dateHoraInicio));

			long dateHoraFim = rs.getDate("R.data_fim").getTime()
					+ rs.getTime("R.hora_fim").getTime();

			reservaConsulta.setHoraDataFim(new Date(dateHoraFim));

			reservas.add(reservaConsulta);
		}
		
		stmt.close();
		rs.close();
		
		return reservas;
	}
	
	public boolean isPossivelReservar(int codItem, long reservaInicio, long reservaFim)
			throws SQLException {
		
		boolean isPossivelReservar = true;
		
		String sql = "SELECT data_inicio,"
				+ " hora_inicio,"
				+ " data_fim,"
				+ " hora_fim"
				+ " FROM tb_reserva"
				+ " WHERE cd_item = ?";

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next() && isPossivelReservar) {
			
			long dataHoraInicio = rs.getDate("data_inicio").getTime()
					+ rs.getTime("hora_inicio").getTime();
			
			long dataHoraFim = rs.getDate("data_fim").getTime()
					+ rs.getTime("hora_fim").getTime();

			if ((dataHoraInicio < reservaInicio && reservaInicio > dataHoraFim)
					&& (dataHoraInicio < reservaFim && reservaFim > dataHoraFim)) {
				
				isPossivelReservar = true;
				
			} else {
				
				isPossivelReservar = false;
			}
		}
		
		return isPossivelReservar;
	}
}
