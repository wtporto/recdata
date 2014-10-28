package br.edu.ifpb.recdata.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
	
	public String reservarItem() {
		
		System.out.println("Usuário: " + usuariosSelecionados);
        System.out.println("Item: " + getItem());
        System.out.println("Data" + dataInicio + " Hora:" + horaInicio);
        System.out.println("Data" + dataFim + " Hora:" + horaFim);       
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataInicio);

        Integer dia = cal.get(Calendar.DAY_OF_MONTH);
        Integer mes = cal.get(Calendar.MONTH);
        Integer ano = cal.get(Calendar.YEAR);        
        
        long dateTimeMilis = dataInicio.getTime() + horaInicio.getTime();
        Date dataHoraInicio = new Date(dateTimeMilis);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateTimeMilis);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
        String data = formatter.format(calendar.getTime());
        
        horaInicio.getMinutes();
		return null;		
	}
	
	private Date getDataHora(Date data, Date hora){
		        
        Calendar horaCalendar = Calendar.getInstance();
        horaCalendar.setTime(hora);
        
        Calendar dataHoraCalendar = Calendar.getInstance();
        dataHoraCalendar.setTime(data);
        dataHoraCalendar.add(Calendar.HOUR, horaCalendar.get(Calendar.HOUR));
        dataHoraCalendar.add(Calendar.MINUTE, horaCalendar.get(Calendar.MINUTE));
        dataHoraCalendar.add(Calendar.SECOND, horaCalendar.get(Calendar.SECOND));       
        
		return dataHoraCalendar.getTime();
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
