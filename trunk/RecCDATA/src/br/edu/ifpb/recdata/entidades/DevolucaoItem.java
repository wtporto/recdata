package br.edu.ifpb.recdata.entidades;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DevolucaoItem implements Entidade {

	private int id;
	private int idUsuarioRecebimento;
	private int idUsuarioDevolucao;
	private int idReserva;
	private Date horaDataDevolucao;
	private Date registro;
	private Date devolucao;
	
	
	public DevolucaoItem() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public Date getHoraDataDevolucao() {
		return horaDataDevolucao;
	}

	public void setHoraDataDevolucao(Date horaDataDevolucao) {
		this.horaDataDevolucao = horaDataDevolucao;
	}

	
	public int getIdUsuarioRecebimento() {
		return idUsuarioRecebimento;
	}

	public void setIdUsuarioRecebimento(int idUsuarioRecebimento) {
		this.idUsuarioRecebimento = idUsuarioRecebimento;
	}

	public int getIdUsuarioDevolucao() {
		return idUsuarioDevolucao;
	}

	public void setIdUsuarioDevolucao(int idUsuarioDeevolucao) {
		this.idUsuarioDevolucao = idUsuarioDeevolucao;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	public Date getDevolucao() {
		return devolucao;
	}

	public void setDevolucao(Date devolucao) {
		this.devolucao = devolucao;
	}

	

	@Override
	public String toString() {
		return "DevolucaoItem [id=" + id +  ", idUsuarioRecebimento=" + idUsuarioRecebimento
				+ ", idUsuarioDeevolucao=" + idUsuarioDevolucao + ", idReserva="
				+ idReserva + ", horaDataDevolucao=" + horaDataDevolucao
				+ ", registro=" + registro + ", devolucao=" + devolucao + "]";
	}

	

}
