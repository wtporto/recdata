package web.recdata.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import web.recdata.factory.ConnectionFactory;
import web.recdata.model.CaixadeSom;
import web.recdata.model.Entidade;
import web.recdata.model.Item;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class CaixadeSomDAO implements DAO {

	// a conexão com o banco de dados
	public Connection connection;

	private ItemDAO itemDAO;

	public CaixadeSomDAO(ConnectionFactory banco) {
		this.connection = (Connection) banco.getConnection();
		itemDAO = new ItemDAO(banco);
	}

	public ArrayList<Entidade> listarTodos() throws SQLException{
		ArrayList<Entidade> itens = new ArrayList<Entidade>();
		// e passado por parrametro um objeto generico para facilitar a nossa vida ;)
		String sql = String
		.format("%s",
				"SELECT idItem, statusItem, Descrição_CaixadeSom FROM `item` I,`caixadeSom` C " +
				"WHERE I.idItem =C.idItem_CaixadeSom");
		
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery(sql);
	
		while(rs.next()){
			Item item = new Item();
			item.setIdItem(rs.getInt("idItem"));
			item.setStatusItem(rs.getBoolean("statusItem"));
			CaixadeSom caixadesom = new CaixadeSom();
			caixadesom.setDescricaoCaixaSom("Descrição_CaixadeSom");
			itens.add(item);
			itens.add(caixadesom);//add o item caixa de som
		}
		
		return itens;
	}
	@Override
	public void creat(Entidade entidade) {

		if (entidade instanceof CaixadeSom) {

			CaixadeSom caixadesomItem = (CaixadeSom) entidade;

			int chave = itemDAO.creat(caixadesomItem);

			if (chave != 0) {

				try {

					String sql = "INSERT INTO `caixadesom` (`idItem_CaixadeSom`,`Descrição_CaixadeSom`)"
							+ " VALUES (?, ?)";

					// prepared statement para inserção
					PreparedStatement stmt = (PreparedStatement) connection
							.prepareStatement(sql);

					// seta os valores
					stmt.setInt(1, chave);
					stmt.setString(2, caixadesomItem.getDescricaoCaixaSom());

					// envia para o Banco e fecha o objeto
					stmt.execute();
					stmt.close();

				} catch (SQLException sqle) {
					throw new RuntimeException(sqle);
				}

			} else
				System.err.println("Não foi possível inserir Caixa de som!");

		}
	}

	@Override
	public void readById(Entidade entidade) {
		if (entidade instanceof CaixadeSom) {

			CaixadeSom caixadesom = (CaixadeSom) entidade;

			try {
				/*
				 * idItem_CaixadeSom` INT NOT NULL AUTO_INCREMENT,
				 * `Descrição_CaixadeSom` VARCHAR(50) NOT NULL,
				 */
				String sql = "SELECT * FROM `caixadeSom` CX " +
						"INNER JOIN `item` I ON CX.`idItem_CaixadeSom` = I.`idItem`" +
						"WHERE CX.`idItem`=" + caixadesom.getIdItem();

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
						caixadesom.setIdItem(rs.getInt("idItem_CaixadeSom"));
					    caixadesom.setStatusItem(rs.getInt("statusItem") == 1 ? true: false  );
						caixadesom.setDescricaoCaixaSom(rs.getString("Descrição_CaixadeSom"));
				}

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		}  
	}

	@Override
	public void update(Entidade entidade)  {
		if (entidade instanceof CaixadeSom) {

			CaixadeSom caixadesom = (CaixadeSom) entidade;

            itemDAO.update(caixadesom);

            try {

                    String sql = "UPDATE `caixadeSom` SET `Descrição_CaixadeSom`=?"
                                    + " WHERE `idItem_CaixadeSom`=?";

                    // prepared statement para inserção
                    PreparedStatement stmt = (PreparedStatement) connection
                                    .prepareStatement(sql);
                    // seta os valores
                    stmt.setString(1, caixadesom.getDescricaoCaixaSom());
                
                    // envia para o Banco e fecha o objeto
                    stmt.execute();

            } catch (SQLException sqle) {
                    throw new RuntimeException(sqle);
            }

    }  

	}

	@Override
	public void delete(Entidade entidade)  {
		  if (entidade instanceof CaixadeSom) {

			  CaixadeSom caixadesom = (CaixadeSom) entidade;

              try {

                      // Deleta uma tupla setando o atributo de identificação com
                      // valor representado por ?
                      String sql = "DELETE FROM `caixadesom` WHERE `idItem_Caixadesom`=?";

                      // prepared statement para inserção
                      PreparedStatement stmt = (PreparedStatement) connection
                                      .prepareStatement(sql);

                      // seta os valores
                      stmt.setInt(1, caixadesom.getIdItem());

                      // envia para o Banco e fecha o objeto
                      stmt.execute();
                      stmt.close();

                      itemDAO.delete(caixadesom);

              } catch (SQLException sqle) {
                      sqle.printStackTrace();
              }
      }
	}

}
