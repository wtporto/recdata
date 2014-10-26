package br.edu.ifpb.recdata.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.recdata.entidades.Item;
import br.edu.ifpb.recdata.service.ProviderServiceFactory;
import br.edu.ifpb.recdata.service.ReCDATAService;

@ManagedBean(name="listarItensBean")
@SessionScoped
public class ListarItensBean extends Item {	
	
	public String mensagem;
	
	ReCDATAService service = ProviderServiceFactory
			.createServiceClient(ReCDATAService.class);
	
	public String listarItens() {
		String navegacao = null;	

		Item item = new Item();
		item.setDescricaoItem(getDescricaoItem());
		List<Item> itens = this.service.consultarItens(item);
		
		mensagem = itens.toString();
		
		System.out.println(mensagem);
		
		return navegacao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}