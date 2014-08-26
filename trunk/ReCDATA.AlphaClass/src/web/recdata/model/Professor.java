package web.recdata.model;

public class Professor extends Usuario implements Entidade{

	private String formacaoProfessor;
	
	public Professor(int usuarioId) {
		super(usuarioId);
		
	}

	public Professor(String nome, String email, String telefone,
			int idade, String sexo, String cpf, String endereco, String login,
			String senha, String formacao) {
		super(nome, email, telefone, idade, sexo, cpf, endereco,
				login, senha);
		setFormacaoProfessor(formacao);


	}

	public String getFormacaoProfessor() {
		return formacaoProfessor;
	}

	public void setFormacaoProfessor(String formacaoProfessor) {
		this.formacaoProfessor = formacaoProfessor;
	}

	@Override
	public String toString() {
		return super.toString()+"  Professor [formacaoProfessor=" + formacaoProfessor + "]";
	}
	
	

}
