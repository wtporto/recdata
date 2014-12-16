package br.edu.ifpb.recdata.entidades;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DevolucaoItem implements Entidade {

	private int id;
	private ReservaItem reserva;
	private Usuario UsuarioRecebimento;
	private Usuario UsuarioDevolucao;
	private Date registro;
	private Date devolucao;

	public DevolucaoItem() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuarioRecebimento() {
		return UsuarioRecebimento;
	}

	public void setUsuarioRecebimento(Usuario UsuarioRecebimento) {
		this.UsuarioRecebimento = UsuarioRecebimento;
	}

	public Usuario getUsuarioDevolucao() {
		return UsuarioDevolucao;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	public Date getDevolucao() {
		return devolucao;
	}

	public void setDevolucao(Date devolucao) {
		this.devolucao = devolucao;
	}

	public ReservaItem getReserva() {
		return reserva;
	}

	public void setReserva(ReservaItem reserva) {
		this.reserva = reserva;
	}

	public void setUsuarioDevolucao(Usuario usuarioDevolucao) {
		UsuarioDevolucao = usuarioDevolucao;
	}

	@Override
	public String toString() {
		return "DevolucaoItem [id=" + id + ", reserva=" + reserva
				+ ", UsuarioRecebimento=" + UsuarioRecebimento
				+ ", UsuarioDevolucao=" + UsuarioDevolucao + ", registro="
				+ registro + ", devolucao=" + devolucao + "]";
	}

}
