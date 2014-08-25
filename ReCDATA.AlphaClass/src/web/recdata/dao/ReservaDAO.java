package dao;

import java.sql.SQLException;

import principal.Banco;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import entidades.Chave;
import entidades.Entidade;
import excecoes.ClasseInvalidaException;

public class ReservaDAO implements DAO{
	
	// a conexão com o banco de dados
	public Connection connection;


	public ReservaDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}


	@Override
	public void creat(Entidade entidade) throws ClasseInvalidaException {
		if (entidade instanceof Chave) {

			Chave chaveItem = (Chave) entidade;

				try {

					String sql = "INSERT INTO `chave` (`idItem_Chave`,`Local_Chave`,`DescriçãoChave`)"
							+ " VALUES (?, ?, ?)";

					// prepared statement para inserção
					PreparedStatement stmt = (PreparedStatement) connection
							.prepareStatement(sql);

					// seta os valores
					stmt.setInt(1, 
					stmt.setString(2, chaveItem.getLocalChave());
					stmt.setString(3, chaveItem.getDescricaoChave());

					// envia para o Banco e fecha o objeto
					stmt.execute();
					stmt.close();

				} catch (SQLException sqle) {
					throw new RuntimeException(sqle);
				}

			} else
				System.err.println("Não foi possível inserir Chave!");
		{
			throw new ClasseInvalidaException();
		}

	}

	@Override
	public void readById(Entidade entidade) throws ClasseInvalidaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Entidade entidade) throws ClasseInvalidaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Entidade entidade) throws ClasseInvalidaException {
		// TODO Auto-generated method stub
		
	}

}
