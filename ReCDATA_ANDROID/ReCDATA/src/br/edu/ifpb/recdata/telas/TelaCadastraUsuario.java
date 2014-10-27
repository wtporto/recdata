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
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.servicos.CadastraUsuarioAsyncTask;
import br.edu.ifpb.recdata.servicos.UsuarioLoginAsyncTask;

public class TelaCadastraUsuario extends Activity implements OnClickListener {

	private List<String> itens_user = new ArrayList<String>();

	// atributos para montar o usuario e envia para o servidor
	EditText nome;
	EditText cpf;
	EditText login;
	EditText senha;
	DatePicker dataNascimento;
	EditText endereco;
	EditText telefone;
	EditText email;
	RadioGroup sexoRadio;
	Spinner tipoUsuario;

	/*
	 * login_usuario,senha_usuario,nome_usuario,email_usuario,telefone_usuario,
	 * cpf_usuario," +
	 * "endereco_usuario,data_nasc_usuario,sexo_usuario,tb_tipousuario_idTipousuario
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_cadastrousuario);

		// Opções de usuario
		itens_user.add("Selecione...");
		itens_user.add("Professor");
		itens_user.add("Servidor");
		itens_user.add("Monitor");

		tipoUsuario = (Spinner) findViewById(R.id.tipousuario);
        
		this.ativarSpinner(tipoUsuario, itens_user);

		Button Cadastrar = (Button) findViewById(R.id.criar_conta);
		Cadastrar.setOnClickListener(this);

	}

/*	public String capturavalorTipoUsuario(){
	
		 final String descricaoUsuario;
		
		tipoUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
			
				descricaoUsuario = (String) parent.getItemAtPosition(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

			
		});	
		return descricaoUsuario;
	}
	*/
	public void ativarSpinner(Spinner generico, List<String> itens_genericos) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itens_genericos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		generico.setAdapter(adapter);
		generico.setSelection(0);

	
	}

	/*
	 * "usuarioId":2, "nomeUsuario":"wemerson Thayne",
	 * "emailUsuario":"wemerson@thayne.com", "telefoneUsuario":"11278944",
	 * "idadeUsuario":"1986-08-10", "sexoUsuario":"M", "cpfUsuario":"491456200",
	 * "enderecoUsuario":"Rua dos desenvolvedores", "loginUsuario":"wemersont",
	 * "senhaUsuario":null, "idTipoUsuario":2, "descricao_tipoUsuario":null
	 */

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

		// ==========================================

		return dataNascimentoCompleto;
	}

	public JSONObject montarObjetoJSON() {

		JSONObject jsonObject = null;

		try {
			jsonObject.put("nomeUsuario",nome.getText().toString() );
			jsonObject.put("emailUsuario", email.getText().toString());
			jsonObject.put("telefoneUsuario",telefone.getText().toString());
			jsonObject.put("idadeUsuario",montarHoraPicker());
			jsonObject.put("sexoUsuario",capturavalor());
			jsonObject.put("cpfUsuario", cpf.getText().toString());
			jsonObject.put("enderecoUsuario", endereco.getText().toString());
			jsonObject.put("loginUsuario", login.getText().toString());
			jsonObject.put("senhaUsuario", senha.getText().toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return jsonObject;

	}

	@Override
	public void onClick(View v) {

		JSONObject jsonObject = montarObjetoJSON();
		 CadastraUsuarioAsyncTask  cadastrar= new CadastraUsuarioAsyncTask(this);
		cadastrar.execute(jsonObject);

	}

}
