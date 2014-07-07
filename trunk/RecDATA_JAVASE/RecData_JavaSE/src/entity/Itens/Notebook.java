package entity.Itens;

import br.edu.ifpb.Funcionaidades.CadastroItem.CadastrarItemNotebook;
import br.edu.ifpb.excecoes.IdadeInvalidaException;
import br.edu.ifpb.excecoes.TamanhoInvalidoException;

public class Notebook extends Item {

	String modeloNotebook;
	String marcaNotebook;
	public Notebook() {
		// TODO Auto-generated constructor stub
	}

	public Notebook(String statusItem, long diItem,String modeloNotebook,String marcaNotebook)
			throws TamanhoInvalidoException, IdadeInvalidaException {
		super(statusItem, diItem);
		setModeloNotebook(modeloNotebook);
	}

	public String getModeloNotebook() {
		return modeloNotebook;
	}

	public void setModeloNotebook(String modeloNotebook)throws TamanhoInvalidoException{
		if (modeloNotebook.length()> 15) {
			new TamanhoInvalidoException();
		}
		this.modeloNotebook = modeloNotebook;
	}

	public String getMarcaNotebook() {
		return marcaNotebook;
	}

	public void setMarcaNotebook(String marcaNotebook) {
		this.marcaNotebook = marcaNotebook;
	}

	public String toString() {
		return super.toString()+"Notebook [modeloNotebook=" + modeloNotebook
				+ ", marcaNotebook=" + marcaNotebook + "]";
	}

	public void setTipoCadastrarItem() {
		cadastrarItem = new CadastrarItemNotebook();
		
	}

	
}


