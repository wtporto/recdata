package br.edu.ifpb.recdata.telas;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.leitorCodigoqr.QrCode;
import br.edu.ifpb.recdata.util.Model;
import br.edu.ifpb.recdata.util.ModelAdapter;

public class TelaListaFuncionalidadesPersonalizada extends Activity  {
	
				

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);
        
        	ArrayList<Model> itens = new ArrayList<Model>();
        	
        	Model model1 = new Model();
        	model1.setNome("Consultar Item");
        	Model model2 = new Model();
        	model2.setNome("Reservar Item");
        	Model model3 = new Model();
        	model3.setNome("Reservar Qr");
        	Model model4 = new Model();
        	model4.setNome("Feedback");
        	Model model5 = new Model();
        	model5.setNome("Voltar");       	
        	
        	
			itens.add(model1);
			itens.add(model2);
			itens.add(model3);
			itens.add(model4);
			itens.add(model5);
        		
         ListView listview = (ListView) findViewById(R.id.lv);
         listview.setAdapter(new ModelAdapter(this, itens));
        
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
					intent =  new Intent(getBaseContext(), QrCode.class);
					startActivity(intent);
					break;
				case 3:
					intent =  new Intent(getBaseContext(), TelaFeedback.class);
					startActivity(intent);
				case 4:
					intent =  new Intent(getBaseContext(), TelaLogin.class);
					startActivity(intent);
				default:
					finish();
				}	
				
			}
		
        		 
    }); 		 
    
    }

}
