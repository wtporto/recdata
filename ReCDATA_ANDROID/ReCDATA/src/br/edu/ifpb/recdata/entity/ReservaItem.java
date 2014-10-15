package br.edu.ifpb.recdata.entity;


import java.util.Date;


public class ReservaItem  {

	private int idReserva;
	private int usuarioIdReserva;
	private int itemIdReserva;
	private Date horaDataInicioReserva;
	private Date horaDataFimReserva;

	public ReservaItem(int idReserva) {
		setIdReserva(idReserva);

	}

	public ReservaItem(Date horaDataInicioReserva, Date horaDataFimRererva,int usuarioId, int itemId) {
		setItemIdReserva(itemId);
		setUsuarioIdReserva(usuarioId);
		setHoraDataInicioReserva(horaDataInicioReserva);
		setHoraDataFimReserva(horaDataFimRererva);		
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

	public Date getHoraDataInicioReserva() {
		return horaDataInicioReserva;
	}

	public void setHoraDataInicioReserva(Date horaDataInicioReserva) {
		this.horaDataInicioReserva = horaDataInicioReserva;
	}

	public Date getHoraDataFimReserva() {
		return horaDataFimReserva;
	}

	public void setHoraDataFimReserva(Date horaDataFimReserva) {
		this.horaDataFimReserva = horaDataFimReserva;
	}	
}
