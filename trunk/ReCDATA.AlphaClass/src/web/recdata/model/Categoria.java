package web.recdata.model;

public class Categoria {

	private int idCategoria;
	private String descricao_categoria;
	
	public Categoria(int id) {
		setIdCategoria(id);
	}
	public Categoria(){
		
	}

	public Categoria(int id, String categoria){
		
		setIdCategoria(id);
		setDescricao_categoria(categoria);
	}
	
	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescricao_categoria() {
		return descricao_categoria;
	}

	public void setDescricao_categoria(String descricao_categoria) {
		this.descricao_categoria = descricao_categoria;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria
				+ ", descricao_categoria=" + descricao_categoria + "]";
	}
	
}
