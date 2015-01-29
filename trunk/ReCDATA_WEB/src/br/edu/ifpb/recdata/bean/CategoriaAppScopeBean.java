package br.edu.ifpb.recdata.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.edu.ifpb.recdata.entidades.Categoria;
import br.edu.ifpb.recdata.service.ProviderServiceFactory;
import br.edu.ifpb.recdata.service.ReCDATAService;

@ManagedBean(eager = true)
@ApplicationScoped
public class CategoriaAppScopeBean implements Serializable {

	private static final long serialVersionUID = -6300467749382296451L;

	private List<Categoria> categorias;

	public CategoriaAppScopeBean() {
	}

	private void populateCategorias() {

		if (categorias == null || (categorias != null && categorias.isEmpty())) {
			
			// Recuperar das categorias do servidor os valores.			
			ReCDATAService service = ProviderServiceFactory
					.createServiceClient(ReCDATAService.class);
			
			// Atribui a lista de categorias.
			categorias = service.listarCategorias();
		}
	}

	public List<Categoria> getCategorias() {
		populateCategorias();
		return categorias;
	}
}