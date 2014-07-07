package br.edu.ifpb.Funcionaidades.CadastroItem;

import java.util.Scanner;

public class CadastrarItemCaixaSom implements CadastrarItem {


	public String entradaDadoItem() {
		Scanner sc = new Scanner(System.in);
		String modelo,status;
		System.out.println("Digite O modelo da Caixa de Som:");
		modelo = sc.nextLine();
		System.out.println("Digite Status");
		status=sc.nextLine();
		return modelo+status;
	}

	public boolean enviaDadosValidacaoItem() {
		System.out.println("Os Dados Informados estão sendo enviados para a validação no Servidor\n Por Favor aguarde um estante!");
		// conectar-se com o servidor atraves de socket ou rmi ou outra merda dessa para validar
		return true;
	}

	public String geraIdentificadorItem() {
		// conectar com o banco e  vê a compatibilidade de dados informados pelo usuario se há algum item igual no banco caso não criar novo item 
		System.out.println("Item Tem Identificador Unico, serva criado o novo item, e só confirmar dados");
		return null;
	}

	public boolean confirmaCadastroItem() {
		// conecta com o banco e cria a nova tupla do item
		System.out.println("Caixa de Som  criada com sucesso...");
		return false;
	}

	public String disponibilizaItem() {
		System.out.println("Item Caixa de Som está pronto para a Reserva");
		// item deve ser enviado para uma tabela de itens disponiveis ;)no banco e claro anta!
		return null;
	}

}
