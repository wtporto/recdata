package br.edu.ifpb.recdata.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.recdata.entidades.Retirada;
import br.edu.ifpb.recdata.entidades.Usuario;

@ManagedBean(name="devolverBean")
@ViewScoped
public class DevolverBean {
	
	private List<Retirada> retiradas;
	
	// Usuário selecionados.
	private List<Usuario> usuariosSelecionados = new ArrayList<Usuario>();
	
	// Todos os usuário da busca no serviço.
	private List<Usuario> usuariosConsulta = new ArrayList<Usuario>();
	
	// Data e hora inicial da reserva.
	private Date dataInicio;
	
	private Date horaInicio;
	
	/**
	 * Listar as retiradas de acordo com o nome do usuário.
	 */
	public String consultarRetiradas() {
		
		String redirect = null;
		
		return redirect;
	}
	
	/**
	 * Devolver o item retirado.
	 */
	public String devolverItem(Retirada retirada) {
		
		String redirect = null;
		
		return redirect;		
	}
	
	public List<Usuario> completeUsuarios(String query) {
    	
        BuscarUsuarioBean buscarUsuarioBean = new BuscarUsuarioBean();
        
        // Guardar usuário para consultar as retiradas.
        this.usuariosConsulta = buscarUsuarioBean.getUsuariosByNome(query); 
        
        return usuariosConsulta;
    }

	public List<Usuario> getUsuariosConsulta() {
		return usuariosConsulta;
	}

	public void setUsuariosConsulta(List<Usuario> usuariosConsulta) {
		this.usuariosConsulta = usuariosConsulta;
	}

	public List<Usuario> getUsuariosSelecionados() {
		return usuariosSelecionados;
	}

	public void setUsuariosSelecionados(List<Usuario> usuariosSelecionados) {
		this.usuariosSelecionados = usuariosSelecionados;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public List<Retirada> getRetiradas() {
		return retiradas;
	}

	public void setRetiradas(List<Retirada> retiradas) {
		this.retiradas = retiradas;
	}
}
