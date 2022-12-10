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
	private static Auth currentAuth = null;
	
	private int memberId = initializeMemberId();
	
	@Override
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

	
	public static Auth getCurrentAuth() {
		return currentAuth;
	}
	
	public static void setCurrentAuth(Auth auth) {
		currentAuth = auth;
	}
	
	@Override
	public void logout() {
		currentAuth=null;
		Main.main(null);
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
	public List<LibraryMember> getAllMembers() {
		DataAccess da = new DataAccessFacade();
		return  new ArrayList<LibraryMember>(da.readMemberMap().values());
	}
	
	@Override
	public List<Book> getAllBooks() {
		DataAccess da = new DataAccessFacade();
		return new ArrayList<Book>(da.readBooksMap().values());
	}
	
	@Override
	public void addMember(LibraryMember member) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> memberMap = da.readMemberMap();
		if (memberMap.containsKey(member.getMemberId())) {
			throw new LibrarySystemException("Member id already exist");
		}
		da.saveNewMember(member);
	}
	
	@Override
	public void updateMember(LibraryMember member) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> memberMap = da.readMemberMap();
		if (!memberMap.containsKey(member.getMemberId())) {
			throw new LibrarySystemException("Member does not already exist");
		}
		da.updateMember(member);
	}
	
	@Override
	public void addBook(Book book) throws LibrarySystemException {
		//check if book is already exist
		DataAccess da = new DataAccessFacade();
		HashMap<String,Book> map = da.readBooksMap();
		if (map.containsKey(book.getIsbn())) {
			throw new LibrarySystemException("Book with ISBN " + book.getIsbn() + " already existed!");
		}
		da.saveNewBook(book);
	}
	
	@Override
	public void updateBook(Book book) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> bookMap = da.readBooksMap();
		if (!bookMap.containsKey(book.getIsbn())) {
			throw new LibrarySystemException("Book does not exist");
		}
		da.updateBook(book);
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
	public LibraryMember getMember(String mId) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> memberMap = da.readMemberMap();
		if(!memberMap.containsKey(mId)) {
			throw new LibrarySystemException("ID " + mId + " not found");
		}
		return memberMap.get(mId);
	}
	
	@Override
	public BookCopy getBookCopy(String isbn) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> bookMap = da.readBooksMap();
		Book book = bookMap.get(isbn);
		if (book == null) {
			throw new LibrarySystemException("ID " + isbn + " not found"); 
		}
		return book.getNextAvailableCopy();
	}
	
	@Override
	public void checkoutBookCopy(CheckoutRecord rec, BookCopy bookCopy) throws LibrarySystemException {
		if (!bookCopy.isAvailable()) {
			throw new LibrarySystemException("Book is not available");
		}
		bookCopy.changeAvailability();
		CheckoutEntry entry = new CheckoutEntry(LocalDate.now(), bookCopy);
		rec.addCheckoutEntry(entry);
		DataAccess da = new DataAccessFacade();
		da.updateBook(bookCopy.getBook());
		da.updateMember(rec.getMember());
	}
		
	@Override
	public CheckoutRecord getCheckoutRecord(String mId) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> memberMap = da.readMemberMap();
		if (!memberMap.containsKey(mId)) {
			throw new LibrarySystemException("Member not found");
		}
		return memberMap.get(mId).getCheckoutRecord();
	}
	
	@Override
	public Book getBook(String isbn) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> bookMap = da.readBooksMap();
		if (!bookMap.containsKey(isbn)) {
			throw new LibrarySystemException("Book not found");
		}		
		return bookMap.get(isbn);
	}
	
	@Override
	public String createMemberId() {
		int currentId = memberId;
		memberId++;
		return Integer.toString(currentId);
	}
	
	private static int initializeMemberId() {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> memberMap = da.readMemberMap();
		String greatest = "";
		for (String key: memberMap.keySet()) {
			if (key.compareTo(greatest) > 0) {
				greatest = key;
			}
		}
		return Integer.parseInt(greatest)+1;
	}
	
}
