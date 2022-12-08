package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import utility.DataUtil;

public class CheckoutRecord implements Serializable {
	private static final long serialVersionUID = -63976228084869913L;
	private LibraryMember member;
	private List<CheckoutEntry> checkoutEntries = new ArrayList<CheckoutEntry>();
	
	
	
	CheckoutRecord(LibraryMember member, BookCopy bookCopy) {
		this.member = member;
		checkoutEntries.add(new CheckoutEntry(LocalDate.now(), bookCopy));
	}
	
//	CheckoutRecord(LibraryMember member, LocalDate checkoutDate) {
//		this.member = member;
//	}
	
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
	
	public void addCheckoutEntry(CheckoutEntry entry) {
		checkoutEntries.add(entry);
	}
	
	public List<String[]> getDataModel() {
		ArrayList<String[]> data = new ArrayList<>();
		for (CheckoutEntry ce : checkoutEntries) {
			String[] record = new String[3];
			record[0] = ce.getBookCopy().getBook().getTitle();
			record[1] = DataUtil.dateString(ce.getDueDate());
			record[2] = DataUtil.dateString(ce.getCheckoutDate());
			data.add(record);
		}
		return data;
	}
}
