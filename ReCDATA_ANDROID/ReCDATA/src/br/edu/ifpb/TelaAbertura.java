package br.edu.ifpb;

import android.app.Activity;
import android.os.Bundle;
import br.edu.ifpb.servicos.VerificaServidorON;


public class TelaAbertura extends Activity{



	@Override
		protected void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
		
        VerificaServidorON verificaConexao = new VerificaServidorON(this);
        verificaConexao.execute();	
	}		
}


