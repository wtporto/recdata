package br.edu.ifpb.recdata.entidades;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservaItem implements Entidade {

	private int id;
	
	private Usuario controladorRecurso;

	private Usuario usuarioReserva;
	
	private Item item;
	
	private String observacao;
	
	private Date horaDataInicio;
	
	private Date horaDataFim;
	
	private Date dataRegistro;
	
	public ReservaItem() {}
	
	public ReservaItem(int id, Usuario usuarioReserva, Item item,String observacao,
			Date horaDataInicio, Date horaDataFim) {
		super();
		this.id = id;
		this.usuarioReserva = usuarioReserva;
		this.item = item;
		this.horaDataInicio = horaDataInicio;
		this.horaDataFim = horaDataFim;
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "ReservaItem [idReserva=" + id 
				+ ", controladorRecurso" + controladorRecurso 
				+ ", usuarioResponsavel=" + usuarioReserva 
				+ ", item=" + item 
				+ " observacao=" + observacao
				+ ", horaDataInicio=" + horaDataInicio 
				+ ", horaDataFim=" + horaDataFim + "]";
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public Usuario getControladorRecurso() {
		return controladorRecurso;
	}

	public void setControladorRecurso(Usuario controladorRecurso) {
		this.controladorRecurso = controladorRecurso;
	}

	@XmlElement
	public Usuario getUsuarioReserva() {
		return usuarioReserva;
	}

	public void setUsuarioReserva(Usuario usuarioReserva) {
		this.usuarioReserva = usuarioReserva;
	}

	@XmlElement
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@XmlElement
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@XmlElement
	public Date getHoraDataInicio() {
		return horaDataInicio;
	}

	public void setHoraDataInicio(Date horaDataInicio) {
		this.horaDataInicio = horaDataInicio;
	}

	@XmlElement
	public Date getHoraDataFim() {
		return horaDataFim;
	}

	public void setHoraDataFim(Date horaDataFim) {
		this.horaDataFim = horaDataFim;
	}

	@XmlElement
	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
}
