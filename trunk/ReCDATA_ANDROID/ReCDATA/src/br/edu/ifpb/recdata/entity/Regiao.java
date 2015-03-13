package br.edu.ifpb.recdata.entity;

import java.io.Serializable;

public class Regiao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String descricao;

	public int getId() {
		return id;
	}
	public Regiao( String nome){
		setDescricao(nome);
	}
	public Regiao( ){
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String nome) {
		this.descricao = nome;
	}
	@Override
	public String toString() {
		return "Regiao [id=" + id + ", Descricao=" + descricao + "]";
	}
	
}
