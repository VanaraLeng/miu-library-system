package utility;

import java.util.List;

public class Validator {
	
	public static boolean isFilled(String val) {
		return val != null && !val.isEmpty();
	}
	
	public static boolean isCorrectPhoneNumber(String val) {
		boolean correctLength = val != null && val.length() == 12;
		if (!correctLength) {
			return false;
		}
		
		if (val.charAt(4) != '-' && val.charAt(7) != '-') {
			return false;
		}
		
		return isNumeric(val.substring(0, 2))
				&& isNumeric(val.substring(4, 7))
				&& isNumeric(val.substring(8, 11));
	}
	
	public static boolean isCorrectISBN(String val) {
		
		boolean correctLength = val != null && val.length() == 8;
		
		if (!correctLength) {
			return false;
		}
		
		if (val.charAt(2) != '-') {
			return false;
		}
		
		return isNumeric(val.substring(0, 1)) && isNumeric(val.substring(3, 7));
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
