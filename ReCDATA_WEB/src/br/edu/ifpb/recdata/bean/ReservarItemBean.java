package br.edu.ifpb.recdata.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.edu.ifpb.recdata.entidades.Item;
import br.edu.ifpb.recdata.entidades.ReservaItem;
import br.edu.ifpb.recdata.entidades.Usuario;
import br.edu.ifpb.recdata.service.ProviderServiceFactory;
import br.edu.ifpb.recdata.service.ReCDATAService;

@ManagedBean(name="reservarItemBean")
@SessionScoped
public class ReservarItemBean extends ReservaItem{
	
	private String itemCategoriaDescricaoRegiao;
	
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
	
	public String reservarItem() {      
        
		String navegacao = null;
		
        Date dataHoraInicio = getDataHora(dataInicio, horaInicio);
        Date dataHoraFim = getDataHora(dataFim, horaFim);
        
        ReservaItem reservaItem = getReservaItem(dataHoraInicio, dataHoraFim);
        
        ReCDATAService service = ProviderServiceFactory
				.createServiceClient(ReCDATAService.class);
        
        Response response = service.cadastrarReservaItem(reservaItem);
		int statusCode = response.getStatus();
		
		if (statusCode == Status.CREATED.getStatusCode()) {
			//Exibir mensagem de sucesso.
			GenericBean.setMessage(null, "info.sucessoReservaItem",
					FacesMessage.SEVERITY_INFO);
			// Limpar sessão.
			resetReservarItem();
		} else {
			//Exibir mensagem de erro.
			GenericBean.setMessage(null, "erro.problemaReservaItem",
					FacesMessage.SEVERITY_ERROR);
		}       
        
		return navegacao;		
	}
	
	public void mudarItem(){
		
		resetReservarItem();
		
		GenericBean.sendRedirect(PathRedirect.LISTARITEM);
	}
	
	private void resetReservarItem() {
		GenericBean.resetSessionScopedBean("reservarItemBean");		
	}

	private ReservaItem getReservaItem(Date dataHoraInicio, Date dataHoraFim) {
		ReservaItem reservaItem = new ReservaItem();
		reservaItem.setUsuario(usuariosSelecionados.get(0));
		reservaItem.setItem(super.getItem());
		reservaItem.setHoraDataInicio(dataHoraInicio);
		reservaItem.setHoraDataFim(dataHoraFim);
		reservaItem.setObservacao(super.getObservacao());
		
		return reservaItem;
	}

	private Date getDataHora(Date data, Date hora){
		        
        Calendar horaCalendar = Calendar.getInstance();
        horaCalendar.setTime(hora);
        
        Calendar dataHoraCalendar = Calendar.getInstance();
        dataHoraCalendar.setTime(data);
        dataHoraCalendar.add(Calendar.HOUR_OF_DAY, horaCalendar.get(Calendar.HOUR_OF_DAY));
        dataHoraCalendar.add(Calendar.MINUTE, horaCalendar.get(Calendar.MINUTE));
        dataHoraCalendar.add(Calendar.SECOND, horaCalendar.get(Calendar.SECOND));       
        
		return dataHoraCalendar.getTime();
	}
	
	public void redirecionarReservaItem() {
		
		GenericBean.sendRedirect(PathRedirect.RESERVARITEM);
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

	public String getItemCategoriaDescricaoRegiao() {
		
		Item item = super.getItem();
		
		this.setItemCategoriaDescricaoRegiao(item.getCategoria().getDescricao() 
				+ " " + item.getDescricao() 
				+ " - " + item.getRegiao().getNome());
		
		return this.itemCategoriaDescricaoRegiao;
	}

	public void setItemCategoriaDescricaoRegiao(String itemCategoriaDescricaoRegiao) {
		this.itemCategoriaDescricaoRegiao = itemCategoriaDescricaoRegiao;
	}
}
