package br.edu.ifpb.recdata.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.util.TimeFormatException;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class TimePickerDialogAdapter implements OnTimeSetListener {

	private Activity activity;

	private EditText timeEditText;


	public TimePickerDialogAdapter(Activity activity, EditText timeEditText) {
		this(activity, timeEditText, "hh:mm:ss");
	}

	public TimePickerDialogAdapter(Activity activity, EditText timeEditText,
			String timezone) {
		this.activity = activity;
		this.timeEditText = timeEditText;
	}

	public TimePickerDialog builder() {

		Calendar newCalendar = Calendar.getInstance();
		int horas = newCalendar.get(Calendar.HOUR_OF_DAY);
		int minutos = newCalendar.get(Calendar.MINUTE);

		// Hora da Reserva
		TimePickerDialog timePickerDialog = new TimePickerDialog(this.activity,
				this, horas, minutos, true);

		return timePickerDialog;
	}

	@Override
	public void onTimeSet(TimePicker timePicker, int horasEscolhida, int MinutosEscolinho) {
        timeEditText.setText(""+horasEscolhida + ":" + MinutosEscolinho +":00");
    }
}
