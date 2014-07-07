package br.edu.ifpb.excecoes;

import java.util.InputMismatchException;

public class IdentificadorInvalidoException extends InputMismatchException{

	public IdentificadorInvalidoException() {
        System.err.println("Por Favor, Inserir Somente numeros");
	}

	

}
