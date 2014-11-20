package br.edu.ifpb.recdata.entity;

public class Regiao {

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
}
