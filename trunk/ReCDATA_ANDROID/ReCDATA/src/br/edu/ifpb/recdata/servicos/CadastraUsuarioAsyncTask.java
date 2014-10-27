package br.edu.ifpb.recdata.servicos;

import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ifpb.recdata.telas.TelaListaFuncionalidadesPersonalizada;
import br.edu.ifpb.recdata.telas.TelaLogin;
import br.edu.ifpb.recdata.util.Constantes;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class CadastraUsuarioAsyncTask extends AsyncTask<JSONObject, Void, HttpResponse> {

	Activity activity;
	
	public CadastraUsuarioAsyncTask( Activity activity) {
	
		this.activity = activity;
	
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
	@Override
	protected HttpResponse doInBackground(JSONObject... jsonObjects) {
	    // Enviar a requisição HTTP via GET.
        HttpService  httpService = new HttpService();
        HttpResponse response = httpService.sendJsonPostRequest(
                       "/usuario/cadastrar", jsonObjects[0]);
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

                if (httpCode >200 &&  httpCode < 400) {
                        
                        Toast.makeText(activity.getApplicationContext(),
                                        Constantes.USUARIO_CADASTRADO,
                                        Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(activity, TelaLogin
                        		.class);
                        activity.startActivity(intent);
                        activity.finish();

                } else {
                        Toast.makeText(activity.getApplicationContext(),
                                        jsonObject.getString("mensagem ERRO Ao Cadastra! "), Toast.LENGTH_SHORT)
                                        .show();
                }

        } catch (JSONException e) {
                Log.e("ReCDATA", "Error parsing data " + e.toString());
        }



	}
}
