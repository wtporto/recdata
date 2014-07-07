package entity.Usuarios;


import br.edu.ifpb.Funcionaidades.CadastroUsuarios.CadastroUsuarioProfessor;
import br.edu.ifpb.excecoes.IdadeInvalidaException;
import br.edu.ifpb.excecoes.NomeInvalidoException;
import br.edu.ifpb.excecoes.SenhaInvalidaException;
import br.edu.ifpb.excecoes.TamanhoInvalidoException;

public class Professor extends Usuario {

	String formacaoProfessor;
	String areadeatuacaoProfessor;
	public Professor() {
		// TODO Auto-generated constructor stub
	}

	public Professor(String nomeUsuario, int idadeUsuario, String emailUsuario,
			long telefoneUsuario, char sexoUsuario,String senhaUsuario,String formacao,String areadeatuacaoProfessor)
			throws NomeInvalidoException, TamanhoInvalidoException,
			IdadeInvalidaException, SenhaInvalidaException {
		super(nomeUsuario, idadeUsuario, emailUsuario, telefoneUsuario,
				sexoUsuario,senhaUsuario);
		setFormacao(formacao);
		setAreadeatucaoProfessor(areadeatuacaoProfessor);
		// TODO Auto-generated constructor stub
	}

	public String getFormacao() {
		return this.formacaoProfessor;
	}

	public void setFormacao(String formacao)throws TamanhoInvalidoException {
	  if (formacao.length() > 50) {
		new TamanhoInvalidoException();
	}else
		this.formacaoProfessor = formacao;
	}

	public String getAreadeatucaoProfessor() {
		return areadeatuacaoProfessor;
	}

	public void setAreadeatucaoProfessor(String areadeatucaoProfessor)throws TamanhoInvalidoException {
		  if (areadeatucaoProfessor.length() > 20) {
				new TamanhoInvalidoException();
			}else
				this.areadeatuacaoProfessor = areadeatucaoProfessor;
	}

	@Override
	public String toString() {
		return super.toString()+"Professor [formacaoProfessor=" + formacaoProfessor
				+ ", areadeatuacaoProfessor=" + areadeatuacaoProfessor + "]";
	}

	public void setTipoCadastroUsuario() {
		cadastroUsuario = new CadastroUsuarioProfessor();
	}
	
}
