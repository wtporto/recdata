package web.recdata.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BancoUtil {

	public static int IDVAZIO;
	
	public BancoUtil() {
	}

	public static int getGenerateKey(PreparedStatement stmt)
			throws SQLException {

		int key = 0;

		// recuperar a chave
		ResultSet rs = stmt.getGeneratedKeys();

		// recuperar a chave como inteiro
		if (rs.next()) {
			key = rs.getInt(1);
		}

		return key;
	}
}
