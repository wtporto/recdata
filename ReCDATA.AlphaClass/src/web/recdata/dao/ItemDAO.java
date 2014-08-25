package web.recdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import web.recdata.model.Item;
import web.recdata.factory.ConnectionFactory;;

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
	
	
	public ArrayList<Item> listarTodos() throws SQLException{
		ArrayList<Item> itens = new ArrayList<Item>();
		
		String sql = String
		.format("%s",
				"SELECT * FROM `item`");

		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);
	
		while(rs.next()){
			Item item = new Item();
			item.setIdItem(rs.getInt("idItem"));
			item.setStatusItem(rs.getBoolean("statusItem"));
			
			itens.add(item);
		}
		
		return itens;
	}
	
	
	// a conexão com o banco de dados
	public Connection connection;

	public ItemDAO(ConnectionFactory banco) {
		this.connection = (Connection) banco.getConnection();
	}
	
	public ItemDAO() {
		this.connection = (Connection) banco.getConnection();
	}

	public int creat(Item item) {

		int chave = 0;

		try {

			// Define um insert com os atributos e cada valor do atributo é
			// representado por ?
			String sql = String.format("%s (%d)",
					"INSERT INTO `item` (`statusItem`) VALUES",
					item.getStatusItem() ? 1 : 0);

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

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return chave;

	}
	
	public Item readById(Item item) {
		
		Item itemAux = null;
		
		try {

			String sql = "SELECT * FROM `item` I WHERE I.`idItem`= " + item.getIdItem();
			
			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				itemAux = new Item();
				itemAux.setIdItem(rs.getInt("idItem"));
				itemAux.setStatusItem(rs.getInt("statusItem") == 1 ? true : false);
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
			
			String sql = "UPDATE `item` SET `statusItem`=" + item.getStatusItem()
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

			String sql = "DELETE FROM `Item` WHERE `idItem`=?";

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
	
}
