package br.edu.ifpb.recdata.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

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

		Item item = new Item(getIdItem(), getDescricaoItem());
		String resultado = service.cadastrarItem(item);
		
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
}
