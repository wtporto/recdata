package br.edu.ifpb.recdata.servicos;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ifpb.recdata.util.Constantes;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ReservarAsyncTask extends
		AsyncTask<JSONObject, Void, HttpResponse> {

	Activity activity;

	public ReservarAsyncTask(Activity activity) {
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
				"/reserva/criar", jsonObjects[0]);
		return response;
	}

	@Override
	protected void onPostExecute(HttpResponse response) {

		int httpCode = response.getStatusLine().getStatusCode();

	  if (httpCode > 200 && httpCode < 400) {
			try {
				// Conversão do response ( resposta HTTP) para String.
				String json = HttpUtil.entityToString(response);
				Log.i("ReCDATA ", "Resquest - POST: " + json);

				JSONObject jsonObject = new JSONObject(json);

				if (httpCode == HttpStatus.SC_CREATED) {

					Toast.makeText(activity.getApplicationContext(), Constantes.RESERVA_CONCLUIDA,
							Toast.LENGTH_SHORT).show();

					// Poderá navegar entre as páginas.
				} else {
					Toast.makeText(activity.getApplicationContext(),Constantes.RESERVA_NAO_CONCLUIDA, Toast.LENGTH_SHORT)
							.show();
				}

			} catch (JSONException e) {
				Log.e("RecDATA", "Erro na reserva do item: " + e.getMessage());
			}
	
		}
		}
}
