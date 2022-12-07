package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}
	
	
	@Override
	public void addMember(String mId, String fname, String lname, String street, String city, String state, String zip, String tel) {
		//data validation here!!
		Address address = new Address(street, city, state, zip);
		LibraryMember member = new LibraryMember(mId, fname, lname, tel, address);
		DataAccess da = new DataAccessFacade();
		da.saveNewMember(member);
	}
	
	@Override
	public void addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors, int numCopy) throws LibrarySystemException {
		//check if book is already exist
		DataAccess da = new DataAccessFacade();
		HashMap<String,Book> map = da.readBooksMap();
		if (map.containsKey(isbn)) {
			throw new LibrarySystemException("Book with ISBN " + isbn + " already existed!");
		}
		
		//add new book
		Book book = new Book(isbn, title, maxCheckoutLength, authors);
		for (int i = 1; i < numCopy; i++) {
			book.addCopy();
		}
		da.saveNewBook(book);
	}
	
	@Override
	public void addBookCopy(String isbn) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		HashMap<String,Book> map = da.readBooksMap();
		if(!map.containsKey(isbn)) {
			throw new LibrarySystemException("Book with ISBN " + isbn + " not found!");
		}
		
		Book book = map.get(isbn);
		book.addCopy();
		da.saveBookCopy(book);
	}
	
	@Override
	public void checkoutBook(String mId, String isbn) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> memberMap = da.readMemberMap();
		if(!memberMap.containsKey(mId)) {
			throw new LibrarySystemException("ID " + mId + " not found");
		}
		
		HashMap<String,Book> bookMap = da.readBooksMap();
		if(!bookMap.containsKey(isbn)) {
			throw new LibrarySystemException("Book with ISBN " + isbn + " not found!");
		}
		
		if (!bookMap.get(isbn).isAvailable()) {
			throw new LibrarySystemException("Book is not available.");
		}
		
		LibraryMember member = memberMap.get(mId);
		Book book = bookMap.get(isbn);
		member.addCheckoutRecord(LocalDate.now(), book.getNextAvailableCopy());
		da.saveCheckoutRecord(member);
	}
	
	public static Author createAuthor(String fn, String ln, String tel, String bio, String street, String city, String state, String zip) {
		Address address = new Address(street, city, state, zip);
		return new Author(fn, ln, tel, address, bio);
	}
	
}
