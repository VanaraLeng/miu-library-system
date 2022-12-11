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
	
	public static String printRecord(
			String[] data,
			int[] columnCount) {
		
		String result = "";

		for (int i = 0; i < data.length; i++) {
			String rec = data[i];
			int remainSpace = columnCount[i] - rec.length();
			
			result += rec;
			for (int j = 0; j < remainSpace; j++) {
				result += " ";
			}			
		}
		return result;
	}
	
}
