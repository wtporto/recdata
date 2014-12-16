package br.edu.ifpb.recdata.convert;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.edu.ifpb.recdata.bean.GenericBean;
import br.edu.ifpb.recdata.bean.RegiaoAppScopeBean;
import br.edu.ifpb.recdata.entidades.Regiao;

@FacesConverter("br.edu.ifpb.recdata.convert.RegioesConverter")
public class RegioesConverter implements Converter {

	public static String EMPYT = "0";
	
	@Override
	public Regiao getAsObject(FacesContext context, UIComponent component,
			String value) {

		Regiao regiaoSelect = null;

		try {
			
			RegiaoAppScopeBean regiaoAppBean = (RegiaoAppScopeBean) GenericBean
					.getApplicationContextValue("regiaoAppScopeBean");
			
			List<Regiao> regioes = regiaoAppBean.getRegioes();

			int id = Integer.parseInt(value);
			
			for (Regiao regiao : regioes) {
				
				if (regiao.getId() == id) {
					regiaoSelect = regiao;
				}
			}		

		} catch (Throwable ex) {
			
			ResourceBundle bundle = ResourceBundle.getBundle("messages");
			FacesMessage msg = new FacesMessage(
					bundle.getString("erro.GeneralServerErro"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			throw new ConverterException(msg);
		}
		
		return regiaoSelect;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componente,
			Object value) {
		
		String idRegiao = null;
        
		try {
			
			Regiao regiao = (Regiao) value;			
            idRegiao = Integer.toString(regiao.getId());
            
		} catch (NullPointerException e) {
        	
			idRegiao = EMPYT;
			
        } catch(Throwable ex) {
        	
            ResourceBundle bundle = ResourceBundle.getBundle("messages");
            FacesMessage msg = new FacesMessage(bundle.getString("erro.GeneralServerErro"));            
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            
            throw new ConverterException(msg);
        }
		
        return idRegiao;
	}
}
