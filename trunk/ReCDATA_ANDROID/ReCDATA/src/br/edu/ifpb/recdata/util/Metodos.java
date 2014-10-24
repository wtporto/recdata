package br.edu.ifpb.recdata.util;

import android.widget.EditText;

public class Metodos {

	private static String ErroPreenche= "CAMPO NÃO PREENCHIDO";
	
	public static boolean ValidarCampoEditText(EditText campo){
		
		 boolean verificarConteudo = false;
		
		 if ((campo.getText().toString().trim().equals(""))||(campo.getText().toString().equals(null))) {
			campo.setError(ErroPreenche.toString());
			campo.setFocusable(true);
			campo.setHintTextColor(12);
			verificarConteudo = false;
		}else{
			verificarConteudo=true;
		    campo.setFocusable(false);
		}
		return verificarConteudo;
		
	}
}
