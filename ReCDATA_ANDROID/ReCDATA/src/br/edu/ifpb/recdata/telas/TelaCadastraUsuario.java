package br.edu.ifpb.recdata.telas;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.servicos.CadastraUsuarioAsyncTask;

public class TelaCadastraUsuario extends Activity implements OnClickListener {

	private List<String> itens_user = new ArrayList<String>();

	// atributos para montar o usuario e envia para o servidor
	private EditText nome;
	private EditText cpf;
	private EditText login;
	private EditText senha;
	private DatePicker dataNascimento;
	private EditText endereco;
	private EditText telefone;
	private EditText email;
	private RadioGroup sexoRadio;
	private Spinner tipoUsuario;
	private int idtipoUsuario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_cadastrousuario);

		carregarTiposUsuarios();
		getViews();

		Button Cadastrar = (Button) findViewById(R.id.criar_conta);
		Cadastrar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		JSONObject jsonObject = montarObjetoJSON();
		CadastraUsuarioAsyncTask cadastrar = new CadastraUsuarioAsyncTask(this);
		cadastrar.execute(jsonObject);
	}

	private void carregarTiposUsuarios() {

		// Opções de usuario
		itens_user.add("Selecione...");
		itens_user.add("Professor");
		itens_user.add("Gestor de Turno");
		itens_user.add("Monitor");

		tipoUsuario = (Spinner) findViewById(R.id.tipousuario);
		this.ativarSpinner(tipoUsuario, itens_user);
	}

	public void ativarSpinner(Spinner generico, List<String> itens_genericos) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itens_genericos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		generico.setAdapter(adapter);
		generico.setSelection(0);

		generico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (arg2 == 0) {
					Toast.makeText(
							TelaCadastraUsuario.this.getApplicationContext(),
							"Item Escolhido , Invalido!", Toast.LENGTH_SHORT)
							.show();
				}
				
				if (arg2 == 1) {
					idtipoUsuario = 1;
				}
				if (arg2 == 2) {
					idtipoUsuario = 2;
				}
				if (arg2 == 3) {
					idtipoUsuario = 3;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
	}

	public String capturavalorRadioGroup() {

		String sexoEscolhido = null;

		sexoRadio = (RadioGroup) findViewById(R.id.sexo);

		switch (sexoRadio.getCheckedRadioButtonId()) {
		case R.id.sexoMasculino:
			sexoEscolhido = "M";
			break;
		case R.id.sexoFeminino:
			sexoEscolhido = "F";
			break;
		default:
			break;
		}

		return sexoEscolhido;
	}

	public String montarHoraPicker(DatePicker datepicker) {
		// montar a data
		int dataNascAno = datepicker.getYear();
		int dataNascMes = datepicker.getMonth();
		int dataNascDia = datepicker.getDayOfMonth();

		String dataNascimentoCompleto = String.valueOf(dataNascAno) + "-"
				+ String.valueOf(dataNascMes) + "-"
				+ String.valueOf(dataNascDia);

		return dataNascimentoCompleto;
	}

	public JSONObject montarObjetoJSON() {

		JSONObject usuarioJsonObject = new JSONObject();

		try {
			usuarioJsonObject.put("nome", nome.getText().toString());
			usuarioJsonObject.put("email", email.getText().toString());
			usuarioJsonObject.put("telefone", telefone.getText().toString());
			usuarioJsonObject.put("nascimento", montarHoraPicker(dataNascimento));
			usuarioJsonObject.put("sexo", capturavalorRadioGroup());
			usuarioJsonObject.put("cpf", cpf.getText().toString());
			usuarioJsonObject.put("endereco", endereco.getText().toString());
			usuarioJsonObject.put("login", login.getText().toString());
			usuarioJsonObject.put("senha", senha.getText().toString());

			JSONObject tipoUsuarioJsonObject = new JSONObject();
			tipoUsuarioJsonObject.put("id", idtipoUsuario);
			
			usuarioJsonObject.put("tipoUsuario", tipoUsuarioJsonObject);
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuarioJsonObject;
	}

	private void getViews() {
		// TODO Pegar todos os campos com o Id definido no layout.
		this.nome = (EditText) findViewById(R.id.nome_completo);
		this.cpf = (EditText) findViewById(R.id.cpf);
		this.login = (EditText) findViewById(R.id.login);
		this.senha = (EditText) findViewById(R.id.senha);
		this.dataNascimento = (DatePicker) findViewById(R.id.dateNascimento);
		this.dataNascimento.init(1995, 11, 21, null);
		this.endereco = (EditText) findViewById(R.id.endereco);
		this.telefone = (EditText) findViewById(R.id.telefone);
		this.email = (EditText) findViewById(R.id.email);
		;
		this.sexoRadio = (RadioGroup) findViewById(R.id.sexo);
		this.tipoUsuario = (Spinner) findViewById(R.id.tipousuario);
	}
}
