package br.edu.ifpb.recdata.util;

import android.widget.EditText;

public class Metodos {

	private static String ErroPreenche= "CAMPO NÃO PREENCHIDO";
	
	public static boolean ValidarCampoEditText(EditText campo){
		
		 boolean verificarConteudo = false;
		
		 if ((campo.getText().toString().trim().equals(""))||(campo.getText().toString().equals(null))) {
			campo.setError("Campo Não Preenchido!");
			campo.setFocusable(true);
			campo.setHintTextColor(12);
			verificarConteudo = false;
		}else{
			verificarConteudo=true;
		    campo.setFocusable(false);
		}
		return verificarConteudo;
		
	}
	
    
    public static boolean validaCampo(EditText campo) {
            if ((campo.getText().toString().trim().equals(""))
                            || (campo.getText().toString().equals(null))) {
                    campo.setError("Campo Não Preenchido!");
                    campo.setFocusable(true);
                    campo.requestFocus();
                    return false;
            }
            return true;
    }

    public static boolean validaSenha(EditText senha1, EditText senha2) {
            if (!(senha1.getText().toString().equals(senha2.getText().toString()))) {
                    senha2.setError("As senhas não correspondem");
                    senha2.setFocusable(true);
                    senha2.requestFocus();
                    return false;
            }
            return true;
    }
}
