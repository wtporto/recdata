package web.recdata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import web.recdata.factory.DBPool;
import web.recdata.util.BancoUtil;
import br.edu.ifpb.recdata.entidades.Categoria;
import br.edu.ifpb.recdata.entidades.Item;
import br.edu.ifpb.recdata.entidades.Regiao;

public class ItemDAO {
	
	static DBPool banco;
	private static ItemDAO instance;

	public static ItemDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new ItemDAO(banco);
		}
		return instance;
	}

	// a conexão com o banco de dados
	public Connection connection;

	public ItemDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	public ItemDAO() {
		this.connection = (Connection) banco.getConn();
	}
	
	public int create(Item item) {

		int idItem = BancoUtil.IDVAZIO;
		
		try {

			String sql = "INSERT INTO tb_item (cd_categoria, cd_regiao, nm_item)"
					+ " VALUES ("
					+ item.getCategoria().getId() + ", "
					+ item.getRegiao().getId() + ", "
					+ "'" + item.getDescricao().trim() + "')";

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

			String sql = "SELECT I.cd_item, I.cd_categoria, I.cd_regiao,"
					+ " I.nm_item, I.dt_registro"
					+ " FROM "
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
			String sql = "UPDATE tb_item"
					+ " SET nm_descricao='" + item.getDescricao().trim() + "',"
					+ " cd_categoria = " + item.getCategoria().getId() + ","
					+ " cd_regiao = " + item.getRegiao().getId() + ","
					+ " WHERE cd_item = " + item.getId();

			// prepared statement para inserção.
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
				"SELECT  I.cd_item, I.cd_categoria, I.cd_regiao, I.nm_item, I.dt_registro"
				+ " FROM  tb_item as I, tb_categoria as C"
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

		String sql = String.format("%s '%s' %s %s",
				"SELECT I.cd_item, I.nm_item,"
				+ " I.dt_registro, C.cd_categoria, C.nm_descricao,"
				+ " R.cd_regiao, R.nm_regiao"
				+ " FROM tb_item as I, tb_categoria as C, tb_regiao as R"
				+ " WHERE I.nm_item LIKE ",
				item.getDescricao() + "%", 
				" AND I.cd_categoria = C.cd_categoria",
				" AND I.cd_regiao = R.cd_regiao");

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			Item itemConsulta = new Item();
			itemConsulta.setId(rs.getInt("I.cd_item"));
			itemConsulta.setDescricao(rs.getString("I.nm_item"));
			
			Categoria categoria = new Categoria();
			categoria.setId(rs.getInt("C.cd_categoria"));
			categoria
					.setDescricao(rs.getString("C.nm_descricao"));
			itemConsulta.setCategoria(categoria);
			
			Regiao regiao = new Regiao();
			regiao.setId(rs.getInt("R.cd_regiao"));
			regiao.setNome(rs.getString("R.nm_regiao"));
			itemConsulta.setRegiao(regiao);
			
			itens.add(itemConsulta);
		}

		return itens;
	}
}
