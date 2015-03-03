package web.recdata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.recdata.entidades.TipoUsuario;
import web.recdata.factory.DBPool;

public class TipoUsuarioDAO {


		static DBPool banco;
		private static TipoUsuarioDAO instance;

		public static TipoUsuarioDAO getInstance() {
			if (instance == null) {
				banco = DBPool.getInstance();
				instance = new TipoUsuarioDAO(banco);
			}
			return instance;
		}

		public Connection connection;

		public TipoUsuarioDAO(DBPool banco) {
			this.connection = (Connection) banco.getConn();
		}

		public TipoUsuarioDAO() {
			this.connection = (Connection) banco.getConn();
		}
		
		/**
		 * Função: Seleção do Tipo do Usuario no banco de dados pelo ID. 
		 * Retorno: Retorna somente um Tipo do Usuario.
		 * */
		public TipoUsuario readById(TipoUsuario tipousuario) {

			TipoUsuario tipousuarioAux = null;

			try {

				String sql = "SELECT T.cd_tipousuario, T.nm_tipousuario"
						+ " FROM  tb_tipousuario  as T" 
						+ " WHERE  T.cd_tipousuario = ?";

				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				stmt.setInt(1, tipousuario.getId());
				
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					tipousuarioAux = new TipoUsuario();
					tipousuarioAux.setId(rs.getInt("cd_tipousuario"));
					tipousuarioAux.setDescricao(rs
							.getString("nm_tipousuario"));
				}
				
				stmt.close();
				rs.close();

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

			return tipousuarioAux;
		}

		/**
		 * Função: Atualizar o valor da descrição do TipoUsuario identificando pelo ID.
		 * Retorno: VOID.
		 * */
		public void update(TipoUsuario tipousuario) {

			try {

				String sql = "UPDATE tb_tiposuario SET nm_tipousuario = ?"
						+ " WHERE cd_tipousuario = ?";

				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				stmt.setString(1, tipousuario.getDescricao());
				stmt.setInt(2, tipousuario.getId());
				
				stmt.execute();
				stmt.close();

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}

		/**
		 * Função: Deleta o tipo do usuario identificada pelo ID.
		 * Retorno: VOID.
		 * */
		public void delete(TipoUsuario tipousuario) {

			try {
				String sql = "DELETE FROM tb_tipousuario"
						+ " WHERE cd_tipousuario = ?";

				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				stmt.setInt(1, tipousuario.getId());

				stmt.execute();
				stmt.close();

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

		/**
		 * Função: Seleção de todas os Tipos de Usuario que estão no banco de dados.
		 * Retorno: ArrayList de Tipo de Usuarios.
		 * 
		 * FUNCIONANDO (TESTADO)
		 * */
		public List<TipoUsuario> listarTodos() throws SQLException {

			ArrayList<TipoUsuario> tiposUsuarios = new ArrayList<TipoUsuario>();

			String sql = "SELECT T.cd_tipousuario, T.nm_tipousuario"
					+ " FROM tb_tipousuario as T";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				TipoUsuario tipousuario = new TipoUsuario();
				tipousuario.setId(rs.getInt("T.cd_tipousuario"));
				tipousuario
						.setDescricao(rs.getString("T.nm_tipousuario"));
				tiposUsuarios.add(tipousuario);
			}

			stmt.execute();
			stmt.close();

			return tiposUsuarios;
		}
	}


