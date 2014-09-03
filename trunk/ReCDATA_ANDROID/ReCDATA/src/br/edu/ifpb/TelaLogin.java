package br.edu.ifpb;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TelaLogin extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
   
       final Button logarbutton = (Button) findViewById(R.id.LoginButton);
        logarbutton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				EditText login =(EditText) findViewById(R.id.LoginApp);
				String nomeUsuario = login.getText().toString();
				System.out.println("Nome Login:"+nomeUsuario);
				
			    Toast.makeText(getApplicationContext(),"Bem Vindo "+nomeUsuario, Toast.LENGTH_LONG).show(); 				 
			    Intent chamarLista = new Intent(TelaLogin.this, TelaListaFuncionalidadesPersonalizada.class);
			    startActivity(chamarLista);
			    finish();
			}
		});
        
        
        final TextView txtCadastrar =(TextView) findViewById(R.id.textview_cadastrar);
        
        txtCadastrar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	 Intent chamarCadastro = new Intent(TelaLogin.this, TelaCadastraUsuario.class);
 			    startActivity(chamarCadastro);
 			    finish();
            }
        });
        
        
	}
   
}
