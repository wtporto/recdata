package br.edu.ifpb.Funcionaidades.CadastroUsuarios;

import java.util.Scanner;

public class CadastrarUsuarioMonitor implements CadastrarUsuario{

	public String entradaDadoUsuario() {
		Scanner sc = new Scanner(System.in);
        String nome,idade,email,telefone,sexo,serie,curso,disciplina;
		
		System.out.println("Digite Nome:");
		nome= sc.nextLine();
		System.out.println("Digite idade:");
		idade= sc.nextLine();
		System.out.println("Digite email:");
		email=sc.nextLine();
		System.out.println("Digite telefone:");
		telefone= sc.nextLine();
		System.out.println("Digite sexo:");
		sexo=sc.nextLine();
		System.out.println("Digite serie:");
		serie=sc.nextLine();
		System.out.println("Digite curso:");
		curso=sc.nextLine();
		System.out.println("Digite disciplina;");
		disciplina=sc.nextLine();
		
	
		return nome+idade+email+telefone+sexo+serie+curso+disciplina;
	}

	public String enviaDadosValidacaoUsuario() {
 
		System.out.println("Seus dados estão sendo avalidado, espere um pouco");
		//conectar-se ao servidor atravez de alguma tecnologia e consultar o banco e vaidar os dados
		return "OK, Carregando!";
	}

	public String senhaCriptografadaUsuario() {
		System.out.println("Senha Criada!");// pesquisar como criptografa senha e mostra e envia para o banco 
		return null;
	}

	public boolean confirmaCadastroUsuario() {
		System.out.println("Seu Cadastro está sendo terminado, Obrigado Monitor...");
		return false;
	}
	
}
