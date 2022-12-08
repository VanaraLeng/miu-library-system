package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private CheckoutRecord checkoutRecord;
	
	public LibraryMember(String memberId, String fname, String lname, String tel, Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;
		this.checkoutRecord = new CheckoutRecord(this);
	}
	
	public String getMemberId() {
		return memberId;
	}

//	public CheckoutRecord addCheckoutRecord(BookCopy bookCopy) {
//		return new CheckoutRecord(this, bookCopy);
//	}

	public CheckoutRecord getCheckoutRecord() {
		return this.checkoutRecord;
	}
	
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
