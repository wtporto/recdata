package br.edu.ifpb.recdata.entidades;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Categoria {

	private int idCategoria;
	private String descricaoCategoria;
	
	public Categoria(int id) {
		setIdCategoria(id);
	}
	public Categoria(){
		
	}

	public Categoria(int id, String categoria){
		
		setIdCategoria(id);
		setDescricaoCategoria(categoria);
	}
	
	@XmlElement
	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	@XmlElement
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria
				+ ", descricaoCategoria=" + descricaoCategoria + "]";
	}
	
}
