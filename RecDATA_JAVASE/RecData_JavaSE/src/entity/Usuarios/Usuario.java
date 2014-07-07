package entity.Usuarios;

import br.edu.ifpb.Funcionaidades.CadastroUsuarios.CadastrarUsuario;
import br.edu.ifpb.excecoes.IdadeInvalidaException;
import br.edu.ifpb.excecoes.NomeInvalidoException;
import br.edu.ifpb.excecoes.SenhaInvalidaException;
import br.edu.ifpb.excecoes.TamanhoInvalidoException;
import br.edu.ifpb.excecoes.TelefoneInvalidoException;

public abstract class Usuario {

	 public CadastrarUsuario cadastroUsuario;
	 String nomeUsuario;
	 int    idadeUsuario;
	 String emailUsuario;
	 long   telefoneUsuario;
	 char   sexoUsuario;
	 String senhaUsuario;
	 
	public Usuario() {
	  super();
	}
	
	public Usuario(String nomeUsuario, int idadeUsuario, String emailUsuario, 
			       long telefoneUsuario, char  sexoUsuario,String senhausuario) throws NomeInvalidoException, TamanhoInvalidoException, IdadeInvalidaException, SenhaInvalidaException{
		
		setNomeUsuario(nomeUsuario);
		setIdadeUsuario(idadeUsuario);
		setEmailUsuario(emailUsuario);
		setTelefoneUsuario(telefoneUsuario);
		setSexoUsuario(sexoUsuario);
		setSenhaUsuario(senhausuario);
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) throws NomeInvalidoException,TamanhoInvalidoException {
		if(nomeUsuario.equalsIgnoreCase("")){
			new NomeInvalidoException();
		}else
		if(nomeUsuario.length() > 30){
			new TamanhoInvalidoException();
			
		}else
		
			this.nomeUsuario =nomeUsuario;
	}

	public int getIdadeUsuario() {
		return idadeUsuario;
	}

	public void setIdadeUsuario(int idadeUsuario)throws IdadeInvalidaException{
		
		if(idadeUsuario< 0){
			new IdadeInvalidaException();
		}else
		   if(idadeUsuario > 100){
			   new IdadeInvalidaException();
		   }else
			   this.idadeUsuario = idadeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public long getTelefoneUsuario() {
		return telefoneUsuario;
	}

	public void setTelefoneUsuario(long telefoneUsuario) throws TelefoneInvalidoException{
		
			new TelefoneInvalidoException();
		    this.telefoneUsuario = telefoneUsuario;
	}

	public char getSexoUsuario() {
		return sexoUsuario;
	}

	public void setSexoUsuario(char sexoUsuario) {
		this.sexoUsuario = sexoUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario)throws SenhaInvalidaException {
		
		if(senhaUsuario == null){
			new SenhaInvalidaException();
		}else
			this.senhaUsuario = senhaUsuario;
	
	}
	
	@Override
	public String toString() {
		return "Usuario [cadastroUsuario=" + cadastroUsuario + ", nomeUsuario="
				+ nomeUsuario + ", idadeUsuario=" + idadeUsuario
				+ ", emailUsuario=" + emailUsuario + ", telefoneUsuario="
				+ telefoneUsuario + ", sexoUsuario=" + sexoUsuario
				+ ", senhaUsuario=" + senhaUsuario + "]";
	}

	public abstract void setTipoCadastroUsuario();

}
