package web.recdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import web.recdata.factory.ConnectionFactory;
import br.edu.ifpb.recdata.entidades.DevolucaoItem;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DevolucaoDAO {

	//---------------------------INICIO DA CONEXÃO COM BANCO DE DADOS -------------------
	static ConnectionFactory banco;
	private static DevolucaoDAO instance;

	public static DevolucaoDAO getInstance() {
		if (instance == null) {
			banco = new ConnectionFactory();
			instance = new DevolucaoDAO(banco);
		}
		return instance;
	}
	
	public Connection connection;

	public DevolucaoDAO(ConnectionFactory banco) {
		this.connection = (Connection) banco.getConnection();
	}

	public DevolucaoDAO() {
		this.connection = (Connection) banco.getConnection();
	}
	//--------------------------------------FIM------------------------------------------
	
	/**
	 * Função: Criar uma nova devolução no banco de dados.
	 * Retorno: VOID.
	 * 
	 * FUNCIONANDO (TESTADO)
	 * */
	public void create(DevolucaoItem devolucao) {

		try {

			String sql = "INSERT INTO tb_devolucao (cd_reserva, "
					+ "cd_usuario_recebimento, cd_usuario_devolucao) "
					+ "VALUES (?,?,?)";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, devolucao.getIdReserva());
			stmt.setInt(2, devolucao.getIdUsuarioReserva());
			stmt.setInt(3, devolucao.getIdUsuarioDevolucao());
			
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

	}

	/**
	 * Função: Deletar uma devolução no banco de dados.
	 * Retorno: VOID.
	 * 
	 * FUNCIONANDO (TESTADO)
	 * */
	public void delete(DevolucaoItem devolucao) {

		try {

			String sql = "DELETE FROM tb_devolucao WHERE cd_devolucao = ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, devolucao.getIdDevolucao());

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	/**
	 * Função: Atualiza quem devolveu o item no banco de dados pelo ID da devolução.
	 * Retorno: VOID.
	 * 
	 * FUNCIONANDO (TESTADO)
	 * */
	public void update(DevolucaoItem devolucao) {

		try {

			String sql = "UPDATE tb_devolucao SET cd_usuario_devolucao = ? WHERE cd_devolucao = ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, devolucao.getIdUsuarioDevolucao());
			stmt.setInt(2, devolucao.getIdDevolucao());

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

	}

	/**
	 * Função: Selecionar todos os itens devolvidos por tal pessoa ID.
	 * Retorno: ArrayList de DevolucaoItem.
	 * 
	 * FUNCIONANDO (TESTADO)
	 * */
	public ArrayList<DevolucaoItem> readById(DevolucaoItem devolucao) {

		DevolucaoItem devolucaoAux = null;
		ArrayList<DevolucaoItem> devolucoes = new ArrayList<DevolucaoItem>();

		try {

			String sql = "SELECT * FROM tb_devolucao WHERE cd_usuario_devolucao = ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, devolucao.getIdUsuarioDevolucao());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				devolucaoAux = new DevolucaoItem();
				devolucaoAux.setIdDevolucao(rs.getInt("cd_devolucao"));
				devolucaoAux.setIdReserva(rs.getInt("cd_reserva"));
				devolucaoAux.setIdUsuarioReserva(rs.getInt("cd_usuario_recebimento"));
				devolucaoAux.setIdUsuarioDevolucao(rs.getInt("cd_usuario_devolucao"));
				devolucaoAux.setDt_devolucao(rs.getDate("dt_devolucao"));
				devolucoes.add(devolucaoAux);
			}

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return devolucoes;
	}

	/**
	 * Função: Selecionar todas as devoluções no banco de dados.
	 * Retorno: ArrayList de DevolucaoItem
	 * 
	 * FUNCIONANDO (TESTADO)
	 * */
	public ArrayList<DevolucaoItem> listarTodos() throws SQLException {
		ArrayList<DevolucaoItem> devolucoes = new ArrayList<DevolucaoItem>();
		DevolucaoItem devolucaoAux = null;
		String sql = "SELECT * FROM tb_devolucao";

		// prepared statement para inserï¿½ï¿½o
		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			devolucaoAux = new DevolucaoItem();
			devolucaoAux.setIdDevolucao(rs.getInt("cd_devolucao"));
			devolucaoAux.setIdReserva(rs.getInt("cd_reserva"));
			devolucaoAux.setIdUsuarioReserva(rs.getInt("cd_usuario_recebimento"));
			devolucaoAux.setIdUsuarioDevolucao(rs.getInt("cd_usuario_devolucao"));
			devolucaoAux.setDt_devolucao(rs.getDate("dt_devolucao"));
			devolucoes.add(devolucaoAux);
		}

		return devolucoes;
	}

}
