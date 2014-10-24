package br.edu.ifpb.recdata.entidades;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item implements Entidade {

	private int idItem;
	
	private String descricaoItem;
	
	private int idCategoria;
	
	private String descricaoCategoria;

	public Item() {}

	/**
	 * Construtor pra readById.
	 * 
	 * @param idCategoria
	 */
	public Item(int idCategoria) {
		setIdCategoria(idCategoria);
	}

	/**
	 * Construtor pra creat.
	 * 
	 * @param idCategoria
	 * @param descricaoItem
	 */
	public Item(int idCategoria, String descricaoItem) {

		setIdCategoria(idCategoria);
		setDescricaoItem(descricaoItem);

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

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	@Override
	public String toString() {
		return "Item [idItem=" + idItem + ", descricaoItem=" + descricaoItem
				+ ", idCategoria=" + idCategoria + ", descricaoCategoria="
				+ descricaoCategoria + "]";
	}
}
