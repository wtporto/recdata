package br.edu.ifpb.recdata.model;

import java.io.InputStream;

import br.edu.ifpb.recdata.entidades.Item;

public class ItemRelatorio extends Item{

	private String nome;
	
	private int idItem;
	
	private InputStream qrCode;

	public ItemRelatorio(String nome, Integer idItem) {
		this.nome = nome;
		this.idItem = idItem;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public InputStream getQrCode() {
		return qrCode;
	}

	public void setQrCode(InputStream qrCode) {
		this.qrCode = qrCode;
	}
}
