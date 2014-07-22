package br.edu.ifpb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class TelaAbertura extends Activity implements Runnable{


	private int duracao_da_tela = 3000;


	@Override
		protected void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_abertura);
     
        new Handler().postDelayed(this, duracao_da_tela);
	
	}

		@Override
		public void run() {
		
		        Intent minha_acao = new Intent(this, TelaTipoUsuario.class);
		        
		        startActivity(minha_acao);
		        
		        finish();
		        
		}
}


