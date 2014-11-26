package br.edu.ifpb.recdata.entity;

import java.io.Serializable;
import java.util.Date;

import br.edu.ifpb.R;


public class ReservaItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Usuario usuario;
	private Item item;
	private String observacao;
	private String horaDataInicio;
	private String horaDataFim;
	private Date dataRegistro;
	
	public ReservaItem() {}
	
	public ReservaItem(int id, Usuario usuario, Item item,String observacao,
			String horaDataInicio, String horaDataFim) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.item = item;
		this.horaDataInicio = horaDataInicio;
		this.horaDataFim = horaDataFim;
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "ReservaItem [idReserva=" + id + ", usuario="
				+ usuario + ", item=" + item +" observacao="+observacao
				+ ", horaDataInicio=" + horaDataInicio + ", horaDataFim="
				+ horaDataFim + "]";
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

	public String getHoraDataInicio() {
		return horaDataInicio;
	}

	public void setHoraDataInicio(String horaDataInicio) {
		this.horaDataInicio = horaDataInicio;
	}

	public String getHoraDataFim() {
		return horaDataFim;
	}

	public void setHoraDataFim(String horaDataFim) {
		this.horaDataFim = horaDataFim;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public int getImagem(int idcategoria) {
		switch (idcategoria) {
		case 1:
			return (R.drawable.icon_reserva);
		default:
			return (R.drawable.icon_errodefault);
		}
	}
	
}
