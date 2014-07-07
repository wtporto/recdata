package br.edu.ifpb.excecoes;

import java.util.InputMismatchException;

public class TelefoneInvalidoException extends InputMismatchException {

	public TelefoneInvalidoException() {
		System.err.println("Por Favor, Informa somente numeros");
	}

	

}
