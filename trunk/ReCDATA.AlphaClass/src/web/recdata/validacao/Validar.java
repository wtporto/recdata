package web.recdata.validacao;

import br.edu.ifpb.recdata.entidades.Item;

public class Validar {

	public static int VALIDACAO_OK = 0;

	public static int validarItem(Item item) {
		if (item.getCategoria() == null || item.getDescricao() == null
				|| item.getRegiao() == null) {
			return 1;
		}
		return 0;
	}

	public static int validarLogin() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int validarUsuario() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int validarRetirada() {
		// TODO Auto-generated method stub
		return 0;
	}
}
