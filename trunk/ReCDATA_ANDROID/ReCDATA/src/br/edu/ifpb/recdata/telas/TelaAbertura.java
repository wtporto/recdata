package br.edu.ifpb.recdata.telas;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.servicos.VerificaServidorOnlineAsyncTasck;

public class TelaAbertura extends Activity implements Runnable{

	 private int duracao_da_tela = 3000; 
	
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_abertura);
		
		new Handler().postDelayed(this, duracao_da_tela);

	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("ReCDATA", "Fechando a aplicação.");
	}
	
	@Override
	public void run() {
		
		VerificaServidorOnlineAsyncTasck verificaConexao = 
				new VerificaServidorOnlineAsyncTasck(this);
		verificaConexao.execute();
		
		
	}
    
}

