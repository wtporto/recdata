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
import br.edu.ifpb.recdata.entity.TipoUsuario;
import br.edu.ifpb.recdata.excecao.HttpServiceException;

public class PreencherSpinnerTipoUsuarioAsyncTask extends
		AsyncTask<Void, Integer, List<TipoUsuario>> {

	private Activity activity;

	public PreencherSpinnerTipoUsuarioAsyncTask(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected List<TipoUsuario> doInBackground(Void... params) {
		List<TipoUsuario> tiposUsuarios = new ArrayList<TipoUsuario>();

		// Enviar a requisição HTTP via GET.
		HttpService httpService;
		try {
			httpService = new HttpService(activity);
			HttpResponse response = httpService
					.sendGETRequest("/tipousuario/listar");
		
			
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				
				// Conversão do response ( resposta HTTP) para String.
				String json = HttpUtil.entityToString(response);
				Log.i("ReCDATA ", "Resquest - GET: " + json);

				try {
					JSONArray tiposUsuarioJsonArray = new JSONArray(
							json.toString());

					Log.i("Recdata,TiposUsuario JSON:",
							tiposUsuarioJsonArray.toString());
					
					for (int i = 0; i < tiposUsuarioJsonArray.length(); i++) {
						
						TipoUsuario tipoUsuario = new TipoUsuario();
					
						
						JSONObject tipoUsuarioJsonObject = tiposUsuarioJsonArray
								.getJSONObject(i);

						tipoUsuario.setId(tipoUsuarioJsonObject.getInt("id"));
						tipoUsuario.setDescricao(tipoUsuarioJsonObject.getString("descricao"));
						
						tiposUsuarios.add(tipoUsuario);
					}
				} catch (JSONException e) {
					Log.e("RecDATA", "Erro na listagem dos Tipos de Usuario: " + e.getMessage());
				}
	}
		} catch (HttpServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return tiposUsuarios;

		
	
	}
}