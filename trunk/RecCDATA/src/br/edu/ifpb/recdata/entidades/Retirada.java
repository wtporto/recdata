package br.edu.ifpb.recdata.entidades;

import java.util.Date;

public class Retirada {

	private int id;
	
	private Usuario usuarioLiberacao;
	
	private ReservaItem reserva;
	
	private Date retirada;
	
	private Date registro;	
	
	@Override
	public String toString() {
		return "Retirada [id=" + id + ", usuarioLiberacao="
				+ usuarioLiberacao.getId() + ", reserva=" + reserva.getId()
				+ ", registro=" + registro + ", retirada=" + retirada + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuarioLiberacao() {
		return usuarioLiberacao;
	}

	public void setUsuarioLiberacao(Usuario usuarioLiberacao) {
		this.usuarioLiberacao = usuarioLiberacao;
	}

	public ReservaItem getReserva() {
		return reserva;
	}

	public void setReserva(ReservaItem reserva) {
		this.reserva = reserva;
	}

	public Date getRetirada() {
		return retirada;
	}

	public void setRetirada(Date retirada) {
		this.retirada = retirada;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}	
}
