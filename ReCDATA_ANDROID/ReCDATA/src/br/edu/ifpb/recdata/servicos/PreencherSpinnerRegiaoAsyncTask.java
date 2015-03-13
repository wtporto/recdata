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
import br.edu.ifpb.recdata.entity.Regiao;
import br.edu.ifpb.recdata.excecao.HttpServiceException;

public class PreencherSpinnerRegiaoAsyncTask extends
		AsyncTask<Void, Integer, List<Regiao>> {

	private Activity activity;

	public PreencherSpinnerRegiaoAsyncTask(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected List<Regiao> doInBackground(Void... params) {
		List<Regiao> ListaRegiao = new ArrayList<Regiao>();

		// Enviar a requisição HTTP via GET.
		HttpService httpService;
		try {
			httpService = new HttpService(activity);
			HttpResponse response = httpService
					.sendGETRequest("/regiao/listar");
		
			
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				
				// Conversão do response ( resposta HTTP) para String.
				String json = HttpUtil.entityToString(response);
				Log.i("ReCDATA ", "Resquest - GET: " + json);

				try {
					JSONArray regiaoJsonArray = new JSONArray(
							json.toString());

					Log.i("Recdata,Regiao JSON:",
							regiaoJsonArray.toString());
					
					for (int i = 0; i < regiaoJsonArray.length(); i++) {
						
						Regiao regiao = new Regiao();
					
						
						JSONObject regiaoJsonObjetc = regiaoJsonArray
								.getJSONObject(i);

						regiao.setId(regiaoJsonObjetc.getInt("id"));
						regiao.setDescricao(regiaoJsonObjetc.getString("nome"));
						
						ListaRegiao.add(regiao);
					}
				} catch (JSONException e) {
					Log.e("RecDATA", "Erro na listagem das Regiões: " + e.getMessage());
				}
	}
		} catch (HttpServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return ListaRegiao;

		
	
	}
}