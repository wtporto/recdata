package br.edu.ifpb.recdata.entidades;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Retirada {

	private int id;
	
	private Usuario usuarioLiberacao;
	
	private ReservaItem reserva;
	
	private Date dataHoraRetirada;
	
	private Date registro;	
	
	@Override
	public String toString() {
		return "Retirada [id=" + id + ", usuarioLiberacao="
				+ usuarioLiberacao.getId() + ", reserva=" + reserva.getId()
				+ ", registro=" + registro + ", Data/hora da retirada=" + dataHoraRetirada + "]";
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public Usuario getUsuarioLiberacao() {
		return usuarioLiberacao;
	}

	public void setUsuarioLiberacao(Usuario usuarioLiberacao) {
		this.usuarioLiberacao = usuarioLiberacao;
	}

	@XmlElement
	public ReservaItem getReserva() {
		return reserva;
	}

	public void setReserva(ReservaItem reserva) {
		this.reserva = reserva;
	}

	@XmlElement
	public Date getDataHoraRetirada() {
		return dataHoraRetirada;
	}

	public void setDataHoraRetirada(Date retirada) {
		this.dataHoraRetirada = retirada;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}	
}
