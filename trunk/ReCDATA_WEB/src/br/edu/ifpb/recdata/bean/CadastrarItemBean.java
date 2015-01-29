package br.edu.ifpb.recdata.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.edu.ifpb.recdata.entidades.Categoria;
import br.edu.ifpb.recdata.entidades.Item;
import br.edu.ifpb.recdata.entidades.Regiao;
import br.edu.ifpb.recdata.service.ProviderServiceFactory;
import br.edu.ifpb.recdata.service.ReCDATAService;

@ManagedBean(name = "cadastrarItemBean")
@RequestScoped
public class CadastrarItemBean extends Item {

	private List<SelectItem> categorias;
	
	private List<SelectItem> regioes;

	public String cadastrarItem() {
		String navegacao = null;
		
		ReCDATAService service = ProviderServiceFactory
				.createServiceClient(ReCDATAService.class);

		Item item = new Item(super.getCategoria().getId(), 
				super.getRegiao().getId(), getDescricao());
		
		Response response = service.cadastrarItem(item);
		int statusCode = response.getStatus();
		
		if (statusCode == Status.CREATED.getStatusCode()) {
			//Exibir mensagem de sucesso.
			GenericBean.setMessage(null, "info.sucessoCadastroItem",
					FacesMessage.SEVERITY_INFO);
			resetCadastrarItemBean();
		} else {
			//Exibir mensagem de erro.
			GenericBean.setMessage(null, "erro.problemaCadastroItem",
					FacesMessage.SEVERITY_ERROR);
		}
		
		return navegacao;
	}

	public List<SelectItem> getCategorias() {

		CategoriaAppScopeBean categoriaAppBean = (CategoriaAppScopeBean) GenericBean
				.getApplicationContextValue("categoriaAppScopeBean");

		List<Categoria> categorias = categoriaAppBean.getCategorias();

		this.categorias = GenericBean.initSelectOneItem();

		for (Categoria categoria : categorias) {

			this.categorias.add(new SelectItem(categoria,
					categoria.getDescricao()));
		}

		return this.categorias;
	}
	
	public List<SelectItem> getRegioes() {
		
		RegiaoAppScopeBean regiaoAppBean = (RegiaoAppScopeBean) GenericBean
				.getApplicationContextValue("regiaoAppScopeBean");

		List<Regiao> regioes = regiaoAppBean.getRegioes();

		this.regioes = GenericBean.initSelectOneItem();

		for (Regiao regiao : regioes) {

			this.regioes.add(new SelectItem(regiao,
					regiao.getNome()));
		}
		
		return this.regioes;
	}
	
	private void resetCadastrarItemBean() {		
		GenericBean.resetRequestScopedBean("cadastrarItemBean");
	}	
}
