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
	
	public static UsuarioDAO getInstance(){
		if(instance == null){
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

		public int creat(Usuario user)  {

		int chave = 0;

		try {

			// Define um insert com os atributos e cada valor do atributo é
			// representado por ?
			String sql = "INSERT INTO `usuario` (`login_usuario`,`senha_usuario`,`nome_usuario`,`email_usuario`,`telefone_usuario`," +
					"                           `cpf_usuario`,`endereco_usuario`,`data_nasc_usuario`,`sexo_usuario`) VALUES"+user.getNomeUsuario()+ 
					user.getEmailUsuario()+	user.getTelefoneUsuario()+ 
					user.getIdadeUsuario()+	user.getSexoUsuario()+ user.getSenhaUsuario()+
					user.getLoginUsuario()+ user.getCpfUsuario()+user.getEnderecoUsuario();

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
					user.setIdadeUsuario(rs.getDate("Idade_Usuario"));
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

	public ArrayList<Usuario> listarTodos() throws SQLException{
		ArrayList<Usuario> users = new ArrayList<Usuario>();
		
		String sql = String
		.format("%s",
				"SELECT * FROM `usuario`");
		
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);
	
		while(rs.next()){
			
			Usuario user = new Usuario(){}; 
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
		
			users.add(user);

		}
		
		return users;
	}

}
