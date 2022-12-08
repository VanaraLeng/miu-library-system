package utility;

import java.util.List;

public class Validator {
	
	public static boolean isFilled(String val) {
		return val != null && !val.isEmpty();
	}
	
	public static boolean isCorrectPhoneNumber(String val) {
		return val != null && val.length() == 10 && isNumeric(val);
	}
	
	public static boolean isCorrectISBN(String val) {
		return val != null && val.length() == 13 && isNumeric(val);
	}
	
	public static boolean isNotEmpty(List list) {
		return list != null && list.isEmpty(); 
	}
	
	public static boolean isCorrectZipCode(String val) {
		return val != null && val.length() == 5 && isNumeric(val);
	}
	
	public static boolean isNumeric(String val) {
		try {
			Double.parseDouble(val);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static boolean isValidCheckoutLength(int n) {
		return n == 7 || n == 21;
	}
}
