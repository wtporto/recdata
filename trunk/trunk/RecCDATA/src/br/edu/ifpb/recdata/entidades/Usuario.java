package br.edu.ifpb.recdata.entidades;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario implements Entidade {

	private int usuarioId;
	
	private String nomeUsuario;
	
	private String emailUsuario;
	
	private String telefoneUsuario;
	
	private java.util.Date idadeUsuario;
	
	private String sexoUsuario;
	
	private String cpfUsuario;
	
	private String enderecoUsuario;
	
	private String loginUsuario;
	
	private String senhaUsuario;

	private int idTipoUsuario;
	
	private String descricao_tipoUsuario;

	public Usuario(int usuarioId) {
		setUsuarioId(usuarioId);

	}

	public Usuario() {

	}

	public Usuario(String login, String senha, String nome, String email,
			String telefone, String cpf, String endereco, Date idade,
			String sexo, int idTipoUsuario, String descricao_tipoUsuario) {

		setLoginUsuario(login);
		setSenhaUsuario(senha);
		setNomeUsuario(nome);
		setEmailUsuario(email);
		setTelefoneUsuario(telefone);
		setCpfUsuario(cpf);
		setEnderecoUsuario(endereco);
		setIdadeUsuario(idade);
		setSexoUsuario(sexo);
		setIdTipoUsuario(idTipoUsuario);
		setDescricao_tipoUsuario(descricao_tipoUsuario);

	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getTelefoneUsuario() {
		return telefoneUsuario;
	}

	public void setTelefoneUsuario(String telefoneUsuario) {
		this.telefoneUsuario = telefoneUsuario;
	}

	public java.util.Date getIdadeUsuario() {
		return idadeUsuario;
	}

	public void setIdadeUsuario(Date idadeUsuario) {
		this.idadeUsuario = idadeUsuario;
	}

	public String getSexoUsuario() {
		return sexoUsuario;
	}

	public void setSexoUsuario(String sexo) {
		this.sexoUsuario = sexo;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public String getEnderecoUsuario() {
		return enderecoUsuario;
	}

	public void setEnderecoUsuario(String enderecoUsuario) {
		this.enderecoUsuario = enderecoUsuario;
	}

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getDescricao_tipoUsuario() {
		return descricao_tipoUsuario;
	}

	public void setDescricao_tipoUsuario(String descricao_tipoUsuario) {
		this.descricao_tipoUsuario = descricao_tipoUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [usuarioId=" + usuarioId + ", nomeUsuario="
				+ nomeUsuario + ", emailUsuario=" + emailUsuario
				+ ", telefoneUsuario=" + telefoneUsuario + ", idadeUsuario="
				+ idadeUsuario + ", sexoUsuario=" + sexoUsuario
				+ ", cpfUsuario=" + cpfUsuario + ", enderecoUsuario="
				+ enderecoUsuario + ", loginUsuario=" + loginUsuario
				+ ", senhaUsuario=" + senhaUsuario + ", idTipoUsuario="
				+ idTipoUsuario + ", descricao_tipoUsuario="
				+ descricao_tipoUsuario + "]";
	}

}