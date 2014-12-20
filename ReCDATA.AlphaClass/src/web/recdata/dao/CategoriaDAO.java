package web.recdata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.recdata.factory.DBPool;
import br.edu.ifpb.recdata.entidades.Categoria;

public class CategoriaDAO {

	static DBPool banco;
	private static CategoriaDAO instance;

	public static CategoriaDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new CategoriaDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public CategoriaDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	public CategoriaDAO() {
		this.connection = (Connection) banco.getConn();
	}
	
	public Categoria readById(Categoria categoria) {

		Categoria categoriaAux = null;

		try {

			String sql = "SELECT C.cd_categoria, C.nm_descricao"
					+ " FROM  tb_categoria  as C" 
					+ " WHERE  C.cd_categoria = ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, categoria.getId());
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				categoriaAux = new Categoria();
				categoriaAux.setId(rs.getInt("cd_categoria"));
				categoriaAux.setDescricao(rs
						.getString("nm_descricao"));
			}
			
			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return categoriaAux;
	}

	public void update(Categoria categoria) {

		try {

			String sql = "UPDATE tb_categoria SET nm_descricao = ?"
					+ " WHERE cd_categoria = ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, categoria.getDescricao());
			stmt.setInt(2, categoria.getId());
			
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
	}

	public void delete(Categoria categoria) {

		try {
			String sql = "DELETE FROM tb_categoria"
					+ " WHERE cd_categoria = ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, categoria.getId());

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public List<Categoria> listarTodos() throws SQLException {

		ArrayList<Categoria> categorias = new ArrayList<Categoria>();

		String sql = "SELECT C.cd_categoria, C.nm_descricao"
				+ " FROM tb_categoria as C";

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Categoria categoria = new Categoria();
			categoria.setId(rs.getInt("C.cd_categoria"));
			categoria
					.setDescricao(rs.getString("C.nm_descricao"));
			categorias.add(categoria);
		}

		stmt.execute();
		stmt.close();

		return categorias;
	}
}
