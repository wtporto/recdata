package entity.Itens;

import br.edu.ifpb.Funcionaidades.CadastroItem.CadastrarItemDataShow;
import br.edu.ifpb.excecoes.IdadeInvalidaException;
import br.edu.ifpb.excecoes.TamanhoInvalidoException;

public class DataShow extends Item {

	String modeloDatashow;
	public DataShow() {
		// TODO Auto-generated constructor stub
	}

	public DataShow(String statusItem, long diItem,String modeloDataShow)
			throws TamanhoInvalidoException, IdadeInvalidaException {
		super(statusItem, diItem);
		setModeloDatashow(modeloDataShow);
	}

	public String getModeloDatashow() {
		return modeloDatashow;
	}

	public void setModeloDatashow(String modeloDatashow)throws TamanhoInvalidoException {
		if (modeloDatashow.length()>15) {
			new TamanhoInvalidoException();
		}
		this.modeloDatashow = modeloDatashow;
	}

	public String toString() {
		return super.toString()+"DataShow [modeloDatashow=" + modeloDatashow + "]";
	}

	public void setTipoCadastrarItem() {
		cadastrarItem = new CadastrarItemDataShow();
		
	}
	

}
