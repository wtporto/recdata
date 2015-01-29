package br.edu.ifpb.recdata.convert;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.edu.ifpb.recdata.bean.CategoriaAppScopeBean;
import br.edu.ifpb.recdata.bean.GenericBean;
import br.edu.ifpb.recdata.entidades.Categoria;

@FacesConverter("br.edu.ifpb.recdata.convert.CategoriasConverter")
public class CategoriasConverter implements Converter {

	public static String EMPYT = "0";
	
	@Override
	public Categoria getAsObject(FacesContext context, UIComponent component,
			String value) {

		Categoria categoriaSelect = null;

		try {
			
			CategoriaAppScopeBean categoriaAppBean = (CategoriaAppScopeBean) GenericBean
					.getApplicationContextValue("categoriaAppScopeBean");
			
			List<Categoria> categorias = categoriaAppBean.getCategorias();

			int id = Integer.parseInt(value);
			
			for (Categoria categoria : categorias) {
				
				if (categoria.getId() == id) {
					categoriaSelect = categoria;
				}
			}		

		} catch (Throwable ex) {
			
			ResourceBundle bundle = ResourceBundle.getBundle("messages");
			FacesMessage msg = new FacesMessage(
					bundle.getString("erro.GeneralServerErro"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			throw new ConverterException(msg);
		}
		
		return categoriaSelect;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componente,
			Object value) {
		
		String idCategorias = null;
        
		try {
			
			Categoria categoria = (Categoria) value;			
            idCategorias = Integer.toString(categoria.getId());
            
		} catch (NullPointerException e) {
        	
			idCategorias = EMPYT;
			
        } catch(Throwable ex) {
        	
            ResourceBundle bundle = ResourceBundle.getBundle("messages");
            FacesMessage msg = new FacesMessage(bundle.getString("erro.GeneralServerErro"));            
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            
            throw new ConverterException(msg);
        }
		
        return idCategorias;
	}
}
