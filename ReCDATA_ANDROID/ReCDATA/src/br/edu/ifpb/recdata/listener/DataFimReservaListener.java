package br.edu.ifpb.recdata.listener;

import android.view.View;
import android.view.View.OnClickListener;
import br.edu.ifpb.recdata.telas.TelaReservar;

public class DataFimReservaListener  implements OnClickListener {

	private TelaReservar activity;

	public DataFimReservaListener(TelaReservar activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View view) {
		if (view == this.activity.getEditTextDataFim()) {
			this.activity.getDataFimPickerDialog().show();
		}
	}

	
}