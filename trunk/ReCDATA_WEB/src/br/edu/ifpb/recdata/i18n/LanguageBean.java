package br.edu.ifpb.recdata.i18n;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
 
@ManagedBean(name="language")
@SessionScoped
public class LanguageBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	static public final Locale BRAZIL = new Locale("pt");
	
	private String clientLocale;
	
	private static Map<String,Object> countries;
	
	static{
		
		countries = new LinkedHashMap<String,Object>(); //label, value
		countries.put("Português", BRAZIL);
	}

	public LanguageBean() {
		
		Locale locale = FacesContext.getCurrentInstance()
				.getViewRoot().getLocale();
		
		// Select local language.
		setClientLocale(locale.getLanguage());
	}
	
	public Map<String, Object> getCountriesInMap() {
		
		return countries;
	}

	public void countryLocaleCodeChanged(ValueChangeEvent e){
		
		String newLocaleValue = e.getNewValue().toString();
		
		//loop a map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
        
        	if(entry.getValue().toString().equals(newLocaleValue)){
        		
        		FacesContext.getCurrentInstance()
        			.getViewRoot().setLocale((Locale)entry.getValue()); 
        		
        		clientLocale = newLocaleValue;
        	}
        }
	}

	/**
	 * @return the clientLocale
	 */
	public String getClientLocale() {
		return clientLocale;
	}

	/**
	 * @param clientLocale the clientLocale to set
	 */
	public void setClientLocale(String clientLocale) {
		this.clientLocale = clientLocale;
	}
}