package br.edu.ifpb.recdata.entity;

import java.io.Serializable;

import br.edu.ifpb.R;

public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String descricao;

	private Categoria categoria;

	private Regiao regiao;

	private String registro;

	public Item() {
	}

	/**
	 * Construtor pra readById.
	 * 
	 * @param idCategoria
	 */
	public Item(int idCategoria) {
		categoria = new Categoria();
		categoria.setId(idCategoria);
	}

	/**
	 * Construtor pra creat.
	 * 
	 * @param idCategoria
	 * @param descricaoItem
	 */
	public Item(int idCategoria, String descricao) {
		categoria = new Categoria();
		categoria.setId(idCategoria);
		setDescricao(descricao);

	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", descricao=" + descricao + ", categoria="
				+ categoria + ", regiao=" + regiao + ", registro=" + registro
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}

	public int getImagem(int idcategoria) {
		switch (idcategoria) {
		case 1:
			return (R.drawable.icon_datashow);
		case 2:
			return (R.drawable.icon_lab);
		case 3:
			return (R.drawable.icon_chave);
		case 4:
			return (R.drawable.icon_caixasom);
		case 5:
			return (R.drawable.icon_notebook);
		case 6:
			return (R.drawable.icon_tv);

		default:
			return (R.drawable.icon_errodefault);
		}
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}
}
