package br.edu.ifpb.recdata.telas;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_cadastrousuario);

		carregarTiposUsuarios();
		
		Button Cadastrar = (Button) findViewById(R.id.criar_conta);
		Cadastrar.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		
		getViews();
		JSONObject jsonObject = montarObjetoJSON();
		CadastraUsuarioAsyncTask cadastrar = new CadastraUsuarioAsyncTask(this);
		cadastrar.execute(jsonObject);
	}

	private void carregarTiposUsuarios() {
		
		// Opções de usuario
		itens_user.add("Selecione...");
		itens_user.add("Professor");
		itens_user.add("Servidor");
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
	}

	public String capturavalor() {

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

	public String montarHoraPicker() {
		// montar a data
		int dataNascAno = dataNascimento.getYear();
		int dataNascMes = dataNascimento.getMonth();
		int dataNascDia = dataNascimento.getDayOfMonth();

		String dataNascimentoCompleto = String.valueOf(dataNascAno) + "-"
				+ String.valueOf(dataNascMes) + "-"
				+ String.valueOf(dataNascDia);

		return dataNascimentoCompleto;
	}

	public JSONObject montarObjetoJSON() {

		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("nomeUsuario", nome.getText().toString());
			jsonObject.put("emailUsuario", email.getText().toString());
			jsonObject.put("telefoneUsuario", telefone.getText().toString());
			jsonObject.put("idadeUsuario", montarHoraPicker());
			jsonObject.put("sexoUsuario", capturavalor());
			jsonObject.put("cpfUsuario", cpf.getText().toString());
			jsonObject.put("enderecoUsuario", endereco.getText().toString());
			jsonObject.put("loginUsuario", login.getText().toString());
			jsonObject.put("senhaUsuario", senha.getText().toString());
			jsonObject.put("idTipoUsuario", 2);
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject;
	}
	
	private void getViews() {
		//TODO Pegar todos os campos com o Id definido no layout.
		this.nome = (EditText) findViewById(R.id.nome_completo);
		//...
	}
}
