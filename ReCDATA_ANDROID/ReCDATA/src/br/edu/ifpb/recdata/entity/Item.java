package br.edu.ifpb.recdata.entity;

import java.util.Date;

import br.edu.ifpb.R;

public class Item  {

	private int id;
	
	private String descricao;
	
	private Categoria categoria;
	
	private Regiao regiao;
	
	private Date registro;

	public Item() {}

	/**
	 * Construtor pra readById.
	 * 
	 * @param idCategoria
	 */
	public Item(int idCategoria) {
		categoria = new Categoria();
		categoria.setId(idCategoria);
	}

	/**
	 * Construtor pra creat.
	 * 
	 * @param idCategoria
	 * @param descricaoItem
	 */
	public Item(int idCategoria, String descricao) {
		categoria = new Categoria();
		categoria.setId(idCategoria);
		setDescricao(descricao);
		
	}

	
	@Override
	public String toString() {
		return "Item [id=" + id + ", descricao=" + descricao + ", categoria="
				+ categoria + ", regiao=" + regiao + ", registro=" + registro
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}
	public int getImagem(int idcategoria) {
		switch (idcategoria) {
		case 1:
			return (R.drawable.icon_chave);
		case 2:
			return (R.drawable.icon_caixasom);
		case 3:
			return (R.drawable.icon_datashow);

		case 4:
			return (R.drawable.icon_notebook);
		default:
			return (R.drawable.icon_errodefault);
		}
	}
}
