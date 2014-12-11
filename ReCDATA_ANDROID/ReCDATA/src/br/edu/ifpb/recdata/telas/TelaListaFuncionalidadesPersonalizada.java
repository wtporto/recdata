package br.edu.ifpb.recdata.telas;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.servicos.BuscaReservaAsyncTask;
import br.edu.ifpb.recdata.util.GlobalState;
import br.edu.ifpb.recdata.util.Model;
import br.edu.ifpb.recdata.util.ModelAdapter;

public class TelaListaFuncionalidadesPersonalizada extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_main);

		ArrayList<Model> itens = new ArrayList<Model>();

		Model minhasReservasModel = new Model();
		minhasReservasModel.setNome("Minhas Reservas");
		Model reservarItemModel = new Model();
		reservarItemModel.setNome("Reservar Item");
		Model reservaQrCodeModel = new Model();
		reservaQrCodeModel.setNome("Reservar via QR CODE");
		Model voltarModel = new Model();
		voltarModel.setNome("Voltar");

		itens.add(minhasReservasModel);
		itens.add(reservarItemModel);
		itens.add(reservaQrCodeModel);
		itens.add(voltarModel);

		ListView listView = (ListView) findViewById(R.id.lv);
		listView.setAdapter(new ModelAdapter(this, itens));

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
	                  int position, long id) {
				Intent intent;

				switch (position) {
				case 0:
					GlobalState gs = (GlobalState) getApplication();

					JSONObject usuarioJsonObject = new JSONObject();
					try {
						usuarioJsonObject.put("id", gs.getUsuario().getId());
					} catch (JSONException e) {
						e.printStackTrace();
					}

					BuscaReservaAsyncTask buscareservarAsyncTask = new BuscaReservaAsyncTask(
							TelaListaFuncionalidadesPersonalizada.this);
					buscareservarAsyncTask.execute(usuarioJsonObject);

					break;

				case 1:
					intent = new Intent(getBaseContext(),
							TelaConsultarItem.class);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent(getBaseContext(), TelaQrCode.class);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent(getBaseContext(), TelaLogin.class);
					startActivity(intent);
					break;
				default:
					finish();
				}

			}

		});

	}

}
