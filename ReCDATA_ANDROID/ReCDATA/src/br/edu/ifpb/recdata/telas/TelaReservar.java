package br.edu.ifpb.recdata.telas;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.edu.ifpb.R;
import br.edu.ifpb.R.drawable;
import br.edu.ifpb.recdata.entity.Item;
import br.edu.ifpb.recdata.entity.ReservaItem;
import br.edu.ifpb.recdata.servicos.BuscaItensServidor;
import br.edu.ifpb.recdata.servicos.HttpService;
import br.edu.ifpb.recdata.servicos.HttpUtil;
import br.edu.ifpb.recdata.util.ItemAdapter;

public class TelaReservar extends Activity {

	private Intent intent;
	private Bundle paramsBundle;
	Item itemBundle;
	ReservaItem reserva;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reserva_item);

		reserva = new ReservaItem();
		reserva=null;
		itemBundle = new Item();
		itemBundle = null;
		
		intent = getIntent();
		paramsBundle = intent.getExtras();
		
		itemBundle = (Item) paramsBundle.getSerializable("Item");

		Log.i("Item  ->", paramsBundle.toString());

		final TextView descItem = (TextView) findViewById(R.id.txview_descItem);
		descItem.setText(itemBundle.getDescricaoItem());

		final TextView idItem = (TextView) findViewById(R.id.txview_idItem);
		descItem.setText(String.valueOf(itemBundle.getIdItem()));

		final TextView descCatItem = (TextView) findViewById(R.id.txview_descCatItem);
		descItem.setText(itemBundle.getDescricaoCategoria());

		final TextView idCatItem = (TextView) findViewById(R.id.txview_idCatItem);
		descItem.setText(String.valueOf(itemBundle.getIdCategoria()));

		
		final EditText horaInicio
		
		final ImageView imagenIcon = (ImageView) findViewById(R.id.imgview_iconReservaItem);

		switch (itemBundle.getIdCategoria()) {
		case 1:
			imagenIcon.setImageResource(drawable.icon_chave);
			break;

		case 2:
			imagenIcon.setImageResource(drawable.icon_caixasom);
			break;

		case 3:
			imagenIcon.setImageResource(drawable.icon_datashow);
			break;

		case 4:
			imagenIcon.setImageResource(drawable.icon_notebook);
			break;

		default:
			imagenIcon.setImageResource(drawable.icon_errodefault);
			break;
		}

		
		
		final Button buscabutton = (Button) findViewById(R.id.buttonReserva);
		buscabutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
		
				new Reservar();
			}
		});

	}
	
	private class Reservar extends AsyncTask<Void, Integer, JSONObject> {


		public Reservar() {

		}

		@Override
		protected JSONObject doInBackground(Void... params) {
			
		   
			JSONObject jsonObjectEnvia = null;
			try {
				jsonObjectEnvia = new JSONObject();
				jsonObjectEnvia.put("itemIdReserva", );
				jsonObjectEnvia.put("usuarioIdReserva",)
				jsonObjectEnvia.put("HoraDataReserva", );     
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			// Enviar a requisição HTTP via POST.
			HttpService httpService = new HttpService();
			HttpResponse response=null;

			try {
				response = httpService.sendJsonPostRequest("/reserva/criar",
							jsonObjectEnvia);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return jsonObjectEnvia;

		}

		@Override
		protected void onPostExecute(JSONObject result) {

			result.get

		}
	}

}
