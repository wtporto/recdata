package br.edu.ifpb.recdata.entity;

import java.io.Serializable;


public class Categoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String descricao;
	

	
	public Categoria(int id) {
		setId(id);
	}
	public Categoria(String descricao) {
		setDescricao(descricao);
	}
	public Categoria(){}

	public Categoria(int id, String categoria){		
		setId(id);
		setDescricao(categoria);
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

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + id
				+ ", descricaoCategoria=" + descricao 
				+  "]";
	}
	
}
