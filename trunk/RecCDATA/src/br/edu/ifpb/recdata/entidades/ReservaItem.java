package br.edu.ifpb.recdata.entidades;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservaItem implements Entidade {

	private int idReserva;
	private int usuarioIdReserva;
	private int itemIdReserva;
	private Date horaDataInicio;
	private Date horaDataFim;
	
	public ReservaItem(int idReserva, int usuarioIdReserva, int itemIdReserva,
			Date horaDataInicio, Date horaDataFim) {
		super();
		this.idReserva = idReserva;
		this.usuarioIdReserva = usuarioIdReserva;
		this.itemIdReserva = itemIdReserva;
		this.horaDataInicio = horaDataInicio;
		this.horaDataFim = horaDataFim;
	}

	public ReservaItem() {
		//null
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public int getUsuarioIdReserva() {
		return usuarioIdReserva;
	}

	public void setUsuarioIdReserva(int usuarioIdReserva) {
		this.usuarioIdReserva = usuarioIdReserva;
	}

	public int getItemIdReserva() {
		return itemIdReserva;
	}

	public void setItemIdReserva(int itemIdReserva) {
		this.itemIdReserva = itemIdReserva;
	}

	public Date getHoraDataInicio() {
		return horaDataInicio;
	}

	public void setHoraDataInicio(Date horaDataInicio) {
		this.horaDataInicio = horaDataInicio;
	}

	public Date getHoraDataFim() {
		return horaDataFim;
	}

	public void setHoraDataFim(Date horaDataFim) {
		this.horaDataFim = horaDataFim;
	}

	@Override
	public String toString() {
		return "ReservaItem [idReserva=" + idReserva + ", usuarioIdReserva="
				+ usuarioIdReserva + ", itemIdReserva=" + itemIdReserva
				+ ", horaDataInicio=" + horaDataInicio + ", horaDataFim="
				+ horaDataFim + "]";
	}

}
