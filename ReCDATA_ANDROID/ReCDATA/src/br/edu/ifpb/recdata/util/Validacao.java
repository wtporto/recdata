package br.edu.ifpb.recdata.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class Validacao {

	private static Pattern pattern;
	private static Pattern patternSenha;
	private static Matcher matcher;

	public static boolean validaCampo(EditText campo) {
		if ((campo.getText().toString().trim().equals(""))
				|| (campo.getText().toString().equals(null))) {
			campo.setError(Constantes.MSG_ErroPreencheCampo);
			campo.setFocusable(true);
			campo.requestFocus();
			return false;
		}
		return true;
	}

	public static boolean validaSenha(EditText senha1, EditText senha2) {
		if (!(senha1.getText().toString().equals(senha2.getText().toString()))) {
			senha2.setError(Constantes.MSG_ErroSenhaDiferentes);
			senha2.setFocusable(true);
			senha2.requestFocus();
			return false;
		}
		return true;
	}

	public static boolean validarSpinner(String campo, Context activity) {
		if ((campo).equals("Selecionado..")) {
			Toast toast = Toast.makeText(activity,
					Constantes.MSG_ErroSpinnerEscolha, Toast.LENGTH_LONG);
			toast.show();
			return false;
		}
		return true;
	}

	public static boolean validaEmail(EditText email) {
		if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
				email.getText().toString()).matches()) {
			email.setError(Constantes.MSG_ErroEmailInvalido);
			email.setFocusable(true);
			email.requestFocus();
			return false;
		}
		return true;
	}

	public static boolean validaNome(EditText nome) {
		String valor;
		valor = nome.getText().toString().trim();
		if ((valor.length() <=8 ) || (valor.length() >= 40)) {
			nome.setError(Constantes.MSG_ErroTamanhoInvalidoNome);
			nome.setFocusable(true);
			nome.requestFocus();
			return false;

		} else {
			if (valor.equals(null)) {
				Validacao.validaCampo(nome);
				return false;
			}
		}

		return true;
	}

	/*
	 * else { if (Validate(valor)) {
	 * nome.setError(Constantes.MSG_ErroCaracterEspecial);
	 * nome.setFocusable(true); nome.requestFocus(); return false; } }
	 */

	public static boolean Validate(final String value) {
		matcher = pattern.matcher(value.trim());
		return matcher.matches();
	}

	public void StringValidator() {
		pattern = Pattern.compile(Constantes.STRING_PATTERN);
		patternSenha = Pattern.compile(Constantes.PASSWORD_PATTERN);
	}

	public boolean ValidatePassword(final String senha) {
		matcher = patternSenha.matcher(senha);
		return matcher.matches();
	}

	public static boolean ValidarCPF(EditText cpf) {
		String valor;
		valor = cpf.getText().toString().trim();

		if ((valor.length() < 11) || (valor.length() > 11) || (valor.equals(null))) {
			cpf.setError(Constantes.MSG_ErroTamanhoInvalidoCPF);
			cpf.setFocusable(true);
			cpf.requestFocus();
			return false;
		}/*
		 * else { if (true == Validate(valor)) {
		 * cpf.setError(Constantes.MSG_ErroCaracterEspecial);
		 * cpf.setFocusable(true); cpf.requestFocus(); return false; } }
		 */
		return true;
	}

	public static boolean ValidarEndereco(EditText endereco) {
		String valor;
		valor = endereco.getText().toString().trim();

		if ((valor.length() < 20) || (valor.length() >70) || valor.equals(null)) {
			endereco.setError(Constantes.MSG_ErroTamanhoInvalidoFone);
			endereco.setFocusable(true);
			endereco.requestFocus();
			return false;
		}/*
		 * else { if (true == Validate(valor)) {
		 * endereco.setError(Constantes.MSG_ErroCaracterEspecial);
		 * endereco.setFocusable(true); endereco.requestFocus(); return false; }
		 * }
		 */
		return true;

	}
}

/*
 * 
 * 
 * public boolean ValidarCPF(EditText datanascimento){ String dat datanascimento
 * if (valor.length() > 40 || nome == null){
 * nome.setError(Constantes.MSG_ErroTamanhoInvalidoNome);
 * nome.setFocusable(true); nome.requestFocus(); return false; }
 * 
 * return true; }
 * 
 * validarIdade(){ MAXIMO 100 MINNO 14
 * 
 * } validarEndereço(){ TAM 70; caracter especial; }
 */

