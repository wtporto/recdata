package br.edu.ifpb.recdata.listener;

import br.edu.ifpb.recdata.telas.TelaCadastraUsuario;
import android.view.View;
import android.view.View.OnClickListener;

public class CadastroUsuarioListener implements OnClickListener {

	private TelaCadastraUsuario activity;

	public CadastroUsuarioListener(TelaCadastraUsuario activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View view) {
		if (view == this.activity.getEditTextDataNascimento()) {
			this.activity.getDataNascimentoPickerDialog().show();
		}
	}
}