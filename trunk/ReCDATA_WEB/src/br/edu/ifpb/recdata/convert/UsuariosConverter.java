package br.edu.ifpb.recdata.convert;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.edu.ifpb.recdata.bean.BuscarUsuarioBean;
import br.edu.ifpb.recdata.bean.GenericBean;
import br.edu.ifpb.recdata.entidades.Usuario;

@FacesConverter("br.edu.ifpb.recdata.convert.UsuariosConverter")
public class UsuariosConverter implements Converter {

	public static String EMPYT = "0";
	
	@Override
	public Usuario getAsObject(FacesContext context, UIComponent component,
			String value) {

		Usuario usuarioSelect = null;

		try {
			
			BuscarUsuarioBean buscarUsuarioBean = (BuscarUsuarioBean) GenericBean
					.getSessionValue("buscarUsuarioBean");
			
			List<Usuario> usuarios = buscarUsuarioBean.getUsuarios();

			int id = Integer.parseInt(value);
			
			for (Usuario usuario : usuarios) {
				
				if (usuario.getId() == id) {
					usuarioSelect = usuario;
				}
			}		

		} catch (Throwable ex) {
			
			ResourceBundle bundle = ResourceBundle.getBundle("messages");
			FacesMessage msg = new FacesMessage(
					bundle.getString("erro.GeneralServerErro"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			throw new ConverterException(msg);
		}
		
		return usuarioSelect;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componente,
			Object value) {
		
		String idUsuario = null;
        
		try {
			
			Usuario state = (Usuario) value;			
            idUsuario = Integer.toString(state.getId());
            
		} catch (NullPointerException e) {
        	
			idUsuario = EMPYT;
			
        } catch(Throwable ex) {
        	
            ResourceBundle bundle = ResourceBundle.getBundle("messages");
            FacesMessage msg = new FacesMessage(bundle.getString("erro.GeneralServerErro"));            
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            
            throw new ConverterException(msg);
        }
		
        return idUsuario;
	}
}
