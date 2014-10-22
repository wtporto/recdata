package br.edu.ifpb.recdata.telas;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.entity.Item;
import br.edu.ifpb.recdata.entity.ReservaItem;
import br.edu.ifpb.recdata.servicos.BuscaItensServidorAsyncTask;
import br.edu.ifpb.recdata.servicos.ReservarAsyncTask;

public class TelaReservar extends Activity implements OnClickListener {

	Item itemBundle = null;
	ReservaItem reserva = null;

	TextView descItem;

	TextView idItem;

	TextView descCatItem;

	TextView idCatItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reserva_item);

		Intent intent = getIntent();
		Bundle paramsBundle = intent.getExtras();

		itemBundle = (Item) paramsBundle.getSerializable("Item");

		Log.i("RecDATA", "Item  ->" + paramsBundle.toString());

		descItem = (TextView) findViewById(R.id.txview_descItem);
		descItem.setText(itemBundle.getDescricaoItem());

		idItem = (TextView) findViewById(R.id.txview_idItem);
		idItem.setText(String.valueOf(itemBundle.getIdItem()));

		descCatItem = (TextView) findViewById(R.id.txview_descCatItem);
		descCatItem.setText(itemBundle.getDescricaoCategoria());

		idCatItem = (TextView) findViewById(R.id.txview_idCatItem);
		idCatItem.setText(String.valueOf(itemBundle.getIdCategoria()));

		ImageView imagenIcon = (ImageView) findViewById(R.id.imgview_iconReservaItem);
		imagenIcon.setImageResource(itemBundle.getImagem(itemBundle.getIdCategoria())) ;
		
		Button buscabutton = (Button) findViewById(R.id.buttonReserva);
		buscabutton.setOnClickListener(this);
		
		//Button voltaListaItens = (Button) findViewById(R.id.buttonVoltarLista);
		//voltaListaItens.setOnClickListener(BuscaItensServidor.class);
		

	}

	private JSONObject montarJsonReserva() {

		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject();
			jsonObject.put("itemIdReserva", itemBundle.getIdItem());
			jsonObject.put("usuarioIdReserva", 1);
			Date date = new Date();
			jsonObject.put("horaDataInicioReserva", date.getDate());
		} catch (JSONException e) {
			Log.e("RecDATA", e.getMessage());
		}

		return jsonObject;
	}

	@Override
	public void onClick(View arg) {
		ReservarAsyncTask reservarAsyncTask = new ReservarAsyncTask(this);
		JSONObject jsonObsect = montarJsonReserva();
		reservarAsyncTask.execute(jsonObsect);
	}
}
