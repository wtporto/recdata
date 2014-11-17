package br.edu.ifpb.recdata.entidades;

import java.util.Date;

public class Retirada {

	private int id;
	private Item  idUsuarioLiberacao;
	private int idReserva;
	private Date registro;
	private Date retirada;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Item getIdUsuarioLiberacao() {
		return idUsuarioLiberacao;
	}
	public void setIdUsuarioLiberacao(Item idUsuarioLiberacao) {
		this.idUsuarioLiberacao = idUsuarioLiberacao;
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idreserva) {
		this.idReserva = idreserva;
	}
	public Date getRegistro() {
		return registro;
	}
	public void setRegistro(Date registro) {
		this.registro = registro;
	}
	public Date getRetirada() {
		return retirada;
	}
	public void setRetirada(Date retirada) {
		this.retirada = retirada;
	}
	@Override
	public String toString() {
		return "Retirada [id=" + id + ", idUsuarioLiberacao="
				+ idUsuarioLiberacao + ", idReserva=" + idReserva
				+ ", registro=" + registro + ", retirada=" + retirada + "]";
	}
	
	
	
}
