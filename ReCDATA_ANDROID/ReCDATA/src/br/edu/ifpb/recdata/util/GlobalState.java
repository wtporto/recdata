package br.edu.ifpb.recdata.util;

import br.edu.ifpb.recdata.entity.Usuario;
import android.app.Application;

public class GlobalState extends Application {
	
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}			 
}