package web.recdata.model;



public class Chave extends Item implements Entidade {

	private String localChave;
	private String descricaoChave;

	public Chave(int itemIdChave) {
		super(itemIdChave);
	}
	public Chave(){
	}

	public Chave(boolean status, String local, String descricao) {
		super(status);
		setLocalChave(local);
		setDescricaoChave(descricao);
	}

	public String getLocalChave() {
		return localChave;
	}

	public void setLocalChave(String localChave) {
		this.localChave = localChave;
	}

	public String getDescricaoChave() {
		return descricaoChave;
	}

	public void setDescricaoChave(String descricaoChave) {
		this.descricaoChave = descricaoChave;
	}

	@Override
	public String toString() {
		return super.toString() + "  Chave [localChave=" + localChave
				+ ", descricaoChava=" + descricaoChave + "]";
	}

}
