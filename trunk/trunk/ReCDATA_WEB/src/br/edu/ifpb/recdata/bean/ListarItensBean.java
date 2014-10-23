package br.edu.ifpb.recdata.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="listarItensBean")
@RequestScoped
public class ListarItensBean {
	
	private String texto;

	public String getTexto() {
		return "Testando";
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
}