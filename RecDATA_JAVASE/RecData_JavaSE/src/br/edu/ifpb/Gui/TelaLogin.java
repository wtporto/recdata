package br.edu.ifpb.Gui;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TelaLogin extends JFrame implements ActionListener{
	
	
	private JPanel contentPane;
	private JTextField textFieldLoginUsuario;
	private JPasswordField passwordFieldSenhaUsuario;

	public TelaLogin(){
		
		this.setSize(500,400);
	    this.setLocationRelativeTo(null);
	    this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Java\\JavaSE\\RecData_JavaSE\\Imagens\\ic_launcher.png"));
	    this.setTitle("Login");
	    this.setResizable(false);
	    

	    
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.getContentPane().setBackground(new Color(230,232,250)); 
		JLabel	jLbtituloInicial= new JLabel("Bem Vindo Ao RecData!");
		jLbtituloInicial.setBounds(30, 15, 424, 14);
		jLbtituloInicial.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		contentPane.add(jLbtituloInicial);
	    
		JLabel jbLLogin = new JLabel("Login:");
		jbLLogin.setFont(new Font("Century Gothic", Font.PLAIN, 14));
	    jbLLogin.setBounds(122, 68, 60, 24);
		contentPane.add(jbLLogin);
	    
		JLabel jLbSenha = new JLabel("Senha:");
		jLbSenha.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		jLbSenha.setBounds(122, 140, 60, 24);
		contentPane.add(jLbSenha);
		
		textFieldLoginUsuario = new JTextField();
		textFieldLoginUsuario.setBounds(227, 70, 125, 20);
		textFieldLoginUsuario.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		contentPane.add(textFieldLoginUsuario);
		textFieldLoginUsuario.setColumns(10);
		
		passwordFieldSenhaUsuario = new JPasswordField();
		passwordFieldSenhaUsuario.setBounds(227, 140, 125, 20);
		contentPane.add(passwordFieldSenhaUsuario);
		
		JButton btLogin = new JButton("Logar");
		btLogin.setBounds(122, 185, 80, 23);
		btLogin.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		contentPane.add(btLogin);
		
		JButton btLimpar = new JButton("Limpar");
		btLimpar.setBounds(240, 185, 80, 23);
		btLimpar.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		contentPane.add(btLimpar);
		
		JButton btCadastrar = new JButton("Cadastrar-se");
		btCadastrar.setBounds(169, 280, 125, 25);
		btCadastrar.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		contentPane.add(btCadastrar);
		
		
		btLogin.addActionListener(this);

	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void actionPerformed(ActionEvent arg0) {
		 
	
		 String nomeUsuario = textFieldLoginUsuario.getText();
		 JOptionPane.showMessageDialog(null,"Bem Vindo(a) "+nomeUsuario);
		 
		 new TelaPrincipal().setVisible(true);
		 this.dispose();//fecha a Jframe
		 
	}

}
