package br.edu.ifpb.Funcionaidades.CadastroUsuarios;

import java.util.Scanner;

public class CadastroUsuarioProfessor implements CadastrarUsuario{

	public String entradaDadoUsuario() {
		Scanner sc = new Scanner(System.in);
        String nome,idade,email,telefone,sexo,formacao,areaatuacao;
		
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
		System.out.println("Digite Formação:");
		formacao=sc.nextLine();
		System.out.println("Digite areaatuacao:");
		areaatuacao=sc.nextLine();
		return  nome+idade+email+telefone+sexo+formacao+areaatuacao;
		
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
		System.out.println("Seu Cadastro está sendo terminado, Obrigado Professor...");
		return false;
	}
	
}
