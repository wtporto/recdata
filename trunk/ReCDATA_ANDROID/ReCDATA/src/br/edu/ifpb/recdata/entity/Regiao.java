package br.edu.ifpb.recdata.entity;

import java.io.Serializable;

public class Regiao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String nome;

	public int getId() {
		return id;
	}
	public Regiao( String nome){
		setNome(nome);
	}
	public Regiao( ){
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "Regiao [id=" + id + ", nome=" + nome + "]";
	}
	
}
