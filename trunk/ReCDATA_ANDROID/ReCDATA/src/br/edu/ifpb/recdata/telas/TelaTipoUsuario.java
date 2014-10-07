package br.edu.ifpb.recdata.telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import br.edu.ifpb.R;


public class TelaTipoUsuario extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_tipo_usuario);
		ImageView iconProf = (ImageView) findViewById(R.id.imageView_prof);
		iconProf.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent chamarTelaLogin = new Intent(TelaTipoUsuario.this,
						TelaLogin.class);
				
				Toast.makeText(getApplicationContext(),"Selecionou Professor!", Toast.LENGTH_LONG).show();
				startActivity(chamarTelaLogin);
				 finish(); 
			}
		});

		ImageView iconMonitor = (ImageView) findViewById(R.id.imageView_monitor);
		iconMonitor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent chamarTelaLogin = new Intent(TelaTipoUsuario.this,
						TelaLogin.class);
				Toast.makeText(getApplicationContext(),"Selecionou Monitor!", Toast.LENGTH_LONG).show();
				startActivity(chamarTelaLogin);
				 finish();
			}
		});

		ImageView iconServidor = (ImageView) findViewById(R.id.imageView_servidor);
		iconServidor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent chamarTelaLogin = new Intent(TelaTipoUsuario.this,
						TelaLogin.class);
				Toast.makeText(getApplicationContext(),"Selecionou Servidor!", Toast.LENGTH_LONG).show();
				startActivity(chamarTelaLogin);
				 finish();
			}
		});

	}

}