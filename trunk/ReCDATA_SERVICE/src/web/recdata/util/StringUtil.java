package web.recdata.util;

public class StringUtil {

	public static boolean ehVazio(String value) {
		return (value == null 
				|| (value != null && value.trim().equals("")));
	}
}
