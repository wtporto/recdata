package web.recdata.dao;

import web.recdata.factory.ConnectionFactory;

import com.mysql.jdbc.Connection;


public class ReservaDAO 	{
	
	// a conex�o com o banco de dados
	public Connection connection;


	public ReservaDAO(ConnectionFactory banco) {
		this.connection = (Connection) banco.getConnection();
	}
}
