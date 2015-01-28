package web.recdata.factory;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.PreparedStatement;

public class ConnectionFactory {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/recdata";
	private static final String USER = "recdata_user";
	private static final String PASS = "r3cd4t4_us3r";
	private Connection connection = null;
	private PreparedStatement stm = null;

	public ConnectionFactory() {
		iniciarConexao();
	}

	// Estabelecer conexão com o banco de dados
	public void iniciarConexao() {

		System.out.println("Estabelecendo conexão...");

		try {
			Class.forName(DRIVER);
			this.connection = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			System.out.println("Não foi possivel conectar ao banco de dados: "
					+ URL);
			e.printStackTrace();
		}
	}

	// Encerrar conexão com banco de dados
	public void encerrarConexao() {
		try {
			if (this.stm != null)
				this.stm.close();
			if (this.connection != null)
				this.connection.close();
			System.out.println("Conexão encerrada!!");
		} catch (Exception e) {
			System.out
					.println("Não foi possivel encerrar conexão ao banco de dados: "
							+ URL);
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

}