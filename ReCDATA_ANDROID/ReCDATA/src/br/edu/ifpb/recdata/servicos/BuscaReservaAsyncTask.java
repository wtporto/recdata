package br.edu.ifpb.recdata.servicos;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import br.edu.ifpb.recdata.telas.TelaResultadosReserva;
import br.edu.ifpb.recdata.util.Constantes;
import br.edu.ifpb.recdata.util.GlobalState;

public class BuscaReservaAsyncTask extends
		AsyncTask<JSONObject, Void, HttpResponse> {

	Activity activity;

	public BuscaReservaAsyncTask(Activity activity) {
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
				"/reserva/listaReservaUser", jsonObjects[0]);
		return response;
	}
	

	@Override
	protected void onPostExecute(HttpResponse response) {

		int httpCode = response.getStatusLine().getStatusCode();

		if (httpCode == HttpStatus.SC_OK) {

			// Conversão do response ( resposta HTTP) para String.
			String reservasJson = HttpUtil.entityToString(response);
			Log.i("ReCDATA ", "Resquest - POST: " + reservasJson);

		
			try {
				JSONArray jsonArray = new JSONArray(reservasJson);

				if (jsonArray.length() > 0) {
					Toast.makeText(activity.getApplicationContext(),
							Constantes.RESERVA_ENCONTRADA, Toast.LENGTH_SHORT)
							.show();
					Intent intent = new Intent(this.activity,
							TelaResultadosReserva.class);
					Bundle bundle = new Bundle();
					bundle.putString("reservas", reservasJson);
					intent.putExtras(bundle);
					this.activity.startActivity(intent);

				} else {
					Toast.makeText(activity.getApplicationContext(),
							Constantes.RESERVA_NAO_ENCONTRADA, Toast.LENGTH_SHORT)
							.show();
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
