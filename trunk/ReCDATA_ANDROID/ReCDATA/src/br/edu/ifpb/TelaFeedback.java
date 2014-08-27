package br.edu.ifpb;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class TelaFeedback extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_feedback);
			
			final Spinner spinner = (Spinner) findViewById(R.id.Spinner_Selecionar_item_Feedback);
			String[] listaItem = { "Falhas ao Reservar", "Muita demora para Confirma Dados", "Gostei!", "Melhorar em?" };
			ArrayAdapter adapter = new ArrayAdapter<Object>(this,
					android.R.layout.simple_spinner_item, listaItem);
			spinner.setAdapter(adapter);
		}
		
	    
	}

