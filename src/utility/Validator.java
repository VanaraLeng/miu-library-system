package utility;

import java.util.List;

public class Validator {
	
	public static boolean isFilled(String val) {
		return val != null && !val.isEmpty();
	}
	
	public static boolean isCorrectPhoneNumber(String val) {
		return val != null && val.length() == 10;
	}
	
	public static boolean isCorrectISBN(String val) {
		return val != null && val.length() == 13;
	}
	
	public static boolean isNotEmpty(List list) {
		return list != null && list.isEmpty(); 
	}
}
