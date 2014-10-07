package br.edu.ifpb.recdata.telas;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.entity.Usuario;

public class TelaCadastraUsuario extends Activity{

	 private Usuario user = new Usuario(){};
     private Spinner tipoUsuario;
     private List<String> itens_user = new ArrayList<String>();

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
}
