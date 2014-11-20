package br.edu.ifpb.recdata.telas;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.entity.Categoria;
import br.edu.ifpb.recdata.entity.Item;
import br.edu.ifpb.recdata.entity.Regiao;
import br.edu.ifpb.recdata.util.ItemAdapter;

public class TelaResultadoItem extends Activity {

	ListView listview;
	ArrayList<Item> ListaItens;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		//[{"id":1,"descricao":"01","categoria":{"id":1,"descricao":"Sala"},
		//"regiao":{"id":1,"nome":"Área de vivência"},"registro":null}]
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado_busca);

		 ListaItens= new ArrayList<Item>();
		 
		Bundle bundle = this.getIntent().getExtras();
		try {
			JSONObject itensJsonObject = new JSONObject(
					bundle.getString("itens"));
			
			JSONArray jsonArray = itensJsonObject.getJSONArray("");
			for (int i = 0; i < jsonArray.length(); i++) {
                Item itemAux = new Item();
                JSONObject explrObject = jsonArray.getJSONObject(i);
                itemAux.setDescricao(explrObject
                                .getString("descricao"));
                itemAux.setCategoria(new Categoria(explrObject.getString("descricao")));
                itemAux.setRegiao(new Regiao(explrObject.getString("nome")));

                ListaItens.add(itemAux);
        }
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
	
		
		
		listview = (ListView) findViewById(R.id.lv_resultado);
        listview.setAdapter(new ItemAdapter(TelaResultadoItem.this,
                        ListaItens));
        

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                int arg2, long arg3) {
                        Intent chamaTelaReserva;

                        Item itemEnviaResultado = new Item();
                        itemEnviaResultado = (Item) arg0.getItemAtPosition(arg2);

                        Bundle params = new Bundle();

                        if (itemEnviaResultado != null) {

                        	chamaTelaReserva = new Intent(
                                                TelaResultadoItem.this, TelaReservar.class);

                                params.putSerializable("Item",
                                                (Serializable) itemEnviaResultado);
                                chamaTelaReserva.putExtras(params);
                                startActivity(chamaTelaReserva);
                                Log.i("Valor Passado por paramentor Bundle->",
                                                params.toString());
                        } else {
                                Toast.makeText(getApplicationContext(),
                                                "Não Foi identificado Item!", Toast.LENGTH_LONG)
                                                .show();
                                // trocar isto para outra intent :D depois
                        }
                }		
		
        });
 }}