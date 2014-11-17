package br.edu.ifpb.recdata.entidades;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DevolucaoItem implements Entidade {

	private int id;
	private int idUsuario;
	private int idItem;
	private Date horaDataDevolucao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int Idusuario) {
		this.idUsuario = Idusuario;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setItemIdDevolucao(int idItem) {
		this.idItem = idItem;
	}

	public Date getHoraDataDevolucao() {
		return horaDataDevolucao;
	}

	public void setHoraDataDevolucao(Date horaDataDevolucao) {
		this.horaDataDevolucao = horaDataDevolucao;
	}

	@Override
	public String toString() {
		return "DevolucaoItem [id=" + id
				+ ", usuarioId=" + idUsuario
				+ ", itemId=" + idItem
				+ ", horaDataDevolucao=" + horaDataDevolucao + "]";
	}

}
