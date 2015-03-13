package br.edu.ifpb.recdata.util;

public class Constantes {

	// mensagens de Sucesso
	public static String USUARIO_CADASTRADO = "USU¡RIO CADASTRADO COM SUCESSO!";// troca por um dialog alert
	public static String RESERVA_CONCLUIDA = "RESERVA CONCLUIDO COM SUCESSO!";
	public static String ITEM_ENCONTRADO = "ITEM ENCONTRADO!";
	public static String RESERVA_ENCONTRADA = "RESERVA ENCONTRADO!";
	public static String EXISTE_CONEXAO = "CONEX√O ESTABELECIDA COM SUCESSO!";
	public static String USUARIO_EXISTE = "BEM VINDO(A) ";

	// mensagens de erros
	public static String USUARIO_NAO_CADASTRADO = "N√O FOI POSSIVEL CADASTRAR USU¡RIO!";
	public static String RESERVA_NAO_CONCLUIDA = "N√O FOI POSSIVEL FAZER A RESERVA!";
	public static String ITEM_NAO_ENCONTRADO = "ITEM N√O ENCONTRADO!";
	public static String RESERVA_NAO_ENCONTRADA = "RESERVA N√O ENCONTRADO!";
	public static String NA0_EXISTE_CONEXAO = "N„o foi possÌvel estabelecer uma conex„o, verifique sua rede.";
	public static String ERRO_LOGAR = "SENHA/LOGIN ERRADO!";
	public static String ERROR_TIPOUSUARIO_NULL = "N„o foi possÌvel encontra os tipos de Usu·rio";
	
	// Campos n„o preenchidos corretamente
	public static String MSG_ErroPreencheCampo = "CAMPO N√O PREENCHIDO";
	public static String MSG_ErroSpinnerEscolha = "Escolha Outro Item.";
	public static String MSG_ErroSenhaDiferentes ="As senhas n„o correspondem";
	public static String MSG_ErroEmailInvalido ="E-mail inv·lido";	
	public static String MSG_ErroTamanhoInvalidoNome ="Campo com Tamanho inv·lido(MIN 8/Max 40)";
	public static String MSG_ErroCaracterEspecial ="Campo N√O permite caracteres especiais";
	public static String MSG_ErroTamanhoInvalidoCPF ="Tamanho inv·lido(Min,Max 11)";
	public static String MSG_ErroTamanhoInvalidoFone ="Tamanho inv·lido(Max 10)";
	public static String MSG_ErroIdadeInvalida ="Idade entre 14 - 100 Anos";
	
	//String para avaliaÁ„o do conte˙do dos campos
	 public static final String STRING_PATTERN = "[0-9a-zA-Z·‡‚„ÈËÍÌÔÛÙıˆ˙¸ÁÒ¡¿¬√…»Õœ”‘’÷⁄‹«— ,-]*";
	 public static final String PASSWORD_PATTERN = "(?=.*[0-9@#$%^&+=])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,25}";
}
