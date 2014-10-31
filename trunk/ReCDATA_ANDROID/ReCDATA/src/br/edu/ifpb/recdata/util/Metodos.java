package br.edu.ifpb.recdata.util;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class Metodos {


	public static boolean validaCampo(EditText campo) {
		if ((campo.getText().toString().trim().equals(""))
				|| (campo.getText().toString().equals(null))) {
			campo.setError("Campo Não Preenchido!");
			campo.setFocusable(true);
			campo.requestFocus();
			return false;
		}
		return true;
	}

	public static boolean validaSenha(EditText senha1, EditText senha2) {
		if (!(senha1.getText().toString().equals(senha2.getText().toString()))) {
			senha2.setError("As senhas não correspondem");
			senha2.setFocusable(true);
			senha2.requestFocus();
			return false;
		}
		return true;
	}

	public static String montarDataPicker(DatePicker datepicker) {
		// montar a data
		int dataAno = datepicker.getYear();
		int dataMes = datepicker.getMonth();
		int dataDia = datepicker.getDayOfMonth();

		String dataNascimentoCompleto = String.valueOf(dataAno) + "-"
				+ String.valueOf(dataMes) + "-" + String.valueOf(dataDia);

		return dataNascimentoCompleto;
	}

	public static String montarTimePicker(TimePicker timepicker) {
		// montar a data
		int hora = timepicker.getCurrentHour();
		int minuto = timepicker.getCurrentMinute();
		int segundo =000;

		String dataNascimentoCompleto = String.valueOf(hora) + ":"
				+ String.valueOf(minuto)+":"+ String.valueOf(segundo);

		return dataNascimentoCompleto;
	}
}
