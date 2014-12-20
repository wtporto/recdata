package web.recdata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.recdata.factory.DBPool;
import br.edu.ifpb.recdata.entidades.Regiao;

public class RegiaoDAO {

	static DBPool banco;
	private static RegiaoDAO instance;

	public static RegiaoDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new RegiaoDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public RegiaoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	public RegiaoDAO() {
		this.connection = (Connection) banco.getConn();
	}

	public List<Regiao> listarTodos() throws SQLException {

		ArrayList<Regiao> regioes = new ArrayList<Regiao>();

		String sql = "SELECT R.cd_regiao, R.nm_regiao"
				+ " FROM tb_regiao as R";

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Regiao regiao = new Regiao();
			regiao.setId(rs.getInt("R.cd_regiao"));
			regiao.setNome(rs.getString("R.nm_regiao"));
			regioes.add(regiao);
		}

		stmt.execute();
		stmt.close();

		return regioes;
	}
}
