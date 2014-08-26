package web.recdata.model;

public class Notebook extends Item  implements Entidade{

	private String descricaoNotebook;
	
	public Notebook(int itemIdNotebook) {
		super(itemIdNotebook);
	}

	public Notebook( boolean status, String descricao) {
		super(status);
		setDescricaoNotebook(descricao);
	}

	public String getDescricaoNotebook() {
		return descricaoNotebook;
	}

	public void setDescricaoNotebook(String descricaoNotebook) {
		this.descricaoNotebook = descricaoNotebook;
	}

	@Override
	public String toString() {
		return super.toString()+" Notebook [descricaoNotebook=" + descricaoNotebook + "]";
	}

	
}
