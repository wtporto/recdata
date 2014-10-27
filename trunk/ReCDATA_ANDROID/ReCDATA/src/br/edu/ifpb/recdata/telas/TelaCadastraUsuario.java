package br.edu.ifpb.recdata.telas;

import java.util.ArrayList;
import java.util.List;

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
import br.edu.ifpb.recdata.entity.Usuario;

public class TelaCadastraUsuario extends Activity implements OnClickListener {

	private List<String> itens_user = new ArrayList<String>();

	//atributos para montar o usuario e envia para o servidor
	EditText nome;
	EditText cpf;
	EditText login;
	EditText senha;
	DatePicker dataNascimento;
	EditText endereco;
	EditText telefone;
	EditText email;
	RadioGroup sexo;
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

	public void ativarSpinner(Spinner generico, List<String> itens_genericos) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itens_genericos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		generico.setAdapter(adapter);
		generico.setSelection(0);

		generico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	/*
	 * "usuarioId":2, "nomeUsuario":"wemerson Thayne",
	 * "emailUsuario":"wemerson@thayne.com", "telefoneUsuario":"11278944",
	 * "idadeUsuario":"1986-08-10", "sexoUsuario":"M", "cpfUsuario":"491456200",
	 * "enderecoUsuario":"Rua dos desenvolvedores", "loginUsuario":"wemersont",
	 * "senhaUsuario":null, "idTipoUsuario":2, "descricao_tipoUsuario":null
	 */

	public void capturavalor(){
		
		radioGroup1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		    @Override
		    public void onCheckedChanged(RadioGroup group, int checkedId) {
		      switch(checkedId) {
		        case R.id.radioValor1:
		          if (checked)
		                // trata radioValor1
		          break;
		        case R.id.radioValor2:
		          if (checked)
		                // trata radioValor2
		          break;
		      }           
		    }
		  });
	}
	public Usuario montarObjetoUsuario() {

		Usuario usuarioMontar= new Usuario();
		return usuarioMontar;

	}

	public JSONObject montarObjetoJSON() {

		JSONObject jsonObject = null;

		//montar a data
		   int dataNascAno = dataNascimento.getYear();
		   int dataNascMes = dataNascimento.getMonth();
		   int dataNascDia = dataNascimento.getDayOfMonth();
		   
		   String dataNascimentoCompleto = String.valueOf(dataNascAno)
				   +"-"+String.valueOf(dataNascMes)+
				   "-"+String.valueOf(dataNascDia);
		   
		 //==========================================
		jsonObject.put("nomeUsuario",nome.getText().toString() );
		
		jsonObject.put("emailUsuario", email.getText().toString());
		
		jsonObject.put("telefoneUsuario",telefone.getText().toString());
		
		jsonObject.put("idadeUsuario",dataNascimentoCompleto);
		
		jsonObject.put("sexoUsuario", sexo.get);
		
		
		jsonObject.put(name, value)
		
		jsonObject.put(name, value)
		jsonObject.put(name, value)
		jsonObject.put(name, value)
		jsonObject.put(name, value)
		
		return jsonObject;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
