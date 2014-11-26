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
import br.edu.ifpb.recdata.entity.Usuario;
import br.edu.ifpb.recdata.util.ReservaAdapter;

public class TelaResultadosReserva extends Activity {
	
/*[{"id":1,"usuario":{"id":1,"nome":null,"email":null,"telefone":null,"nascimento":null,
 "sexo":null,"cpf":null,"endereco":null,"login":null,"senha":null,"tipoUsuario":null},
 "item":{"id":1,"registro":null},"observacao":null,"horaDataInicio":1593209512000,
  "horaDataFim":1593126832000,"dataRegistro":null}*/
	
	ArrayList<ReservaItem> reservas ;
	ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_reserva_busca);
	
		Intent intent = getIntent();
		Bundle bundle =intent.getExtras();
		
		JSONArray ReservasJsonArray;
		try {
		
			ReservasJsonArray = new JSONArray(
					bundle.getString("reservas"));

			Log.i("Reservas:", ReservasJsonArray.toString());
			for (int i = 0; i < ReservasJsonArray.length(); i++) {
			ReservaItem reserva = new ReservaItem();
				Usuario usuario = new Usuario();
				Item item = new Item();
				
				JSONObject reservaJsonObject = ReservasJsonArray.getJSONObject(i);
				
				
				reserva.setId(reservaJsonObject.getInt("id"));
				reserva.setObservacao(reservaJsonObject.getString("observacao"));
				reserva.setHoraDataInicio(reservaJsonObject.getString("horaDataInicio"));
				reserva.setHoraDataFim(reservaJsonObject.getString("horaDataFim"));
				
				JSONObject usuarioJsonObject = reservaJsonObject.getJSONObject("usuario");
                usuario.setId(usuarioJsonObject.getInt("id"));
                reserva.setUsuario(usuario);
                
                JSONObject itemJsonObject= reservaJsonObject.getJSONObject("item");
                item.setId(itemJsonObject.getInt("id"));
                reserva.setItem(item);
				
				reservas.add(reserva);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		listview = (ListView) findViewById(R.id.lv_reservaResultados);
		listview.setAdapter(new ReservaAdapter(TelaResultadosReserva.this, reservas));
 

	
	}
}
