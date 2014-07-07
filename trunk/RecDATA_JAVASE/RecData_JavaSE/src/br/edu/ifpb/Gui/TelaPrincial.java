package br.edu.ifpb.Gui;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TelaPrincial {

    
	
	public TelaPrincial(){
		
		JFrame telaPrincial =new JFrame();
		JButton butãoLogin = new JButton();
		JButton butãoCadastrar = new JButton();
	
		butãoLogin.setText("Login");
		butãoLogin.setSize(10,10);
		butãoCadastrar.setText("Cadastrar");
		
		telaPrincial.getContentPane().add(butãoLogin);
		telaPrincial.getContentPane().add(butãoCadastrar);
		telaPrincial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaPrincial.setSize(1000,600);
		telaPrincial.setVisible(true);
		
		
		
	}
	public static void main(String[] args) {
		new TelaPrincial();
	}
}
