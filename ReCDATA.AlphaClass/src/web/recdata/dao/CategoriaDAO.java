package web.recdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import web.recdata.factory.ConnectionFactory;
import web.recdata.model.Categoria;
import web.recdata.model.Item;

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

	public int creat(Categoria categoria) {

		int chave = 0;

		try {

			// Define um insert com os atributos e cada valor do atributo é
			// representado por ?
			String sql = "INSERT INTO `tb_categoria` (`descricao_categoria`) VALUES"
				+categoria.getDescricao_categoria();

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// envia para o Banco e fecha o objeto
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			// recuperar a chave
			ResultSet rs = stmt.getGeneratedKeys();

			// recupera a chave como um inteiro
			if (rs.next()) {
				chave = rs.getInt(1);
			}

			stmt.close();
			
			// insere os valores de categoria do item
			 sql = "INSERT INTO `tb_categoria` (`descricao_categoria`) VALUES"
				 +categoria.getDescricao_categoria();

			 stmt = (PreparedStatement) connection.prepareStatement(sql);
			 
			// seta os valores
             stmt.setInt(1, chave);
             stmt.setString(3,categoria.getDescricao_categoria());

             // envia para o Banco e fecha o objeto
             stmt.execute();
             stmt.close();

			 
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return chave;

	}
	
	public Categoria readById(Categoria categoria) {
		
		Item itemAux = null;
		
		try {

			String sql = "SELECT * FROM `tb_categoria` C  WHERE " + item.getIdItem()+
			              "I.`tb_categoria_idCategoria` = C.`idCategoria` ";
			
			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				itemAux = new Item();
				itemAux.setIdItem(rs.getInt("idItem"));
				itemAux.setIdCategoria(rs.getInt("tb_categoria_idCategoria"));
				itemAux.setDescricaoItem(rs.getString("descricao_item"));
				itemAux.setDescricaoCategoria(rs.getString("descricao_categoria"));
			}

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
		
		return itemAux;
	}

	
	public void update(Item item) {

		try {

			// Define um update com os atributos e cada valor é representado por
			// ?
			
			String sql = "UPDATE `tb_item` SET `descricao_item`=" + item.getDescricaoItem()
					+ " WHERE `idItem`=" + item.getIdItem();

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

	public void delete(Item item) {

		try {

			String sql = "DELETE FROM `tb_item` WHERE `idItem`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, item.getIdItem());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	public ArrayList<Item> listarTodos() throws SQLException{
		ArrayList<Item> itens = new ArrayList<Item>();
		
		String sql = String
		.format("%s",
				"SELECT * FROM `tb_item`,`tb_categoria`" +
				"WHERE tb_categoria_id_catoria = idCategoria");

		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);
	
		while(rs.next()){
			Item item = new Item();
			item.setIdItem(rs.getInt("idItem"));
			item.setDescricaoItem(rs.getString("descricao_item"));
			item.setIdCategoria(rs.getInt("tb_categoria_id_catoria")); 
			item.setDescricaoCategoria(rs.getString("descricao_categoria"));
			itens.add(item);
		}
		
		return itens;
	}
	


}
