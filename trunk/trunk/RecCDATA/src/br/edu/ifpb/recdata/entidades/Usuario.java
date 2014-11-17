package br.edu.ifpb.recdata.entidades;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario implements Entidade {

	private int id;
	
	private String nome;
	
	private String email;
	
	private String telefone;
	
	private java.util.Date nascimento;
	
	private String sexo;
	
	private String cpf;
	
	private String endereco;
	
	private String login;
	
	private String senha;
	
	private TipoUsuario tipoUsuario;

	public Usuario(int usuarioId) {
		setId(usuarioId);

	}

	public Usuario() {

	}

	public Usuario(String login, String senha, String nome, String email,
			String telefone, String cpf, String endereco, Date idade,
			String sexo, TipoUsuario tipoUsuario) {

		setLogin(login);
		setSenha(senha);
		setNome(nome);
		setEmail(email);
		setTelefone(telefone);
		setCpf(cpf);
		setEndereco(endereco);
		setNascimento(idade);
		setSexo(sexo);
		setTipoUsuario(tipoUsuario);
	}

	public int getId() {
		return id;
	}

	public void setId(int usuarioId) {
		this.id = usuarioId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [usuarioId=" + id + ", nomeUsuario="
				+ nome + ", emailUsuario=" + email
				+ ", telefoneUsuario=" + telefone + ", idadeUsuario="
				+ nascimento + ", sexoUsuario=" + sexo
				+ ", cpfUsuario=" + cpf + ", enderecoUsuario="
				+ endereco + ", loginUsuario=" + login
				+ ", senhaUsuario=" + senha + ", idTipoUsuario="
				+ tipoUsuario.getId() + ", descricao_tipoUsuario="
				+ tipoUsuario.getDescricao() + "]";
	}
}