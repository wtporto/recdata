package br.edu.ifpb;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class TelaConsultar extends Activity {
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listaactivity_tela_consultar);
		
		 final Spinner spinner =(Spinner) findViewById(R.id.Spinner_Seleciona_Item);
		 String[] listaItem = {"CHAVE","CAIXA DE SOM","DATASHOW","NOTEBOOK"};
		 ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaItem);
		 spinner.setAdapter(adapter);
	
	 final Button buscabutton = (Button) findViewById(R.id.BuscaButton);
     buscabutton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			    Toast.makeText(getApplicationContext(),"Buscando Informações...", Toast.LENGTH_LONG).show(); 				 
			     
			}
     });
     
     
	}
}