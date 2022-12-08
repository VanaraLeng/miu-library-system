package myLibrarySystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import librarysystem.AllMemberIdsWindow;
import librarysystem.authentication.LoginWindow;
import librarysystem.mainUI.MainUI;
import librarysystem.managebook.AddBookPanel;

public class Menu extends JPanel {
	JButton btnAddMember;
	JButton btnLoginLogout;
	JButton btnAddBook;
	JButton btnAddBookCopy;
	JButton btnCheckoutBook;
	JButton btnCheckoutRecord;
	JButton btnCheckoutOverdue;
	JButton btnAllMembers;
	JButton btnAllBooks;
	
	public Menu() {
		setBounds(0, 0, 174, 397);
    	setLayout(null);
    	
    	btnAddMember = new JButton("Add Member");
    	btnAddMember.setBounds(10, 65, 154, 21);
    	btnAddMember.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			MainUI.INSTANCE.setMainPanel(AddMemberWindow.INSTANCE);
    		}
    	});
    	add(btnAddMember);
    	
    	btnLoginLogout = new JButton("Login/Logout");
    	btnLoginLogout.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			MainUI.INSTANCE.setMainPanel(LoginWindow.INSTANCE);
    		}
    	});
    	btnLoginLogout.setBounds(10, 27, 154, 21);
    	add(btnLoginLogout);
    	
    	btnAddBook = new JButton("Add Book");
    	btnAddBook.setBounds(10, 103, 154, 21);
    	btnAddBook.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			MainUI.INSTANCE.setMainPanel(new AddBookPanel());
    		}
    	});
    	add(btnAddBook);
    	
    	btnAddBookCopy = new JButton("Add Book Copy");
    	btnAddBookCopy.setBounds(10, 141, 154, 21);
    	btnAddBookCopy.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			MainUI.INSTANCE.setMainPanel( AddBookCopyWindow.INSTANCE);
    		}
    	});
    	add(btnAddBookCopy);
    	
    	btnCheckoutBook = new JButton("Checkout Book");
    	btnCheckoutBook.setBounds(10, 179, 154, 21);
    	btnCheckoutBook.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			MainUI.INSTANCE.setMainPanel(new AddBookPanel());
    		}
    	});
    	add(btnCheckoutBook);
    	
    	btnCheckoutRecord = new JButton("Checkout Record");
    	btnCheckoutRecord.setBounds(10, 217, 154, 21);
    	btnCheckoutRecord.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			MainUI.INSTANCE.setMainPanel(new AddBookPanel());
    		}
    	});
    	add(btnCheckoutRecord);
    	
    	btnCheckoutOverdue = new JButton("Checkout Overdue");
    	btnCheckoutOverdue.setBounds(10, 255, 154, 21);
    	btnCheckoutOverdue.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			MainUI.INSTANCE.setMainPanel(new AddBookPanel());
    		}
    	});
    	add(btnCheckoutOverdue);
    	
    	btnAllMembers = new JButton("All Members");
    	btnAllMembers.setBounds(10, 293, 154, 21);
    	btnAllMembers.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			MainUI.INSTANCE.setMainPanel( AllMemberIdsWindow.INSTANCE);
    		}
    	});
    	add(btnAllMembers);
    	
    	btnAllBooks = new JButton("All Books");
    	btnAllBooks.setBounds(10, 331, 154, 21);
    	btnAllBooks.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			MainUI.INSTANCE.setMainPanel(new AddBookPanel());
    		}
    	});
    	add(btnAllBooks);
	}
	
	private static final long serialVersionUID = 1L;

}
