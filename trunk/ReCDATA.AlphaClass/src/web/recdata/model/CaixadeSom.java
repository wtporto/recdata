package web.recdata.model;


public class CaixadeSom extends Item implements Entidade {

	private String descricaoCaixaSom;

	//construtor de leitura de dados via banco
	public CaixadeSom(int itemIdCaixaSom) {
		super(itemIdCaixaSom);
		
	}

	public CaixadeSom(){
		
	}
	
	public CaixadeSom(boolean status, String descricao) {
		super(status);
		setDescricaoCaixaSom(descricao);
		
	}

	public String getDescricaoCaixaSom() {
		return descricaoCaixaSom;
	}

	public void setDescricaoCaixaSom(String descricaoCaixaSom) {
		this.descricaoCaixaSom = descricaoCaixaSom;
	}

	@Override
	public String toString() {
		return super.toString()+"  CaixadeSom [descricaoCaixaSom=" + descricaoCaixaSom + "]";
	}
	
	

}
