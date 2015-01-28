package br.edu.ifpb.recdata.entidades;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Categoria {

	private int id;
	
	private String descricao;
	
	private Date registro;
	
	public Categoria(int id) {
		setId(id);
	}
	
	public Categoria(){}

	public Categoria(int id, String categoria){		
		setId(id);
		setDescricao(categoria);
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

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + id
				+ ", descricaoCategoria=" + descricao 
				+ "registro=" + registro + "]";
	}
	
}
