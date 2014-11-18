package br.edu.ifpb.recdata.entidades;

public class Regiao {

	private int id;
	
	private String nome;

	@Override
	public String toString() {
		return "[id = "+ id + ", nome = "+ nome +"]";
	}
	
	public int getId() {
		return id;
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
