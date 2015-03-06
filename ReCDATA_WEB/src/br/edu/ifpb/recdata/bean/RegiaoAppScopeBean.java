package br.edu.ifpb.recdata.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.edu.ifpb.recdata.entidades.Regiao;
import br.edu.ifpb.recdata.service.ProviderServiceFactory;
import br.edu.ifpb.recdata.service.ReCDATAService;

@ManagedBean(eager = true)
@ApplicationScoped
public class RegiaoAppScopeBean implements Serializable {

	private static final long serialVersionUID = -6300467749382296451L;

	private List<Regiao> regioes;

	public RegiaoAppScopeBean() {
	}

	private void populateRegioes() {

		if (regioes == null || (regioes != null && regioes.isEmpty())) {
			
			// Recuperar das categorias do servidor os valores.			
			ReCDATAService service = ProviderServiceFactory
					.createServiceClient(ReCDATAService.class);
			
			// Atribui a lista de categorias.
			regioes = service.listarRegioes();
		}
	}

	public List<Regiao> getRegioes() {
		populateRegioes();
		return regioes;
	}
}