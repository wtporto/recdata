package br.edu.ifpb.recdata.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.recdata.entidades.Usuario;
import br.edu.ifpb.recdata.service.ProviderServiceFactory;
import br.edu.ifpb.recdata.service.ReCDATAService;

@ManagedBean(eager = true)
@SessionScoped
public class BuscarUsuarioBean implements Serializable {

	private static final long serialVersionUID = -6300467749382296451L;

	private List<Usuario> usuarios;

	public BuscarUsuarioBean() {
	}

	private List<Usuario> populateUsuarios(String nome) {

		List<Usuario> usuarioServico = new ArrayList<Usuario>();
		
		if (usuarios == null || (usuarios != null && usuarios.isEmpty())) {
			
			// Recuperar das categorias do servidor os valores.			
			ReCDATAService service = ProviderServiceFactory
					.createServiceClient(ReCDATAService.class);
			
			// Atribui a lista de categorias.
			Usuario usuario = new Usuario();
			usuario.setNomeUsuario(nome);
			usuarioServico = service.consultarUsuarios(usuario);
		}
		
		return usuarioServico;
	}

	public List<Usuario> getUsuariosByNome(String nome) {
		this.usuarios = populateUsuarios(nome);
		return usuarios;
	}

	public List<Usuario> getUsuarios() {
		
		if (this.usuarios == null) {
			this.usuarios = new ArrayList<Usuario>();
		}
		
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}