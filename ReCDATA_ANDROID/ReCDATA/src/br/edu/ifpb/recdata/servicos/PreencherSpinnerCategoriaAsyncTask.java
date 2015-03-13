package br.edu.ifpb.recdata.servicos;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import br.edu.ifpb.recdata.entity.Categoria;
import br.edu.ifpb.recdata.excecao.HttpServiceException;

public class PreencherSpinnerCategoriaAsyncTask extends
		AsyncTask<Void, Integer, List<Categoria>> {

	private Activity activity;

	public PreencherSpinnerCategoriaAsyncTask(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected List<Categoria> doInBackground(Void... params) {
		List<Categoria> ListaCategoria = new ArrayList<Categoria>();

		// Enviar a requisição HTTP via GET.
		HttpService httpService;
		try {
			httpService = new HttpService(activity);
			HttpResponse response = httpService
					.sendGETRequest("/categoria/listar");
		
			
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				
				// Conversão do response ( resposta HTTP) para String.
				String json = HttpUtil.entityToString(response);
				Log.i("ReCDATA ", "Resquest - GET: " + json);

				try {
					JSONArray categoriaJsonArray = new JSONArray(
							json.toString());

					Log.i("Recdata,Categoria JSON:",
							categoriaJsonArray.toString());
					
					for (int i = 0; i < categoriaJsonArray.length(); i++) {
						
						Categoria categoria = new  Categoria();
					
						
						JSONObject categoriaJsonObject = categoriaJsonArray
								.getJSONObject(i);

						categoria.setId(categoriaJsonObject.getInt("id"));
						categoria.setDescricao(categoriaJsonObject.getString("descricao"));
						
						ListaCategoria.add(categoria);
					}
				} catch (JSONException e) {
					Log.e("RecDATA", "Erro na listagem da Categoria: " + e.getMessage());
				}
	}
		} catch (HttpServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return ListaCategoria;

		
	
	}
}