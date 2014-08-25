package web.recdata.model;



public class Datashow extends Item {
	
	private String descricaoDatashow;
	
	public Datashow(int itemId) {
		super(itemId);
		// TODO Auto-generated constructor stub
	}

	public Datashow(){
		
		
	}
	public Datashow(boolean status, String descricao) {
		super(status);
		setDescricaoDatashow(descricao);
	}

	public String getDescricaoDatashow() {
		return descricaoDatashow;
	}

	public void setDescricaoDatashow(String descricaoDatashow) {
		this.descricaoDatashow = descricaoDatashow;
	}

	@Override
	public String toString() {
		return super.toString()+" Datashow [descricaoDatashow=" + descricaoDatashow + "]";
	}

	
}
