package entity.Usuarios;

import br.edu.ifpb.Funcionaidades.CadastroUsuarios.CadastrarUsuarioMonitor;
import br.edu.ifpb.excecoes.IdadeInvalidaException;
import br.edu.ifpb.excecoes.NomeInvalidoException;
import br.edu.ifpb.excecoes.SenhaInvalidaException;
import br.edu.ifpb.excecoes.TamanhoInvalidoException;

public class Monitor extends Usuario {


	String serieMonitor;
	String cursoMonitor;
	String disciplinaMonitor;
	
	public Monitor() {
		// TODO Auto-generated constructor stub
	}

	public Monitor(String nomeUsuario, int idadeUsuario, String emailUsuario,
			long telefoneUsuario, char sexoUsuario,String senhaUsuario ,String serieMonitor, String cursoMonitor, 
			String disciplinaMonitor)
			throws NomeInvalidoException, TamanhoInvalidoException,
			IdadeInvalidaException, SenhaInvalidaException {
		super(nomeUsuario, idadeUsuario, emailUsuario, telefoneUsuario,
				sexoUsuario,senhaUsuario);
		setSerieMonitor(serieMonitor);
		setCursoMonitor(cursoMonitor);
		setDisciplicaMonitor(disciplinaMonitor);
		}

	public String getSerieMonitor() {
		return serieMonitor;
	}

	public void setSerieMonitor(String serieMonitor) throws TamanhoInvalidoException{
		 if (serieMonitor.length() > 10) { 
				new TamanhoInvalidoException();
			}else
				this.serieMonitor = serieMonitor;
	}

	public String getCursoMonitor() {
		return cursoMonitor;
	}

	public void setCursoMonitor(String cursoMonitor) throws TamanhoInvalidoException {
		 if (cursoMonitor.length() > 20) {
				new TamanhoInvalidoException();
			}else
		this.cursoMonitor = cursoMonitor;
	}

	public String getDisciplicaMonitor() {
		return disciplinaMonitor;
	}

	public void setDisciplicaMonitor(String disciplinaMonitor)throws TamanhoInvalidoException{
		 if (disciplinaMonitor.length() > 20) {
				new TamanhoInvalidoException();
			}else
		this.disciplinaMonitor = disciplinaMonitor;
	}

	@Override
	public String toString() {
		return super.toString()+"Monitor [serieMonitor=" + serieMonitor + ", cursoMonitor="
				+ cursoMonitor + ", disciplinaMonitor=" + disciplinaMonitor
				+ "]";
	}

	public void setTipoCadastroUsuario() {
		cadastroUsuario = new CadastrarUsuarioMonitor();
	}
	
	

}
