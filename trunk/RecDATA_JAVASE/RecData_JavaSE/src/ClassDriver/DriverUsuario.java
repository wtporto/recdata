package ClassDriver;

import java.util.ArrayList;

import entity.Usuarios.Monitor;
import entity.Usuarios.Usuario;

public class DriverUsuario {

	public static void main(String[] args) {
		
		// criar usuarios do tipo Monitor;
		
		Usuario m1 = new Monitor(); 
		Usuario m2 = new Monitor();
		Usuario m3 = new Monitor();
		m1.setTipoCadastroUsuario();
		m1.cadastroUsuario.entradaDadoUsuario();
						
		ArrayList<Usuario> lista = new ArrayList();
		lista.add(m1);
		System.out.println(lista.toString());
	}
}
