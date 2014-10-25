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

	public int creat(Item item) {

		int idItem = BancoUtil.IDVAZIO;
		try {

			String sql = "INSERT INTO tb_item (tb_categoria_IdCategoria,descricao_item)"
					+ " VALUES ("
					+ item.getCategoria().getIdCategoria()
					+ ",'"
					+ item.getDescricaoItem() + "')";

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

			String sql = "SELECT * FROM tb_item as I, tb_categoria as C"
					+ " WHERE"
					+ " AND C.IdCategoria = I.tb_categoria_IdCategoria"
					+ " AND I.idItem = " + id;

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				itemAux = new Item();
				itemAux.setIdItem(rs.getInt("idItem"));
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("tb_categoria_idCategoria"));
				categoria.setDescricaoCategoria(rs
						.getString("descricao_categoria"));
				itemAux.setCategoria(categoria);
				itemAux.setDescricaoItem(rs.getString("descricao_item"));
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

			String sql = "UPDATE `tb_item` SET `descricao_item`=\""
					+ item.getDescricaoItem() + "\" WHERE `idItem`="
					+ item.getIdItem();

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

			String sql = "DELETE FROM tb_item WHERE idItem=" + item.getIdItem();

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
				"SELECT * FROM `tb_item` I,`tb_categoria` C"
						+ " WHERE I.tb_categoria_idCategoria = C.idCategoria");

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			Item item = new Item();
			item.setIdItem(rs.getInt("idItem"));
			item.setDescricaoItem(rs.getString("descricao_item"));
			Categoria categoria = new Categoria();
			categoria.setIdCategoria(rs.getInt("tb_categoria_idCategoria"));
			categoria
					.setDescricaoCategoria(rs.getString("descricao_categoria"));
			item.setCategoria(categoria);
			itens.add(item);
		}

		return itens;
	}

}
