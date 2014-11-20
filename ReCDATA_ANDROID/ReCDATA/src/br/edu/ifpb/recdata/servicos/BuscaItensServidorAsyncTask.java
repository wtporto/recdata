package br.edu.ifpb.recdata.servicos;

import java.io.Serializable;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import br.edu.ifpb.recdata.telas.TelaConsultarItem;
import br.edu.ifpb.recdata.telas.TelaResultadoItem;
import br.edu.ifpb.recdata.util.Constantes;

public class BuscaItensServidorAsyncTask extends
		AsyncTask<JSONObject, Void, HttpResponse> {

	Activity activity;

	public BuscaItensServidorAsyncTask(Activity activity) {
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
				"/item/consultarItens", jsonObjects[0]);
		return response;

	}

	@Override
	protected void onPostExecute(HttpResponse response) {

		int httpCode = response.getStatusLine().getStatusCode();

		if (httpCode >= 200 && httpCode < 400) {

			// Conversão do response ( resposta HTTP) para String.
			String itensJson = HttpUtil.entityToString(response);
			Log.i("ReCDATA ", "Resquest - POST: " + itensJson);

			if (httpCode == HttpStatus.SC_ACCEPTED) {

				Toast.makeText(activity.getApplicationContext(),
						Constantes.ITEM_ENCONTRADO, Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(this.activity, TelaResultadoItem.class);
				Bundle bundle = new Bundle();
				bundle.putString("itens", itensJson);
				intent.putExtras(bundle);
				this.activity.startActivity(intent);

			} else {
				Toast.makeText(activity.getApplicationContext(),
						Constantes.ITEM_NAO_ENCONTRADO, Toast.LENGTH_SHORT)
						.show();
			}

		}

	}
}
