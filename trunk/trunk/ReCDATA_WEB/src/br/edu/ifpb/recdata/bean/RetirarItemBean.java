package br.edu.ifpb.recdata.bean;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.edu.ifpb.recdata.entidades.Categoria;
import br.edu.ifpb.recdata.entidades.Regiao;
import br.edu.ifpb.recdata.entidades.Retirada;

@ManagedBean(name = "retirarItemBean")
@SessionScoped
public class RetirarItemBean extends Retirada {

	private List<SelectItem> categorias;

	private List<SelectItem> regioes;

	private Categoria categoria;

	private Regiao regiao;
	
	private String descricao;
	
	private Date dataReserva;

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

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}
}
