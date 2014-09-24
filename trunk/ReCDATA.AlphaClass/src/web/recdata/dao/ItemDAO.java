package web.recdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.recdata.factory.ConnectionFactory;
import web.recdata.model.Item;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/* tabela Item
 * `idItem` INT NOT NULL AUTO_INCREMENT,
  `tb_categoria_IdCategoria` INT NOT NULL,
  `descricao_item` VARCHAR(60) NOT NULL
  
  tabela Categoria
  
  `IdCategoria` INT NOT NULL AUTO_INCREMENT,
  `descricao_categoria` VARCHAR(45) NOT NULL,
  *
  */


public class ItemDAO {

	static ConnectionFactory banco;
	private static ItemDAO instance;
	
	public static ItemDAO getInstance(){
		if(instance == null){
			banco = new ConnectionFactory();
			instance = new ItemDAO(banco);
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;

	public ItemDAO(ConnectionFactory banco) {
		this.connection = (Connection) banco.getConnection();
	}
	
	public ItemDAO() {
		this.connection = (Connection) banco.getConnection();
	}	

	public void creat(Item item) {

		try {
			
			String sql = "INSERT INTO tb_item  VALUES (" + item.getIdItem() 
					+ "," + item.getIdCategoria() + ",\"" + item.getDescricaoItem() + "\")";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

	}
	public ArrayList<Item> readById(Item item) {
		List<Item> listaItem = null;
		Item itemAux = null;
		
		try {
			
			String sql = "SELECT * " +
					"FROM tb_item I, tb_categoria C " +
					"WHERE  I.tb_categoria_idCategoria =" + item.getIdCategoria()
						+" AND I.tb_categoria_idCategoria = C.IdCategoria";    
			
			// prepared statement para inser��o
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			listaItem = new ArrayList<Item>();
			
			while (rs.next()) {
				itemAux = new Item();
				itemAux.setIdItem(rs.getInt("idItem"));
				itemAux.setIdCategoria(rs.getInt("tb_categoria_idCategoria"));
				itemAux.setDescricaoItem(rs.getString("descricao_item"));
				itemAux.setDescricaoCategoria(rs.getString("descricao_categoria"));
				listaItem.add(itemAux);
			}

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
		
		return (ArrayList<Item>) listaItem;
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
				"WHERE tb_categoria_IdCategoria = idCategoria");

		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);
	
		while(rs.next()){
			Item item = new Item();
			item.setIdItem(rs.getInt("idItem"));
			item.setDescricaoItem(rs.getString("descricao_item"));
			item.setIdCategoria(rs.getInt("tb_categoria_IdCategoria")); 
			item.setDescricaoCategoria(rs.getString("descricao_categoria"));
			itens.add(item);
		}
		
		return itens;
	}
	


	
}
