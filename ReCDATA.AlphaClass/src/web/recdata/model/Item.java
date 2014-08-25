package web.recdata.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item implements Entidade {

	private int idItem;
	private boolean statusItem;

	public Item(){
		
	}
	
	// construtor pra readById
	public Item(int itemId) {
		setIdItem(itemId);
	}

	// onstrutor pra creat
	public Item(boolean status) {
		setStatusItem(status);
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public boolean getStatusItem() {
		return statusItem;
	}

	public void setStatusItem(boolean statusItem) {
		this.statusItem = statusItem;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + idItem + ", statusItem=" + statusItem + "]";
	}

}
