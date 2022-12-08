package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUtil {
	
	public static int getInt(String text) {
		if (text == null ) {
			return -1;
		}
		try {
			int intVal = Integer.parseInt(text);
			return intVal;
		} catch(NumberFormatException e) {
			return -1;
		}
	}
	
	public static String dateString(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return formatter.format(date);
	}
	
}
