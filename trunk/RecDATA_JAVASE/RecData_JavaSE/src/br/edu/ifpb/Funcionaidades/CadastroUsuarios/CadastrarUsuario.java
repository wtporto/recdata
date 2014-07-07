package br.edu.ifpb.Funcionaidades.CadastroUsuarios;

public interface CadastrarUsuario {

	public String entradaDadoUsuario();// entrada básica de dados 
	public String enviaDadosValidacaoUsuario();// envia os dados via Socket ou RMI ou outros, para o servidor validar
	public String senhaCriptografadaUsuario();// verifica se a algum item   no banco com dados parecidos;
	public boolean confirmaCadastroUsuario();// retorna um boolen true para a confirma do cadastro com sucesso, e  false caso contrario
  

}
