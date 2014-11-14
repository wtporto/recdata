package web.recdata.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.recdata.exececao.ReCDataSQLException;
import web.recdata.factory.ConnectionFactory;
import web.recdata.util.BancoUtil;
import br.edu.ifpb.recdata.entidades.Categoria;
import br.edu.ifpb.recdata.entidades.Item;
import br.edu.ifpb.recdata.entidades.Usuario;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/*
 cd_usuario int(11) NOT NULL AUTO_INCREMENT,
 nm_login varchar(40) NOT NULL,
 nm_senha varchar(23) NOT NULL,
 nm_usuario varchar(50) NOT NULL,
 nm_email varchar(45) NOT NULL,
 nr_telefone varchar(10) NOT NULL,
 nr_cpf varchar(11) NOT NULL,
 nm_endereco varchar(70) DEFAULT NULL,
 dt_nascimento date NOT NULL,
 tp_sexo varchar(1) NOT NULL,
 cd_tipousuario int(11) NOT NULL,

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
			String sql = String
					.format("%s ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d)",
							"INSERT INTO tb_usuario (nm_login, nm_senha, "
									+ " nm_nome, nm_email, nr_telefone, nr_cpf,"
									+ " nm_endereco, dt_nascimento, tp_sexo, "
									+ " cd_tipousuario)" + " VALUES ", usuario
									.getLoginUsuario(), usuario
									.getSenhaUsuario(), usuario
									.getNomeUsuario(), usuario
									.getEmailUsuario(), usuario
									.getTelefoneUsuario(), usuario
									.getCpfUsuario(), usuario
									.getEnderecoUsuario(), new Date(usuario
									.getIdadeUsuario().getTime()), usuario
									.getSexoUsuario(), usuario
									.getIdTipoUsuario());

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

	public Usuario verificarLogin(Usuario usuario) {

		Usuario usuarioConsulta = null;

		try {

			String sql = "SELECT U.cd_usuario, U.nm_login, U.nm_nome, U.nm_email,"
					+ " U.nr_telefone, U.nr_cpf, U.nm_endereco,"
					+ " U.dt_nascimento, U.tp_sexo, U.cd_tipousuario"
					+ " FROM tb_usuario as U"
					+ " WHERE U.nm_login = '"
					+ usuario.getLoginUsuario()
					+ "'"
					+ " AND U.nm_senha = '"
					+ usuario.getSenhaUsuario() + "'";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				usuarioConsulta = new Usuario();
				usuarioConsulta.setUsuarioId(rs.getInt("U.cd_usuario"));
				usuarioConsulta.setLoginUsuario(rs.getString("U.nm_login"));
				usuarioConsulta.setNomeUsuario(rs.getString("U.nm_nome"));
				usuarioConsulta.setEmailUsuario(rs.getString("U.nm_email"));
				usuarioConsulta.setTelefoneUsuario(rs
						.getString("U.nr_telefone"));
				usuarioConsulta.setCpfUsuario(rs.getString("U.nr_cpf"));
				usuarioConsulta.setEnderecoUsuario(rs.getString("nm_endereco"));
				usuarioConsulta.setIdadeUsuario(rs.getDate("U.dt_nascimento"));
				usuarioConsulta.setSexoUsuario(rs.getString("U.tp_sexo"));
				usuarioConsulta.setIdTipoUsuario(rs.getInt("U.cd_tipousuario"));
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

			String sql = String.format("%s %d",
					"SELECT * FROM `tb_usuario` I WHERE I.`cd_tipousuario`=",
					user.getIdTipoUsuario());

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				user.setUsuarioId(rs.getInt("cd_usuario"));
				user.setLoginUsuario(rs.getString("nm_login"));
				user.setSenhaUsuario(rs.getString("nm_senha"));
				user.setNomeUsuario(rs.getString("nm_nome"));
				user.setEmailUsuario(rs.getString("nm_email"));
				user.setTelefoneUsuario(rs.getString("nr_telefone"));
				user.setCpfUsuario(rs.getString("nr_cpf"));
				user.setEnderecoUsuario(rs.getString("nm_endereco"));
				user.setIdadeUsuario(rs.getDate("dt_nascimento"));
				user.setSexoUsuario(rs.getString("tp_sexo"));
				user.setIdTipoUsuario(rs.getInt("cd_tipousuario"));

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
			String sql = "UPDATE `tb_usuario` SET `nm_senha`=?"
					+ " WHERE `nm_login`=?";

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

			String sql = "DELETE FROM `tb_usuario` WHERE `nm_login`=?";

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

		String sql = String.format("%s",
				"SELECT * FROM tb_usuario as U, tb_tipousuario as T"
						+ " WHERE U.cd_tipousuario = T.cd_tipousuario");

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			Usuario user = new Usuario();
			user.setUsuarioId(rs.getInt("cd_usuario"));
			user.setLoginUsuario(rs.getString("nm_login"));
			user.setSenhaUsuario(rs.getString("nm_senha"));
			user.setNomeUsuario(rs.getString("nm_nome"));
			user.setEmailUsuario(rs.getString("nm_email"));
			user.setTelefoneUsuario(rs.getString("nr_telefone"));
			user.setCpfUsuario(rs.getString("nr_cpf"));
			user.setEnderecoUsuario(rs.getString("nm_endereco"));
			user.setIdadeUsuario(rs.getDate("dt_nascimento"));
			user.setSexoUsuario(rs.getString("tp_sexo"));

			user.setIdTipoUsuario(rs.getInt("cd_tipousuario"));
			user.setDescricao_tipoUsuario(rs.getString("nm_descricao"));

			users.add(user);
		}

		stmt.close();

		return users;
	}

	public List<Usuario> consultarUsuariosByNome(Usuario usuario)
			throws SQLException {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		String sql = String.format("%s '%s'", "SELECT U.cd_usuario, U.nm_nome"
				+ " FROM tb_usuario as U" + " WHERE U.nm_nome LIKE ",
				usuario.getNomeUsuario() + "%");

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			Usuario usuarioConsulta = new Usuario();
			usuarioConsulta.setUsuarioId(rs.getInt("U.cd_usuario"));
			usuarioConsulta.setNomeUsuario(rs.getString("U.nm_nome"));
			usuarios.add(usuarioConsulta);
		}

		stmt.close();

		return usuarios;
	}
}
