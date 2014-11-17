package web.recdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import web.recdata.factory.ConnectionFactory;
import web.recdata.util.BancoUtil;
import br.edu.ifpb.recdata.entidades.Categoria;
import br.edu.ifpb.recdata.entidades.Item;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ItemDAO {

	//------------------------INICIO DA CONEX�O COM BANCO DE DADOS-------------
	static ConnectionFactory banco;
	private static ItemDAO instance;

	public static ItemDAO getInstance() {
		if (instance == null) {
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
	//------------------------------FIM----------------------------------------
	
	
	public int create(Item item) {

		int idItem = BancoUtil.IDVAZIO;
		
		try {

			String sql = "INSERT INTO tb_item (cd_categoria,nm_item)"
					+ " VALUES ("
					+ item.getCategoria().getId()
					+ ",'"
					+ item.getDescricao() + "')";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idItem = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return idItem;
	}

	public ArrayList<Item> readById(int id) {

		Item itemAux = null;
		ArrayList<Item> itens = new ArrayList<Item>();

		try {

			String sql = "SELECT I.cd_item, I.cd_categoria, I.cd_regiao, I.nm_item, I.dt_registro FROM "
					+ "tb_item as I, tb_categoria as C"
					+ " WHERE"
					+ " AND C.cd_categoria = I.cd_categoria"
					+ " AND I.cd_item = " + id;

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				itemAux = new Item();
				itemAux.setId(rs.getInt("cd_item"));
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("cd_categoria"));
				categoria.setDescricao(rs
						.getString("nm_descricao"));
				itemAux.setCategoria(categoria);
				itemAux.setDescricao(rs.getString("nm_descricao"));
				itens.add(itemAux);
			}

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return itens;
	}

	public void update(Item item) {

		try {

			// Define um update com os atributos e cada valor é representado por
			// ?

			String sql = "UPDATE tb_item SET nm_descricao=\""
					+ item.getDescricao() + "\" WHERE `cd_item`="
					+ item.getId();

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

			String sql = "DELETE FROM tb_item WHERE cd_item=" + item.getId();

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	public ArrayList<Item> listarTodos() throws SQLException {
		
		ArrayList<Item> itens = new ArrayList<Item>();

		String sql = String.format("%s",
				"SELECT  I.cd_item, I.cd_categoria, I.cd_regiao, I.nm_item, I.dt_registro FROM "
				+ "tb_item as I, tb_categoria as C"
						+ " WHERE I.cd_categoria = C.cd_categoria");

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			Item item = new Item();
			item.setId(rs.getInt("cd_item"));
			item.setDescricao(rs.getString("nm_descricao"));
			Categoria categoria = new Categoria();
			categoria.setId(rs.getInt("cd_categoria"));
			categoria
					.setDescricao(rs.getString("nm_descricao"));
			item.setCategoria(categoria);
			itens.add(item);
		}

		return itens;
	}
	
	public ArrayList<Item> listarItens(Item item) throws SQLException {
		
		ArrayList<Item> itens = new ArrayList<Item>();

		String sql = String.format("%s '%s' %s",
				"SELECT I.cd_item, I.nm_descricao, I.cd_categoria,"
				+ " C.nm_descricao"
				+ " FROM tb_item as I, tb_categoria as C"
				+ " WHERE I.nm_descricao LIKE",
				item.getDescricao() + "%", 
				"AND I.cd_categoria = C.cd_categoria");

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			Item itemConsulta = new Item();
			itemConsulta.setId(rs.getInt("I.cd_item"));
			itemConsulta.setDescricao(rs.getString("I.nm_descricao"));
			Categoria categoria = new Categoria();
			categoria.setId(rs.getInt("I.cd_categoria"));
			categoria
					.setDescricao(rs.getString("C.nm_descricao"));
			itemConsulta.setCategoria(categoria);
			itens.add(itemConsulta);
		}

		return itens;
	}

}
