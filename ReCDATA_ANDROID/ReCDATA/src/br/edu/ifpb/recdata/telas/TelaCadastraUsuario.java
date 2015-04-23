package br.edu.ifpb.recdata.telas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.entity.TipoUsuario;
import br.edu.ifpb.recdata.listener.CadastroUsuarioListener;
import br.edu.ifpb.recdata.servicos.CadastraUsuarioAsyncTask;
import br.edu.ifpb.recdata.servicos.PreencherSpinnerTipoUsuarioAsyncTask;
import br.edu.ifpb.recdata.util.Constantes;
import br.edu.ifpb.recdata.util.Mascara;
import br.edu.ifpb.recdata.util.Validacao;
import br.edu.ifpb.recdata.widgets.DatePickerDialogAdapter;

public class TelaCadastraUsuario extends Activity implements OnClickListener {

	private List<TipoUsuario> tiposUsuarios = new ArrayList<TipoUsuario>();

	// atributos para montar o usuario e envia para o servidor
	private EditText nome;
	private EditText cpf;
	private EditText login;
	private EditText senha;
	private EditText dataNascimento;
	private EditText endereco;
	private EditText telefone;
	private EditText email;
	private RadioGroup sexoRadio;
	private Spinner tipoUsuarioSpinner;
	private int idtipoUsuario;

	private DatePickerDialog dataNascimentoPickerDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrousuario);

		getViews();
		setDatePickerDialog();
		carregarTiposUsuarios();
		addMascaras();
		Button Cadastrar = (Button) findViewById(R.id.criar_conta);
		Cadastrar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		removeMascarar(cpf);
		removeMascarar(telefone);
		if (validateAll()) {
			JSONObject jsonObject = montarObjetoJSON();
			Log.i("RecDATA - Objeto Usuário", jsonObject.toString());
			CadastraUsuarioAsyncTask cadastrar = new CadastraUsuarioAsyncTask(
					this);
			cadastrar.execute(jsonObject);
		}
	}

	private void carregarTiposUsuarios() {

		PreencherSpinnerTipoUsuarioAsyncTask preencherSpinner = new PreencherSpinnerTipoUsuarioAsyncTask(
				this);
		tipoUsuarioSpinner = (Spinner) findViewById(R.id.tipousuario);

		try {
			tiposUsuarios = preencherSpinner.execute().get();
			List<String> tipoUsuarioAux;
			Log.i("ReCDATA - ", tiposUsuarios.toString());
			if (tiposUsuarios != null) {
				tipoUsuarioAux = new ArrayList<String>();
				tipoUsuarioAux.add("Selecione..");
				for (int i = 0; i < tiposUsuarios.size(); i++) {
					tipoUsuarioAux.add(tiposUsuarios.get(i).getDescricao());
				}

				this.ativarSpinner(tipoUsuarioSpinner, tipoUsuarioAux);

			} else {
				Toast.makeText(
						TelaCadastraUsuario.this.getApplicationContext(),
						Constantes.ERROR_TIPOUSUARIO_NULL, Toast.LENGTH_SHORT)
						.show();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
					// chamar Validação
				}
				if (arg2 == 1) {
					idtipoUsuario = 1;
				}
				if (arg2 == 1) {
					idtipoUsuario = 2;
				}
				if (arg2 == 2) {
					idtipoUsuario = 3;
				}
				if (arg2 == 3) {
					idtipoUsuario = 4;
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
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

	public void addMascaras() {
		// Adicionar Mascara no campo Cpf e telefone
		cpf.addTextChangedListener(Mascara.insert("###.###.###-##", cpf));
		telefone.addTextChangedListener(Mascara.insert("(##)####-####",
				telefone));
	}

	public void removeMascarar(EditText campo) {
		String s;
		s = campo.getText().toString();
		Mascara.unmask(s);
	}

	public JSONObject montarObjetoJSON() {

		JSONObject usuarioJsonObject = new JSONObject();

		try {
			usuarioJsonObject.put("nome", nome.getText().toString());
			usuarioJsonObject.put("email", email.getText().toString());
			usuarioJsonObject.put("telefone", telefone.getText().toString());
			usuarioJsonObject.put("nascimento",
					getValorEditTextDataNascimento());
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
		this.dataNascimento = (EditText) findViewById(R.id.dataNascimento);
		this.endereco = (EditText) findViewById(R.id.endereco);
		this.telefone = (EditText) findViewById(R.id.telefone);
		this.email = (EditText) findViewById(R.id.email);
		this.sexoRadio = (RadioGroup) findViewById(R.id.sexo);
		this.tipoUsuarioSpinner = (Spinner) findViewById(R.id.tipousuario);
	}

	public EditText getEditTextDataNascimento() {
		return dataNascimento;
	}

	public String getValorEditTextDataNascimento() {
		return dataNascimento.getText().toString();
	}

	public DatePickerDialog getDataNascimentoPickerDialog() {
		return dataNascimentoPickerDialog;
	}

	private void setDatePickerDialog() {
		CadastroUsuarioListener listener = new CadastroUsuarioListener(this);

		dataNascimento.setOnClickListener(listener);

		DatePickerDialogAdapter dataNascimentoDatePicker = new DatePickerDialogAdapter(
				this, dataNascimento);
		dataNascimentoDatePicker.setTitleDate("Data de Nascimento");
		dataNascimentoPickerDialog = dataNascimentoDatePicker.builder();

	}

	public boolean validateAll() {
		if (Validacao.validaNome(nome))
			if (Validacao.ValidarCPF(cpf))
				if (Validacao.validaCampo(login))
					if (Validacao.validarSpinner(tipoUsuarioSpinner
							.getSelectedItem().toString(),
							getApplicationContext()))
						if (Validacao.ValidarEndereco(endereco))
							if (Validacao.validaCampo(dataNascimento))
								if (Validacao.validaCampo(telefone))
									if (Validacao.validaEmail(email))

										if (Validacao.validaSenha(senha, senha))

											return true;

		return false;
	}

}
