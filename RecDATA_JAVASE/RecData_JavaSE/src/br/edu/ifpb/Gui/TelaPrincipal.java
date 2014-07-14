package br.edu.ifpb.Gui;

import java.awt.EventQueue;




import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {
	
	
	private JPanel contentPane;
	
	public TelaPrincipal(){
		
		this.setSize(1000,600);
	    this.setLocationRelativeTo(null);
	    
	    
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
	    JLabel jLb = new JLabel("Testa");
	    jLb.setBounds(30, 15, 424, 14);
	    contentPane.add(jLb);
	    
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
	

}
