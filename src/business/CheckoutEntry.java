package business;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

public class CheckoutEntry implements Serializable {
	private static final long serialVersionUID = -696228114869914L;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BookCopy bookCopy;
	
	CheckoutEntry(LocalDate checkoutDate, BookCopy bookCopy) {
		this.checkoutDate = checkoutDate;
		this.bookCopy = bookCopy;
		this.dueDate = setDueDate(checkoutDate, bookCopy.getBook().getMaxCheckoutLength());
	}
	
	private static LocalDate setDueDate(LocalDate checkoutDate, int maxCheckoutLength) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(checkoutDate.toString()));
		} catch(NullPointerException | ParseException e) {
			System.out.println("set due date parse error");
		}
		c.add(Calendar.DATE, maxCheckoutLength);		
		return c.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public LocalDate getCheckoutDate() {
		return this.checkoutDate;
	}
	
	public LocalDate getDueDate() {
		return this.dueDate;
	}
	
	public BookCopy getBookCopy() {
		return this.bookCopy;
	}
	
	public boolean isDue() { 
		return LocalDate.now().isAfter(dueDate);
	}
	
	@Override
	public String toString() {
		return bookCopy.getBook().getTitle() + ", " + checkoutDate.toString() + ", " + dueDate.toString();
	}
}
