package br.edu.ifpb.recdata.telas;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.servicos.VerificaServidorOnlineAsyncTasck;

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
		VerificaServidorOnlineAsyncTasck verifica_conexao = new VerificaServidorOnlineAsyncTasck(this);
		verifica_conexao.execute();
	}
}
