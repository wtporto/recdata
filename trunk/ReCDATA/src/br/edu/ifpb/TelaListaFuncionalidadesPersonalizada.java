package br.edu.ifpb;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TelaListaFuncionalidadesPersonalizada extends Activity  {
	
				

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);
        
        	ArrayList<ItemModel> itens = new ArrayList<ItemModel>();
        	
        	ItemModel item1 = new ItemModel();
        	item1.setNome("Consultar Item");
        	ItemModel item2 = new ItemModel();
        	item2.setNome("Reservar Item");
        	ItemModel item3 = new ItemModel();
        	item3.setNome("Reedback");
        	ItemModel item4 = new ItemModel();
        	item4.setNome("Voltar");       	
        	
        	
			itens.add(item1);
			itens.add(item2);
			itens.add(item3);
			itens.add(item4);
        		
         ListView listview = (ListView) findViewById(R.id.lv);
         listview.setAdapter(new ItemAdapter(this, itens));
        
         listview.setOnItemClickListener( new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent;
				
				switch(arg2){
				case 0:
					intent =  new Intent(getBaseContext(), TelaConsultar.class);
					startActivity(intent);
					break;
				case 1:
					intent =  new Intent(getBaseContext(), TelaReservar.class);
					startActivity(intent);
					break;
				case 2:
					intent =  new Intent(getBaseContext(), TelaFeedback.class);
					startActivity(intent);
				case 3:
					intent =  new Intent(getBaseContext(), TelaLogin.class);
					startActivity(intent);
				default:
					finish();
				}	
				
			}
		
        		 
    }); 		 
    
    }

}
