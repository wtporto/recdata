package br.edu.ifpb.recdata.telas;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.entity.Item;
import br.edu.ifpb.recdata.entity.ReservaItem;
import br.edu.ifpb.recdata.util.ReservaAdapter;

public class TelaResultadosReserva extends Activity {

	private ArrayList<ReservaItem> reservas = new ArrayList<ReservaItem>();

	private ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_reserva_busca);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();

		JSONArray reservasJsonArray;
		try {

			reservasJsonArray = new JSONArray(bundle.getString("reservas"));

			Log.i("Reservas:", reservasJsonArray.toString());
			for (int i = 0; i < reservasJsonArray.length(); i++) {
				ReservaItem reserva = new ReservaItem();
				Item item = new Item();

				JSONObject reservaJsonObject = reservasJsonArray
						.getJSONObject(i);

				reserva.setId(reservaJsonObject.getInt("id"));
				reserva.setHoraDataInicio(reservaJsonObject
						.getString("horaDataInicio"));
				reserva.setHoraDataFim(reservaJsonObject
						.getString("horaDataFim"));

				
				
				JSONObject itemJsonObject = reservaJsonObject
						.getJSONObject("item");
				item.setId(itemJsonObject.getInt("id"));
				reserva.setItem(item);

				this.reservas.add(reserva);
			}
		
		} catch (JSONException e) {
			Log.i("ReCDATA", e.getMessage());
		}

		listview = (ListView) findViewById(R.id.lv_reservaResultados);
		listview.setAdapter(new ReservaAdapter(TelaResultadosReserva.this,
				reservas));

	}
}
