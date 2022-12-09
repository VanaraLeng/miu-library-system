package business;

import java.time.LocalDate;
import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	
	public List<LibraryMember> getAllMembers();
	public void addMember(LibraryMember member) throws LibrarySystemException;
	public void addBook(Book book) throws LibrarySystemException;
	public void addBookCopy(String isbn) throws LibrarySystemException;
	public void updateMember(LibraryMember member) throws LibrarySystemException;
	public void updateBook(Book book) throws LibrarySystemException;
	
	public LibraryMember getMember(String mId) throws LibrarySystemException;
//	public CheckoutRecord createCheckoutRecord(LibraryMember member, BookCopy bookCopy) throws LibrarySystemException;
	public BookCopy getBookCopy(String isbn) throws LibrarySystemException;
	public void checkoutBookCopy(CheckoutRecord rec, BookCopy book) throws LibrarySystemException;
	
//	public CheckoutRecord checkoutBook(String mId, String isbn) throws LibrarySystemException;
	public CheckoutRecord getCheckoutRecord(String mId) throws LibrarySystemException;
	public Book getBook(String isbn) throws LibrarySystemException;
	
	public String createMemberId();
}
