package br.edu.ifpb;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import br.edu.ifpb.servicos.VerificaServidorON;

public class TelaAbertura extends Activity implements Runnable {

	private static int SPLASH_TIME_OUT = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_abertura);
		Handler handler = new Handler();
		handler.postDelayed(this, SPLASH_TIME_OUT);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		VerificaServidorON verifica_conexao = new VerificaServidorON(this);
		verifica_conexao.execute();
	}
}
