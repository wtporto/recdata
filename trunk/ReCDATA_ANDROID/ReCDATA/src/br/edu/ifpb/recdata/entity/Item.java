package br.edu.ifpb.recdata.entity;

import java.io.Serializable;

import br.edu.ifpb.R;

public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int idItem;

	private String descricaoItem;

	private Categoria categoria;

	public Item() {
	}

	/**
	 * Construtor pra readById.
	 * 
	 * @param idCategoria
	 */
	public Item(int idCategoria) {
		categoria = new Categoria();
		categoria.setIdCategoria(idCategoria);
	}

	/**
	 * Construtor pra creat.
	 * 
	 * @param idCategoria
	 * @param descricaoItem
	 */
	public Item(int idCategoria, String descricaoItem) {
		categoria = new Categoria();
		categoria.setIdCategoria(idCategoria);
		setDescricaoItem(descricaoItem);
	}

	@Override
	public String toString() {
		return "Item [idItem=" + idItem + ", descricaoItem=" + descricaoItem
				+ ", idCategoria=" + categoria.getIdCategoria()
				+ ", descricaoCategoria=" + categoria.getDescricaoCategoria()
				+ "]";
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getDescricaoItem() {
		return descricaoItem;
	}

	public void setDescricaoItem(String descricaoItem) {
		this.descricaoItem = descricaoItem;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
