package br.edu.ifpb.recdata.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.edu.ifpb.recdata.entidades.Categoria;
import br.edu.ifpb.recdata.entidades.Item;
import br.edu.ifpb.recdata.entidades.Regiao;
import br.edu.ifpb.recdata.entidades.ReservaItem;
import br.edu.ifpb.recdata.entidades.Retirada;
import br.edu.ifpb.recdata.service.ProviderServiceFactory;
import br.edu.ifpb.recdata.service.ReCDATAService;

@ManagedBean(name = "retirarItemBean")
@SessionScoped
public class RetirarItemBean extends Retirada {

	private List<SelectItem> categorias;

	private List<SelectItem> regioes;
	
	private List<ReservaItem> reservas = new ArrayList<ReservaItem>();

	private Categoria categoria;

	private Regiao regiao;
	
	private String descricao;

	public void retirarItem(ReservaItem reserva) {
		System.out.println("reserva");
	}
	
	public String listarReservas() {
		String navegacao = null;
		
		ReservaItem reserva = new ReservaItem();
		Item item = new Item();
		item.setRegiao(regiao);
		item.setCategoria(categoria);
		item.setDescricao(descricao);
		
		reserva.setItem(item);
		
		ReCDATAService service = ProviderServiceFactory
				.createServiceClient(ReCDATAService.class);
		
		this.reservas = service.consultarReservas(reserva);
		
		return navegacao;
	}
	
	public List<SelectItem> getCategorias() {

		CategoriaAppScopeBean categoriaAppBean = (CategoriaAppScopeBean) GenericBean
				.getApplicationContextValue("categoriaAppScopeBean");

		List<Categoria> categorias = categoriaAppBean.getCategorias();

		this.categorias = GenericBean.initSelectOneItem();

		for (Categoria categoria : categorias) {

			this.categorias.add(new SelectItem(categoria, categoria
					.getDescricao()));
		}

		return this.categorias;
	}

	public List<SelectItem> getRegioes() {

		RegiaoAppScopeBean regiaoAppBean = (RegiaoAppScopeBean) GenericBean
				.getApplicationContextValue("regiaoAppScopeBean");

		List<Regiao> regioes = regiaoAppBean.getRegioes();

		this.regioes = GenericBean.initSelectOneItem();

		for (Regiao regiao : regioes) {

			this.regioes.add(new SelectItem(regiao, regiao.getNome()));
		}

		return this.regioes;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ReservaItem> getReservas() {
		return reservas;
	}

	public void setReservas(List<ReservaItem> reservas) {
		this.reservas = reservas;
	}
}
