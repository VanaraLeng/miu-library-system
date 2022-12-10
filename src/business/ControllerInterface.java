package business;

import java.util.List;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public void logout();
	
	public List<String> allMemberIds();
	public List<String> allBookIds();
	
	public List<LibraryMember> getAllMembers();
	public List<Book> getAllBooks();
	public LibraryMember getMember(String mId) throws LibrarySystemException;
	public Book getBook(String isbn) throws LibrarySystemException;
	
	public void addMember(LibraryMember member) throws LibrarySystemException;
	public void addBook(Book book) throws LibrarySystemException;
	public void addBookCopy(String isbn) throws LibrarySystemException;
	public void updateMember(LibraryMember member) throws LibrarySystemException;
	public void updateBook(Book book) throws LibrarySystemException;
	
	public BookCopy getBookCopy(String isbn) throws LibrarySystemException;
	public void checkoutBookCopy(CheckoutRecord rec, BookCopy book) throws LibrarySystemException;
	public CheckoutRecord getCheckoutRecord(String mId) throws LibrarySystemException;
	
	public String createMemberId();
}
