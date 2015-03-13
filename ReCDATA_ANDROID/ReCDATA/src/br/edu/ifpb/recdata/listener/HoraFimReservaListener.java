package br.edu.ifpb.recdata.listener;

import android.view.View;
import android.view.View.OnClickListener;
import br.edu.ifpb.recdata.telas.TelaReservar;

public class HoraFimReservaListener  implements OnClickListener {

	private TelaReservar activity;

	public HoraFimReservaListener(TelaReservar activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View view) {
		if (view == this.activity.getEditTextHoraFim()) {
			this.activity.getHoraFimPickerDialog().show();
		}
	}

}	

