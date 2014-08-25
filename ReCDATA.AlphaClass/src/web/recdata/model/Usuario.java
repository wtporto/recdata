package web.recdata.model;



public abstract class Usuario implements Entidade {

	private int usuarioId;
	private String nomeUsuario;
	private String emailUsuario;
	private String telefoneUsuario;
	private int idadeUsuario;
	private String sexoUsuario;
	private String cpfUsuario;
	private String enderecoUsuario;
	private String loginUsuario;
	private String senhaUsuario;
	
	
	public Usuario(int usuarioId){
		setUsuarioId(usuarioId);

	}
	public Usuario(){
		
	}
	
	public Usuario(String nome, String email, 
			       String telefone, int idade, String sexo, String cpf, String endereco, 
			       String login , String senha) {
		
		setNomeUsuario(nome);
		setEmailUsuario(email);
		setTelefoneUsuario(telefone);
		setIdadeUsuario(idade);
		setSexoUsuario(sexo);
		setCpfUsuario(cpf);
		setEnderecoUsuario(endereco);
		setLoginUsuario(login);
		setSenhaUsuario(senha);
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
	public int getIdadeUsuario() {
		return idadeUsuario;
	}
	public void setIdadeUsuario(int idadeUsuario) {
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

	@Override
	public String toString() {
		return "Usuario [usuarioId=" + usuarioId + ", nomeUsuario="
				+ nomeUsuario + ", emailUsuario=" + emailUsuario
				+ ", telefoneUsuario=" + telefoneUsuario + ", idadeUsuario="
				+ idadeUsuario + ", sexoUsuario=" + sexoUsuario
				+ ", cpfUsuario=" + cpfUsuario + ", enderecoUsuario="
				+ enderecoUsuario + ", loginUsuario=" + loginUsuario
				+ ", senhaUsuario=" + senhaUsuario + "]";
	}

	
	
	
	
	
}
