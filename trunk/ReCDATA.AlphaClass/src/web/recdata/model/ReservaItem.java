package web.recdata.model;

import java.util.Date;

public class ReservaItem implements Entidade {

	private int idReserva;
	private int usuarioIdReserva;
	private int itemIdReserva;
	private Date horaDataReserva;
	private Date horaEntrega;

	public ReservaItem(int idReserva) {
		setIdReserva(idReserva);

	}

	public ReservaItem(Date horaReserva, Date horaEntrega) {
		setHoraDataReserva(horaReserva);
		setHoraEntrega(horaEntrega);

	}

	public ReservaItem() {
		// TODO Auto-generated constructor stub
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

	public Date getHoraDataReserva() {
		return horaDataReserva;
	}

	public void setHoraDataReserva(Date horaDataReserva) {
		this.horaDataReserva = horaDataReserva;
	}

	public Date getHoraEntrega() {
		return horaEntrega;
	}

	public void setHoraEntrega(Date horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

	@Override
	public String toString() {
		return "ReservaItem [idReserva=" + idReserva + ", UsuarioIdReserva="
				+ usuarioIdReserva + ", ItemIdReserva=" + itemIdReserva
				+ ", horaDataReserva=" + horaDataReserva + ", horaEntrega="
				+ horaEntrega + "]";
	}

}
