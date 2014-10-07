package br.edu.ifpb.recdata.servicos;

import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ifpb.recdata.telas.TelaTipoUsuario;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class VerificaServidorON extends AsyncTask<Void, Integer, JSONObject> {

	private Activity activity;

	public VerificaServidorON(Activity activity) {

		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {

		super.onPreExecute();

	}

	@Override
	protected JSONObject doInBackground(Void... params) {

		JSONObject jsonObject = null;

		// Enviar a requisição HTTP via GET.
		HttpService httpService = new HttpService();
		HttpResponse response = httpService.sendGETRequest("/servicos/servidorOnline");

		// Conversão do response ( resposta HTTP) para String.
		String json = HttpUtil.entityToString(response);

		Log.i("AsyncTaskKJson", "Resquest - GET: " + json);

		try {

			jsonObject = new JSONObject(json);

		} catch (JSONException e) {

			Log.e("AsyncTaskKJson", "Error parsing data " + e.toString());
		}

		return jsonObject;
	}

	@Override
	protected void onPostExecute(JSONObject result) {

		try {

			boolean online = result.getBoolean("online");

			Log.i("AsyncTaskKJson", "Servidor conectado: " + online);

			if (online == true) {

				Intent intent = new Intent(activity, TelaTipoUsuario.class);
				activity.startActivity(intent);
				activity.finish();
			} else {

				String texto = "Não foi possível estabelecer a conexão: Servidor em Manutenção";
				int duracao = Toast.LENGTH_LONG;
				Toast toast = Toast.makeText(activity.getApplicationContext(),
						texto, duracao);
				toast.show();
			}

		} catch (JSONException e) {
			Log.e("AsyncTaskKJson", "Error parsing data " + e.toString());
		}

	}

}
