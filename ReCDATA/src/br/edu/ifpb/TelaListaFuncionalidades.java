package br.edu.ifpb;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TelaListaFuncionalidades extends ListActivity {

	private static final String[] ListaFuncionalidades = new String[]{"Consultar Itens", "Reservar Itens","Voltar"}; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListaFuncionalidades);
		//ListView listView = (ListView) findViewById(android.R.id.list);
		setListAdapter(adapter);
	    
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		Intent intent;
		
		switch(position){
			case 0:
				intent = new Intent(this, TelaConsultar.class);
				startActivity(intent);
				break;
			case 1:
				intent = new Intent(this, TelaReservar.class);
				startActivity(intent);
				break;
			case 2:
				intent = new Intent(this, TelaLogin.class);
				startActivity(intent);
			default:
				finish();
				
		}
	}
}
