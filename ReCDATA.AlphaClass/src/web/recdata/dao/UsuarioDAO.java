package web.recdata.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import web.recdata.exececao.ReCDataSQLException;
import web.recdata.factory.ConnectionFactory;
import web.recdata.util.BancoUtil;
import br.edu.ifpb.recdata.entidades.Usuario;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

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

	// a conexão com o banco de dados
	public Connection connection;

	public UsuarioDAO(ConnectionFactory banco) {
		this.connection = (Connection) banco.getConnection();
	}

	public UsuarioDAO() {

	}

	public int creat(Usuario usuario) throws ReCDataSQLException {

		int chave = BancoUtil.IDVAZIO;
		
		try {
			String sql = String.format("%s ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d)", 
					"INSERT INTO tb_usuario (login_usuario, senha_usuario, "
					+ " nome_usuario, email_usuario, telefone_usuario, cpf_usuario,"
					+ " endereco_usuario, data_nasc_usuario, sexo_usuario, "
					+ " tb_tipousuario_idTipousuario)"
					+ " VALUES ", 
					usuario.getLoginUsuario(),
					usuario.getSenhaUsuario(),
					usuario.getNomeUsuario(),
					usuario.getEmailUsuario(),
					usuario.getTelefoneUsuario(),
					usuario.getCpfUsuario(),
					usuario.getEnderecoUsuario(),
					new Date(usuario.getIdadeUsuario().getTime()),
					usuario.getSexoUsuario(),
					usuario.getIdTipoUsuario());

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// envia para o Banco e fecha o objeto
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			chave = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			throw new ReCDataSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}
		
		return chave;
	}

	public Usuario verificaLogin(Usuario usuario) {

		Usuario usuarioConsulta = null;

		try {

			String sql = "SELECT U.idUsuario, U.login_usuario, U.nome_usuario, U.email_usuario,"
					+ " U.telefone_usuario, U.cpf_usuario, U.endereco_usuario,"
					+ " U.data_nasc_usuario, U.sexo_usuario, U.tb_tipousuario_idTipousuario"
					+ " FROM tb_usuario as U"
					+ " WHERE U.login_usuario = '"
					+ usuario.getLoginUsuario()
					+ "'"
					+ " AND U.senha_usuario = '"
					+ usuario.getSenhaUsuario()
					+ "'";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				usuarioConsulta = new Usuario();
				usuarioConsulta.setUsuarioId(rs.getInt("U.idUsuario"));
				usuarioConsulta
						.setLoginUsuario(rs.getString("U.login_usuario"));
				usuarioConsulta.setNomeUsuario(rs.getString("U.nome_Usuario"));
				usuarioConsulta
						.setEmailUsuario(rs.getString("U.email_Usuario"));
				usuarioConsulta.setTelefoneUsuario(rs
						.getString("U.telefone_usuario"));
				usuarioConsulta.setCpfUsuario(rs.getString("U.cpf_usuario"));
				usuarioConsulta.setEnderecoUsuario(rs
						.getString("endereco_usuario"));
				usuarioConsulta.setIdadeUsuario(rs
						.getDate("U.data_nasc_usuario"));
				usuarioConsulta.setSexoUsuario(rs.getString("U.sexo_usuario"));
				usuarioConsulta.setIdTipoUsuario(rs
						.getInt("U.tb_tipousuario_idTipousuario"));
			}

			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
		return usuarioConsulta;

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

			// Define um update com os atributos e cada valor � representado
			// por
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
