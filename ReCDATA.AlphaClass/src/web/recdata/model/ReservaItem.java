package web.recdata.model;


public class ReservaItem implements Entidade{

	private int idReserva;
	private int UsuarioId_reserva;
	private int ItemId_reserva;
	private String hora_dataReserva;
	private String hora_Entrega;

	public ReservaItem(int idReserva) {
		setIdReserva(idReserva);

	}

	public ReservaItem(String hora_reserva, String hora_entrega) {
		setHora_dataReserva(hora_entrega);
		setHora_Entrega(hora_entrega);

	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public int getUsuarioId_reserva() {
		return UsuarioId_reserva;
	}

	public void setUsuarioId_reserva(int usuarioId_reserva) {
		UsuarioId_reserva = usuarioId_reserva;
	}

	public int getItemId_reserva() {
		return ItemId_reserva;
	}

	public void setItemId_reserva(int itemId_reserva) {
		ItemId_reserva = itemId_reserva;
	}

	public String getHora_dataReserva() {
		return hora_dataReserva;
	}

	public void setHora_dataReserva(String hora_dataReserva) {
		this.hora_dataReserva = hora_dataReserva;
	}

	public String getHora_Entrega() {
		return hora_Entrega;
	}

	public void setHora_Entrega(String hora_Entrega) {
		this.hora_Entrega = hora_Entrega;
	}

	@Override
	public String toString() {
		return "ReservaItem [idReserva=" + idReserva + ", UsuarioId_reserva="
				+ UsuarioId_reserva + ", ItemId_reserva=" + ItemId_reserva
				+ ", hora_dataReserva=" + hora_dataReserva + ", hora_Entrega="
				+ hora_Entrega + "]";
	}

}
