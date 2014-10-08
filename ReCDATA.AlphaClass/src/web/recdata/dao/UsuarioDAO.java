package web.recdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import web.recdata.factory.ConnectionFactory;
import web.recdata.model.Usuario;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/*
 * tabela usuario
 * 
 * `idUsuario` INT NOT NULL AUTO_INCREMENT,
 `login_usuario` VARCHAR(40) NOT NULL,
 `senha_usuario` VARCHAR(23) NOT NULL,
 `nome_usuario` VARCHAR(50) NOT NULL,
 `email_usuario` VARCHAR(45) NOT NULL,
 `telefone_usuario` VARCHAR(10) NOT NULL,
 `cpf_usuario` VARCHAR(11) NOT NULL,
 `endereco_usuario` VARCHAR(70) NULL,
 `data_nasc_usuario` DATE NOT NULL,
 `sexo_usuario` VARCHAR(1) NOT NULL,
 idtipousuario, descricaousuario;
 * 
 * */
public class UsuarioDAO {

	static ConnectionFactory banco;
	private static UsuarioDAO instance;

	public static UsuarioDAO getInstance() {
		if (instance == null) {
			banco = new ConnectionFactory();
			instance = new UsuarioDAO(banco);
		}
		return instance;
	}

	// a conex�o com o banco de dados
	public Connection connection;

	public UsuarioDAO(ConnectionFactory banco) {
		this.connection = (Connection) banco.getConnection();
	}

	public UsuarioDAO() {

	}

	public void creat(Usuario user) {

		try {

			// Define um insert com os atributos e cada valor do atributo �
			// representado por ?
			String sql = "INSERT INTO tb_usuario (login_usuario,senha_usuario,nome_usuario,email_usuario,telefone_usuario,cpf_usuario,"
					+ "endereco_usuario,data_nasc_usuario,sexo_usuario,tb_tipousuario_idTipousuario) VALUES "
					+ "(?,?,?,?,?,?,?,?,?,?)";

			// prepared statement para inser��o
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, user.getLoginUsuario());
			stmt.setString(2, user.getSenhaUsuario());
			stmt.setString(3, user.getNomeUsuario());
			stmt.setString(4, user.getEmailUsuario());
			stmt.setString(5, user.getTelefoneUsuario());
			stmt.setString(6, user.getCpfUsuario());
			stmt.setString(7, user.getEnderecoUsuario());
			stmt.setDate(8, new java.sql.Date(user.getIdadeUsuario().getTime()));
			stmt.setString(9, user.getSexoUsuario());
			stmt.setInt(10, user.getIdTipoUsuario());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

	}

	public ArrayList<Usuario> readById(Usuario user) {

		ArrayList<Usuario> users = new ArrayList<Usuario>();

		try {

			String sql = String
					.format("%s %d",
							"SELECT * FROM `tb_usuario` I WHERE I.`tb_tipousuario_idTipousuario`=",
							user.getIdTipoUsuario());

			// prepared statement para inser��o
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				user.setUsuarioId(rs.getInt("idUsuario"));
				user.setLoginUsuario(rs.getString("login_usuario"));
				user.setSenhaUsuario(rs.getString("senha_usuario"));
				user.setNomeUsuario(rs.getString("nome_Usuario"));
				user.setEmailUsuario(rs.getString("email_Usuario"));
				user.setTelefoneUsuario(rs.getString("telefone_usuario"));
				user.setCpfUsuario(rs.getString("cpf_usuario"));
				user.setEnderecoUsuario(rs.getString("endereco_usuario"));
				user.setIdadeUsuario(rs.getDate("data_nasc_usuario"));
				user.setSexoUsuario(rs.getString("sexo_usuario"));
				user.setIdTipoUsuario(rs.getInt("tb_tipousuario_idTipousuario"));

				users.add(user);

			}

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return users;

	}

	public void update(Usuario user) {

		try {

			// Define um update com os atributos e cada valor � representado por
			// ?
			String sql = "UPDATE `tb_usuario` SET `senha_usuario`=?"
					+ " WHERE `login_usuario`=?";

			// prepared statement para inser��o
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, user.getSenhaUsuario());
			stmt.setString(2, user.getLoginUsuario());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

	}

	public void delete(Usuario user) {

		try {

			String sql = "DELETE FROM `tb_usuario` WHERE `login_usuario`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, user.getLoginUsuario());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	public ArrayList<Usuario> listarTodos() throws SQLException {
		ArrayList<Usuario> users = new ArrayList<Usuario>();

		String sql = String
				.format("%s",
						"SELECT * FROM `tb_usuario` U,`tb_tipousuario` T WHERE U.tb_tipousuario_idTipousuario = T.idTipousuario");

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			Usuario user = new Usuario();
			user.setUsuarioId(rs.getInt("idUsuario"));
			user.setLoginUsuario(rs.getString("login_usuario"));
			user.setSenhaUsuario(rs.getString("senha_usuario"));
			user.setNomeUsuario(rs.getString("nome_usuario"));
			user.setEmailUsuario(rs.getString("email_usuario"));
			user.setTelefoneUsuario(rs.getString("telefone_usuario"));
			user.setCpfUsuario(rs.getString("cpf_usuario"));
			user.setEnderecoUsuario(rs.getString("endereco_usuario"));
			user.setIdadeUsuario(rs.getDate("data_nasc_usuario"));
			user.setSexoUsuario(rs.getString("sexo_usuario"));

			user.setIdTipoUsuario(rs.getInt("tb_tipousuario_idTipousuario"));
			user.setDescricao_tipoUsuario(rs.getString("descricao_tipousuario"));

			users.add(user);

		}

		return users;
	}
}
