package business;

import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord {
	private LibraryMember member;
	private List<CheckoutEntry> checkoutEntries = new ArrayList<CheckoutEntry>();
	
	CheckoutRecord(LibraryMember member) {
		this.member = member;
	}
}
