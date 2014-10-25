package br.edu.ifpb.recdata.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

public class GenericBean {
	
	/**
	 * 
	 * @param expressao
	 * @param obj
	 * @param clazz
	 */
	@SuppressWarnings("unchecked")
	public void setAtributo(String expressao, Object obj, Class clazz) {

		FacesContext current = FacesContext.getCurrentInstance();

		Application app = current.getApplication();

		ExpressionFactory fac = app.getExpressionFactory();

		ValueExpression ve = fac.createValueExpression(current.getELContext(),
				expressao, clazz);
		ve.setValue(current.getELContext(), obj);
	}

	/**
	 * 
	 * @param exp
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object getAtributo(String exp, Class clazz) {

		FacesContext current = FacesContext.getCurrentInstance();

		Application app = current.getApplication();

		ExpressionFactory fac = app.getExpressionFactory();

		ValueExpression ve = fac.createValueExpression(current.getELContext(),
				exp, clazz);
		Object resultado = ve.getValue(current.getELContext());

		return resultado;
	}

	/**
	 * Throws messages in the context.
	 * 
	 * @param key
	 * @param severity
	 */
	public static void setMessage(String clientID, String key,
			Severity severity) {

		FacesMessage message = new FacesMessage(message(key));
		message.setSeverity(severity);

		FacesContext fc = FacesContext.getCurrentInstance();

		fc.addMessage(clientID, message);
	}

	/**
	 * Capture message i18n.
	 * 
	 * @param clientId
	 * @param key
	 * @return
	 */
	public static String message(String key) {
		// Look up the requested message text
		FacesContext fc = FacesContext.getCurrentInstance();

		String text = null;

		try {
			ResourceBundle bundle = ResourceBundle.getBundle(
					"br.edu.ifpb.recdata.i18n.messages", fc.getViewRoot().getLocale());
			text = bundle.getString(key);
		} catch (Exception e) {
			text = "???" + key + "???";
		}

		return text;
	}

	/**
	 * Recuperar informações de outro bean
	 * 
	 * @param nomeDoBean
	 * @return
	 */
	public Object acessarOutroBean(String nomeDoBean) {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getELContext().getELResolver()
				.getValue(context.getELContext(), null, nomeDoBean);

	}

	/**
	 * Verificar se o objeto é nulo ou vazio
	 * 
	 * @param obj
	 * @return
	 */
	public boolean isEmptyOrNull(Object obj) {
		if (obj == null)
			return true;
		if (obj instanceof String) {
			if (((String) obj).trim().equals(""))
				return true;
		}
		return false;
	}

	/**
	 * Recuperar as configura��es do Tomcat
	 * 
	 * @return
	 */
	public String getTomcatHome() {
		
		return System.getProperty("catalina.base");
	}
	
	/**
	 * Recuperar valores armazenados na Sessão do cliente.
	 * 
	 * @param key
	 * @return
	 */
	public static Object getSessionValue(String key) {
		// Recupera cliente da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		
		return session.getAttribute(key);
	}
	
	/**
	 * Recuperar valores armazenados na Sess�o do cliente.
	 * 
	 * @param key
	 * @return
	 */
	public static void setSessionValue(String key, Object value) {
		// Recupera cliente da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		
		session.setAttribute(key, value);
	}
	
	/**
	 * Recuperar valores do contexto de aplicação
	 * @param key
	 * @return
	 */
	public static Object getApplicationContextValue(String key) {
		
		return FacesContext.getCurrentInstance()
				.getExternalContext()
				.getApplicationMap()
				.get(key);
	}
	
	/**
	 * Adicionar um item vazio ao componente de seleção
	 * 
	 * @param types
	 */
	public static List<SelectItem> initSelectOneItem() {
		
		List<SelectItem> types = new ArrayList<SelectItem>();
				
		types.add(new SelectItem(null, GenericBean.message("selectOne")));
		
		return types;
	}
}
