package br.edu.ifpb.recdata.util;

import br.edu.ifpb.R;

public class Model {

	private String nome;

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getImagem(int position) {
		switch (position) {
		case 0:
			 return (R.drawable.icon_consultas);
		case 1:
			return (R.drawable.icon_reservar);
		case 2:
			return(R.drawable.icon_qrcode);
		case 3:
			return (R.drawable.icon_feedback);
			
		case 4:
			return (R.drawable.icon_seta);
		default:
			return (R.drawable.icon_errodefault);
		}
		
	}

	
	
}
