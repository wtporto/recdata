package br.edu.ifpb.recdata.servicos;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import br.edu.ifpb.recdata.excecao.HttpServiceException;
import br.edu.ifpb.recdata.util.Constantes;

import android.app.Activity;
import android.util.Log;

public class HttpService {

	// URL to get JSON Array
	private static String url = "http://192.168.1.119:8080/ReCDATA_SERVICE";

	// constructor
	public HttpService(Activity activity) throws HttpServiceException {
		
		boolean existeConexao = HttpUtil.isConnect(activity);
		Log.i("ReCDATA", "Conexão existe? " + existeConexao);
		
		if (!existeConexao) {
			throw new HttpServiceException(Constantes.NA0_EXISTE_CONEXAO);
		}
	}

	public HttpResponse sendGETRequest(String service) {

		HttpResponse response = null;

		HttpGet httpGet = new HttpGet(url + service);

		try {

			HttpClient httpClient = new DefaultHttpClient();

			response = httpClient.execute(httpGet);

		} catch (ClientProtocolException e) {
			Log.e("AsyncTaskKJson", "Error converting result " + e.toString());
		} catch (IOException e) {
			Log.e("AsyncTaskKJson", "Error converting result " + e.toString());
		}
		return response;
	}

	public static HttpResponse sendJsonPostRequest(String service,
			JSONObject json) {

		// Response
		HttpResponse response = null;

		// Create a new HttpClient and Post Header
		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(url + service);

		try {

			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			StringEntity se = new StringEntity(json.toString(), HTTP.ISO_8859_1);
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json;charset=" + HTTP.ISO_8859_1));

			httpPost.setEntity(se);

			response = httpClient.execute(httpPost);

		} catch (UnsupportedEncodingException e) {

			Log.i("AsyncTaskKJson", e.getMessage());

		} catch (ClientProtocolException e) {

			Log.i("AsyncTaskKJson", e.getMessage());
		} catch (IOException e) {

			Log.i("AsyncTaskKJson", e.getMessage());
		}

		return response;
	}

	public HttpResponse sendParamPostRequest(String service,
			List<NameValuePair> nameValuePairs) {

		// Response
		HttpResponse response = null;

		// Create a new HttpClient and Post Header
		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(url + service);

		try {
			// Add data
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			response = httpClient.execute(httppost);

		} catch (UnsupportedEncodingException e) {

			Log.i("AsyncTaskKJson", e.getMessage());

		} catch (ClientProtocolException e) {

			Log.i("AsyncTaskKJson", e.getMessage());

		} catch (IOException e) {

			Log.i("AsyncTaskKJson", e.getMessage());
		}

		return response;
	}
}
