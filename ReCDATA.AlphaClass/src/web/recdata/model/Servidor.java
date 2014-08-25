package entidades;

public class Servidor extends Usuario implements Entidade {

	private boolean privilegioServidor;
	
	public Servidor(int usuarioId) {
		super(usuarioId);
		
	}

	public Servidor(String nome, String email, String telefone,
			int idade, String sexo, String cpf, String endereco, String login,
			String senha, boolean privilegio) {
		super(nome, email, telefone, idade, sexo, cpf, endereco,
				login, senha);

		setPrivilegioServidor(privilegio);
	}

	public boolean isPrivilegioServidor() {
		return privilegioServidor;
	}

	public void setPrivilegioServidor(boolean privilegioServidor) {
		this.privilegioServidor = privilegioServidor;
	}

	@Override
	public String toString() {
		return super.toString()+"  Servidor [privilegioServidor=" + privilegioServidor + "]";
	}

}
