package entity.Itens;

import br.edu.ifpb.Funcionaidades.CadastroItem.CadastrarItemChaveLaboratorio;
import br.edu.ifpb.excecoes.IdadeInvalidaException;
import br.edu.ifpb.excecoes.TamanhoInvalidoException;
import entity.Itens.Chave;

public class ChaveLaboratorio extends Chave {

	 String areaLaboratorioChave;

	 public ChaveLaboratorio() {
		// TODO Auto-generated constructor stub
	}

	public ChaveLaboratorio(String statusItem, long diItem,String localChave,String areaLaboratorioChave)
			throws TamanhoInvalidoException, IdadeInvalidaException {
		super(statusItem, diItem,localChave);
		setAreaLboratorio(areaLaboratorioChave);
	}

	public String getAreaLboratorio() {
		return areaLaboratorioChave;
	}

	public void setAreaLboratorio(String areaLboratorio) throws TamanhoInvalidoException {
		if(areaLboratorio.length()> 15){
			new TamanhoInvalidoException();
		}else
		this.areaLaboratorioChave = areaLboratorio;
	}

	public String toString() {
		return super.toString()+"ChaveLaboratorio [areaLboratorio=" + areaLaboratorioChave + "]";
	}

	public void setTipoCadastrarItem() {
	
		cadastrarItem = new CadastrarItemChaveLaboratorio();
	}
	
}
