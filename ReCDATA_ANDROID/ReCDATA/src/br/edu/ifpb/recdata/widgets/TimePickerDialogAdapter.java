package br.edu.ifpb.recdata.widgets;

import java.util.Calendar;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.widget.EditText;
import android.widget.TimePicker;

public class TimePickerDialogAdapter implements OnTimeSetListener {

	private Activity activity;

	private EditText timeEditText;

	private String title;

	public TimePickerDialogAdapter(Activity activity, EditText timeEditText) {
		this(activity, timeEditText, "hh:mm:ss");
	}
	
	public void setTitleTimer(String title){
		this.title = title;
	}
	public String getTitleTimer(){
		return this.title;
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

		timePickerDialog.setTitle(getTitleTimer());
		return timePickerDialog;
	}

	@Override
	public void onTimeSet(TimePicker timePicker, int horasEscolhida, int MinutosEscolinho) {
        timeEditText.setText(""+horasEscolhida + ":" + MinutosEscolinho +":00");
    }
}
