package br.edu.ifpb.recdata.telas;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.servicos.VerificaServidorOnlineAsyncTasck;

public class TelaAbertura extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_abertura);
		
		VerificaServidorOnlineAsyncTasck verificaConexao = 
				new VerificaServidorOnlineAsyncTasck(this);
		verificaConexao.execute();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("ReCDATA", "Fechando a aplicação.");
	}
}
