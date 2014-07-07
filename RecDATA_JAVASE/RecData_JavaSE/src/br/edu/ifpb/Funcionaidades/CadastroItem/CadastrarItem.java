package br.edu.ifpb.Funcionaidades.CadastroItem;

public interface CadastrarItem {
	
	
//ver se e melhor criar uma inteface pra cada tipo de cadastro;
	public String entradaDadoItem();// entrada básica de dados 
	public boolean enviaDadosValidacaoItem();// envia os dados via Socket ou RMI ou outros, para o servidor validar
	public String geraIdentificadorItem();// verifica se a algum item   no banco com dados parecidos , se não cria outra tupla e retorna o ID da mesma
	public boolean confirmaCadastroItem();// retorna um boolen true para a confirma do cadastro com sucesso, e  false caso contrario
    public String disponibilizaItem();// salva o item no banco em tabela especifica de items;
    
}
