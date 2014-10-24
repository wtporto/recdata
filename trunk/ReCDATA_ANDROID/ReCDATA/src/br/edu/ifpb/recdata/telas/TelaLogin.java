package br.edu.ifpb.recdata.telas;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.servicos.UsuarioLoginAsyncTask;
import br.edu.ifpb.recdata.util.*;

public class TelaLogin extends Activity implements OnClickListener {

	EditText login;
	EditText senha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_login);

		login = (EditText) findViewById(R.id.LoginApp);
		senha = (EditText) findViewById(R.id.SenhaApp);

		final Button logarbutton = (Button) findViewById(R.id.LoginButton);
		logarbutton.setOnClickListener(this);

		final TextView txtCadastrar = (TextView) findViewById(R.id.textview_cadastrar);

		txtCadastrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent chamarCadastro = new Intent(TelaLogin.this,
						TelaCadastraUsuario.class);
				startActivity(chamarCadastro);
				finish();
			}
		});

	}

	private JSONObject logarUsuario() {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("loginUsuario", login.getText());
			jsonObject.put("senhaUsuario", senha.getText());

		} catch (JSONException e) {
			Log.e("ReCDATA", e.getMessage());
			// Toast para o usuário com erro mais suave.
		}
		return jsonObject;
	}

	@Override
	public void onClick(View arg0) {

		if ((Metodos.ValidarCampoEditText(login) == true)
				&& (Metodos.ValidarCampoEditText(senha) == true)) {
			
			
			JSONObject jsonObject = logarUsuario();
			UsuarioLoginAsyncTask usuarioLogar = new UsuarioLoginAsyncTask(this);
			usuarioLogar.execute(jsonObject);

		} else if ((Metodos.ValidarCampoEditText(login) == false)
				&& (Metodos.ValidarCampoEditText(senha) == false)) {

			

		}
	}
}
