package br.edu.ifpb.excecoes;

public class TamanhoInvalidoException extends Exception {

	public TamanhoInvalidoException() {
		System.err.println("Por Favor, Coloca no Máximo 30 carater, nosso Banco não e o data center do IBGE!");
	}

}
