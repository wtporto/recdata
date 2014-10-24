package br.edu.ifpb.recdata.entidades;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item implements Entidade {

	private int idItem;
	
	private String descricaoItem;
	
	private Categoria categoria;

	public Item() {}

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
				+ ", idCategoria=" + categoria.getIdCategoria() + ", descricaoCategoria="
				+ categoria.getDescricaoCategoria() + "]";
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
}
