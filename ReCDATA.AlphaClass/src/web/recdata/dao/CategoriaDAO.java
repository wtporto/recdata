package web.recdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import web.recdata.factory.ConnectionFactory;
import web.recdata.model.Categoria;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class CategoriaDAO {

	static ConnectionFactory banco;
	private static CategoriaDAO instance;
	
	public static CategoriaDAO getInstance(){
		if(instance == null){
			banco = new ConnectionFactory();
			instance = new CategoriaDAO(banco);
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;

	public CategoriaDAO(ConnectionFactory banco) {
		this.connection = (Connection) banco.getConnection();
	}
	
	public CategoriaDAO() {
		this.connection = (Connection) banco.getConnection();
	}	
	
	public Categoria readById(Categoria categoria) {
		
		Categoria categoriaAux= null;
		
		try {

			String sql = "SELECT * FROM `tb_categoria` C  WHERE  C.`idCategoria =`" +categoria.getIdCategoria();
			              
			
			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				categoriaAux = new Categoria();
				categoriaAux.setIdCategoria(rs.getInt("idCategoria"));
				categoriaAux.setDescricao_categoria(rs.getString("descricao_categoria"));
			}

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
		
		return categoriaAux;
	}

	
	public void update (Categoria categoria) {

		try {

			// Define um update com os atributos e cada valor é representado por
			// ?
			
			String sql = "UPDATE `tb_categoria` SET `descricao_categoria`=" + categoria.getDescricao_categoria()
					+ " WHERE `idCategoria`=" + categoria.getIdCategoria();

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);


			
			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

			
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

	}

	public void delete(Categoria categoria) {

		try {

			String sql = "DELETE FROM `tb_categoria` WHERE `idCategoria`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, categoria.getIdCategoria());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	public ArrayList<Categoria> listarTodos() throws SQLException{
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		
		String sql = "SELECT * FROM `tb_categoria`";

		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);
	
		while(rs.next()){
			Categoria categoria = new Categoria();
			categoria.setIdCategoria(rs.getInt("idCategoria"));
			categoria.setDescricao_categoria(rs.getString("descricao_categoria"));
			
			categorias.add(categoria);
		}
		
		stmt.execute();
		stmt.close();
		
		return categorias;
	}
	
}
