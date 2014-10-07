package br.edu.ifpb.recdata.telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.servicos.BuscaItensServidor;

public class TelaConsultar extends Activity {

	String[] listaItem = { "Selecione Categoria", "CHAVE", "CAIXA DE SOM",
			"DATASHOW", "NOTEBOOK" };

	private int idCategoriaSpinner = 0; // setar o id categoria atravez do item
										

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listaactivity_tela_consultar);

		final Spinner spinner = (Spinner) findViewById(R.id.Spinner_Seleciona_Item);

		final ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, listaItem);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View v,
					int posicao, long id) {

				

				int AuxidCategoriaSpinner = posicao;

				if (AuxidCategoriaSpinner == 1) {
					
					idCategoriaSpinner = 1;//chave
					Log.i("Spinner", "id do Item:" + AuxidCategoriaSpinner);
					Log.i("Idcategoria - INT", ""+idCategoriaSpinner);
					
				} else if (AuxidCategoriaSpinner == 2) {
					
					idCategoriaSpinner = 2;//caixa de som 
					Log.i("Spinner", "id do Item:" + AuxidCategoriaSpinner);
					Log.i("Idcategoria - INT", ""+idCategoriaSpinner);
					
				} else if (AuxidCategoriaSpinner == 3) {
					
					idCategoriaSpinner = 3;//datashow 
					Log.i("Spinner", "id do Item:" + AuxidCategoriaSpinner);
					Log.i("Idcategoria - INT", ""+idCategoriaSpinner);
					
				} else if (AuxidCategoriaSpinner == 4) {
					
					idCategoriaSpinner = 4;// notebook
					Log.i("Spinner", "id do Item:" + AuxidCategoriaSpinner);
					Log.i("Idcategoria - INT", ""+idCategoriaSpinner);
					
				}

			}

			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		final Button buscabutton = (Button) findViewById(R.id.BuscaButton);
		buscabutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Toast.makeText(getApplicationContext(),
						"Buscando Informações no Servidor...",
						Toast.LENGTH_LONG).show();
				// criar uma intent aqui!!, para leva com a tela de resultados

				Intent chamarResultados = new Intent(TelaConsultar.this, BuscaItensServidor.class);
				Bundle params = new Bundle();

				if( idCategoriaSpinner >=1){

					params.putInt("idcategoria", idCategoriaSpinner);
					chamarResultados.putExtras(params);
					startActivity(chamarResultados);
					Log.i("Valor Passado por paramentor Bundle->",
							params.toString());
				} else {
					Toast.makeText(getApplicationContext(),
							"Valor Errado da Spinner!", Toast.LENGTH_LONG)
							.show();
					// trocar isto para outra intent :D depois
				}
			}

		});

	}

}