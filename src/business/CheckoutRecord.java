package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord {
	private LibraryMember member;
	private List<CheckoutEntry> checkoutEntries = new ArrayList<CheckoutEntry>();
	
	CheckoutRecord(LibraryMember member, LocalDate checkoutDate, BookCopy bookCopy) {
		this.member = member;
		checkoutEntries.add(new CheckoutEntry(checkoutDate, bookCopy));
	}
	
	public LibraryMember getMember() {
		return this.member;
	}
	
	public List<CheckoutEntry> getCheckoutEntries() {
		return this.checkoutEntries;
	}
	
	@Override
	public String toString() {
		return "CheckoutRecord for: " + member.getFirstName() + ", " + member.getLastName() + checkoutEntries;
	}
}
