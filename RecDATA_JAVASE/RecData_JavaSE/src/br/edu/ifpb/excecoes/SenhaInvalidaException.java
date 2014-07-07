package br.edu.ifpb.excecoes;

public class SenhaInvalidaException extends Exception {

	public SenhaInvalidaException() {
		System.err.println("Por Favor, Insira Senha Correta, OK..");
	}

}
