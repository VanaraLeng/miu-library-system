package dataaccess;

import java.util.HashMap;

import business.Book;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member);
	public void saveNewBook(Book book);
	public void saveBookCopy(Book book);
	public void saveCheckoutRecord(LibraryMember member);
	public void updateMember(LibraryMember member);
	public void updateBook(Book book);
}
