package br.edu.ifpb.recdata.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.widget.DatePicker;
import android.widget.EditText;

public class DatePickerDialogAdapter implements OnDateSetListener {

	private Activity activity;

	private EditText dateEditText;

	SimpleDateFormat dateFormatter;

	public DatePickerDialogAdapter(Activity activity, EditText dateEditText) {
		this(activity, dateEditText, "yyyy-MM-dd");
	}

	public DatePickerDialogAdapter(Activity activity, EditText dateEditText,
			String dateFormat) {
		this.activity = activity;
		this.dateEditText = dateEditText;
		dateFormatter = new SimpleDateFormat(dateFormat, Locale.US);
	}

	public DatePickerDialog builder() {

		Calendar newCalendar = Calendar.getInstance();

		// Data de Nascimento
		DatePickerDialog dataPickerDialog = new DatePickerDialog(this.activity,
				this, newCalendar.get(Calendar.YEAR),
				newCalendar.get(Calendar.MONTH),
				newCalendar.get(Calendar.DAY_OF_MONTH));

		return dataPickerDialog;
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		Calendar newDate = Calendar.getInstance();
		newDate.set(year, monthOfYear, dayOfMonth);
		dateEditText.setText(dateFormatter.format(newDate.getTime()));
	}
}
