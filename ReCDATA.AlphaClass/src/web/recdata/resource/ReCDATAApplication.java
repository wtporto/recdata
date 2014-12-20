package web.recdata.resource;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class ReCDATAApplication extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public ReCDATAApplication() {
		this.singletons.add(new CategoriaResource());
		this.singletons.add(new DevolucaoResource());
		this.singletons.add(new ItemResource());
		this.singletons.add(new ReservaResource());
		this.singletons.add(new UsuarioResource());		
		this.singletons.add(new RegiaoResource());
		this.singletons.add(new RetiradaResource());
		this.singletons.add(new VerificaServidor());
	}

	public Set<Class<?>> getClasses() {
		return this.empty;
	}

	public Set<Object> getSingletons() {
		return this.singletons;
	}
}