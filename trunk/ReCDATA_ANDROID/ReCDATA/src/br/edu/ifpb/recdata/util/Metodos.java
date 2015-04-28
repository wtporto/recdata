package br.edu.ifpb.recdata.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Metodos {

public static String converteTomillInDate(String dateInMillis){
		
		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		TimeZone.setDefault(tz);
		Calendar calendar = Calendar.getInstance(tz);

		long milli = Long.valueOf(dateInMillis).longValue();

				calendar.setTimeInMillis(milli);
		
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = calendar.getTime();
				String strDate = sdf.format(date);

				System.out.println("Date>>"+strDate);
				 
		return strDate;
		
	}
}
