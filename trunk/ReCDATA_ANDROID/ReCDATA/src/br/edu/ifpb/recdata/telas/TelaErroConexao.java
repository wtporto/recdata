package br.edu.ifpb.recdata.telas;

import br.edu.ifpb.recdata.util.Constantes;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class TelaErroConexao extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(br.edu.ifpb.R.layout.activity_erroconexao);
		 Toast.makeText(this.getApplicationContext(),
					Constantes.NA0_EXISTE_CONEXAO, Toast.LENGTH_SHORT).show();
	}

}
