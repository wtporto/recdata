package web.recdata.factory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import snaq.db.ConnectionPoolManager;

/**
 * DBPool class file will be used to get new Database connection with the help
 * of ConnectionPoolManager.
 * 
 * It will fetch all the DB Connection properties from DBPool.properties file
 * and it will create new connection.
 */
public class DBPool {

	protected Connection conn;
	protected ConnectionPoolManager connManager;
	private static DBPool dbPool;

	static Logger logger = Logger.getLogger(DBPool.class);

	// Name of the database connection name from DBPool.properties file.
	// static final String databaseName = "pool-mysql";
	static final String databaseName = "pool-recdata";

	/**
	 * Class constructor creates ConnectionPoolManager object
	 * 
	 * @exception properties
	 *                file not found.
	 */
	public DBPool() {
		try {
			
			connManager = ConnectionPoolManager
					.getInstance("DBPool.properties");
			
			System.out.println(connManager.getName());
		
		} catch (IOException ex) {
			logger.info("Error While Connecting with DBPool Properties file :=> "
					+ ex.toString());
		}
	}

	/**
	 * Creates/Provides the instance of the Pool.
	 * 
	 * @return DBPool
	 */
	public static DBPool getInstance() {
		if (dbPool == null)
			dbPool = new DBPool();
		return dbPool;
	}

	/**
	 * Sets the connection object.
	 * 
	 * @exception cannot
	 *                get connection.
	 */
	public Connection getConn() {
		
		Connection con = null;

		try {
			con = connManager.getConnection(databaseName);
			logger.info("Connection Created: " + con.toString());

			if (con != null) {
				this.conn = con;
				logger.info("Connection Released: " + this.conn.toString());
			}

		} catch (SQLException ex) {
			logger.info("Error While Creating Connection :=> " + ex.toString());
		}
		
		return con;
	}
}