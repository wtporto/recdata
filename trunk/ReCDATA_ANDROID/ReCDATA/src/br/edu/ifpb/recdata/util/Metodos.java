package br.edu.ifpb.recdata.util;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class Metodos {

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
		int segundo = 000;

		String dataNascimentoCompleto = String.valueOf(hora) + ":"
				+ String.valueOf(minuto) + ":" + String.valueOf(segundo);

		return dataNascimentoCompleto;
	}
}
