package br.edu.ifpb.recdata.entidades;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservaItem implements Entidade {

	private int id;
	private Usuario usuario;
	private Item item;
	private Date horaDataInicio;
	private Date horaDataFim;
	private Date dataRegistro;
	
	public ReservaItem() {}
	
	public ReservaItem(int id, Usuario usuario, Item item,
			Date horaDataInicio, Date horaDataFim) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.item = item;
		this.horaDataInicio = horaDataInicio;
		this.horaDataFim = horaDataFim;
	}

	@Override
	public String toString() {
		return "ReservaItem [idReserva=" + id + ", usuario="
				+ usuario + ", item=" + item
				+ ", horaDataInicio=" + horaDataInicio + ", horaDataFim="
				+ horaDataFim + "]";
	}

	public int getIdReserva() {
		return id;
	}

	public void setIdReserva(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

}
