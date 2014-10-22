package br.edu.ifpb.recdata.servicos;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.entity.Item;
import br.edu.ifpb.recdata.telas.TelaReservar;
import br.edu.ifpb.recdata.util.ItemAdapter;

public class BuscaItensServidorAsyncTask extends Activity {

	ListView listview;
	ArrayList<Item> ListaItens;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_view_item);

		ListaItens = new ArrayList<Item>();

		new Busca().execute();

	}

	private class Busca extends AsyncTask<Void, Integer, JSONObject> {

		private Intent intent;
		private Bundle paramsBundle;

		public Busca() {

		}

		@Override
		protected JSONObject doInBackground(Void... params) {

			// recupera o valor da categoria de outra intent
			intent = getIntent();
			paramsBundle = intent.getExtras();
			int categoriaIdBunble = paramsBundle.getInt("idcategoria");

			Log.i("IdCategoria ->", paramsBundle.toString());

			JSONObject jsonObjectEnvia = null;
			JSONObject jsonObjectRecebe = null;
			Item item = new Item();

			item.setIdCategoria(categoriaIdBunble);

			try {
				jsonObjectEnvia = new JSONObject();
				jsonObjectEnvia.put("descricaoCategoria", item.getIdItem());
				jsonObjectEnvia.put("descricaoItem", item.getDescricaoItem());
				jsonObjectEnvia.put("idCategoria", item.getIdCategoria());
				jsonObjectEnvia.put("idItem", item.getIdItem());

			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			// Enviar a requisição HTTP via POST.
			HttpService httpService = new HttpService();
			HttpResponse response;
			try {
				response = httpService.sendJsonPostRequest("/item/leitor",
						jsonObjectEnvia);

				// Conversão do response ( resposta HTTP) para String.
				String json = HttpUtil.entityToString(response);

				Log.i("AsyncTaskKJson", "Resquest - POST: " + json);

				jsonObjectRecebe = new JSONObject(json);
				JSONArray jsonArray = jsonObjectRecebe.getJSONArray("item");
				// itemAux serve para ser montado o objeto Item no lado
				// cliente

				for (int i = 0; i < jsonArray.length(); i++) {
					Item itemAux = new Item();
					JSONObject explrObject = jsonArray.getJSONObject(i);
					itemAux.setDescricaoCategoria(explrObject
							.getString("descricaoCategoria"));
					itemAux.setDescricaoItem(explrObject
							.getString("descricaoItem"));
					itemAux.setIdCategoria(explrObject.getInt("idCategoria"));
					itemAux.setIdItem(explrObject.getInt("idItem"));

					ListaItens.add(itemAux);
				}

			} catch (JSONException e) {

				Log.e("AsyncTaskKJson", "Error parsing data " + e.toString());

			}

			jsonObjectEnvia = null;

			return jsonObjectRecebe;

		}

		@Override
		protected void onPostExecute(JSONObject result) {

			listview = (ListView) findViewById(R.id.listaResultados);
			listview.setAdapter(new ItemAdapter(BuscaItensServidorAsyncTask.this,
					ListaItens));
			super.onPostExecute(result);

			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Intent chamaTelaResultado;

					Item itemEnviaResultado = new Item();
					itemEnviaResultado = (Item) arg0.getItemAtPosition(arg2);

					Bundle params = new Bundle();

					if (itemEnviaResultado != null) {

						chamaTelaResultado = new Intent(
								BuscaItensServidorAsyncTask.this, TelaReservar.class);

						params.putSerializable("Item",
								(Serializable) itemEnviaResultado);
						chamaTelaResultado.putExtras(params);
						startActivity(chamaTelaResultado);
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

		}
	}
}