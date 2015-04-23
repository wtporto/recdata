package br.edu.ifpb.recdata.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
		if ((campo).equals("Selecione..")) {
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
		if ((valor.length() <= 8) || (valor.length() >= 40)) {
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

		if ((valor.length() < 11) || (valor.length() > 11)
				|| (valor.equals(null))) {
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

	public static boolean validaCampoItemBusca(EditText nomeItem) {
		String valor;
		valor = nomeItem.getText().toString().trim();
		if ((valor.equals(null)) || (valor.equals(""))) {
			nomeItem.setError(Constantes.MSG_ErroCampoItemInvalido);
			nomeItem.setFocusable(true);
			nomeItem.requestFocus();
			return false;
		}
		return true;
	}

	public static boolean ValidarEndereco(EditText endereco) {
		String valor;
		valor = endereco.getText().toString().trim();

		if ((valor.length() < 20) || (valor.length() > 70)
				|| valor.equals(null)) {
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

	public static boolean ValidarSpinner2(Spinner spinner) {

		View selectedView = spinner.getSelectedView();
		if (selectedView != null && selectedView instanceof TextView) {
			TextView selectedTextView = (TextView) selectedView;
			if (spinner.getSelectedItem() == "Selecionado..") {
				String errorString = Constantes.MSG_ErroSpinnerEscolha;
				selectedTextView.setError(errorString);
			} else {
				selectedTextView.setError(null);
			}
		}
		return true;
	}

	public  static boolean validaIntervaloData(EditText dataInicio, EditText dataFinal) {

		boolean dataValida = false;

		// Atribui os valores dos editText para conversão
		String dataInStringInicio = dataInicio.getText().toString();
		String dataInStringFinal = dataFinal.getText().toString();

		// converte os editText em datas
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		try {

			// Converte para Date
			Date dateIndataInicio = df.parse(dataInStringInicio);
			Date dateIndataFinal = df.parse(dataInStringFinal);

			if (dateIndataInicio.equals(dateIndataFinal)) {
				Log.i("Data Valida", "Data é do mesmo Dia!");
				dataValida = true;
			} else if (dateIndataInicio.before(dateIndataFinal)) {
				dataFinal.setError(Constantes.MSG_ErroCampoDataFinalAntes);
				dataFinal.setFocusable(true);
				dataFinal.requestFocus();
				Log.e("Data invalida!", "Data Final Antes da Inicial");
				dataValida = false;
			} else if (dateIndataInicio.after(dateIndataFinal)) {
				dataInicio.setError(Constantes.MSG_ErroCampoDataInicioDepois);
				dataInicio.setFocusable(true);
				dataInicio.requestFocus();
				Log.e("Data invalida", "Data Inicio Após a da Final ");
				dataValida = false;
			}

		} catch (ParseException e) {
			Log.e("ERRO:", "Parcer na Datas");

			e.printStackTrace();
		}

		return dataValida;
	}

	public static boolean validaIntervaloHora(EditText horaInicio, EditText horaFinal) {

		boolean horaValida = false;

		// Atribui os valores dos editText para conversão
		String horaInStringInicio = horaInicio.getText().toString();
		String horaInStringFinal = horaFinal.getText().toString();

		// converte os editText em datas
		DateFormat df = new SimpleDateFormat("HH:mm:ss");

		try {

			// Converte para Date
			Date horaIndataInicio = df.parse(horaInStringInicio);
			Date horaIndataFinal = df.parse(horaInStringFinal);

			if (horaIndataInicio.equals(horaIndataFinal)) {

				Log.e("Horário Inválido ", "Horas Iguais!");
				horaInicio.setError(Constantes.MSG_ErroCampoHorasEquals);
				horaFinal.setFocusable(true);
				horaFinal.requestFocus();

				horaValida = false;

			} else

			if (horaIndataInicio.before(horaIndataFinal)) {

				Log.e("Data Inválida", "Hora Final antes da Hora Inicial");
				horaFinal.setError(Constantes.MSG_ErroCampoHoraFinalAntes);
				horaFinal.setFocusable(true);
				horaFinal.requestFocus();

				horaValida = false;

			} else if (horaIndataInicio.after(horaIndataFinal)) {
				Log.i("Hora Valida", "Hora Final Após a hora Inicial");
				horaValida = true;
			}
		} catch (ParseException e) {
			Log.e("ERRO:", "Parcer na Datas");
			e.printStackTrace();
		}

		return horaValida;
	}

	public  static boolean validaPeriodo(EditText horaInicio, EditText horaFinal) {

		Calendar calendarInicio = Calendar.getInstance();
		Calendar calendarFinal = Calendar.getInstance();

		String horaInStringInicio = horaInicio.getText().toString();

		String horaInStringFinal = horaFinal.getText().toString();

		boolean validar = false;

		long diferencaMilli = 0;
		long milliInicio = 0;
		long milliFinal = 0;

		try {

			Date horaInicioIndate = new SimpleDateFormat("HH:mm:ss")
					.parse(horaInStringInicio);
			calendarInicio.setTime(horaInicioIndate);

			Date horaFinalIndate = new SimpleDateFormat("HH:mm:ss")
					.parse(horaInStringFinal);
			calendarFinal.setTime(horaFinalIndate);

			milliInicio = calendarInicio.getTimeInMillis();
			milliFinal = calendarFinal.getTimeInMillis();

			diferencaMilli = (milliFinal - milliInicio);

			int minutos = (int) ((diferencaMilli / 60000) % 60);
			int horas = (int) (diferencaMilli / 3600000);

			if ((horas > 15) && (minutos > 59)) {
				horaFinal.setError(Constantes.MSG_ErroIntervaloMaior);
				horaFinal.setFocusable(true);
				horaFinal.requestFocus();
				Log.e("Intervalo Maior",
						"Diferença e Maior que 15 horas!, Diferença é");

				return validar = false;

			} else if ((horas == 0) && (minutos == 00)) {
				horaInicio.setError(Constantes.MSG_ErroIntervaloIgualZERO);
				horaInicio.setFocusable(true);
				horaInicio.requestFocus();
				Log.e("Intervalo Nulo",
						"Diferença e igual há Zero, Mesmas horas!, Diferença é");

				return validar = false;
			} else if ((horas <= 15) && (minutos <= 60)) {
				Log.i("Intervalo Permitido ",
						"Diferença Menor que 15!, Diferença é");

				return validar = true;
			}
		} catch (ParseException e) {
			Log.e("ReCDATA", "Problema no parser da data.");
		}
		return validar;
	}

	public static boolean validarCampoDataHora(EditText data) {
		String valor;
		valor = data.getText().toString().trim();
		if ((valor.equals(null)) || (valor.equals(""))) {
			data.setError(Constantes.MSG_ErroCampoData);
			data.setFocusable(true);
			data.requestFocus();
			return false;
		}
		return true;
	}

	//TODO:criar validação para reserva no mesmo horario;

	public boolean validarCampoHora(EditText hora) {
		String valor;
		valor = hora.getText().toString().trim();
		if ((valor.equals(null)) || (valor.equals(""))) {
			hora.setError(Constantes.MSG_ErroCampoHora);
			hora.setFocusable(true);
			hora.requestFocus();
			return false;
		}
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

