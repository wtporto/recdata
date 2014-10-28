package br.edu.ifpb.recdata.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.recdata.entidades.Item;
import br.edu.ifpb.recdata.entidades.ReservaItem;
import br.edu.ifpb.recdata.entidades.Usuario;

@ManagedBean(name="reservarItemBean")
@SessionScoped
public class ReservarItemBean extends ReservaItem{
	
	// Usuário selecionados.
	private List<Usuario> usuariosSelecionados = new ArrayList<Usuario>();
	
	// Todos os usuário da busca no serviço.
	private List<Usuario> usuariosConsulta = new ArrayList<Usuario>();
	
	private Date dataInicio;
	
	private Date horaInicio;
	
	private Date dataFim;
	
	private Date horaFim;
	
	private boolean retirarItem;
	
	public ReservarItemBean() {}
	
	public ReservarItemBean(Item item) {
		super.setItem(item);
	}
	
	public void redirecionarReservaItem() {
		GenericBean.sendRedirect(PathRedirect.reservarItem);
	}    

    public List<Usuario> completeUsuarios(String query) {
    	
        BuscarUsuarioBean buscarUsuarioBean = new BuscarUsuarioBean();
        this.usuariosConsulta = buscarUsuarioBean.getUsuariosByNome(query);
        System.out.println(this.usuariosConsulta);
        
        GenericBean.setSessionValue("buscarUsuarioBean", buscarUsuarioBean);       
        
        return usuariosConsulta;
    }

	public List<Usuario> getUsuariosSelecionados() {
		return usuariosSelecionados;
	}

	public void setUsuariosSelecionados(List<Usuario> usuariosSelecionados) {
		this.usuariosSelecionados = usuariosSelecionados;
	}

	public List<Usuario> getUsuariosConsulta() {
		return usuariosConsulta;
	}

	public void setUsuariosConsulta(List<Usuario> usuariosConsulta) {
		this.usuariosConsulta = usuariosConsulta;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public boolean isRetirarItem() {
		return retirarItem;
	}

	public void setRetirarItem(boolean retirarItem) {
		this.retirarItem = retirarItem;
	}
}
