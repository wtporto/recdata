package br.edu.ifpb.recdata.servicos;


import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import br.edu.ifpb.recdata.telas.TelaListaFuncionalidadesPersonalizada;

public class UsuarioLoginAsyncTask extends
                AsyncTask<JSONObject, Void, HttpResponse> {

        private Activity activity;

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
                HttpService  httpService = new HttpService();
                HttpResponse response = httpService.sendJsonPostRequest(
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

                        if (httpCode >200 &&  httpCode <400) {
                                
                                Toast.makeText(activity.getApplicationContext(),
                                                "Bem vindo, " + jsonObject.getString("nomeUsuario"),
                                                Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(activity, TelaListaFuncionalidadesPersonalizada
                                		.class);
                                activity.startActivity(intent);
                                activity.finish();

                        } else {
                                Toast.makeText(activity.getApplicationContext(),
                                                jsonObject.getString("mensagem ERRO Ao LOGAR! "), Toast.LENGTH_SHORT)
                                                .show();
                        }

                } catch (JSONException e) {
                        Log.e("ReCDATA", "Error parsing data " + e.toString());
                }
        }

}
