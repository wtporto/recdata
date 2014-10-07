package br.edu.ifpb.recdata.entity;

import br.edu.ifpb.R;

public class Item  {
/*   Tabela Categoria  
 *    `IdCategoria` INT NOT NULL AUTO_INCREMENT,
	  `descricao_categoria` VARCHAR(45) NOT NULL,
	  
	  tabela item
			`idItem` INT NOT NULL AUTO_INCREMENT,
  			`tb_categoria_IdCategoria` INT NOT NULL,
  	`descricao_item` VARCHAR(60) NOT NULL,
  
*/
	private int idItem;
	private String descricaoItem;
	private int idCategoria;
	private String descricaoCategoria; 
	

	public Item(){
		
	}
	
	// construtor pra readById
	public Item(int itemId) {
		setIdItem(itemId);
	}

	// construtor pra creat
	public Item(String descricaoItem, int idCategoria, String descricaoCategora){
		
		setDescricaoItem(descricaoItem);
		setIdCategoria(idCategoria);
		setDescricaoCategoria(descricaoCategora);
		
	}
	
	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getDescricaoItem() {
		return descricaoItem;
	}

	public void setDescricaoItem(String descricaoItem) {
		this.descricaoItem = descricaoItem;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	@Override
	public String toString() {
		return "Item IdItem: "+ idItem + "Descrição do Item: " + descricaoItem
				+ "IdCategoria: " + idCategoria + "Descrição da Categoria:"
				+ descricaoCategoria;
	}
	
	public int getImagem(int idcategoria) {
		switch (idcategoria) {
		case 1:
			 return (R.drawable.icon_chave);
		case 2:
			return (R.drawable.icon_caixasom);
		case 3:
			return (R.drawable.icon_datashow);
			
		case 4:
			return (R.drawable.icon_notebook);
		default:
			return (R.drawable.icon_errodefault);
		}
	}
}
