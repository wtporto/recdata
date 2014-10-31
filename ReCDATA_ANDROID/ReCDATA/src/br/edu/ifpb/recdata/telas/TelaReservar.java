package br.edu.ifpb.recdata.telas;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.entity.Item;
import br.edu.ifpb.recdata.entity.ReservaItem;
import br.edu.ifpb.recdata.servicos.ReservarAsyncTask;
import br.edu.ifpb.recdata.util.GlobalState;
import br.edu.ifpb.recdata.util.Metodos;

public class TelaReservar extends Activity implements OnClickListener {

	Item itemBundle = null;
	ReservaItem reserva = null;

	DatePicker dataInicio;
	TimePicker horaInicio;

	DatePicker dataFim;
	TimePicker horaFim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reserva_item);
		getViews();
		
		
		Intent intent = getIntent();
		Bundle paramsBundle = intent.getExtras();

		itemBundle = (Item) paramsBundle.getSerializable("Item");

		Log.i("RecDATA", "Item  ->" + paramsBundle.toString());

		Button buscabutton = (Button) findViewById(R.id.buttonReserva);
		buscabutton.setOnClickListener(this);

		// Voltar intent.
		Button voltaListaItens = (Button) findViewById(R.id.buttonVoltarLista);
		voltaListaItens.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent chamarTelaQr = new Intent();
				startActivity(chamarTelaQr);
				finish();
			}
		});

	}


	public void getViews() {
		this.dataInicio = (DatePicker) findViewById(R.id.dataInicio);
		this.horaInicio = (TimePicker) findViewById(R.id.horaInicio);
		this.horaInicio.setIs24HourView(true);
		this.dataFim = (DatePicker) findViewById(R.id.dataFim);
		this.horaFim = (TimePicker) findViewById(R.id.horaFim);
		this.horaFim.setIs24HourView(true);
	}

	private JSONObject montarJsonReserva() {

		JSONObject jsonObject = null;
		try {
			
			GlobalState gs = (GlobalState) getApplication();
			
			jsonObject = new JSONObject();
			jsonObject.put("itemIdReserva", itemBundle.getIdItem());
			jsonObject.put("usuarioIdReserva", gs.getUsuario().getUsuarioId());
			jsonObject.put(
					"horaDataInicio",getDataHora(dataInicio.getYear(),
							dataInicio.getMonth(),dataInicio.getDayOfMonth(),
							horaInicio.getCurrentHour(),
							horaInicio.getCurrentMinute()));

			jsonObject.put(
					"horaDataFim", 
					getDataHora(dataFim.getYear(), dataFim.getMonth(),
							dataFim.getDayOfMonth(), horaFim.getCurrentHour(),
							horaFim.getCurrentMinute()));
		} catch (JSONException e) {
			Log.e("RecDATA", e.getMessage());
		}

		return jsonObject;
	}

	@Override
	public void onClick(View arg) {

		getViews();
		ReservarAsyncTask reservarAsyncTask = new ReservarAsyncTask(this);
		JSONObject jsonObsect = montarJsonReserva();
		reservarAsyncTask.execute(jsonObsect);
	}

	private Date getDataHora(int dia, int mes, int ano, int hora, int minuto) {

		Calendar dataHoraCalendar = Calendar.getInstance();
		dataHoraCalendar.add(Calendar.DAY_OF_MONTH, dia);
		dataHoraCalendar.add(Calendar.DAY_OF_MONTH, mes);
		dataHoraCalendar.add(Calendar.DAY_OF_MONTH, ano);
		dataHoraCalendar.add(Calendar.HOUR_OF_DAY, hora);
		dataHoraCalendar.add(Calendar.MINUTE, minuto);

		return dataHoraCalendar.getTime();
	}
}
