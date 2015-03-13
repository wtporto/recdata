package br.edu.ifpb.recdata.listener;

import android.view.View;
import android.view.View.OnClickListener;
import br.edu.ifpb.recdata.telas.TelaReservar;

public class DataInicioReservaListener  implements OnClickListener {

	private TelaReservar activity;

	public DataInicioReservaListener(TelaReservar activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View view) {
		if (view == this.activity.getEditTextDataInicio()) {
			this.activity.getDataInicioPickerDialog().show();
		}
	}
}
