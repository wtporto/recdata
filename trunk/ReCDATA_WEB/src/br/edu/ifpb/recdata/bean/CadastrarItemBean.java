package br.edu.ifpb.recdata.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.edu.ifpb.recdata.entidades.Categoria;
import br.edu.ifpb.recdata.entidades.Item;
import br.edu.ifpb.recdata.service.ProviderServiceFactory;
import br.edu.ifpb.recdata.service.ReCDATAService;

@ManagedBean(name = "cadastrarItemBean")
@RequestScoped
public class CadastrarItemBean extends Item {

	private List<SelectItem> categorias;

	public String cadastrarItem() {
		String navegacao = null;
		
		ReCDATAService service = ProviderServiceFactory
				.createServiceClient(ReCDATAService.class);

		Item item = new Item(super.getCategoria().getIdCategoria(), getDescricaoItem());
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

		CategoriaAppScopeBean cityStateAppBean = (CategoriaAppScopeBean) GenericBean
				.getApplicationContextValue("categoriaAppScopeBean");

		List<Categoria> categoriasAppBean = cityStateAppBean.getCategorias();

		this.categorias = GenericBean.initSelectOneItem();

		for (Categoria categoriaAppBean : categoriasAppBean) {

			this.categorias.add(new SelectItem(categoriaAppBean,
					categoriaAppBean.getDescricaoCategoria()));
		}

		return this.categorias;
	}
	
	private void resetCadastrarItemBean() {		
		GenericBean.resetRequestScopedBean("cadastrarItemBean");
	}
}
