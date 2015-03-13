package br.edu.ifpb.recdata.servicos;

import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import br.edu.ifpb.recdata.excecao.HttpServiceException;
import br.edu.ifpb.recdata.telas.TelaAbertura;
import br.edu.ifpb.recdata.telas.TelaLogin;
import br.edu.ifpb.recdata.util.SemConexaoAlertDialog;

public class VerificaServidorOnlineAsyncTasck extends
		AsyncTask<Void, Void, JSONObject> {

	private Activity activity;

	private boolean existeConexao = true;

	public VerificaServidorOnlineAsyncTasck(Activity activity) {

		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
	}

	@Override
	protected JSONObject doInBackground(Void... params) {

		JSONObject serverJsonObject = null;

		try {

			// Enviar a requisição HTTP via GET.
			HttpService httpService = new HttpService(this.activity);
			HttpResponse response = httpService
					.sendGETRequest("/servicos/servidorOnline/");

			// Verificar se o servidor respondeu.
			if (response!=null) {
				// Conversão do response ( resposta HTTP) para String.
				String json = HttpUtil.entityToString(response);
				Log.i("ReCDATA", "Resquest - GET: " + json);

				serverJsonObject = new JSONObject(json);
			}
		} catch (JSONException e) {

			Log.e("ReCDATA", "Parsing data: " + e.getMessage());

		} catch (HttpServiceException e) {

			Log.e("ReCDATA", e.getMessage());

			this.existeConexao = false;
		}

		return serverJsonObject;
	}

	@Override
	protected void onPostExecute(JSONObject serverJsonObject) {

		if (this.existeConexao 
				&& serverJsonObject != null) {
			try {
				boolean online = serverJsonObject.getBoolean("online");

				Log.i("ReCDATA", "Servidor conectado: " + online);

				if (online == true) {

					Intent intent = new Intent(activity, TelaLogin.class);
					activity.startActivity(intent);
					activity.finish();

				} else {

					String texto = "Não foi possível estabelecer a conexão: Servidor em Manutenção";
					int duracao = Toast.LENGTH_LONG;
					Toast toast = Toast.makeText(
							activity.getApplicationContext(), texto, duracao);
					toast.show();
				}

			} catch (JSONException e) {

				Log.e("ReCDATA", "Error parsing data " + e.toString());
			}
		} else {
				SemConexaoAlertDialog semConexao = 
						new SemConexaoAlertDialog(activity);
				semConexao.showDialog();
		
		}
	}
}
