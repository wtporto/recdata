package br.edu.ifpb.recdata.telas;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.entity.Item;
import br.edu.ifpb.recdata.entity.ReservaItem;
import br.edu.ifpb.recdata.listener.DataFimReservaListener;
import br.edu.ifpb.recdata.listener.DataInicioReservaListener;
import br.edu.ifpb.recdata.listener.HoraFimReservaListener;
import br.edu.ifpb.recdata.listener.HoraInicioReservaListener;
import br.edu.ifpb.recdata.servicos.ReservarAsyncTask;
import br.edu.ifpb.recdata.util.DatePickerDialogAdapter;
import br.edu.ifpb.recdata.util.GlobalState;
import br.edu.ifpb.recdata.util.TimePickerDialogAdapter;

public class TelaReservar extends Activity implements OnClickListener {

	Item itemBundle = null;
	ReservaItem reserva = null;

	EditText dataInicio;
	private DatePickerDialog dataInicioPickerDialog;

	EditText horaInicio;
	private TimePickerDialog horaInicioPickerDialog;

	EditText dataFim;
	private DatePickerDialog dataFimPickerDialog;

	EditText horaFim;
	private TimePickerDialog horaFimPickerDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reserva_item);
		getViews();
		setDatePickerDialog();
		setHoraPickerDialog();
		setDateFimPickerDialog();
		setHoraFimPickerDialog();

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
				Intent chamarTelaQr = new Intent(TelaReservar.this,
						TelaListaFuncionalidades.class);
				startActivity(chamarTelaQr);
				finish();
			}
		});

	}

	public void getViews() {
		this.dataInicio = (EditText) findViewById(R.id.dataInicio);
		this.horaInicio = (EditText) findViewById(R.id.horaInicio);
		this.dataFim = (EditText) findViewById(R.id.dataFim);
		this.horaFim = (EditText) findViewById(R.id.horaFim);

	}

	private JSONObject montarJsonReserva() {

		JSONObject reservaItemJson = null;
		JSONObject usuarioJson = null;
		JSONObject itemJson = null;

		try {

			GlobalState gs = (GlobalState) getApplication();

			reservaItemJson = new JSONObject();
			usuarioJson = new JSONObject();
			usuarioJson.put("id", gs.getUsuario().getId());

			itemJson = new JSONObject();

			itemJson.put("id", itemBundle.getId());

			
			reservaItemJson.put("usuario", usuarioJson);
			reservaItemJson.put("item", itemJson);
			// TODO: colocar o campo texto para inserir obseevação
			//reservaItemJson
		 reservaItemJson.put("horaDataInicio", getValorEditTextDataInicio()+" "+getValorEditTextHoraInicio());
			 reservaItemJson.put("horaDataFim",getValorEditTextDataFim()+" "+getValorEditTextHoraFim());
			 Log.i("RecDATA - ReservaJSON", reservaItemJson.toString());

		} catch (JSONException e) {
			Log.e("RecDATA", e.getMessage());
		}

		return reservaItemJson;
	}

	@Override
	public void onClick(View arg) {
		//TODO: colocar validação dos campos
		ReservarAsyncTask reservarAsyncTask = new ReservarAsyncTask(this);
		JSONObject jsonObsect = montarJsonReserva();
		reservarAsyncTask.execute(jsonObsect);
	}

	// Métodos para a Captura da Data Inicial da Reserva

	public EditText getEditTextDataInicio() {
		return dataInicio;
	}

	public String getValorEditTextDataInicio() {
		return dataInicio.getText().toString();
	}

	public DatePickerDialog getDataInicioPickerDialog() {
		return dataInicioPickerDialog;
	}

	private void setDatePickerDialog() {
		DataInicioReservaListener listener = new DataInicioReservaListener(this);

		dataInicio.setOnClickListener(listener);

		DatePickerDialogAdapter dataInicoDatePicker = new DatePickerDialogAdapter(
				this, dataInicio);
		dataInicioPickerDialog = dataInicoDatePicker.builder();

	}

	// Métodos para a Captura da Hora Inicial da Reserva
	// Métodos para a Captura da hora Inicial

	public EditText getEditTextHoraInicio() {
		return horaInicio;
	}

	public String getValorEditTextHoraInicio() {
		return horaInicio.getText().toString();
	}

	public TimePickerDialog getHoraInicioPickerDialog() {
		return horaInicioPickerDialog;
	}

	private void setHoraPickerDialog() {
		HoraInicioReservaListener listener = new HoraInicioReservaListener(this);

		horaInicio.setOnClickListener(listener);

		TimePickerDialogAdapter timeInicoDatePicker = new TimePickerDialogAdapter(
				this, horaInicio);
		horaInicioPickerDialog = timeInicoDatePicker.builder();

	}

	// Métodos para a Captura da Data Final da Reserva

	public EditText getEditTextDataFim() {
		return dataFim;
	}

	public String getValorEditTextDataFim() {
		return dataFim.getText().toString();
	}

	public DatePickerDialog getDataFimPickerDialog() {
		return dataFimPickerDialog;
	}

	private void setDateFimPickerDialog() {
		DataFimReservaListener listener = new DataFimReservaListener(this);

		dataFim.setOnClickListener(listener);

		DatePickerDialogAdapter dataFimDatePicker = new DatePickerDialogAdapter(
				this, dataFim);
		dataFimPickerDialog = dataFimDatePicker.builder();

	}

	// Métodos para a Captura da hora Final

	public EditText getEditTextHoraFim() {
		return horaFim;
	}

	public String getValorEditTextHoraFim() {
		return horaFim.getText().toString();
	}

	public TimePickerDialog getHoraFimPickerDialog() {
		return horaFimPickerDialog;
	}

	private void setHoraFimPickerDialog() {
		HoraFimReservaListener listener = new HoraFimReservaListener(this);

		horaFim.setOnClickListener(listener);

		TimePickerDialogAdapter timeFimDatePicker = new TimePickerDialogAdapter(
				this, horaFim);
		horaFimPickerDialog = timeFimDatePicker.builder();

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