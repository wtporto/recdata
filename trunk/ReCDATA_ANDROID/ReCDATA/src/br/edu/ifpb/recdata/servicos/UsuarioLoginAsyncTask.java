package br.edu.ifpb.recdata.servicos;

import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import br.edu.ifpb.recdata.entity.Usuario;
import br.edu.ifpb.recdata.telas.TelaListaFuncionalidades;
import br.edu.ifpb.recdata.util.Constantes;
import br.edu.ifpb.recdata.util.GlobalState;

public class UsuarioLoginAsyncTask extends
		AsyncTask<JSONObject, Void, HttpResponse> {

	private Activity activity;

	GlobalState gs;

	public UsuarioLoginAsyncTask(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected HttpResponse doInBackground(JSONObject... jsonObjects) {

		// Enviar a requisição HTTP via GET.
		HttpResponse response = HttpService.sendJsonPostRequest(
				"/usuario/verificar", jsonObjects[0]);
		return response;
	}

	@Override
	protected void onPostExecute(HttpResponse response) {

		int httpCode = response.getStatusLine().getStatusCode();

		try {
			// Conversão do response ( resposta HTTP) para String.
			String json = HttpUtil.entityToString(response);
			Log.i("ReCDATA", "Resquest - POST: " + json);

			JSONObject jsonObject = new JSONObject(json);

			if (httpCode > 200 && httpCode < 400) {

				Usuario usuario = new Usuario();
				usuario.setId(jsonObject.getInt("id"));
				usuario.setNome(jsonObject.getString("nome"));
				gs = (GlobalState) activity.getApplication();
				gs.setUsuario(usuario);

				Toast.makeText(activity.getApplicationContext(),
						Constantes.USUARIO_EXISTE + usuario.getNome(),
						Toast.LENGTH_SHORT).show();

				Intent intent = new Intent(activity,
						TelaListaFuncionalidades.class);
				activity.startActivity(intent);
				activity.finish();

			} else {
				if (httpCode >=403) {
					Toast.makeText(activity.getApplicationContext(),Constantes.ERRO_LOGAR,
							Toast.LENGTH_SHORT).show();
				
				//	EditText login = (EditText) findViewById(R.id.LoginApp);
					//EditText senha = (EditText) findViewById(R.id.SenhaApp);
					
					//login.setText("");
				//	senha.setText("");
				}
					
			}

		} catch (JSONException e) {
			Log.e("ReCDATA", "Error parsing data " + e.toString());
		}
	}
}
