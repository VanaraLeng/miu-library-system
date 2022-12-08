package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private List<CheckoutRecord> checkoutRecords;
	
	public LibraryMember(String memberId, String fname, String lname, String tel, Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;	
		this.checkoutRecords = new ArrayList<>();
	}
	
	public String getMemberId() {
		return memberId;
	}

	public CheckoutRecord addCheckoutRecord(LocalDate checkoutDate, BookCopy bookCopy) {
		if (checkoutRecords == null) {
			checkoutRecords = new ArrayList<>();
		}
		CheckoutRecord cr = new CheckoutRecord(this, checkoutDate, bookCopy);
		checkoutRecords.add(cr);
		
		return cr;
	}

	
	public List<CheckoutRecord> getCheckoutRecords() {
		return this.checkoutRecords;
	}
	
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
