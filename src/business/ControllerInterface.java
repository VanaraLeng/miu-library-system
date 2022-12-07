package business;

import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	
	public void addMember(String mId, String fname, String lname, String street, String city, String state, String zip, String tel);
	public void addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors, int numCopy) throws LibrarySystemException;
	public void addBookCopy(String isbn) throws LibrarySystemException;
	public void checkoutBook(String mId, String isbn) throws LibrarySystemException;
}
