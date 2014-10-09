package web.recdata.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservaItem implements Entidade {

	private int idReserva;
	private int usuarioIdReserva;
	private int itemIdReserva;
	private Date horaDataReserva;

	public ReservaItem(int idReserva) {
		setIdReserva(idReserva);

	}

	public ReservaItem(Date horaReserva) {
		setHoraDataReserva(horaReserva);
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

	@Override
	public String toString() {
		return "ReservaItem [idReserva=" + idReserva + ", UsuarioIdReserva="
				+ usuarioIdReserva + ", ItemIdReserva=" + itemIdReserva
				+ ", horaDataReserva=" + horaDataReserva + "]";
	}

}
