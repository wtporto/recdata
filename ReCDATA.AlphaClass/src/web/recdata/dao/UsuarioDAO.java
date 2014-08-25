package web.recdata.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import web.recdata.factory.ConnectionFactory;
import web.recdata.model.Entidade;
import web.recdata.model.Usuario;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UsuarioDAO {

	// a conexão com o banco de dados
	public Connection connection;

	public UsuarioDAO(ConnectionFactory banco) {
		this.connection = (Connection) banco.getConnection();
	}

	public ArrayList<Entidade> listarTodos() throws SQLException{
		ArrayList<Entidade> users = new ArrayList<Entidade>();
		// e passado por parrametro um objeto generico para facilitar a nossa vida ;)
		String sql = String
		.format("%s",
				"SELECT * FROM `usuario`");
		
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);
	
		while(rs.next()){
			/*Usuario user = new Usuario(); 
			user.getNomeUsuario(), user.getEmailUsuario(),
			user.getTelefoneUsuario(), user.getIdadeUsuario(),
			user.getSexoUsuario(), user.getSenhaUsuario(),
			user.getLoginUsuario(), user.getCpfUsuario(),
			user.getEnderecoUsuario());
			users.add();
			*/
			
			
		}
		
		return users;
	}
	public int creat(Usuario user)  {

		int chave = 0;

		try {

			// Define um insert com os atributos e cada valor do atributo é
			// representado por ?
			String sql = String
					.format("%s %s %s (%d) (%c) %s %s %s %s   ",
							"INSERT INTO `usuario` (`Nome_Usuario`,`E-mail_Usuario`,`Telefone_Usuario`,`Idade_Usuario`,`Sexo_Usuario`,`Senha_Usuario`,"
									+ "`Login_Usuario`,`Cpf_Usuario`,`Endereco_Usuario`) VALUES(? , ?, ?, ?, ?, ?, ?, ?, ?)",
							user.getNomeUsuario(), user.getEmailUsuario(),
							user.getTelefoneUsuario(), user.getIdadeUsuario(),
							user.getSexoUsuario(), user.getSenhaUsuario(),
							user.getLoginUsuario(), user.getCpfUsuario(),
							user.getEnderecoUsuario());

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

	public void readById(Usuario user) {
		try {

			String sql = String.format("%s %d",
					"SELECT * FROM `usuario` I WHERE I.`idUsuario`=",
					user.getUsuarioId());

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// tabela  usuario
					user.setUsuarioId(rs.getInt("idUsuario"));
					user.setNomeUsuario(rs.getString("Nome_Usuario")); 
					user.setEmailUsuario(rs.getString("E-mail_Usuario"));
					user.setTelefoneUsuario(rs.getString("Telefone_Usuario"));
					user.setIdadeUsuario(rs.getInt("Idade_Usuario"));
					user.setSexoUsuario(rs.getString("Sexo_Usuario"));
					user.setSenhaUsuario(rs.getString("Senha_Usuario"));
					user.setLoginUsuario(rs.getString("Login_Usuario"));
					user.setCpfUsuario(rs.getString("Cpf_Usuario"));
					user.setEnderecoUsuario(rs.getString("Endereco_Usuario"));
			}

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

	}

	public void update(Usuario user) {

		try {

			// Define um update com os atributos e cada valor é representado por
			// ?
			String sql = "UPDATE `usuario` SET `Senha_Usuario`=?"
					+ " WHERE `idUsuario`=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, user.getSenhaUsuario());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

	}

	public void delete(Usuario user) {

		try {

			String sql = "DELETE FROM `usuario` WHERE `idUsuario`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, user.getUsuarioId());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

}
