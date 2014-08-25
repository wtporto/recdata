package entidades;

public class Monitor extends Usuario implements Entidade {

	private String serieMonitor;
	private String cursoMonitor;
	private String  disciplinaMonitor;
	
	
	public Monitor(int usuarioId) {
		super(usuarioId);

	}

	public Monitor(String nome, String email, String telefone,
			int idade, String sexo, String cpf, String endereco, String login,
			String senha, String serie, String curso, String disciplina) {
		super(nome, email, telefone, idade, sexo, cpf, endereco,
				login, senha);
		setSerieMonitor(serie);
		setCursoMonitor(curso);
		setDisciplinaMonitor(disciplina);
	}

	public String getSerieMonitor() {
		return serieMonitor;
	}

	public void setSerieMonitor(String serieMonitor) {
		this.serieMonitor = serieMonitor;
	}

	public String getCursoMonitor() {
		return cursoMonitor;
	}

	public void setCursoMonitor(String cursoMonitor) {
		this.cursoMonitor = cursoMonitor;
	}

	public String getDisciplinaMonitor() {
		return disciplinaMonitor;
	}

	public void setDisciplinaMonitor(String disciplinaMonitor) {
		this.disciplinaMonitor = disciplinaMonitor;
	}

	@Override
	public String toString() {
		return super.toString()+"   Monitor [serieMonitor=" + serieMonitor + ", cursoMonitor="
				+ cursoMonitor + ", disciplinaMonitor=" + disciplinaMonitor
				+ "]";
	}
	
	

}
