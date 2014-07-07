package br.edu.ifpb.excecoes;

public class IdadeInvalidaException extends Exception {

	public IdadeInvalidaException() {
		System.err.println("Por Favor, Idade Invalida, Informar Idade maior 0 e menor 100 :P");
	}

}
