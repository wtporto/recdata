package entity.Usuarios;


import br.edu.ifpb.Funcionaidades.CadastroUsuarios.CadastrarUsuarioServidor;
import br.edu.ifpb.excecoes.IdadeInvalidaException;
import br.edu.ifpb.excecoes.NomeInvalidoException;
import br.edu.ifpb.excecoes.SenhaInvalidaException;
import br.edu.ifpb.excecoes.TamanhoInvalidoException;

public class Servidor extends Usuario {

	@Override
	public String toString() {
		return super.toString()+"Servidor []";
	}

	// permição para cadastrar itens etc...
	public Servidor() {
		// TODO Auto-generated constructor stub
	}

	public Servidor(String nomeUsuario, int idadeUsuario, String emailUsuario,
			long telefoneUsuario, char sexoUsuario, String senhaUsuario)
			throws NomeInvalidoException, TamanhoInvalidoException,
			IdadeInvalidaException, SenhaInvalidaException {
		super(nomeUsuario, idadeUsuario, emailUsuario, telefoneUsuario,
				sexoUsuario, senhaUsuario);
		// TODO Auto-generated constructor stub
	}
	
	private void Permicao(){
		
		System.out.println("Voce Pode cadastra Itens , OK");
	}

	public void setTipoCadastroUsuario() {
		cadastroUsuario = new CadastrarUsuarioServidor();
	}
	
}
