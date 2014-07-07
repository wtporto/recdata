package entity.Itens;


import br.edu.ifpb.Funcionaidades.CadastroItem.CadastrarItemCaixaSom;
import br.edu.ifpb.excecoes.IdadeInvalidaException;
import br.edu.ifpb.excecoes.TamanhoInvalidoException;

public class CaixaSom extends Item {

	
	String modeloCaixaSom;
	public CaixaSom() {
		// TODO Auto-generated constructor stub
	}

	public CaixaSom(String statusItem, long diItem)
			throws TamanhoInvalidoException, IdadeInvalidaException  {
		super(statusItem, diItem);
	}

	public String getModeloCaixaSom() {
		return modeloCaixaSom;
	}

	public void setModeloCaixaSom(String modeloCaixaSom) throws TamanhoInvalidoException {
		if(modeloCaixaSom.length()> 20){
			new TamanhoInvalidoException();
		}else
			this.modeloCaixaSom = modeloCaixaSom;
	}
	
	public String toString() {
		return super.toString()+"CaixaSom [modeloCaixaSom=" + modeloCaixaSom + "]";
	}

	public void setTipoCadastrarItem() {
		cadastrarItem = new CadastrarItemCaixaSom();
		
	}
	
	// parte da implementação dos métodos da interface;
	
	
	
	
	
	

}
