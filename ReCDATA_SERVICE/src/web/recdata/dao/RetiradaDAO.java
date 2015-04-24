package web.recdata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import web.recdata.factory.DBPool;
import web.recdata.util.BancoUtil;
import br.edu.ifpb.recdata.entidades.Retirada;

public class RetiradaDAO {

	static DBPool banco;
	
	private static RetiradaDAO instance;
	
	public Connection connection;

	public static RetiradaDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new RetiradaDAO(banco);
		return instance;
	}	

	public RetiradaDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	public RetiradaDAO() {
		this.connection = (Connection) banco.getConn();
	}
	
	public int create(Retirada retirada) {
		
		int idRetirada = BancoUtil.IDVAZIO;
		
		try {

			String sql = "INSERT INTO tb_ (cd_reserva, cd_usuario_liberacao)"
					+ " VALUES ("
					+ retirada.getReserva().getId() + ", "
					+ retirada.getUsuarioLiberacao().getId()
					+ ")";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idRetirada = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			
			throw new RuntimeException(sqle);
		}

		return idRetirada;
	}
}
