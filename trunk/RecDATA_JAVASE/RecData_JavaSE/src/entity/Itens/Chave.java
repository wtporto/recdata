package entity.Itens;

import br.edu.ifpb.Funcionaidades.CadastroItem.CadastrarItemChave;
import br.edu.ifpb.excecoes.IdadeInvalidaException;
import br.edu.ifpb.excecoes.TamanhoInvalidoException;

public class Chave extends Item {

	String localChave;
	
	public Chave() {
		// TODO Auto-generated constructor stub
	}

	public Chave(String statusItem, long diItem, String localChave)
			throws TamanhoInvalidoException, IdadeInvalidaException {
		super(statusItem, diItem);
		setLocalChave(localChave);
	}

	public String getLocalChave() {
		return localChave;
	}

	public void setLocalChave(String localChave) {
		this.localChave = localChave;
	}

	public String toString() {
		return super.toString()+"Chave [localChave=" + localChave + "]";
	}

	public void setTipoCadastrarItem() {
		cadastrarItem = new CadastrarItemChave();
		
	}
	

}
