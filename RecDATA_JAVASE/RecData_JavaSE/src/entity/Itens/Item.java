package entity.Itens;


import br.edu.ifpb.Funcionaidades.CadastroItem.CadastrarItem;
import br.edu.ifpb.excecoes.IdadeInvalidaException;
import br.edu.ifpb.excecoes.TamanhoInvalidoException;

public abstract class  Item  {
	
	public CadastrarItem cadastrarItem;
	String statusItem;
	long   idItem;
	
	public Item() {
		super();		
	}
	
	public Item(String statusItem, long diItem) throws TamanhoInvalidoException, IdadeInvalidaException {
		setStatusItem(statusItem);
		setIdItem(diItem);
	
		
	}
 
	public String getStatusItem() {
		return statusItem;
	}

	public void setStatusItem(String statusItem) throws TamanhoInvalidoException{
		if (statusItem.length() > 15 ){
			new TamanhoInvalidoException();
		}else
		this.statusItem = statusItem;
	}

	public long getIdItem() {
		return idItem;
	}

	public void setIdItem(long idItem) throws IdadeInvalidaException {
		this.idItem = idItem;
	}

	public abstract void setTipoCadastrarItem();

	public String toString() {
		return "Item [statusItem=" + statusItem + ", idItem=" + idItem + "]";
	}

	

}
