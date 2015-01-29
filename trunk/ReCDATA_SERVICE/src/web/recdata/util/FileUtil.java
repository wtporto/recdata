package web.recdata.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Utils para manipulação de arquivos.
 * 
 * @author Rhavy
 */
public class FileUtil {

	private static String SERVER_PATH = "C:\\Java\\web\\uploadFile\\";

	public static String PDF_FILE = "pdf";

	/**
	 * Salvar os arquivo no diretório do servidor.
	 * 
	 * @param content
	 * @param filename
	 * @throws IOException
	 */
	public static void writeFile(byte[] content, String filename) 
			throws IOException {

		File file = new File(SERVER_PATH + filename);
		
		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();
	}
}
