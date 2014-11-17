package br.edu.ifpb.recdata.entidades;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item implements Entidade {

	private int id;
	
	private String descricao;
	
	private Categoria categoria;

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
		return "Item [id=" + id + ", descricao=" + descricao
				+ ", idCategoria=" + categoria.getId() + ", descricaoCategoria="
				+ categoria.getDescricao() + "]";
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@XmlElement
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
