package br.edu.ifpb.recdata.entidades;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DevolucaoItem implements Entidade {

	private int idDevolucao;
	private int usuarioIdDevolucao;
	private int itemIdDevolucao;
	private Date horaDataDevolucao;

	public int getIdDevolucao() {
		return idDevolucao;
	}

	public void setIdDevolucao(int idDevolucao) {
		this.idDevolucao = idDevolucao;
	}

	public int getUsuarioIdDevolucao() {
		return usuarioIdDevolucao;
	}

	public void setUsuarioIdDevolucao(int usuarioIdDevolucao) {
		this.usuarioIdDevolucao = usuarioIdDevolucao;
	}

	public int getItemIdDevolucao() {
		return itemIdDevolucao;
	}

	public void setItemIdDevolucao(int itemIdDevolucao) {
		this.itemIdDevolucao = itemIdDevolucao;
	}

	public Date getHoraDataDevolucao() {
		return horaDataDevolucao;
	}

	public void setHoraDataDevolucao(Date horaDataDevolucao) {
		this.horaDataDevolucao = horaDataDevolucao;
	}

	@Override
	public String toString() {
		return "DevolucaoItem [idDevolucao=" + idDevolucao
				+ ", usuarioIdDevolucao=" + usuarioIdDevolucao
				+ ", itemIdDevolucao=" + itemIdDevolucao
				+ ", horaDataDevolucao=" + horaDataDevolucao + "]";
	}

}
